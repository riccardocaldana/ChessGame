package view;

import controller.Controller;
import model.Model;

/**
 * Interfaccia grafica
 */

public interface View {

	/**
	 * Ridisegna la configurazione della scacchiera aggiornata
	 */
	public void onConfigurationChange();
	
	/**
	 * Setta il controller per questa interfaccia
	 * @param controller interfaccia del controllore
	 */
	public void setController(Controller controller);
	
	/**
	 * Ritorna l'interfaccia della struttura dati della scacchiera
	 * @return model
	 */
	public Model getModel();

	/**
	 * Colora le posizione del pezzo selezionato
	 * @param x posizione x del pezzo
	 * @param y posizione y del pezzo
	 */
	public void colorPiece(int x, int y);
	
	/**
	 * Decolora la posizine del pezzo appena mosso
	 * @param x posizione x del pezzo
	 * @param y posizione y del pezzo
	 */
	public void noColorPiece(int x, int y);

	/**
	 * Mostra la finestra a comparsa per la condizione di scacco
	 */
	public void showCheckWindow();

	/**
	 * Mostra la finestra a comparsa per la condizione di scacco matto
	 */
	public void showCheckMateWindow();

	/**
	 * Mostra la finestra a comporsa per l'azione di promozione del pedone
	 * @param x posizione x del pedone e del "nuovo pezzo"
	 * @param y posizione y del pedone e del "nuovo pezzo"
	 * @return int: 0 per la torre, 1 per il cavallo, 2 per l'alfiere, 3 per la regina, 4 per restare pedone
	 */
	public int showPawnUpgrade(int x,int y);

	/**
	 * Mostra la finestra a comparsa per la condizione di pareggio
	 */
	public void showTieChessWindow();
}
