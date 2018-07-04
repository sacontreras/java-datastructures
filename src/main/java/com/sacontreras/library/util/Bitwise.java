package com.sacontreras.library.util;

final public class Bitwise {
	public enum OPERATION {
		AND,
		OR,
		XOR
		;
		
		final public String symbol() {
			switch (this) {
				case AND: return "&";
				case OR: return "|";
				case XOR: 
				default: return "^";
			}
		}
	}

	final public static int bitwise_combine(final int i1, final int i2, Bitwise.OPERATION op) {
		switch (op) {
			case AND: return (i1 & i2);
			case OR: return (i1 | i2);
			case XOR: 
			default: return (i1 ^ i2);
		}
	}
	
	final public static void bitwise_swap(int[] i_ary) {
    	int 
    		len = i_ary.length,
    		len_half = len/2,
    		start = 0, 
    		end = len - 1;
    	for (int i = 0; i < len_half; i++) {
    		i_ary[start] = Bitwise.bitwise_combine(i_ary[start], i_ary[end], Bitwise.OPERATION.XOR);
    		i_ary[end] = Bitwise.bitwise_combine(i_ary[end], i_ary[start], Bitwise.OPERATION.XOR);
    		i_ary[start] = Bitwise.bitwise_combine(i_ary[start], i_ary[end], Bitwise.OPERATION.XOR);
    		start++;
    		end--;
    	}
    }
	
	final public static void bitwise_swap(char[] c_ary) {
    	int 
    		len = c_ary.length,
    		len_half = len/2,
    		start = 0, 
    		end = len - 1;  	
    	for (int i = 0; i < len_half; i++) {
    		c_ary[start] = (char)Bitwise.bitwise_combine(c_ary[start], c_ary[end], Bitwise.OPERATION.XOR);
    		c_ary[end] = (char)Bitwise.bitwise_combine(c_ary[end], c_ary[start], Bitwise.OPERATION.XOR);
    		c_ary[start] = (char)Bitwise.bitwise_combine(c_ary[start], c_ary[end], Bitwise.OPERATION.XOR);
    		start++;
    		end--;
    	}
    }
}