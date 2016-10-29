package Test;

import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import org.junit.Test;

import Task.Utils;

public class UtilsTest {

	@Test
	public void testIsSafePrime() {
		assertEquals("7", true, Utils.isSafePrime(BigInteger.valueOf(7)));

		assertEquals("3701", false, Utils.isSafePrime(BigInteger.valueOf(3701)));
		
		assertEquals("3107", false, Utils.isSafePrime(BigInteger.valueOf(3107)));
		
		assertEquals("1823", true, Utils.isSafePrime(BigInteger.valueOf(1823)));
	}

	@Test
	public void testGenSafePrime() {
		assertEquals(true, Utils.isSafePrime(Utils.genSafePrime(7)));

		assertEquals(true, Utils.isSafePrime(Utils.genSafePrime(12)));
		
		assertEquals(true, Utils.isSafePrime(Utils.genSafePrime(20)));

	}

	@Test
	public void testGenPrime() {
		assertEquals(true,  Utils.genPrime(7).isProbablePrime(Utils.CERTAINTY));

		assertEquals(true,  Utils.genPrime(12).isProbablePrime(Utils.CERTAINTY));

		assertEquals(true,  Utils.genPrime(20).isProbablePrime(Utils.CERTAINTY));
	}

}
