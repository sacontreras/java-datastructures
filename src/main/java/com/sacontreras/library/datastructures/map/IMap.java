package com.sacontreras.library.datastructures.map;

import com.sacontreras.library.CKeyValuePair;

public interface IMap<TKey, TValue> {
    CKeyValuePair<TKey, TValue> find(CKeyValuePair<TKey, TValue> kvp);
	boolean contains(TKey key);
	boolean put(TKey key, TValue value);
    TValue get(TKey key);
    TValue remove(TKey key);
	int getSize();
}
