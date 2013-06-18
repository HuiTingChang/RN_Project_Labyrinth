package server.userInterface;

import generated.MoveMessageType;
import generated.TreasureType;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.GroupLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import server.Board;
import server.Card;
import server.Card.CardShape;
import server.Card.Orientation;
import server.Player;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class GraphicalUI extends javax.swing.JFrame implements UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3985631697359900852L;
	private JPanel shiftCardContainer;
	private JLabel jLShiftCard;
	private JLabel jLTreasuresToGo;
	private JLabel jLName;
	private JLabel currentTreasure;
	private JLabel jLSpieler;
	private JLabel jLcurrentTreasure;
	private JPanel statisticsPane;
	private GraphicalBoard BoardPane;
	private GraphicalCardBuffered shiftCard;

	private JLabel[][] stats;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GraphicalUI inst = new GraphicalUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public GraphicalUI() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] { 0.0, 0.0 };
			thisLayout.rowHeights = new int[] { 100, 100 };
			thisLayout.columnWeights = new double[] { 0.0, 0.0 };
			thisLayout.columnWidths = new int[] { 70, 30 };

			getContentPane().setLayout(thisLayout);
			this.setTitle("MazeCom");
			{
				BoardPane = new GraphicalBoard();
				getContentPane().add(
						BoardPane,
						new GridBagConstraints(0, 0, 1, 2, 0.7, 0.1,
								GridBagConstraints.CENTER,
								GridBagConstraints.BOTH,
								new Insets(5, 5, 5, 5), 0, 0));
				BoardPane.setSize(700, 700);

			}
			this.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent evt) {
					System.out.println("this.windowClosed, event=" + evt);
					System.exit(0);
				}
			});

			{
				shiftCardContainer = new JPanel();
				GridBagLayout shiftCardContainerLayout = new GridBagLayout();
				shiftCardContainer.setLayout(shiftCardContainerLayout);
				getContentPane().add(shiftCardContainer, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				shiftCardContainer.setPreferredSize(new java.awt.Dimension(120,
						150));
				{
					jLShiftCard = new JLabel();
					shiftCardContainer.add(jLShiftCard, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLShiftCard.setText("freie Karte:");
				}
				{
					shiftCard = new GraphicalCardBuffered();
					shiftCardContainer.add(shiftCard, new GridBagConstraints(0, 1, 1, 1, 0.5, 0.5, GridBagConstraints.NORTH, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
					shiftCard.setPreferredSize(new java.awt.Dimension(120, 120));
					shiftCard.setSize(120, 120);

				}
					shiftCardContainerLayout.rowWeights = new double[] {0.1, 0.1};
					shiftCardContainerLayout.rowHeights = new int[] {7, 7};
					shiftCardContainerLayout.columnWeights = new double[] {0.1};
					shiftCardContainerLayout.columnWidths = new int[] {7};
			}
			{
				statisticsPane = new JPanel();
				GridBagLayout statisticsPaneLayout = new GridBagLayout();
				getContentPane().add(
						statisticsPane,
						new GridBagConstraints(1, 1, 1, 1, 0.0, 0.2,
								GridBagConstraints.CENTER,
								GridBagConstraints.BOTH,
								new Insets(0, 0, 0, 0), 0, 0));
				statisticsPaneLayout.rowWeights = new double[] { 0.1, 0.1, 0.1,
						0.1, 0.1, 0.1, 0.1, 0.1 };
				statisticsPaneLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7,
						7, 7 };
				statisticsPaneLayout.columnWeights = new double[] { 0.1, 0.1 };
				statisticsPaneLayout.columnWidths = new int[] { 7, 7 };
				statisticsPane.setLayout(statisticsPaneLayout);
				{
					jLcurrentTreasure = new JLabel();
					statisticsPane.add(jLcurrentTreasure,
							new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.NONE, new Insets(0, 0,
											0, 0), 0, 0));
					jLcurrentTreasure.setText("gesuchter Schatz:");
				}
				{
					currentTreasure = new JLabel();
					statisticsPane.add(currentTreasure, new GridBagConstraints(
							0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					currentTreasure
							.setIcon(new ImageIcon(
									getClass()
											.getClassLoader()
											.getResource(
													"server/userInterface/resources/sym05.png")));
				}
				{
					jLSpieler = new JLabel();
					statisticsPane.add(jLSpieler, new GridBagConstraints(0, 2,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					jLSpieler.setText("Spieler");
				}
				{
					jLName = new JLabel();
					statisticsPane.add(jLName, new GridBagConstraints(0, 3, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					jLName.setText("Name");
				}
				{
					jLTreasuresToGo = new JLabel();
					statisticsPane.add(jLTreasuresToGo, new GridBagConstraints(
							1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					jLTreasuresToGo.setText("übrige Schätze");
				}
			}
			stats = new JLabel[4][2];
			for (int i = 0; i < stats.length; i++) {
				stats[i][0] = new JLabel();
				statisticsPane.add(stats[i][0], new GridBagConstraints(0,
						4 + i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				stats[i][0].setText((i + 1) + ": Nicht Verbunden");

				stats[i][1] = new JLabel();
				statisticsPane.add(stats[i][1], new GridBagConstraints(1,
						4 + i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				stats[i][1].setText("" + 0);
			}
			pack();
			this.setSize(1000, 700);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void displayMove(MoveMessageType mm, Board gb) {
		updateBoard(gb);
	}

	@Override
	public void init(Board b) {
		updateBoard(b);
		this.setVisible(true);

	}

	private void updateBoard(Board b) {
		BoardPane.updateBoard(b);
		shiftCard.setCard(new Card(b.getShiftCard()));
		setCurrentTreasure(b.getTreasure());
	}

	private void setCurrentTreasure(TreasureType t) {
		if (t != null) {
			currentTreasure.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource(
							"server/userInterface/resources/" + t.value()
									+ ".png")));
		} else {
			currentTreasure.setIcon(null);
		}
	}

	@Override
	public void updatePlayerStatistics(List<Player> stats, Integer current) {
		for (int i = 0; i < 4; ++i) {
			this.stats[i][0].setText((i + 1) + ": nicht verbunden");
			this.stats[i][1].setText("");
		}
		for (Player p : stats) {
			if (p.getID() == current) {
				this.stats[p.getID() - 1][0].setText(">" + p.getID() + ": "
						+ p.getName());
			} else {
				this.stats[p.getID() - 1][0].setText(p.getID() + ": "
						+ p.getName());
			}
			this.stats[p.getID() - 1][1].setText("" + p.treasuresToGo());
		}

	}

}
