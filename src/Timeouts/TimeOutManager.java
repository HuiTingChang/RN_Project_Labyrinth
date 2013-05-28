package Timeouts;

import java.util.Timer;

import config.Settings;

import server.Game;

public class TimeOutManager extends Timer {
	
	private Game currentGame;
	private LoginTimeOut lto;
	
	public TimeOutManager(Game currentGame) {
		super("TimeOuts",true);
		this.currentGame=currentGame;
	}

	public void startLoginTimeOut(){
		lto=new LoginTimeOut(currentGame);
		this.schedule(lto, Settings.LOGINTIMEOUT);
	}
	public void stopLoginTimeOut(){
		lto.cancel();
	}
}
