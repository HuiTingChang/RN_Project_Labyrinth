package server.userInterface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import server.Board;
import server.Card;

public class GraphicalBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8826989344981510486L;

	private GraphicalCard[][] boardDisplay = null;
	private TexturePaint ourPainter = null;
	private Board b;
	private boolean ready = true;

	public GraphicalBoard() {
		super();
		GridLayout BoardPaneLayout = new GridLayout(7, 7);
		BoardPaneLayout.setHgap(0);
		BoardPaneLayout.setVgap(0);
		this.setLayout(BoardPaneLayout);
		this.setPreferredSize(new java.awt.Dimension(700, 700));
		boardDisplay = new GraphicalCard[7][7];
		for (int i = 0; i < boardDisplay.length; i++) {
			for (int j = 0; j < boardDisplay[i].length; j++) {
				boardDisplay[i][j] = new GraphicalCard();
				this.add(boardDisplay[i][j]);
			}

		}
	}

	public void resizeBoard() {
		for (int i = 0; i < boardDisplay.length; i++) {
			for (int j = 0; j < boardDisplay[i].length; j++) {
				boardDisplay[i][j].resizeCard(new Dimension(
						this.getHeight() / 7, this.getWidth() / 7));
			}
		}
		this.revalidate();
	}

	public void updateBoard(Board b) {
		ready = false;
		for (int i = 0; i < boardDisplay.length; i++) {
			for (int j = 0; j < boardDisplay[i].length; j++) {
				Card c = new Card(b.getCard(i, j));
				boardDisplay[i][j].setCard(c);
			}

		}
		resizeBoard();
		this.b = b;
		updatePaint();
		ready = true;
	}

	private void updatePaint() {
		if (boardDisplay == null) {
			ourPainter = null;
			return;
		}

		int w = this.getWidth();
		int h = this.getHeight();

		if (w <= 0 || h <= 0) {
			ourPainter = null;
			return;
		}

		BufferedImage buff = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB_PRE);

		Graphics2D g2 = buff.createGraphics();
		super.paintComponent(g2);

		ourPainter = new TexturePaint(buff, new Rectangle(0, 0, w, h));
		g2.dispose();
	}

	protected void paintComponent(Graphics g) {
		// if (ready) {
		// super.paintComponent(g);

		if (ourPainter != null) {
			int w = getWidth();
			int h = getHeight();
			Insets in = getInsets();

			int x = in.left;
			int y = in.top;
			w = w - in.left - in.right;
			h = h - in.top - in.bottom;

			if (w >= 0 && h >= 0) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setPaint(ourPainter);
				g2.fillRect(x, y, w, h);
			}
		}
		// }
	}

}
