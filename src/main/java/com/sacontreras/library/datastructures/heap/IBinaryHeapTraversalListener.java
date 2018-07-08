package com.sacontreras.library.datastructures.heap;

import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;

public interface IBinaryHeapTraversalListener<TData> extends IBinaryTreeTraversalListener<TData> {
	default void onNodeVisted(final TData data, final DISPOSITION disp, final TData data_parent) {}
	default void onNullNode() {}
	
	void onNodeVisted(final int i_node, final TData node_data, final DISPOSITION disp, final int i_parent, final TData parent_data);
	void onNullNode(final int i_node);
}
