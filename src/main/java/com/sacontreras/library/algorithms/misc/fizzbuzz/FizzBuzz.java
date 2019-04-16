package com.sacontreras.library.algorithms.misc.fizzbuzz;

//Write a program that prints the numbers from 1 to 100. But for multiples of
// three print “Fizz” instead of the number and for the multiples of five print
// “Buzz”. For numbers which are multiples of both three and five print
// “FizzBuzz”
public class FizzBuzz implements Runnable {
	private final int start;
	private final int end;
	private final int fizzFactor;
	private final int buzzFactor;
	
	private final FizzBuzzListener listener;
	
	public FizzBuzz(int start, int end, int fizzFactor, int buzzFactor, FizzBuzzListener listener) {
		this.start = start;
		this.end = end;
		this.fizzFactor = fizzFactor;
		this.buzzFactor = buzzFactor;
		this.listener = listener;
	}
	public FizzBuzz(FizzBuzzListener listener) {
		this(1, 100, 3, 5, listener);
	}
	
	@Override
	public void run() {
		StringBuilder sbFizzBuzz = new StringBuilder();
		
		for (int i = start; i <= end; i++) {
			boolean 
				fizz = i % 3 == 0, 
				buzz = i % 5 == 0;
			
			if (!(fizz || buzz))
				sbFizzBuzz.append(i);
			else {
				if (fizz)
					sbFizzBuzz.append("Fizz");
				if (buzz)
					sbFizzBuzz.append("Buzz");
			}
			listener.OnItem(i, sbFizzBuzz.toString());
			sbFizzBuzz.delete(0, sbFizzBuzz.length());
		}
	}
}