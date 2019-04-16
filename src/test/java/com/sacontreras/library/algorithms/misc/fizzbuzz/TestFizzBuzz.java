package com.sacontreras.library.algorithms.misc.fizzbuzz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestFizzBuzz {
	private static class TestFizzBuzzListener implements FizzBuzzListener {
		public final List<String> fizzbuzzLines;
		public TestFizzBuzzListener(List<String> fizzbuzzLines) {
			this.fizzbuzzLines = fizzbuzzLines;
		}
		
		@Override
		public void OnItem(int i, String output) {
			fizzbuzzLines.add(output);
		}
	}
	
	private static Stream<List<String>> expectedFizzBuzzArrayProvider() {
		return Stream.of(Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz",
			"13", "14", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz", "Fizz", "22", "23", "Fizz", "Buzz", "26",
			"Fizz", "28", "29", "FizzBuzz", "31", "32", "Fizz", "34", "Buzz", "Fizz", "37", "38", "Fizz", "Buzz",
			"41", "Fizz", "43", "44", "FizzBuzz", "46", "47", "Fizz", "49", "Buzz", "Fizz", "52", "53", "Fizz",
			"Buzz", "56", "Fizz", "58", "59", "FizzBuzz", "61", "62", "Fizz", "64", "Buzz", "Fizz", "67", "68",
			"Fizz", "Buzz", "71", "Fizz", "73", "74", "FizzBuzz", "76", "77", "Fizz", "79", "Buzz", "Fizz", "82",
			"83", "Fizz", "Buzz", "86", "Fizz", "88", "89", "FizzBuzz", "91", "92", "Fizz", "94", "Buzz", "Fizz",
			"97", "98", "Fizz", "Buzz")
		);
	}
	@ParameterizedTest(name = "testFizzBuzz[{index}]")
	@MethodSource("expectedFizzBuzzArrayProvider")
	public void testFizzBuzz(List<String> expected) {
		TestFizzBuzzListener listener = new TestFizzBuzzListener(new ArrayList<>());
		FizzBuzz fizzBuzz = new FizzBuzz(listener);
		fizzBuzz.run();
		Assertions.assertIterableEquals(expected, listener.fizzbuzzLines);
	}
}
