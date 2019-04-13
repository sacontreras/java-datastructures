import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.algorithms.sort.MergeSort;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.test.mock.Arrays;
import com.sacontreras.library.datastructures.test.mock.Trees;
import com.sacontreras.library.util.Transform;

public class Algorithms_MergeSort_Test {
	
	@Test
	@DisplayName("test_MergeSort")
	public void test_MergeSort() {
		Trees.CIntegerBinarySearchTree intBinarySearchTree = Trees.CIntegerBinarySearchTree.fromArray(Arrays.i_ary_expected_preorder);

        ///in-order traversal
		Trees.CIntegerBinaryTreeTraversalListener traversalListener = new Trees.CIntegerBinaryTreeTraversalListener(Trees.CIntegerBinarySearchTree.class.getSimpleName(), "inOrder");
		intBinarySearchTree.traverseInOrder(traversalListener);
		Integer[] int_ary = Transform.to_array(traversalListener.q_visit_order);
		int i_expect = Arrays.i_ary_expected_inorder.length;
		int i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < Arrays.i_ary_expected_inorder.length; i++) {
			i_expect = Arrays.i_ary_expected_inorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		
		//now sort i_ary_expected_preorder using mergesort and expect i_ary_expected_inorder values...
		
		//... using array and comparator (data need not be inherently comparable in this case... the comparator takes care of this for us)
		Trees.CIntegerComparator binarysearchcomparator = new Trees.CIntegerComparator();
		Integer[] ary_expected_preorder__sorted_inorder = MergeSort.<Integer>execute(Arrays.i_ary_expected_preorder, binarysearchcomparator);
		for (int i = 0; i < Arrays.i_ary_expected_inorder.length; i++) {
			i_expect = Arrays.i_ary_expected_inorder[i];
			i_result = ary_expected_preorder__sorted_inorder[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//...using queue and comparator (data need not be inherently comparable in this case... the comparator takes care of this for us)
		CLinkedListQueue<Integer> q_expected_preorder__sorted_inorder = MergeSort.<Integer>execute(Transform.to_queue(Arrays.i_ary_expected_preorder), binarysearchcomparator);
		for (int i = 0; i < Arrays.i_ary_expected_inorder.length; i++) {
			i_expect = Arrays.i_ary_expected_inorder[i];
			i_result = q_expected_preorder__sorted_inorder.poll();
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//...using array of comparables
		ary_expected_preorder__sorted_inorder = MergeSort.<Integer>execute(Arrays.i_ary_expected_preorder);
		for (int i = 0; i < Arrays.i_ary_expected_inorder.length; i++) {
			i_expect = Arrays.i_ary_expected_inorder[i];
			i_result = ary_expected_preorder__sorted_inorder[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//...using queue of comparables
		q_expected_preorder__sorted_inorder = MergeSort.<Integer>execute(Transform.to_queue(Arrays.i_ary_expected_preorder));
		for (int i = 0; i < Arrays.i_ary_expected_inorder.length; i++) {
			i_expect = Arrays.i_ary_expected_inorder[i];
			i_result = q_expected_preorder__sorted_inorder.poll();
			assertEquals(
				i_expect,
				i_result
			);
		}
	}
}
