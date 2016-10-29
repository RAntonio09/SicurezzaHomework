package Test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import implementationTask.Zeta;

public class ZetaTest {
	private Zeta z;

	@Before
	public void init() {
		z = new Zeta(10);
	}

	@Test
	public void addTest(){
		assertEquals("Add 3 + 2 = 5 ", z.add(BigInteger.valueOf(3), BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(5)), 0);
	}

}
