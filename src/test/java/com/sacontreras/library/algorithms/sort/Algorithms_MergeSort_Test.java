package com.sacontreras.library.algorithms.sort;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sacontreras.library.common.TestCaseData;

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
		Integer[] sorted = Arrays.copyOf(testCaseData.unsorted, testCaseData.unsorted.length);
		MergeSort.<Integer>execute(sorted);
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
		Integer[] sorted = Arrays.copyOf(testCaseData.unsorted, testCaseData.unsorted.length);
		MergeSort.<Integer>execute(sorted);
		Assertions.assertArrayEquals(testCaseData.sorted, sorted);
	}
}
