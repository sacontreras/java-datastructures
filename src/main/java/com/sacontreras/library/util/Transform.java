package com.sacontreras.library.util;

import java.util.Comparator;
import java.util.List;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.CBinarySearchTree;

public class Transform {

	public static <TData> 
	TData[] to_array(final List<TData> list) {
	    if (list == null || list.isEmpty())
	    	return null;
	    final TData data = list.get(0);
	    final TData[] ary = Generics.<TData>newArray(data, list.size());
	    for (int i = 0; i < list.size(); i++)
	        ary[i] = list.get(i);
	    return ary;
	}
	
	final public static <TData> 
	TData[] to_array(final CLinkedListQueue<TData> queue) {
		if (queue == null || queue.isEmpty())
	    	return null;
	    final TData data = queue.front();
	    final TData[] ary = Generics.<TData>newArray(data, queue.getSize());
	    int i = 0;
	    while (!queue.isEmpty())
	    	ary[i++] = queue.poll();
	    return ary;
	}
	
	final public static <TData> 
	CLinkedListQueue<TData> to_queue(final TData[] ary_data) {
		final CLinkedListQueue<TData> queue = new CLinkedListQueue<TData>();
		if (ary_data != null && ary_data.length > 0) {
			for (int i = 0; i < ary_data.length; i++)
				queue.enqueue(ary_data[i]);
		}
		return queue;
	}
	
	final public static <TData> 
	CBinarySearchTree<TData> to_bst(final TData[] ary_data, final Comparator<TData> comparator) {
		final CBinarySearchTree<TData> bst = new CBinarySearchTree<TData>(comparator);
		if (ary_data != null && ary_data.length > 0) {
			for (int i = 0; i < ary_data.length; i++)
				bst.add(ary_data[i]);
		}
		return bst;
	}
}
