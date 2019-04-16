package com.sacontreras.library.algorithms.strings.nonrepeatingchar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sacontreras.library.common.TestCaseData;
import com.sacontreras.library.common.TestCaseDataStreamBuilder;


public class TestNonRepeatingCharacterFinder {
	private static class FindFirstNonRepeatingCharTestCaseData implements TestCaseData {
		public final NonRepeatingCharFinder nonRepeatingCharFinderImpl;
		public final String string;
		public final int expectIndex;
		public FindFirstNonRepeatingCharTestCaseData(NonRepeatingCharFinder nonRepeatingCharFinderImpl, String string, int expectIndex) {
			this.nonRepeatingCharFinderImpl = nonRepeatingCharFinderImpl;
			this.string = string;
			this.expectIndex = expectIndex;
		}

		@Override
		public String toString() {
			return String.format(
				"{%s, %s, %d}", 
				nonRepeatingCharFinderImpl.getClass().getSimpleName(),
				string == null ? string : "\"" + string + "\"",
				expectIndex
			);
		}
	}
	
	private static List<NonRepeatingCharFinder> nonRepeatingCharFinderImpls;
	@BeforeAll
	private static void setupBeforeClass() {
		nonRepeatingCharFinderImpls = new ArrayList<>() {{
			add(new BruteForceNonRepeatingCharFinder());
		}};
	}
	
	private static class FindFirstNonRepeatingCharTestCaseDataStreamBuilder extends TestCaseDataStreamBuilder<FindFirstNonRepeatingCharTestCaseData, NonRepeatingCharFinder> {
		public FindFirstNonRepeatingCharTestCaseDataStreamBuilder() {
			super(nonRepeatingCharFinderImpls);
		}
		public void add(String string, int expectIndex) {
			for (NonRepeatingCharFinder nonRepeatingCharFinder: nonRepeatingCharFinderImpls)
				add(new FindFirstNonRepeatingCharTestCaseData(nonRepeatingCharFinder, string, expectIndex));
		}
	}
	
	
	
	
	private static Stream<FindFirstNonRepeatingCharTestCaseData> nullTestCaseDataProvider() {
		FindFirstNonRepeatingCharTestCaseDataStreamBuilder testCaseDataStreamBuilder = new FindFirstNonRepeatingCharTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add(null, -1);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindFirstNonRepeatingCharOnNullStringShouldThrow[{index}] on {0}")
	@MethodSource("nullTestCaseDataProvider")
	public void testFindFirstNonRepeatingCharOnNullStringShouldThrow(FindFirstNonRepeatingCharTestCaseData testCaseData) {
		Assertions.assertThrows(
			NullPointerException.class, 
			() -> {
				testCaseData.nonRepeatingCharFinderImpl.findFirst(testCaseData.string);
			}
		);
	}
	
	private static Stream<FindFirstNonRepeatingCharTestCaseData> emptyTestCaseDataProvider() {
		FindFirstNonRepeatingCharTestCaseDataStreamBuilder testCaseDataStreamBuilder = new FindFirstNonRepeatingCharTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add("", -1);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindFirstNonRepeatingCharEmptyStringReturnNegOne[{index}] on {0}")
	@MethodSource("emptyTestCaseDataProvider")
	public void testFindFirstNonRepeatingCharEmptyStringReturnNegOne(FindFirstNonRepeatingCharTestCaseData testCaseData) {
		int index = testCaseData.nonRepeatingCharFinderImpl.findFirst(testCaseData.string);
		Assertions.assertEquals(-1, index);
	}
	
	private static Stream<FindFirstNonRepeatingCharTestCaseData> validTestCaseDataProvider() {
		FindFirstNonRepeatingCharTestCaseDataStreamBuilder testCaseDataStreamBuilder = new FindFirstNonRepeatingCharTestCaseDataStreamBuilder();
		//negative test cases
		testCaseDataStreamBuilder.add("aa", -1);
		testCaseDataStreamBuilder.add("aabb", -1);
		testCaseDataStreamBuilder.add("abba", -1);
		
		//positive test cases
		testCaseDataStreamBuilder.add("a", 0);
		testCaseDataStreamBuilder.add("ab", 0);
		testCaseDataStreamBuilder.add("aab", 2);
		testCaseDataStreamBuilder.add("abbacb", 4);
		testCaseDataStreamBuilder.add("abbacbcd", 7);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindFirstNonRepeatingCharOnValidStringReturnExpectedIndex[{index}] on {0}")
	@MethodSource("validTestCaseDataProvider")
	public void testFindFirstNonRepeatingCharOnValidStringReturnExpectedIndex(FindFirstNonRepeatingCharTestCaseData testCaseData) {
		int index = testCaseData.nonRepeatingCharFinderImpl.findFirst(testCaseData.string);
		Assertions.assertEquals(testCaseData.expectIndex, index);
	}
}
