package com.sacontreras.library.algorithms.strings.reverse;

public class CharacterSwapStringReverser implements StringReverser {

	@Override
	public String reverse(String string) {
		char[] chString = string.toCharArray();
		int len = chString.length;
		for (int i = 0; i < len/2; i++) {
			int swappos = len - 1 - i;
			char swap = chString[swappos];
			chString[swappos] = chString[i];
			chString[i]= swap; 
		}
		return new String(chString);
	}
}
