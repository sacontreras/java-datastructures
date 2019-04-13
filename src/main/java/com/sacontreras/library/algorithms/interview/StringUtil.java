package com.sacontreras.library.algorithms.interview;

public class StringUtil {
	public static char[] reverse(char[] s) {
		int len = s.length;
		for (int i = 0; i < len/2; i++) {
			int swappos = len - 1 - i;
			char swap = s[swappos];
			s[swappos] = s[i];
			s[i]= swap; 
		}
		return s;
	}
	
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
		String s2StrippedAndReversed = new String(reverse(s2Stripped.toCharArray()));
		System.out.printf("isPalindrome: \"%s\" reversed: \"%s\"\n", s2Stripped, s2StrippedAndReversed);
		return s2StrippedAndReversed.equals(s2Stripped);
	}
	
	public static boolean isPalindromeV2(String s) {
		String s2Stripped = s.toLowerCase().replaceAll("[^a-z]+", "");
		System.out.printf("isPalindromeV2: \"%s\" stripped: \"%s\"\n", s, s2Stripped);
		String s2StrippedAndReversed = new String(reverse(s2Stripped.toCharArray()));
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
