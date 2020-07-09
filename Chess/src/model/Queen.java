package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import moves.Mover;

/**
 * Classe che descrive la Regina
 */
public class Queen extends Piece {
	private ImageIcon imgWhite = new ImageIcon(getClass().getResource(
			"/img/WhiteQ.png"));
	private ImageIcon imgBlack = new ImageIcon(getClass().getResource(
			"/img/BlackQ.png"));

	public Queen(int x, int y, Color color) {
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
		// verticali sopra
		for (int i = x - 1; i >= 0; i--)
			possibilities.add(new Pair(i, y));

		// verticali sotto
		for (int i = x + 1; i < 8; i++)
			possibilities.add(new Pair(i, y));

		// orizzontali sx
		for (int i = y - 1; i >= 0; i--)
			possibilities.add(new Pair(x, i));

		// orizzontali dx
		for (int i = y + 1; i < 8; i++)
			possibilities.add(new Pair(x, i));
		// diagonale dx sopra
		for (int i = x, j = y; i - 1 >= 0; i--) {
			if (j + 1 < 8) {
				possibilities.add(new Pair(i - 1, j + 1));
				j++;
			} else
				break;
		}

		// diaognale dx sotto
		for (int i = x, j = y; i + 1 < 8; i++) {
			if (j + 1 < 8) {
				possibilities.add(new Pair(i + 1, j + 1));
				j++;
			} else
				break;
		}

		// diagonale sx sopra
		for (int i = x, j = y; i - 1 >= 0; i--) {
			if (j - 1 >= 0) {
				possibilities.add(new Pair(i - 1, j - 1));
				j--;
			} else
				break;
		}
		// diagonale sx sotto
		for (int i = x, j = y; i + 1 < 8; i++) {
			if (j - 1 >= 0) {
				possibilities.add(new Pair(i + 1, j - 1));
				j--;
			} else
				break;
		}
		
		return possibilities;
	}
	
	public void setMoves(Mover mover) {
		mover.queenMoves(this);
	}
}