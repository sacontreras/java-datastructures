package com.sacontreras.library.algorithms.interview;

public class MathUtil {
	public static int fibonacci(int n) {
		if (n == 0 || n == 1)
			return n;
		else 
			return fibonacci(n - 2) + fibonacci(n - 1);
	}
	
	public static int factorial(int n) {
		if (n == 0)
			return 1;
		else
			return n * factorial(n - 1);
	}
}
