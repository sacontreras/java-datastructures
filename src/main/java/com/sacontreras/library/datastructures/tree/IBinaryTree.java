package com.sacontreras.library.datastructures.tree;

public interface IBinaryTree<TData> {
	boolean isEmpty();
	
	//the number of nodes
	int size();	
	
	//maximal leaf depth
	int height();
	
	//a binary tree T is full if each node is either a leaf or possesses exactly two child nodes - note that a null (root) tree is by definition full
	boolean isFull();	//not that this has no relation to !isEmpty(), but rather refers to the full binary tree property as given by the def above
	
	//a binary tree T with n levels is complete if all levels except possibly the last are completely full, and the last level has all its nodes to the left side
	boolean isComplete();
	
	boolean isHeightBalanced();
	
	void traversePreOrder(final IBinaryTreeTraversalListener<TData> traversalListener);
	void traverseInOrder(final IBinaryTreeTraversalListener<TData> traversalListener);
	void traversePostOrder(final IBinaryTreeTraversalListener<TData> traversalListener);
	void traverseLevelOrder(final IBinaryTreeTraversalListener<TData> traversalListener);
}
