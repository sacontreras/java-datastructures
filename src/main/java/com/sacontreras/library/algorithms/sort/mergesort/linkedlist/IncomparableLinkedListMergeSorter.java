package com.sacontreras.library.algorithms.sort.mergesort.linkedlist;

import java.util.Comparator;

import com.sacontreras.library.datastructures.linkedlist.CLinkedList;

public class IncomparableLinkedListMergeSorter<TData, TComparator extends Comparator<TData>> {
	public CLinkedList<TData> mergeSort(CLinkedList<TData> linkedList, TComparator comparator) {
		if (linkedList == null)
			throw new NullPointerException("ary cannot be null");
		
		int len = linkedList.getSize();
		if (len <= 1)
			return linkedList;
		
		//split q in half
		int m = len/2;	//int div rounds down, which means if (tdata_ary.length mod 2) > 0, then the second half will contain the extra element
		 
		CLinkedList<TData> q_left = new CLinkedList<>();
		for (int i = 0; i < m; i++) {
			q_left.append(linkedList.getFirst());
			linkedList.removeFirst();
		}
		q_left = mergeSort(q_left, comparator);
		
		CLinkedList<TData> q_right = new CLinkedList<>();
		for (int i = m; i < len; i++) {
			q_right.append(linkedList.getFirst());
			linkedList.removeFirst();
		}
		q_right = mergeSort(q_right, comparator);
		
		return sortmerge(q_left, q_right, comparator);
	}
	
	//queue of data that does not need to be inherently comparable... the comparator takes care of this for us
	private CLinkedList<TData> sortmerge(final CLinkedList<TData> q_left, final CLinkedList<TData> q_right, final Comparator<TData> comparator) {
		CLinkedList<TData> q_merged = new CLinkedList<>();
		
		while (!q_left.isEmpty() && !q_right.isEmpty()) {
			if (comparator.compare(q_right.getFirst(), q_left.getFirst()) <= 0) {
				q_merged.append(q_right.getFirst());
				q_right.removeFirst();
			} else {
				q_merged.append(q_left.getFirst());
				q_left.removeFirst();
			}
		}
		
		//remnants in left will automatically be in order, after ary_merged[n_merged-1], by virtue of ordered sorting/merge done above
		while (!q_left.isEmpty()) {
			q_merged.append(q_left.getFirst());
			q_left.removeFirst();
		}
		
		//same goes for remnants in right half  
		while (!q_right.isEmpty()) {
			q_merged.append(q_right.getFirst());
			q_right.removeFirst();
		}
		
		return q_merged;
	}
}
