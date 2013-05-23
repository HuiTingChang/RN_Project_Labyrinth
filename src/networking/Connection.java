/*
 * Connection regelt die serverseitige Protokollarbeit
 */
package networking;

import generated.MazeCom;
import generated.MoveMessageType;
import generated.WinMessageType;

import java.net.Socket;

import server.Board;
import server.Player;

public class Connection {

	Socket curCon;
	XmlInStream inFromClient;
	XmlOutStream outToClient;

	public Connection(Socket s) {
		// TODO TCP-Verbindungsaufbau und erzeugen der Streams
	}

	public void sendMessage(MazeCom mc) {
		// TODO senden einer Nachricht
		// XmlOutStream.write ...
	}

	public MazeCom receiveMessage() {
		// TODO empfangen einer Nachricht
		// XmlInStream.read ...
		// MazeCom zurueck geben
		return null;
	}

	public Player login(int newId) {
		// receiveMessage -> ersten Login bekommen => Ueberpruefen ob korrekte
		// Nachricht
		// Aufbau ergebnisNachricht
		// sende Ergebnis => mit neuer ID
		// TODO sende Message vom Type LoginReplyMessageType mit neuer SpielerID
		return null;
	}

	public MoveMessageType awaitMove(Board brett) {
		// TODO sende Ihm das neue Brett
		return null;
	}
	
	public void sendWin(int winnerId, String name){
		//TODO sende Ergebnis raus
	}

	public void disconnect() {
		// TODO Erronachricht generieren und Verbindung abbrechen
	}

}
