package cryptoAnalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMonoalphabeticAnalysisEx4 {

	public static void main(String[] args) {
		String cipherText = "", key1, key2, key3, key4;
		MonoalphabeticAnalysis ma = new MonoalphabeticAnalysis();

		try {
			Scanner s = new Scanner(new FileInputStream(new File("Codificato.txt")));
			while (s.hasNext()) {
				cipherText += s.next() + " ";
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File non trovato!");
		}
		key1 = ma.frequenciesAnalysisMonocharacter(cipherText);
		System.out.println("Analisi singolo carattere\t\t\t\t\t Key: " + key1);
		key2 = ma.frequenciesAnalysisDigram(cipherText);
		System.out.println("Analisi digramma \t\t\t\t\t\t Key: " + key2);
		ma.setPartOfKey('z', 'a');
		ma.setPartOfKey('a', 'z');
		ma.setPartOfKey('m', 'n');
		ma.setPartOfKey('i', 'r');
		ma.setPartOfKey('j', 'q');
		key3 = ma.frequenciesAnalysisMonocharacter(cipherText);
		System.out.println("Analisi singolo carattere dopo aver imposto parte della chiave \t Key: " + key3);
		key4 = ma.frequenciesAnalysisDigram(cipherText);
		System.out.println("Analisi digramma dopo aver imposto parte della chiave \t\t Key: " + key4);
	}

}
