package networking;

import generated.ErrorType;
import generated.MazeCom;
import generated.MazeComType;
import generated.ObjectFactory;

public class MazeComMessageFaktory {

	static private ObjectFactory of = new ObjectFactory();
	
	public MazeCom createLoginReply(int newID) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.LOGINREPLY);
		mc.setId(newID);
		mc.setLoginReplyMessage(of.createLoginReplyMessageType());
		mc.getLoginReplyMessage().setNewID(newID);
		return mc;
	}
	
	public MazeCom createError(int id, ErrorType et) {
		MazeCom mc = of.createMazeCom();
		mc.setMcType(MazeComType.LOGINREPLY);
		mc.setId(id);
		mc.setErrorMessage(of.createErrorMessageType());
		mc.getErrorMessage().setError(et);		
		return mc;
	}
}
