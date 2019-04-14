package com.sacontreras.library.algorithms.sort.mergesort.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sacontreras.library.common.TestCaseData;
import com.sacontreras.library.common.TestCaseDataStreamBuilder;

public class TestInplaceArrayMergeSorter {
	private static class MergeSortTestCaseData implements TestCaseData {
		public final ArrayMergeSorter<Integer> mergeSorterImpl;
		public final Integer[] unsorted;
		public final Integer[] sorted;

		public MergeSortTestCaseData(ArrayMergeSorter<Integer> mergeSorterImpl, Integer[] unsorted, Integer[] sorted) {
			this.mergeSorterImpl = mergeSorterImpl;
			this.unsorted = unsorted;
			this.sorted = sorted;
		}

		@Override
		public String toString() {
			return String.format("{%s, %s, %s}", mergeSorterImpl.getClass().getSimpleName(),
					unsorted == null ? null : Arrays.toString(unsorted),
					sorted == null ? null : Arrays.toString(sorted));
		}
	}

	private static List<ArrayMergeSorter<Integer>> mergeSorterImpls;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		mergeSorterImpls = new ArrayList<>() {
			{
				add(new InplaceArrayMergeSorter<Integer>());
			}
		};
	}

	private static class MergeSortTestCaseDataStreamBuilder
			extends TestCaseDataStreamBuilder<MergeSortTestCaseData, ArrayMergeSorter<Integer>> {
		public MergeSortTestCaseDataStreamBuilder() {
			super(mergeSorterImpls);
		}

		public Builder<MergeSortTestCaseData> add(Integer[] unsorted, Integer[] expectSorted) {
			for (ArrayMergeSorter<Integer> mergeSorter : mergeSorterImpls)
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
		testCaseDataStreamBuilder.add(new Integer[] {}, new Integer[] {});
		testCaseDataStreamBuilder.add(new Integer[] { 0 }, new Integer[] { 0 });
		return testCaseDataStreamBuilder.build();
	}

	@ParameterizedTest(name = "testMergeSortOnArrayWithZeroOrOneElementShouldReturnOriginal[{index}] on {0}")
	@MethodSource("emptyOrOneElementTestCaseDataProvider")
	void testMergeSortOnArrayWithZeroOrOneElementShouldReturnOriginal(MergeSortTestCaseData testCaseData) {
		Integer[] sorted = Arrays.copyOf(testCaseData.unsorted, testCaseData.unsorted.length);
		testCaseData.mergeSorterImpl.mergeSort(sorted);
		Assertions.assertArrayEquals(testCaseData.sorted, sorted);
	}

	public static Stream<MergeSortTestCaseData> regularTestCaseDataProvider() {
		MergeSortTestCaseDataStreamBuilder testCaseDataStreamBuilder = new MergeSortTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add(new Integer[] { 0, 9, 2, 0, 9, 7, 5, 6 },
				new Integer[] { 0, 0, 2, 5, 6, 7, 9, 9 });
		testCaseDataStreamBuilder.add(new Integer[] { 7, 0, 2, 5, 9, -1, -73 },
				new Integer[] { -73, -1, 0, 2, 5, 7, 9 });
		return testCaseDataStreamBuilder.build();
	}

	@ParameterizedTest(name = "testMergeSortOnRegularUnsortedArrayShouldReturnSorted[{index}] on {0}")
	@MethodSource("regularTestCaseDataProvider")
	void testMergeSortOnRegularUnsortedArrayShouldReturnSorted(MergeSortTestCaseData testCaseData) {
		Integer[] sorted = Arrays.copyOf(testCaseData.unsorted, testCaseData.unsorted.length);
		testCaseData.mergeSorterImpl.mergeSort(sorted);
		Assertions.assertArrayEquals(testCaseData.sorted, sorted);
	}
}
