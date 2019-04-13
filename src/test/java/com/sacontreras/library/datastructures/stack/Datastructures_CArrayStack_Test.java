package com.sacontreras.library.datastructures.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Datastructures_CArrayStack_Test {
	
	@Test
	@DisplayName("test_CArrayStack_of_chars_zero_size")
	public void test_CArrayStack_of_chars_zero_size() {
		//explicitly construct with zero size
		CArrayStack<Character> char_stack = new CArrayStack<Character>(0);
		assertEquals(
			0,
			char_stack.getSize()
		);
	
		CStackUnderflowException e1 = assertThrows(
			CStackUnderflowException.class,
			() -> {
				Character c = char_stack.peek();
			}
		);
		assertEquals(
			"top==-1", 
			e1.getMessage()
		);
		
		CStackUnderflowException e2 = assertThrows(
			CStackUnderflowException.class,
			() -> {
				Character c = char_stack.pop();
			}
		);
		assertEquals(
			"top==-1", 
			e2.getMessage()
		);
		
		CStackOverflowException e3 = assertThrows(
			CStackOverflowException.class,
			() -> {
				char_stack.push('a');
			}
		);
		assertEquals(
			String.format("top==%d (capacity==%d-1)", -1, 0), 
			e3.getMessage()
		);
	}
	
	@Test
	@DisplayName("test_CArrayStack_of_chars_non_zero_size")
	public void test_CArrayStack_of_chars_non_zero_size() {
		char[] c_ary_expected = new char[] {'a', 'b', 'c', 'd', 'e'};
		int top_expected = -1;
		
		CArrayStack<Character> char_stack = new CArrayStack<Character>(c_ary_expected.length);
		assertEquals(
			c_ary_expected.length,
			char_stack.getSize()
		);
	
		//since we have not yet pushed any elements, we should expect CStackUnderflowException
		CStackUnderflowException e1 = assertThrows(
			CStackUnderflowException.class,
			() -> {
				Character c = char_stack.peek();
			}
		);
		assertEquals(
			"top==-1", 
			e1.getMessage()
		);
		
		//and same for pop()
		CStackUnderflowException e2 = assertThrows(
			CStackUnderflowException.class,
			() -> {
				Character c = char_stack.pop();
			}
		);
		assertEquals(
			"top==-1", 
			e2.getMessage()
		);
		
		//push c_ary_expected onto stack element by element
		try {
			for (int i = 0; i < c_ary_expected.length; i++) {
				char_stack.push(c_ary_expected[i]);
				top_expected = i;
				assertEquals(
					top_expected,
					char_stack.getTop()
				);
			}
		} catch (CStackOverflowException e) {
			e.printStackTrace();
			fail("push failed when it should have succeeded - something is wrong in implementation of push");
		}
		
		//stack should be full, so attempting to push another element we should expect CStackOverflowException
		CStackOverflowException e3 = assertThrows(
			CStackOverflowException.class,
			() -> {
				char_stack.push(c_ary_expected[0]);
			}
		);
		assertEquals(
			String.format("top==%d (capacity==%d-1)", top_expected, c_ary_expected.length), 
			e3.getMessage()
		);
		
		char c = '\0';
		for (int i = 0; i < c_ary_expected.length; i++) {
			try {
				c = char_stack.peek();
			} catch (CStackUnderflowException e) {
				e.printStackTrace();
				fail("peek failed when it should have succeeded - something is wrong in implementation of peek");
			}
			//top should not change with peek and should be c_ary_expected.length - 1 since that is the last element we pushed onto the stack
			assertEquals(
				c_ary_expected.length - 1,
				char_stack.getTop()
			);
			//c should not change since we are only peeking, and should always refer to c_ary_expected[c_ary_expected.length - 1] (until we pop)
			assertEquals(
				c_ary_expected[c_ary_expected.length - 1],
				c
			);
		}
		
		char[] c_ary_expected_reversed = new char[c_ary_expected.length];
		for (int i = 0; i < c_ary_expected.length; i++) {
			top_expected = c_ary_expected.length - i - 1;
			assertEquals(
				top_expected,
				char_stack.getTop()
			);
			try {
				c_ary_expected_reversed[i] = char_stack.pop();
			} catch (CStackUnderflowException e) {
				e.printStackTrace();
				fail("pop failed when it should have succeeded - something is wrong in implementation of pop");
			}
			top_expected--;
			assertEquals(
				top_expected,
				char_stack.getTop()
			);
			assertEquals(
				c_ary_expected[c_ary_expected.length - i - 1],
				c_ary_expected_reversed[i]
			);
		}
	}
}
