package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import moves.Mover;

/**
 * Classe che descrive la cella vuota sulla scacchiera
 */
public class Null extends Piece {

	public Null(int x, int y) {
		super(x,y,Color.NULL);
	}
	
	public ImageIcon getImage() {
		return null;
	}
	
	@Override
	public ArrayList<Pair> possibilities() {
		return new ArrayList<Pair>();
	}
	
	public void setMoves(Mover mover) {
	}
}
