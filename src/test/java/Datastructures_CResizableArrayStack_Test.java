import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.stack.CResizableArrayStack;
import com.sacontreras.library.datastructures.stack.CStackUnderflowException;

public class Datastructures_CResizableArrayStack_Test {
	
	@Test
	@DisplayName("test_CResizableArrayStack_of_chars_zero_size")
	public void test_CResizableArrayStack_of_chars_zero_size() {
		//explicitly construct with zero size
		CResizableArrayStack<Character> char_stack = new CResizableArrayStack<Character>();
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
	@DisplayName("test_CResizableArrayStack_of_chars_non_zero_size")
	public void test_CResizableArrayStack_of_chars_non_zero_size() {
		char[] c_ary_expected = new char[] {'a', 'b', 'c', 'd', 'e'};
		
		CResizableArrayStack<Character> char_stack = new CResizableArrayStack<Character>();
		assertEquals(
			true,
			char_stack.isEmpty()
		);
	
		//since we have not yet pushed any elements, we should expect CStackUnderflowException
		CStackUnderflowException e1 = assertThrows(
			CStackUnderflowException.class,
			() -> {
				Character c = char_stack.peek();
			}
		);
		
		//and same for pop()
		CStackUnderflowException e2 = assertThrows(
			CStackUnderflowException.class,
			() -> {
				Character c = char_stack.pop();
			}
		);
		
		//push c_ary_expected onto stack element by element
		for (int i = 0; i < c_ary_expected.length; i++) {
			char_stack.push(c_ary_expected[i]);
			assertEquals(
				i + 1,
				char_stack.getSize()
			);
		}
		
		char c = '\0';
		for (int i = 0; i < c_ary_expected.length; i++) {
			c = char_stack.peek();
			//size should not change with peek and should be c_ary_expected.length - 1 since that is the last element we pushed onto the stack
			assertEquals(
				c_ary_expected.length,
				char_stack.getSize()
			);
			//c should not change since we are only peeking, and should always refer to c_ary_expected[c_ary_expected.length - 1] (until we pop)
			assertEquals(
				c_ary_expected[c_ary_expected.length - 1],
				c
			);
		}
		
		char[] c_ary_expected_reversed = new char[c_ary_expected.length];
		for (int i = 0; i < c_ary_expected.length; i++) {
			assertEquals(
				c_ary_expected.length - i,
				char_stack.getSize()
			);
			c_ary_expected_reversed[i] = char_stack.pop();
			assertEquals(
				c_ary_expected.length - i - 1,
				char_stack.getSize()
			);
			assertEquals(
				c_ary_expected[c_ary_expected.length - i - 1],
				c_ary_expected_reversed[i]
			);
		}
	}
}
