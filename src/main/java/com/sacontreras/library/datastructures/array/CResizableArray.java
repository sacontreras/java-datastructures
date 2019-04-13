package com.sacontreras.library.datastructures.array;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.util.Generics;
import com.sacontreras.library.util.MathStuff;

public class CResizableArray<TData> {
	
	public final static int DEFAULT_CAPACITY = 16;
	public final static float DEFAULT_LOAD_FACTOR_THRESHOLD = 1.0f;
	
	private int capacity = DEFAULT_CAPACITY;
	private final float load_factor_threshold;
	
	private BoxedType<TData>[] backing_array = null;
    private int load = 0;
    
    public CResizableArray(final int capacity, final float load_factor_threshold) {
    	if (capacity < 0)
    		throw new ArrayIndexOutOfBoundsException();
    	if (!(0.0f < load_factor_threshold && load_factor_threshold <= 1.0f))
    		throw new IllegalArgumentException();
    	backing_array = Generics.newBoxedTypeArray(capacity);
        System.out.printf("CResizableArray::ctor: backing_array[] allocated with capacity (size): %d\n", backing_array.length);
    	this.capacity = capacity;
    	this.load_factor_threshold = load_factor_threshold;
    }
    public CResizableArray(final int capacity) {
    	this(capacity, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    public CResizableArray(final float load_factor) {
    	this(DEFAULT_CAPACITY, load_factor);
    }
    public CResizableArray() {
    	this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }

    private void resize(int basis) {
    	int new_capacity = -1;
		
    	//compute new capacity (size)
    	if (basis <= 0)
    		new_capacity = DEFAULT_CAPACITY;
    	else {
    		//get smallest DEFAULT_CAPACITY * 2^n > basis; we must solve for n, then add 1; log2(DEFAULT_CAPACITY * 2^n) > log2 (basis) --> log2 (DEFAULT_CAPACITY) + log2 (2^n) > log2 (basis) --> n > log2 (basis) - log2 (DEFAULT_CAPACITY)
    		int times_resized = (int)(MathStuff.getInstance().log_b(basis, 2) - MathStuff.getInstance().log_b(DEFAULT_CAPACITY, 2));
    		new_capacity = DEFAULT_CAPACITY * (int)Math.pow(2, times_resized + 1);
    	}
        System.out.printf("CResizableArray::resize: allocating new_backing_array with capacity: %d\n", new_capacity);
    	BoxedType<TData>[] new_backing_array = Generics.newBoxedTypeArray(new_capacity);
    	for (int i = 0; i < load; i++)
    		new_backing_array[i] = backing_array[i];
        System.out.printf("CResizableArray::resize: set elements at indexes 0 through %d of new_backing_array to those from backing_array\n", load-1);
    	capacity = new_capacity;
    	backing_array = new_backing_array;
        System.out.printf("CResizableArray::resize: new_backing_array assigned to backing_array\n", load);
    }
    private void resize() {
    	resize(capacity);
    }
    
    public void add(final TData data) {
        System.out.printf("CResizableArray::add: setting backing_array[%d] ...\n", load);
    	if (capacity == 0 || getLoadFactor() >= load_factor_threshold) {
            resize();
            System.out.printf("CResizableArray::add: backing_array[] now has capacity: %d\n", backing_array.length);
        }
    	backing_array[load++] = new BoxedType<TData>(data);
        System.out.printf("CResizableArray::add: backing_array[%d] == " + data + "\n", load-1);
    }
    
    public void set(final TData data, final int index) {
    	boolean resized = false;
    	if (index >= capacity) {
    		resize(index);
    		resized = true;
    	}
    	backing_array[index] = new BoxedType<TData>(data);
    	if (resized)
    		load = index;
    }
    
    public TData get(final int index) {
    	if (index >= 0 && index < backing_array.length ) {
    		BoxedType<TData> tmp = null;
    		return (tmp = backing_array[index]) != null ? tmp.getValue() : null;
    	}
    	return null;
    }
    
    public TData remove(final int index) {
    	TData data = get(index);
    	if (data != null)
    		backing_array[--load] = null;
    	return data;
    }
    
    public int getSize() {
    	return load;
    }
    
    public double getLoadFactor() {
    	return ((double)load)/((double)capacity);
    }
    
    public double getLoadFactorThreshold() {
    	return load_factor_threshold;
    }
    
    public int getCapacity() {
    	return capacity;
    }


    public static void main(String[] args) {
        CResizableArray<Integer> int_ary = new CResizableArray<Integer>();

        int i = 0;

        //add first 16 integers to int_ary
        for (; i < 16; i++)
            int_ary.add(i + 1);

        //add 17th integer to int_ary - note that this will resize the backing array to twice its original capacity (16) - should be 32 after
        int_ary.add(i++ + 1);

        //add next 15 integers (at indexes 16 through 31 of backing array)
        for (; i < 32; i++)
            int_ary.add(i + 1);

        //add 33rd integer to int_ary - note that this will resize the backing array to twice its previous capacity (32) - should be 64 after
        int_ary.add(i + 1);
    }
}
