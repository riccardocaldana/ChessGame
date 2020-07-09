package test;

import model.Bishop;
import model.ChessModel;
import model.Color;
import model.Null;
import moves.Mover;

import org.junit.Assert;
import org.junit.Test;

public class MoverTest {

	@Test
	public void simulateFalseTest(){	//pedone su traiettoria scacco non può muoversi
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		model.getModelChess()[3][1] = new Bishop(3,1,Color.WHITE);
		Assert.assertTrue(false == mover.simulate(1, 3, 2, 3));
	}
	
	@Test
	public void simulateTrueTest(){ //pedone intercetta traiettoria scacco, può muoversi
		ChessModel model=new ChessModel();
		Mover mover = new Mover(model);
		model.getModelChess()[3][1] = new Bishop(3,1,Color.WHITE);
		model.getModelChess()[1][3] = new Null (1,3);
		Assert.assertTrue(true == mover.simulate(1, 2, 2, 2));
	}
}
