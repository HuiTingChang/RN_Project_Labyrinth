package server.userInterface;
import generated.MoveMessageType;
import generated.TreasureType;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import server.Board;
import server.Card;
import server.Card.CardShape;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GraphicalUI extends javax.swing.JFrame implements UI{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3985631697359900852L;
	private JScrollPane boardScrollPane;
	private JPanel shiftCardContainer;
	private JLabel jLShiftCard;
	private JPanel BoardPane;
	private GraphicalCard shiftCard;
	private GraphicalCard[][] boardDisplay;

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
			thisLayout.rowWeights = new double[] {0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7};
			thisLayout.columnWeights = new double[] {0.7, 0.3};
			thisLayout.columnWidths = new int[] {70, 30};
			getContentPane().setLayout(thisLayout);
			this.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent evt) {
					//System.out.println("this.componentResized, event="+evt);
					for (int i = 0; i < boardDisplay.length; i++) {
						for (int j = 0; j < boardDisplay[i].length; j++) {
							boardDisplay[i][j].resizeCard(new Dimension(BoardPane.getHeight()/7, BoardPane.getWidth()/7));						
						}
						
					}
				}
			});
			{
				boardScrollPane = new JScrollPane();
				getContentPane().add(boardScrollPane, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					BoardPane = new JPanel();
					GridLayout BoardPaneLayout = new GridLayout(7, 7);
					BoardPaneLayout.setRows(7);
					BoardPaneLayout.setColumns(7);
					BoardPaneLayout.setHgap(5);
					BoardPaneLayout.setVgap(5);
					boardScrollPane.setViewportView(BoardPane);
					BoardPane.setLayout(BoardPaneLayout);
					BoardPane.setPreferredSize(new java.awt.Dimension(213, 266));
					boardDisplay=new GraphicalCard[7][7];
					for (int i = 0; i < boardDisplay.length; i++) {
						for (int j = 0; j < boardDisplay[i].length; j++){
							boardDisplay[i][j]=new GraphicalCard();
							BoardPane.add(boardDisplay[i][j]);							
						}
						
					}
					
				}
			}
			{
				shiftCardContainer = new JPanel();
				BorderLayout shiftCardContainerLayout = new BorderLayout();
				getContentPane().add(shiftCardContainer, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				shiftCardContainer.setLayout(shiftCardContainerLayout);
				{
					jLShiftCard = new JLabel();
					shiftCardContainer.add(jLShiftCard, BorderLayout.NORTH);
					jLShiftCard.setText("freie Karte:");
				}
				{
					shiftCard = new GraphicalCard();
					shiftCard.loadShape(CardShape.T);
					shiftCard.loadTreasure(TreasureType.SYM_01);
					shiftCardContainer.add(shiftCard, BorderLayout.CENTER);
					shiftCard.setPreferredSize(new java.awt.Dimension(124, 246));
				}
			}
			pack();
			this.setSize(1000, 700);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void displayMove(MoveMessageType mm, Board b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Board b) {
		// TODO Auto-generated method stub
		updateBoard(b);
	}

	private void updateBoard(Board b){
		for (int i = 0; i < boardDisplay.length; i++) {
			for (int j = 0; j < boardDisplay[i].length; j++) {
				Card c=new Card(b.getCard(i, j));
				boardDisplay[i][j].setCard(c);
				
			}
			
		}
		shiftCard.setCard(new Card(b.getShiftCard()));
	}
	
}
