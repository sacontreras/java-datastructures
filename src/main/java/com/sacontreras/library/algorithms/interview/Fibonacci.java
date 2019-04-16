package com.sacontreras.library.algorithms.interview;

import com.sacontreras.library.algorithms.math.MathUtil;

public class Fibonacci {
	public static void main(String[] args) {
		int indexes[] = {0, 1, 2, 8, 13};
		int fibonacciNumbers[] = {0, 1, 1, 21, 233};
		
		for (int i = 0; i < indexes.length; i++) {
			System.out.printf("expect %d Fibonacci number to be %d and got: %d\n", indexes[i], fibonacciNumbers[i], MathUtil.fibonacci(indexes[i]));
		}
	}
}
