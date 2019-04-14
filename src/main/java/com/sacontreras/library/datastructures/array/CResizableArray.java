package com.sacontreras.library.datastructures.array;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.util.Generics;
import com.sacontreras.library.util.MathStuff;

public class CResizableArray<TData> {
	
	public final static int DEFAULT_CAPACITY = 16;
	public final static float DEFAULT_LOAD_FACTOR_THRESHOLD = 1.0f;
	
	private int capacity = DEFAULT_CAPACITY;
	private final float loadFactorThreshold;
	
	private BoxedType<TData>[] backingArray = null;
    private int load = 0;
    
    public CResizableArray(final int capacity, final float loadFactorThreshold) {
    	if (capacity < 0)
    		throw new ArrayIndexOutOfBoundsException();
    	if (!(0.0f < loadFactorThreshold && loadFactorThreshold <= 1.0f))
    		throw new IllegalArgumentException();
    	backingArray = Generics.newBoxedTypeArray(capacity);
        //System.out.printf("CResizableArray::ctor: backing_array[] allocated with capacity (size): %d\n", backing_array.length);
    	this.capacity = capacity;
    	this.loadFactorThreshold = loadFactorThreshold;
    }
    public CResizableArray(final int capacity) {
    	this(capacity, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    public CResizableArray(final float loadFactor) {
    	this(DEFAULT_CAPACITY, loadFactor);
    }
    public CResizableArray() {
    	this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }

    private void resize(int basis) {
    	int newCapacity = -1;
		
    	//compute new capacity (size)
    	if (basis <= 0)
    		newCapacity = DEFAULT_CAPACITY;
    	else {
    		//get smallest DEFAULT_CAPACITY * 2^n > basis; we must solve for n, then add 1; log2(DEFAULT_CAPACITY * 2^n) > log2 (basis) --> log2 (DEFAULT_CAPACITY) + log2 (2^n) > log2 (basis) --> n > log2 (basis) - log2 (DEFAULT_CAPACITY)
    		int timesResized = (int)(MathStuff.getInstance().log_b(basis, 2) - MathStuff.getInstance().log_b(DEFAULT_CAPACITY, 2));
    		newCapacity = DEFAULT_CAPACITY * (int)Math.pow(2, timesResized + 1);
    	}
        //System.out.printf("CResizableArray::resize: allocating new_backing_array with capacity: %d\n", new_capacity);
    	BoxedType<TData>[] newBackingArray = Generics.newBoxedTypeArray(newCapacity);
    	for (int i = 0; i < load; i++)
    		newBackingArray[i] = backingArray[i];
        //System.out.printf("CResizableArray::resize: set elements at indexes 0 through %d of new_backing_array to those from backing_array\n", load-1);
    	capacity = newCapacity;
    	backingArray = newBackingArray;
        //System.out.printf("CResizableArray::resize: new_backing_array assigned to backing_array\n", load);
    }
    private void resize() {
    	resize(capacity);
    }
    
    public void add(final TData data) {
        //System.out.printf("CResizableArray::add: setting backing_array[%d] ...\n", load);
    	if (capacity == 0 || getLoadFactor() >= loadFactorThreshold) {
            resize();
            //System.out.printf("CResizableArray::add: backing_array[] now has capacity: %d\n", backing_array.length);
        }
    	backingArray[load++] = new BoxedType<>(data);
        //System.out.printf("CResizableArray::add: backing_array[%d] == " + data + "\n", load-1);
    }
    
    public void set(final TData data, final int index) {
    	boolean resized = false;
    	if (index >= capacity) {
    		resize(index);
    		resized = true;
    	}
    	backingArray[index] = new BoxedType<>(data);
    	if (resized)
    		load = index;
    }
    
    public TData get(final int index) {
    	if (index >= 0 && index < backingArray.length ) {
    		BoxedType<TData> tmp = null;
    		return (tmp = backingArray[index]) != null ? tmp.getValue() : null;
    	}
    	return null;
    }
    
    public TData remove(final int index) {
    	TData data = get(index);
    	if (data != null)
    		backingArray[--load] = null;
    	return data;
    }
    
    public int getSize() {
    	return load;
    }
    
    public double getLoadFactor() {
    	return ((double)load)/((double)capacity);
    }
    
    public double getLoadFactorThreshold() {
    	return loadFactorThreshold;
    }
    
    public int getCapacity() {
    	return capacity;
    }
}
