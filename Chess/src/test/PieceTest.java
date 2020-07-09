package test;

import model.Color;
import model.ChessModel;

import org.junit.Assert;
import org.junit.Test;

public class PieceTest {

	@Test
	public void colorTest(){ //return colore del pezzo
		ChessModel model=new ChessModel();
		Assert.assertEquals(model.getModelChess()[0][0].getColor(), Color.BLACK);
	}
	
	@Test
	public void xTest(){ //return posizione x del pezzo
		ChessModel model=new ChessModel();
		Assert.assertEquals(model.getModelChess()[0][0].getX(), 0);
	}
	
	@Test
	public void yTest(){ //return posizione y del pezzo
		ChessModel model=new ChessModel();
		Assert.assertEquals(model.getModelChess()[0][0].getY(), 0);
	}	
}
