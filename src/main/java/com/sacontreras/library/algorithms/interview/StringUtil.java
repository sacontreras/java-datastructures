package com.sacontreras.library.algorithms.interview;

import com.sacontreras.library.algorithms.strings.reverse.CharacterSwapStringReverser;
import com.sacontreras.library.algorithms.strings.reverse.StringReverser;

public class StringUtil {
	private static StringReverser stringReverser = new CharacterSwapStringReverser();
	
	public static boolean isPalindrome(String s) {
		String s2 = s.toLowerCase();
		char[] s2AsChars = s2.toLowerCase().toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s2AsChars.length; i++) {
			char c = s2AsChars[i];
			if (c >= 'a' && c <= 'z')
				sb.append(c);
		}
		String s2Stripped = sb.toString();
		System.out.printf("isPalindrome: \"%s\" stripped: \"%s\"\n", s, s2Stripped);
		String s2StrippedAndReversed = new String(stringReverser.reverse(new String(s2Stripped.toCharArray())));
		System.out.printf("isPalindrome: \"%s\" reversed: \"%s\"\n", s2Stripped, s2StrippedAndReversed);
		return s2StrippedAndReversed.equals(s2Stripped);
	}
	
	public static boolean isPalindromeV2(String s) {
		String s2Stripped = s.toLowerCase().replaceAll("[^a-z]+", "");
		System.out.printf("isPalindromeV2: \"%s\" stripped: \"%s\"\n", s, s2Stripped);
		String s2StrippedAndReversed = new String(stringReverser.reverse(new String(s2Stripped.toCharArray())));
		System.out.printf("isPalindromeV2: \"%s\" reversed: \"%s\"\n", s2Stripped, s2StrippedAndReversed);
		return s2StrippedAndReversed.equals(s2Stripped);
	}
	
	public static boolean isPalindromeV3(String s) {
		String s2 = s.toLowerCase().replaceAll("[^a-z]", "");
		System.out.printf("isPalindromeV3: \"%s\" stripped: \"%s\"\n", s, s2);
		StringBuffer sb = new StringBuffer(s2);
		String s2StrippedAndReversed =sb.reverse().toString();
		System.out.printf("isPalindromeV3: \"%s\" reversed: \"%s\"\n", s2, s2StrippedAndReversed);
		return s2StrippedAndReversed.equals(s2);
	}
}
