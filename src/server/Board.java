package server;
import generated.BoardType;
import generated.TreasureType;

import java.util.ArrayList;

import server.Card.CardShape;
import server.Card.Orientation;

public class Board extends BoardType {
	
	
	
	public Board() {
		// TODO Auto-generated method stub
		
		
	}
	
	private void generateInitialBoard() {
		ArrayList<Card> freeCards=new ArrayList<Card>(); 
		
		//15 mal L-shape (6 (sym) + 9 (ohne))
		freeCards.add(new Card(CardShape.L,Orientation.fromValue(0),TreasureType.SYM_01));
		
		
		// TODO Auto-generated method stub
		/*
		// Freie Karten
		// 13 I
		for (int i = 0; i < 13; i++) {
			addI();
		}
		// 6 T
		addT(Suchkarten.FLASCHENGEIST);
		addT(Suchkarten.TROLL);
		addT(Suchkarten.DRACHE);
		addT(Suchkarten.GESPENST);
		addT(Suchkarten.FEE);
		addT(Suchkarten.FLEDERMAUS);

		boolean flag = true;
		schiebkarte = getFreeCard(flag);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (i % 2 != 0 || j % 2 != 0) {
					spielbrett[i][j] = getFreeCard(flag);
				}
			}
		}
		*/
	}
	
	public void proceedTurn(){
		
	}
	
	public void validate(){
		
	}
	
	public void validateTransition(){
		
	}
}
