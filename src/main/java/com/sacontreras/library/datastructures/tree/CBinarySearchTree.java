package com.sacontreras.library.datastructures.tree;

import java.util.Comparator;

public class CBinarySearchTree<TData> extends CBinaryTree<TData> {
	
	final private Comparator<TData> comparator;
	public Comparator<TData> getComparator() {
		return comparator;
	}
	
	public CBinarySearchTree(final Comparator<TData> comparator) {
		this.comparator = comparator;
	}
	private CBinarySearchTree() {
		this(null);
	}
	
	//if data is not found in a root_node itself or a sub-tree of root-node, then we still  return root_node - consumer must then determine what to do based on data value of return
	final private static <TData> 
	CBinaryTreeNode<TData> findLast(final TData data, final CBinaryTreeNode<TData> root_node, final Comparator<TData> comparator) {
		if (root_node != null) {
			if (comparator.compare(data, root_node.data) < 0 && root_node.left != null)
				return findLast(data, root_node.left, comparator);
			else if (comparator.compare(data, root_node.data) > 0 && root_node.right != null)
				return findLast(data, root_node.right, comparator);
			//else data is found in root_node
		}
		return root_node;
	}
	public boolean find(final TData data) {
		CBinaryTreeNode<TData> last_node = findLast(data, root, comparator);
		return last_node != null ? (last_node.data == data ? true : false) : false;
	}
	
	public static class CAddResult<TData> {
		public CBinaryTreeNode<TData> result_node = null;
		public boolean added = false;
		public CAddResult(final CBinaryTreeNode<TData> result_node, final boolean added) {
			this.result_node = result_node;
			this.added = added;
		}
		public CAddResult(final CBinaryTreeNode<TData> result_node) {
			this(result_node, false);
		}
		public CAddResult() {
			this(null, false);
		}
	}
	final public static <TData>
	CAddResult<TData> addChild(final CBinaryTreeNode<TData> node, CBinaryTreeNode<TData> root_node, final Comparator<TData> comparator) {
        if (root_node == null)
        	root_node = make(node, node.left, node.right);
        else {
            int comparison = comparator.compare(node.data, root_node.data);
            if (comparison < 0)
            	root_node = make(root_node, node, root_node.right);
            else if (comparison > 0) {
            	root_node = make(root_node, root_node.left, node);
            } else
                return new CAddResult<TData>(root_node, false);   //node is already in the tree - but we should update value
        }
        return new CAddResult<TData>(root_node, true);        
    }
	
	final public static <TData> 
	CAddResult<TData> add(final TData data, CBinaryTreeNode<TData> root_node, final Comparator<TData> comparator) {
		CBinaryTreeNode<TData> last_node = findLast(data, root_node, comparator);
		return addChild(new CBinaryTreeNode<TData>(data), last_node, comparator);   
	}

//	final public static <TData> 
//	CBinaryTreeNode<TData> add(final TData data, CBinaryTreeNode<TData> root_node, final Comparator<TData> comparator) {
//		if (root_node == null)
//			root_node = new CBinaryTreeNode<TData>(data);
//		else {
//			if (comparator.compare(data, root_node.data) < 0) {
//				if (root_node.left == null)
//					root_node.left = new CBinaryTreeNode<TData>(data, root_node);
//				else
//					add(data, root_node.left, comparator);	
//			} else if (comparator.compare(data, root_node.data) > 0) {
//				if (root_node.right == null)
//					root_node.right = new CBinaryTreeNode<TData>(data, root_node);
//				else
//					add(data, root_node.right, comparator);
//			} else
//				root_node.setData(data);	//effectively augments "reference" count for this data
//		}
//		return root_node;
//	}
	
	public CAddResult<TData> add(final TData data) {
		CAddResult<TData> add_result = add(data, root, comparator);
		if (root == null)
			root = add_result.result_node;
		return add_result;
	}
	
	final public static <TData> 
	boolean isBinarySearchTree(final CBinaryTreeNode<TData> root_node) {
		return false;
	}
}