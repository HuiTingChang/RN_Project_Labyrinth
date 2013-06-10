package tools;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Debug {

	private class Unit {
		private DebugLevel level;
		private OutputStream stream;

		public Unit(OutputStream stream, DebugLevel level) {
			this.level = level;
			this.stream = stream;
		}

		public void print(String str, DebugLevel level) throws IOException {
			if (level.value() <= this.level.value())
				stream.write(str.getBytes());
		}
	}

	static List<Unit> liste = new ArrayList<Unit>();

	public static void addDebugger(OutputStream stream, DebugLevel level) {
		// Unit u=new Unit(stream, level);
		// liste.add(u);
	}

	public static void print(String str, DebugLevel level) {
		for (Unit u : liste) {
			try {
				u.print(str, level);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
