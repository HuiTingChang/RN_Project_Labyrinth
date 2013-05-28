package server;

import networking.Connection;
import generated.PositionType;
import generated.TreasureType;

public class Player {
	final int ID;
	private String name;
	private TreasureType currentTreasure;

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
		initialized=false;
	}

	public TreasureType getCurrentTreasure() {
		return currentTreasure;
	}

	public void setCurrentTreasure(TreasureType currentTreasure) {
		this.currentTreasure = currentTreasure;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Connection getConToClient() {
		return conToClient;
	}

	public void disconnect() {
		conToClient.disconnect();
	}

	public void init(String name) {
		this.name=name;
		initialized=true;
	}

	public PositionType getPos() {
		return pos;
	}

	public boolean isInitialized() {
		return initialized;
	}
	
	
	
}
