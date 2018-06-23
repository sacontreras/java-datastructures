package com.sacontreras.datastructures.practice;

import com.sacontreras.library.*;

public class Main {

	public static void main(String[] args) {
		String 
			test_string_before = "Hello World!", 
			test_string_after = null;
		
//		System.out.println(String.format("main: running bitwise_AND_swap_chars on test_string \"%s\"", test_string_before));
//		test_string_after = Utils.Strings.NoUseFound.bitwise_AND_swap_chars(test_string_before);
//		System.out.println(String.format("main: bitwise_AND_swap_chars - test_string: \"%s\", test_string_after: \"%s\"", test_string_before, test_string_after));
//		System.out.println("");
//		
//		System.out.println(String.format("main: running bitwise_OR_swap_chars on test_string \"%s\"", test_string_before));
//		test_string_after = Utils.Strings.NoUseFound.bitwise_OR_swap_chars(test_string_before);
//		System.out.println(String.format("main: bitwise_OR_swap_chars - test_string: \"%s\", test_string_after: \"%s\"", test_string_before, test_string_after));
//		System.out.println("");
		
		System.out.println(String.format("main: running bitwise_XOR_swap_chars on test_string \"%s\"", test_string_before));
		test_string_after = Utils.Strings.Useful.bitwise_XOR_swap_chars(test_string_before);
		System.out.println(String.format("main: bitwise_XOR_swap_chars - test_string: \"%s\", test_string_after: \"%s\"", test_string_before, test_string_after));
		System.out.println("");
		
		System.out.println(String.format("main: running revserseString_using_stack on test_string \"%s\"", test_string_before));
		test_string_after = Utils.Strings.revserseString_using_stack(test_string_before);
		System.out.println(String.format("main: revserseString_using_stack - test_string: \"%s\", test_string_after: \"%s\"", test_string_before, test_string_after));
		System.out.println("");
	}
}
