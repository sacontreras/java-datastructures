package com.sacontreras.library.algorithms.sort.mergesort.linkedlist;

import java.util.Comparator;

import com.sacontreras.library.datastructures.linkedlist.CLinkedList;

public class LinkedListMergeSorter<TData extends Comparable<TData>> extends IncomparableLinkedListMergeSorter<TData, Comparator<TData>> {
	@Override
	public CLinkedList<TData> mergeSort(CLinkedList<TData> linkedList, Comparator<TData> comparator) {
		return super.mergeSort(linkedList, Comparator.<TData>naturalOrder());
	}
	
	public CLinkedList<TData> mergeSort(CLinkedList<TData> linkedList) {
		return super.mergeSort(linkedList, Comparator.<TData>naturalOrder());
	}
}
