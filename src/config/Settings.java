package config;


public class Settings {
	// TODO falls alles nur Static => Konstruktor private machen
	public final static int port = 5123;
	public final static int DEFAULT_PLAYERS = 2;
	public final static long LOGINTIMEOUT = 2 * 60 * 1000;// 2min
	public final static int LOGINTRIES = 5;// maximal 5 Loginversuche
	public static final int MOVETRIES = 3; // maximale Versuche einen gueltigen
											// zug zu machen
	public static final long SENDTIMEOUT = 1 * 60 * 1000;// 1min
}
