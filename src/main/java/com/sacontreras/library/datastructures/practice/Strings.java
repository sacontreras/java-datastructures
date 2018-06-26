package com.sacontreras.library.datastructures.practice;

import com.sacontreras.library.Utils;

public class Strings {

	final public static void reverseString_all_to_console(final String str) {
		String str_reversed;
		long 
			l_start = -1,
			l_end = -1;
		
		System.out.println(String.format("main: running bitwise_XOR_swap_chars on test_string \"%s\"", str));
		l_start = System.nanoTime();
		str_reversed = Utils.Strings.Useful.bitwise_XOR_swap_chars(str);
		l_end = System.nanoTime();
		System.out.println(String.format("main: bitwise_XOR_swap_chars - test_string: \"%s\", test_string_after: \"%s\"; duration: %d ns", str, str_reversed, (l_end - l_start)));
		System.out.println("");
		
		System.out.println(String.format("main: running revserseString_using_arraystack on test_string \"%s\"", str));
		l_start = System.nanoTime();
		str_reversed = new String(Utils.Strings.Useful.to_char_array(Utils.Strings.Useful.to_CArrayStack(str)));
		l_end = System.nanoTime();
		System.out.println(String.format("main: revserseString_using_arraystack - test_string: \"%s\", test_string_after: \"%s\"; duration: %d ns", str, str_reversed, (l_end - l_start)));
		System.out.println("");
		
		System.out.println(String.format("main: running revserseString_using_linkedliststack on test_string \"%s\"", str));
		l_start = System.nanoTime();
		str_reversed = new String(Utils.Strings.Useful.to_char_array(Utils.Strings.Useful.to_CLinkedListStack(str)));
		l_end = System.nanoTime();
		System.out.println(String.format("main: revserseString_using_linkedliststack - test_string: \"%s\", test_string_after: \"%s\"; duration: %d ns", str, str_reversed, (l_end - l_start)));
		System.out.println("");
	}
}
