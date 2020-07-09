package model;


/**
 * Classe che costruisce tutte le strutture dati utili al gioco
 */

public class ChessModel implements Model {
	// x=righe y=colonne
	//private View view; 
	private Piece[][] chessBoard;
	
	public ChessModel() {
		chessBoard = new Piece[8][8];
		startConfiguration();
	}
	
	/**
	 * Configurazione iniziale della scacchiera
	 */
	private void startConfiguration() {
		//NERI
		//pezzi neri
		chessBoard[0][0] = new Rook (0,0,Color.BLACK);
		chessBoard[0][1] = new Knight (0,1,Color.BLACK);
		chessBoard[0][2] = new Bishop (0,2,Color.BLACK);
		chessBoard[0][3] = new Queen (0,3,Color.BLACK);
		chessBoard[0][4] = new King (0,4,Color.BLACK);
		chessBoard[0][5] = new Bishop (0,5,Color.BLACK);
		chessBoard[0][6] = new Knight (0,6,Color.BLACK);
		chessBoard[0][7] = new Rook (0,7,Color.BLACK);

		//pedoni neri
		for(int i = 0; i < 8 ; i++){
			chessBoard[1][i] = new Pawn(1,i,Color.BLACK);
		}
		
		//caselle vuote (Null)
		for(int i=2;i<6;i++){
			for(int j=0;j<8;j++){
				chessBoard[i][j]=new Null(i,j);
			}
		}
		
		//BIANCHI
		//pedoni bianchi
		for(int i = 0; i < 8 ; i++){
			chessBoard[6][i] = new Pawn(6,i,Color.WHITE);
		}
		
		//pezzi bianchi
		chessBoard[7][0] = new Rook (7,0,Color.WHITE);
		chessBoard[7][1] = new Knight (7,1,Color.WHITE);
		chessBoard[7][2] = new Bishop (7,2,Color.WHITE);
		chessBoard[7][3] = new Queen (7,3,Color.WHITE);
		chessBoard[7][4] = new King (7,4,Color.WHITE);
		chessBoard[7][5] = new Bishop (7,5,Color.WHITE);
		chessBoard[7][6] = new Knight (7,6,Color.WHITE);
		chessBoard[7][7] = new Rook (7,7,Color.WHITE);
	}


	@Override
	public Piece getPiece(int x, int y) {
		return chessBoard[x][y];
	}

	@Override
	public Piece[][] getModelChess() {
		return chessBoard;
	}

}