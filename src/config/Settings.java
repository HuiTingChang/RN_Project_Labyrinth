package config;

public class Settings {
	//TODO falls alles nur Static => Konstruktor private machen
	public final static int port = 5123;
	public final static long LOGINTIMEOUT = 2 * 60 * 1000;// 2min
	public final static int LOGINTRIALS = 5;// maximal 5 Loginversuche
	public static final int MOVETRIALS = 3; // maximale Versuche einen gültigen zug zu machen
}
