package server;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Stack;

import networking.Connection;
import generated.ErrorType;
import generated.PositionType;
import generated.TreasureType;

public class Player {
	final int ID;
	private String name;
	private TreasureType currentTreasure;
	private Stack<TreasureType> treasures;
	private Connection conToClient;
	private PositionType pos;
	private boolean initialized;

	/*
	 * Player darf nicht selber generiert werden nur vom Login erzeugt!
	 */
	public Player(int id, Connection c) {
		ID = id;
		this.name = "Player0" + ID;
		conToClient = c;
		currentTreasure = null;
		initialized = false;
	}

	public TreasureType getCurrentTreasure() {
		return currentTreasure;
	}

	//returns remaing treasures
	public int foundTreasure(){
		try{
			currentTreasure=treasures.pop();
		}catch(EmptyStackException e){
			return 0;
		}
		return treasures.size()+1;
	
	}
	public int treausresToGo(){
		return treasures.size()+1;
	}
	public void setTreasure(Collection<? extends TreasureType> c) {
		this.treasures.addAll(c);
	}
	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public Connection getConToClient() {
		return conToClient;
	}

	public void disconnect(ErrorType et) {
		conToClient.disconnect(et);
	}

	public void init(String name) {
		if (!initialized) {
			this.name = name;
			initialized = true;
		}
	}

	public boolean isInitialized() {
		return initialized;
	}

}
