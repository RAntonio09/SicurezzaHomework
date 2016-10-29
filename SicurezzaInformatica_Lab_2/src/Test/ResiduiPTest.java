package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import Exception.ElementNotContainException;
import implementationTask.ResiduiP;

public class ResiduiPTest {
	private ResiduiP RQ;

	@Before
	public void init() {
		RQ = new ResiduiP(BigInteger.valueOf(11));
	}

	@Test
	public void testMultiply() {
		assertEquals("Test multiply correct input", BigInteger.valueOf(5),
				RQ.multiply(BigInteger.valueOf(3), BigInteger.valueOf(9)));
	}

	@Test(expected = ElementNotContainException.class)
	public void testMultiplyIncorrectA() {
		assertEquals("Test multiply incorrect input", BigInteger.valueOf(7),
				RQ.multiply(BigInteger.valueOf(2), BigInteger.valueOf(9)));
	}

	@Test(expected = ElementNotContainException.class)
	public void testMultiplyIncorrectB() {
		assertEquals("Test multiply incorrect input", BigInteger.valueOf(7),
				RQ.multiply(BigInteger.valueOf(3), BigInteger.valueOf(2)));
	}

	@Test
	public void testPow() {
		assertEquals("Test correct pow", BigInteger.valueOf(9), RQ.pow(BigInteger.valueOf(4), BigInteger.valueOf(3)));
	}

	@Test(expected = ElementNotContainException.class)
	public void testPowIncorrectA() {
		assertEquals("Test pow incorrect input", BigInteger.valueOf(4),
				RQ.pow(BigInteger.valueOf(2), BigInteger.valueOf(2)));
	}

	@Test
	public void testInverse() {
		assertEquals(BigInteger.valueOf(4), RQ.inverse(BigInteger.valueOf(3)));
	}

	@Test
	public void testSample() {
		for (int i = 0; i < 10; i++) {
			assertTrue(RQ.contains(RQ.sample()));
		}
	}

	@Test
	public void testContains() {
		assertTrue(RQ.contains(BigInteger.ONE));

		assertTrue(RQ.contains(BigInteger.valueOf(9)));

		assertFalse(RQ.contains(BigInteger.ZERO));

		assertFalse(RQ.contains(BigInteger.valueOf(11)));
	}

	@Test
	public void testOrder() {
		assertEquals(BigInteger.valueOf(5), RQ.order());
	}

	@Test
	public void testRandomGenerator() {
		for (int i = 0; i < 10; i++) {
			assertTrue(RQ.isGenerator((RQ.randomGenerator())));
		}
	}

	@Test
	public void testGetGenerator() {
		assertTrue(RQ.isGenerator(RQ.getGenerator()));

		RQ.setGenerator(BigInteger.valueOf(3));
		assertTrue(RQ.isGenerator(RQ.getGenerator()));
	}

	@Test
	public void testSetGenerator() {
		assertTrue(RQ.setGenerator(BigInteger.valueOf(9)));

		assertFalse(RQ.setGenerator(BigInteger.valueOf(11)));

		assertFalse(RQ.setGenerator(BigInteger.valueOf(1)));
	}

	@Test
	public void testIsGenerator() {
		assertTrue(RQ.isGenerator(BigInteger.valueOf(9)));

		assertFalse(RQ.isGenerator(BigInteger.valueOf(11)));

		assertFalse(RQ.isGenerator(BigInteger.valueOf(1)));
	}

}
