package com.sacontreras.library.algorithms.sort;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;

public class MergeSort<TData extends Comparable<TData>> {
	
	private CLinkedListQueue<TData> sortmerge(final CLinkedListQueue<TData> q_left, final CLinkedListQueue<TData> q_right) {
		CLinkedListQueue<TData> q_merged = new CLinkedListQueue<TData>();
		while (!q_left.isEmpty() && !q_right.isEmpty()) {
			if (q_right.front().compareTo(q_left.front()) < 0)
				q_merged.enqueue(q_right.poll());
			else
				q_merged.enqueue(q_left.poll());
		}
		while (!q_left.isEmpty()) 
			q_merged.enqueue(q_left.poll());
		  
		while (!q_right.isEmpty()) 
			q_merged.enqueue(q_right.poll());
		return q_merged;
	}
	
	public CLinkedListQueue<TData> execute(final CLinkedListQueue<TData> q) {
		int len = q.getSize();
		if (len == 1 )
			return q;
		
		//split tdata_ary in half
		int m = len/2;	//int div rounds down, which means if (tdata_ary.length mod 2) > 0, then the second half will contain the extra element
		 
		CLinkedListQueue<TData> q_left = new CLinkedListQueue<TData>();
		for (int i = 0; i < m; i++)
			q_left.enqueue(q.poll());
		q_left = execute(q_left);
		
		CLinkedListQueue<TData> q_right = new CLinkedListQueue<TData>();
		for (int i = m; i < len; i++)
			q_right.enqueue(q.poll());
		q_right = execute(q_right);
		
		return sortmerge(q_left, q_right);
	}
}
