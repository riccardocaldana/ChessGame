package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import moves.Mover;

/**
 * Classe che descrive il Pedone
 */
public class Pawn extends Piece {
	private ImageIcon imgWhite = new ImageIcon(getClass().getResource(
			"/img/WhiteP.png"));
	private ImageIcon imgBlack = new ImageIcon(getClass().getResource(
			"/img/BlackP.png"));

	public Pawn(int x, int y, Color color) {
		super(x, y, color);
	}

	public ImageIcon getImage() {
		if (color==Color.WHITE)
			return imgWhite;
		else
			return imgBlack;
	}

	@Override
	public ArrayList<Pair> possibilities() {
		ArrayList<Pair> possibilities = new ArrayList<Pair>();
		if (color==Color.WHITE) {
			if (x != 0) {
				possibilities.add(new Pair(x - 1, y));
				if (y == 0)
					possibilities.add(new Pair(x - 1, y + 1));
				else if (y == 7)
					possibilities.add(new Pair(x - 1, y - 1));
				else {
					possibilities.add(new Pair(x - 1, y + 1));
					possibilities.add(new Pair(x - 1, y - 1));
				}
			}
		} else {
			if (x != 7) {
				possibilities.add(new Pair(x + 1, y));
				if (y == 0)
					possibilities.add(new Pair(x + 1, y + 1));
				else if (y == 7)
					possibilities.add(new Pair(x + 1, y - 1));
				else {
					possibilities.add(new Pair(x + 1, y + 1));
					possibilities.add(new Pair(x + 1, y - 1));
				}
			}
		}
		
		return possibilities;

	}
	
	public void setMoves(Mover mover) {
		mover.pawnMoves(this);
		
	}
}