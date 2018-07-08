package com.sacontreras.library.datastructures.heap;

import java.util.Comparator;

import com.sacontreras.library.datastructures.array.CResiableArray;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.CBinaryTreeNode;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener.DISPOSITION;

//Eytzinger's method allows us to represent a complete binary tree as an array by laying out the nodes of the tree in breadth-first order (level-order).
//In this way, the root is stored at position 0, the root's left child is stored at position 1, the root's right child at position 2, the left child of the left child of the root is stored at position 3, and so on.
//The left child of the node at index i is at index left(i) = 2i + 1, and the right child of the node at index i is at index right(i) = 2i + 2.
//The parent of the (left/right child) node at index i is parent(i) = (i - 1)/2.
//A BinaryHeap uses this technique to implicitly represent a complete binary tree in which the elements are heap-ordered:
//	The value stored at any index i is not smaller than the value stored at index parent(i), with the exception of the root value, i == 0.
//It follows that the smallest value in the priority Queue (Binary Heap) is therefore stored/always located at position 0 (the root).
public class CBinaryHeap<TData> implements IBinaryHeap<TData> {
	
	private final CResiableArray<TData> ary;
	private int count = 0;
	
	final private Comparator<TData> comparator;
	public Comparator<TData> getComparator() {
		return comparator;
	}
	
	public CBinaryHeap(final Comparator<TData> comparator) {
		ary = new CResiableArray<TData>();
		this.comparator = comparator;
    }
	private CBinaryHeap() {
		this(null);
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public int size() {
		return count;
	}
	
	//example:
	// array: {5, 15, 10, 20, 30, 35. 30, 55, 40, 50, 45}
	// graphically:
	//			          5(i:0)
	//	   	     /     		           \
	//	        /	      		        \
	//	       15(i:1)                10(i:2)
	//		  /          \     		  /  	\
	//	     /            \   	     /    	 \
	//	  20(i:3)        30(i:4)  35(i:5)   30(i:6)
	//	 /      \        /      \  
	//  /        \      /        \
	// 55(i:7) 40(i:8) 50(i:9) 45(i:10) 
	private int left_child(final int i_parent) {
        return 2 * i_parent + 1;
    }
	
    private int right_child(final int i_parent) {
        return 2 * i_parent + 2;
    }
    
    private int parent_of(final int i_node) {
        return (i_node - 1) / 2;
    }
    
    final static public <TData>
    int height(final CBinaryHeap<TData> heap, final int i_root) {
    	int h = -1;
        if (heap.ary.get(i_root) != null)
        	h = 1 + Math.max(height(heap, heap.left_child(i_root)), height(heap, heap.right_child(i_root)));
        return h;
    }

	@Override
	public int height() {
		return height(this, 0);
	}
	
	//traversal: pre-order
	public static <TData> 
	void traversePreOrder(final CBinaryHeap<TData> heap, final int i_root, final IBinaryHeapTraversalListener<TData> traversalListener) {
		if (i_root < 0 || i_root >= heap.ary.getSize())
			return;
		TData data = heap.ary.get(i_root);
		if (data == null)
			traversalListener.onNullNode(i_root);
		else {
			int 
				tmp = -1,
				i_left = heap.left_child(i_root),
				i_right = heap.right_child(i_root),
				i_parent = (tmp = heap.parent_of(i_root)) != i_root ? tmp : -1,
				i_parent_left = i_parent < 0 ? -1 : heap.left_child(i_parent),
				i_parent_right = i_parent < 0 ? -1 : heap.right_child(i_parent);
			TData 
				data_left = i_left < 0 ? null : heap.ary.get(i_left),
				data_right = i_right < 0 ? null : heap.ary.get(i_right),
				data_parent = i_parent < 0 ? null : heap.ary.get(i_parent),
				data_parent_left = i_parent_left < 0 ? null : heap.ary.get(i_parent_left),
				data_parent_right = i_parent_right < 0 ? null : heap.ary.get(i_parent_right);
			DISPOSITION disp = (data_parent == null ? DISPOSITION.ROOT : (data_parent_left != null && data_parent_left == data ? DISPOSITION.LEFT_CHILD : DISPOSITION.RIGHT_CHILD));
			traversalListener.onNodeVisted(i_root, data, disp, i_parent, data_parent);
			if (i_left > 0 && data_left != null)
				traversePreOrder(heap, i_left, traversalListener);
			if (i_right > 0 && data_right != null)
				traversePreOrder(heap, i_right, traversalListener);
		}
	}

	@Override
	public void traversePreOrder(final IBinaryHeapTraversalListener<TData> traversalListener) {
		traversePreOrder(this, 0, traversalListener);
	}
	
	//traversal: in-order
	public static <TData> 
	void traverseInOrder(final CBinaryHeap<TData> heap, final int i_root, final IBinaryHeapTraversalListener<TData> traversalListener) {
		if (i_root < 0 || i_root >= heap.ary.getSize())
			return;
		TData data = heap.ary.get(i_root);
		if (data == null)
			traversalListener.onNullNode(i_root);
		else {
			int 
				tmp = -1,
				i_left = heap.left_child(i_root),
				i_right = heap.right_child(i_root),
				i_parent = (tmp = heap.parent_of(i_root)) != i_root ? tmp : -1,
				i_parent_left = i_parent < 0 ? -1 : heap.left_child(i_parent),
				i_parent_right = i_parent < 0 ? -1 : heap.right_child(i_parent);
			TData 
				data_left = i_left < 0 ? null : heap.ary.get(i_left),
				data_right = i_right < 0 ? null : heap.ary.get(i_right),
				data_parent = i_parent < 0 ? null : heap.ary.get(i_parent),
				data_parent_left = i_parent_left < 0 ? null : heap.ary.get(i_parent_left),
				data_parent_right = i_parent_right < 0 ? null : heap.ary.get(i_parent_right);
			DISPOSITION disp = (data_parent == null ? DISPOSITION.ROOT : (data_parent_left != null && data_parent_left == data ? DISPOSITION.LEFT_CHILD : DISPOSITION.RIGHT_CHILD));
			if (i_left > 0 && data_left != null)
				traverseInOrder(heap, i_left, traversalListener);
			traversalListener.onNodeVisted(i_root, data, disp, i_parent, data_parent);
			if (i_right > 0 && data_right != null)
				traverseInOrder(heap, i_right, traversalListener);
			}
	}

	@Override
	public void traverseInOrder(final IBinaryHeapTraversalListener<TData> traversalListener) {
		traverseInOrder(this, 0, traversalListener);
	}
	
	//traversal: post-order
	public static <TData> 
	void traversePostOrder(final CBinaryHeap<TData> heap, final int i_root, final IBinaryHeapTraversalListener<TData> traversalListener) {
		if (i_root < 0 || i_root >= heap.ary.getSize())
			return;
		TData data = heap.ary.get(i_root);
		if (data == null)
			traversalListener.onNullNode(i_root);
		else {
			int 
				tmp = -1,
				i_left = heap.left_child(i_root),
				i_right = heap.right_child(i_root),
				i_parent = (tmp = heap.parent_of(i_root)) != i_root ? tmp : -1,
				i_parent_left = i_parent < 0 ? -1 : heap.left_child(i_parent),
				i_parent_right = i_parent < 0 ? -1 : heap.right_child(i_parent);
			TData 
				data_left = i_left < 0 ? null : heap.ary.get(i_left),
				data_right = i_right < 0 ? null : heap.ary.get(i_right),
				data_parent = i_parent < 0 ? null : heap.ary.get(i_parent),
				data_parent_left = i_parent_left < 0 ? null : heap.ary.get(i_parent_left),
				data_parent_right = i_parent_right < 0 ? null : heap.ary.get(i_parent_right);
			DISPOSITION disp = (data_parent == null ? DISPOSITION.ROOT : (data_parent_left != null && data_parent_left == data ? DISPOSITION.LEFT_CHILD : DISPOSITION.RIGHT_CHILD));
			if (i_left > 0)
				traversePostOrder(heap, i_left, traversalListener);
			if (i_right > 0)
				traversePostOrder(heap, i_right, traversalListener);
			traversalListener.onNodeVisted(i_root, data, disp, i_parent, data_parent);
		}
	}

	@Override
	public void traversePostOrder(final IBinaryHeapTraversalListener<TData> traversalListener) {
		traversePostOrder(this, 0, traversalListener);
	}
	
	//traversal: level-order
	public static <TData> 
	void traverseLevelOrder(final CBinaryHeap<TData> heap, final int i_root, final IBinaryHeapTraversalListener<TData> traversalListener) {
		if (heap.ary.get(i_root) == null)
			traversalListener.onNullNode();
		else {
			CLinkedListQueue<Integer> q = new CLinkedListQueue<Integer>();	//we use a queue because we want FIFO retrieval
			q.enqueue(i_root);
			while (!q.isEmpty()) {
				int 
					i_node = q.poll(),
					tmp = -1,
					i_left = heap.left_child(i_node),
					i_right = heap.right_child(i_node),
					i_parent = (tmp = heap.parent_of(i_node)) != i_node ? tmp : -1,
					i_parent_left = i_parent < 0 ? -1 : heap.left_child(i_parent),
					i_parent_right = i_parent < 0 ? -1 : heap.right_child(i_parent);
				TData 
					data = heap.ary.get(i_node),
					data_left = i_left < 0 || data == null ? null : heap.ary.get(i_left),
					data_right = i_right < 0 || data == null ? null : heap.ary.get(i_right),
					data_parent = i_parent < 0 || data == null ? null : heap.ary.get(i_parent),
					data_parent_left = i_parent_left < 0 || data_parent == null ? null : heap.ary.get(i_parent_left),
					data_parent_right = i_parent_right < 0 || data_parent == null ? null : heap.ary.get(i_parent_right);
				DISPOSITION disp = (data_parent == null ? DISPOSITION.ROOT : (data_parent_left != null && data_parent_left == data ? DISPOSITION.LEFT_CHILD : DISPOSITION.RIGHT_CHILD));
				traversalListener.onNodeVisted(i_node, data, disp, i_parent, data_parent);
				if (i_left > 0 && data_left != null)
					q.enqueue(i_left);
				if (i_right > 0 && data_right != null)
					q.enqueue(i_right);
			}
		}
	}

	@Override
	public void traverseLevelOrder(final IBinaryHeapTraversalListener<TData> traversalListener) {
		traverseLevelOrder(this, 0, traversalListener);
	}
	
	private void bubbleup(int i_node) {
		int i_parent = parent_of(i_node);
		TData 
			data_node = null,
			data_parent = null;
        while (i_node > 0 && comparator.compare(data_node = ary.get(i_node), data_parent = ary.get(i_parent)) < 0) {
        	ary.set(data_parent, i_node);
        	ary.set(data_node, i_parent);
        	i_node = i_parent;
        	i_parent = parent_of(i_node);
        }	
	}

	@Override
	public void add(TData data) {
		ary.add(data);
		count++;
		bubbleup(count - 1);
	}
	
	private void trickledown(int i_node) {
		do {
            int 
            	i_left = left_child(i_node),
        		i_right = right_child(i_node),
        		i_swap = -1;
            TData 
				data_node = ary.get(i_node),
				data_left = ary.get(i_left),
				data_right = ary.get(i_right),
				data_swap = null;
            if (i_right < count && comparator.compare(data_right, data_node) < 0) {
            	i_swap = comparator.compare(data_left, data_right) < 0 ? i_left : i_right;
            	data_swap = ary.get(i_swap);
            } else {
            	if (i_left < count && comparator.compare(data_left, data_node) < 0) {
            		i_swap = i_left;
                    data_swap = ary.get(i_left);
            	}
            }
            if (data_swap != null) {    
            	ary.set(data_swap, i_node);
            	ary.set(data_node, i_swap);
            }
            i_node = i_swap;
        } while (i_node >= 0);
	}

	@Override
	public TData remove() {
		TData data = ary.get(0);
        ary.set(ary.get(count--), 0);
        trickledown(0);
//        if (3 * count < ary.getSize()) 
//        	ary.resize();
        return data;
	}
}
