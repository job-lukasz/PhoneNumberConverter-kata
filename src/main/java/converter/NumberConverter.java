package converter;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class NumberConverter {

	private char numberArr[];

	private static final Map<Character, char[]> numberMap;
	static {
		Map<Character, char[]> tempMap = new TreeMap<Character, char[]>();
		tempMap.put('0', new char[] { '0' });
		tempMap.put('1', new char[] { '1' });
		tempMap.put('2', new char[] { 'A', 'B', 'C' });
		numberMap = Collections.unmodifiableMap(tempMap);
	}

	public String convert(String number) {
		String result = "";
		boolean cursorRight = true;
		int cursorPosition = 0;
		numberArr = new char[9];
		int numberCursorIteration[] = new int[9];
		int maxCursorIteration[] = new int[9];
		for (int i = 0; i < 9; i++) {
			numberArr[i] = number.charAt(i);
			numberCursorIteration[i] = -1;
			if (numberArr[i] > '1') {
				maxCursorIteration[i] = 3;
			} else {
				maxCursorIteration[i] = 1;
			}
		}
		String tempWord = "";
		while ((cursorRight == true) || (cursorPosition != -1)) {
			numberCursorIteration[cursorPosition]++;
			if (!cursorRight && tempWord.length() > 0) {
				tempWord = tempWord.substring(0, tempWord.length() - 1);
			}
			if (numberCursorIteration[cursorPosition] < maxCursorIteration[cursorPosition]) {
				tempWord += numberMap.get(numberArr[cursorPosition])[numberCursorIteration[cursorPosition]];

				cursorRight = true;
				cursorPosition++;

				if (cursorPosition < 9) {
					numberCursorIteration[cursorPosition] = -1;
				} else {
					result += tempWord + "\n";

					cursorRight = false;
					cursorPosition--;
				}
			} else {
				cursorRight = false;
				cursorPosition--;
			}
		}
		return result.substring(0, result.length() - 1);
	}
}
