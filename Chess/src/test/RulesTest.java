package test;

import model.Bishop;
import model.ChessModel;
import model.Color;
import model.King;
import model.Null;
import model.Pawn;
import model.Queen;
import model.Rook;
import moves.Mover;

import org.junit.Assert;
import org.junit.Test;

public class RulesTest {
	@Test
	public void promoTrueTest(){	//promozione pedone
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[1][2] = new Pawn(1,2,Color.WHITE);
		model.getModelChess()[0][2] = new Null(0,2);
		mover.setX(1);
		mover.setY(2);
		mover.canMove(0, 2);
		Assert.assertTrue(true==mover.getRules().pawnUpgrade(0, 2));
	}
	
	@Test
	public void promoFalseTest(){	//no promozione pedone
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[2][2] = new Pawn(2,2,Color.WHITE);
		model.getModelChess()[1][2] = new Null(1,2);
		mover.setX(2);
		mover.setY(2);
		mover.canMove(1, 2);
		Assert.assertTrue(false==mover.getRules().pawnUpgrade(1, 2));
	}
	
	@Test
	public void isCheckTrueTest(){ //scacco 
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		model.getModelChess()[3][1] = new Bishop(3,1,Color.WHITE);
		model.getModelChess()[1][3] = new Null (1,3);
		Assert.assertTrue(true == mover.getRules().isCheck(Color.WHITE));
	}
	
	@Test
	public void isCheckFalseTest(){ //non scacco
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		model.getModelChess()[3][1] = new Bishop(3,1,Color.WHITE);
		Assert.assertTrue(false == mover.getRules().isCheck(Color.WHITE));
	}
	
	@Test
	public void isCheckMateTrueTest(){ //scacco matto
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		model.getModelChess()[3][1] = new Queen(4,7,Color.BLACK);
		model.getModelChess()[6][5] = new Null (6,5);
		model.getModelChess()[6][6] = new Null (6,6);
		Assert.assertTrue(true == mover.getRules().isCheckMate(Color.BLACK));
	}
	
	@Test
	public void isCheckMateKingCanMoveTest(){ //re può muoversi, no scacco matto
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		model.getModelChess()[3][1] = new Queen(4,7,Color.BLACK);
		model.getModelChess()[6][5] = new Null (6,5);
		model.getModelChess()[6][6] = new Null (6,6);
		model.getModelChess()[6][3] = new Null (6,3);
		Assert.assertTrue(false == mover.getRules().isCheckMate(Color.BLACK));
	}
	
	@Test
	public void isCheckMatePieceCanCrossTest(){ //intercettazione traiettoria, no scacco matto
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		model.getModelChess()[3][1] = new Queen(4,7,Color.BLACK);
		model.getModelChess()[6][5] = new Null (6,5);
		model.getModelChess()[6][6] = new Rook (6,6,Color.WHITE);
		Assert.assertTrue(false == mover.getRules().isCheckMate(Color.BLACK));
	}
	
	@Test
	public void isCheckMatePieceCanEatTest(){ //mangiare pezzo che fa scacco, no scacco matto
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		model.getModelChess()[3][1] = new Queen(4,7,Color.BLACK);
		model.getModelChess()[6][5] = new Null (6,5);
		model.getModelChess()[5][7] = new Rook (5,7,Color.WHITE);
		Assert.assertTrue(false == mover.getRules().isCheckMate(Color.BLACK));
	}
	
	@Test
	public void tieChessFalseTest(){ //no pareggio
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		Assert.assertTrue(false == mover.getRules().tieChess());
	}
	
	@Test
	public void tieChessTrueTest(){ //pareggio
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				model.getModelChess()[i][j] = new Null(i,j);
			}
		}
		model.getModelChess()[0][0] = new King(0,0,Color.BLACK);
		model.getModelChess()[7][7] = new King(7,7,Color.WHITE);
		Assert.assertTrue(true == mover.getRules().tieChess());
	}
}
