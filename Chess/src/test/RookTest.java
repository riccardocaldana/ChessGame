package test;

import model.ChessModel;
import model.Color;
import model.Null;
import model.Pawn;
import moves.Mover;

import org.junit.Assert;
import org.junit.Test;

public class RookTest {

	@Test
	public void canMoveFalseTest(){ //movimento non consentito/mangiare pezzo amico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(0);
		mover.setY(0);
		Assert.assertTrue(false==mover.canMove(1, 0));
	}
	
	@Test
	public void canMoveTrueTest(){ //movimento consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[1][0] = new Null(1,0);
		mover.setX(0);
		mover.setY(0);
		Assert.assertTrue(true==mover.canMove(5, 0));
	}
	
	@Test
	public void canMoveOnFalseTest(){ //movimento avanti di due non consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(0);
		mover.setY(0);
		Assert.assertTrue(false==mover.canMove(2, 0));
	}
	
	@Test
	public void canEatTrueTest(){ //mangiare pezzo nemico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[1][0] = new Pawn(1,0,Color.WHITE);
		mover.setX(0);
		mover.setY(0);
		Assert.assertTrue(true==mover.canMove(1, 0));
	}
}
