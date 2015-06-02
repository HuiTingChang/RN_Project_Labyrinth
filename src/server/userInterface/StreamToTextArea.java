package server.userInterface;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class StreamToTextArea extends OutputStream {

	private final JTextArea textArea;

	private final StringBuilder sb = new StringBuilder();

	public StreamToTextArea(final JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void write(int b) {
		//FIXME funktioniert nicht mit UTF-8 ausserhalb von ascii
//		Handelt es sich bei einem Zeichen um ein ASCII-Zeichen wird ein Byte zur Codierung benötig
//		(0 – 127) → 0xxx xxx
//
//		Handelt es sich um ein anderes Zeichen werden 2 bis 4 Bytes zur Codierung benötigt
//		Das erste Byte beginnt mit einer Anzahl von Einsen, die der Anzahl von zu verwendenden Bytes entspricht, gefolgt von einer 0
//		Jedes folgende Byte beginnt mit 10
//		Beispiel
//		Ein 3 Byte-Code sieht wie folgt aus: 1110 xxxx | 10xx xxxx | 10xx xxxx
//		Damit verbleiben 16 Bits zur Darstellung eines Unicode Zeichens
		System.out.println(b+" als char: "+((char) b));
		int[] bytes = { b };
		write(bytes, 0, bytes.length);
	}

	public void write(int[] bytes, int offset, int length) {
//		System.out.println("bytes: "+bytes+"offset: "+offset+"length: "+length);
		String s = new String(bytes, offset, length);
		if (s.equals("\r")) //$NON-NLS-1$
			return;
		if (s.equals("\n")) { //$NON-NLS-1$
			final String text = sb.toString() + "\n"; //$NON-NLS-1$
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textArea.append(text);
					textArea.setCaretPosition(textArea.getDocument()
							.getLength());
				}
			});
			sb.setLength(0);
			return;
		}

		sb.append(s);

	}

	public void write2(int b) throws IOException {
char c='1';
		if (c == '\r')
			return;

		if (c == '\n') {
			final String text = sb.toString() + "\n"; //$NON-NLS-1$
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textArea.append(text);
					textArea.setCaretPosition(textArea.getDocument()
							.getLength());
				}
			});
			sb.setLength(0);
			return;
		}

		sb.append(c); //$NON-NLS-1$

	}

	public JTextArea getTextArea() {
		return textArea;
	}

}
