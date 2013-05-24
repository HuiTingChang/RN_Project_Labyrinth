package Timeouts;

import java.util.TimerTask;

import server.Game;

public class LoginTimeOut extends TimerTask {
	Game currentGame;

	public LoginTimeOut(Game currentGame) {
		super();
		this.currentGame = currentGame;
	}

	@Override
	public void run() {
		currentGame.stopLogin();
	}

}
