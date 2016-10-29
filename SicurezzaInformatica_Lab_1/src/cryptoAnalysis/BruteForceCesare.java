package cryptoAnalysis;

import java.io.InputStream;
import java.util.Scanner;

import cesareCipher.CesareCipher;

public class BruteForceCesare {
	private String key;
	private static int NUMKEY = 26;

	public BruteForceCesare() {
	}

	public String tryForce(String cipherText) {
		CesareCipher cc = new CesareCipher("0");
		String plainText;
		Scanner s = null;
		
		for (int i = 1; i < NUMKEY; i++) {
			cc.setKey(i + "");
			plainText = cc.Dec(cipherText);
			s = new Scanner(plainText);
			if (isCorrectWord(s.next()))
				if (isCorrectWord(s.next()))
					if (isCorrectWord(s.next())) {
						key = i + "";
						s.close();
						return plainText;
					}
		}
		if(s != null)
			s.close();
		return cipherText;
	}

	public boolean isCorrectWord(String w) {
		Scanner s = null;
		InputStream input = getClass().getResourceAsStream("Dizionario.txt");
		s = new Scanner(input);
		while (s.hasNext()) {
			if (s.next().equals(w)){
				s.close();
				return true;
			}
		}
		s.close();
		return false;
	}

	public String getKey() {
		return key;
	}

}
