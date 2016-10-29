package cipherMonoalphabeticTrasposition;

import java.util.Random;
import classicCipherPackage.*;

public class MonoalphabeticTrasposition implements ClassicCipher {

	private static int LENKEY = 26;
	private String key;

	public MonoalphabeticTrasposition(String key) {
		this.key = key;
	}

	public boolean checkKey(String k) {
		String newKey = "";
		Character x;

		if (k.length() != LENKEY)
			return false;

		for (int i = 0; i < LENKEY; i++) {
			x = k.charAt(i);
			if (newKey.contains(x.toString())) {
				return false;
			}
			newKey += x.toString();
		}
		return true;
	}

	@Override
	public void setKey(String key) {
		if (!checkKey(key))
			throw new InvalidKeyException();
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String genKey() {
		char key[] = new char[LENKEY], tmp;
		String newKey = "";
		int x, y;

		for (int i = 0; i < LENKEY; i++) {
			key[i] = (char) ((int) 'a' + i);
		}
		Random rnd = new Random();
		for (int i = 0; i < LENKEY / 2 + 1; i++) {
			x = rnd.nextInt(LENKEY);
			y = rnd.nextInt(LENKEY);
			tmp = key[y];
			key[y] = key[x];
			key[x] = tmp;
		}
		for (int i = 0; i < LENKEY; i++) {
			newKey += key[i];
		}
		return newKey;
	}

	@Override
	public String Enc(String plainText) {
		String cipherText = "";
		char plainChar;
		int index;

		plainText = plainText.toLowerCase();
		
		for (int i = 0; i < plainText.length(); i++) {
			plainChar = plainText.charAt(i);
			if (plainChar >= 'a' && plainChar <= 'z') {
				index = ((int) plainChar - (int) 'a');
				cipherText += key.charAt(index);
			}else
				cipherText += plainChar;
		}
		return cipherText;
	}

	@Override
	public String Dec(String cipherText) {
		String plainText = "";
		int index;
		char cipherChar;

		for (int i = 0; i < cipherText.length(); i++) {
			cipherChar = cipherText.charAt(i);
			if (cipherChar >= 'a' && cipherChar <= 'z') {
				index = key.indexOf(cipherChar);
				plainText += (char) ((int) 'a' + index);
			} else {
				plainText += cipherChar;
			}

		}
		return plainText;
	}

}
