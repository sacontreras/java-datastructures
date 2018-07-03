package com.sacontreras.library.datastructures.tree;

public class CBinaryTreeNode<TData> {
	protected TData data = null;
	private int count = 0;
	public void setData(final TData data) {
		if (this.data == null || this.data != data)
			count = 0;
		this.data = data;
		count++;
	}
	
	protected CBinaryTreeNode<TData> parent = null;
	public void setParent(final CBinaryTreeNode<TData> parent) {
		this.parent = parent;
	}
	public CBinaryTreeNode<TData> getParent() {
		return parent;
	}
	
	protected CBinaryTreeNode<TData> left = null;
	public void setLeft(final CBinaryTreeNode<TData> left) {
		this.left = left;
	}
	public CBinaryTreeNode<TData> getLeft() {
		return left;
	}
	
	protected CBinaryTreeNode<TData> right = null;
	public void setRight(final CBinaryTreeNode<TData> right) {
		this.right = right;
	}
	public CBinaryTreeNode<TData> getRight() {
		return right;
	}
	
	public CBinaryTreeNode(final TData data, final CBinaryTreeNode<TData> parent, final CBinaryTreeNode<TData> left, final CBinaryTreeNode<TData> right) {
		setData(data);
		setParent(parent);
		setLeft(left);
		setRight(right);
	}
	public CBinaryTreeNode(final TData data, final CBinaryTreeNode<TData> parent) {
		this(data, parent, null, null);
	}
	public CBinaryTreeNode(final TData data) {
		this(data, null, null, null);
	}
	public CBinaryTreeNode() {
		this(null, null, null, null);
	}
}
