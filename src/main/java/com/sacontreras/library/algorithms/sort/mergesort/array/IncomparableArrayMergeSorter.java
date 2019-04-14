package com.sacontreras.library.algorithms.sort.mergesort.array;

import java.util.Comparator;

public interface IncomparableArrayMergeSorter<TData, TComparator extends Comparator<TData>> {
	void mergeSort(TData[] ary, TComparator comparator);
}
