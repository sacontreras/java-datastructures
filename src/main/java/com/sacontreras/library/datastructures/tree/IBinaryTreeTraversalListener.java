package com.sacontreras.library.datastructures.tree;

public interface IBinaryTreeTraversalListener<TData> {
	enum DISPOSITION {
		ROOT,
		LEFT_CHILD,
		RIGHT_CHILD
	}
	void onNodeVisted(final TData data, final DISPOSITION disp, final TData data_parent);
	void onNullNode();
}
