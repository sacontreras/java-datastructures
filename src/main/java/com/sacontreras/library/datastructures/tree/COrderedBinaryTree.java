package com.sacontreras.library.datastructures.tree;

import java.util.Comparator;

public class COrderedBinaryTree<TData, TComparator extends Comparator<TData>> extends CBinaryTree<TData> {
	
	final private Comparator<TData> comparator;
	
	public COrderedBinaryTree(final Comparator<TData> comparator) {
		super();
		this.comparator = comparator;
	}
	private COrderedBinaryTree() {
		super();
		this.comparator = null;
	}

	final public static <TData> CBinaryTreeNode<TData> insert(CBinaryTreeNode<TData> root_node, final TData data, final Comparator<TData> comparator) {
		if (root_node == null)
			root_node = new CBinaryTreeNode<TData>(data);
		else {
			if (comparator.compare(data, root_node.data) < 0) {
				if (root_node.left == null)
					root_node.left = new CBinaryTreeNode<TData>(data, root_node);
				else
					insert(root_node.left, data, comparator);	
			} else if (comparator.compare(data, root_node.data) > 0) {
				if (root_node.right == null)
					root_node.right = new CBinaryTreeNode<TData>(data, root_node);
				else
					insert(root_node.right, data, comparator);
			} else
				root_node.setData(data);	//effectively augments "reference" count for this data
		}
		return root_node;
	}
	public void insert(final TData data) {
		root = insert(root, data, comparator);
	}
}
