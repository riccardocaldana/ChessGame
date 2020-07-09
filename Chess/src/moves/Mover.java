package moves;

import java.util.ArrayList;
import java.util.Iterator;

import model.Color;
import model.Model;
import model.Null;
import model.Pair;
import model.Piece;

/**
 * Classe che si occupa del movimento dei vari pezzi 
 * conformi alle regole del gioco.
 */

public class Mover {
	private Model model;
	private Rules rules;
	private int fromX;
	private int fromY;
	private ArrayList<Pair> moves;

	/**
	 * Re bianco
	 */
	public Piece wKing;

	/**
	 * Re nero
	 */
	public Piece bKing;
	
	/**
	 * Pezzo responsabile dello scacco
	 */
	public Piece checkPiece;

	public Mover(Model model) {
		this.model = model;
		this.rules = new Rules(model, this);
		bKing = model.getPiece(0, 4);	//posizione iniziale del Re nero
		wKing = model.getPiece(7, 4);	//posizione iniziale del Re bianco
	}

	/**
	 * Corregge le possibili mosse dell'alfiere
	 * @param piece pezzo su cui correggere le mosse
	 */
	public void bishopMoves(Piece piece) {
		moves = piece.possibilities();
		int x = piece.getX();
		int y = piece.getY();
		if (y != 7 || x != 7) {
			// diagonale dx sopra
			for (int i = x - 1, j = y + 1; i >= 0; i--) {
				if (j < 8) {
					if (!(model.getPiece(i, j) instanceof Null)) {
						if (model.getPiece(i, j).getColor() == piece.getColor()) {
							for (int k = i, w = j; k >= 0; k--) {
								moves.remove(new Pair(k, w));
								w++;
							}
						} else
							for (int k = i, w = j; k >= 0; k--) {
								moves.remove(new Pair(k - 1, w + 1));
								w++;
							}
					}
					j++;
				}
			}
		}
		// diagonale dx sotto
		for (int i = x + 1, j = y + 1; i < 8; i++) {
			if (j < 8) {
				if (!(model.getPiece(i, j) instanceof Null)) {
					if (model.getPiece(i, j).getColor() == piece.getColor()) {
						for (int k = i, w = j; k < 8; k++) {
							moves.remove(new Pair(k, w));
							w++;
						}
					} else
						for (int k = i, w = j; k < 8; k++) {
							moves.remove(new Pair(k + 1, w + 1));
							w++;
						}
				}
				j++;
			}
		}
		if (y != 0 || x != 0) {
			// diagonale sx sopra
			for (int i = x - 1, j = y - 1; i >= 0; i--) {
				if (j > 0) {
					if (!(model.getPiece(i, j) instanceof Null)) {
						if (model.getPiece(i, j).getColor() == piece.getColor()) {
							for (int k = i, w = j; k >= 0; k--) {
								moves.remove(new Pair(k, w));
								w--;
							}
						} else
							for (int k = i, w = j; k >= 0; k--) {
								moves.remove(new Pair(k - 1, w - 1));
								w--;
							}
					}
					j--;
				}
			}
			// diagonale sx sotto
			for (int i = x + 1, j = y - 1; i < 8; i++) {
				if (j >= 0) {
					if (!(model.getPiece(i, j) instanceof Null)) {
						if (model.getPiece(i, j).getColor() == piece.getColor()) {
							for (int k = i, w = j; k < 8; k++) {
								moves.remove(new Pair(k, w));
								w--;
							}
						} else
							for (int k = i, w = j; k < 8; k++) {
								moves.remove(new Pair(k + 1, w - 1));
								w--;
							}
					}
					j--;
				}
			}
		}
	}

	/**
	 * Corregge le possibili mosse del pedone
	 * @param piece pezzo su cui correggere le mosse
	 */
	public void pawnMoves(Piece piece) {
		moves = piece.possibilities();
		int x = piece.getX();
		int y = piece.getY();
		if (piece.getColor() == Color.WHITE) {
			if (x != 0) {
				if (y != 0
						&& ((model.getPiece(x - 1, y - 1) instanceof Null) || (model
								.getPiece(x - 1, y - 1).getColor() == piece
								.getColor())))
					moves.remove(new Pair(x - 1, y - 1));
				if (y != 7
						&& ((model.getPiece(x - 1, y + 1) instanceof Null) || (model
								.getPiece(x - 1, y + 1).getColor() == piece
								.getColor())))
					moves.remove(new Pair(x - 1, y + 1));
				if (!(model.getPiece(x - 1, y) instanceof Null))
					moves.remove(new Pair(x - 1, y));
			}
		} else {
			if (x != 7) {
				if (y != 0
						&& ((model.getPiece(x + 1, y - 1) instanceof Null) || (model
								.getPiece(x + 1, y - 1).getColor() == piece
								.getColor())))
					moves.remove(new Pair(x + 1, y - 1));
				if (y != 7
						&& ((model.getPiece(x + 1, y + 1) instanceof Null) || (model
								.getPiece(x + 1, y + 1).getColor() == piece
								.getColor())))
					moves.remove(new Pair(x + 1, y + 1));
				if (!(model.getPiece(x + 1, y) instanceof Null))
					moves.remove(new Pair(x + 1, y));
			}
		}
	}

	/**
	 * Corregge le possibili mosse della regina
	 * @param piece pezzo su cui correggere le mosse
	 */
	public void queenMoves(Piece piece) {
		moves = piece.possibilities();
		int x = piece.getX();
		int y = piece.getY();
		if (y != 7 || x != 7) {
			// diagonale dx sopra
			for (int i = x - 1, j = y + 1; i >= 0; i--) {
				if (j < 8) {
					if (!(model.getPiece(i, j) instanceof Null)) {
						if (model.getPiece(i, j).getColor() == piece.getColor()) {
							for (int k = i, w = j; k >= 0; k--) {
								moves.remove(new Pair(k, w));
								w++;
							}
						} else
							for (int k = i, w = j; k >= 0; k--) {
								moves.remove(new Pair(k - 1, w + 1));
								w++;
							}
					}
					j++;
				}
			}
		}
		// diagonale dx sotto
		for (int i = x + 1, j = y + 1; i < 8; i++) {
			if (j < 8) {
				if (!(model.getPiece(i, j) instanceof Null)) {
					if (model.getPiece(i, j).getColor() == piece.getColor()) {
						for (int k = i, w = j; k < 8; k++) {
							moves.remove(new Pair(k, w));
							w++;
						}
					} else
						for (int k = i, w = j; k < 8; k++) {
							moves.remove(new Pair(k + 1, w + 1));
							w++;
						}
				}
				j++;
			}
		}
		if (y != 0 || x != 0) {
			// diagonale sx sopra
			for (int i = x - 1, j = y - 1; i >= 0; i--) {
				if (j > 0) {
					if (!(model.getPiece(i, j) instanceof Null)) {
						if (model.getPiece(i, j).getColor() == piece.getColor()) {
							for (int k = i, w = j; k >= 0; k--) {
								moves.remove(new Pair(k, w));
								w--;
							}
						} else
							for (int k = i, w = j; k >= 0; k--) {
								moves.remove(new Pair(k - 1, w - 1));
								w--;
							}
					}
					j--;
				}
			}
			// diagonale sx sotto
			for (int i = x + 1, j = y - 1; i < 8; i++) {
				if (j >= 0) {
					if (!(model.getPiece(i, j) instanceof Null)) {
						if (model.getPiece(i, j).getColor() == piece.getColor()) {
							for (int k = i, w = j; k < 8; k++) {
								moves.remove(new Pair(k, w));
								w--;
							}
						} else
							for (int k = i, w = j; k < 8; k++) {
								moves.remove(new Pair(k + 1, w - 1));
								w--;
							}
					}
					j--;
				}
			}
		}
		// verticali sopra
		for (int i = x - 1; i >= 0; i--)
			if (!(model.getPiece(i, y) instanceof Null)) {
				if (model.getPiece(i, y).getColor() == piece.getColor())
					for (int j = i; j >= 0; j--)
						moves.remove(new Pair(j, y));
				else
					for (int j = i - 1; j >= 0; j--)
						moves.remove(new Pair(j, y));
			}

		// verticali sotto
		for (int i = x + 1; i < 8; i++)
			if (!(model.getPiece(i, y) instanceof Null)) {
				if (model.getPiece(i, y).getColor() == piece.getColor())
					for (int j = i; j < 8; j++)
						moves.remove(new Pair(j, y));
				else
					for (int j = i + 1; j < 8; j++)
						moves.remove(new Pair(j, y));
			}

		// orizzontali dx
		for (int i = y + 1; i < 8; i++)
			if (!(model.getPiece(x, i) instanceof Null)) {
				if (model.getPiece(x, i).getColor() == piece.getColor())
					for (int j = i; j < 8; j++)
						moves.remove(new Pair(x, j));
				else
					for (int j = i + 1; j < 8; j++)
						moves.remove(new Pair(x, j));
			}

		// orizzontali sx
		for (int i = y - 1; i >= 0; i--)
			if (!(model.getPiece(x, i) instanceof Null)) {
				if (model.getPiece(x, i).getColor() == piece.getColor())
					for (int j = i; j >= 0; j--)
						moves.remove(new Pair(x, j));
				else
					for (int j = i - 1; j >= 0; j--)
						moves.remove(new Pair(x, j));
			}
	}

	/**
	 * Corregge le possibili mosse della torre
	 * @param piece pezzo su cui correggere le mosse
	 */
	public void rookMoves(Piece piece) {
		moves = piece.possibilities();
		int x = piece.getX();
		int y = piece.getY();
		// verticali sopra
		for (int i = x - 1; i >= 0; i--)
			if (!(model.getPiece(i, y) instanceof Null)) {
				if (model.getPiece(i, y).getColor() == piece.getColor())
					for (int j = i; j >= 0; j--)
						moves.remove(new Pair(j, y));
				else
					for (int j = i - 1; j >= 0; j--)
						moves.remove(new Pair(j, y));
			}

		// verticali sotto
		for (int i = x + 1; i < 8; i++)
			if (!(model.getPiece(i, y) instanceof Null)) {
				if (model.getPiece(i, y).getColor() == piece.getColor())
					for (int j = i; j < 8; j++)
						moves.remove(new Pair(j, y));
				else
					for (int j = i + 1; j < 8; j++)
						moves.remove(new Pair(j, y));
			}

		// orizzontali dx
		for (int i = y + 1; i < 8; i++)
			if (!(model.getPiece(x, i) instanceof Null)) {
				if (model.getPiece(x, i).getColor() == piece.getColor())
					for (int j = i; j < 8; j++)
						moves.remove(new Pair(x, j));
				else
					for (int j = i + 1; j < 8; j++)
						moves.remove(new Pair(x, j));
			}

		// orizzontali sx
		for (int i = y - 1; i >= 0; i--)
			if (!(model.getPiece(x, i) instanceof Null)) {
				if (model.getPiece(x, i).getColor() == piece.getColor())
					for (int j = i; j >= 0; j--)
						moves.remove(new Pair(x, j));
				else
					for (int j = i - 1; j >= 0; j--)
						moves.remove(new Pair(x, j));
			}
	}

	/**
	 * Corregge le possibili mosse del cavallo
	 * @param piece pezzo su cui correggere le mosse
	 */
	public void knightMoves(Piece piece) {
		moves = piece.possibilities();
		Iterator<Pair> it = moves.iterator();
		while (it.hasNext()) {
			Pair p = it.next();
			if (!(model.getPiece(p.getX(), p.getY()) instanceof Null))
				if (model.getPiece(p.getX(), p.getY()).getColor() == piece
						.getColor())
					it.remove();
		}
	}

	/**
	 * Corregge le possibili mosse del re
	 * @param piece pezzo su cui correggere le mosse
	 */
	public void kingMoves(Piece piece) {
		moves = piece.possibilities();
		Iterator<Pair> it = moves.iterator();
		while (it.hasNext()) {
			Pair p = it.next();
			if (!(model.getPiece(p.getX(), p.getY()) instanceof Null))
				if (model.getPiece(p.getX(), p.getY()).getColor() == piece
						.getColor())
					it.remove();

		}
	}

	/**
	 * Esegue la mossa se la simulazione è avvenuta con successo senza scacco/scacco matto
	 * @param toX posizione x dove muoversi
	 * @param toY posizione y dove muoversi
	 * @return boolean: true se la mossa è avvenuta con successo, altrimenti false
	 */
	public boolean canMove(int toX, int toY) {
		Piece piece = model.getPiece(fromX, fromY);
		if (simulate(fromX, fromY,toX, toY)) {
			if (rules.tryPawn(toX, toY, piece)
					|| (rules.tryMove(toX, toY, piece))) {
				model.getModelChess()[toX][toY] = piece;
				piece.setX(toX);	//aggiornamento posizione x del pezzo
				piece.setY(toY);	//aggiornamento posizione y del pezzo
				model.getModelChess()[fromX][fromY] = new Null(fromX, fromY);	//sostuisce al pezzo mosso la casella vuota (Null)
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Simula la mossa e verifica eventuali scacchi
	 * @param fromX posizione x di partenza
	 * @param fromY posizione y di partenza
	 * @param toX posizione x di arrivo
	 * @param toY posiione y di arrivo
	 * @return boolean: true il pezzo può muoversi, altrimenti false
	 */
	// 2 possibilità: sulla traiettoria->no mossa , non sulla traiettoria ma
	// mossa fa salvataggio->si mossa
	public boolean simulate(int fromX, int fromY, int toX, int toY) {
		boolean res = true;
		Piece piece = model.getPiece(fromX, fromY);
		Piece pieceTo = model.getPiece(toX, toY);
		Color turn = model.getPiece(fromX, fromY).getColor() == Color.WHITE ? Color.BLACK
				: Color.WHITE;
		//sostituzione
		model.getModelChess()[toX][toY] = piece;
		piece.setX(toX);
		piece.setY(toY);
		model.getModelChess()[fromX][fromY] = new Null(fromX, fromY);
		//verifica scacco
		if (rules.isCheck(turn))	
			res = false;
		else
			res = true;
		//ripristino
		model.getModelChess()[fromX][fromY] = piece;
		piece.setX(fromX);
		piece.setY(fromY);
		model.getModelChess()[toX][toY] = pieceTo;
		return res;
	}

	/**
	 * Ritorna una lista delle possibli mosse corrette
	 * @return ArrayList -Pair-
	 */
	public ArrayList<Pair> getMoves() {
		return moves;
	}

	/**
	 * Imposta la coordinata x del pezzo
	 * @param x coordinata x del pezzo
	 */
	public void setX(int x) {
		this.fromX = x;
	}

	/**
	 * Ritorna la coordinata x del pezzo
	 * @return int fromX
	 */
	public int getX(){
		return fromX;
	}
	/**
	 * Imposta la coordinata y del pezzo
	 * @param y coordinata y del pezzo
	 */
	public void setY(int y) {
		this.fromY = y;
	}

	/**
	 * Ritorna la coordinata y del pezzo
	 * @return int fromY
	 */
	public int getY(){
		return fromY;
	}

	/**
	 * Ritorna le regole del gioco
	 * @return Rules
	 */
	public Rules getRules() {
		return rules;
	}
}