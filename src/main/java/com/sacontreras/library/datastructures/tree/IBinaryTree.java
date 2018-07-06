package com.sacontreras.library.datastructures.tree;

public interface IBinaryTree<TData> {
	boolean isEmpty();
	
	//the number of nodes
	int size();	
	
	//maximal leaf depth
	int height();
	
	void traversePreOrder(final IBinaryTreeTraversalListener<TData> traversalListener);
	void traverseInOrder(final IBinaryTreeTraversalListener<TData> traversalListener);
	void traversePostOrder(final IBinaryTreeTraversalListener<TData> traversalListener);
	void traverseLevelOrder(final IBinaryTreeTraversalListener<TData> traversalListener);
}
