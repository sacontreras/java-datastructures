package com.sacontreras.library.algorithms.sort.mergesort.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sacontreras.library.common.TestCaseData;
import com.sacontreras.library.common.TestCaseDataStreamBuilder;
import com.sacontreras.library.datastructures.linkedlist.CLinkedList;

public class TestLinkedListMergeSort {
	private static class MergeSortTestCaseData implements TestCaseData {
		public final LinkedListMergeSorter<Integer> mergeSorterImpl;
		public final CLinkedList<Integer> unsorted;
		public final CLinkedList<Integer> sorted;

		public MergeSortTestCaseData(LinkedListMergeSorter<Integer> mergeSorterImpl, CLinkedList<Integer> unsorted, CLinkedList<Integer> sorted) {
			this.mergeSorterImpl = mergeSorterImpl;
			this.unsorted = unsorted;
			this.sorted = sorted;
		}

		@Override
		public String toString() {
			return String.format("{%s, %s, %s}", mergeSorterImpl.getClass().getSimpleName(), unsorted, sorted);
		}
	}

	private static List<LinkedListMergeSorter<Integer>> mergeSorterImpls;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		mergeSorterImpls = new ArrayList<>() {
			{
				add(new LinkedListMergeSorter<Integer>());
			}
		};
	}

	private static class MergeSortTestCaseDataStreamBuilder
			extends TestCaseDataStreamBuilder<MergeSortTestCaseData, LinkedListMergeSorter<Integer>> {
		public MergeSortTestCaseDataStreamBuilder() {
			super(mergeSorterImpls);
		}

		public Builder<MergeSortTestCaseData> add(CLinkedList<Integer> unsorted, CLinkedList<Integer> expectSorted) {
			for (LinkedListMergeSorter<Integer> mergeSorter : mergeSorterImpls)
				add(new MergeSortTestCaseData(mergeSorter, unsorted, expectSorted));
			return theBuilder;
		}
	}

	public static Stream<MergeSortTestCaseData> nullTestCaseDataProvider() {
		MergeSortTestCaseDataStreamBuilder testCaseDataStreamBuilder = new MergeSortTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add(null, null);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testMergeSortOnNullShouldThrow[{index}] on {0}")
	@MethodSource("nullTestCaseDataProvider")
	void testMergeSortOnNullShouldThrow(MergeSortTestCaseData testCaseData) {
		Assertions.assertThrows(NullPointerException.class, () -> {
			testCaseData.mergeSorterImpl.mergeSort(testCaseData.unsorted);
		});
	}

	public static Stream<MergeSortTestCaseData> emptyOrOneElementTestCaseDataProvider() {
		MergeSortTestCaseDataStreamBuilder testCaseDataStreamBuilder = new MergeSortTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add(new CLinkedList<>(), new CLinkedList<>());
		testCaseDataStreamBuilder.add(new CLinkedList<>(){{append(0);}}, new CLinkedList<>(){{append(0);}});
		return testCaseDataStreamBuilder.build();
	}

	@ParameterizedTest(name = "testMergeSortOnArrayWithZeroOrOneElementShouldReturnOriginal[{index}] on {0}")
	@MethodSource("emptyOrOneElementTestCaseDataProvider")
	void testMergeSortOnArrayWithZeroOrOneElementShouldReturnOriginal(MergeSortTestCaseData testCaseData) {
		CLinkedList<Integer> sorted = testCaseData.mergeSorterImpl.mergeSort(testCaseData.unsorted);
		Assertions.assertIterableEquals(testCaseData.sorted, sorted);
	}

	public static Stream<MergeSortTestCaseData> regularTestCaseDataProvider() {
		MergeSortTestCaseDataStreamBuilder testCaseDataStreamBuilder = new MergeSortTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add(
			new CLinkedList<Integer>() {{ append(0); append(9); append(2); append(0); append(9); append(7); append(5); append(6); }},
			new CLinkedList<Integer>() {{ append(0); append(0); append(2); append(5); append(6); append(7); append(9); append(9); }}
		);
		testCaseDataStreamBuilder.add(
			new CLinkedList<Integer>() {{ append(7); append(0); append(2); append(5); append(9); append(-1); append(-73); }},
			new CLinkedList<Integer>() {{ append(-73); append(-1); append(0); append(2); append(5); append(7); append(9); }}
		);
		return testCaseDataStreamBuilder.build();
	}

	@ParameterizedTest(name = "testMergeSortOnRegularUnsortedArrayShouldReturnSorted[{index}] on {0}")
	@MethodSource("regularTestCaseDataProvider")
	void testMergeSortOnRegularUnsortedArrayShouldReturnSorted(MergeSortTestCaseData testCaseData) {
		CLinkedList<Integer> sorted = testCaseData.mergeSorterImpl.mergeSort(testCaseData.unsorted);
		Assertions.assertIterableEquals(testCaseData.sorted, sorted);
	}
}
