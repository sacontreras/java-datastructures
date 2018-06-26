package com.sacontreras.library;

import com.sacontreras.datastructures.stack.CArrayStack;
import com.sacontreras.datastructures.stack.CLinkedListStack;
import com.sacontreras.datastructures.stack.CStackOverflowException;
import com.sacontreras.datastructures.stack.CStackUnderflowException;

public class Utils {
	final public static class Bitwise {
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

		final public static int bitwise_combine(final int i1, final int i2, OPERATION op) {
			switch (op) {
				case AND: return (i1 & i2);
				case OR: return (i1 | i2);
				case XOR: 
				default: return (i1 ^ i2);
			}
		}
		
	}
	
	final public static class Strings {
		final public static String bitwise_swap_chars(final String str, final Bitwise.OPERATION op) {
	    	int start = 0, end = str.length() - 1;
	    	char[] c_str = str.toCharArray();
	    	int len_half = str.length()/2;
	    	String s_outln = null;
//	    	s_outln = String.format(
//    			"bitwise_swap_chars: str: \"%s\", length: %d - swapping %d char pairs via triple-pass %s technique", 
//    			str, 
//    			str.length(), 
//    			len_half, 
//    			op.name()
//			);
//	    	System.out.println(s_outln);
//	    	System.out.println("");
	    	
	    	for (int i = 0; i < len_half; i++) {
	    		char 
	    			c_start = c_str[start],
	    			c_end = c_str[end];
//	    		s_outln = String.format(
//    				"bitwise_swap_chars: i== %d; str[%d]: '%c' (%s); str[%d]: '%c' (%s)", 
//    				i, 
//    				start, 
//    				c_start, 
//    				Integer.toBinaryString(c_start),
//    				end, 
//    				c_end, 
//    				Integer.toBinaryString(c_end)
//				);
//	    		System.out.println(s_outln);
	    		
	    		c_start = c_str[start];
	    		c_end = c_str[end];
	    		c_str[start] = (char)Bitwise.bitwise_combine(c_str[start], c_str[end], op);
//	    		s_outln = String.format(
//    				"bitwise_swap_chars: \t pass %d: str[%d]: '%c' (%s) %s= str[%d]: '%c' (%s) --> str[%d]: '%c' (%s)", 
//    				1, 
//    				start, 
//    				c_start, 
//    				Integer.toBinaryString(c_start),
//    				op.symbol(),
//    				end, 
//    				c_end, 
//    				Integer.toBinaryString(c_end),
//    				start, 
//    				c_str[start], 
//    				Integer.toBinaryString(c_str[start])
//				);
//	    		System.out.println(s_outln);
	    		
	    		c_start = c_str[start];
	    		c_end = c_str[end];
	    		c_str[end] = (char)Bitwise.bitwise_combine(c_str[end], c_str[start], op);
//	    		s_outln = String.format(
//    				"bitwise_swap_chars: \t pass %d: str[%d]: '%c' (%s) %s= str[%d]: '%c' (%s) --> str[%d]: '%c' (%s)", 
//    				2, 
//    				end, 
//    				c_end, 
//    				Integer.toBinaryString(c_end),
//    				op.symbol(),
//    				start, 
//    				c_start, 
//    				Integer.toBinaryString(c_start),
//    				end, 
//    				c_str[end], 
//    				Integer.toBinaryString(c_str[end])
//				);
//	    		System.out.println(s_outln);
	    		
	    		c_start = c_str[start];
	    		c_end = c_str[end];
	    		c_str[start] = (char)Bitwise.bitwise_combine(c_str[start], c_str[end], op);
//	    		s_outln = String.format(
//    				"bitwise_swap_chars: \t pass %d: str[%d]: '%c' (%s) %s= str[%d]: '%c' (%s) --> str[%d]: '%c' (%s)", 
//    				3, 
//    				start, 
//    				c_start, 
//    				Integer.toBinaryString(c_start),
//    				op.symbol(),
//    				end, 
//    				c_end, 
//    				Integer.toBinaryString(c_end),
//    				start, 
//    				c_str[start], 
//    				Integer.toBinaryString(c_str[start])
//				);
//	    		System.out.println(s_outln);
	    		
	    		start++;
	    		end--;
	    	}
	    	
	        return new String(c_str);
	    }
		
		final public static String revserseString_using_arraystack(final String str) {
			return new String(Useful.to_char_array(Useful.to_CArrayStack(str)));
		}
		
		final public static String revserseString_using_linkedliststack(final String str) {
			return new String(Useful.to_char_array(Useful.to_CLinkedListStack(str)));
		}
		
		
		final public static class Useful {
			final public static String bitwise_XOR_swap_chars(final String str) {
				return Strings.bitwise_swap_chars(str, Bitwise.OPERATION.XOR);
			}
			
			final public static CArrayStack<Character> to_CArrayStack(final String str) {
				CArrayStack<Character> char_stack = null;
				try {
					char[] c_ary_str = str.toCharArray();
					char_stack = new CArrayStack<Character>(c_ary_str.length);
					for (int i = 0; i < c_ary_str.length; i++) {
						char_stack.push(c_ary_str[i]);
					}
				} catch (CStackOverflowException e) {
					e.printStackTrace();
				}
				return char_stack;
			}
			
			final public static CLinkedListStack<Character> to_CLinkedListStack(final String str) {
				CLinkedListStack<Character> char_stack = null;
				try {
					char[] c_ary_str = str.toCharArray();
					char_stack = new CLinkedListStack<Character>();
					for (int i = 0; i < c_ary_str.length; i++) {
						char_stack.push(c_ary_str[i]);
					}
				} catch (CStackOverflowException e) {
					e.printStackTrace();
				}
				return char_stack;
			}
			
			final public static char[] to_char_array(final CArrayStack<Character> char_stack) {
				char[] c_ary = null;
				if (char_stack.getTop() > 0) {
					try {
						c_ary = new char[char_stack.getTop() + 1];
						int i = 0;
						while (char_stack.getTop() > -1)
							c_ary[i++] = char_stack.pop();
					} catch (CStackUnderflowException e) {
						e.printStackTrace();
					}
				}
				return c_ary;
			}
			
			final public static char[] to_char_array(final CLinkedListStack<Character> char_stack) {
				char[] c_ary = null;
				int size = char_stack.getSize();
				if (size > 0) {
					try {
						c_ary = new char[size];
						int i = 0;
						while (true)
							c_ary[i++] = char_stack.pop();
					} catch (CStackUnderflowException e) {
						//e.printStackTrace();
					}
				}
				return c_ary;
			}
		}
		
		final public static class NoUseFound {
			final public static String bitwise_AND_swap_chars(final String str) {
				return Strings.bitwise_swap_chars(str, Bitwise.OPERATION.AND);
			}
			final public static String bitwise_OR_swap_chars(final String str) {
				return Strings.bitwise_swap_chars(str, Bitwise.OPERATION.OR);
			}
		}
	}
}
