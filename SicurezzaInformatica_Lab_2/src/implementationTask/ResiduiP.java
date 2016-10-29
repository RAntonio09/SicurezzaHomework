package implementationTask;

import java.math.BigInteger;
import java.util.Random;

import Exception.ElementNotContainException;
import Exception.isNotSafePrimeException;
import Task.MultiplicativeCyclilcGroup;
import Task.Utils;

public class ResiduiP implements MultiplicativeCyclilcGroup {

	private BigInteger p;
	private BigInteger order;
	private BigInteger generator;

	public ResiduiP(int modLength) {
		this.p = Utils.genSafePrime(modLength);
		this.order = p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
	}

	public ResiduiP(BigInteger p) {
		if (!Utils.isSafePrime(p))
			throw new isNotSafePrimeException();
		this.p = p;
		this.order = p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
	}

	@Override
	public BigInteger multiply(BigInteger el1, BigInteger el2) {
		if (!this.contains(el1) || !this.contains(el2))
			throw new ElementNotContainException();

		return el1.multiply(el2).mod(p);
	}

	@Override
	public BigInteger pow(BigInteger el, BigInteger n) {
		if (!contains(el))
			throw new ElementNotContainException();
		return el.modPow(n, p);
	}

	@Override
	public BigInteger inverse(BigInteger a) {
		if (!contains(a))
			throw new ElementNotContainException();
		return a.modInverse(p);
	}

	@Override
	public BigInteger sample() {
		Random rnd = new Random();
		int maxNumBitLength = p.bitLength();
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
		BigInteger exponent = p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
		return (el.modPow(exponent, p).compareTo(BigInteger.ONE) == 0);
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
			g = new BigInteger(this.p.bitLength(), rnd);
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
		return (contains(el) && el.compareTo(BigInteger.ONE) != 0);
	}

}
