package com.sacontreras.datastructures.stack;

public class CStackUnderflowException extends CStackException {
	public CStackUnderflowException() {
		super();
	}
	
	public CStackUnderflowException(final String msg) {
		super(msg);
	}
}
