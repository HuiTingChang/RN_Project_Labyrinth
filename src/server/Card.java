package server;

import generated.CardType;
import generated.TreasureType;

public class Card extends CardType {
	public enum CardShape {
		L, T, I
	}

	public enum Orientation {

		D0(0), D90(90), D180(180), D270(270);

		final int value;

		Orientation(int v) {
			value = v;
		}

		public int value() {
			return value;
		}

		public static Orientation fromValue(int v) {
			for (Orientation c : Orientation.values()) {
				if (c.value == v) {
					return c;
				}
			}
			throw new IllegalArgumentException(v + "");
		}
	}

	public Card(CardType c) {
		super();
		
		this.setOpenings(new Openings());		
		this.getOpenings().setBottom(c.getOpenings().isBottom());
		this.getOpenings().setLeft(c.getOpenings().isLeft());
		this.getOpenings().setRight(c.getOpenings().isRight());
		this.getOpenings().setTop(c.getOpenings().isTop());
		
		this.setTreasure(c.getTreasure());
		this.setPin(new Pin());
		this.pin.getPlayerID().addAll(c.getPin().getPlayerID());
	}

	public Card(CardShape shape, Orientation o, TreasureType t) {
		super();
		this.setOpenings(new Openings());
		this.setPin(new Pin());
		this.pin.getPlayerID();
		switch (shape) {
		case I:
			switch (o) {
			case D180:
			case D0:
				this.openings.setBottom(true);
				this.openings.setTop(true);
				this.openings.setLeft(false);
				this.openings.setRight(false);
				break;
			case D270:
			case D90:
				this.openings.setBottom(false);
				this.openings.setTop(false);
				this.openings.setLeft(true);
				this.openings.setRight(true);
				break;
			default:
				// TODO Wrong Rotation
				break;
			}
			break;
		case L:
			switch (o) {
			case D180:
				this.openings.setBottom(true);
				this.openings.setTop(false);
				this.openings.setLeft(true);
				this.openings.setRight(false);
				break;
			case D270:
				this.openings.setBottom(false);
				this.openings.setTop(true);
				this.openings.setLeft(true);
				this.openings.setRight(false);
				break;
			case D90:
				this.openings.setBottom(true);
				this.openings.setTop(false);
				this.openings.setLeft(false);
				this.openings.setRight(true);
				break;
			case D0:
				this.openings.setBottom(false);
				this.openings.setTop(true);
				this.openings.setLeft(false);
				this.openings.setRight(true);
				break;
			default:
				// TODO Wrong Rotation
				break;
			}
			break;
		case T:
			switch (o) {
			case D180:
				this.openings.setBottom(false);
				this.openings.setTop(true);
				this.openings.setLeft(true);
				this.openings.setRight(true);
				break;
			case D270:
				this.openings.setBottom(true);
				this.openings.setTop(true);
				this.openings.setLeft(false);
				this.openings.setRight(true);
				break;
			case D90:
				this.openings.setBottom(true);
				this.openings.setTop(true);
				this.openings.setLeft(true);
				this.openings.setRight(false);
				break;
			case D0:
				this.openings.setBottom(true);
				this.openings.setTop(false);
				this.openings.setLeft(true);
				this.openings.setRight(true);
				break;
			default:
				// TODO Wrong Rotation
				break;
			}
			break;
		default:
			// TODO Wrong Shape

		}
		this.treasure = t;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Codeoptimierung
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		CardType other = (CardType) obj;
		if (this.treasure != other.getTreasure()) {
			return false;
		}
		if (this.pin != other.getPin()) {
			return false;
		}
		// positions-check:
		boolean[] op1 = new boolean[4];
		boolean[] op2 = new boolean[4];
		op1[0] = getOpenings().isTop();
		op1[1] = getOpenings().isRight();
		op1[2] = getOpenings().isBottom();
		op1[3] = getOpenings().isLeft();

		op2[0] = other.getOpenings().isTop();
		op2[1] = other.getOpenings().isRight();
		op2[2] = other.getOpenings().isBottom();
		op2[3] = other.getOpenings().isLeft();
		int indsum1 = 0;
		int anzop1 = 0;
		int indsum2 = 0;
		int anzop2 = 0;
		for (int i = 0; i < op2.length; i++) {
			if (op1[i]) {
				indsum1 += i;
				++anzop1;
			}
			if (op2[i]) {
				indsum2 += i;
				++anzop2;
			}
		}
		if (anzop1 != anzop2) {
			return false;
		}
		if (indsum1 % 2 != indsum2 % 2) {
			return false;
		}
		return true;
	}

}
