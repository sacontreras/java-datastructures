package com.sacontreras.library.algorithms.strings.palindrome;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sacontreras.library.common.TestCaseData;
import com.sacontreras.library.common.TestCaseDataStreamBuilder;

public class TestPalindromTester {
	private static class PalindromeTesterTestCaseData implements TestCaseData {
		public final PalindromeTester palindromeTesterImpl;
		public final String string;
		public final boolean expectIsPalindrome;
		public PalindromeTesterTestCaseData(PalindromeTester palindromeTesterImpl, String string, boolean expectIsPalindrome) {
			this.palindromeTesterImpl = palindromeTesterImpl;
			this.string = string;
			this.expectIsPalindrome = expectIsPalindrome;
		}
		@Override
		public String toString() {
			return String.format(
				"{%s, %s, %b}", 
				palindromeTesterImpl.getClass().getSimpleName(),
				string == null ? null : "\"" + string + "\"",
				expectIsPalindrome
			);
		}
	}
	
	private static List<PalindromeTester> palindromeTesterImpls;
	
	@BeforeAll
	private static void setupBeforeClass() {
		palindromeTesterImpls = new ArrayList<>() {{
			add(new CharacterComparisonPalindromTester());
			add(new RegexMatcherPalindromTester());
			add(new RegexMatcherStringBufferReverserPalindromeTester());
		}};
	}
	
	private static class PalindromeTesterTestCaseDataStreamBuilder extends TestCaseDataStreamBuilder<PalindromeTesterTestCaseData, PalindromeTester> {
		public PalindromeTesterTestCaseDataStreamBuilder() {
			super(palindromeTesterImpls);
		}
		public void add(String string, boolean expectIsPalindrome) {
			for (PalindromeTester palindromeTester: palindromeTesterImpls)
				add(new PalindromeTesterTestCaseData(palindromeTester, string, expectIsPalindrome));
		}
	}
	
	private static Stream<PalindromeTesterTestCaseData> nullTestCaseDataProvider() {
		PalindromeTesterTestCaseDataStreamBuilder testCaseDataStreamBuilder = new PalindromeTesterTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add(null, false);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testPalindromTesterOnNullShouldThrow[{index}] on {0}")
	@MethodSource("nullTestCaseDataProvider")
	public void testPalindromTesterOnNullShouldThrow(PalindromeTesterTestCaseData testCaseData) {
		Assertions.assertThrows(
			NullPointerException.class, 
			() -> {
				testCaseData.palindromeTesterImpl.isPalindrome(testCaseData.string);
			}
		);
	}
	
	private static Stream<PalindromeTesterTestCaseData> emptyTestCaseDataProvider() {
		PalindromeTesterTestCaseDataStreamBuilder testCaseDataStreamBuilder = new PalindromeTesterTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add("Able was I ere I saw Elba", true);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testPalindromTesterOnNullShouldThrow[{index}] on {0}")
	@MethodSource("emptyTestCaseDataProvider")
	public void testPalindromonEmptyStringShouldReturnTrue(PalindromeTesterTestCaseData testCaseData) {
		boolean isPalindrome = testCaseData.palindromeTesterImpl.isPalindrome(testCaseData.string);
		Assertions.assertEquals(testCaseData.expectIsPalindrome, isPalindrome);
	}
	
	private static Stream<PalindromeTesterTestCaseData> validTestCaseDataProvider() {
		PalindromeTesterTestCaseDataStreamBuilder testCaseDataStreamBuilder = new PalindromeTesterTestCaseDataStreamBuilder();
		//positive test cases
		testCaseDataStreamBuilder.add("Able was I ere I saw Elba", true);
		testCaseDataStreamBuilder.add("A man, a plan, a canal – Panama", true);
		testCaseDataStreamBuilder.add("Madam, I'm Adam", true);
		testCaseDataStreamBuilder.add("Never odd or even", true);
		testCaseDataStreamBuilder.add("Doc, note: I dissent. A fast never prevents a fatness. I diet on cod", true);
		
		//negative test cases
		testCaseDataStreamBuilder.add("This is not a palindrome!", false);
		testCaseDataStreamBuilder.add("My name is \"Steven Contreras\".", false);
		testCaseDataStreamBuilder.add("All out of negative test cases.", false);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testPalindromonValidStringShouldReturnExpected[{index}] on {0}")
	@MethodSource("validTestCaseDataProvider")
	public void testPalindromonValidStringShouldReturnExpected(PalindromeTesterTestCaseData testCaseData) {
		boolean isPalindrome = testCaseData.palindromeTesterImpl.isPalindrome(testCaseData.string);
		Assertions.assertEquals(testCaseData.expectIsPalindrome, isPalindrome);
	}
}
