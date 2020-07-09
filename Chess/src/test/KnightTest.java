package test;

import model.ChessModel;
import model.Color;
import model.Pawn;
import moves.Mover;

import org.junit.Assert;
import org.junit.Test;

public class KnightTest {

	@Test
	public void canMoveFalseTest(){ //movimento non consentito/mangiare pezzo amico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(7);
		mover.setY(1);
		Assert.assertTrue(false==mover.canMove(6, 3));
	}
	
	@Test
	public void canMoveTrueTest(){ //movimento consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(7);
		mover.setY(1);
		Assert.assertTrue(true==mover.canMove(5, 0));
	}
	
	@Test
	public void canMoveOnFalseTest(){ //movimento avanti di due non consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(7);
		mover.setY(1);
		Assert.assertTrue(false==mover.canMove(5, 1));
	}
	
	@Test
	public void canEatTrueTest(){ //mangiare pezzo nemico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[5][0] = new Pawn(5,0,Color.BLACK);
		mover.setX(7);
		mover.setY(1);
		Assert.assertTrue(true==mover.canMove(5, 0));
	}
}
