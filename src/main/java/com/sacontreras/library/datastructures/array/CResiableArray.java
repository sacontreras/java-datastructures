package com.sacontreras.library.datastructures.array;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.util.Generics;
import com.sacontreras.library.util.MathStuff;

public class CResiableArray<TData> {
	
	public final static int DEFAULT_CAPACITY = 16;
	public final static double DEFAULT_LOAD_FACTOR_THRESHOLD = 1.0;
	
	private int capacity = DEFAULT_CAPACITY;
	private final double load_factor_threshold;
	
	private BoxedType<TData>[] ary = null;
    private int load = 0;
    private double load_factor = 0;
    
    public CResiableArray(final int capacity, final double load_factor_threshold) {
    	if (capacity < 0)
    		throw new ArrayIndexOutOfBoundsException();
    	if (!(0.0 < load_factor_threshold && load_factor_threshold <= 1.0))
    		throw new IllegalArgumentException();
    	ary = Generics.newBoxedTypeArray(capacity);
    	this.capacity = capacity;
    	this.load_factor_threshold = load_factor_threshold;
    }
    public CResiableArray(final int capacity) {
    	this(capacity, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    public CResiableArray(final double load_factor) {
    	this(DEFAULT_CAPACITY, load_factor);
    }
    public CResiableArray() {
    	this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }

    private void resize(int basis) {
    	int new_capacity = -1;
		
    	//compute new capacity (size)
    	if (basis <= 0)
    		new_capacity = DEFAULT_CAPACITY;
    	else {
    		//get smallest DEFAULT_CAPACITY * 2^n > basis; we must solve for n, then add 1; log2(DEFAULT_CAPACITY * 2^n) > log2 (basis) --> log2 (DEFAULT_CAPACITY) + log2 (2^n) > log2 (basis) --> n > log2 (basis) - log2 (DEFAULT_CAPACITY)
    		int times_resized = (int)(MathStuff.log_b(basis, 2) - MathStuff.log_b(DEFAULT_CAPACITY, 2));
    		new_capacity = DEFAULT_CAPACITY * (int)Math.pow(2, times_resized + 1);
    	}
    	BoxedType<TData>[] new_ary = Generics.newBoxedTypeArray(new_capacity);
    	for (int i = 0; i < load; i++)
    		new_ary[i] = ary[i];
    	capacity = new_capacity;
    	ary = new_ary;
    }
    private void resize() {
    	resize(capacity);
    }
    
    public void add(final TData data) {
    	if (capacity == 0 || load_factor >= load_factor_threshold)
    		resize();
    	ary[load++] = new BoxedType<TData>(data);
    	load_factor = ((double)load)/((double)capacity);
    }
    
    public void set(final TData data, final int index) {
    	boolean resized = false;
    	if (index >= capacity) {
    		resize(index);
    		resized = true;
    	}
    	ary[index] = new BoxedType<TData>(data);
    	if (resized) {
    		load = index;
    		load_factor = ((double)load)/((double)capacity);
    	}
    }
    
    public TData get(final int index) {
    	if (index >= 0 && index < ary.length ) {
    		BoxedType<TData> tmp = null;
    		return (tmp = ary[index]) != null ? tmp.getValue() : null;
    	}
    	return null;
    }
    
    public int getLoad() {
    	return load;
    }
    
    public double getLoadFactor() {
    	return load_factor;
    }
    
    public int getSize() {
    	return capacity;
    }
}
