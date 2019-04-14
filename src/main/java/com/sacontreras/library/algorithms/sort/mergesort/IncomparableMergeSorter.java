package com.sacontreras.library.algorithms.sort.mergesort;

import java.util.Comparator;

public interface IncomparableMergeSorter<TData, TComparator extends Comparator<TData>> {
	void mergeSort(TData[] ary, TComparator comparator);
}
