package com.sacontreras.library.datastructures.tree;

public interface IBinaryTreeTraversalListener<TData> {
	void onNodeVisted(final TData data);
	void onNullNode();
}
