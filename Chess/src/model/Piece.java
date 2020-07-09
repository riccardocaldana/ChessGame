package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import moves.Mover;

/**
 * Classe che descrive il pezzo della scacchiera
 */
public abstract class Piece {
	protected int x;
	protected int y;
	protected Color color;

	public Piece(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**
	 * Ritorna la posizione x del pezzo
	 * @return int x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Ritorna la posizione y del pezzo
	 * @return int y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Imposta la posizione x del pezzo
	 * @param x posizione x del pezzo
	 */
	public void setX(int x){
		this.x=x;
	}
	
	/**
	 * Imposta la posizione y del pezzo
	 * @param y posizione y del pezzo
	 */
	public void setY(int y){
		this.y=y;
	}
	
	/**
	 * Ritorna il colore del pezzo 
	 * @return Color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Ritorna l'immagine corrispondente al pezzo
	 * @return ImageIcon
	 */
	public abstract ImageIcon getImage();

	/**
	 * Ricalcola le mosse in base alla posizione del pezzo sulla scacchiera
	 * @param mover classe gestione mosse
	 */
	public abstract void setMoves(Mover mover);

	/**
	 * Calcola tutte le mosse del pezzo, anche quelle non possibili
	 * @return ArrayList -Pair-
	 */
	public abstract ArrayList<Pair> possibilities();
}
