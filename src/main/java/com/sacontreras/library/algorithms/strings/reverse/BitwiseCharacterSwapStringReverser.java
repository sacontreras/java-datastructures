package com.sacontreras.library.algorithms.strings.reverse;

import com.sacontreras.library.util.StringUtils;

public class BitwiseCharacterSwapStringReverser implements StringReverser {
	@Override
	public String reverse(String string) {
		return StringUtils.bitwise_swap_chars(string);
	}
}
