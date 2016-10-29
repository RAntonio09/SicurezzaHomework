package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import Exception.ElementNotContainException;
import implementationTask.ResiduiN;

public class ResiduiNTest {
	private ResiduiN RQN;

	@Before
	public void init() {
		RQN = new ResiduiN(BigInteger.valueOf(3), BigInteger.valueOf(7));
	}

	@Test
	public void testMultiply() {
		assertEquals("Test multiply correct input", BigInteger.valueOf(4),
				RQN.multiply(BigInteger.valueOf(4), BigInteger.valueOf(1)));
	}

	@Test(expected = ElementNotContainException.class)
	public void testMultiplyIncorrectA() {
		assertEquals("Test multiply incorrect input", BigInteger.valueOf(3),
				RQN.multiply(BigInteger.valueOf(3), BigInteger.valueOf(9)));
	}

	@Test(expected = ElementNotContainException.class)
	public void testMultiplyIncorrectB() {
		assertEquals("Test multiply incorrect input", BigInteger.valueOf(7),
				RQN.multiply(BigInteger.valueOf(3), BigInteger.valueOf(6)));
	}

	@Test
	public void testPow() {
		assertEquals("Test correct pow", BigInteger.valueOf(1), RQN.pow(BigInteger.valueOf(4), BigInteger.valueOf(3)));
	}

	@Test(expected = ElementNotContainException.class)
	public void testPowIncorrectA() {
		assertEquals("Test pow incorrect input", BigInteger.valueOf(4),
				RQN.pow(BigInteger.valueOf(7), BigInteger.valueOf(2)));
	}

	@Test
	public void testInverse() {
		assertEquals(BigInteger.valueOf(16), RQN.inverse(BigInteger.valueOf(4)));
	}

	@Test
	public void testSample() {
		for (int i = 0; i < 10; i++) {
			assertTrue(RQN.contains(RQN.sample()));
		}
	}

	@Test
	public void testContains() {
		assertTrue(RQN.contains(BigInteger.ONE));
		// assertTrue(RQN.contains(BigInteger.valueOf(2)));
		assertTrue(RQN.contains(BigInteger.valueOf(4)));
		// assertTrue(RQN.contains(BigInteger.valueOf(5)));
		// assertTrue(RQN.contains(BigInteger.valueOf(8)));
		// assertTrue(RQN.contains(BigInteger.valueOf(10)));
		// assertTrue(RQN.contains(BigInteger.valueOf(11)));
		// assertTrue(RQN.contains(BigInteger.valueOf(13)));
		assertTrue(RQN.contains(BigInteger.valueOf(16)));
		// assertTrue(RQN.contains(BigInteger.valueOf(17)));
		// assertTrue(RQN.contains(BigInteger.valueOf(19)));
		// assertTrue(RQN.contains(BigInteger.valueOf(20)));

		assertFalse(RQN.contains(BigInteger.ZERO));
		assertFalse(RQN.contains(BigInteger.valueOf(3)));
		assertFalse(RQN.contains(BigInteger.valueOf(6)));
		assertFalse(RQN.contains(BigInteger.valueOf(7)));
		assertFalse(RQN.contains(BigInteger.valueOf(9)));
		assertFalse(RQN.contains(BigInteger.valueOf(12)));
		assertFalse(RQN.contains(BigInteger.valueOf(15)));
		assertFalse(RQN.contains(BigInteger.valueOf(18)));
		assertFalse(RQN.contains(BigInteger.valueOf(21)));

		assertFalse(RQN.contains(BigInteger.valueOf(2)));
		assertFalse(RQN.contains(BigInteger.valueOf(5)));
		assertFalse(RQN.contains(BigInteger.valueOf(8)));
		assertFalse(RQN.contains(BigInteger.valueOf(10)));
		assertFalse(RQN.contains(BigInteger.valueOf(11)));
		assertFalse(RQN.contains(BigInteger.valueOf(13)));

	}

	@Test
	public void testOrder() {
		assertEquals(BigInteger.valueOf(3), RQN.order());
	}

	@Test
	public void testRandomGenerator() {
		for (int i = 0; i < 10; i++) {
			BigInteger gen = RQN.randomGenerator();
			System.out.println(gen);
			assertTrue(RQN.isGenerator(gen));
		}
	}

	@Test
	public void testGetGenerator() {
		assertTrue(RQN.isGenerator(RQN.getGenerator()));

		RQN.setGenerator(BigInteger.valueOf(4));
		assertTrue(RQN.isGenerator(RQN.getGenerator()));
	}

	@Test
	public void testSetGenerator() {
		assertTrue(RQN.setGenerator(BigInteger.valueOf(4)));
		assertTrue(RQN.setGenerator(BigInteger.valueOf(16)));

		assertFalse(RQN.setGenerator(BigInteger.valueOf(13)));
		assertFalse(RQN.setGenerator(BigInteger.valueOf(20)));
		assertFalse(RQN.setGenerator(BigInteger.valueOf(1)));
	}

	@Test
	public void testIsGenerator() {
		assertTrue(RQN.isGenerator(BigInteger.valueOf(4)));
		assertTrue(RQN.isGenerator(BigInteger.valueOf(16)));

		assertFalse(RQN.isGenerator(BigInteger.valueOf(13)));

		assertFalse(RQN.isGenerator(BigInteger.valueOf(1)));
	}

}
