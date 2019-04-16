package com.sacontreras.library.algorithms.strings.palindrome;

import com.sacontreras.library.algorithms.strings.reverse.CharacterSwapStringReverser;
import com.sacontreras.library.algorithms.strings.reverse.StringReverser;

public class RegexMatcherPalindromTester implements PalindromeTester {
	final private StringReverser stringReverser;

	public RegexMatcherPalindromTester() {
		stringReverser = new CharacterSwapStringReverser();
	}

	@Override
	public boolean isPalindrome(String string) {
		//convert to lowercase and strip via replaceAll with regex pattern mather
		String s2Stripped = string.toLowerCase().replaceAll("[^a-z]+", "");
//		System.out.printf("isPalindromeV2: \"%s\" stripped: \"%s\"\n", string, s2Stripped);
		
		//now reverse string
		String s2StrippedAndReversed = new String(stringReverser.reverse(new String(s2Stripped.toCharArray())));
//		System.out.printf("isPalindromeV2: \"%s\" reversed: \"%s\"\n", s2Stripped, s2StrippedAndReversed);
		
		return s2StrippedAndReversed.equals(s2Stripped);
	}
}
