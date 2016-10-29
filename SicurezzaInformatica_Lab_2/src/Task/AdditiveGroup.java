package Task;

import java.math.BigInteger;

public interface AdditiveGroup extends Group {
	// Restituisce l'addizione di due elementi del gruppo
	BigInteger add(BigInteger el1, BigInteger el2);

	// Restituisce il risultato della somma di n volte
	// dell'elemento el del gruppo con se stesso
	// n * el = el+el+ ... + el (n volte)
	BigInteger multiply(BigInteger el, BigInteger n);
}
