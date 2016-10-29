package cesareCipher;

import static org.junit.Assert.*;
import org.junit.*;

import classicCipherPackage.InvalidKeyException;

public class CesareCipherTest {

	private CesareCipher cc;
	private String plainText, cipherText;

	@Before
	public void InizializeTest() {
		cc = new CesareCipher("1");
	}

	@Test
	public void checkKeyTest() {
		assertEquals("Test key stringa valida : 16", true, cc.checkKey("16"));
		assertEquals("Test key stringa non valida : 0", false, cc.checkKey("0"));
		assertEquals("Test key stringa non valida : 26", false, cc.checkKey("26"));
		assertEquals("Test key stringa non valida : asd", false, cc.checkKey("asd"));
		assertEquals("Test key stringa non valida :  ", false, cc.checkKey(" "));
	}

	@Test
	public void setKeyTest() {
		boolean x = true;
		try {
			cc.setKey("0");
		} catch (InvalidKeyException e) {
			x = false;
		}
		assertEquals("Test setKey chiave errata", false, x);

		x = true;
		try {
			cc.setKey("cac");
		} catch (InvalidKeyException e) {
			x = false;
		}
		assertEquals("Test setKey chiave errata", false, x);

		cc.setKey("5");
		assertEquals("Test setKey chiave giusta", true, cc.getKey().equals("5"));
	}

	@Test
	public void EncTest(){
		plainText = "abcDefg";
		cipherText = cc.Enc(plainText);
		assertEquals("Testo criptato con chiave 1",cipherText , "bcdefgh");
		
		plainText = "pranzo dacqua fa volti sghembi";
		cipherText = cc.Enc(plainText);
		assertEquals("qsboap ebdrvb gb wpmuj thifncj", cipherText);
	}
	
	@Test
	public void DecTest(){
		cipherText = "qsboap e'bdrvb gb wpmuj thifncj";
		plainText = cc.Dec(cipherText);
		assertEquals("Test di encrypt cond normali", "pranzo d'acqua fa volti sghembi", plainText);
		
		String decText;
		plainText = "prova di inversione";
		cipherText = cc.Enc(plainText);
		decText = cc.Dec(cipherText);
		assertEquals("Test di inversione text = Dec(Enc(text))",plainText, decText);
		
		cc.setKey("3");
		decText = cc.Dec(cipherText);
		assertNotEquals("Testo che con chiave sbagliata non funziona", plainText, decText);
	}

	@Test
	public void genKeyTest(){
		String k;
		for(int i = 1; i <= 100; i++){
			k = cc.genKey();
			assertEquals("Test chiave casuale test("+ i + "/100)", true, cc.checkKey(k));	
		}
		
	}
	
}
