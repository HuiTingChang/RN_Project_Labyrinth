package networking;

import server.Player;
import generated.ErrorType;
import generated.MazeCom;
import generated.MazeComType;

public class LoginThread extends Thread {

	private XmlInStream in;
	private Connection con;
	private Player p;
	private MazeComMessageFactory mcmf;
	private int newId;

	public LoginThread(XmlInStream inFromClient, Connection c, Player p,
			int newId) {
		this.in = new XmlInStream(inFromClient);
		this.con = c;
		this.p = p;
		this.mcmf = new MazeComMessageFactory();
		this.newId = newId;
	}

	public void run() {
		MazeCom loginMes = this.con.receiveMessage();
		// Test ob es sich um eine LoginNachricht handelt
		if (loginMes.getMcType() == MazeComType.LOGIN) {
			// sende Reply
			this.con.sendMessage(this.mcmf.createLoginReplyMessage(this.p
					.getID()));
			this.p.setName(loginMes.getLoginMessage().getName());
			// TODO Spieler anlegen! Daten setzten + connection + id + name
		} else {
			// Sende Fehler
			this.con.sendMessage(this.mcmf.createErrorMessage(-1,
					ErrorType.AWAIT_LOGIN));
		}
	}
}
