package Task;

import java.math.BigInteger;

public interface MultiplicativeGroup extends Group {
	//Restituisce la moltiplicazione di due elementi del gruppo
    BigInteger multiply(BigInteger el1, BigInteger el2);

    //Restituisce il risultato della moltiplicaione di n volte 
    //dell'elemento el del gruppo con se stesso
    //  el^n = el * el * ... * el (n volte)
    BigInteger pow(BigInteger el, BigInteger n);

}
