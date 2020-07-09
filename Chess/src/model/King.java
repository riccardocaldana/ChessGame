package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import moves.Mover;
/**
 * Classe che descrive il Re
 */
public class King extends Piece {
	private ImageIcon imgWhite = new ImageIcon(getClass().getResource(
			"/img/WhiteK.png"));
	private ImageIcon imgBlack = new ImageIcon(getClass().getResource(
			"/img/BlackK.png"));

	public King(int x, int y, Color color) {
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
		if (x - 1 >= 0 && y - 1 >= 0)
			possibilities.add(new Pair(x - 1, y - 1));
		if (x - 1 >= 0)
			possibilities.add(new Pair(x - 1, y));
		if (x - 1 >= 0 && y + 1 < 8)
			possibilities.add(new Pair(x - 1, y + 1));
		if (y + 1 < 8)
			possibilities.add(new Pair(x, y + 1));
		if (y - 1 >= 0)
			possibilities.add(new Pair(x, y - 1));
		if (x + 1 < 8 && y + 1 < 8)
			possibilities.add(new Pair(x + 1, y + 1));
		if (x + 1 < 8)
			possibilities.add(new Pair(x + 1, y));
		if (x + 1 < 8 && y - 1 >= 0)
			possibilities.add(new Pair(x + 1, y - 1));
		
		return possibilities;
	}
	
	public void setMoves(Mover mover) {
		mover.kingMoves(this);	
	}
}