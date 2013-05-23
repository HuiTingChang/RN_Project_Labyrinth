package server;

import networking.Connection;
import generated.TreasureType;

public class Player {
	int ID;

	TreasureType m_currentTreasure;

	Connection conToClient;

	/*
	 * Player darf nicht selber generiert werden nur vom Login erzeugt!
	 */
	protected Player() {
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
