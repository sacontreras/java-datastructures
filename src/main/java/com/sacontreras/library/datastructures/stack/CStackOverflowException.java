package com.sacontreras.library.datastructures.stack;

public class CStackOverflowException extends CStackException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CStackOverflowException() {
		super();
	}
	
	public CStackOverflowException(final String msg) {
		super(msg);
	}
}
