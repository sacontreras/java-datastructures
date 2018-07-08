import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.array.CResiableArray;

public class Datastructures_CResizableArray_Test {

	@Test
	@DisplayName("test_CResizableArray")
	public void test_CResizableArray() {
		CResiableArray<Integer> int_ary = new CResiableArray<Integer>(0);
		
		int
			i_expect = 0,
			i_result = int_ary.getSize();
		assertEquals(
			i_expect,
			i_result
		);
		
		int times_resized = -1;
		int_ary.add(0);	//since we start with 0 capacity, first call to add() should result in allocating backing array with DEFAULT_CAPACITY size
		times_resized++;
		int new_capacity = (int)(CResiableArray.DEFAULT_CAPACITY * Math.pow(2, times_resized));	//CResiableArray.DEFAULT_CAPACITY
		i_expect = new_capacity;
		i_result = int_ary.getSize();
		assertEquals(
			i_expect,
			i_result
		);
		double d_expect = ((double)int_ary.getLoad())/((double)new_capacity);
		double d_result = int_ary.getLoadFactor();
		assertEquals(
			d_expect,
			d_result
		);
		
		//fill until load_factor == 1.0
		for (int i = 1; i < CResiableArray.DEFAULT_CAPACITY; i++)
			int_ary.add(i);
		i_expect = new_capacity;	//still CResiableArray.DEFAULT_CAPACITY, shouldn't have changed
		i_result = int_ary.getSize();
		assertEquals(
			i_expect,
			i_result
		);
		d_expect = 1.0;
		d_result = int_ary.getLoadFactor();
		assertEquals(
			d_expect,
			d_result
		);
		
		
		int_ary.add(-97);	//load_factor should currently be at 1.0, resulting in internal call to resize()
		times_resized++;	//times_resized==1
		int prev_capacity = new_capacity;	//was 
		i_expect = new_capacity = (int)(CResiableArray.DEFAULT_CAPACITY * Math.pow(2, times_resized));	//eval new_capacity==32
		//size (capacity) of array should now be 32
		i_result = int_ary.getSize();
		assertEquals(
			i_expect,
			i_result
		);
		//load should now be (prev_capacity+1)
		i_expect = prev_capacity + 1;
		i_result = int_ary.getLoad();
		assertEquals(
			i_expect,
			i_result
		);
		
		int_ary.set(-1, 0);	//this should NOT resize array since we are setting an index below capacity, so we expect current value in new_capacity (not recomputed)
		i_expect = new_capacity;
		i_result = int_ary.getSize();
		assertEquals(
			i_expect,
			i_result
		);
		//load should still be (prev_capacity+1)
		i_expect = prev_capacity + 1;
		i_result = int_ary.getLoad();
		assertEquals(
			i_expect,
			i_result
		);
		
		//since at this point we have only resized twice, capacity should be at 32 (n==0 --> new_capacity==(int)(CResiableArray.DEFAULT_CAPACITY * Math.pow(2, n + 1)))
		//	and since we attempt to set at index >= capacity, we will resize with an explicit basis internally, which will be equivalent to 4 resizes (0th resize creates with DEFAULT_CAPACITY)
		//	4 resizes would amount to a size/capacity of 256, which is what we should have after...
		int_ary.set(-3233, 255);
		times_resized = 4;
		i_expect = new_capacity = (int)(CResiableArray.DEFAULT_CAPACITY * Math.pow(2, times_resized));	//256
		i_result = int_ary.getSize();
		assertEquals(
			i_expect,
			i_result
		);
		d_expect = ((double)255/(double)256);
		d_result = int_ary.getLoadFactor();
		assertEquals(
			d_expect,
			d_result
		);
		
		int_ary.set(-47, 256);	//should resize to 512
		times_resized = 5;
		i_expect = new_capacity = (int)(CResiableArray.DEFAULT_CAPACITY * Math.pow(2, times_resized));	//512
		i_result = int_ary.getSize();
		assertEquals(
			i_expect,
			i_result
		);
		d_expect = ((double)256/(double)512);
		d_result = int_ary.getLoadFactor();
		assertEquals(
			d_expect,
			d_result
		);
	}
}
