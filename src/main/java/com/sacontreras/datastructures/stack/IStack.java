package com.sacontreras.datastructures.stack;

//all stack operations (except for getSize()) of descendants must run in constant time O(1)!
public interface IStack<TElement> {
	void push(TElement e) throws CStackOverflowException;
	
	TElement peek() throws CStackUnderflowException;
	TElement pop() throws CStackUnderflowException;
	
	//the only method/operation that is not required to run in O(1) time
	int getSize();
	
	boolean isEmpty();
}
