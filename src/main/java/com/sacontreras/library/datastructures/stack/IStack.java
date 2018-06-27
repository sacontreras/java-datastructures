package com.sacontreras.library.datastructures.stack;

//primary property of stack is that it is LIFO sequenced storage
//all operations except getSize() can be performed in O(1) time. getSize() runs in at worst O(N).
public interface IStack<TElement> {
	void push(TElement e) throws CStackOverflowException;
	
	TElement peek() throws CStackUnderflowException;
	TElement pop() throws CStackUnderflowException;
	
	int getSize();
	
	boolean isEmpty();
	boolean isFull();
}
