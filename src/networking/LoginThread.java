package networking;

import config.Settings;
import server.Player;
import generated.ErrorType;
import generated.MazeCom;
import generated.MazeComType;

public class LoginThread extends Thread {

	private XmlInStream in;
	private Connection con;
	private Player p;
	private MazeComMessageFactory mcmf;

	public LoginThread(XmlInStream inFromClient, Connection c, Player p) {
		this.in = inFromClient;
		this.p = p;
		this.con = c;
		this.mcmf = new MazeComMessageFactory();
	}

	public void run() {
		MazeCom loginMes = this.in.readMazeCom();
		int failCounter = 0;
		while (failCounter < Settings.LOGINTRIES) {
			// Test ob es sich um eine LoginNachricht handelt
			if (loginMes.getMcType() == MazeComType.LOGIN) {
				// sende Reply
				this.con.sendMessage(this.mcmf.createLoginReplyMessage(this.p
						.getID()));
				this.p.init(loginMes.getLoginMessage().getName());
				return;// verlassen des Threads
			} else {
				// Sende Fehler
				this.con.sendMessage(this.mcmf.createAcceptMessage(-1,
						ErrorType.AWAIT_LOGIN));
				failCounter++;
				// nach einem Fehler auf den nächsten Versuch warten
				loginMes = this.in.readMazeCom();
			}
		}
		// Verlassen mit schwerem Fehlerfall
		this.con.disconnect(ErrorType.TOO_MANY_TRIES);
	}
}
