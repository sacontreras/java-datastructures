package com.sacontreras.library.algorithms.strings.palindrome;

public class RegexMatcherStringBufferReverserPalindromeTester implements PalindromeTester {

	@Override
	public boolean isPalindrome(String string) {
		//identical to RegexMatcherPalindromTester, convert to lowercase and strip via replaceAll with regex pattern mather
		String s2 = string.toLowerCase().replaceAll("[^a-z]", "");
//		System.out.printf("isPalindromeV3: \"%s\" stripped: \"%s\"\n", string, s2);
		
		//now instead of using a homebrewed string reverser, use reverse() from StringBuffer
		StringBuffer sb = new StringBuffer(s2);
		String s2StrippedAndReversed =sb.reverse().toString();
//		System.out.printf("isPalindromeV3: \"%s\" reversed: \"%s\"\n", s2, s2StrippedAndReversed);
		
		return s2StrippedAndReversed.equals(s2);
	}
}
