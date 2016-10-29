package Task;

import java.math.BigInteger;
import java.util.Random;

public class Utils {

	/**
	 * La costante Constant CERTAINTY I numeri primi sono generati con
	 * probabilità 1-2^(-CERTAINTY)
	 **/
	public static final int CERTAINTY = 50;

	/**
	 * Genera un numero primo
	 *
	 * @param modLength
	 *            la lunghezza in bit del primo
	 * @return il numero primo
	 */
	public static BigInteger genPrime(int modLength) {

		BigInteger p = null;
		boolean ok = false;

		do {

			p = BigInteger.probablePrime(modLength, new Random());
			if (p.isProbablePrime(CERTAINTY))
				ok = true;

		} while (!ok);

		return p;
	}

	/**
	 * Genera un numero safe prime
	 *
	 * @param modLength
	 *            la lunghezza in bit del primo
	 * @return il numero safe prime
	 */
	public static BigInteger genSafePrime(int modLength) {
		BigInteger p = null, q;
		do {
			q = genPrime(modLength);
			p = (q.multiply(BigInteger.valueOf(2))).add(BigInteger.ONE);
		} while (!isSafePrime(p));
		return p;
	}

	/**
	 * Verifica se il valore input è un safe prime.
	 *
	 * @param p
	 *            il numero
	 * @return true, se p è un safe prime
	 */
	public static boolean isSafePrime(BigInteger p) {
		BigInteger q = (p.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));
		return (p.isProbablePrime(CERTAINTY) && (q.isProbablePrime(CERTAINTY) || q.equals(BigInteger.ONE)));
	}

}
