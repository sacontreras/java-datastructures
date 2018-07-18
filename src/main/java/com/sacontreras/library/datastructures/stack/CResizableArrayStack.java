package com.sacontreras.library.datastructures.stack;

import com.sacontreras.library.datastructures.array.CResizableArray;

public class CResizableArrayStack<TElement> implements IStack<TElement> {
	
	final private CResizableArray<TElement> m_rary = new CResizableArray<TElement>();

	@Override
	public void push(TElement e) {
		m_rary.add(e);
	}

	@Override
	public TElement peek() {
		int size = m_rary.getSize();
		if (size <= 0)
			throw new CStackUnderflowException();
		return m_rary.get(size - 1);
	}

	@Override
	public TElement pop() {
		int size = m_rary.getSize();
		if (size <= 0)
			throw new CStackUnderflowException();
		return m_rary.remove(size - 1);
	}

	@Override
	public int getSize() {
		return m_rary.getSize();
	}

	@Override
	public boolean isEmpty() {
		return m_rary.getSize() == 0;
	}

	@Override
	public boolean isFull() {
		//note that since we are using a resizable array, this will tell us if we are at load_capacity (full load), not necessary that we at full capacity
		//	this is an indication of whether a resize is imminent (on next add())
		return m_rary.getLoadFactor() >= m_rary.getLoadFactorThreshold();
	}

}
