package com.sacontreras.library.algorithms.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sacontreras.library.common.TestCaseData;
import com.sacontreras.library.datastructures.array.MockArrays;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.MockTrees;
import com.sacontreras.library.util.Transform;

public class Algorithms_MergeSort_Test {
	private static class MergeSortTestCaseData implements TestCaseData {
		public final Integer[] unsorted;
		public final Integer[] sorted;
		public MergeSortTestCaseData(Integer[] unsorted, Integer[] sorted) {
			this.unsorted = unsorted;
			this.sorted = sorted;
		}
		
		@Override
		public String toString() {
			return String.format(
				"{%s, %s}", 
				unsorted == null ? null : Arrays.toString(unsorted),
				sorted == null ? null : Arrays.toString(sorted)
			);
		}
	}
	
	

	
	public static Stream<MergeSortTestCaseData> nullTestCaseDataProvider() {
		return Stream.of(new MergeSortTestCaseData(null, null));
	}
	@ParameterizedTest(name = "testMergeSortOnNullShouldThrow[{index}] on {0}")
	@MethodSource("nullTestCaseDataProvider")
	void testMergeSortOnNullShouldThrow(MergeSortTestCaseData testCaseData) {
		Assertions.assertThrows(
			NullPointerException.class, 
			() -> {
				MergeSort.<Integer>execute(testCaseData.unsorted);
			}
		);
	}
	
	
	public static Stream<MergeSortTestCaseData> emptyOrOneElementTestCaseDataProvider() {
		return Stream.of(
			new MergeSortTestCaseData(new Integer[]{}, new Integer[]{}),
			new MergeSortTestCaseData(new Integer[]{0}, new Integer[]{0})
		);
	}
	@ParameterizedTest(name = "testMergeSortOnArrayWithZeroOrOneElementShouldReturnOriginal[{index}] on {0}")
	@MethodSource("emptyOrOneElementTestCaseDataProvider")
	void testMergeSortOnArrayWithZeroOrOneElementShouldReturnOriginal(MergeSortTestCaseData testCaseData) {
		Integer[] sorted = MergeSort.<Integer>execute(testCaseData.unsorted);
		Assertions.assertArrayEquals(testCaseData.sorted, sorted);
	}
	
	
	public static Stream<MergeSortTestCaseData> regularTestCaseDataProvider() {
		return Stream.of(
			new MergeSortTestCaseData(new Integer[]{0,9,2,0,9,7,5,6}, new Integer[]{0,0,2,5,6,7,9,9}),
			new MergeSortTestCaseData(new Integer[]{7,0,2,5,9,-1,-73}, new Integer[] {-73,-1,0,2,5,7,9})
		);
	}
	@ParameterizedTest(name = "testMergeSortOnRegularUnsortedArrayShouldReturnSorted[{index}] on {0}")
	@MethodSource("regularTestCaseDataProvider")
	void testMergeSortOnRegularUnsortedArrayShouldReturnSorted(MergeSortTestCaseData testCaseData) {
		Integer[] sorted = MergeSort.<Integer>execute(testCaseData.unsorted);
		Assertions.assertArrayEquals(testCaseData.sorted, sorted);
	}
	

	
	
	//these are all old and defunct - both the implementation and tests will be refactored
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
