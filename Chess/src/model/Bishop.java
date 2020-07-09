package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import moves.Mover;

/**
 * Classe che descrive l'Alfiere
 */
public class Bishop extends Piece{ 
	private ImageIcon imgWhite=new ImageIcon(getClass().getResource("/img/WhiteB.png"));
	private ImageIcon imgBlack=new ImageIcon(getClass().getResource("/img/BlackB.png"));
	
	public Bishop(int x, int y, Color color) {
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
		//diagonale dx sopra
		for (int i = x,j=y; i-1>=0; i--){
			if(j+1<8){
				possibilities.add(new Pair(i-1, j+1));
				j++;
			}
			else
				break;
		}
		
		//diaognale dx sotto
		for (int i = x,j=y; i+1 < 8; i++){
			if(j+1<8){
				possibilities.add(new Pair(i+1, j+1));
				j++;
			}
			else
				break;
		}
		
		//diagonale sx sopra
		for (int i = x,j=y; i-1>=0; i--){
			if(j-1>=0){
				possibilities.add(new Pair(i-1, j-1));
				j--;
			}
			else
				break;
		}
		//diagonale sx sotto
		for (int i = x,j=y; i+1 < 8; i++){
			if(j-1>=0){
				possibilities.add(new Pair(i+1, j-1));
				j--;
			}
			else
				break;
		}
		
		return possibilities;
	}

	@Override
	public void setMoves(Mover mover) {
		mover.bishopMoves(this);	
	}	
}
