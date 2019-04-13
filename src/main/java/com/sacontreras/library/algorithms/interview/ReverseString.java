package com.sacontreras.library.algorithms.interview;

public class ReverseString {
	public static void main(String[] args) {
		String s = "Steven Contreras";
		char[] reversed = StringUtil.reverse(s.toCharArray());
		System.out.printf("\"%s\" reversed is \"%s\"", s, new String(reversed));
	}
}
