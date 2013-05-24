package Timeouts;

import java.util.Timer;

import config.Settings;

import server.Game;

public class TimeOutManager extends Timer {
	
	Game currentGame;
	
	public TimeOutManager(Game currentGame) {
		super("TimeOuts",true);
		this.currentGame=currentGame;
	}

	public void startLoginTimeOut(){
		this.schedule(new LoginTimeOut(currentGame), Settings.LOGINTIMEOUT);
	}
}
