package com.sacontreras.library.algorithms.sort;

import java.lang.reflect.Array;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;

public class MergeSort {
	
	@SuppressWarnings("unchecked")
	private final static <TData extends Comparable<TData>>
	TData[] sortmerge(final TData[] ary_left, final TData[] ary_right) {
		int 
			len_left = ary_left.length,
			trans_len_left = len_left,
			len_right = ary_right.length,
			trans_len_right = len_right,
			trans_first_left = 0,
			trans_first_right = 0,
			n_merged = 0;
		TData[] ary_merged = (TData[])Array.newInstance(ary_left[0].getClass(), len_left + len_right);
		while (trans_len_left > 0 && trans_len_right > 0) {
			if (ary_right[trans_first_right].compareTo(ary_left[trans_first_left]) < 0) {
				ary_merged[n_merged++] = ary_right[trans_first_right];
				trans_len_right--;
				trans_first_right++;
			} else {
				ary_merged[n_merged++] = ary_left[trans_first_left];
				trans_len_left--;
				trans_first_left++;
			}
		}
		while (trans_len_left > 0) {
			ary_merged[n_merged++] = ary_left[trans_first_left];
			trans_len_left--;
			trans_first_left++;
		}
		  
		while (trans_len_right > 0) {
			ary_merged[n_merged++] = ary_right[trans_first_right];
			trans_len_right--;
			trans_first_right++;
		}
		return ary_merged;
	}
	
	@SuppressWarnings("unchecked")
	public final static <TData extends Comparable<TData>> 
	TData[] execute(final TData[] ary) {
		int len = ary.length;
		if (len == 1 )
			return ary;
		
		//split ary in half - divide and conquer
		int m = len/2;	//int div rounds down, which means if (tdata_ary.length mod 2) > 0, then the second half will contain the extra element
		
		TData[] ary_left = (TData[])Array.newInstance(ary[0].getClass(), m);
		for (int i = 0; i < m; i++)
			ary_left[i] = ary[i];
		ary_left = execute(ary_left);
		
		TData[] ary_right = (TData[])Array.newInstance(ary[0].getClass(), len - m);
		for (int i = m; i < len; i++)
			ary_right[i - m] = ary[i];
		ary_right = execute(ary_right);
		
		return sortmerge(ary_left, ary_right);
	}
	
	
	
	
	private final static <TData extends Comparable<TData>> 
	CLinkedListQueue<TData> sortmerge(final CLinkedListQueue<TData> q_left, final CLinkedListQueue<TData> q_right) {
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
