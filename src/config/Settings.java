package config;

public class Settings {
	// TODO falls alles nur Static => Konstruktor private machen
	public final static int port = 5123;
	public final static int DEFAULT_PLAYERS = 4;
	public final static int MOVEDELAY = (int)(1.0 * 1000); // 1 sec

	public final static long LOGINTIMEOUT = 1 * 60 * 1000;// 1min
	public final static int LOGINTRIES = 3;// maximal 3 Loginversuche
	public static final int MOVETRIES = 3; // maximale Versuche einen gueltigen
											// zug zu machen
	public static final long SENDTIMEOUT = 1 * 60 * 1000;// 1min

	public static final boolean TESTBOARD = false; // Das Testbrett ist immer
													// gleich
	public static final long TESTBOARD_SEED = 0; // Hiermit lassen sich die
													// Testfälle Anpassen
													// (Pseudozufallszahlen)
	

}
