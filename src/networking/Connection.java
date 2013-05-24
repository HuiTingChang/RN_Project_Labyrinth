/*
 * Connection regelt die serverseitige Protokollarbeit
 */
package networking;

import generated.ErrorType;
import generated.MazeCom;
import generated.MazeComType;
import generated.MoveMessageType;
import generated.ObjectFactory;
import generated.WinMessageType;

import java.io.IOException;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import server.Board;
import server.Player;

public class Connection {

	// TODO ID aus dem Player holen => ID löschen bzw. Namen
	private int playerID = -1;
	private Player p;
	private Socket socket;
	private XmlInStream inFromClient;
	private XmlOutStream outToClient;
	private MazeComMessageFactory mcmf;
	private String playerName;

	/**
	 * Speicherung des Sockets und oeffnen der Streams
	 * 
	 * @param s
	 *            Socket der Verbindung
	 */
	public Connection(Socket s) {
		this.socket = s;
		try {
			this.inFromClient = new XmlInStream(this.socket.getInputStream());
		} catch (IOException e) {
			System.err
					.println("[ERROR]: Inputstream konnte nicht geoeffnet werden");
		}
		try {
			this.outToClient = new XmlOutStream(this.socket.getOutputStream());
		} catch (IOException e) {
			System.err
					.println("[ERROR]: Outputstream konnte nicht geoeffnet werden");
		}
		this.mcmf = new MazeComMessageFactory();
	}

	/**
	 * Allgemeines senden einer fertigen MazeCom-Instanz
	 */
	public void sendMessage(MazeCom mc) {
		this.outToClient.write(mc);
	}

	/**
	 * Allgemeines empfangen einer MazeCom-Instanz
	 * 
	 * @return
	 */
	public MazeCom receiveMessage() {
		MazeCom result = this.inFromClient.readMazeCom();
		return result;
	}

	/**
	 * Allgemeines erwarten eines Login
	 * 
	 * @param newId
	 * @return Neuer Player, bei einem Fehler jedoch null
	 */
	public Player login(int newId) {
		MazeCom loginMes = this.receiveMessage();

		this.p = null;
		// Test ob es sich um eine LoginNachricht handelt
		if (loginMes.getMcType() == MazeComType.LOGIN) {
			// sende Reply
			this.playerID = newId;
			this.playerName = loginMes.getLoginMessage().getName();
			this.sendMessage(this.mcmf.createLoginReplyMessage(this.playerID));
			// TODO Spieler anlegen! Daten setzten
			return p;
		} else {
			// Sende Fehler
			this.sendMessage(this.mcmf.createErrorMessage(-1,
					ErrorType.WRONGLOGIN));
			return this.p;
		}
	}

	public MoveMessageType awaitMove(Board brett) {
		// TODO sende Ihm das neue Brett
		return null;
	}

	public void sendWin(int winnerId, String name, Board b) {
		// this.sendMessage(this.mcmf.createWinMessage(this.playerID,winnerId,name,b));
	}

	/**
	 * Senden, dass Spieler diconnected wurde
	 * */
	public void disconnect() {
		this.sendMessage(this.mcmf.createDisconnectMessage(this.playerID,
				this.playerName));
	}
}
