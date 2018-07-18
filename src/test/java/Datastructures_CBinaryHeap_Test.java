import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.test.mock.Arrays;
import com.sacontreras.library.datastructures.test.mock.Heaps;

public class Datastructures_CBinaryHeap_Test {
	
	@Test
	@DisplayName("test_CBinaryHeap")
	public void test_CBinaryHeap() {
		Heaps.CIntegerBinaryHeap int_bin_heap = new Heaps.CIntegerBinaryHeap();
		boolean 
			b_expect = true,
			b_result = int_bin_heap.isEmpty();
		assertEquals(
			b_expect,
			b_result
		);
		int 
			i_expect = 0,
			i_result = int_bin_heap.size();
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = int_bin_heap.height();
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = true;
		b_result = int_bin_heap.isFull();
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = int_bin_heap.isComplete();
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = int_bin_heap.isHeightBalanced();
		assertEquals(
			b_expect,
			b_result
		);
		
				
		//manually add items one-by-one from i_ary_expected_preorder ({30, 20, 10, 5, 15, 25, 45, 35, 40, 55, 50})
		//	this should yield the following insertion iterations:
		//		Graphically:
		//			ITERATION 0: insert 30
		//			  30
		//			ITERATION 1: insert 20
		//		      20
		//	   	    /     
		//	       /	      
		//	      30       
		//			ITERATION 2: insert 10
		//		      10
		//	   	   /      \  
		//	      /	       \
		//	     30        20
		//			ITERATION 3: insert 5
		//		      5
		//	   	   /      \  
		//	      /	       \
		//	     10        20
		//		/
		//	   /
		//	  30
		//			ITERATION 4: insert 15
		//		      5
		//	   	   /      \  
		//	      /	       \
		//	     10        20
		//		/  \
		//	   /    \
		//	  30    15
		//			ITERATION 5: insert 25
		//		      5
		//	   	   /      \  
		//	      /	       \
		//	     10        20
		//		/  \      /
		//	   /    \    /
		//	  30    15  25
		//			ITERATION 6: insert 45
		//		      5
		//	   	   /      \  
		//	      /	       \
		//	     10        20
		//		/  \      /  \
		//	   /    \    /    \
		//	  30    15  25    45
		//			ITERATION 7: insert 35
		//		      5
		//	   	   /      \  
		//	      /	       \
		//	     10        20
		//		/  \      /  \
		//	   /    \    /    \
		//	  30    15  25    45
		//   /
		//  /
		// 35
		//			ITERATION 8: insert 40
		//		      5
		//	   	   /      \  
		//	      /	       \
		//	     10        20
		//		/  \      /  \
		//	   /    \    /    \
		//	  30    15  25    45
		//   /  \
		//  /    \
		// 35    40
		//			ITERATION 9: insert 55
		//		       5
		//	   	   /       \  
		//	      /	        \
		//	     10         20
		//		/   \      /  \
		//	   /     \    /    \
		//	  30     15  25    45
		//   /  \     /
		//  /    \   /
		// 35    40 55
		//			ITERATION 10: insert 50
		//		        5
		//	   	    /       \  
		//	       /	     \
		//	      10         20
		//		/    \      /  \
		//	   /      \    /    \
		//	  30      15  25    45
		//   /  \     / \
		//  /    \   /   \
		// 35    40 55   50
		
		
		Heaps.CIntegerBinaryHeapTraversalListener traversalListener = null;
		ArrayList<Integer> i_al_expected_preorder = new ArrayList<Integer>();
		List<Integer> i_list_expected_inorder = null;
		for (int i = 0; i < Arrays.i_ary_expected_preorder.length; i++) {
			int val = Arrays.i_ary_expected_preorder[i];
			int_bin_heap.add(val);
			
			//so we can visualize its structure after add (after bubbleup)
			traversalListener = new Heaps.CIntegerBinaryHeapTraversalListener(String.format("CIntegerBinaryHeap-iteration-%d", i), "levelOrder");
			int_bin_heap.traverseLevelOrder(traversalListener);
			
			i_expect = i + 1;
			i_result = int_bin_heap.size();
			assertEquals(
				i_expect,
				i_result
			);
			
			//regardless, we always expect a complete and height-balanced tree
			b_expect = true;
			b_result = int_bin_heap.isComplete();
			assertEquals(
				b_expect,
				b_result
			);
			b_expect = true;
			b_result = int_bin_heap.isHeightBalanced();
			assertEquals(
				b_expect,
				b_result
			);
			
			//ensure heap (ordering) property
			//	first add to a collection (so that we may use sorting apis)
			i_al_expected_preorder.add(val);
			//	now sort based on natural order
			i_list_expected_inorder = i_al_expected_preorder
				.stream()
				.sorted()
				.collect(Collectors.toList());
			//	now take the min of that collection
			i_expect = Collections.min(i_list_expected_inorder);
			i_result = int_bin_heap.peek();
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		
		b_expect = false;
		b_result = int_bin_heap.isEmpty();
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = int_bin_heap.isFull();
		assertEquals(
			b_expect,
			b_result
		);
		i_expect = 3;
		i_result = int_bin_heap.height();
		assertEquals(
			i_expect,
			i_result
		);
		
		
		//expect polling heap to result in-order values
		final CLinkedListQueue<Integer> q_priority_order = new CLinkedListQueue<Integer>();
		while (!int_bin_heap.isEmpty() ) {
			Integer 
				val = int_bin_heap.poll();
			System.out.println(String.format("CIntegerBinaryHeap::poll: %d", val));
			q_priority_order.enqueue(val);
		}
		int n = 0;
		i_expect = -1;
		i_result = -1;
		while (!q_priority_order.isEmpty()) {
			i_expect = Arrays.i_ary_expected_inorder[n];
			i_result = q_priority_order.poll();
			assertEquals(
				i_expect,
				i_result
			);
			n++;
		}
		
		
		//for curiosity's sake...
		traversalListener = new Heaps.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "inOrder");
		int_bin_heap.traverseInOrder(traversalListener);
		traversalListener = new Heaps.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "preOrder");
		int_bin_heap.traversePreOrder(traversalListener);
		traversalListener = new Heaps.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "postOrder");
		int_bin_heap.traversePostOrder(traversalListener);
	}
}
