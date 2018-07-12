package com.sacontreras.library.datastructures.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener.DISPOSITION;

public class CBinaryTree<TData> implements IBinaryTree<TData> {
	
	protected CBinaryTreeNode<TData> root = null;
	
	public CBinaryTree(final CBinaryTreeNode<TData> root) {
		make(root);
	}
	public CBinaryTree() {
		this(null);
	}
	
	final public static <TData>
	boolean isEmpty(final CBinaryTreeNode<TData> node) {
		return node == null;
	}
	
	@Override
	public boolean isEmpty() {
		return isEmpty(root);
	}
	
	final public static <TData> 
	CBinaryTreeNode<TData> make(final CBinaryTreeNode<TData> node, final CBinaryTreeNode<TData> left, final CBinaryTreeNode<TData> right) {
		CBinaryTreeNode<TData> newTreeRoot = null;
		if (node != null) {
			node.left = left;
			if (left != null)
				left.parent = node;
			node.right = right;
			if (right != null)
				right.parent = node;
			newTreeRoot = node;
		}
		return newTreeRoot;
	}
	final public void make(final CBinaryTreeNode<TData> node) {
		this.root = node;	//should copy? for now we just set reference to
	}
	
	final public static <TData> 
	int depth(final CBinaryTreeNode<TData> node) {
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
	
	final public static <TData> 
	int size(final CBinaryTreeNode<TData> node) {
		int s = 0;
		if (node != null)
			s = 1 + size(node.left) + size(node.right);
		return s;
	}
	
	@Override
	final public int size() {
		return size(root);
	}
	
	final public static <TData> 
	int height(final CBinaryTreeNode<TData> node) {
		int h = -1;
        if (node != null)
        	h = 1 + Math.max(height(node.left), height(node.right));
        return h;
    }
	
	@Override
	final public int height() {
		return height(root);
	}
	
	
	
	
	//traversal: pre-order
	public static <TData> 
	void traversePreOrder(final CBinaryTreeNode<TData> node, final IBinaryTreeTraversalListener<TData> traversalListener) {
		if (node == null)
			traversalListener.onNullNode();
		else {
			TData 
				data = node.data,
				data_parent = (node.parent != null ? node.parent.data : null);
			DISPOSITION disp = (node.parent == null ? DISPOSITION.ROOT : (node.parent.left != null && node.parent.left == node ? DISPOSITION.LEFT_CHILD : DISPOSITION.RIGHT_CHILD));
			traversalListener.onNodeVisted(data, disp, data_parent);
			if (node.left != null)
				traversePreOrder(node.left, traversalListener);
			if (node.right != null)
				traversePreOrder(node.right, traversalListener);
		}
	}
	
	@Override
	public void traversePreOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {
		traversePreOrder(root, traversalListener);
	}
	
	private static <TData> 
	CBinaryTreeNode<TData> predecessor_preorder(CBinaryTreeNode<TData> node) {
		return null;
	}
	
	private static <TData> 
	CBinaryTreeNode<TData> successor_preorder(CBinaryTreeNode<TData> node) {
		if (node == null)
	        return null;
		
		CBinaryTreeNode<TData> successor = null;
		if (node.left != null)
			successor = node.left;
	    else {
	    	if (node.right != null)
	    		successor = node.right;
	    	else {
	    		CBinaryTreeNode<TData> 
	    			child = node,
	    			parent = node.parent;
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
	
	public static <TData> 
	Iterator<TData> iterator_preorder(final CBinaryTreeNode<TData> start_from) {
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
	public static <TData> 
	void traverseInOrder(final CBinaryTreeNode<TData> node, final IBinaryTreeTraversalListener<TData> traversalListener) {
		if (node == null)
			traversalListener.onNullNode();
		else {
			if (node.left != null)
				traverseInOrder(node.left, traversalListener);
			TData 
				data = node.data,
				data_parent = (node.parent != null ? node.parent.data : null);
			DISPOSITION disp = (node.parent == null ? DISPOSITION.ROOT : (node.parent.left != null && node.parent.left == node ? DISPOSITION.LEFT_CHILD : DISPOSITION.RIGHT_CHILD));
			traversalListener.onNodeVisted(data, disp, data_parent);
			if (node.right != null)
				traverseInOrder(node.right, traversalListener);
		}
	}
	
	@Override
	public void traverseInOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {
		traverseInOrder(root, traversalListener);
	}
	
	private static <TData> 
	CBinaryTreeNode<TData> predecessor_inorder(CBinaryTreeNode<TData> node) {
		if (node == null)
	        return null;
		
//		If its left subtree is not null
//        Then predecessor will be the right most 
//        child of left subtree or left child itself.
		
		CBinaryTreeNode<TData> predecessor = null;
		if (node.left != null) {
			predecessor = node.left;
	        while (predecessor.right != null)
	        	predecessor = predecessor.right;
	        return predecessor;
	    } else {
	    	predecessor = node.parent;
	    	CBinaryTreeNode<TData> child = node;
	        while (predecessor != null && child == predecessor.left) {
	        	child = predecessor;
	        	predecessor = predecessor.parent;
	        }
	        return predecessor;
	    }
	}
	
	private static <TData> 
	CBinaryTreeNode<TData> successor_inorder(CBinaryTreeNode<TData> node) {
		if (node == null)
	        return null;
		
		CBinaryTreeNode<TData> successor = null;
		if (node.right != null) {//in-order line of descendants (successors) of root_node starts in right sub-tree
			successor = node.right;	//but there may be a "closer" successor in between the right-child
	        while (successor.left != null)	//which would be the leaf in left sub-tree (if it exists) of the right-child
	        	successor = successor.left;
	        return successor;
	    } else {//since root_node does not contain any descendants in its right sub-tree, we must walk up the line of predecessors which also have successor equal to root_node - we find our successor when we encounter a predecessor with successor (right-child) not equal to root_node 
	    	successor = node.parent;
	    	CBinaryTreeNode<TData> child = node;
	        while (successor != null && child == successor.right) {
	        	child = successor;
	        	successor = successor.parent;
	        }
	        return successor;
	    }
	}
	
	public static <TData> 
	Iterator<TData> iterator_inorder(final CBinaryTreeNode<TData> start_from) {
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
	public static <TData> 
	void traversePostOrder(final CBinaryTreeNode<TData> node, final IBinaryTreeTraversalListener<TData> traversalListener) {
		if (node == null)
			traversalListener.onNullNode();
		else {
			if (node.left != null)
				traversePostOrder(node.left, traversalListener);
			if (node.right != null)
				traversePostOrder(node.right, traversalListener);
			TData 
				data = node.data,
				data_parent = (node.parent != null ? node.parent.data : null);
			DISPOSITION disp = (node.parent == null ? DISPOSITION.ROOT : (node.parent.left != null && node.parent.left == node ? DISPOSITION.LEFT_CHILD : DISPOSITION.RIGHT_CHILD));
			traversalListener.onNodeVisted(data, disp, data_parent);
		}
	}
	
	@Override
	public void traversePostOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {
		traversePostOrder(root, traversalListener);
	}
	
	public Iterator<TData> iterator_postorder() {
		return null;
	}
	
	
	
	
	//traversal: level-order
	public static <TData> 
	void traverseLevelOrder(final CBinaryTreeNode<TData> node, final IBinaryTreeTraversalListener<TData> traversalListener) {
		if (node == null)
			traversalListener.onNullNode();
		else {
			CLinkedListQueue<CBinaryTreeNode<TData>> q = new CLinkedListQueue<CBinaryTreeNode<TData>>();	//we use a queue because we want FIFO retrieval
			q.enqueue(node);
			while (!q.isEmpty()) {
				CBinaryTreeNode<TData> level_order_node = q.poll();
				TData 
					data = level_order_node.data,
					data_parent = (level_order_node.parent != null ? level_order_node.parent.data : null);
				DISPOSITION disp = (level_order_node.parent == null ? DISPOSITION.ROOT : (level_order_node.parent.left != null && level_order_node.parent.left == level_order_node ? DISPOSITION.LEFT_CHILD : DISPOSITION.RIGHT_CHILD));
				traversalListener.onNodeVisted(data, disp, data_parent);
				if (level_order_node.left != null)
					q.enqueue(level_order_node.left);
				if (level_order_node.right != null)
					q.enqueue(level_order_node.right);
			}
		}
	}
	
	@Override
	public void traverseLevelOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {
		traverseLevelOrder(root, traversalListener);
	}
	
	public Iterator<TData> iterator_levelorder() {
		return null;
	}
	
	
	//a binary tree T is full if each node is either a leaf or possesses exactly two child nodes
	public static <TData> 
	boolean isFull(final CBinaryTreeNode<TData> node) {
		if (node == null)
			return true;
		boolean 
			isLeaf = node.left == null && node.right == null,
			containsLeftAndRight = node.left != null && node.right != null,
			full_node = isLeaf || containsLeftAndRight;
		return full_node && isFull(node.left) && isFull(node.right);
	}
	
	@Override
	public boolean isFull() {
		return isFull(root);
	}
	
	
	//a binary tree T with n levels is complete if all levels except possibly the last are completely full, and the last level has all its nodes to the left side
	public static <TData> 
	boolean isComplete(final CBinaryTreeNode<TData> node, final int i_node, final int size) {
		if (node == null)
			return true;
	    if (i_node >= size) 
	        return false; 
	    return isComplete(node.left, 2 * i_node + 1, size) && isComplete(node.right, 2 * i_node + 2, size); 
	}
	
	@Override
	public boolean isComplete() {
		return isComplete(root, 0, size());
	}
}
