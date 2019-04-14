package com.sacontreras.library.datastructures.linkedlist;

import java.util.Iterator;

//based on https://en.wikibooks.org/wiki/Data_Structures/List_Structures
public interface LinkedList<TData> extends Iterable<TData> {
	//returns the list iterator that represents the first element of the list. runs in O(1) time.
	Iterator<TData> getStart();
	
	//returns the list iterator that represents one element past the last element in the list. runs in O(1) time.
	Iterator<TData> getEnd();
	
	//adds a new element at the beginning of a list. runs in O(1) time.
	void prepend(TData data);
	
	//adds a new element immediately after the last item in the list. runs in O(N) time.
	void append(TData data);
	
	//removes the element at the beginning of a list. runs in O(1) time.
	void removeFirst();
	
	//remove the last element from the list. runs in O(N) time. 
	void removeLast();
	
	//true if there are no elements in the list. Has a default implementation. runs in O(1) time. 
	boolean isEmpty();
	
	//returns the number of elements in the list. Has a default implementation. runs in O(N) time. 
	int getSize();
	
	//returns the nth element in the list, counting from 0. Has a default implementation. runs in O(N) time.
	TData get(int n) throws LinkedListException;
	
	//assigns a new value to the nth element in the list, counting from 0. runs in O(N) time.
	void set(int n, TData data) throws LinkedListException;
	
	//for convenience...
	TData getFirst();
	TData getLast();
}
