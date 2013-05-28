package Timeouts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import networking.Connection;

import config.Settings;

import server.Game;

public class TimeOutManager extends Timer {

	private Game currentGame;
	private LoginTimeOut lto;
	private HashMap<Integer, SendMessageTimeout> smt;

	public TimeOutManager(Game currentGame) {
		super("TimeOuts", true);
		this.currentGame = currentGame;
		this.smt = new HashMap<Integer, SendMessageTimeout>();
	}

	public void startLoginTimeOut() {
		lto = new LoginTimeOut(currentGame);
		this.schedule(lto, Settings.LOGINTIMEOUT);
	}

	public void stopLoginTimeOut() {
		lto.cancel();
	}

	public void startSendMessageTimeOut(int playerId, Connection c) {
		smt.put(playerId, new SendMessageTimeout(c));
		this.schedule(smt.get(playerId), Settings.SENDTIMEOUT);
	}

	public void stopSendMessageTimeOut(int playerId) {
		smt.get(playerId).cancel();
	}
}
