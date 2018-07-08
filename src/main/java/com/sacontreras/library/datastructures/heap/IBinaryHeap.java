package com.sacontreras.library.datastructures.heap;

import com.sacontreras.library.datastructures.tree.IBinaryTree;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;

//heap property:
//	- complete binary tree
//	- every level except the last has the maximum possible number of nodes

public interface IBinaryHeap<TData> extends IBinaryTree<TData> {
	void add(TData data);		//must run in O(log n) time
	TData remove();				//must run in O(log n) time
	
	default void traversePreOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {}
	default void traverseInOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {}
	default void traversePostOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {}
	default void traverseLevelOrder(final IBinaryTreeTraversalListener<TData> traversalListener) {}
	
	void traversePreOrder(IBinaryHeapTraversalListener<TData> traversalListener);
	void traverseInOrder(IBinaryHeapTraversalListener<TData> traversalListener);
	void traversePostOrder(IBinaryHeapTraversalListener<TData> traversalListener);
	void traverseLevelOrder(IBinaryHeapTraversalListener<TData> traversalListener);
}
