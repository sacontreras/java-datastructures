package com.sacontreras.library.datastructures.practice;

import com.sacontreras.library.util.*;

public class Strings {

	final public static void reverseString_all_to_console(final String str) {
		String str_reversed;
		long 
			l_start = -1,
			l_end = -1;
		
		//System.out.println(String.format("main: running bitwise_XOR_swap_chars on test_string \"%s\"", str));
		l_start = System.nanoTime();
//		str_reversed = StringUtils.bitwise_swap_chars(str);
		char[] c_str = str.toCharArray();
		Bitwise.bitwise_swap(c_str);
		str_reversed = new String(c_str);
		l_end = System.nanoTime();
		//System.out.println(String.format("main: bitwise_XOR_swap_chars - test_string: \"%s\", test_string_after: \"%s\"; duration: %d ns", str, str_reversed, (l_end - l_start)));
		//System.out.println("");
		
		//System.out.println(String.format("main: running revserseString_using_arraystack on test_string \"%s\"", str));
		l_start = System.nanoTime();
		str_reversed = new String(StringUtils.to_char_array(StringUtils.to_CArrayStack(str)));
		l_end = System.nanoTime();
		//System.out.println(String.format("main: revserseString_using_arraystack - test_string: \"%s\", test_string_after: \"%s\"; duration: %d ns", str, str_reversed, (l_end - l_start)));
		//System.out.println("");
		
		//System.out.println(String.format("main: running revserseString_using_linkedliststack on test_string \"%s\"", str));
		l_start = System.nanoTime();
		str_reversed = new String(StringUtils.to_char_array(StringUtils.to_CLinkedListStack(str)));
		l_end = System.nanoTime();
		//System.out.println(String.format("main: revserseString_using_linkedliststack - test_string: \"%s\", test_string_after: \"%s\"; duration: %d ns", str, str_reversed, (l_end - l_start)));
		//System.out.println("");
	}
}
