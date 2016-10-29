package Task;


import java.math.BigInteger;

/*
  Al posto di BigInteger dovremmo avere una classe Element che
  rappresenta l'elemento generico del gruppo. Per il progetto è
  sufficiente rappresentare l'elemento generico con BigInteger.
*/

public interface Group {
	// Restituisce il valore inverso di a
	// -a per gruppi additivi
	// a^-1 per gruppi moltiplicativi modInverse
	BigInteger inverse(BigInteger a);

	// Restituisce un elemento casuale del gruppo
	BigInteger sample();

	// Restituisce true se il gruppo contiene el
	boolean contains(BigInteger el);

	// Restituisce l'ordine del gruppo
	BigInteger order();

}
