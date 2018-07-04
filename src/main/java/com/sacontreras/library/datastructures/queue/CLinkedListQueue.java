package com.sacontreras.library.datastructures.queue;

import com.sacontreras.library.datastructures.linkedlist.CLinkedList;

public class CLinkedListQueue<TData> implements IQueue<TData> {
	
	private final CLinkedList<TData> ll = new CLinkedList<TData>();

	@Override
	public void enqueue(TData data) {
		ll.append(data);
	}

	@Override
	public TData front() {
		return ll.getFirst();
	}

	@Override
	public void dequeue() {
		ll.removeFirst();
	}

	@Override
	public boolean isEmpty() {
		return ll.isEmpty();
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int getSize() {
		return ll.getSize();
	}
}
