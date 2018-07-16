package com.sacontreras.library.datastructures.tree;

public class CBinaryTreeNode<TData> {
	protected CBinaryTreeNode<TData> parent = null;
	protected TData data = null;
	protected CBinaryTreeNode<TData> left = null;
	protected CBinaryTreeNode<TData> right = null;
	
	public void setParent(final CBinaryTreeNode<TData> parent) {
		this.parent = parent;
	}
	public CBinaryTreeNode<TData> getParent() {
		return parent;
	}
	
	public void setData(final TData data) {
		this.data = data;
	}
	public TData getData() {
		return data;
	}
	
	public void setLeft(final CBinaryTreeNode<TData> left) {
		this.left = left;
	}
	public CBinaryTreeNode<TData> getLeft() {
		return left;
	}
	
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
