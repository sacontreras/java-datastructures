package com.sacontreras.library.datastructures.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CBinaryTree<TData> {
	
	protected CBinaryTreeNode<TData> root = null;
	
	public CBinaryTree(final CBinaryTreeNode<TData> root) {
		make(root);
	}
	public CBinaryTree() {
		this(null);
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	final public static <TData> CBinaryTreeNode<TData> make(final CBinaryTreeNode<TData> root, final CBinaryTreeNode<TData> left, final CBinaryTreeNode<TData> right) {
		CBinaryTreeNode<TData> newTreeRoot = null;
		if (root != null) {
			root.left = left;
			if (left != null)
				left.parent = root;
			root.right = right;
			if (right != null)
				right.parent = root;
			newTreeRoot = root;
		}
		return newTreeRoot;
	}
	final public void make(final CBinaryTreeNode<TData> root) {
		this.root = root;	//should copy? for now we just set reference to
	}
	
	final public static <TData> int depth(final CBinaryTreeNode<TData> node) {
		int d = -1;
		if (node != null) {
			d = 0;
			CBinaryTreeNode<TData> parent = node;
			while ((parent = parent.parent) != null) {
				d++;
			}
		}
		return d;
	}
	
	final public static <TData> int size(final CBinaryTreeNode<TData> root) {
		int s = 0;
		if (root != null)
			s = 1 + size(root.left) + size(root.right);
		return s;
	}
	final public int size() {
		return size(root);
	}
	
	final public static <TData> int height(final CBinaryTreeNode<TData> root) {
		int h = -1;
        if (root != null) {
        	h = 1 + Math.max(height(root.left), height(root.right));
        }
        return h;
    }
	final public int height() {
		return height(root);
	}
	
	//in-order successor
	private static <TData> CBinaryTreeNode<TData> successor(CBinaryTreeNode<TData> root_node) {
		if (root_node == null)
	        return null;
		
		CBinaryTreeNode<TData> successor = null;
		if (root_node.right != null) {//in-order line of descendants (successors) of root_node is in its right sub-tree
			successor = root_node.right;	//but there may be another successor in between the right-child and right-child's successors
	        while (successor.left != null)	//which would fall in *first* populated left sub-tree of the right child; so now find first populated left sub-tree...
	        	successor = successor.left;
	        return successor;	//successor now refers to first populated left sub-tree; successor value is the supremum (least upper-bound) of root-node's value
	    } else {//assuming our in-order insert works correctly, root-node's next successor will be the first descendant in its line of parents whose descendant is not root_node
	    	successor = root_node.parent;
	    	CBinaryTreeNode<TData> child = root_node;
	        while (successor != null && child == successor.right) {
	        	child = successor;
	        	successor = successor.parent;
	        }
	        return successor;
	    }
	}
	
	public static <TData> Iterator<TData> inOrderIterator(final CBinaryTreeNode<TData> start_from) {
		return new Iterator<TData>() {
			private CBinaryTreeNode<TData> _next_node = start_from;

			@Override
			public boolean hasNext() {
				return (_next_node != null);
			}

			@Override
			public TData next() {
				if (!hasNext()) 
					throw new NoSuchElementException();
				TData data = _next_node.data;
				_next_node = successor(_next_node);
			    return data;
			}
		};
	}
	public Iterator<TData> inOrderIterator() {
		return inOrderIterator(root);
	}
	
	public Iterator<TData> preOrderIterator() {
		return null;
	}
	
	public Iterator<TData> postOrderIterator() {
		return null;
	}
	
	public Iterator<TData> levelOrderIterator() {
		return null;
	}
	
	
	
	
	public static <TData> void traverseInOrder(CBinaryTreeNode<TData> root, final IBinaryTreeTraversalListener<TData> traversalListener) {
		if (root == null)
			traversalListener.onNullNode();
		else {
			if (root.left != null)
				traverseInOrder(root.left, traversalListener);
			traversalListener.onNodeVisted(root.data);
			if (root.right != null)
				traverseInOrder(root.right, traversalListener);
		}
	}
	public void traverseInOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {
		traverseInOrder(root, traversalListener);
	}
	
	public static <TData> void traversePreOrder(CBinaryTreeNode<TData> root, final IBinaryTreeTraversalListener<TData> traversalListener) {
		if (root == null)
			traversalListener.onNullNode();
		else {
			traversalListener.onNodeVisted(root.data);
			if (root.left != null)
				traversePreOrder(root.left, traversalListener);
			if (root.right != null)
				traversePreOrder(root.right, traversalListener);
		}
	}
	public void traversePreOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {
		traversePreOrder(root, traversalListener);
	}
	
	public static <TData> void traversePostOrder(CBinaryTreeNode<TData> root, final IBinaryTreeTraversalListener<TData> traversalListener) {
		if (root == null)
			traversalListener.onNullNode();
		else {
			if (root.left != null)
				traversePostOrder(root.left, traversalListener);
			if (root.right != null)
				traversePostOrder(root.right, traversalListener);
			traversalListener.onNodeVisted(root.data);
		}
	}
	public void traversePostOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {
		traversePostOrder(root, traversalListener);
	}
}
