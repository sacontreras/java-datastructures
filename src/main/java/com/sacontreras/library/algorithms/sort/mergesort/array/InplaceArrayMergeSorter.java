package com.sacontreras.library.algorithms.sort.mergesort.array;

import java.util.Comparator;

public class InplaceArrayMergeSorter<TData extends Comparable<TData>>
		extends InplaceIncomparableArrayMergeSorter<TData, Comparator<TData>> 
		implements ArrayMergeSorter<TData> {
	@Override
	public void mergeSort(TData[] ary) {
		mergeSort(ary, Comparator.<TData>naturalOrder());
	}
}
