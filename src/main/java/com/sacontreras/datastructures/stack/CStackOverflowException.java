package com.sacontreras.datastructures.stack;

public class CStackOverflowException extends CStackException {
	public CStackOverflowException() {
		super();
	}
	
	public CStackOverflowException(final String msg) {
		super(msg);
	}
}
