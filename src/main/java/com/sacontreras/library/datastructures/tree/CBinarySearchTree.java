package com.sacontreras.library.datastructures.tree;

import java.util.Comparator;

public class CBinarySearchTree<TData extends Comparable<TData>> extends COrderedBinaryTree<TData, CBinarySearchComparator<TData>> {
	public CBinarySearchTree() {
		super(new CBinarySearchComparator<TData>());
	}
}