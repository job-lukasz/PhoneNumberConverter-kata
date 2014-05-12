package converter;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class NumberConverter {

	private static final Map<Character, char[]> numberMap;
	static {
		Map<Character, char[]> tempMap = new TreeMap<Character, char[]>();
		tempMap.put('0', new char[] { '0' });
		tempMap.put('1', new char[] { '1' });
		tempMap.put('2', new char[] { 'A', 'B', 'C' });
		numberMap = Collections.unmodifiableMap(tempMap);
	}

	private class Digit {
		public int currentCursorIteration;
		public char converterValue[];

		public Digit(char value) {
			currentCursorIteration = -1;
			converterValue = numberMap.get(value);
		}

		public char getCurrentDigit() {
			return converterValue[currentCursorIteration];
		}

		public boolean isMoreValue() {
			return currentCursorIteration < converterValue.length;
		}
	}

	private Digit digits[];
	private String result = "";
	private boolean cursorRight = true;
	private int cursorPosition = 0;
	private String currWord = "";
	
	public String convert(String number) {
		initDigits(number);
		while (isAnythingToDo()) {
			digits[cursorPosition].currentCursorIteration++;
			if (digits[cursorPosition].isMoreValue()) {
				currWord += digits[cursorPosition].getCurrentDigit();
				moveCursorRight();
			} else {
				moveCursorLeft();
			}
		}
		return result.substring(0, result.length() - 1);
	}

	private void deleteCursoredChar() {
		if (currWord.length() > 0) {
			currWord = currWord.substring(0, currWord.length() - 1);
		}
	}

	private void moveCursorLeft() {
		cursorRight = false;
		cursorPosition--;
		deleteCursoredChar();
	}

	private void moveCursorRight() {
		cursorRight = true;
		cursorPosition++;
		if (cursorPosition < 9) {
			digits[cursorPosition].currentCursorIteration = -1;
		} else {
			result += currWord + "\n";
			moveCursorLeft();
		}
	}

	private boolean isAnythingToDo() {
		return (cursorRight == true) || (cursorPosition != -1);
	}

	private void initDigits(String number) {
		digits = new Digit[9];
		for (int i = 0; i < 9; i++) {
			digits[i] = new Digit(number.charAt(i));
		}
	}
}
