package tools;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;

public class Debug {

	static HashMap<OutputStream, DebugLevel> liste = (HashMap<OutputStream, DebugLevel>) Collections
			.synchronizedMap(new HashMap<OutputStream, DebugLevel>());

	public static void addDebugger(OutputStream stream, DebugLevel level) {
		liste.put(stream, level);
	}

	// TODO erlaeuterndes Javadoc
	public static void print(String str, DebugLevel level) {
		// FIXME string chaining, uasses
		str += "\n"; //$NON-NLS-1$
		synchronized (liste) {
			for (OutputStream out : liste.keySet()) {
				DebugLevel streamLevel = liste.get(out);
				try {
					if (streamLevel.value() >= level.value())
						out.write(str.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
