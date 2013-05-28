package server;

import generated.PositionType;

public class Position extends PositionType {
	public Position() {
	}

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
