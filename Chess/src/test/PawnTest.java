package test;
import model.Color;

import org.junit.Assert;
import org.junit.Test;

import model.ChessModel;
import model.Pawn;
import moves.Mover;

public class PawnTest {
	@Test
	public void canMoveTrueTest(){ //movimento in avanti consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(6);
		mover.setY(5);
		Assert.assertTrue(true==mover.canMove(5, 5));
	}
	
	@Test
	public void canMoveFalseTest(){ //movimento diagonale non consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(6);
		mover.setY(5);
		Assert.assertTrue(false==mover.canMove(5, 6));
	}
	
	@Test
	public void canMoveTest(){ //movimemento in avanti non consentito con pezzo davanti
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[5][5] = new Pawn(5,5,Color.BLACK);
		mover.setX(6);
		mover.setY(5);
		Assert.assertTrue(false==mover.canMove(5, 5));
	}
	
	public void canEatTrueTest(){ //mangiare pezzo nemico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[5][5] = new Pawn(5,6,Color.BLACK);
		mover.setX(6);
		mover.setY(5);
		Assert.assertTrue(true==mover.canMove(5, 6));
	}
	
	public void canEatFalseTest(){ //mangiare pezzo amico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[5][5] = new Pawn(5,6,Color.WHITE);
		mover.setX(6);
		mover.setY(5);
		Assert.assertTrue(false==mover.canMove(5, 6));
	}
}