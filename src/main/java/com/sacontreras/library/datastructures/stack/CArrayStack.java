package com.sacontreras.library.datastructures.stack;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.util.Generics;

public class CArrayStack<TElement> implements IStack<TElement> {
	
	final public static int DEFAULT_SIZE = 16;
	
	final private BoxedType<TElement>[] m_array;
	
	private int top = -1;
	public int getTop() {
		return top;
	}
	
	public CArrayStack(final int size) {
		m_array = Generics.newBoxedTypeArray(size);
	}
	public CArrayStack() {
		this(DEFAULT_SIZE);
	}

	@Override
	public void push(TElement e) {
		if (isFull())
			throw new CStackOverflowException(String.format("top==%d (capacity==%d-1)", top, m_array.length));
		m_array[++top] = new BoxedType<TElement>(e);
	}
	
	@Override
	public TElement peek() {
		if (isEmpty())
			throw new CStackUnderflowException("top==-1");
		return m_array[top].getValue();
	}

	@Override
	public TElement pop() {
		TElement element = peek();
		top--;
		return element;
	}

	@Override
	public int getSize() {
		return m_array.length;
	}
	
	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return (top == (m_array.length - 1));
	}
}
