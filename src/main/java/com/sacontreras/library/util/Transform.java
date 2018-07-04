package com.sacontreras.library.util;

import java.lang.reflect.Array;
import java.util.List;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;

public class Transform {

	@SuppressWarnings("unchecked")
	public static <TData> TData[] to_array(final List<TData> list) {
	    if (list == null || list.isEmpty())
	    	return null;
	    final TData data = list.get(0);
	    final TData[] ary = (TData[])Array.newInstance(data.getClass(), list.size());
	    for (int i = 0; i < list.size(); i++)
	        ary[i] = list.get(i);
	    return ary;
	}
	
	@SuppressWarnings("unchecked")
	final public static <TData> TData[] to_array(final CLinkedListQueue<TData> queue) {
		if (queue == null || queue.isEmpty())
	    	return null;
	    final TData data = queue.front();
	    final TData[] ary = (TData[])Array.newInstance(data.getClass(), queue.getSize());
	    int i = 0;
	    while (!queue.isEmpty())
	    	ary[i++] = queue.poll();
	    return ary;
	}
	
	final public static <TData> CLinkedListQueue<TData> to_queue(final TData[] ary_data) {
		final CLinkedListQueue<TData> queue = new CLinkedListQueue<TData>();
		if (ary_data != null && ary_data.length > 0) {
			for (int i = 0; i < ary_data.length; i++)
				queue.enqueue(ary_data[i]);
		}
		return queue;
	}
}
