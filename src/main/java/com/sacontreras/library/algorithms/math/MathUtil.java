package com.sacontreras.library.algorithms.math;

public class MathUtil {
	public static double log_b(int n, int b) {
	    return (Math.log(n) / Math.log(b));
	}
	
	public static int fibonacci(int n) {
		if (n < 0)
			throw new IllegalArgumentException("n cannot be negative");
		
		if (n == 0 || n == 1)
			return n;
		else 
			return fibonacci(n - 2) + fibonacci(n - 1);
	}
	
	public static int factorial(int n) {
		if (n < 0)
			throw new IllegalArgumentException("n cannot be negative");
		
		if (n == 0)
			return 1;
		else
			return n * factorial(n - 1);
	}
}
