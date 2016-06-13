package de.fhaachen.mazenet.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import de.fhaachen.mazenet.config.Settings;
import de.fhaachen.mazenet.generated.ErrorType;
import de.fhaachen.mazenet.generated.MoveMessageType;
import de.fhaachen.mazenet.generated.TreasureType;
import de.fhaachen.mazenet.networking.Connection;
import de.fhaachen.mazenet.server.userInterface.UI;
import de.fhaachen.mazenet.timeouts.TimeOutManager;
import de.fhaachen.mazenet.tools.Debug;
import de.fhaachen.mazenet.tools.DebugLevel;

public class Game extends Thread {

	/**
	 * beinhaltet die Spieler, die mit dem Server verbunden sind und die durch
	 * die ID zugreifbar sind
	 */
	private HashMap<Integer, Player> spieler;
	private ServerSocket serverSocket;
	private TimeOutManager timeOutManager;
	private Board spielBrett;
	/**
	 * Defaultwert -1, solange kein Gewinner feststeht
	 */
	private Integer winner = -1;
	private UI userinterface;
	private int playerCount;
	private List<TreasureType> foundTreasures;

	public Game() {
		Debug.addDebugger(System.out, Settings.DEBUGLEVEL);
		Debug.print(Messages.getString("Game.Constructor"), DebugLevel.VERBOSE); //$NON-NLS-1$
		winner = -1;
		spieler = new HashMap<Integer, Player>();
		timeOutManager = new TimeOutManager();
		foundTreasures = new ArrayList<TreasureType>();
	}

	/**
	 * Auf TCP Verbindungen warten und den Spielern die Verbindung ermoeglichen
	 */
	public void init(int playerCount) {
		
		Debug.print(Messages.getString("Game.initFkt"), DebugLevel.VERBOSE); //$NON-NLS-1$
		// Socketinitialisierung aus dem Constructor in init verschoben. Sonst
		// Errors wegen Thread.
		// init wird von run (also vom Thread) aufgerufen, im Gegesatz zum
		// Constructor
		try {
			
			/*
			TODO Prepare for SSL
			SSLServer server = new SSLServer();

			// Server needs some key material.  We'll use an OpenSSL/PKCS8 style key (possibly encrypted).
			String certificateChain = "/path/to/this/server.crt";
			String privateKey = "/path/to/this/server.key";
			char[] password = "changeit".toCharArray();
			KeyMaterial km = new KeyMaterial( certificateChain, privateKey, password ); 

			server.setKeyMaterial( km );

			// These settings have to do with how we'll treat client certificates that are presented
			// to us.  If the client doesn't present any client certificate, then these are ignored.
			server.setCheckHostname( false ); // default setting is "false" for SSLServer
			server.setCheckExpiry( true );    // default setting is "true" for SSLServer
			server.setCheckCRL( true );       // default setting is "true" for SSLServer

			// This server trusts all client certificates presented (usually people won't present
			// client certs, but if they do, we'll give them a socket at the very least).
			server.addTrustMaterial( TrustMaterial.TRUST_ALL );
			SSLServerSocket ss = (SSLServerSocket) server.createServerSocket( 7443 );
			
			*/
			
			
			
			serverSocket = new ServerSocket(de.fhaachen.mazenet.config.Settings.PORT);
		} catch (IOException e) {
			System.err.println(Messages.getString("Game.portUsed")); //$NON-NLS-1$
		}
		try {
			int i = 1;
			boolean accepting = true;
			timeOutManager.startLoginTimeOut(this);
			// FIXME: wenn sich kein Spieler verbindet, sollte ewig gewartet
			// werden
			while (accepting && i <= playerCount) {
				try {
					// TODO Was wenn ein Spieler beim Login rausfliegt
					Debug.print(Messages.getString("Game.waitingForPlayer") + " (" + i + "/" + playerCount //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							+ ")", DebugLevel.DEFAULT); //$NON-NLS-1$
					// TODO SSLSocket socket = (SSLSocket) ss.accept();
					Socket mazeClient = serverSocket.accept();
					Connection c = new Connection(mazeClient, this, i);
					spieler.put(i, c.login(i));
				} catch (SocketException e) {
					Debug.print(Messages.getString("Game.playerWaitingTimedOut"), //$NON-NLS-1$
							DebugLevel.DEFAULT);
				}
				++i;
			}
			// Warten bis die Initialisierung durchgelaufen ist
			boolean spielbereit = false;
			while (!spielbereit) {
				spielbereit = true;
				for (Integer id : spieler.keySet()) {
					Player p = spieler.get(id);
					if (!p.isInitialized()) {
						spielbereit = false;
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			timeOutManager.stopLoginTimeOut();
			// Spielbrett generieren
			spielBrett = new Board();
			// Verteilen der Schatzkarten
			ArrayList<TreasureType> treasureCardPile = new ArrayList<TreasureType>();
			treasureCardPile.add(TreasureType.SYM_01);
			treasureCardPile.add(TreasureType.SYM_02);
			treasureCardPile.add(TreasureType.SYM_03);
			treasureCardPile.add(TreasureType.SYM_04);
			treasureCardPile.add(TreasureType.SYM_05);
			treasureCardPile.add(TreasureType.SYM_06);
			treasureCardPile.add(TreasureType.SYM_07);
			treasureCardPile.add(TreasureType.SYM_08);
			treasureCardPile.add(TreasureType.SYM_09);
			treasureCardPile.add(TreasureType.SYM_10);
			treasureCardPile.add(TreasureType.SYM_11);
			treasureCardPile.add(TreasureType.SYM_12);
			treasureCardPile.add(TreasureType.SYM_13);
			treasureCardPile.add(TreasureType.SYM_14);
			treasureCardPile.add(TreasureType.SYM_15);
			treasureCardPile.add(TreasureType.SYM_16);
			treasureCardPile.add(TreasureType.SYM_17);
			treasureCardPile.add(TreasureType.SYM_18);
			treasureCardPile.add(TreasureType.SYM_19);
			treasureCardPile.add(TreasureType.SYM_20);
			treasureCardPile.add(TreasureType.SYM_21);
			treasureCardPile.add(TreasureType.SYM_22);
			treasureCardPile.add(TreasureType.SYM_23);
			treasureCardPile.add(TreasureType.SYM_24);
			if (!Settings.TESTBOARD)
				Collections.shuffle(treasureCardPile);
			if (spieler.size() == 0) {
				System.err.println(Messages.getString("Game.noPlayersConnected")); //$NON-NLS-1$
				stopGame();
				return;
			}
			int anzCards = treasureCardPile.size() / spieler.size();
			i = 0;
			for (Integer player : spieler.keySet()) {
				ArrayList<TreasureType> cardsPerPlayer = new ArrayList<TreasureType>();
				for (int j = i * anzCards; j < (i + 1) * anzCards; j++) {
					cardsPerPlayer.add(treasureCardPile.get(j));
				}
				spieler.get(player).setTreasure(cardsPerPlayer);
				++i;
			}
		} catch (IOException e) {
			System.err.println(Messages.getString("Game.errorWhileConnecting")); //$NON-NLS-1$
			System.err.println(e.getMessage());
		}

	}

	public void closeServerSocket() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
			} else
				Debug.print(Messages.getString("Game.serverSocketNull"), DebugLevel.DEFAULT); //$NON-NLS-1$
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Player> playerToList() {
		Debug.print(Messages.getString("Game.playerToListFkt"), DebugLevel.VERBOSE); //$NON-NLS-1$
		List<Player> erg = new ArrayList<Player>();
		for (Integer id : spieler.keySet()) {
			erg.add(spieler.get(id));
		}
		return erg;
	}

	public void singleTurn(Integer currPlayer) {
		/**
		 * Connection.awaitMove checken -> Bei Fehler illegalMove -> liefert
		 * neuen Zug
		 */
		Debug.print(Messages.getString("Game.singleTurnFkt"), DebugLevel.VERBOSE); //$NON-NLS-1$
		userinterface.updatePlayerStatistics(playerToList(), currPlayer);
		TreasureType t = spieler.get(currPlayer).getCurrentTreasure();
		spielBrett.setTreasure(t);
		Debug.print(Messages.getString("Game.boardBeforeMoveFromPlayerWithID") + currPlayer, //$NON-NLS-1$
				DebugLevel.VERBOSE);
		Debug.print(spielBrett.toString(), DebugLevel.DEBUG);
		MoveMessageType move = spieler.get(currPlayer).getConToClient().awaitMove(spieler, this.spielBrett, 0,
				foundTreasures);
		boolean found = false;
		if (move != null) {
			// proceedTurn gibt zurueck ob der Spieler seinen Schatz erreicht
			// hat
			if (spielBrett.proceedTurn(move, currPlayer)) {
				found = true;
				Debug.print(String.format(Messages.getString("Game.foundTreasure"), spieler.get(currPlayer).getName(), //$NON-NLS-1$
						currPlayer), DebugLevel.DEFAULT);
				foundTreasures.add(t);
				// foundTreasure gibt zurueck wieviele
				// Schaetze noch zu finden sind
				if (spieler.get(currPlayer).foundTreasure() == 0) {
					winner = currPlayer;
				}
			}
			userinterface.displayMove(move, spielBrett, Settings.MOVEDELAY, Settings.SHIFTDELAY, found);
		} else {
			Debug.print(Messages.getString("Game.gotNoMove"), DebugLevel.DEFAULT); //$NON-NLS-1$
		}
	}

	public Board getBoard() {
		return spielBrett;
	}

	/**
	 * Aufraeumen nach einem Spiel
	 */
	public void cleanUp() {
		Debug.print(Messages.getString("Game.cleanUpFkt"), DebugLevel.VERBOSE); //$NON-NLS-1$
		if (winner > 0) {
			for (Integer playerID : spieler.keySet()) {
				Player s = spieler.get(playerID);
				s.getConToClient().sendWin(winner, spieler.get(winner).getName(), spielBrett);
			}
			userinterface.updatePlayerStatistics(playerToList(), winner);
			Debug.print(String.format(Messages.getString("Game.playerIDwon"), spieler //$NON-NLS-1$
					.get(winner).getName(), winner), DebugLevel.DEFAULT);

		} else {
			// Iterator<Integer> playerID = spieler.keySet().iterator();
			// while (playerID.hasNext() ) {
			// Player s = spieler.get(playerID.next());
			// s.getConToClient().disconnect(ErrorType.NOERROR);
			// }
			while (spieler.size() > 0) {
				Player s = spieler.get(spieler.keySet().iterator().next());
				s.getConToClient().disconnect(ErrorType.NOERROR);

			}
		}
		stopGame();
	}

	public boolean somebodyWon() {
		return winner != -1;

	}

	public static void main(String[] args) {
		Settings.reload( "/de/fhaachen/mazenet/config/config.properties"); //$NON-NLS-1$
		Locale.setDefault(Settings.LOCALE);
		Game currentGame = new Game();
		currentGame.parsArgs(args);
		currentGame.userinterface = Settings.USERINTERFACE;
		currentGame.userinterface.init(new Board());
		currentGame.userinterface.setGame(currentGame);
	}

	public void setUserinterface(UI userinterface) {
		this.userinterface = userinterface;
	}

	public void parsArgs(String args[]) {
		playerCount = Settings.DEFAULT_PLAYERS;
		for (String arg : args) {
			String playerFlag = "-n"; //$NON-NLS-1$
			if (arg.startsWith(playerFlag)) {
				playerCount = Integer.valueOf(arg.substring(playerFlag.length()));
			}
		}
	}

	public void run() {
		Debug.print(Messages.getString("Game.runFkt"), DebugLevel.VERBOSE); //$NON-NLS-1$
		Debug.print(Messages.getString("Game.startNewGame"), DebugLevel.DEFAULT); //$NON-NLS-1$
		// TODO Configfile austauschbar machen
		init(playerCount);
		if (spieler.isEmpty()) {
			return;
		}
		userinterface.init(spielBrett);
		Integer currPlayer = 1;
		userinterface.updatePlayerStatistics(playerToList(), currPlayer);
		while (!somebodyWon()) {
			Debug.print(String.format(Messages.getString("Game.playersTurn"), spieler.get(currPlayer).getName(), //$NON-NLS-1$
					currPlayer), DebugLevel.DEFAULT);
			singleTurn(currPlayer);
			try {
				currPlayer = nextPlayer(currPlayer);
			} catch (NoSuchElementException e) {
				Debug.print(Messages.getString("Game.AllPlayersLeft"), DebugLevel.DEFAULT); //$NON-NLS-1$
				stopGame();
			}
		}
		cleanUp();
	}

	private Integer nextPlayer(Integer currPlayer) throws NoSuchElementException {
		Debug.print(Messages.getString("Game.nextPlayerFkt"), DebugLevel.VERBOSE); //$NON-NLS-1$
		Iterator<Integer> iDIterator = spieler.keySet().iterator();
		Integer id = -1;
		while (iDIterator.hasNext()) {
			id = iDIterator.next();
			if (id == currPlayer) {
				break;
			}
		}
		if (iDIterator.hasNext()) {
			return iDIterator.next();
		}
		// Erste ID zurueckgeben,
		return spieler.keySet().iterator().next();
	}

	public void removePlayer(int id) {
		Debug.print(Messages.getString("Game.removePlayerFkt"), DebugLevel.VERBOSE); //$NON-NLS-1$
		this.spieler.remove(id);
		Debug.print(String.format(Messages.getString("Game.playerIDleftGame"), id), //$NON-NLS-1$
				DebugLevel.DEFAULT);
	}

	public void stopGame() {
		Debug.print(Messages.getString("Game.stopFkt"), DebugLevel.VERBOSE); //$NON-NLS-1$
		Debug.print(Messages.getString("Game.stopGame"), DebugLevel.DEFAULT); //$NON-NLS-1$
		userinterface.gameEnded(spieler.get(winner));
		winner = -2;
		userinterface.setGame(null);
		timeOutManager.cancel();
		closeServerSocket();
	}
}
