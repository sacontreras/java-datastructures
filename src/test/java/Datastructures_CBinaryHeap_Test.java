import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.test.TestUtils;

public class Datastructures_CBinaryHeap_Test {
	
	@Test
	@DisplayName("test_CBinaryHeap")
	public void test_CBinaryHeap() {
		TestUtils.CIntegerBinaryHeap int_bin_heap = TestUtils.CIntegerBinaryHeap.fromArray(TestUtils.i_ary_expected_preorder);
		
		TestUtils.CIntegerBinaryHeapTraversalListener traversalListener = new TestUtils.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "preOrder");
		int_bin_heap.traversePreOrder(traversalListener);
		
		traversalListener = new TestUtils.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "inOrder");
		int_bin_heap.traverseInOrder(traversalListener);
		
		traversalListener = new TestUtils.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "postOrder");
		int_bin_heap.traversePostOrder(traversalListener);
		
		traversalListener = new TestUtils.CIntegerBinaryHeapTraversalListener("CIntegerBinaryHeap", "levelOrder");
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
			i_expect = TestUtils.i_ary_expected_inorder[n];
			i_result = q_priority_order.poll();
			assertEquals(
				i_expect,
				i_result
			);
			n++;
		}
	}
}
