package com.revature.eval.java.core;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j = 0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String[] words = phrase.split("\\W+");
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < words.length; i++) {
			result.append(Character.toUpperCase(words[i].charAt(0)));
		}
		return result.toString();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if (sideOne == sideTwo && sideTwo == sideThree) {
				return true;
			}
			return false;
		}

		public boolean isIsosceles() {
			if (sideOne == sideTwo) {
				return true;
			} else if (sideOne == sideThree) {
				return true;
			} else if (sideTwo == sideThree) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			if (sideOne != sideTwo && sideTwo != sideThree && sideOne != sideThree) {
				return true;
			}
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		char[] chars = string.toCharArray();
		int finalScore = 0;

		String[] points = { "AEIOULNRST", "DG", "BCMP", "FHVWY", "K", "JX" };
		for (char c : chars) {
			c = Character.toUpperCase(c);
			if (points[0].indexOf(c) >= 0) {
				finalScore += 1;
			} else if (points[1].indexOf(c) >= 0) {
				finalScore += 2;
			} else if (points[2].indexOf(c) >= 0) {
				finalScore += 3;
			} else if (points[3].indexOf(c) >= 0) {
				finalScore += 4;
			} else if (points[4].indexOf(c) >= 0) {
				finalScore += 5;
			} else if (points[5].indexOf(c) >= 0) {
				finalScore += 8;
			} else {
				finalScore += 10;
			}
		}

		return finalScore;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		string = string.replaceAll("[\\s()-.]", "");
		char[] chars = string.toCharArray();
		StringBuffer finalNumber = new StringBuffer();
		
		for (char c : chars) {
			if (Character.toString(c).matches("[0-9]") && finalNumber.length() < 11) {
				finalNumber.append(c);
			} else {
				throw new IllegalArgumentException();
			}
		}
		
		return finalNumber.toString();
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		String[] words = string.split("\\W+");
		Map<String, Integer> map = new HashMap<>();
		for (String w : words) {
			if (map.containsKey(w)) {
				map.put(w, map.get(w) + 1);
			} else {
				map.put(w, 1);
			}
		}
		return map;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int start = 0;
			int end = sortedList.size() - 1;
			int index = (start + end) / 2;

			Comparable<T> compT = (Comparable<T>) t;
			while (!sortedList.get(index).equals(compT)) {
				if (compT.compareTo(sortedList.get(index)) > 0) {
					start = index + 1;
					index = (start + end) / 2;
				} else {
					end = index - 1;
					index = (start + end) / 2;
				}
			}
			return index;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		String[] words = string.split("\\s+");
		StringBuffer output = new StringBuffer();
		
		for (int i = 0; i < words.length; i++) {
			
			if (words[i].substring(0, 1).matches("[AEIOUaeiou]")) {
				words[i] = words[i] + "ay";
			} else if (words[i].charAt(0) == 'q') {
				words[i] = words[i].substring(2) + "quay";
			} else if (words[i].toLowerCase().indexOf("a") > 1 || words[i].toLowerCase().indexOf("e") > 1
					|| words[i].toLowerCase().indexOf("i") > 1 || words[i].toLowerCase().indexOf("o") > 1
					|| words[i].toLowerCase().indexOf("u") > 1) {
				for (int j = 0; j < words[i].length(); j++) {
					if (Character.toString(words[i].charAt(j)).matches("[AEIOUaeiou]")) {
						System.out.println(words[i].charAt(j));
						words[i] = words[i].substring(j) + words[i].substring(0, j) + "ay";
						break;
					}
				}
			} else {
				words[i] = words[i].substring(1) + words[i].substring(0, 1) + "ay";
			}
		}
		for (int i = 0; i < words.length; i++) {
			if (i != words.length - 1) {
				output.append(words[i] + " ");
			} else {
				output.append(words[i]);
			}

		}
		return output.toString();

	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String inString = Integer.toString(input);
		char[] digits = inString.toCharArray();
		int total = 0;
		
		for (char c : digits) {
			total += Math.pow(Integer.parseInt(Character.toString(c)), digits.length);
		}
		
		if (total == input) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		ArrayList<Long> returnList= new ArrayList<Long>();
		int cap = (int)Math.sqrt(l);
		
		while(l % 2 == 0) {
			returnList.add((long)2);
			l /= 2;
		}
		
		for (int i = 3; i <= cap; i += 2) {
			while(l % i == 0) {
				returnList.add((long) i);
				l /= i;
			}
		}
		
		if (l > 2) {
			returnList.add(l);
		}
		return returnList;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			StringBuilder output = new StringBuilder();
			char[] letters = string.toCharArray();
			for (char c : letters) {
				if (Character.toString(c).matches("[^a-zA-Z]")) {
					output.append(c);
				} else if (c >= 97 && c <= 122) {
					int newVal = ((c-97) + key) % 26;
					System.out.println(newVal);
					output.append((char)(newVal+97));
				} else if (c >= 65 && c <= 90) {
					int newVal = ((c-65) + key) % 26;
					System.out.println(newVal);
					output.append((char)(newVal+65));
				} 
//				else if ((int)c >= 48 && (int)c <= 57) {
//					int newVal = (((int)c-48) + key) % 10;
//					System.out.println(newVal);
//					output.append((char)(newVal+48));
//				}
			}
			
			return output.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		if (i == 0) {
			throw new IllegalArgumentException();
		}
		
		for (int num = 2, j = 0; j < i; num++) {
			if(isPrime(num)) {
				j++;
				if (j == i) {
					return num;
				}
			}
		}
		return 0;
	}
	
	private boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			StringBuilder output = new StringBuilder();
			char[] letters = string.toCharArray();
//			char temp;
			int count = 0;
			
			for (char c : letters) {
//				temp = Character.toLowerCase(c);
//				if (Character.toString(c).matches("[A-Z]")) {
//					temp = (char)(c+32);
//				} else {
//					temp = c;
//				}
				
//				System.out.println(temp);
				
				if (Character.toString(c).matches("[a-z]")) {
					int dist = c-97;
					dist = 25-dist;
					output.append((char)(97+dist));
					count++;
				} else if (Character.toString(c).matches("[0-9]")) {
					output.append(c);
					count++;
				} else if (Character.toString(c).matches("[A-Z]")) {
					int dist = (c+32)-97;
					dist = 25-dist;
					output.append((char)(97+dist));
					count++;
				}
				
				
				if (count == 5) {
					output.append(" ");
					count = 0;
				}
			}
			
			return output.toString().trim();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			string = string.replace(" ", "");
			StringBuilder output = new StringBuilder();
			char[] letters = string.toCharArray();
			
			for (char c : letters) {
				if (Character.toString(c).matches("[a-z]")) {
					int dist = c-97;
					dist = 25-dist;
					output.append((char)(97+dist));
				} else if (Character.toString(c).matches("[0-9]")) {
					output.append(c);
				}
			}
			return output.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		string = string.replace("-", "");
		char[] letters = string.toCharArray();
		int mult = 10;
		int total = 0;
		
		for (char c : letters) {
			if (Character.isDigit(c)) {
				total += Character.getNumericValue(c) * mult;
				System.out.println(c);
				mult--;
			} else if (c == 'X') {
				total += 10 * mult;
				mult--;
			} else {
				return false;
			}
		}
		
		if (total % 11 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		string = string.replace(" ", "");
		char[] letters = string.toCharArray();
		HashSet<Character> total = new HashSet<Character>();
		
		for (char c : letters) {
			total.add(c);
		}
		
		System.out.println(total);
		if (total.size() == 26) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		if (given.getClass().equals(LocalDateTime.class)) {
			LocalDateTime temp = (LocalDateTime)given;
			return temp.plusSeconds(1000000000);
		} else {
			LocalDate temp = (LocalDate)given;
			LocalDateTime finalTime = temp.atStartOfDay();
			finalTime = finalTime.plusSeconds(1000000000);
			return finalTime;
		}
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		HashSet<Integer> factors = new HashSet<Integer>();
		int finalTotal = 0;
		
		for (int num : set) {
			for (int j = 0; j < i; j++) {
				if (j % num == 0) {
					factors.add(j);
				}
			}
		}
		
		for (int num : factors) {
			finalTotal += num;
		}
		
		return finalTotal;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		string = string.replace(" ", "");
		System.out.println(string);
		ArrayList<Integer> values = new ArrayList<Integer>();
		int total = 0;
		int temp;
		
		for (int i = string.length()-2; i >= 0; i-=2) {
			if (Character.isDigit(string.charAt(i))) {
				temp = Integer.parseInt(Character.toString(string.charAt(i)));
				if (temp * 2 < 10) {
					System.out.println(temp + " " + temp*2);
					values.add(temp * 2);
				} else {
					int num = temp * 2;
					int sum = 0;
					
					while(num > 0) {
						sum += num % 10;
						num /= 10;
					}
					System.out.println(sum);
					values.add(sum);
				}
			} else {
				return false;
			}
		}
		
		for (int i = string.length()-1; i >= 0; i-=2) {
			if (Character.isDigit(string.charAt(i))) {
				values.add(Integer.parseInt(Character.toString(string.charAt(i))));
			} else {
				return false;
			}
		}
		
		for (int i : values) {
			total += i;
		}
		
		if (total % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		int finalAnswer = 0;
		
		string = string.replace("What is ", "");
		string = string.replace("?", "");
		
		if (string.contains("plus")) {
			String[] nums = string.split(" plus ");
			finalAnswer = Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]);
		} else if (string.contains("minus")) {
			String[] nums = string.split(" minus ");
			finalAnswer = Integer.parseInt(nums[0]) - Integer.parseInt(nums[1]);
		} else if (string.contains("divided by")) {
			String[] nums = string.split(" divided by ");
			finalAnswer = Integer.parseInt(nums[0]) / Integer.parseInt(nums[1]);
		} else {
			String[] nums = string.split(" multiplied by ");
			finalAnswer = Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
		}
		return finalAnswer;
	}

}
