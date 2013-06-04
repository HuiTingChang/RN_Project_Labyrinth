package networking;

import server.Board;
import generated.BoardType;
import generated.ErrorType;
import generated.MazeCom;
import generated.MazeComType;
import generated.ObjectFactory;
import generated.WinMessageType.Winner;

public class MazeComMessageFactory {

	static private ObjectFactory of = new ObjectFactory();

	public MazeCom createLoginReplyMessage(int newID) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.LOGINREPLY);
		mc.setId(newID);
		mc.setLoginReplyMessage(of.createLoginReplyMessageType());
		mc.getLoginReplyMessage().setNewID(newID);
		return mc;
	}

	public MazeCom createAcceptMessage(int playerID, ErrorType et) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.ACCEPT);
		mc.setId(playerID);
		mc.setAcceptMessage(of.createAcceptMessageType());
		mc.getAcceptMessage().setErrorCode(et);
		mc.getAcceptMessage().setAccept(et == ErrorType.NOERROR);
		return mc;
	}

	public MazeCom createWinMessage(int playerID, int winnerId, String name,
			Board b) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.WIN);
		mc.setId(playerID);
		mc.setWinMessage(of.createWinMessageType());
		Winner w = of.createWinMessageTypeWinner();
		w.setId(winnerId);
		mc.getWinMessage().setWinner(w);
		mc.getWinMessage().setBoard(b);
		return mc;
	}

	public MazeCom createDisconnectMessage(int playerID, String name, ErrorType et) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.DISCONNECT);
		mc.setId(playerID);
		mc.setDisconnectMessage(of.createDisconnectMessageType());
		mc.getDisconnectMessage().setErroCode(et);
		mc.getDisconnectMessage().setName(name);
		return mc;
	}


	public MazeCom createAwaitMoveMessage(int playerId, Board brett) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.AWAITMOVE);
		mc.setId(playerId);
		mc.setAwaitMoveMessage(of.createAwaitMoveMessageType());
		
		// TODO Brett erstellen und übergeben
		mc.getAwaitMoveMessage().setBoard(brett);
	
		return mc;
	}
}
