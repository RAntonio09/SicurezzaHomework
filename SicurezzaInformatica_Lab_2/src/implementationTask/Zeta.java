package implementationTask;

import java.math.BigInteger;
import java.util.Random;

import Exception.ElementNotContainException;
import Task.AdditiveCyclicGroup;
import Task.Utils;

public class Zeta implements AdditiveCyclicGroup {
	private BigInteger order;
	private BigInteger generator;

	public Zeta(BigInteger num) {
		order = num;

	}

	public Zeta(int modLength) {
		order = Utils.genPrime(modLength);

	}

	@Override
	public BigInteger add(BigInteger el1, BigInteger el2) {
		if (!contains(el1) || !contains(el2))
			throw new ElementNotContainException();
		return el1.add(el2).remainder(order);
	}

	@Override
	public BigInteger multiply(BigInteger el, BigInteger n) {
		if (!contains(el))
			throw new ElementNotContainException();
		BigInteger tmp = el.add(BigInteger.ZERO);
		for (BigInteger i = BigInteger.ONE; i.compareTo(n) == -1; i = i.add(BigInteger.valueOf(1))) {
			tmp = tmp.add(el);
		}
		return tmp.remainder(order);
	}

	@Override
	public BigInteger inverse(BigInteger a) {
		if (!contains(a))
			throw new ElementNotContainException();
		return order.subtract(a);
	}

	@Override
	public BigInteger sample() {
		Random rnd = new Random();
		int maxNumBitLength = order.bitLength();
		BigInteger aRandomBigInt;
		do {
			aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
		} while (aRandomBigInt.compareTo(order) >= 0); // continua fin quando
														// non generi un numer
														// <=ordine
		return aRandomBigInt;
	}

	@Override
	public boolean contains(BigInteger el) {
		BigInteger zero = BigInteger.valueOf(0);
		if ((el.compareTo(order) == -1) && (el.compareTo(zero) != -1))// el>=0,
																		// el<ordine
			return true;
		return false;
	}

	@Override
	public BigInteger order() {
		return this.order;
	}

	@Override
	public BigInteger randomGenerator() {
		Random rnd = new Random();
		int maxNumBitLength = order.bitLength();
		BigInteger aRandomBigInt;
		while (true) { // continua fin quando non generi un numero generatore
			aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
			if (aRandomBigInt.compareTo(order) < 0)
				if (this.isGenerator(aRandomBigInt))
					break;
		}
		return aRandomBigInt;
	}

	@Override
	public BigInteger getGenerator() {
		if (generator == null)
			this.setGenerator(this.randomGenerator());
		return this.generator;

	}

	@Override
	public boolean setGenerator(BigInteger g) {
		if (!contains(g))
			throw new ElementNotContainException();
		if (this.isGenerator(g)) {
			this.generator = g;
			return true;
		}
		return false;
	}

	@Override
	public boolean isGenerator(BigInteger el) {
		if (!contains(el))
			throw new ElementNotContainException();
		return (el.gcd(order).compareTo(BigInteger.ONE) == 0); // MCD
	}

}
