package server;

import networking.Connection;
import generated.TreasureType;

public class Player {
	final int ID;
	String name;
	TreasureType currentTreasure;

	Connection conToClient;

	/*
	 * Player darf nicht selber generiert werden nur vom Login erzeugt!
	 */
	protected Player(int id, Connection c) {
		ID = id;
		this.name = "Player0" + ID;
		conToClient = c;
		currentTreasure = null;

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

	/**
	 * Bewegung Karte + Pin
	 */
	public void makeTurn() {

	}

	public void disconnect() {

	}

	public void init() {

	}
}
