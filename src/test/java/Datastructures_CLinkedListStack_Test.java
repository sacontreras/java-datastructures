import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.datastructures.stack.CLinkedListStack;
import com.sacontreras.datastructures.stack.CStackOverflowException;
import com.sacontreras.datastructures.stack.CStackUnderflowException;

public class Datastructures_CLinkedListStack_Test {
	@Test
	@DisplayName("test_CLinkedListStack_of_chars_zero_size")
	public void test_CLinkedListStack_of_chars_zero_size() {
		CLinkedListStack<Character> char_stack = new CLinkedListStack<Character>();
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
		
		CStackUnderflowException e2 = assertThrows(
			CStackUnderflowException.class,
			() -> {
				Character c = char_stack.pop();
			}
		);
	}
	
	@Test
	@DisplayName("test_CLinkedListStack_of_chars_non_zero_size")
	public void test_CLinkedListStack_of_chars_non_zero_size() {
		char[] c_ary_expected = new char[] {'a', 'b', 'c', 'd', 'e'};
		
		char 
			c_expect = '\0',
			c_result = '\0';
		int 
			i_expect = -1,
			i_result = -1;
		boolean
			b_expect = false,
			b_result = false;
		
		CLinkedListStack<Character> char_stack = new CLinkedListStack<Character>();
		i_expect = 0;
		i_result = char_stack.getSize();
		assertEquals(
			i_expect,
			i_result
		);
	
		//push c_ary_expected onto stack element by element
		try {
			for (int i = 0; i < c_ary_expected.length; i++) {
				char_stack.push(c_ary_expected[i]);
				
				c_expect = c_ary_expected[i];
				c_result = char_stack.peek();
				assertEquals(
					c_expect,
					c_result
				);
				
				i_expect = i + 1;
				i_result = char_stack.getSize();
				assertEquals(
					i_expect,
					i_result
				);
			}
		} catch (CStackOverflowException e) {
			e.printStackTrace();
			fail("push failed when it should have succeeded - something is wrong in implementation of push");
		} catch (CStackUnderflowException e) {
			e.printStackTrace();
			fail("peek failed when it should have succeeded - something is wrong in implementation of peek");
		}
		
		char[] c_ary_expected_reversed = new char[c_ary_expected.length];
		try {
			for (int i = 0; i < c_ary_expected.length; i++) {
				c_ary_expected_reversed[i] = char_stack.pop();
				
				i_expect = c_ary_expected.length - (i + 1 /*for the pop*/);
				i_result = char_stack.getSize();
				assertEquals(
					i_expect,
					i_result
				);
				
				c_expect = c_ary_expected[c_ary_expected.length - i - 1];
				c_result = c_ary_expected_reversed[i];
				assertEquals(
					c_expect,
					c_result
				);
			}
		} catch (CStackUnderflowException e) {
			e.printStackTrace();
			fail("pop failed when it should have succeeded - something is wrong in implementation of pop");
		}
	}
}
