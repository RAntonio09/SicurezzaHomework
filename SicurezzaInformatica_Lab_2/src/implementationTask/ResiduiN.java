package implementationTask;

import java.math.BigInteger;
import java.util.Random;

import Exception.ElementNotContainException;
import Exception.isNotSafePrimeException;
import Task.MultiplicativeCyclilcGroup;
import Task.Utils;

public class ResiduiN implements MultiplicativeCyclilcGroup {

	private BigInteger n;
	private BigInteger q;
	private BigInteger p;
	private BigInteger order;
	private BigInteger generator;

	public ResiduiN(BigInteger p, BigInteger q) {
		if (!Utils.isSafePrime(p) || !Utils.isSafePrime(q))
			throw new isNotSafePrimeException();
		this.p = p;
		this.q = q;
		this.n = p.multiply(q);
		BigInteger orderP = p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
		BigInteger orderQ = q.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
		this.order = orderQ.multiply(orderP);
	}

	public BigInteger multiply(BigInteger el1, BigInteger el2) {
		if (!this.contains(el1) || !this.contains(el2))
			throw new ElementNotContainException();

		return el1.multiply(el2).mod(n);
	}

	@Override
	public BigInteger pow(BigInteger el, BigInteger n) {
		if (!contains(el))
			throw new ElementNotContainException();
		return el.modPow(n, this.n);
	}

	@Override
	public BigInteger inverse(BigInteger a) {
		if (!contains(a))
			throw new ElementNotContainException();
		return a.modInverse(n);
	}

	@Override
	public BigInteger sample() {
		Random rnd = new Random();
		int maxNumBitLength = n.bitLength();
		BigInteger aRandomBigInt;
		do {
			aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
		} while (!contains(aRandomBigInt));
		return aRandomBigInt;
	}

	@Override
	public boolean contains(BigInteger el) {
		if (el.compareTo(BigInteger.ZERO) != 1)
			return false;
		BigInteger exponentP = (p.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));
		BigInteger exponentQ = (q.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));

		boolean modP = (el.modPow(exponentP, p).compareTo(BigInteger.ONE) == 0);
		boolean modQ = (el.modPow(exponentQ, q).compareTo(BigInteger.ONE) == 0);
		return (modP && modQ);
		// r^((p-1)/2)= 1 (mod p)
	}

	@Override
	public BigInteger order() {
		return order;
	}

	@Override
	public BigInteger randomGenerator() {
		BigInteger g;
		Random rnd = new Random();
		do {
			g = new BigInteger(this.n.bitLength(), rnd);
		} while (!isGenerator(g));
		return g;
	}

	@Override
	public BigInteger getGenerator() {
		if (this.generator == null) {
			this.generator = randomGenerator();
		}
		return this.generator;
	}

	@Override
	public boolean setGenerator(BigInteger g) {
		if (isGenerator(g)) {
			generator = g;
			return true;
		}
		return false;
	}

	@Override
	public boolean isGenerator(BigInteger el) {
		if (el.compareTo(BigInteger.ONE) != 1 || !contains(el))
			return false;
		return true;
	}

}
