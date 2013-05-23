package server;

import java.util.HashMap;

public class Game {

	/**
	 * beinhaltet die Spieler, die mit dem Server verbunden sind und die durch die ID zugreifbar sind
	 */
	private HashMap<Integer, Player> spieler;
	
	public void init(){
		
	}
	public void singleTurn(){
		
	}
	/**
	 * Aufraeumen nach einem Spiel
	 */
	public void cleanUp(){
		
	}
	
	public boolean someBodyWon(){
		return false;
		//TODO mitteilung an alle Clients, dass einer Gewonnen hat
	}
	
	public static void main(String[] args) {
		
	}
	
	
}
