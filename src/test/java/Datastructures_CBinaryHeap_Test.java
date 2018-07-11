import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.test.mock.Arrays;
import com.sacontreras.library.datastructures.test.mock.Heaps;

public class Datastructures_CBinaryHeap_Test {
	
	@Test
	@DisplayName("test_CBinaryHeap")
	public void test_CBinaryHeap() {
		Heaps.CIntegerBinaryHeap int_bin_heap = Heaps.CIntegerBinaryHeap.fromArray(Arrays.i_ary_expected_preorder);
		
		Heaps.CIntegerBinaryHeapTraversalListener traversalListener = new Heaps.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "preOrder");
		int_bin_heap.traversePreOrder(traversalListener);
		
		traversalListener = new Heaps.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "inOrder");
		int_bin_heap.traverseInOrder(traversalListener);
		
		traversalListener = new Heaps.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "postOrder");
		int_bin_heap.traversePostOrder(traversalListener);
		
		traversalListener = new Heaps.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "levelOrder");
		int_bin_heap.traverseLevelOrder(traversalListener);
		
		final CLinkedListQueue<Integer> q_priority_order = new CLinkedListQueue<Integer>();
		while (!int_bin_heap.isEmpty() ) {
			Integer 
				val = int_bin_heap.poll();
			System.out.println(String.format("CIntegerBinaryHeap::poll: %d", val));
			q_priority_order.enqueue(val);
		}
		int 
			n = 0,
			i_expect = -1,
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
	}
}
