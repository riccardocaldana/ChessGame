package test;
import model.ChessModel;
import model.Color;
import model.Null;
import model.Pawn;
import moves.Mover;

import org.junit.Assert;
import org.junit.Test;

public class BishopTest {
	
	@Test
	public void canMoveFalseTest(){ //movimento diagonale non consentito/mangiare pezzo amico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(0);
		mover.setY(2);
		Assert.assertTrue(false==mover.canMove(1, 3));
	}
	
	@Test
	public void canMoveOnFalseTest(){ //movimento avanti non consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(0);
		mover.setY(2);
		Assert.assertTrue(false==mover.canMove(1, 2));
	}
	
	@Test
	public void canMoveTrueTest(){ //movimento diagonale consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[1][3] = new Null(1,3);
		mover.setX(0);
		mover.setY(2);
		Assert.assertTrue(true==mover.canMove(1, 3));
	}
	
	@Test
	public void canEatTrueTest(){ //mangiare pezzo nemico
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		model.getModelChess()[1][3] = new Pawn(1,3,Color.WHITE);
		mover.setX(0);
		mover.setY(2);
		Assert.assertTrue(true==mover.canMove(1, 3));
	}
}
