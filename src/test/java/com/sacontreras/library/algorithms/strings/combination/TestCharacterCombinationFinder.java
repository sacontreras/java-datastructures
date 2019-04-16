package com.sacontreras.library.algorithms.strings.combination;

import java.util.ArrayList;
import java.util.HashMap;
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


public class TestCharacterCombinationFinder {
	private static class CharacterCombinationTestCaseData implements TestCaseData {
		public final CharacterCombinationFinder characterCombinationFinder;
		public final String string;
		public final TreeSet<String> combinations;
		public CharacterCombinationTestCaseData(CharacterCombinationFinder characterCombinationFinder, String string,
				TreeSet<String> combinations) {
			this.characterCombinationFinder = characterCombinationFinder;
			this.string = string;
			this.combinations = combinations;
		}
		@Override
		public String toString() {
			return String.format(
				"{%s, \"%s\", %s}", 
				characterCombinationFinder.getClass().getSimpleName(),
				string == null ? null : "\"" + string + "\"",
				combinations
			);
		}
	}

	private static List<CharacterCombinationFinder> combinationFinderImpls;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		combinationFinderImpls = new ArrayList<>() {{
			add(new CCharacterCombinationFinder());
		}};
	}
	
	private static class CharacterCombinationTestCaseDataStreamBuilder extends TestCaseDataStreamBuilder<CharacterCombinationTestCaseData, CharacterCombinationFinder> {
		public CharacterCombinationTestCaseDataStreamBuilder(List<CharacterCombinationFinder> implsUnderTest) {
			super(implsUnderTest);
		}
		public Builder<CharacterCombinationTestCaseData> add(String string, TreeSet<String> expectCombos) {
			for (CharacterCombinationFinder characterCombinationFinder : implsUnderTest)
				add(new CharacterCombinationTestCaseData(characterCombinationFinder, string, expectCombos));
			return theBuilder;
		}
	}

	
	
	
	private static Stream<CharacterCombinationTestCaseData> nullStringTestCaseDataProvider() {
		CharacterCombinationTestCaseDataStreamBuilder testCaseDataStreamBuilder = new CharacterCombinationTestCaseDataStreamBuilder(combinationFinderImpls);
		testCaseDataStreamBuilder.add(null, null);
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindCharacterCombinationationsOnNullShouldThrow[{index}] on {0}")
	@MethodSource("nullStringTestCaseDataProvider")
	void testFindCharacterCombinationationsOnNullShouldThrow(CharacterCombinationTestCaseData testCaseData) {
		Assertions.assertThrows(
			NullPointerException.class, 
			() -> {
				testCaseData.characterCombinationFinder.findCharacterCombination(testCaseData.string);
			}
		);
	}
	
	
	
	
	private static class TreeSetBuilderCharacterCombinationFinderListener implements CharacterCombinationFinderListener {
		public final HashMap<String, TreeSet<String>> stringCombinationsMap = new HashMap<>();

		@Override
		public void OnCharacterCombinationFound(String string, String combination) {
			System.out.printf("\"%s\" combination found: \"%s\"\n", string, combination);
			TreeSet<String> combinations = stringCombinationsMap.get(string);
			if (combinations == null) {
				combinations = new TreeSet<>();
				stringCombinationsMap.put(string, combinations);
			}
			combinations.add(combination);
		}
	}
	
	private static Stream<CharacterCombinationTestCaseData> emptyStringTestCaseDataProvider() {
		CharacterCombinationTestCaseDataStreamBuilder testCaseDataStreamBuilder = new CharacterCombinationTestCaseDataStreamBuilder(combinationFinderImpls);
		testCaseDataStreamBuilder.add("", new TreeSet<String>(){{add("");}});
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindCharacterCombinationationsOnEmptyStringShouldReturnSetWithOnlyEmptyString[{index}] on {0}")
	@MethodSource("emptyStringTestCaseDataProvider")
	void testFindCharacterCombinationationsOnEmptyStringShouldReturnSetWithOnlyEmptyString(CharacterCombinationTestCaseData testCaseData) {
		TreeSetBuilderCharacterCombinationFinderListener listener = new TreeSetBuilderCharacterCombinationFinderListener();
		testCaseData.characterCombinationFinder.findCharacterCombination(testCaseData.string, listener);
		TreeSet<String> combinations = listener.stringCombinationsMap.get(testCaseData.string);
		Assertions.assertEquals(testCaseData.combinations, combinations, String.format("GOT: %s", combinations));
	}
	
	
	
	
	private static Stream<CharacterCombinationTestCaseData> validStringTestCaseDataProvider() {
		CharacterCombinationTestCaseDataStreamBuilder testCaseDataStreamBuilder = new CharacterCombinationTestCaseDataStreamBuilder(combinationFinderImpls);
		
		testCaseDataStreamBuilder.add(
			"a", 
			new TreeSet<String>(){{
				add("a");
			}}
		);
		testCaseDataStreamBuilder.add(
			"ab", 
			new TreeSet<String>(){{
				//2-choose-1
				add("a");
				add("b");
				
				//2-choose-2
				add("ab");
			}}
		);
		testCaseDataStreamBuilder.add(
			"abc", 
			new TreeSet<String>(){{
				//3-choose-1
				add("a");
				add("b");
				add("c");
				
				//3-choose-2
				add("ab");
				add("ac");
				add("bc");
				
				//3-choose-1
				add("abc");
			}}
		);
		
		return testCaseDataStreamBuilder.build();
	}
	@ParameterizedTest(name = "testFindCharacterCombinationationsOnValidStringShouldReturnExpectedCombinationsSet[{index}] on {0}")
	@MethodSource("validStringTestCaseDataProvider")
	void testFindCharacterCombinationationsOnValidStringShouldReturnExpectedCombinationsSet(CharacterCombinationTestCaseData testCaseData) {
		TreeSetBuilderCharacterCombinationFinderListener listener = new TreeSetBuilderCharacterCombinationFinderListener();
		testCaseData.characterCombinationFinder.findCharacterCombination(testCaseData.string, listener);
		TreeSet<String> combinations = listener.stringCombinationsMap.get(testCaseData.string);
		Assertions.assertEquals(testCaseData.combinations, combinations, String.format("GOT: %s", combinations));
	}
}
