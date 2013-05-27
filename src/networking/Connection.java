/*
 * Connection regelt die serverseitige Protokollarbeit
 */
package networking;

import generated.ErrorType;
import generated.MazeCom;
import generated.MazeComType;
import generated.MoveMessageType;

import java.io.IOException;
import java.net.Socket;

import server.Board;
import server.Player;

public class Connection {

	private Socket socket;
	private Player p;
	private XmlInStream inFromClient;
	private XmlOutStream outToClient;
	private MazeComMessageFactory mcmf;

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
		this.p = new Player(newId, this);
		LoginThread lt = new LoginThread(this.inFromClient, this, this.p, newId);
		lt.start();
		return this.p;
	}

	/**
	 * Anfrage eines Zuges beim Spieler
	 * 
	 * @param brett
	 *            aktuelles Spielbrett
	 * @return Zug des Spielers
	 */
	public MoveMessageType awaitMove(Board brett) {
		this.sendMessage(this.mcmf.createAwaitMoveMessage(this.p.getID(), brett));
		MazeCom result = this.receiveMessage();
		if (result.getMcType() == MazeComType.MOVE) {
			return result.getMoveMessage();
		} else {
			this.sendMessage(this.mcmf.createErrorMessage(this.p.getID(),
					ErrorType.AWAIT_MOVE));
			return null;
		}
	}

	/**
	 * Erhaltener Move ist falsch gewesen => Fehler senden und neuen AwaitMove
	 * sende!
	 * 
	 * @param brett
	 *            aktuelles Spielbrett
	 * @return Zug des Spielers
	 */
	public MoveMessageType illigalMove(Board brett) {
		this.sendMessage(this.mcmf.createErrorMessage(this.p.getID(),
				ErrorType.ILLEGAL_MOVE));
		return this.awaitMove(brett);
	}

	/**
	 * sendet dem Spieler den Namen des Gewinners sowie dessen ID und das
	 * Schlussbrett
	 * 
	 * @param winnerId
	 * @param name
	 * @param b
	 */
	public void sendWin(int winnerId, String name, Board b) {
		this.sendMessage(this.mcmf.createWinMessage(this.p.getID(), winnerId,
				name, b));
		try {
			this.inFromClient.close();
			this.outToClient.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Senden, dass Spieler diconnected wurde
	 * */
	public void disconnect() {
		this.sendMessage(this.mcmf.createDisconnectMessage(this.p.getID(),
				this.p.getName()));
		try {
			this.inFromClient.close();
			this.outToClient.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
