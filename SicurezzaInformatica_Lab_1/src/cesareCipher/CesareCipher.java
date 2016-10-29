package cesareCipher;

import classicCipherPackage.InvalidKeyException;

public class CesareCipher implements classicCipherPackage.ClassicCipher {
	private static int LENKEY = 26;
	private String key;

	public CesareCipher(String key) {
		this.key = key;
	}

	@Override
	public void setKey(String key) {
		if (!checkKey(key)) {
			throw new InvalidKeyException();
		}
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String genKey() {
		int newkey = (int) ((Math.random() * (LENKEY-1)) + 1);
		try {
			setKey(Integer.toString(newkey));
		} catch (RuntimeException e) {
			return "Problema con il generatore di chiavi!";
		}
		return key;
	}

	@Override
	public String Enc(String plainText) {
		char plainChar, cipherChar;
		String cipherText = "";
		
		plainText = plainText.toLowerCase();
		
		int k = Integer.parseInt(key);

		for (int i = 0; i < plainText.length(); i++) {
			plainChar = plainText.charAt(i);
			if (plainChar >= 'a' && plainChar <= 'z') {
				cipherChar = (char) ((int) 'a' + (((int) plainChar - (int) 'a') + k) % LENKEY);
			} else {
				cipherChar = plainChar;
			}
			cipherText += cipherChar;
		}
		return cipherText;
	}

	@Override
	public String Dec(String cipherText) {
		String plainText = "";
		char plainChar, cipherChar;

		int k = Integer.parseInt(key);

		for (int i = 0; i < cipherText.length(); i++) {
			cipherChar = cipherText.charAt(i);
			int tmp;
			if (cipherChar >= 'a' && cipherChar <= 'z') {
				tmp = (((int) cipherChar - (int) 'a') - k) % LENKEY;
				if (tmp >= 0) {
					plainChar = (char) ((int) 'a' + tmp);
				} else {
					plainChar = (char) ((int) 'a' + LENKEY + tmp);
				}

			} else {
				plainChar = cipherChar;
			}
			plainText += plainChar;
		}
		return plainText;
	}

	public boolean checkKey(String k) {
		int key;
		try {
			key = Integer.parseInt(k);
			if (key < 1 || key > 25) {
				return false;
			}
		} catch (RuntimeException e) {
			return false;
		}
		return true;
	}
}
