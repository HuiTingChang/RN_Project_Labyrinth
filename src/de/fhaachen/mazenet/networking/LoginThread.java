package de.fhaachen.mazenet.networking;

import java.util.Stack;

import de.fhaachen.mazenet.config.Settings;
import de.fhaachen.mazenet.generated.ErrorType;
import de.fhaachen.mazenet.generated.MazeCom;
import de.fhaachen.mazenet.generated.MazeComType;
import de.fhaachen.mazenet.server.Player;
import de.fhaachen.mazenet.tools.Debug;
import de.fhaachen.mazenet.tools.DebugLevel;

public class LoginThread extends Thread {

	private Connection connection;
	private Player player;
	private MazeComMessageFactory mazeComMessageFactory;
	private Stack<Integer> availablePlayers;

	public LoginThread(Connection connection, Player player,Stack<Integer> availablePlayers) {
		this.player = player;
		this.connection = connection;
		this.mazeComMessageFactory = new MazeComMessageFactory();
		this.availablePlayers = availablePlayers;
	}

	private String cleanUpName(String name) {
		// ersetze alle Zeichen ausser den erlaubten
		// \w A word character: [a-zA-Z_0-9]
		// \p{Punct} Punctuation: One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
		// ausserdem äöüßÄÖÜ und Leerzeichen
		String nameBuffer = name.replaceAll("[^\\w äüöÜÖÄß\\p{Punct}]", ""); //$NON-NLS-1$//$NON-NLS-2$
		return nameBuffer.substring(0,
				Math.min(Settings.MAX_NAME_LENGTH, nameBuffer.length()));
	}

	public void run() {
		MazeCom loginMes = this.connection.receiveMessage();
		int failCounter = 0;
		while (failCounter < Settings.LOGINTRIES) {
			// Test ob es sich um eine LoginNachricht handelt
			if (loginMes != null && loginMes.getMcType() == MazeComType.LOGIN) {
				// sende Reply
				this.connection.sendMessage(this.mazeComMessageFactory
						.createLoginReplyMessage(this.player.getID()), false);
				this.player.init(cleanUpName(loginMes.getLoginMessage()
						.getName()));
							
				Debug.print(String.format(de.fhaachen.mazenet.networking.Messages.getString("LoginThread.successful"), player.getID(),player.getName()), DebugLevel.DEFAULT); //$NON-NLS-1$
				return;// verlassen des Threads
			}
			// Sende Fehler
			this.connection.sendMessage(this.mazeComMessageFactory
					.createAcceptMessage(-1, ErrorType.AWAIT_LOGIN), true);
			failCounter++;
			// nach einem Fehler auf den nächsten Versuch warten
			loginMes = this.connection.receiveMessage();
		}
		// Verlassen mit schwerem Fehlerfall
		// ID wird wieder freigegeben
		Debug.print(String.format(de.fhaachen.mazenet.networking.Messages.getString("LoginThread.failed"), player.getID()), DebugLevel.DEFAULT); //$NON-NLS-1$
		availablePlayers.push(this.player.getID());
		this.connection.disconnect(ErrorType.TOO_MANY_TRIES);
	}
}
