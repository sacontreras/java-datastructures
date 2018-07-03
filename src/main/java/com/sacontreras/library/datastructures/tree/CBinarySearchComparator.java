package com.sacontreras.library.datastructures.tree;

import java.util.Comparator;

public class CBinarySearchComparator<TData extends Comparable<TData>> implements Comparator<TData> {
	@Override
	public int compare(TData o1, TData o2) {
		return o1.compareTo(o2);
	}
}
