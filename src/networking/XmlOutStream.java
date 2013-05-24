package networking;

import generated.MazeCom;
import generated.ObjectFactory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

public class XmlOutStream extends DataOutputStream {

	private Marshaller marshaller;

	public XmlOutStream(OutputStream out) {
		super(out);
		// Anlegen der JAXB-Komponenten
		try {
			JAXBContext jc = JAXBContext.newInstance(MazeCom.class);
			this.marshaller = jc.createMarshaller();
			this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			this.marshaller = jc.createMarshaller();
		} catch (JAXBException e) {
			System.err
					.println("[ERROR]: Fehler beim Initialisieren der JAXB-Komponenten");
		}
	}

	/**
	 * Versenden einer XML Nachricht
	 * 
	 * @param mc
	 */
	public void write(MazeCom mc) {
		// gernerierung des fertigen XML
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			this.marshaller.marshal(mc, baos);
			// TODO entferne Ausgabe
			System.out.println("Geschrieben");
			System.out.println(new String(baos.toByteArray()));
			// Versenden des XML
			this.writeUTF(new String(baos.toByteArray()));
		} catch (IOException e) {
			System.err.println("[ERROR]: Fehler beim versendern der Nachricht");
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
