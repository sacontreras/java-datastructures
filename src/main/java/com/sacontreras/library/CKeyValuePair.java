package com.sacontreras.library;

public class CKeyValuePair<TKey, TValue> {
	final private TKey key;
	final public TKey getKey() {
		return key;
	}

	private TValue value = null;
	public void setValue(final TValue value) {
		this.value = value;
	}
	public TValue getValue() {
		return value;
	}
	
	public CKeyValuePair(final TKey key, final TValue value) {
		this.key = key;
		this.value = value;
	}
	public CKeyValuePair(final TKey key) {
		this(key, null);
	}
	private CKeyValuePair() {
		this.key = null;
		this.value = null;
	}
}
