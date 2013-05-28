package server;

import generated.MoveMessageType;
import generated.TreasureType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import config.Settings;

import networking.Connection;
import Timeouts.TimeOutManager;

public class Game {

	/**
	 * beinhaltet die Spieler, die mit dem Server verbunden sind und die durch
	 * die ID zugreifbar sind
	 */
	private HashMap<Integer, Player> spieler;
	private ServerSocket s;
	private TimeOutManager timeOutMan;
	private Board spielBrett;

	public Game() {
		spieler = new HashMap<Integer, Player>();
		timeOutMan = new TimeOutManager(this);
		try {
			s = new ServerSocket(config.Settings.port);
		} catch (IOException e) {
			System.err
					.println("Port bereits belegt, bitte schließen Sie alle Serverinstanzen");
		}
	}

	/**
	 * Auf TCP Verbindungen warten und den Spielern die Verbindung ermöglichen
	 */
	public void init() {
		try {
			int i = 1;
			boolean accepting = true;
			timeOutMan.startLoginTimeOut();
			while (accepting && i <= 4) {
				try {
					// TODO Was wenn ein Spieler beim Login rausfliegt
					System.out
							.println("Waiting for another Player (" + i + ")");
					Socket mazeClient = s.accept();
					Connection c = new Connection(mazeClient, this);
					spieler.put(i, c.login(i));
				} catch (SocketException e) {
					System.out.println("...Waiting for Player timed out!");
				}
			}
			timeOutMan.stopLoginTimeOut();

			spielBrett = new Board();

		} catch (IOException e) {
			System.err.println("Fehler beim Verbindungsaufbau (game.init()):");
			System.err.println(e.getMessage());
			// e.printStackTrace();
		}

	}

	public void stopLogin() {
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void singleTurn(Integer currPlayer) {
		// TODO
		/**
		 * Connection.awaitMove checken ->Bei Fehler illegalMove->liefert neuen
		 * Zug
		 */
		TreasureType t = spieler.get(currPlayer).getCurrentTreasure();
		spielBrett.setTreasure(t);
		MoveMessageType move = spieler.get(currPlayer).getConToClient()
				.awaitMove(this.spielBrett, 0);
		if (move != null) {
			spielBrett.proceedTurn(move);
		}
	}

	public Board getBoard() {
		return spielBrett;
	}

	/**
	 * Aufraeumen nach einem Spiel
	 */
	public void cleanUp() {

	}

	public boolean someBodyWon() {
		return false;
		// TODO mitteilung an alle Clients, dass einer Gewonnen hat
	}

	public static void main(String[] args) {
		Game currentGame = new Game();
		currentGame.init();
		Integer currPlayer = 1;
		while (!currentGame.someBodyWon()) {
			currentGame.singleTurn(currPlayer);
			currPlayer = currentGame.nextPlayer(currPlayer);
		}
	}

	private Integer nextPlayer(Integer currPlayer) {
		// TODO Auto-generated method stub
		// Soll Verhindern, das bereits vom Spiel
		// ausgeschlossene Spieler nach an die Reihe kommen
		// (noch zu implementieren)
		return ++currPlayer;
	}

	public void removePlayer(int id) {
		this.spieler.remove(id);
	}

}
