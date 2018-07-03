package com.sacontreras.library.datastructures.queue;

//primary property of queue is that it is FIFO sequenced storage
//all operations except getSize() can be performed in O(1) time. getSize() runs in at worst O(N).
public interface IQueue<TData> {
	//adds an item onto the end of the queue.
	void enqueue(TData data);
	
	//returns the item at the front of the queue.
	TData front();
	
	//removes the item from the front of the queue.
	void dequeue();
	
	//true if no more items can be dequeued and there is no front item.
	boolean isEmpty();
	
	//true if no more items can be enqueued.
	boolean isFull();
	
	//returns the number of elements in the queue.
	int getSize();
}
