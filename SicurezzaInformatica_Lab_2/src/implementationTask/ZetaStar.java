package implementationTask;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.Random;

import Exception.ElementNotContainException;
import Exception.isNotPrimeException;
import Task.MultiplicativeGroup;
import Task.Utils;

public class ZetaStar implements MultiplicativeGroup {

	private BigInteger n;
	private BigInteger p;
	private int k;

	public ZetaStar(int modLength) {
		this.n = Utils.genPrime(modLength);
		this.p = this.n;
	}

	public ZetaStar(BigInteger p) {
		if (!p.isProbablePrime(Utils.CERTAINTY))
			throw new isNotPrimeException();
		this.n = p;
		this.p = this.n;
	}

	public ZetaStar(BigInteger p, int k, boolean two) {
		if (!p.isProbablePrime(Utils.CERTAINTY) || p.compareTo(BigInteger.valueOf(2)) != -1)
			throw new isNotPrimeException();
		if (k < 1)
			throw new InvalidParameterException();
		this.k = k;
		this.p = p;
		if (two) {
			this.n = (p.pow(k)).multiply(BigInteger.valueOf(2));
		} else {
			this.n = p.pow(k);
		}
	}

	@Override
	public BigInteger inverse(BigInteger a) {
		if (!this.contains(a))
			throw new ElementNotContainException();
		return a.modInverse(n);
	}

	@Override
	public BigInteger sample() {
		BigInteger el;
		Random r = new Random();
		do {
			el = new BigInteger(n.bitLength(), r);
		} while (!this.contains(el));
		return el;
	}

	@Override
	public boolean contains(BigInteger el) {
		if (el.compareTo(BigInteger.valueOf(0)) != 1 || el.compareTo(n) != -1)
			return false;
		return el.gcd(n).equals(BigInteger.valueOf(1));
	}

	@Override
	public BigInteger order() {
		// utilizziamo la funzione di eulero f(2*p^k)=f(p^k)= f(p^2)=(p-1)*p
		// vale se k > 1 altrimenti vale f(2p) = p - 1
		if (n.isProbablePrime(Utils.CERTAINTY) || k == 1) {
			// Se n è primo allora n = p se non è primo allora significa che
			// n=2*p e p > 2
			return p.subtract(BigInteger.ONE);
		} else {
			return (p.subtract(BigInteger.ONE)).multiply(p);
		}
	}

	@Override
	public BigInteger multiply(BigInteger el1, BigInteger el2) {
		if (!this.contains(el1) || !this.contains(el2)) {
			throw new ElementNotContainException();
		}
		return el1.multiply(el2).mod(n);
	}

	@Override
	public BigInteger pow(BigInteger el, BigInteger n) {
		if (!this.contains(el))
			throw new ElementNotContainException();
		if (n.compareTo(BigInteger.ZERO) == -1)
			throw new InvalidParameterException();

		return el.modPow(n, this.n);
	}

}
