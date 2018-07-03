package com.sacontreras.library.datastructures.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;

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
	
	
	
	
	//traversal: pre-order
	public static <TData> void traversePreOrder(final CBinaryTreeNode<TData> root, final IBinaryTreeTraversalListener<TData> traversalListener) {
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
	
	private static <TData> CBinaryTreeNode<TData> predecessor_preorder(CBinaryTreeNode<TData> root_node) {
		return null;
	}
	
	private static <TData> CBinaryTreeNode<TData> successor_preorder(CBinaryTreeNode<TData> root_node) {
		if (root_node == null)
	        return null;
		
		CBinaryTreeNode<TData> successor = null;
		if (root_node.left != null)
			successor = root_node.left;
	    else {
	    	if (root_node.right != null)
	    		successor = root_node.right;
	    	else {
	    		CBinaryTreeNode<TData> 
	    			child = root_node,
	    			parent = root_node.parent;
	    		while (parent != null && child == parent.right) {
	    			child = parent;
	    			parent = parent.parent;
	    		}
	    		if (parent != null)
    				successor = parent.right;
	    	}
	    }
		return successor;
	}
	
	public static <TData> Iterator<TData> iterator_preorder(final CBinaryTreeNode<TData> start_from) {
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
				_next_node = successor_preorder(_next_node);
			    return data;
			}
		};
	}
	public Iterator<TData> iterator_preorder() {
		return iterator_preorder(root);
	}
	
	
	
	
	//traversal: in-order
	public static <TData> void traverseInOrder(final CBinaryTreeNode<TData> root, final IBinaryTreeTraversalListener<TData> traversalListener) {
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
	
	private static <TData> CBinaryTreeNode<TData> predecessor_inorder(CBinaryTreeNode<TData> root_node) {
		if (root_node == null)
	        return null;
		
//		If its left subtree is not null
//        Then predecessor will be the right most 
//        child of left subtree or left child itself.
		
		CBinaryTreeNode<TData> predecessor = null;
		if (root_node.left != null) {
			predecessor = root_node.left;
	        while (predecessor.right != null)
	        	predecessor = predecessor.right;
	        return predecessor;
	    } else {
	    	predecessor = root_node.parent;
	    	CBinaryTreeNode<TData> child = root_node;
	        while (predecessor != null && child == predecessor.left) {
	        	child = predecessor;
	        	predecessor = predecessor.parent;
	        }
	        return predecessor;
	    }
	}
	
	private static <TData> CBinaryTreeNode<TData> successor_inorder(CBinaryTreeNode<TData> root_node) {
		if (root_node == null)
	        return null;
		
		CBinaryTreeNode<TData> successor = null;
		if (root_node.right != null) {//in-order line of descendants (successors) of root_node starts in right sub-tree
			successor = root_node.right;	//but there may be a "closer" successor in between the right-child
	        while (successor.left != null)	//which would be the leaf in left sub-tree (if it exists) of the right-child
	        	successor = successor.left;
	        return successor;
	    } else {//since root_node does not contain any descendants in its right sub-tree, we must walk up the line of predecessors which also have successor equal to root_node - we find our successor when we encounter a predecessor with successor (right-child) not equal to root_node 
	    	successor = root_node.parent;
	    	CBinaryTreeNode<TData> child = root_node;
	        while (successor != null && child == successor.right) {
	        	child = successor;
	        	successor = successor.parent;
	        }
	        return successor;
	    }
	}
	
	public static <TData> Iterator<TData> iterator_inorder(final CBinaryTreeNode<TData> start_from) {
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
				_next_node = successor_inorder(_next_node);
			    return data;
			}
		};
	}
	public Iterator<TData> iterator_inorder() {
		CBinaryTreeNode<TData> 
			predecessor = root,
			tmp = predecessor;
		while (tmp != null) {
			tmp = predecessor_inorder(tmp);
			if (tmp != null)
				predecessor = tmp;
		}
		return iterator_inorder(predecessor);	
	}
	
	
	
	
	//traversal: post-order
	public static <TData> void traversePostOrder(final CBinaryTreeNode<TData> root, final IBinaryTreeTraversalListener<TData> traversalListener) {
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
	
	public Iterator<TData> iterator_postorder() {
		return null;
	}
	
	
	
	
	//traversal: level-order
	public static <TData> void traverseLevelOrder(final CBinaryTreeNode<TData> root, final IBinaryTreeTraversalListener<TData> traversalListener) {
		if (root == null)
			traversalListener.onNullNode();
		else {
			CLinkedListQueue<CBinaryTreeNode<TData>> q = new CLinkedListQueue<CBinaryTreeNode<TData>>();	//we use a queue because we want FIFO retrieval
			q.enqueue(root);
			while (!q.isEmpty()) {
				CBinaryTreeNode<TData> node = q.poll();
				traversalListener.onNodeVisted(node.data);
				if (node.left != null)
					q.enqueue(node.left);
				if (node.right != null)
					q.enqueue(node.right);
			}
		}
	}
	public void traverseLevelOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {
		traverseLevelOrder(root, traversalListener);
	}
	
	public Iterator<TData> iterator_levelorder() {
		return null;
	}
}
