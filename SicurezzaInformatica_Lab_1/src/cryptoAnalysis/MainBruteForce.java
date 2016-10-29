package cryptoAnalysis;

import cesareCipher.CesareCipher;

public class MainBruteForce {

	public static void main(String[] args) {
		BruteForceCesare bfc = new BruteForceCesare();
		CesareCipher cc = new CesareCipher("22");
		String cipherText, plainText, plainTextDec;

		plainText = "dichiarare contare giocare assistere aumentare lanciare scherzare elevare promettere scomparire trarre distruggere";
		cc.setKey(cc.genKey());
		System.out.println("Cripto con chiave " + cc.getKey());
		
		cipherText = cc.Enc(plainText);

		plainTextDec = bfc.tryForce(cipherText);

		System.out.println("Testo decriptato con chiave " + bfc.getKey() + ":\n" + plainTextDec);

	}

}
