package cipherMonoalphabeticTrasposition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import classicCipherPackage.InvalidKeyException;

public class MonoalphabeticTraspositionTest {

	private MonoalphabeticTrasposition mt;
	private String plainText, cipherText;

	@Before
	public void InizializeTest() {
		mt = new MonoalphabeticTrasposition("qwertyuiopasdfghjklzxcvbnm");
	}

	@Test
	public void checkKeyTest() {
		assertEquals("Test key stringa valida : 'zxcvbnmasdfghjklqwertyuio'", true,
				mt.checkKey("zxcvbnmasdfghjklqwertyuiop"));
		assertEquals("Test key stringa non valida : 'ggvg'", false, mt.checkKey("ggvg"));
		assertEquals("Test key stringa non valida : 'zzcvbnmasdfghjklqwertyuio'", false,
				mt.checkKey("zzxcvbnmasdfghjklqwertyuio"));
		assertEquals("Test key stringa non valida : ' '", false, mt.checkKey(" "));
	}

	@Test
	public void setKeyTest() {
		boolean x = true;
		try {
			mt.setKey("qwertyuiopasdfghjklzxcvbmm");
		} catch (InvalidKeyException e) {
			x = false;
		}
		assertEquals("Test setKey chiave errata", false, x);

		x = true;
		try {
			mt.setKey("cac");
		} catch (InvalidKeyException e) {
			x = false;
		}
		assertEquals("Test setKey chiave errata", false, x);

		mt.setKey("qwertyuiopzxcvbnmasdfghjkl");
		assertEquals("Test setKey chiave giusta", true, mt.getKey().equals("qwertyuiopzxcvbnmasdfghjkl"));
	}

	@Test
	public void EncTest() {
		plainText = "abcDefg";
		cipherText = mt.Enc(plainText);
		assertEquals("Testo criptato con chiave 'qwertyuiopasdfghjklzxcvbnm'", cipherText, "qwertyu");

		plainText = "PrAnzO d'acqua fa volti sghembi";
		cipherText = mt.Enc(plainText);
		assertEquals(cipherText, "hkqfmg r'qejxq yq cgszo luitdwo");
	}

	@Test
	public void DecTest() {
		cipherText = "hkqfmg r'qejxq yq cgszo luitdwo";
		plainText = mt.Dec(cipherText);
		assertEquals("Test di encrypt cond normali", "pranzo d'acqua fa volti sghembi", plainText);

		String decText;
		plainText = "prova di inversione";
		cipherText = mt.Enc(plainText);
		decText = mt.Dec(cipherText);
		assertEquals("Test di inversione text = Dec(Enc(text))", plainText, decText);

		mt.setKey("zxcvbnmasdfghjklqwertyuiop");
		decText = mt.Dec(cipherText);
		assertNotEquals("Testo che con chiave sbagliata non funziona", plainText, decText);
	}

	@Test
	public void genKeyTest() {
		String k;
		for (int i = 1; i <= 100; i++) {
			k = mt.genKey();
			assertEquals("Test chiave casuale test(" + i + "/100)", true, mt.checkKey(k));
		}

	}

}
