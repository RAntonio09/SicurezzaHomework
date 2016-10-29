package Task;

import java.math.BigInteger;

public interface AdditiveCyclicGroup extends AdditiveGroup {
	// Restituisce un generatore scelto a caso del gruppo
	BigInteger randomGenerator();

	// Restituisce il generatore del gruppo
	BigInteger getGenerator();

	// Assegna g come generatore del gruppo se g può essere un generatore
	// Restituisce true se g è un generatore, false altrimenti
	boolean setGenerator(BigInteger g);

	// Restituisce true se l'elemento è un generatore del gruppo
	boolean isGenerator(BigInteger el);
}
