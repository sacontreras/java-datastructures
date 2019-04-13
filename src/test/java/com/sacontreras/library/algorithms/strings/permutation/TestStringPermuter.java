package com.sacontreras.library.algorithms.strings.permutation;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sacontreras.library.common.TestCaseData;
import com.sacontreras.library.common.TestCaseDataStreamBuilder;

public class TestStringPermuter {
	private static class StringPermutationTestCaseData implements TestCaseData {
		public final StringPermuter stringPermuter;
		public final String string;
		public final TreeSet<String> permutations;

		public StringPermutationTestCaseData(StringPermuter stringPermuter, String string, TreeSet<String> permutations) {
			this.stringPermuter = stringPermuter;
			this.string = string;
			this.permutations = permutations;
		}

		@Override
		public String toString() {
			return String.format("{%s, %s, %s}", 
				stringPermuter.getClass().getSimpleName(),
				string != null ? "\"" + string + "\"" : string,
				permutations);
		}
	}
	
	private static class TreeSetBuilderStringPermuterListener implements StringPerumuterListener {
		public final TreeSet<String> permutations = new TreeSet<>();

		@Override
		public void OnStringPermuted(String string, String permutation) {
			permutations.add(permutation);
		}
	}
	
	private static List<StringPermuter> stringPermuterImpls;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		stringPermuterImpls = new ArrayList<>() {{
			add(new BacktrackingStringPermuter());
		}};
	}
	
	private static class StringPermutationTestCaseDataStreamBuilder extends TestCaseDataStreamBuilder<StringPermutationTestCaseData, StringPermuter> {
		public StringPermutationTestCaseDataStreamBuilder(List<StringPermuter> implsUnderTest) {
			super(implsUnderTest);
		}
		public Builder<StringPermutationTestCaseData> add(String string, TreeSet<String> expectPermutations) {
			for (StringPermuter stringPermuter : implsUnderTest)
				add(new StringPermutationTestCaseData(stringPermuter, string, expectPermutations));
			return theBuilder;
		}
	}
	
	
	
	
	private static Stream<StringPermutationTestCaseData> nullTestCaseDataProvider() {
		StringPermutationTestCaseDataStreamBuilder testCaseDataStreamBuilder = new StringPermutationTestCaseDataStreamBuilder(stringPermuterImpls);
		testCaseDataStreamBuilder.add(null, null);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindPermutationsOnNullShouldThrow[{index}] on {0}")
	@MethodSource("nullTestCaseDataProvider")
	void testFindPermutationsOnNullShouldThrow(StringPermutationTestCaseData testCaseData) {
		Assertions.assertThrows(
			NullPointerException.class, 
			() -> {
				testCaseData.stringPermuter.permute(testCaseData.string);
			}
		);
	}
	
	
	private static Stream<StringPermutationTestCaseData> emptyStringTestCaseDataProvider() {
		StringPermutationTestCaseDataStreamBuilder testCaseDataStreamBuilder = new StringPermutationTestCaseDataStreamBuilder(stringPermuterImpls);
		testCaseDataStreamBuilder.add("", new TreeSet<>(){{add("");}});
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindPermutationsOnEmptyStringShouldReturnSetWithOnlyEmptyString[{index}] on {0}")
	@MethodSource("emptyStringTestCaseDataProvider")
	void testFindPermutationsOnEmptyStringShouldReturnSetWithOnlyEmptyString(StringPermutationTestCaseData testCaseData) {
		TreeSetBuilderStringPermuterListener listener = new TreeSetBuilderStringPermuterListener();
		testCaseData.stringPermuter.permute(testCaseData.string, listener);
		Assertions.assertEquals(testCaseData.permutations, listener.permutations);
	}
	
	
	private static Stream<StringPermutationTestCaseData> nonemptyStringTestCaseDataProvider() {
		StringPermutationTestCaseDataStreamBuilder testCaseDataStreamBuilder = new StringPermutationTestCaseDataStreamBuilder(stringPermuterImpls);
		
		testCaseDataStreamBuilder.add(
			"a", 
			new TreeSet<>() {{
				add("a");
			}}
		);
		
		testCaseDataStreamBuilder.add(
			"ab", 
			new TreeSet<>()	{{
				add("ab");
				add("ba");
			}}
		);
		
		testCaseDataStreamBuilder.add(
			"abc", 
			new TreeSet<>()	{{
				add("abc");
				add("acb");
				add("bac");
				add("bca");
				add("cab");
				add("cba");
			}}
		);
		
		testCaseDataStreamBuilder.add(
			"abcd", 
			new TreeSet<>()	{{
				add("abcd");
				add("abdc");
				add("acbd");
				add("acdb");
				add("adbc");
				add("adcb");
				
				add("bacd");
				add("badc");
				add("bcad");
				add("bcda");
				add("bdac");
				add("bdca");
				
				add("cabd");
				add("cadb");
				add("cbad");
				add("cbda");
				add("cdab");
				add("cdba");
				
				add("dabc");
				add("dacb");
				add("dbac");
				add("dbca");
				add("dcab");
				add("dcba");
			}}
		);
		
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindPermutationsOnNonemptyStringShouldReturnSetWithCorrectPermutations[{index}] on {0}")
	@MethodSource("nonemptyStringTestCaseDataProvider")
	void testFindPermutationsOnNonemptyStringShouldReturnSetWithCorrectPermutations(StringPermutationTestCaseData testCaseData) {
		TreeSetBuilderStringPermuterListener listener = new TreeSetBuilderStringPermuterListener();
		testCaseData.stringPermuter.permute(testCaseData.string, listener);
		Assertions.assertEquals(testCaseData.permutations, listener.permutations, String.format("GOT: %s", listener.permutations));
	}
}
