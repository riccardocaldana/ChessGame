package controller;

/**
 * Interfaccia del controllore
 */
public interface Controller {

	/**
	 * Azione del bottone
	 * @param x posizione x del bottone premuto
	 * @param y posizione y del bottone premuto
	 */
	public void onClick(int x, int y);

	/**
	 * Ritorna la stringa in italiano del turno
	 * @return Stringa 
	 */
	public String getTurn();
}
