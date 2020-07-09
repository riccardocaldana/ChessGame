package model;


/**
 * Interfaccia della struttura dati
 */

public interface Model {

	/**
	 * Ritorna il pezzo alla posizione (x,y)
	 * @param x posizione x
	 * @param y posizione y
	 * @return Piece
	 */
	public Piece getPiece(int x, int y);
	
	/**
	 * Ritorna la configurazione della scacchiera
	 * @return Piece[][]
	 */
	public Piece[][] getModelChess();
}
