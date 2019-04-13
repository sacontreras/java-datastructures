package com.sacontreras.library.algorithms.interview;

public class Palindrome {
	public static void main(String[] args) {
		String[] testStrings = {
			"This is not a palindrome!",
			"A man, a plan, a canal – Panama"
		};
		
		for (int i = 0; i < testStrings.length; i++) {
			System.out.printf("\"%s\" %s a palindrome\n", testStrings[i], StringUtil.isPalindromeV3(testStrings[i]) ? "IS" : "is NOT");
		}
	}
}
