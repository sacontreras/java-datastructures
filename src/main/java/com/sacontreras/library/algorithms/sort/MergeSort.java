package com.sacontreras.library.algorithms.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.util.Generics;

public class MergeSort {
	
	private final static <TData>
	void execute(TData[] ary, TData[] temp, int left, int right, final Comparator<TData> comparator) {
		if (right > left) {
			int mid = (left + right)/2;
			execute(ary, temp, left, mid, comparator);
			execute(ary, temp, mid + 1, right, comparator);
			sortmerge(ary, temp, left, mid + 1, right, comparator);
		}
	}
	private final static <TData>
	void sortmerge(TData[] ary, TData[] temp, int left, int mid, int right, final Comparator<TData> comparator) {
		int left_end, size, temp_pos;
		
		temp_pos = left;
		left_end = mid - 1;
		//right_start = mid + 1;
		size = right - left + 1;
		
		while (left <= left_end && mid <= right) {
			if (comparator.compare(ary[left], ary[mid]) <= 0) {
				temp[temp_pos] = ary[left];
				temp_pos += 1;	//move temp_pos to the right by one
				left += 1;		//also move left endpoint to the right by one
			} else {
				temp[temp_pos] = ary[mid];
				temp_pos += 1;	//move temp_pos to the right by one
				mid += 1;		//move mid to the right by one
			}
		}
		
		while (left <= left_end) {
			temp[temp_pos] = ary[left];
			left += 1;		//also move left endpoint to the right by one
			temp_pos += 1; 	//move temp_pos to the right by one
		}
		
		while (mid <= right) {
			temp[temp_pos] = ary[mid];
			mid += 1;		//move mid to the right by one
			temp_pos += 1; 	//move temp_pos to the right by one
		}
		
		for (int i = 0; i < size; i++) {
			ary[right] = temp[right];
			right -= 1;
		}
	}
	public final static <TData>
	void execute(TData[] ary, final Comparator<TData> comparator) {
		if (ary == null)
			throw new NullPointerException("ary cannot be null");
		if (ary.length == 0)
			return;
		TData[] temp = Generics.<TData>newArray(ary[0], ary.length);
		Arrays.fill(temp, Collections.min(Arrays.asList(ary), comparator));
		execute(ary, temp, 0, ary.length - 1, comparator);
	}
	
	private final static <TData extends Comparable<TData>>
	void execute(TData[] ary, TData[] temp, int left, int right) {
		if (right > left) {
			int mid = (left + right)/2;
			execute(ary, temp, left, mid);
			execute(ary, temp, mid + 1, right);
			sortmerge(ary, temp, left, mid + 1, right);
		}
	}
	private final static <TData extends Comparable<TData>>
	void sortmerge(TData[] ary, TData[] temp, int left, int mid, int right) {
		int left_end, size, temp_pos;
		
		temp_pos = left;
		left_end = mid - 1;
		//right_start = mid + 1;
		size = right - left + 1;
		
		while (left <= left_end && mid <= right) {
			if (ary[left].compareTo(ary[mid]) <= 0) {
				temp[temp_pos] = ary[left];
				temp_pos += 1;	//move temp_pos to the right by one
				left += 1;		//also move left endpoint to the right by one
			} else {
				temp[temp_pos] = ary[mid];
				temp_pos += 1;	//move temp_pos to the right by one
				mid += 1;		//move mid to the right by one
			}
		}
		
		while (left <= left_end) {
			temp[temp_pos] = ary[left];
			left += 1;		//also move left endpoint to the right by one
			temp_pos += 1; 	//move temp_pos to the right by one
		}
		
		while (mid <= right) {
			temp[temp_pos] = ary[mid];
			mid += 1;		//move mid to the right by one
			temp_pos += 1; 	//move temp_pos to the right by one
		}
		
		for (int i = 0; i < size; i++) {
			ary[right] = temp[right];
			right -= 1;
		}
	}
	public final static <TData extends Comparable<TData>>
	void execute(TData[] ary) {
		if (ary == null)
			throw new NullPointerException("ary cannot be null");
		if (ary.length == 0)
			return;
		TData[] temp = Generics.<TData>newArray(ary[0], ary.length);
		Arrays.fill(temp, Collections.min(Arrays.asList(ary)));
		execute(ary, temp, 0, ary.length - 1);
	}
	
	
	
	
	//queue of data that does not need to be inherently comparable... the comparator takes care of this for us
	private final static <TData> 
	CLinkedListQueue<TData> sortmerge(final CLinkedListQueue<TData> q_left, final CLinkedListQueue<TData> q_right, final Comparator<TData> comparator) {
		CLinkedListQueue<TData> q_merged = new CLinkedListQueue<TData>();
		
		while (!q_left.isEmpty() && !q_right.isEmpty()) {
			if (comparator.compare(q_right.front(), q_left.front()) < 0)
				q_merged.enqueue(q_right.poll());
			else
				q_merged.enqueue(q_left.poll());
		}
		
		//remnants in left will automatically be in order, after ary_merged[n_merged-1], by virtue of ordered sorting/merge done above
		while (!q_left.isEmpty()) 
			q_merged.enqueue(q_left.poll());
		
		//same goes for remnants in right half  
		while (!q_right.isEmpty()) 
			q_merged.enqueue(q_right.poll());
		
		return q_merged;
	}
	public final static <TData>
	CLinkedListQueue<TData> execute(final CLinkedListQueue<TData> q, final Comparator<TData> comparator) {
		int len = q.getSize();
		if (len == 1 )
			return q;
		
		//split q in half
		int m = len/2;	//int div rounds down, which means if (tdata_ary.length mod 2) > 0, then the second half will contain the extra element
		 
		CLinkedListQueue<TData> q_left = new CLinkedListQueue<TData>();
		for (int i = 0; i < m; i++)
			q_left.enqueue(q.poll());
		q_left = execute(q_left, comparator);
		
		CLinkedListQueue<TData> q_right = new CLinkedListQueue<TData>();
		for (int i = m; i < len; i++)
			q_right.enqueue(q.poll());
		q_right = execute(q_right, comparator);
		
		return sortmerge(q_left, q_right, comparator);
	}
	
	
	
	
	//for queue of comparable data
	private final static <TData extends Comparable<TData>> 
	CLinkedListQueue<TData> sortmerge(final CLinkedListQueue<TData> q_left, final CLinkedListQueue<TData> q_right) {
		CLinkedListQueue<TData> q_merged = new CLinkedListQueue<TData>();
		
		while (!q_left.isEmpty() && !q_right.isEmpty()) {
			if (q_right.front().compareTo(q_left.front()) < 0)
				q_merged.enqueue(q_right.poll());
			else
				q_merged.enqueue(q_left.poll());
		}
		
		//remnants in left will automatically be in order, after ary_merged[n_merged-1], by virtue of ordered sorting/merge done above
		while (!q_left.isEmpty()) 
			q_merged.enqueue(q_left.poll());
		
		//same goes for remnants in right half  
		while (!q_right.isEmpty()) 
			q_merged.enqueue(q_right.poll());
		
		return q_merged;
	}
	public final static <TData extends Comparable<TData>>
	CLinkedListQueue<TData> execute(final CLinkedListQueue<TData> q) {
		int len = q.getSize();
		if (len == 1 )
			return q;
		
		//split q in half
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
