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
	
	public LoginThread(XmlInStream inFromClient, Connection c, Player p,
			int newId) {
		this.in = new XmlInStream(inFromClient);
		this.p = p;
		this.con = c;
		this.mcmf = new MazeComMessageFactory();
	}

	public void run() {
		MazeCom loginMes = this.in.readMazeCom();
		int failCounter = 0;
		// TODO FailCounter auf Settings anpassen
		while (failCounter < Settings.LOGINTRIALS) {
			// Test ob es sich um eine LoginNachricht handelt
			if (loginMes.getMcType() == MazeComType.LOGIN) {
				// sende Reply
				this.con.sendMessage(this.mcmf.createLoginReplyMessage(this.p
						.getID()));
				this.p.setName(loginMes.getLoginMessage().getName());
				break;
			} else {
				// Sende Fehler
				this.con.sendMessage(this.mcmf.createErrorMessage(-1,
						ErrorType.AWAIT_LOGIN));
				failCounter++;
				// nach einem Fehler auf den nächsten Versuch warten
				loginMes = this.in.readMazeCom();
			}
		}
	}
}
