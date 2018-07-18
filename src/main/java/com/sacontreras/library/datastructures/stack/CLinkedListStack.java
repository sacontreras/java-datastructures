package com.sacontreras.library.datastructures.stack;

import java.util.NoSuchElementException;

import com.sacontreras.library.datastructures.linkedlist.CLinkedList;

public class CLinkedListStack<TElement> implements IStack<TElement> {
	
	private final CLinkedList<TElement> ll = new CLinkedList<TElement>();
	
	public CLinkedListStack() {}

	@Override
	public void push(TElement e) {
		ll.prepend(e);
	}

	@Override
	public TElement peek() {
		TElement element = null;
		try {
			element = ll.getStart().next();
		} catch (NoSuchElementException e) {
			throw new CStackUnderflowException();
		}
		return element;
	}

	@Override
	public TElement pop() {
		final TElement element = peek();
		ll.removeFirst();;
		return element;
	}

	@Override
	public int getSize() {
		return ll.getSize();
	}

	@Override
	public boolean isEmpty() {
		return !ll.getStart().hasNext();
	}

	@Override
	public boolean isFull() {
		return false;
	}
}
