package server;

import generated.BoardType;
import generated.CardType;
import generated.MoveMessageType;
import generated.PositionType;
import generated.TreasureType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import server.Card.CardShape;
import server.Card.Orientation;

public class Board extends BoardType {

	public Board() {
		super();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < i; j++) {
				this.getRow().get(i).getCol().get(j);
			}

		}
		generateInitialBoard();
		// TODO Auto-generated method stub

	}

	private void generateInitialBoard() {
		ArrayList<Card> freeCards = new ArrayList<Card>();
		Random rng = new Random();

		// 15 mal L-shape (6 (sym) + 9 (ohne))
		freeCards.add(new Card(CardShape.L, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_01));
		freeCards.add(new Card(CardShape.L, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_02));
		freeCards.add(new Card(CardShape.L, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_03));
		freeCards.add(new Card(CardShape.L, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_04));
		freeCards.add(new Card(CardShape.L, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_05));
		freeCards.add(new Card(CardShape.L, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_06));

		for (int i = 0; i < 9; i++) {
			freeCards.add(new Card(CardShape.L, Orientation.fromValue(rng
					.nextInt(4) * 90), null));
		}

		// 13 mal I-shape
		for (int i = 0; i < 13; i++) {
			freeCards.add(new Card(CardShape.I, Orientation.fromValue(rng
					.nextInt(4) * 90), null));
		}

		// 6 mal T-shape
		freeCards.add(new Card(CardShape.T, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_07));
		freeCards.add(new Card(CardShape.T, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_08));
		freeCards.add(new Card(CardShape.T, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_09));
		freeCards.add(new Card(CardShape.T, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_10));
		freeCards.add(new Card(CardShape.T, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_11));
		freeCards.add(new Card(CardShape.T, Orientation.fromValue(rng
				.nextInt(4) * 90), TreasureType.SYM_12));

		Collections.shuffle(freeCards);

		// fixedCards:
		setCard(0, 0, new Card(CardShape.L, Orientation.D90, null));
		setCard(0, 2,
				new Card(CardShape.T, Orientation.D0, TreasureType.SYM_13));
		setCard(0, 4,
				new Card(CardShape.T, Orientation.D0, TreasureType.SYM_14));
		setCard(0, 6, new Card(CardShape.L, Orientation.D180, null));
		setCard(2, 0, new Card(CardShape.T, Orientation.D270,
				TreasureType.SYM_15));
		setCard(2, 2, new Card(CardShape.T, Orientation.D270,
				TreasureType.SYM_16));
		setCard(2, 4,
				new Card(CardShape.T, Orientation.D0, TreasureType.SYM_17));
		setCard(2, 6, new Card(CardShape.T, Orientation.D90,
				TreasureType.SYM_18));
		setCard(4, 0, new Card(CardShape.T, Orientation.D270,
				TreasureType.SYM_18));
		setCard(4, 2, new Card(CardShape.T, Orientation.D180,
				TreasureType.SYM_19));
		setCard(4, 4, new Card(CardShape.T, Orientation.D90,
				TreasureType.SYM_20));
		setCard(4, 6, new Card(CardShape.T, Orientation.D90,
				TreasureType.SYM_21));
		setCard(6, 0, new Card(CardShape.L, Orientation.D0, null));
		setCard(6, 2, new Card(CardShape.T, Orientation.D180,
				TreasureType.SYM_22));
		setCard(6, 4, new Card(CardShape.T, Orientation.D180,
				TreasureType.SYM_23));
		setCard(6, 6, new Card(CardShape.L, Orientation.D270, null));

		int k = 0;
		for (int i = 1; i < 7; i += 2) {
			for (int j = 1; j < 7; j += 2) {
				setCard(i, j, freeCards.get(k++));
			}

		}
		this.setShiftCard(freeCards.get(k));
		getCard(0, 0).getPin().getPlayerID().add(0);
		getCard(0, 6).getPin().getPlayerID().add(1);
		getCard(6, 0).getPin().getPlayerID().add(2);
		getCard(6, 6).getPin().getPlayerID().add(3);

	}

	private void setCard(int row, int col, Card c) {
		this.getRow().get(row).getCol().add(col, c);
	}

	public CardType getCard(int row, int col) {
		return this.getRow().get(row).getCol().get(col);
	}

	public void proceedTurn(MoveMessageType move) {

	}

	public void validate() {

	}

	public boolean validateTransition(MoveMessageType move) {
		PositionType sm = move.getShiftPosition();
		if (sm.getCol() == 0 || sm.getCol() == 6) {
			if (sm.getRow() % 2 == 2) {
				return false;
			}
		} else if (sm.getRow() == 0 || sm.getRow() == 6) {
			if (sm.getCol() % 2 == 2) {
				return false;
			}
		} else {
			return false;
		}

		Card sc = new Card(move.getShiftCard());
		if (!sc.equals(shiftCard)) {
			return false;
		}
		// TODO Test PINPosition
		//if(pathpossible()){
		//
		//
		return true;
	}
}
