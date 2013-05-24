package networking;

import generated.MazeCom;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlInStream extends DataInputStream {

	private Unmarshaller unmarshaller;

	public XmlInStream(InputStream in) {
		super(in);
		try {
			JAXBContext jc = JAXBContext.newInstance(MazeCom.class);
			this.unmarshaller = jc.createUnmarshaller();
		} catch (JAXBException e) {
			System.err
					.println("[ERROR]: Fehler beim initialisieren der JAXB-Komponenten");
		}
	}

	/**
	 * Liest eine Nachricht und gibt die entsprechende Instanz zurück
	 * 
	 * @return
	 */
	public MazeCom readMazeCom() {
		byte[] bytes = null;
		MazeCom result = null;
		try {
			bytes = this.readUTF().getBytes();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

			try {
				result = (MazeCom) this.unmarshaller.unmarshal(bais);
			} catch (JAXBException e) {
				System.err
						.println("[ERROR]: Fehler beim unmarshallen der Nachricht");
			}
		} catch (IOException e1) {
			System.err.println("[ERROR]: Fehler beim lesen der Nachricht");
		} catch (NullPointerException e) {
			System.err
					.println("[ERROR]: Nullpointer beim lesen der Nachricht aufgrund weiterer Fehler");
		}
		return result;
	}
}