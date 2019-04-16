package com.sacontreras.library.algorithms.strings.palindrome;

import com.sacontreras.library.algorithms.strings.reverse.CharacterSwapStringReverser;
import com.sacontreras.library.algorithms.strings.reverse.StringReverser;

public class CharacterComparisonPalindromTester implements PalindromeTester {	
	final private StringReverser stringReverser;

	public CharacterComparisonPalindromTester() {
		stringReverser = new CharacterSwapStringReverser();
	}

	@Override
	public boolean isPalindrome(String string) {
		//to lowercase first
		String s2 = string.toLowerCase();
		char[] s2AsChars = s2.toLowerCase().toCharArray();
		
		//now strip non alphabetic from lcase string
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s2AsChars.length; i++) {
			char c = s2AsChars[i];
			if (c >= 'a' && c <= 'z')
				sb.append(c);
		}
		String s2Stripped = sb.toString();
		
		//now reverse string
//		System.out.printf("isPalindrome: \"%s\" stripped: \"%s\"\n", string, s2Stripped);
		String s2StrippedAndReversed = new String(stringReverser.reverse(new String(s2Stripped.toCharArray())));
//		System.out.printf("isPalindrome: \"%s\" reversed: \"%s\"\n", s2Stripped, s2StrippedAndReversed);
		
		return s2StrippedAndReversed.equals(s2Stripped);
	}
}
