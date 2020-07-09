package test;

import model.ChessModel;
import model.Color;
import model.Null;
import model.Pawn;
import moves.Mover;

import org.junit.Assert;
import org.junit.Test;

public class QueenTest {

	@Test
	public void canMoveFalseTest(){ //movimento non consentito/mangiare pezzo amico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(7);
		mover.setY(3);
		Assert.assertTrue(false==mover.canMove(6, 3));
	}
	
	@Test
	public void canMoveTrueTest(){ //movimento consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[6][3] = new Null(6,3);
		mover.setX(7);
		mover.setY(3);
		Assert.assertTrue(true==mover.canMove(4, 3));
	}
	
	@Test
	public void canMoveOnFalseTest(){ //movimento avanti di due non consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(7);
		mover.setY(3);
		Assert.assertTrue(false==mover.canMove(5, 3));
	}
	
	@Test
	public void canEatTrueTest(){ //mangiare pezzo nemico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[6][3]= new Pawn(6,3,Color.BLACK);
		mover.setX(7);
		mover.setY(3);
		Assert.assertTrue(true==mover.canMove(6, 3));
	}
}
