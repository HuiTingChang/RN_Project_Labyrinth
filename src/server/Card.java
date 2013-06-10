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
			System.err.println("Schatz ungleich");
			return false;
		}
		for (Integer ID: this.getPin().getPlayerID()) {
			if(!other.getPin().getPlayerID().contains(ID))
				System.err.println("Spieler ungleich");
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
			System.err.println("Form ungleich(Anzahl)");
			System.out.println("From:");
			System.out.println(this);
			System.out.println("To:");
			System.out.println(other);
			return false;
		}
		if ((anzop1!=3) && (indsum1 % 2) != (indsum2 % 2)) {
			System.err.println("Form ungleich(Index)");
			System.out.println("From: ("+indsum1+")");
			System.out.println(this);
			System.out.println("To:("+indsum2+")");
			System.out.println(other);
			return false;
		}
		return true;
	}
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(" ------ \n");

		StringBuilder line1 = new StringBuilder("|");
		StringBuilder line2 = new StringBuilder("|");
		StringBuilder line3 = new StringBuilder("|");
		StringBuilder line4 = new StringBuilder("|");
		StringBuilder line5 = new StringBuilder("|");
		StringBuilder line6 = new StringBuilder("|");

		if (this.getOpenings().isTop()) {
			line1.append("##  ##|");
			line2.append("##  ##|");
		} else {
			line1.append("######|");
			line2.append("######|");
		}
		if (this.getOpenings().isLeft()) {
			line3.append("    ");
			line4.append("    ");
		} else {
			line3.append("##  ");
			line4.append("##  ");
		}
		if (this.getOpenings().isRight()) {
			line3.append("  |");
			line4.append("  |");
		} else {
			line3.append("##|");
			line4.append("##|");
		}
		if (this.getOpenings().isBottom()) {
			line5.append("##  ##|");
			line6.append("##  ##|");
		} else {
			line5.append("######|");
			line6.append("######|");
		}

		sb.append(line1.toString() + "\n");
		sb.append(line2.toString() + "\n");
		sb.append(line3.toString() + "\n");
		sb.append(line4.toString() + "\n");
		sb.append(line5.toString() + "\n");
		sb.append(line6.toString() + "\n");
		sb.append(" ------ \n");

		return sb.toString();
	}

}
