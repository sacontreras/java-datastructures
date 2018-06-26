import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.linkedlist.CLinkedList;
import com.sacontreras.library.datastructures.linkedlist.CLinkedListException;

public class Datastructures_CLinkedList_Test {
	private char 
		c_expect = '\0',
		c_result = '\0';
	private int 
		i_expect = -1,
		i_result = -1;
	private boolean
		b_expect = false,
		b_result = false;
	
	@Test
	@DisplayName("test_CLinkedList_of_chars_zero_size")
	public void test_CLinkedList_of_chars_zero_size() {
		final CLinkedList<Character> ll = new CLinkedList<Character>();
		
		i_expect = 0;
		i_result = ll.getSize();
		assertEquals(
			i_expect,
			i_result
		);
		
		b_expect = false;
		b_result = ll.iterator().hasNext();
		assertEquals(
			b_expect,
			b_result
		);
		
		CLinkedListException e1 = assertThrows(
			CLinkedListException.class,
			() -> {
				Character c = ll.get(0);
			}
		);
		assertEquals(
			"linkedlist is empty", 
			e1.getMessage()
		);
		
		CLinkedListException e2 = assertThrows(
			CLinkedListException.class,
			() -> {
				ll.set(0, 'b');
			}
		);
		assertEquals(
			"linkedlist is empty", 
			e1.getMessage()
		);
	}
	
	@Test
	@DisplayName("test_CLinkedList_of_chars_non_zero_size")
	public void test_CLinkedList_of_chars_non_zero_size() {
		char[] c_ary_expected = new char[] {'a', 'b', 'c', 'd', 'e'};
		
		final CLinkedList<Character> ll = new CLinkedList<Character>();
		
		//append - also tests get(n)
		for (int i = 0; i < c_ary_expected.length; i++) {
			ll.append(c_ary_expected[i]);
			
			i_expect = i + 1;
			i_result = ll.getSize();
			assertEquals(
				i_expect, 
				i_result
			);
			
			c_expect = c_ary_expected[0];
			c_result = ll.getHead().next();
			assertEquals(
				c_expect,
				c_result
			);
			
			c_expect = c_ary_expected[i];
			c_result = ll.getTail().next();
			assertEquals(
				c_expect,
				c_result
			);
			
			try {
				c_expect = c_ary_expected[0];
				c_result = ll.get(0);
				assertEquals(
					c_expect,
					c_result
				);
				
				c_expect = c_ary_expected[i];
				c_result = ll.get(i);
				assertEquals(
					c_expect,
					c_result
				);
			} catch (CLinkedListException e) {
				e.printStackTrace();
				fail(String.format("encountered CLinkedListException(\"%s\") when we should not have", e.getMessage()));
			}
		}
		
		//set(n)
		try {
			c_expect = c_ary_expected[2];
			c_result = ll.get(2);
			assertEquals(
				c_expect,
				c_result
			);
			
			c_expect = 'z';
			ll.set(2, c_expect);
			c_result = ll.get(2);
			assertEquals(
				c_expect,
				c_result
			);
			
			ll.set(2, c_ary_expected[2]);
			c_result = ll.get(2);
			assertEquals(
				c_ary_expected[2],
				c_result
			);
		} catch (CLinkedListException e) {
			e.printStackTrace();
			fail(String.format("encountered CLinkedListException(\"%s\") when we should not have", e.getMessage()));
		}
		
		//removeFirst
		for (int i = 0; i < c_ary_expected.length; i++) {
			ll.removeFirst();
			
			if (i != c_ary_expected.length-1) {
				i_expect = c_ary_expected.length - (i + 1 /*for the one we just removed*/);
				i_result = ll.getSize();
				assertEquals(
					i_expect, 
					i_result
				);
				
				c_expect = c_ary_expected[i+1];
				c_result = ll.getHead().next();
				assertEquals(
					c_expect,
					c_result
				);
				
				c_expect = c_ary_expected[c_ary_expected.length-1];
				c_result = ll.getTail().next();
				assertEquals(
					c_expect,
					c_result
				);
				
				try {
					c_expect = c_ary_expected[i+1];
					c_result = ll.get(0);
					assertEquals(
						c_expect,
						c_result
					);
					
					c_expect = c_ary_expected[c_ary_expected.length-1];
					c_result = ll.get(ll.getSize()-1);
					assertEquals(
						c_expect,
						c_result
					);
				} catch (CLinkedListException e) {
					e.printStackTrace();
					fail(String.format("encountered CLinkedListException(\"%s\") when we should not have", e.getMessage()));
				}
			} else {
				i_expect = 0;
				i_result = ll.getSize();
				assertEquals(
					i_expect, 
					i_result
				);
			}
		}
		
		//prepend
		for (int i = c_ary_expected.length; i > 0; i--) {
			ll.prepend(c_ary_expected[i-1]);
			
			i_expect = c_ary_expected.length - i + 1;
			i_result = ll.getSize();
			assertEquals(
				i_expect, 
				i_result
			);
			
			c_expect = c_ary_expected[c_ary_expected.length-1];
			c_result = ll.getTail().next();
			assertEquals(
				c_expect,
				c_result
			);
			
			c_expect = c_ary_expected[i-1];
			c_result = ll.getHead().next();
			assertEquals(
				c_expect,
				c_result
			);
			
			try {
				c_expect = c_ary_expected[c_ary_expected.length-1];
				c_result = ll.get(ll.getSize()-1);
				assertEquals(
					c_expect,
					c_result
				);
				
				c_expect = c_ary_expected[i-1];
				c_result = ll.get(0);
				assertEquals(
					c_expect,
					c_result
				);
			} catch (CLinkedListException e) {
				e.printStackTrace();
				fail(String.format("encountered CLinkedListException(\"%s\") when we should not have", e.getMessage()));
			}
		}
		
		//removeLast
		for (int i = c_ary_expected.length; i > 0; i--) {
			ll.removeLast();
			
			if (i != 1) {
				i_expect = i - 1 /*for the one we just removed*/;
				i_result = ll.getSize();
				assertEquals(
					i_expect, 
					i_result
				);
				
				c_expect = c_ary_expected[i-(1 + 1 /*for the one we just removed*/)];
				c_result = ll.getTail().next();
				assertEquals(
					c_expect,
					c_result
				);
				
				c_expect = c_ary_expected[0];
				c_result = ll.getHead().next();
				assertEquals(
					c_expect,
					c_result
				);
				
				try {
					c_expect = c_ary_expected[i-(1 + 1 /*for the one we just removed*/)];
					c_result = ll.get(ll.getSize()-1);
					assertEquals(
						c_expect,
						c_result
					);
					
					c_expect = c_ary_expected[0];
					c_result = ll.get(0);
					assertEquals(
						c_expect,
						c_result
					);
				} catch (CLinkedListException e) {
					e.printStackTrace();
					fail(String.format("encountered CLinkedListException(\"%s\") when we should not have", e.getMessage()));
				}
			} else {
				i_expect = 0;
				i_result = ll.getSize();
				assertEquals(
					i_expect, 
					i_result
				);
			}
		}
	}
}
