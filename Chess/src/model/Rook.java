package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import moves.Mover;

/**
 * Classe che descrive la Torre
 */
public class Rook extends Piece {
	private ImageIcon imgWhite = new ImageIcon(getClass().getResource(
			"/img/WhiteR.png"));
	private ImageIcon imgBlack = new ImageIcon(getClass().getResource(
			"/img/BlackR.png"));

	public Rook(int x, int y, Color color) {
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
		for (int i = y-1; i >= 0; i--)
			possibilities.add(new Pair(x, i));
		
		// orizzontali dx		
		for (int i = y+1; i < 8; i++)
			possibilities.add(new Pair(x, i));
		
		return possibilities;
	}
	
	public void setMoves(Mover mover) {
		mover.rookMoves(this);	
	}
}