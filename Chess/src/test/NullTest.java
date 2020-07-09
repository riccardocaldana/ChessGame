package test;

import model.ChessModel;
import moves.Mover;

import org.junit.Assert;
import org.junit.Test;

public class NullTest {
	
	@Test
	public void canMoveFalseTest(){ //movimento non consentito
		ChessModel model=new ChessModel();
		Mover mover=new Mover(model);
		mover.setX(2);
		mover.setY(0);
		Assert.assertTrue(false==mover.canMove(2, 1));
	}
}
