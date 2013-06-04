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

import config.Settings;

import Timeouts.TimeOutManager;

import server.Board;
import server.Game;
import server.Player;

public class Connection {

	private Socket socket;
	private Player p;
	private XmlInStream inFromClient;
	private XmlOutStream outToClient;
	private MazeComMessageFactory mcmf;
	private TimeOutManager tom;
	private Game currentGame;

	/**
	 * Speicherung des Sockets und oeffnen der Streams
	 * 
	 * @param s
	 *            Socket der Verbindung
	 */
	public Connection(Socket s, Game g) {
		// TODO entfernen => nur fuer Test
		this.socket = s;
		this.currentGame = g;
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
		this.tom = new TimeOutManager();
	}

	/**
	 * Allgemeines senden einer fertigen MazeCom-Instanz
	 */
	public void sendMessage(MazeCom mc) {
		// Timer starten, der beim lesen beendet wird
		// Ablauf Timer = Problem User
		this.tom.startSendMessageTimeOut(this.p.getID(), this);
		this.outToClient.write(mc);
	}

	/**
	 * Allgemeines empfangen einer MazeCom-Instanz
	 * 
	 * @return
	 */
	public MazeCom receiveMessage() {
		MazeCom result = this.inFromClient.readMazeCom();
		this.tom.stopSendMessageTimeOut(this.p.getID());
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
	 * @return Valieder Zug des Spielers oder NULL
	 */
	public MoveMessageType awaitMove(Board brett, int tries) {
		this.sendMessage(this.mcmf.createAwaitMoveMessage(this.p.getID(), brett));
		MazeCom result = this.receiveMessage();
		if (result.getMcType() == MazeComType.MOVE) {
			// Antwort mit NOERROR

			if (this.currentGame.getBoard().validateTransition(
					result.getMoveMessage(), this.p.getID())) {
				this.sendMessage(this.mcmf.createAcceptMessage(this.p.getID(),
						ErrorType.NOERROR));
				return result.getMoveMessage();
			} else if (tries < Settings.MOVETRIES)
				return illigalMove(brett, ++tries);
			else {
				disconnect(ErrorType.TOO_MANY_TRIES);
				return null;
			}

		} else {
			this.sendMessage(this.mcmf.createAcceptMessage(this.p.getID(),
					ErrorType.AWAIT_MOVE));
			if (tries < Settings.MOVETRIES)
				return awaitMove(brett, ++tries);
			else {
				disconnect(ErrorType.TOO_MANY_TRIES);
			}
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
	public MoveMessageType illigalMove(Board brett, int tries) {
		this.sendMessage(this.mcmf.createAcceptMessage(this.p.getID(),
				ErrorType.ILLEGAL_MOVE));
		if (tries < Settings.MOVETRIES)
			return this.awaitMove(brett, tries);
		else {
			disconnect(ErrorType.TOO_MANY_TRIES);
			return null;
		}
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
	public void disconnect(ErrorType et) {
		this.sendMessage(this.mcmf.createDisconnectMessage(this.p.getID(),
				this.p.getName(), et));
		try {
			this.inFromClient.close();
			this.outToClient.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//entfernen des Spielers
		this.currentGame.removePlayer(this.p.getID());
	}
}
