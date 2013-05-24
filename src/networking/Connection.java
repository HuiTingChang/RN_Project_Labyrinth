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

import server.Board;
import server.Player;

public class Connection {

	Socket socket;
	XmlInStream inFromClient;
	XmlOutStream outToClient;
	MazeComMessageFaktory mcmf;

	public Connection(Socket s) {
		// TODO TCP-Verbindungsaufbau und erzeugen der Streams
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
		this.mcmf = new MazeComMessageFaktory();
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
		MazeCom loginMes = this.inFromClient.readMazeCom();
		
		Player p = null;
		// Test ob es sich um eine LoginNachricht handelt
		if (loginMes.getMcType() == MazeComType.LOGIN) {
			//sende Reply
			this.outToClient.write(this.mcmf.createLoginReply(newId));
			//TODO Spieler anlegen!
			return p;
		} else {
			//Sende Fehler
			this.outToClient.write(this.mcmf.createError(-1, ErrorType.WRONGMESSAGE));
			return null;
		}
	}

	public MoveMessageType awaitMove(Board brett) {
		// TODO sende Ihm das neue Brett
		return null;
	}

	public void sendWin(int winnerId, String name) {
		// TODO sende Ergebnis raus
	}

	public void disconnect() {
		// TODO Erronachricht generieren und Verbindung abbrechen
	}

	private MazeCom generateMazeCom(MazeComType msgType, Object[] parameter) {
		MazeCom result = null;
		switch (msgType) {
		// Erstellen einer Loginnachricht
		case LOGIN:
			break;
		// Erstellen einer Loginantwortnachricht
		case LOGINREPLY:
			break;
		case AWAITMOVE:
			break;
		case ACCEPT:
			break;
		case WIN:
			break;
		case MOVE:
			break;
		case DISCONNECT:
			break;
		case ERROR:
			break;
		default:
			break;
		}
		return result;
	}

}
