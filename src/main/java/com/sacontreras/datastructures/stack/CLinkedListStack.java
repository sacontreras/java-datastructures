package com.sacontreras.datastructures.stack;

import java.util.NoSuchElementException;

import com.sacontreras.datastructures.linkedlist.CLinkedList;

public class CLinkedListStack<TElement> implements IStack<TElement> {
	
	private final CLinkedList<TElement> ll = new CLinkedList<TElement>();
	
	public CLinkedListStack() {}

	@Override
	public void push(TElement e) throws CStackOverflowException {
		ll.append(e);
	}

	@Override
	public TElement peek() throws CStackUnderflowException {
		TElement element = null;
		try {
			element = ll.getTail().next();
		} catch (NoSuchElementException e) {
			throw new CStackUnderflowException();
		}
		return element;
	}

	@Override
	public TElement pop() throws CStackUnderflowException {
		final TElement element = peek();
		ll.removeLast();
		return element;
	}

	@Override
	public int getSize() {
		return ll.getSize();
	}

	@Override
	public boolean isEmpty() {
		return !ll.getHead().hasNext();
	}

}
