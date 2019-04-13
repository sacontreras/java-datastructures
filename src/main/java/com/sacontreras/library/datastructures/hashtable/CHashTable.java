package com.sacontreras.library.datastructures.hashtable;

import com.sacontreras.library.CKeyValuePair;
import com.sacontreras.library.datastructures.map.IMap;
import com.sacontreras.library.hash.IHashable;

//abstract class - the idea is for descendants to implement one of the following particular collision resolution technique
//  1. direct chaining - separate chaining
//  2. open addressing
//      a. linear probing
//      b. quadratic probing
//      c. double-hashing

public abstract class CHashTable<TKey, TValue, THasher extends IHashable<TKey, Integer>> implements IMap<TKey, TValue> {
    protected int load;
    protected int capacity;
    protected final float load_factor_threshold;

    protected final THasher hasher;


    public int getSize() {
        return load;
    }
    public float getLoadFactor() {
        return ((float)load)/((float)capacity);
    }
    public float getLoadFactorThreshold() {
        return load_factor_threshold;
    }
    public int getCapacity() {
        return capacity;
    }

    public CHashTable(final float load_factor_threshold, final THasher hasher) {
        this.load_factor_threshold = load_factor_threshold;
        this.load = 0;
        this.hasher = hasher;
    }
}
