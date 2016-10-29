package cesareCipher;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		CesareCipher cc = new CesareCipher("1");
		String k, cipherText, plainText, sc, decText;
		boolean x = true;

		while (x) {
			System.out.println("1. Cambia chiave. Attuale(" + cc.getKey() + ")");
			System.out.println("2. Cripta un messaggio");
			System.out.println("3. Decripta un messaggio");
			System.out.println("4. Genera chiave casualmente");
			System.out.println("X. Esci");
			System.out.println("D. Demo");
			sc = s.next();

			switch (sc) {
			case "1":
				System.out.println("Inserisci chiave: ");
				k = s.next();
				if (cc.checkKey(k)) {
					cc.setKey(k);
					System.out.println("Chiave cambiata");
				} else
					System.out.println("Chiave errata");
				break;
			case "2":
				System.out.println("Inserisci messaggio:");
				plainText = s.next();
				cipherText = cc.Enc(plainText);
				System.out.println("Messaggio criptato: " + cipherText);
				break;
			case "3":
				System.out.println("Inserisci messaggio:");
				cipherText = s.next();
				plainText = cc.Dec(cipherText);
				System.out.println("Messaggio decriptato: " + plainText);
				break;
			case "4":
				cc.setKey(cc.genKey());
				System.out.println("Chiave generata: " + cc.getKey());
				break;
			case "D":
				plainText = "PrAnzO d'acqua fa volti sghembi.";
				k = "5";
				cc.setKey(k);
				cipherText = cc.Enc(plainText);
				decText = cc.Dec(cipherText);
				System.out.println("Messaggio:" + plainText);
				System.out.println("Messaggio criptato con chiave(" + k + "): " + cipherText);
				System.out.println("Messaggio decriptato: " + decText);
				break;
			default:
				System.out.println("Finito!");
				x = false;
				break;
			}
		}
		s.close();
	}

}
