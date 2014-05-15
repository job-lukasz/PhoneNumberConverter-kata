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
		tempMap.put('2', new char[] { 'A', 'B', 'C'});
		tempMap.put('3', new char[] { 'D', 'E', 'F'});
		tempMap.put('4', new char[] { 'G', 'H', 'I'});
		tempMap.put('5', new char[] { 'J', 'K', 'L'});
		tempMap.put('6', new char[] { 'M', 'N', 'O'});
		tempMap.put('7', new char[] { 'P', 'Q', 'R', 'S'});
		tempMap.put('8', new char[] { 'T', 'U', 'V'});
		tempMap.put('9', new char[] { 'W', 'X', 'Y', 'Z'});
		numberMap = Collections.unmodifiableMap(tempMap);
	}

	private class Digit {
		private int digitIteration;
		private char converterValue[];

		public Digit(char value) {
			digitIteration = -1;
			converterValue = numberMap.get(value);
		}

		public char getCurrentDigit() {
			return converterValue[digitIteration];
		}

		public boolean isMoreValue() {
			return digitIteration < converterValue.length;
		}
		public void increaseIteration(){
			digitIteration++;
		}
		public void resetIteration(){
			digitIteration = -1;
		}
	}

	private Digit digits[];
	private String result = "";
	private boolean cursorRight = true;
	private int cursorPosition = 0;
	private String currWord = "";
	
	public String convert(String number) {
		if(!initDigits(number)){
			return "ERROR";
		}
		while (isAnythingToDo()) {
			digits[cursorPosition].increaseIteration();
			if (digits[cursorPosition].isMoreValue()) {
				currWord += digits[cursorPosition].getCurrentDigit();
				moveCursorRight();
			} else {
				moveCursorLeft();
			}
		}
		return resultWithoutLastNL();
	}

	private String resultWithoutLastNL() {
		return result.substring(0, result.length() - 1);
	}

	private void deleteLastChar() {
		if (currWord.length() > 0) {
			currWord = currWord.substring(0, currWord.length() - 1);
		}
	}

	private void resetLastCharIterator() {
		if (cursorPosition < 9) {
			digits[cursorPosition].resetIteration();
		}
	}

	private void moveCursorLeft() {
		resetLastCharIterator();
		deleteLastChar();
		cursorRight = false;
		cursorPosition--;
	}

	private void moveCursorRight() {
		cursorRight = true;
		cursorPosition++;
		if (cursorPosition > 8) {
			addWordToResult();
			moveCursorLeft();
		}
	}

	private void addWordToResult() {
		result += currWord + "\n";
	}

	private boolean isAnythingToDo() {
		return (cursorRight == true) || (cursorPosition != -1);
	}

	private boolean initDigits(String number) {
		digits = new Digit[9];
		if(number.length()!=9) {
			return false;
		}
		for (int i = 0; i < 9; i++) {
				char curChar = number.charAt(i);
				if(!isDigit(curChar)){
					return false;
				}
				digits[i] = new Digit(curChar);
		}
		return true;
	}

	private boolean isDigit(char curChar) {
		return curChar>='0'&&curChar<='9';
	}
}
