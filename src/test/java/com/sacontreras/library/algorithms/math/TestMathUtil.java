package com.sacontreras.library.algorithms.math;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.sacontreras.library.common.TestCaseData;

public class TestMathUtil {
	private static class ScalarIntegerTestCaseData implements TestCaseData {
		public final int n;
		public final int expect;
		public ScalarIntegerTestCaseData(int n, int expect) {
			this.n = n;
			this.expect = expect;
		}
		@Override
		public String toString() {
			return String.format(
				"{%d, %d}", 
				n,
				expect
			);
		}
	}
	
	private static Stream<ScalarIntegerTestCaseData> negativeTestCaseDataProvider() {
		return Stream.of(
			new ScalarIntegerTestCaseData(-1, -1), 
			new ScalarIntegerTestCaseData(-356, -1),
			new ScalarIntegerTestCaseData(-Integer.MAX_VALUE, -1)
		);
	}
	@ParameterizedTest(name = "testFibonacciOnNegativeShouldThrow[{index}] on {0}")
	@MethodSource("negativeTestCaseDataProvider")
	public void testFibonacciOnNegativeShouldThrow(ScalarIntegerTestCaseData testCaseData) {
		Assertions.assertThrows(
			IllegalArgumentException.class, 
			() -> {
				MathUtil.fibonacci(testCaseData.n);
			}
		);
	}
	
	private static Stream<ScalarIntegerTestCaseData> validFibonacciTestCaseDataProvider() {
		return Stream.of(
			new ScalarIntegerTestCaseData(0, 0), 
			new ScalarIntegerTestCaseData(1, 1),
			new ScalarIntegerTestCaseData(2, 1),
			new ScalarIntegerTestCaseData(3, 2),
			new ScalarIntegerTestCaseData(4, 3),
			new ScalarIntegerTestCaseData(5, 5),
			new ScalarIntegerTestCaseData(6, 8),
			new ScalarIntegerTestCaseData(13, 233),
			new ScalarIntegerTestCaseData(20, 6765)
		);
	}
	@ParameterizedTest(name = "testFibonacciOnValidShouldReturnExpectedFibonacciNumber[{index}] on {0}")
	@MethodSource("validFibonacciTestCaseDataProvider")
	public void testFibonacciOnValidShouldReturnExpectedFibonacciNumber(ScalarIntegerTestCaseData testCaseData) {
		Assertions.assertEquals(testCaseData.expect, MathUtil.fibonacci(testCaseData.n));
	}
	
	
	
	
	@ParameterizedTest(name = "testFactorialOnNegativeShouldThrow[{index}] on {0}")
	@MethodSource("negativeTestCaseDataProvider")
	public void testFactorialOnNegativeShouldThrow(ScalarIntegerTestCaseData testCaseData) {
		Assertions.assertThrows(
			IllegalArgumentException.class, 
			() -> {
				MathUtil.factorial(testCaseData.n);
			}
		);
	}
	
	private static Stream<ScalarIntegerTestCaseData> validFactorialTestCaseDataProvider() {
		return Stream.of(
			new ScalarIntegerTestCaseData(0, 1), 
			new ScalarIntegerTestCaseData(1, 1),
			new ScalarIntegerTestCaseData(2, 2),
			new ScalarIntegerTestCaseData(3, 6),
			new ScalarIntegerTestCaseData(4, 24),
			new ScalarIntegerTestCaseData(5, 120),
			new ScalarIntegerTestCaseData(10, 3628800)
		);
	}
	@ParameterizedTest(name = "testFactorialOnValidShouldReturnExpectedFactorialized[{index}] on {0}")
	@MethodSource("validFactorialTestCaseDataProvider")
	public void testFactorialOnValidShouldReturnExpectedFactorialized(ScalarIntegerTestCaseData testCaseData) {
		Assertions.assertEquals(testCaseData.expect, MathUtil.factorial(testCaseData.n));
	}
}
