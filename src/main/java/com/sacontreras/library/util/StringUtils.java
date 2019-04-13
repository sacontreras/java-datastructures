package com.sacontreras.library.util;

//import com.sacontreras.library.datastructures.hashtable.CChainedHashTable;
import com.sacontreras.library.datastructures.stack.CArrayStack;
import com.sacontreras.library.datastructures.stack.CLinkedListStack;
import com.sacontreras.library.datastructures.stack.CStackOverflowException;
import com.sacontreras.library.datastructures.stack.CStackUnderflowException;

final public class StringUtils {
		
		final public static String bitwise_swap_chars(final String str) {
			char[] c_str = str.toCharArray();
			Bitwise.bitwise_swap(c_str);
			return new StringBuilder().append(c_str).toString();
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

		final public static char[] to_char_array(final int[] i_ary) {
			char[] c_ary = null;
			int len = i_ary.length;
			if (len > 0) {
				c_ary = new char[len];
				for (int i = 0; i < len; i++)
					c_ary[i] = (char)i_ary[i];
			}
			return c_ary;
		}

		final public static int[] to_int_array(final char[] c_ary) {
			int[] i_ary = null;
			int len = c_ary.length;
			if (len > 0) {
				i_ary = new int[len];
				for (int i = 0; i < len; i++)
					i_ary[i] = c_ary[i];
			}
			return i_ary;
		}

		final public static String gen_random_string(final int len) {
            StringBuilder sb = new StringBuilder();
            int which_set = 0;
            char c = 0;
            for (int j = 0; j < len; j++) {
                which_set = MathStuff.getInstance().randomIntVal(0, 2);
                switch (which_set) {
                    case 0: //numbers
                        c = (char)MathStuff.getInstance().randomIntVal(48, 57);
                        break;
                    case 1: //ucase
                        c = (char)MathStuff.getInstance().randomIntVal(65, 90);
                        break;
                    default:    //lcase
                        c = (char)MathStuff.getInstance().randomIntVal(97, 122);
                        break;
                }
                sb.append(c);
            }
            return sb.toString();
        }
	}