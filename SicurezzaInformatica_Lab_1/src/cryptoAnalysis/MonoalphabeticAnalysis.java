package cryptoAnalysis;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class MonoalphabeticAnalysis {
	final private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	// chiave parziale data dall'utente
	private Map<Character, Character> partialKey;

	// Strutture per l'analisi delle frequenze dei singoli caratteri
	private Map<String, Integer> mapFrequencyCipherText;
	private List<StringOccurence> frequencyCipherTextList;
	private List<StringOccurence> frequencyPlainTextList;

	// Strutture per l'analisi delle frequenze dei digrammi
	private Map<String, Integer> frequencyDigramPlainText;
	private List<StringOccurence> frequencyDigramPlainTextList;
	private Map<String, Integer> frequencyDigramCipherText;
	private List<StringOccurence> frequencyDigramCipherTextList;

	private Map<Character, Character> decrypiontMap;
	private Map<Character, Character> encryptionMap;

	public MonoalphabeticAnalysis() {
		decrypiontMap = new HashMap<Character, Character>();
		encryptionMap = new HashMap<Character, Character>();
		partialKey = new TreeMap<Character, Character>();
		genFrequencyLetter("IT");

	}

	private void genFrequencyLetter(String l) {
		switch (l) {
		case "IT":
			frequencyPlainTextList = new LinkedList<StringOccurence>();
			frequencyPlainTextList.add(new StringOccurence("a", (float) 11.74));
			frequencyPlainTextList.add(new StringOccurence("b", (float) 0.92));
			frequencyPlainTextList.add(new StringOccurence("c", (float) 4.50));
			frequencyPlainTextList.add(new StringOccurence("d", (float) 3.73));
			frequencyPlainTextList.add(new StringOccurence("e", (float) 11.79));
			frequencyPlainTextList.add(new StringOccurence("f", (float) 0.95));
			frequencyPlainTextList.add(new StringOccurence("g", (float) 1.64));
			frequencyPlainTextList.add(new StringOccurence("h", (float) 1.54));
			frequencyPlainTextList.add(new StringOccurence("i", (float) 11.28));
			frequencyPlainTextList.add(new StringOccurence("j", (float) 0));
			frequencyPlainTextList.add(new StringOccurence("k", (float) 0));
			frequencyPlainTextList.add(new StringOccurence("l", (float) 6.51));
			frequencyPlainTextList.add(new StringOccurence("m", (float) 2.52));
			frequencyPlainTextList.add(new StringOccurence("n", (float) 6.88));
			frequencyPlainTextList.add(new StringOccurence("o", (float) 9.83));
			frequencyPlainTextList.add(new StringOccurence("p", (float) 3.05));
			frequencyPlainTextList.add(new StringOccurence("q", (float) 0.51));
			frequencyPlainTextList.add(new StringOccurence("r", (float) 6.37));
			frequencyPlainTextList.add(new StringOccurence("s", (float) 4.98));
			frequencyPlainTextList.add(new StringOccurence("t", (float) 5.62));
			frequencyPlainTextList.add(new StringOccurence("u", (float) 3.01));
			frequencyPlainTextList.add(new StringOccurence("v", (float) 2.10));
			frequencyPlainTextList.add(new StringOccurence("w", (float) 0));
			frequencyPlainTextList.add(new StringOccurence("x", (float) 0));
			frequencyPlainTextList.add(new StringOccurence("y", (float) 0));
			frequencyPlainTextList.add(new StringOccurence("z", (float) 0.49));
			Collections.sort(frequencyPlainTextList);
			break;
		case "FR":
			frequencyPlainTextList = null;
			break;
		}
	}

	private void clearMaps() {
		encryptionMap.clear();
		decrypiontMap.clear();
		encryptionMap.put(' ', ' ');
		decrypiontMap.put(' ', ' ');
	}

	public String frequenciesAnalysisMonocharacter(String cipherText) {
		clearMaps();
		genFrequencyLetter("IT");
		countOccurenceMonocharacter(cipherText);
		return generateMonocharacterKey();
	}

	private void countOccurenceMonocharacter(String cipherText) {
		mapFrequencyCipherText = new HashMap<String, Integer>();
		char c;
		// Inizializzo la mappa con le frequenze 0
		for (int i = 0; i < alphabet.length(); i++) {
			mapFrequencyCipherText.put(alphabet.charAt(i) + "", 0);
		}
		// Contiamo le occorrenze dei caratteri nel testo cifrato.
		for (int i = 0; i < cipherText.length(); i++) {
			c = cipherText.charAt(i);
			if (alphabet.contains(c + ""))
				mapFrequencyCipherText.put(c + "", mapFrequencyCipherText.get(c + "") + 1);

		}
		// ordino per occorrenze
		frequencyCipherTextList = new LinkedList<StringOccurence>();
		for (String ch : mapFrequencyCipherText.keySet()) {
			if (alphabet.contains(ch))
				frequencyCipherTextList.add(new StringOccurence(ch + "", new Float(mapFrequencyCipherText.get(ch))));
		}
		Collections.sort(frequencyCipherTextList);
	}

	private String generateMonocharacterKey() {
		removeElementOfPartialKey();

		for (int i = 0; i < frequencyPlainTextList.size(); i++) {
			decrypiontMap.put(frequencyCipherTextList.get(i).str.charAt(0),
					frequencyPlainTextList.get(i).str.charAt(0));
			// Essendo questa stringa composta da singoli caratteri posso
			// considerare solo charAt(0)
		}
		String tmp = "";
		for (int i = 0; i < alphabet.length(); i++) {
			tmp += decrypiontMap.get(alphabet.charAt(i));
		}
		return tmp;
	}

	private void removeElementOfPartialKey() {
		Set<Character> keySet = partialKey.keySet();
		Character value;
		for (Character k : keySet) {
			value = partialKey.get(k);
			// Tolgo dal vettore delle frequenze del testo in chiaro l'elemento
			// nella chiave parziale
			if (frequencyPlainTextList != null
					&& frequencyPlainTextList.contains(new StringOccurence(value + "", (float) 0)))
				frequencyPlainTextList.remove(new StringOccurence(value + "", (float) 0));
			// Tolgo il corrispondente carattere dal vettore del testo cifrato
			if (frequencyCipherTextList != null
					&& frequencyCipherTextList.contains(new StringOccurence(k + "", (float) 0)))
				frequencyCipherTextList.remove(new StringOccurence(k + "", (float) 0));
			decrypiontMap.put(k, value);
			encryptionMap.put(value, k);

		}
	}

	public void setPartOfKey(char cipherChar, char plainChar) {
		partialKey.put(cipherChar, plainChar);
	}

	public String frequenciesAnalysisDigram(String cipherText) {
		clearMaps();
		countOccurenceDigramPlainText();
		countOccurenceDigramCipherText(cipherText);
		return generateDigramKey();
	}

	private void countOccurenceDigramCipherText(String cipherText) {
		String tmp = "";
		char ch, ch2;

		frequencyDigramCipherText = new HashMap<String, Integer>();
		// Inizializzo la mappa con le frequenze 0
		for (int i = 0; i < alphabet.length(); i++) {
			for (int j = 0; j < alphabet.length(); j++) {
				frequencyDigramCipherText.put("" + alphabet.charAt(i) + alphabet.charAt(j), 0);
			}
		}
		for (int i = 0; i < cipherText.length(); i++) {
			ch = cipherText.charAt(i);
			// Se nn è un carattere ammesso sovrascrivo con spazio
			if (!alphabet.contains(ch + "")) {
				ch = ' ';
			}

			if (i + 1 < cipherText.length()) {
				ch2 = cipherText.charAt(i + 1);
				if (!alphabet.contains(ch2 + "")) {
					ch2 = ' ';
				}
				tmp = "" + ch + ch2;
				i++;
			} else {
				tmp = ch + " ";
				// se il testo non finisce con un digramma aggiungo lo spazio
			}
			if (frequencyDigramCipherText.containsKey(tmp)) {
				frequencyDigramCipherText.put(tmp, frequencyDigramCipherText.get(tmp) + 1);
			}
		}
		frequencyDigramCipherText.remove("  "); // rimuovo i doppi spazi dalla
												// mappa

		frequencyDigramCipherTextList = new LinkedList<StringOccurence>();
		for (String k : frequencyDigramCipherText.keySet()) {
			frequencyDigramCipherTextList.add(new StringOccurence(k, (float) frequencyDigramCipherText.get(k)));
		}
		Collections.sort(frequencyDigramCipherTextList);
	}

	private void countOccurenceDigramPlainText() {
		String alphabetExtended = " " + this.alphabet;
		frequencyDigramPlainText = new HashMap<>();

		Scanner sc = new Scanner(getClass().getResourceAsStream("frequenciesDigramTable.txt"));
		for (int i = 0; i < alphabetExtended.length(); i++) {
			for (int j = 0; j < alphabet.length() + 1; j++) {
				frequencyDigramPlainText.put("" + alphabetExtended.charAt(i) + alphabetExtended.charAt(j),
						sc.nextInt());
			}
		}
		sc.close();

		frequencyDigramPlainTextList = new LinkedList<StringOccurence>();
		for (String k : frequencyDigramPlainText.keySet()) {
			frequencyDigramPlainTextList.add(new StringOccurence(k, (float) frequencyDigramPlainText.get(k)));
		}
		Collections.sort(frequencyDigramPlainTextList);
	}

	private String generateDigramKey() {
		removeElementOfPartialKey();
		int index;
		for (StringOccurence so : frequencyDigramCipherTextList) {
			index = 0;
			while (index < frequencyDigramPlainTextList.size()
					&& !isValidAssociation(so, frequencyDigramPlainTextList.get(index))) {
				index++;
			}
			if (index < frequencyDigramPlainTextList.size()) {
				if (!decrypiontMap.containsKey(so.str.charAt(0))
						&& alphabet.contains("" + frequencyDigramPlainTextList.get(index).str.charAt(0))) {
					decrypiontMap.put(so.str.charAt(0), frequencyDigramPlainTextList.get(index).str.charAt(0));
					encryptionMap.put(frequencyDigramPlainTextList.get(index).str.charAt(0), so.str.charAt(0));
				}
				if (!decrypiontMap.containsKey(so.str.charAt(1))
						&& alphabet.contains("" + frequencyDigramPlainTextList.get(index).str.charAt(1))) {
					decrypiontMap.put(so.str.charAt(1), frequencyDigramPlainTextList.get(index).str.charAt(1));
					encryptionMap.put(frequencyDigramPlainTextList.get(index).str.charAt(1), so.str.charAt(1));
				}
			}
			decrypiontMap.remove(' ');
		}
		String tmp = "";
		for (int i = 0; i < alphabet.length(); i++) {
			tmp += decrypiontMap.get(alphabet.charAt(i));
		}
		return tmp;
	}

	private boolean isValidAssociation(StringOccurence stringOccurence1, StringOccurence stringOccurence2) {
		if (decrypiontMap.containsKey(stringOccurence1.str.charAt(0))) {
			if (decrypiontMap.get(stringOccurence1.str.charAt(0)) == stringOccurence2.str.charAt(0)) {
				if (!decrypiontMap.containsKey(stringOccurence1.str.charAt(1))
						&& !encryptionMap.containsKey(stringOccurence2.str.charAt(1))) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			if (decrypiontMap.containsKey(stringOccurence1.str.charAt(1))) {
				if (decrypiontMap.get(stringOccurence1.str.charAt(1)) == stringOccurence2.str.charAt(1)
						&& !encryptionMap.containsKey(stringOccurence2.str.charAt(0))) {
					return true;
				} else {
					return false;
				}
			} else {
				if (!encryptionMap.containsKey(stringOccurence2.str.charAt(0))
						|| !encryptionMap.containsKey(stringOccurence2.str.charAt(1)))
					return true;
				else
					return false;
			}
		}
	}

	private <E> List<List<E>> permutateN(List<E> l, int N) {
		List<List<E>> lReturn = new LinkedList<List<E>>();
		if (N == 2) {
			List<E> l1 = new LinkedList<E>(l);
			List<E> l2 = new LinkedList<E>(l);
			Collections.swap(l2, 0, 1);
			lReturn.add(l1);
			lReturn.add(l2);
			return lReturn;
		}
		List<List<E>> lPermutReturn = new LinkedList<List<E>>();
		List<E> lPopped = new LinkedList<E>(l);
		for (int i = 0; i < l.size() && i < N; i++) {
			E pop = lPopped.remove(i);
			List<List<E>> lPermut = permutateN(lPopped, N - 1);
			for (int j = 0; j < lPermut.size(); j++) {
				List<E> tmp = lPermut.get(j);
				tmp.add(0, pop);
				lPermutReturn.add(tmp);
			}
			lPopped = new LinkedList<>(l);
		}
		return lPermutReturn;
	}

	public List<String> permutatedMonocharacterKeys(String cipherText) {
		List<String> keys = new LinkedList<>();
		countOccurenceMonocharacter(cipherText);
		genFrequencyLetter("IT");
		List<List<StringOccurence>> lists = permutateN(frequencyPlainTextList, 3);
		for (List<StringOccurence> l : lists) {
			clearMaps();
			frequencyPlainTextList = l;
			keys.add(generateMonocharacterKey());
		}
		return keys;
	}

	public List<String> permutatedDigramKeys(String cipherText) {
		List<String> keys = new LinkedList<>();
		countOccurenceDigramCipherText(cipherText);
		countOccurenceDigramPlainText();
		List<List<StringOccurence>> lists = permutateN(frequencyDigramPlainTextList, 3);
		for (List<StringOccurence> l : lists) {
			clearMaps();
			frequencyDigramPlainTextList = l;
			keys.add(generateDigramKey());
		}
		return keys;
	}

	private class StringOccurence implements Comparable<StringOccurence> {
		public String str;
		public Float occurrence;

		public StringOccurence(String c, Float o) {
			str = c;
			occurrence = o;
		}

		@Override
		public int compareTo(StringOccurence o) {
			if (o.occurrence < occurrence)
				return -1;
			if (o.occurrence > occurrence)
				return 1;
			return 0;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StringOccurence other = (StringOccurence) obj;
			if (str == null) {
				if (other.str != null)
					return false;
			} else if (str.compareTo(other.str) != 0)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "StringOccurence {str=" + str + ", occurrence=" + occurrence + "}";
		}

	}

	public String changeKey(String key, Character cipherChar, Character plainChar) {
		int indexPlain, indexCipher;
		Character tmp;
		indexPlain = alphabet.indexOf(plainChar);
		indexCipher = key.indexOf(cipherChar);
		
		tmp = key.charAt(indexPlain);
		key = key.substring(0, indexPlain) + cipherChar + key.substring(indexPlain + 1, key.length());
		key = key.substring(0, indexCipher) + tmp + key.substring(indexCipher + 1, key.length());
		
		return key;	
	}
}
