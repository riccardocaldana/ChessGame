package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import moves.Mover;

/**
 * Classe che descrive il Cavallo
 */
public class Knight extends Piece{
	private ImageIcon imgWhite=new ImageIcon(getClass().getResource("/img/WhiteN.png"));
	private ImageIcon imgBlack=new ImageIcon(getClass().getResource("/img/BlackN.png"));
	
	public Knight(int x, int y, Color color) {
		super(x,y,color);
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
		if(x-2>=0 && y-1>=0)
			possibilities.add(new Pair(x-2,y-1));
		if(x-1>=0 && y-2>=0)
			possibilities.add(new Pair(x-1,y-2));
		if(x+1<8 && y-2>=0)
			possibilities.add(new Pair(x+1,y-2));
		if(x+2<8 && y-1>=0)
			possibilities.add(new Pair(x+2,y-1));
		if(x-2>=0 && y+1<8)
			possibilities.add(new Pair(x-2,y+1));
		if(x-1>=0 && y+2<8)
			possibilities.add(new Pair(x-1,y+2));
		if(x+1<8 && y+2<8)
			possibilities.add(new Pair(x+1,y+2));
		if(x+2<8 && y+1<8)
			possibilities.add(new Pair(x+2,y+1));
		
		return possibilities;
	}
	
	public void setMoves(Mover mover) {
		mover.knightMoves(this);	
	}
}