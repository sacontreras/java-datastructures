package com.sacontreras.datastructures.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

//based on https://en.wikibooks.org/wiki/Data_Structures/List_Structures

public class CLinkedList<TData> implements ILinkedList<TData> {
	
	public static class Node<TData> {
		private TData data;
		private Node<TData> next_node;
		
		public Node() {
			this.data = null;
			this.next_node = null;
		}
		
		public Node(final TData data) {
			this.data = data;
			this.next_node = null;
		}
		
		//note that this ctor has the effect of prepending a new node to an existing one 
		public Node(final TData data, final Node<TData> next_node) {
			this.data = data;
			this.next_node = next_node;
		}
		
		public static class NodeIterator<TData> implements Iterator<TData> {
			private Node<TData> _next_node;
			
			private NodeIterator(final Node<TData> _next_node) {
				this._next_node = _next_node;
			}

			@Override
			public boolean hasNext() {
				return (_next_node != null);
			}

			//next() accesses data of _next_node, then advances _next_node := _next_node.next_node
			@Override
			public TData next() {
				if (!hasNext()) 
					throw new NoSuchElementException();
				TData data = _next_node.data;
				_next_node = _next_node.next_node;
		        return data;
			}
			
			public Node<TData> get() {
				return _next_node;
			}
			
			//note that removing via the iterator requires tracking the last node prior to advancing the "next pointer"
			//	although we could easily do this, the LinkedList "spec" does not call for this behavior and thus is not expected
			//	that is what a DoublyLinkedList is for
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
	}
	
	private Node<TData> head = null;
	private Node<TData> tail = null;
	
	public CLinkedList() {
	}
	
	private Node.NodeIterator<TData> iterator(Node<TData> start_from) {
		return new Node.NodeIterator<TData>(start_from);
	}

	@Override
	public Node.NodeIterator<TData> iterator() {
		return iterator(head);
	}
	
	public Node.NodeIterator<TData> getHead() {
		return iterator();
	}
	
	public Node.NodeIterator<TData> getTail() {
		return iterator(tail);
	}
	
	@Override
	public void prepend(final TData data) {
		if (head == null)
			head = tail = new Node(data);
		else 
			head = new Node<TData>(data, head);
	}
	
	@Override
	public void append(final TData data) {
		if (tail == null)
			head = tail = new Node<TData>(data);
		else {
			Node<TData> current = head;
			while (current.next_node != null)
				current = current.next_node;
			current.next_node = new Node<TData>(data);
			tail = current.next_node;
		}
	}

	@Override
	public void removeFirst() {
		if (head != null && head != tail) {
			Node<TData >tmp = head;
			head = head.next_node;
			tmp.next_node = null;
		} else
			head = tail = null;
	}
	
	@Override
	public void removeLast() {
		if (tail != null && tail != head) {
			Node<TData> 
				current = head,
				newTail = current;
			while (current.next_node != null) {
				newTail = current;
				current = current.next_node;
			}
			newTail.next_node = null;
			tail = newTail;
		} else
			tail = head = null;
	}

	@Override
	public boolean isEmpty() {
		return (head==tail && tail==null);
	}

	@Override
	public int getSize() {
		Node.NodeIterator<TData> it = iterator();
		int n = 0;
		while (it.hasNext()) {
			n++;
			it.next();
		}
		return n;
	}
	
	private Node<TData> getNode(final int n) throws CLinkedListException {
		if (n < 0)
			throw new CLinkedListException(String.format("Index %d out of bounds", n));
		Node<TData> current = head;
		if (current == null)
			throw new CLinkedListException("linkedlist is empty");
		int i = 0; //since we are at head
		while (n > i++) {
			if (current.next_node == null)
				throw new CLinkedListException(String.format("Index %d out of bounds", n));
			else
				current = current.next_node;
		}
		return current;
	}

	@Override
	public TData get(int n) throws CLinkedListException {
		return getNode(n).data;
	}

	@Override
	public void set(int n, TData data) throws CLinkedListException {
		getNode(n).data = data;
	}
}
