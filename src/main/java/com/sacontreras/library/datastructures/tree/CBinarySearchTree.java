package com.sacontreras.library.datastructures.tree;

public class CBinarySearchTree<TData extends Comparable<TData>> extends COrderedBinaryTree<TData, CBinarySearchComparator<TData>> {
	public CBinarySearchTree() {
		super(new CBinarySearchComparator<TData>());
	}
}