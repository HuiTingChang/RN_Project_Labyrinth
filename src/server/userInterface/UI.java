package server.userInterface;

import generated.MoveMessageType;
import server.Board;

public interface UI {
	public void displayMove(MoveMessageType mm, Board b);
	public void init(Board b);
}
