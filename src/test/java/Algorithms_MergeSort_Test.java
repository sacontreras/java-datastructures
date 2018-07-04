import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.algorithms.sort.MergeSort;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.CBinarySearchTree;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;
import com.sacontreras.library.util.Transform;

public class Algorithms_MergeSort_Test {
	
	private class CIntegerBinarySearchTree extends CBinarySearchTree<Integer> {}
	
	private class CIntegerBinarySearchTreeTraversalListener implements IBinaryTreeTraversalListener<Integer> {
		private final String order;
		public final CLinkedListQueue<Integer> q_visit_order = new CLinkedListQueue<Integer>();
		
		public CIntegerBinarySearchTreeTraversalListener(final String order) {
			this.order = order;
		}
		
		@Override
		public void onNodeVisted(Integer data) {
			//System.out.println(String.format("CIntegerBinarySearchTreeTraversalListener::onNodeVisted-%s: value: %d", order, data));
			q_visit_order.enqueue(data);
		}

		@Override
		public void onNullNode() {
			//System.out.println(String.format("CIntegerBinarySearchTreeTraversalListener::onNullNode-%s", order));
		}
	}
	
	@Test
	@DisplayName("test_MergeSort")
	public void test_MergeSort() {
		CIntegerBinarySearchTree intBinarySearchTree = new CIntegerBinarySearchTree();
		
		//pre-prder amounts to insertion order
		Integer[] i_ary_expected_preorder = new Integer[] {
			30, 20, 10, 5, 15, 25, 45, 35, 40, 55, 50
		};
		
		for (int i = 0; i < i_ary_expected_preorder.length; i++)
			intBinarySearchTree.insert(i_ary_expected_preorder[i]);
		
		//in-order (left, visit, right):
		//		5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55
		Integer[] i_ary_expected_inorder = new Integer[] {
			5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55
		};
		
		//in-order traversal
		CIntegerBinarySearchTreeTraversalListener intBinarySearchTreeTraversalListener = new CIntegerBinarySearchTreeTraversalListener("inOrder");
		intBinarySearchTree.traverseInOrder(intBinarySearchTreeTraversalListener);
		Integer[] int_ary = Transform.to_array(intBinarySearchTreeTraversalListener.q_visit_order);
		int i_expect = i_ary_expected_inorder.length;
		int i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < i_ary_expected_inorder.length; i++) {
			i_expect = i_ary_expected_inorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//now sort i_ary_expected_preorder using mergesort and expect i_ary_expected_inorder values
		final Integer[] ary_expected_preorder__sorted_inorder = MergeSort.<Integer>execute(i_ary_expected_preorder);
		for (int i = 0; i < i_ary_expected_inorder.length; i++) {
			i_expect = i_ary_expected_inorder[i];
			i_result = ary_expected_preorder__sorted_inorder[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		final CLinkedListQueue<Integer> q_expected_preorder__sorted_inorder = MergeSort.<Integer>execute(Transform.to_queue(i_ary_expected_preorder));
		for (int i = 0; i < i_ary_expected_inorder.length; i++) {
			i_expect = i_ary_expected_inorder[i];
			i_result = q_expected_preorder__sorted_inorder.poll();
			assertEquals(
				i_expect,
				i_result
			);
		}
	}
}
