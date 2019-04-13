package com.sacontreras.library.datastructures.heap;

import com.sacontreras.library.datastructures.heap.CBinaryHeap;
import com.sacontreras.library.datastructures.heap.IBinaryHeapTraversalListener;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.MockTrees;

public class MockHeaps {

	public static class CIntegerBinaryHeap extends CBinaryHeap<Integer> {
	
		public CIntegerBinaryHeap() {
			super(new MockTrees.CIntegerComparator());
		}
		
		public static CIntegerBinaryHeap fromArray(final Integer[] ary_data) {
			CIntegerBinaryHeap heap = new CIntegerBinaryHeap(); 
			if (ary_data != null && ary_data.length > 0) {
				for (int i = 0; i < ary_data.length; i++)
					heap.add(ary_data[i]);
			}
			return heap;
		}
	}

	public static class CIntegerBinaryHeapTraversalListener implements IBinaryHeapTraversalListener<Integer> {
		private final String tag;
		private final String order;
		
		public final CLinkedListQueue<Integer> q_visit_order = new CLinkedListQueue<Integer>();
		
		public CIntegerBinaryHeapTraversalListener(final String tag, final String order) {
			this.tag = tag;
			this.order = order;
		}
		
		@Override
		public void onNodeVisted(int i_node, Integer node_data, DISPOSITION disp, int i_parent, Integer parent_data) {
			System.out.println(String.format("%s::onNodeVisted-%s: index: %d, value: %d, disp: %s, parent-index: %d, parent-value: %d", tag, order, i_node, node_data, disp.name(), i_parent, parent_data));
			q_visit_order.enqueue(node_data);	
		}
	
		@Override
		public void onNullNode(int i_node) {
			//System.out.println(String.format("%s::onNullNode-%s: index: %d", tag, order, i_node));
		}
	}

}
