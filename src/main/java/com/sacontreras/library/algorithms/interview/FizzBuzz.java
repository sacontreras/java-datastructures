package com.sacontreras.library.algorithms.interview;

//Write a program that prints the numbers from 1 to 100. But for multiples of
// three print “Fizz” instead of the number and for the multiples of five print
// “Buzz”. For numbers which are multiples of both three and five print
// “FizzBuzz”
public class FizzBuzz implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			boolean div3 = i % 3 == 0, div5 = i % 5 == 0;
			if (!(div3 || div5))
				System.out.print(i);
			else {
				if (div3)
					System.out.print("Fizz");
				if (div5)
					System.out.print("Buzz");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		new FizzBuzz().run();
	}
}