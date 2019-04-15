package com.sacontreras.library.algorithms.strings.reverse;
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


public class TestStringReverser {
	private static class StringReverserTestCaseData implements TestCaseData {
		public final StringReverser stringReverserImpl;
		public final String string;
		public final String expectReversed;
		public StringReverserTestCaseData(StringReverser stringReverserImpl, String string, String expectReversed) {
			this.stringReverserImpl = stringReverserImpl;
			this.string = string;
			this.expectReversed = expectReversed;
		}
		@Override
		public String toString() {
			return String.format(
				"{%s, %s, %s}", 
				stringReverserImpl.getClass().getSimpleName(),
				string == null ? null : "\"" + string + "\"",
				expectReversed == null ? null : "\"" + expectReversed + "\""
			);
		}
	}
	
	private static List<StringReverser> stringReverserImpls;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		stringReverserImpls = new ArrayList<>() {{
			add(new CharacterSwapStringReverser());
			add(new BitwiseCharacterSwapStringReverser());
		}};
	}
	
	private static class StringReverserTestCaseDataStreamBuilder extends TestCaseDataStreamBuilder<StringReverserTestCaseData, StringReverser> {
		public StringReverserTestCaseDataStreamBuilder() {
			super(stringReverserImpls);
		}
		public Builder<StringReverserTestCaseData> add(String string, String expectReversed) {
			for (StringReverser stringReverser : stringReverserImpls)
				add(new StringReverserTestCaseData(stringReverser, string, expectReversed));
			return theBuilder;
		}
	}
	
	
	
	
	private static Stream<StringReverserTestCaseData> nullTestCaseDataProvider() {
		StringReverserTestCaseDataStreamBuilder testCaseDataStreamBuilder = new StringReverserTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add(null, null);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testReverseOnNullShouldThrow[{index}] on {0}")
	@MethodSource("nullTestCaseDataProvider")
	public void testReverseOnNullShouldThrow(StringReverserTestCaseData testCaseData) {
		Assertions.assertThrows(
			NullPointerException.class, 
			() -> {
				testCaseData.stringReverserImpl.reverse(testCaseData.string);
			}
		);
	}
	
	private static Stream<StringReverserTestCaseData> emptyTestCaseDataProvider() {
		StringReverserTestCaseDataStreamBuilder testCaseDataStreamBuilder = new StringReverserTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add("", "");
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testReverseOnEmptyStringShouldReturnEmptyString[{index}] on {0}")
	@MethodSource("emptyTestCaseDataProvider")
	public void testReverseOnEmptyStringShouldReturnEmptyString(StringReverserTestCaseData testCaseData) {
		String reversed = testCaseData.stringReverserImpl.reverse(testCaseData.string);
        Assertions.assertEquals(
    		testCaseData.expectReversed,
    		reversed,
			String.format(
				"%s(\"%s\") should return \"%s\" but returned \"%s\" instead",
				testCaseData.stringReverserImpl.getClass().getSimpleName(), 
				testCaseData.string,
				testCaseData.expectReversed, 
				reversed
			)
		);
	}
	
	private static Stream<StringReverserTestCaseData> validTestCaseDataProvider() {
		StringReverserTestCaseDataStreamBuilder testCaseDataStreamBuilder = new StringReverserTestCaseDataStreamBuilder();
		testCaseDataStreamBuilder.add("Hello World!", "!dlroW olleH");
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testReverseOnValidStringShouldReturnStringReversed[{index}] on {0}")
	@MethodSource("validTestCaseDataProvider")
    public void testReverseOnValidStringShouldReturnStringReversed(StringReverserTestCaseData testCaseData) {
        String reversed = testCaseData.stringReverserImpl.reverse(testCaseData.string);
        Assertions.assertEquals(
    		testCaseData.expectReversed,
    		reversed,
			String.format(
				"%s(\"%s\") should return \"%s\" but returned \"%s\" instead",
				testCaseData.stringReverserImpl.getClass().getSimpleName(), 
				testCaseData.string,
				testCaseData.expectReversed, 
				reversed
			)
		);
    }
}
