package server.userInterface;

import generated.TreasureType;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import server.Card;
import server.Card.CardShape;

public class GraphicalCard extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7583185643671311612L;
	private BufferedImage shape;
	private BufferedImage treasure;
	private List<Integer> pin;

	public void resizeCard(Dimension d) {
		Image image = shape.getScaledInstance(d.width, d.height,
				Image.SCALE_SMOOTH);
		shape = new BufferedImage(d.width, d.height,
				BufferedImage.TYPE_INT_ARGB);
		shape.getGraphics().drawImage(image, 0, 0, null);
		this.setBounds(new Rectangle(shape.getWidth(), shape.getHeight()));
	}

	public void rotateCard(int orientierung) {

		Graphics2D g = shape.createGraphics();
		AffineTransform trans = AffineTransform.getRotateInstance(
				Math.toRadians(90 * orientierung), shape.getWidth() / 2,
				shape.getHeight() / 2);
		g.setTransform(trans);
	}

	public GraphicalCard() {
		super();
		loadShape(CardShape.I);
		treasure = null;
		pin = null;
	}

	public void setCard(Card c){
		loadShape(c.getShape());
		loadTreasure(c.getTreasure());
		loadPins(c.getPin().getPlayerID());
	}
	
	public void loadShape(CardShape c) {
		try {
			shape = ImageIO.read(GraphicalCard.class
					.getResource("/server/userInterface/resources/" + c.toString() + ".png"));
			this.setBounds(new Rectangle(shape.getWidth(), shape.getHeight()));
		} catch (IOException e) {
		}
	}

	public void loadTreasure(TreasureType t) {
		try {
			treasure = ImageIO.read(GraphicalCard.class
					.getResource("/server/userInterface/resources/" + t.value()
							+ ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadPins(List<Integer> list) {
		pin = list;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(shape, 0, 0, null);
		if (treasure != null) {
			int zentrum = shape.getHeight() / 2 - treasure.getHeight() / 2;
			g.drawImage(treasure, zentrum, zentrum, null);
		}
		if (pin != null) {
			int height=20;
			int width=20;
			int zentrum = shape.getHeight() / 2 - height / 2;
			g.fillOval(zentrum, zentrum, height,width);
		}
		// g2.fillOval(x, y, width, height)

	}

}
