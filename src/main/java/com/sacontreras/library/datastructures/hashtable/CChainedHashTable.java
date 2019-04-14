package com.sacontreras.library.datastructures.hashtable;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.CKeyValuePair;
import com.sacontreras.library.datastructures.linkedlist.CLinkedList;
import com.sacontreras.library.datastructures.linkedlist.LinkedListException;
import com.sacontreras.library.util.Generics;
import com.sacontreras.library.util.MathStuff;
import java.util.Iterator;

public class CChainedHashTable<TKey, TValue> extends CHashTable<TKey, TValue, ChainedHashTableKeyHasher<TKey>> {

    public final static int DEFAULT_CAPACITY = 16;

    //we choose a load factor threshold of 100% - see image: https://en.wikibooks.org/wiki/Data_Structures/Hash_Tables#/media/File:Hash_table_average_insertion_time.png
    public static final float LOAD_FACTOR_THRESHOLD = 1.0f;

    private BoxedType<CLinkedList<CKeyValuePair<TKey, TValue>>>[] backing_array = null;

    public CChainedHashTable(int initial_capacity, ChainedHashTableKeyHasher chainedHashTableKeyHasher) {
        super(LOAD_FACTOR_THRESHOLD, chainedHashTableKeyHasher);
        if (initial_capacity < DEFAULT_CAPACITY)
            initial_capacity = DEFAULT_CAPACITY;
        this.backing_array = Generics.newBoxedTypeArray(initial_capacity);
        for (int i = 0; i < initial_capacity; i++)
            this.backing_array[i] = new BoxedType<CLinkedList<CKeyValuePair<TKey, TValue>>>(new CLinkedList<CKeyValuePair<TKey, TValue>>());
        this.capacity = initial_capacity;
    }

    public CChainedHashTable(ChainedHashTableKeyHasher chainedHashTableKeyHasher) {
        this(DEFAULT_CAPACITY, chainedHashTableKeyHasher);
    }

    private final CLinkedList<CKeyValuePair<TKey, TValue>> get_chain(int index) {
        CLinkedList<CKeyValuePair<TKey, TValue>> kvp_chain_hash_match = null;
        try {
            kvp_chain_hash_match = backing_array[index].getValue();
        } catch (Exception e) {}
        return kvp_chain_hash_match;
    }
    private final CLinkedList<CKeyValuePair<TKey, TValue>> get_chain(TKey key) {
        return get_chain(hasher.hash(key, capacity));
    }

    //The performance of a hash table depends critically on the choice of the hash function.
    // A good hash function will spread the elements evenly among the t.length lists,
    // so that the expected size of the list t[hash(x)] is O(n/t.length) = O(1).
    // On the other hand, a bad hash function will hash all values (including x) to the same table location,
    // in which case the size of the list t[hash(x)] will be n.
    public CKeyValuePair<TKey, TValue> find_by_value(TValue value, CLinkedList<CKeyValuePair<TKey, TValue>> kvp_chain_hash_match) {
        if (kvp_chain_hash_match != null) {
            for (CKeyValuePair<TKey, TValue> kvp_hash_match : kvp_chain_hash_match)
                if (kvp_hash_match.getValue().equals(value))
                    return kvp_hash_match;
        }
        return null;
    }
    public CKeyValuePair<TKey, TValue> find_by_key(TKey key, CLinkedList<CKeyValuePair<TKey, TValue>> kvp_chain_hash_match) {
        if (kvp_chain_hash_match != null) {
            for (CKeyValuePair<TKey, TValue> kvp_hash_match : kvp_chain_hash_match)
                if (kvp_hash_match.getKey().equals(key))
                    return kvp_hash_match;
        }
        return null;
    }
    @Override
    public CKeyValuePair<TKey, TValue> find(CKeyValuePair<TKey, TValue> a_kvp) {
        return find_by_value(a_kvp.getValue(), get_chain(a_kvp.getKey()));
    }
    public CKeyValuePair<TKey, TValue> find(TKey key) {
        return find_by_key(key, get_chain(key));
    }

    @Override
    public boolean contains(TKey key) {
        return get_chain(key) != null;
    }

    private void rehash(int basis) {
        int new_capacity = -1;

        //compute new capacity (size)
        if (basis < DEFAULT_CAPACITY)
            new_capacity = DEFAULT_CAPACITY;
        else {
            //get smallest DEFAULT_CAPACITY * 2^n > basis; we must solve for n, then add 1; log2(DEFAULT_CAPACITY * 2^n) > log2 (basis) --> log2 (DEFAULT_CAPACITY) + log2 (2^n) > log2 (basis) --> n > log2 (basis) - log2 (DEFAULT_CAPACITY)
            int times_resized = (int)(MathStuff.getInstance().log_b(basis, 2) - MathStuff.getInstance().log_b(DEFAULT_CAPACITY, 2));
            //System.out.println(String.format("CChainedHashTable::rehash: times_resized==%d", times_resized));
            new_capacity = DEFAULT_CAPACITY * (int)Math.pow(2, times_resized + 1);
        }
        //System.out.println(String.format("CChainedHashTable::rehash: set new_capacity==%d", new_capacity));

        BoxedType<CLinkedList<CKeyValuePair<TKey, TValue>>>[] new_backing_array = Generics.newBoxedTypeArray(new_capacity);
        for (int i = 0; i < new_capacity; i++)
            new_backing_array[i] = new BoxedType<CLinkedList<CKeyValuePair<TKey, TValue>>>(new CLinkedList<CKeyValuePair<TKey, TValue>>());

        int index;
        for (int i = 0; i < capacity; i++) {
            //go through each kvp in each list of the backing array
            //  rehash key of each kvp using new capacity and move to new bucket/list
            CLinkedList<CKeyValuePair<TKey, TValue>> kvp_chain = backing_array[i].getValue();
            CLinkedList.Node.DataIterator<CKeyValuePair<TKey, TValue>> kvp_iterator = kvp_chain.getStart();
            CKeyValuePair<TKey, TValue> kvp = null;
            while (kvp_iterator.hasNext()) {
                kvp = kvp_iterator.next();
                new_backing_array[hasher.hash(kvp.getKey(), new_capacity)].getValue().append(kvp);
            }

            //now clear out old linked list at i
            backing_array[i] = new BoxedType<CLinkedList<CKeyValuePair<TKey, TValue>>>(new CLinkedList<CKeyValuePair<TKey, TValue>>());
        }

        capacity = new_capacity;
        backing_array = new_backing_array;
    }
    private void rehash() {
        rehash(capacity);
    }

    @Override
    public boolean put(TKey key, TValue value) {
        CKeyValuePair<TKey, TValue> kvp = new CKeyValuePair<TKey, TValue>(key, value);

        int hash_val = hasher.hash(key, capacity);

        //first check to see if there is a bucket for this hash
        CLinkedList<CKeyValuePair<TKey, TValue>> kvp_chain = get_chain(hash_val);

        if (kvp_chain != null) {
            //System.out.println(String.format("CChainedHashTable::put: found kvp chain for hash value %d", hash_val));
            //now check to see if the kvp we are attempting to add already exists in the chain
            int n = 0;
            for (CKeyValuePair<TKey, TValue> kvp_hash_match : kvp_chain) {
                if (kvp_hash_match.getKey().equals(key)) {
                    //match
                    TValue old_Val = kvp_hash_match.getValue();
                    if (value != null) {
                        //System.out.println(String.format("CChainedHashTable::put: kvp already exists for key: \"%s\" (old val: \"%s\", new val: \"%s\")", key, old_Val, value));
                        try {
                            kvp_chain.set(n, new CKeyValuePair<TKey, TValue>(key, value));
                            return true;
                        } catch (LinkedListException e) {
                            e.printStackTrace();
                            return false;
                        }
                    } else {
                        //System.out.println(String.format("CChainedHashTable::put: kvp already exists for key: \"%s\" (cannot replace old val: \"%s\" with null)", key, old_Val));
                        return false;
                    }
                }
                n++;
            }
            //okay, a bucket exists already for this hash, but there is no kvp in the chain for this value, proceed...
        }

        ////System.out.println(String.format("CChainedHashTable::put: no kvp chain exists hash value %d", hasher.hash(key, this)));
        float load_factor = getLoadFactor();
        //System.out.println(String.format("CChainedHashTable::put: capacity is %d, load is %d --> load_factor==%f; load_factor_threshold==%f", capacity, load, load_factor, load_factor_threshold));
        //check load factor threshold first to determine if we need a new bucket
        if (capacity == 0 || load_factor >= load_factor_threshold) {
            //System.out.println(String.format("CChainedHashTable::put: capacity == 0 || load_factor (%f) >= load_factor_threshold (%f) --> resizing!", load_factor, load_factor_threshold));
            rehash();
        }

        if (kvp_chain == null) {
            //now create a new kbp chain and set it at position hash(key) in backing_array
            kvp_chain = new CLinkedList<CKeyValuePair<TKey, TValue>>();
            backing_array[hash_val] = new BoxedType<CLinkedList<CKeyValuePair<TKey, TValue>>>(kvp_chain);
        }

        //now add this kvp to kvp_chain
        //System.out.println(String.format("CChainedHashTable::put: putting kvp key==\"%s\", val==\"%s\" in kvp chain at index (hash val) %d", key, value, hash_val));
        kvp_chain.append(kvp);
        //System.out.println(String.format("CChainedHashTable::put: this kvp chain is %d elements in size", kvp_chain.getSize()));

        //don't forget to increment load!!!
        load++;
        //System.out.println(String.format("CChainedHashTable::put: load is now %d", load));

        return true;
    }

    public TValue get(TKey key) {
        CKeyValuePair<TKey, TValue> kvp_hash_match = find(key);
        return kvp_hash_match != null ? kvp_hash_match.getValue() : null;
    }

    public Iterator<CLinkedList<CKeyValuePair<TKey, TValue>>> iterator() {
        return new Iterator<CLinkedList<CKeyValuePair<TKey, TValue>>>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < backing_array.length;
            }

            @Override
            public CLinkedList<CKeyValuePair<TKey, TValue>> next() {
                BoxedType<CLinkedList<CKeyValuePair<TKey, TValue>>> bt_kvp_chain = null;
                while (index < backing_array.length && (bt_kvp_chain = backing_array[index++]) == null) {}   //skip over indexes for which a bucket has not been allocated
                return bt_kvp_chain != null ? bt_kvp_chain.getValue() : null;
            }
        };
    }

    public int bucket_count() {
        return backing_array.length;
    }

    @Override
    public TValue remove(TKey tKey) {
        return null;
    }
}
