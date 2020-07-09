package moves;

import java.util.ArrayList;

import model.Color;
import model.King;
import model.Model;
import model.Null;
import model.Pair;
import model.Pawn;
import model.Piece;

/**
 * Classe che definisce le regole del gioco.
 * Regole di movimento consentito utilizzabili dal Mover
 * Regole di scacco, scacco matto, pareggio utilizzabili dal Controller
 */

public class Rules {
	private Model model;
	private Mover mover;

	public Rules(Model model, Mover mover) {
		this.model = model;
		this.mover = mover;
	}

	/**
	 * Verifica prima dello spostamento se la mossa del pedone è consentita
	 * @param toX posizione x di arrivo
	 * @param toY posizione y di arrivo
	 * @param piece pezzo su cui verificare lo spostamento
	 * @return boolean: true se il pedone può muoversi, altrimenti false
	 */
	public boolean tryPawn(int toX, int toY, Piece piece) {
		if (piece instanceof Pawn) {
			Pair newPair = new Pair(toX, toY);
			piece.setMoves(mover);
			if (piece.getColor() == Color.WHITE) {
				if (mover.getMoves().contains(newPair))
					// pedone dritto
					if ((toX == (piece.getX() - 1) && toY == piece.getY() && model
							.getPiece(toX, toY) instanceof Null)
							|| (!(model.getPiece(toX, toY) instanceof Null) && model
									.getPiece(toX, toY).getColor() != piece
									.getColor()))
						return true;
				return false;
			} else {
				if (mover.getMoves().contains(newPair))
					if ((toX == (piece.getX() + 1) && toY == piece.getY() && model
							.getPiece(toX, toY) instanceof Null)
							|| (!(model.getPiece(toX, toY) instanceof Null) && model
									.getPiece(toX, toY).getColor() != piece
									.getColor()))
						return true;
				return false;
			}
		}
		return false;
	}

	/**
	 * Verifica prima dello spostamento se la mossa di tutti gli altri pezzi è consentita
	 * @param toX posizione x di arrivo
	 * @param toY posizione y di arrivo
	 * @param piece pezzo su cui verificare lo spostamento
	 * @return boolean: true se i pezzi possono muoversi, altrimenti false
	 */
	public boolean tryMove(int toX, int toY, Piece piece) {
		Pair newPair = new Pair(toX, toY);
		piece.setMoves(mover);
		if (mover.getMoves().contains(newPair)) {
			if (model.getPiece(toX, toY) instanceof Null)
				return true;
			else {
				if (model.getPiece(toX, toY).getColor() != piece.getColor()) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Controlla la posizine del pedone per eventuale promozione
	 * @param x posizione x del pedone
	 * @param y posizione y del pedone
	 * @return boolean: true se promozione possibile, altrimenti false
	 */
	public boolean pawnUpgrade(int x, int y) {
		Piece p = model.getPiece(x, y);
		if(p instanceof Pawn && p.getColor() == Color.WHITE && x == 0)
			return true;
		else if (p instanceof Pawn && p.getColor() == Color.BLACK && x == 7)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica lo scacco
	 * @param turn turno della partita
	 * @return boolean: true se scacco, altrimenti false
	 */
	public boolean isCheck(Color turn) {
		if (turn == Color.WHITE) { // neri sotto scacco
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					Piece p = model.getPiece(i, j);
					if (p.getColor() == Color.WHITE) {
						p.setMoves(mover); 
						if (mover.getMoves()
								.contains(
										new Pair(mover.bKing.getX(),
												mover.bKing.getY()))) {
							mover.checkPiece = p;
							return true;
						}
					}
				}
			return false;
		} else if (turn == Color.BLACK) { // bianchi sotto scacco
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					Piece p = model.getPiece(i, j);
					if (p.getColor() == Color.BLACK) {
						p.setMoves(mover);
						if (mover.getMoves()
								.contains(
										new Pair(mover.wKing.getX(),
												mover.wKing.getY()))) {
							mover.checkPiece = p;
							return true;
						}
					}
				}
			return false;
		}
		return false;
	}


	/**
	 * Verifica lo scacco matto
	 * @param turn turno della partita
	 * @return boolean: true se scacco matto, altrimenti false
	 */
	public boolean isCheckMate(Color turn) {
		return kingCantMove(turn) && pieceCantEat(turn) && pieceCantCross(turn);
	}

	/**
	 * Verifica se esistono eventuali mosse possibili per il re sotto scacco
	 * @param turn turno della partita
	 * @return boolean: true se non può muoversi, altrimenti false
	 */
	private boolean kingCantMove(Color turn) {
		// 1: re può muoversi?
		if (turn == Color.WHITE) {
			mover.bKing.setMoves(mover);
			for (Pair p : mover.getMoves())
				if (mover.simulate(mover.bKing.getX(), mover.bKing.getY(),
						p.getX(), p.getY()))
					return false;
		} else {
			mover.wKing.setMoves(mover);
			for (Pair p : mover.getMoves())
				if (mover.simulate(mover.wKing.getX(), mover.wKing.getY(),
						p.getX(), p.getY()))
					return false;
		}
		return true;
	}

	/**
	 * Verifica se esistono pezzi che possono mangiare il pezzo responsabile dello scacco
	 * @param turn turno della partita
	 * @return boolean: true se non esistono, altrimenti false
	 */
	private boolean pieceCantEat(Color turn) {
		// 2: pezzi possono mangiare il pezzo responsabile dello scacco (checkPiece)
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = model.getPiece(i, j);
				if (turn == Color.WHITE) {
					if (p.getColor() == Color.BLACK) {
						p.setMoves(mover);
						if (mover.getMoves().contains(
								new Pair(mover.checkPiece.getX(),
										mover.checkPiece.getY())))
							return false;
					}
				} else {
					if (p.getColor() == Color.WHITE) {
						p.setMoves(mover);
						if (mover.getMoves().contains(
								new Pair(mover.checkPiece.getX(),
										mover.checkPiece.getY())))
							return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Verifica se esistono pezzi che possono intercettare la traiettoria dello scacco
	 * @param turn turno della partita
	 * @return boolean: true se non esistono, altrimenti false
	 */
	private boolean pieceCantCross(Color turn) {
		// 3: pezzi possono interferire con la traiettoria dello scacco
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = model.getPiece(i, j);
				if (turn == Color.WHITE) {
					if (p.getColor() == Color.BLACK) {
						p.setMoves(mover);
						ArrayList<Pair> mosse = mover.getMoves();
						mover.checkPiece.setMoves(mover);
						for (Pair pair : mover.getMoves())
							if (mosse.contains(pair)
									&& mover.simulate(p.getX(), p.getY(),
											pair.getX(), pair.getY()))
								return false;
					}
				} else {
					if (p.getColor() == Color.WHITE) {
						p.setMoves(mover);
						ArrayList<Pair> mosse = mover.getMoves();
						mover.checkPiece.setMoves(mover);
						for (Pair pair : mover.getMoves())
							if (mosse.contains(pair)
									&& mover.simulate(p.getX(), p.getY(),
											pair.getX(), pair.getY()))
								return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Controlla se sulla scacchiera sono presenti soltanto i due Re (Pareggio)
	 * @return boolean: true se pareggio, altrimenti false
	 */
	public boolean tieChess() {
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				Piece p = model.getPiece(i, j);
				if(!(p instanceof King) && !(p instanceof Null))
					return false;
			}
		return true;
	}
}