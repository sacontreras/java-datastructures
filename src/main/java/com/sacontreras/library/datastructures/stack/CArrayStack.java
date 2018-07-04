package com.sacontreras.library.datastructures.stack;

import java.lang.reflect.Array;import com.sacontreras.library.BoxedType;

public class CArrayStack<TElement> implements IStack<TElement> {
	
	final public static int DEFAULT_SIZE = 16;
	
	final private BoxedType<TElement>[] m_array;
	
	private int top = -1;
	public int getTop() {
		return top;
	}
	
	@SuppressWarnings("unchecked")
	public CArrayStack(final int size) {
		m_array = (BoxedType<TElement>[])(new BoxedType[size]);
	}
	public CArrayStack() {
		this(DEFAULT_SIZE);
	}

	@Override
	public void push(TElement e) throws CStackOverflowException {
		if (isFull())
			throw new CStackOverflowException(String.format("top==%d (capacity==%d-1)", top, m_array.length));
		m_array[++top] = new BoxedType<TElement>(e);
	}
	
	@Override
	public TElement peek() throws CStackUnderflowException {
		if (isEmpty())
			throw new CStackUnderflowException("top==-1");
		return m_array[top].getValue();
	}

	@Override
	public TElement pop() throws CStackUnderflowException {
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
