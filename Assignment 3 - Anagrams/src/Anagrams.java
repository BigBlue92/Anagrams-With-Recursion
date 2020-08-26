//CS145 B 
//Assignment 3: Anagrams
//Ryan Mackenzie
//Anagrams v1.1: Made some minor formatting improvements.

import java.util.*;

public class Anagrams {

	private Map<String, LetterInventory> dict = new TreeMap<String, LetterInventory>();
	private SortedSet<String> anagrams = new TreeSet<String>();

	
	//Pre: Constructor method, takes the dictionary of potential anagrams as a set of strings.
	//Post: Builds a map of the dictionary with all words keyed to their letter inventory.
	public Anagrams(Set<String> dictionary) {
		if (dictionary == null) {
			throw new IllegalArgumentException("Dictionary is empty, please select a valid dictionary.");
		}
		for (String str : dictionary) {
			LetterInventory i = new LetterInventory(str);
			dict.put(str, i);
		}

	}

	//Pre: Takes the phrase as a string.
	//Post: Prints all possible words contained within phrase in alphabetical order.
	public Set<String> getWords(String phrase) {
		anagrams.clear();
		if (phrase == null) {
			throw new IllegalArgumentException("Input phrase is null.");
		}
		LetterInventory letters = new LetterInventory(phrase);
		for (String str : dict.keySet()) {
			if (letters.contains(dict.get(str))) {
				anagrams.add(str);
			}
		}
		System.out.print(anagrams.size());
		return anagrams;
	}

	// Pre: Takes the users input phrase, and the number which represents the
	// maximum amount of words that can be used in the anagram.
	// Post: Calls on a private method to print all anagrams contained within the
	// phrase.
	public void print(String phrase) {
		if (phrase == null) {
			throw new IllegalArgumentException("Phrase is null.");
		}
		LetterInventory letters = new LetterInventory(phrase);
		ArrayList<String> answers = new ArrayList<String>();
		print(letters, answers, "");

	}

	// Pre: Only called by the method above, it's passed the letter
	// inventory of the phrase, the array list of answers, and the last word that
	// was chosen by the recursive program (empty at the start).
	// Post: Recursively tests all possibilities, and prints out the built answer
	// list.
	private void print(LetterInventory letters, ArrayList<String> answers, String lastWord) {
		if (letters.isEmpty()) {
			System.out.println(answers);
			letters.add(lastWord);
			answers.remove(answers.size() - 1);
		} else {
			for (String str : anagrams) {
				if (letters.contains(dict.get(str)) && !answers.contains(str)) {
					answers.add(str);
					letters.subtract(dict.get(str));
					print(letters, answers, str);// If it succeeds, it adds the word and subtracts from the letter
												 // inventory. Then starts a new thread. Recursive statement.
				}
			}
			letters.add(lastWord);
			if (!answers.isEmpty()) {
				answers.remove(answers.size() - 1);// This part backtracks if it gets to the end of the list and fails.
			}
		}
	}

	// Pre: Takes the users input phrase, and the number which represents the
	// maximum amount of words that can be used in the anagram.
	// Post: Calls on a private method to print all anagrams that use the max number
	// of words or less.
	public void print(String phrase, int max) {
		if (phrase == null || max < 0) {
			throw new IllegalArgumentException("Phrase is null or max is less than zero.");
		}
		LetterInventory letters = new LetterInventory(phrase);
		ArrayList<String> answers = new ArrayList<String>();
		print(letters, answers, "", max);

	}

	// Pre: Only called by the private method above, it's passed the letter
	// inventory of the phrase, the array list of answers, the last word that was
	// chosen by the recursive program (empty at the start), and the maximum amount
	// of words allowed for anagrams.
	// Post: Recursively tests all possibilities, and prints out the built answer
	// list if it contains less than the max amount of words.
	private void print(LetterInventory letters, ArrayList<String> answers, String lastWord, int max) {
		if (letters.isEmpty()) {
			// The if statement below is the only part I had to add to complete this method,
			// everything else was copy pasted except for the parts where the int max was
			// added.
			if (answers.size() <= max || max == 0) {
				System.out.println(answers);
			}
			letters.add(lastWord);
			answers.remove(answers.size() - 1);
		} else {
			for (String str : anagrams) {
				if (letters.contains(dict.get(str)) && !answers.contains(str)) {
					answers.add(str);
					letters.subtract(dict.get(str));
					print(letters, answers, str, max);
				}
			}
			letters.add(lastWord);
			if (!answers.isEmpty()) {
				answers.remove(answers.size() - 1);
			}
		}
	}// end print

}// end class