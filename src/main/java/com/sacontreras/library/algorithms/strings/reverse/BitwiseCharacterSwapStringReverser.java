package com.sacontreras.library.algorithms.strings.reverse;

public class BitwiseCharacterSwapStringReverser implements StringReverser {
	@Override
	public String reverse(String string) {
		char[] chString = string.toCharArray();
		int len = chString.length,
			start = 0, 
			end = len - 1;  	
		for (int i = 0; i < len/2; i++) {
			chString[start] ^= chString[end];
			chString[end] ^= chString[start];
			chString[start] ^= chString[end];
			start++;
			end--;
		}
		return new String(chString);
	}
}
