import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
	}
}
