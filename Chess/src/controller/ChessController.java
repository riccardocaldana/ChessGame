package controller;

import model.Bishop;
import model.Color;
import model.Knight;
import model.Queen;
import model.Rook;
import moves.Mover;
import moves.Rules;
import view.View;

/**
 * Classe che gestisce le azioni da intraprendere dopo
 * l'interazione utente-gioco e si occupa di tutti i controlli
 * necessari per il corretto svolgimento della partita
 */
public class ChessController implements Controller {

	private View view;
	private Mover mover;
	private Rules rules;
	private Color turn = Color.WHITE;
	private int click;
	
	public ChessController(View view) {
		this.view = view;
		this.mover = new Mover (view.getModel());
		this.rules = mover.getRules();
		click = 0;
		view.setController(this); //stabilisce il controller della view
	}	

	@Override
	public void onClick(int x, int y) {
		if(click == 0){	//primo click
			if(turn == view.getModel().getPiece(x, y).getColor()){	//gestione turno
				view.getModel().getPiece(x, y).setMoves(mover);	//calcola mosse
				view.colorPiece(x,y);	//colora casella pezzo
				mover.setX(x); //salva fromX nel Mover
				mover.setY(y); //salva fromY nel Mover
				click++;	
			}
		}
		else{	//secondo click
			if(mover.canMove(x,y)){	//prova ad eseguire la mossa
				view.onConfigurationChange(); //modifica grafica
				view.noColorPiece(mover.getX(),mover.getY());	//decolora la casella precedente del pezzo se c'è stata la mossa
				if (rules.pawnUpgrade(x,y))	//controlla condizione promozione pedone
					changePiece(view.showPawnUpgrade(x,y),x,y);
				if(rules.isCheck(turn)){	//controlla lo scacco
					if(rules.isCheckMate(turn))	//controlla lo scacco matto
						view.showCheckMateWindow();	
					else
						view.showCheckWindow();
						
				}
				if(rules.tieChess())	//controlla il pareggio
					view.showTieChessWindow();
				int index=turn.ordinal();	//cambio turno
				index=(index+1)%2;
				turn=Color.values()[index];
			}
			view.noColorPiece(mover.getX(),mover.getY());	//decolora la casella del pezzo se non c'è mossa
			click=0;	
		}
	}

	@Override
	public String getTurn() {
		return turn == Color.WHITE ? "BIANCO" : "NERO";
	}
	
	/**
	 * Promuove il pedone, sostituendo ad esso il nuovo pezzo selezionato
	 * @param n intero del pezzo selezionato
	 * @param x posizione x del pedone/nuovo pezzo
	 * @param y posizione y del pedone/nuovo pezzo
	 */
	private void changePiece(int n,int x,int y){
		switch(n){
		case 0:
			view.getModel().getModelChess()[x][y] = new Rook(x,y,view.getModel().getPiece(x, y).getColor());
			view.onConfigurationChange();
			break;
		case 1:
			view.getModel().getModelChess()[x][y] = new Knight(x,y,view.getModel().getPiece(x, y).getColor());
			view.onConfigurationChange();
			break;
		case 2:
			view.getModel().getModelChess()[x][y] = new Bishop(x,y,view.getModel().getPiece(x, y).getColor());
			view.onConfigurationChange();
			break;
		case 3:
			view.getModel().getModelChess()[x][y] = new Queen(x,y,view.getModel().getPiece(x, y).getColor());
			view.onConfigurationChange();
			break;
		}
	}
}
