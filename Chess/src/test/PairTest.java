package test;


import model.Pair;

import org.junit.Assert;
import org.junit.Test;

public class PairTest {

	@Test
	public void equalsTest(){  //test di uguaglianza tra coppie di coordinate
		Pair p = new Pair(1,3);
		Assert.assertEquals(p,new Pair(1,3));
	}
}
