package networking;

import server.Board;
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

	public MazeCom createErrorMessage(int playerID, ErrorType et) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.ERROR);
		mc.setId(playerID);
		mc.setErrorMessage(of.createErrorMessageType());
		mc.getErrorMessage().setError(et);
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

	public MazeCom createDisconnectMessage(int playerID, String name) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.DISCONNECT);
		mc.setId(playerID);
		mc.setDisconnectMessage(of.createDisconnectMessageType());
		mc.getDisconnectMessage().setErroCode(ErrorType.DISCONNECT);
		mc.getDisconnectMessage().setName(name);
		return mc;
	}

	public MazeCom createLoginMessage(String name) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.LOGIN);
		mc.setId(99);
		mc.setLoginMessage(of.createLoginMessageType());
		mc.getLoginMessage().setName(name);
		return mc;
	}
}
