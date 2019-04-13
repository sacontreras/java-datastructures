package com.sacontreras.library.algorithms.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.array.MockArrays;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.MockTrees;
import com.sacontreras.library.util.Transform;

public class Algorithms_MergeSort_Test {

	@Test
	@DisplayName("test_MergeSort")
	public void test_MergeSort() {
		MockTrees.CIntegerBinarySearchTree intBinarySearchTree = MockTrees.CIntegerBinarySearchTree
				.fromArray(MockArrays.i_ary_expected_preorder);

		/// in-order traversal
		MockTrees.CIntegerBinaryTreeTraversalListener traversalListener = new MockTrees.CIntegerBinaryTreeTraversalListener(
				MockTrees.CIntegerBinarySearchTree.class.getSimpleName(), "inOrder");
		intBinarySearchTree.traverseInOrder(traversalListener);
		Integer[] int_ary = Transform.to_array(traversalListener.q_visit_order);
		int i_expect = MockArrays.i_ary_expected_inorder.length;
		int i_result = int_ary.length;
		assertEquals(i_expect, i_result);
		for (int i = 0; i < MockArrays.i_ary_expected_inorder.length; i++) {
			i_expect = MockArrays.i_ary_expected_inorder[i];
			i_result = int_ary[i];
			assertEquals(i_expect, i_result);
		}

		// now sort i_ary_expected_preorder using mergesort and expect
		// i_ary_expected_inorder values...

		// ... using array and comparator (data need not be inherently comparable in
		// this case... the comparator takes care of this for us)
		MockTrees.CIntegerComparator binarysearchcomparator = new MockTrees.CIntegerComparator();
		Integer[] ary_expected_preorder__sorted_inorder = MergeSort.<Integer>execute(MockArrays.i_ary_expected_preorder,
				binarysearchcomparator);
		for (int i = 0; i < MockArrays.i_ary_expected_inorder.length; i++) {
			i_expect = MockArrays.i_ary_expected_inorder[i];
			i_result = ary_expected_preorder__sorted_inorder[i];
			assertEquals(i_expect, i_result);
		}

		// ...using queue and comparator (data need not be inherently comparable in this
		// case... the comparator takes care of this for us)
		CLinkedListQueue<Integer> q_expected_preorder__sorted_inorder = MergeSort
				.<Integer>execute(Transform.to_queue(MockArrays.i_ary_expected_preorder), binarysearchcomparator);
		for (int i = 0; i < MockArrays.i_ary_expected_inorder.length; i++) {
			i_expect = MockArrays.i_ary_expected_inorder[i];
			i_result = q_expected_preorder__sorted_inorder.poll();
			assertEquals(i_expect, i_result);
		}

		// ...using array of comparables
		ary_expected_preorder__sorted_inorder = MergeSort.<Integer>execute(MockArrays.i_ary_expected_preorder);
		for (int i = 0; i < MockArrays.i_ary_expected_inorder.length; i++) {
			i_expect = MockArrays.i_ary_expected_inorder[i];
			i_result = ary_expected_preorder__sorted_inorder[i];
			assertEquals(i_expect, i_result);
		}

		// ...using queue of comparables
		q_expected_preorder__sorted_inorder = MergeSort
				.<Integer>execute(Transform.to_queue(MockArrays.i_ary_expected_preorder));
		for (int i = 0; i < MockArrays.i_ary_expected_inorder.length; i++) {
			i_expect = MockArrays.i_ary_expected_inorder[i];
			i_result = q_expected_preorder__sorted_inorder.poll();
			assertEquals(i_expect, i_result);
		}
	}
}
