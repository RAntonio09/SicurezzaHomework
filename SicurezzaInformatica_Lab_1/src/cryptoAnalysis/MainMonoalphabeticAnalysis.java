package cryptoAnalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cipherMonoalphabeticTrasposition.MonoalphabeticTrasposition;

public class MainMonoalphabeticAnalysis {

	public static void main(String[] args) {
		String cipherText = "", key;
		MonoalphabeticAnalysis ma = new MonoalphabeticAnalysis();
		MonoalphabeticTrasposition cc2 = new MonoalphabeticTrasposition("");
		List<String> keys = new LinkedList<>();
		Scanner in = new Scanner(System.in);

		try {
			Scanner s = new Scanner(new FileInputStream(new File("Codificato.txt")));
			while (s.hasNext()) {
				cipherText += s.next() + " ";
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File non trovato!");
		}

		keys = ma.permutatedMonocharacterKeys(cipherText);
		keys.addAll(ma.permutatedDigramKeys(cipherText));

		boolean choice = false;
		Character cipherChar, plainChar;
		int index = 0, keyChoosen;
		for (String k : keys) {
			cc2.setKey(k);
			System.out.println(index + ". Testo decifrato:" + cc2.Dec(cipherText).substring(0, 100));
			System.out.println("\t\t<------------------------------------------------->");
			index++;
		}
		System.out.println("Quale chiave pensi sia più corretta? (0-" + keys.size() + ")");
		keyChoosen = in.nextInt();
		key = keys.get(keyChoosen);
		do {
			cc2.setKey(key);
			System.out.println("Key: " + key);
			System.out.println("Testo Decriptato: " + cc2.Dec(cipherText));
			System.out.println("Vuoi raffinare la chiave? (Y/N)");
			choice = in.next().equals("y");
			if (choice) {
				System.out.println("Testo cifrato:    " + cipherText + "\nTesto decriptato: " + cc2.Dec(cipherText));
				System.out.println("Che carattere vuoi sostituire?\nInserisci carattere cifrato: ");
				cipherChar = in.next().charAt(0);
				System.out.println("Inserisci carattere in chiaro:");
				plainChar = in.next().charAt(0);
				key = ma.changeKey(key, cipherChar, plainChar);
			}
		} while (choice);
		System.out.println("La chiave finale è :\n" + key);
		System.out.println("Il testo decodificato è:\n" + cc2.Dec(cipherText));
		in.close();
	}

}
