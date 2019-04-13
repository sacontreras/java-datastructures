package com.sacontreras.library.algorithms.strings.substring;

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

class TestSubstringFinder {
	private static class SubstringFinderTestCaseData implements TestCaseData {
		public final SubstringFinder substringFinder;
		public final String base;
		public final String sub;
		public final int foundAt;

		public SubstringFinderTestCaseData(SubstringFinder substringFinder, String base, String sub, int foundAt) {
			this.substringFinder = substringFinder;
			this.base = base;
			this.sub = sub;
			this.foundAt = foundAt;
		}

		@Override
		public String toString() {
			return String.format("{%s, %s, %s, %d}", 
				substringFinder.getClass().getSimpleName(),
				base != null ? "\"" + base + "\"" : base,
				sub != null ? "\"" + sub + "\"" : sub, 
				foundAt);
		}
	}

	private static List<SubstringFinder> substringFinderImpls;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		substringFinderImpls = new ArrayList<>() {{
				add(new BruteForceSubstringFinder_chars());
				add(new BruteForceSubstringFinder_str());
			}
		};
	}

	private static class SubstringFinderTestCaseDataStreamBuilder extends TestCaseDataStreamBuilder<SubstringFinderTestCaseData, SubstringFinder> {
		public SubstringFinderTestCaseDataStreamBuilder(List<SubstringFinder> implsUnderTest) {
			super(implsUnderTest);
		}
		public Builder<SubstringFinderTestCaseData> add(String base, String sub, int foundAt) {
			for (SubstringFinder substringFinder : implsUnderTest)
				add(new SubstringFinderTestCaseData(substringFinder, base, sub, foundAt));
			return theBuilder;
		}
	}

	private static Stream<SubstringFinderTestCaseData> nullTestCaseDataProvider() {
		SubstringFinderTestCaseDataStreamBuilder testCaseDataStreamBuilder = new SubstringFinderTestCaseDataStreamBuilder(substringFinderImpls);
		
		testCaseDataStreamBuilder.add(null, "", -1);
		testCaseDataStreamBuilder.add("", null, -1);
		testCaseDataStreamBuilder.add(null, "", -1);
		
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindSubstringOnNullShouldThrow[{index}] on {0}")
	@MethodSource("nullTestCaseDataProvider")
	void testFindSubstringOnNullShouldThrow(SubstringFinderTestCaseData testCaseData) {
		Assertions.assertThrows(NullPointerException.class, () -> {
			testCaseData.substringFinder.FindSubstring(testCaseData.base, testCaseData.sub);
		});
	}

	private static Stream<SubstringFinderTestCaseData> emptySubstringTestCaseDataProvider() {
		SubstringFinderTestCaseDataStreamBuilder testCaseDataStreamBuilder = new SubstringFinderTestCaseDataStreamBuilder(substringFinderImpls);
		
		testCaseDataStreamBuilder.add("", "", 0);
		testCaseDataStreamBuilder.add("a", "", 0);
		testCaseDataStreamBuilder.add(" ", "", 0);
		
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindSubstringWithEmptySubstringShouldReturn0[{index}] on {0}")
	@MethodSource("emptySubstringTestCaseDataProvider")
	void testFindSubstringWithEmptySubstringShouldReturn0(SubstringFinderTestCaseData testCaseData) {
		int foundAt = testCaseData.substringFinder.FindSubstring(testCaseData.base, testCaseData.sub);
		Assertions.assertEquals(testCaseData.foundAt, foundAt);
	}

	private static Stream<SubstringFinderTestCaseData> existingSubstringTestCaseDataProvider() {
		SubstringFinderTestCaseDataStreamBuilder testCaseDataStreamBuilder = new SubstringFinderTestCaseDataStreamBuilder(substringFinderImpls);
		
		testCaseDataStreamBuilder.add("a", "a", 0);
		testCaseDataStreamBuilder.add("ab", "b", 1);
		testCaseDataStreamBuilder.add("abc", "b", 1);
		testCaseDataStreamBuilder.add("abcd", "bc", 1);
		testCaseDataStreamBuilder.add("ab cd", " ", 2);
		testCaseDataStreamBuilder.add("Steven Contreras", "Contreras", 7);
		
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindSubstringWithExistingSubstringShouldReturnCorrectIndex[{index}] on {0}")
	@MethodSource("existingSubstringTestCaseDataProvider")
	void testFindSubstringWithExistingSubstringShouldReturnCorrectIndex(SubstringFinderTestCaseData testCaseData) {
		int foundAt = testCaseData.substringFinder.FindSubstring(testCaseData.base, testCaseData.sub);
		Assertions.assertEquals(testCaseData.foundAt, foundAt);
	}
	
	private static Stream<SubstringFinderTestCaseData> nonexistentSubstringTestCaseDataProvider() {
		SubstringFinderTestCaseDataStreamBuilder testCaseDataStreamBuilder = new SubstringFinderTestCaseDataStreamBuilder(substringFinderImpls);
		
		testCaseDataStreamBuilder.add("a", "b", -1);
		testCaseDataStreamBuilder.add("ab", "c", -1);
		testCaseDataStreamBuilder.add("abc", "cb", -1);
		testCaseDataStreamBuilder.add("abcd", " ", -1);
		testCaseDataStreamBuilder.add("a", "A", -1);
		testCaseDataStreamBuilder.add("Steven Contreras", "ConTreras", -1);
		
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindSubstringWithNonexistentSubstringShouldReturnCorrectIndex[{index}] on {0}")
	@MethodSource("nonexistentSubstringTestCaseDataProvider")
	void testFindSubstringWithNonexistentSubstringShouldReturnCorrectIndex(SubstringFinderTestCaseData testCaseData) {
		int foundAt = testCaseData.substringFinder.FindSubstring(testCaseData.base, testCaseData.sub);
		Assertions.assertEquals(-1, foundAt);
	}
}
