package com.sacontreras.library.algorithms.sort.mergesort.array;

import java.util.Comparator;

public class InplaceIncomparableArrayMergeSorter<TData, TComparator extends Comparator<TData>> implements IncomparableArrayMergeSorter<TData, TComparator> {

	@Override
	public void mergeSort(TData[] ary, TComparator comparator) {
		if (ary == null)
			throw new NullPointerException("ary cannot be null");
		if (ary.length == 0)
			return;
		mergeSort(ary, 0, ary.length-1, comparator);
	}
	
	//this function applies Divide and Conquer
	private void mergeSort(TData[] ary, int lbound, int rbound, TComparator comparator) {
		//this is a recursive function, so we need a base case
		if (lbound == rbound)	//then we have sub-divided ary until the point that the current sub-array contains only a single element
			return;
			
		//otherwise, sub-divide ary,	
		//e.g. {74, 4, -12, 8, 9, 7, 2, 0}		should produce the following logical subdivisions
		//		  |				 |
		//{-74, 4, -12, 8}	{9, 7, 2, 0}
		//	  |   	   |	  |		  |
		//{-74, 4} {-12, 8}	 {9, 7}   {2, 0}
		//  |    |   |    |   |   |    |  |
		//{-74} {4} {-12} {8} {9} {7} {2} {0}
		
		//first step is to find the index that will sub-divide ary into two logical partitions
		int lpartEnd = (lbound + rbound)/2;	//note that since this is an int, we will implicitly round down
		
		//now recursively partition ary into left and right sub-arrays
		//left partition will consist of elements from ary ranging from indexes lbound to lpartEnd
		mergeSort(ary, lbound, lpartEnd, comparator);
		//right partition will consist of elements from ary ranging from indexes lpartEnd+1 to rbound
		mergeSort(ary, lpartEnd+1, rbound, comparator);
		
		//now we need to sort/merge the left and right partitions
		sortMerge(ary, lbound, lpartEnd, rbound, comparator);
	}
	
	private void sortMerge(TData[] ary, int lbound, int leftpartEnd, int rbound, TComparator comparator) {
		//pivot on left partition first
		int inspectPos = lbound;
		int rightpartPos = leftpartEnd+1;		
		while (inspectPos < leftpartEnd+1) {
			if (comparator.compare(ary[inspectPos], ary[rightpartPos]) > 0) {	//then item in left partition is greater than item in right partition
				//so swap items
				TData swap = ary[inspectPos];
				ary[inspectPos] = ary[rightpartPos];
				ary[rightpartPos] = swap;	
			}
			if (rightpartPos < rbound)
				rightpartPos++;
			else {
				inspectPos++;
				rightpartPos = leftpartEnd+1;
			}
		}
		
		//now reconcile right partition
		inspectPos = leftpartEnd+1;
		rightpartPos = leftpartEnd+2;
		while (inspectPos < rbound) {
			if (comparator.compare(ary[inspectPos], ary[rightpartPos]) > 0) {
				//so swap items
				TData swap = ary[inspectPos];
				ary[inspectPos] = ary[rightpartPos];
				ary[rightpartPos] = swap;	
			} 
			if (rightpartPos < rbound)
				rightpartPos++;
			else {
				inspectPos++;
				rightpartPos = inspectPos+1;
			}
		}
	}
}
