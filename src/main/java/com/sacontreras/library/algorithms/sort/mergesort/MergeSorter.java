package com.sacontreras.library.algorithms.sort.mergesort;

public interface MergeSorter<TData extends Comparable<TData>> {
	void mergeSort(TData[] ary);
}
