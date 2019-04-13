package com.sacontreras.library.algorithms.interview;

public class Factorial {
	public static void main(String[] args) {
		int indexes[] = {0, 3, 5, 10};
		long factorial[] = {1, 6, 120, 3628800};
		
		for (int i = 0; i < indexes.length; i++) {
			System.out.printf("expect %d factorial to be %d and got: %d\n", indexes[i], factorial[i], MathUtil.factorial(indexes[i]));
		}
	}
}
