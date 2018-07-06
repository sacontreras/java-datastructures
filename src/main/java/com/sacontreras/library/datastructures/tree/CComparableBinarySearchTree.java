package com.sacontreras.library.datastructures.tree;

public class CComparableBinarySearchTree<TData extends Comparable<TData>> extends CBinarySearchTree<TData> {
	public CComparableBinarySearchTree(final CComparableBinarySearchComparator<TData> comparator) {
		super(comparator);
	}
	public CComparableBinarySearchTree() {
		this(new CComparableBinarySearchComparator<TData>());
	}
}
