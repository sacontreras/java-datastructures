package com.sacontreras.library.algorithms.sort.mergesort.inplace;

import java.util.Arrays;

import com.sacontreras.library.algorithms.sort.mergesort.MergeSorter;

public class InplaceMergeSorter<TData extends Comparable<TData>> implements MergeSorter<TData> {
	@Override
	public void mergeSort(TData[] ary) {
		if (ary == null)
			throw new NullPointerException("ary cannot be null");
		if (ary.length == 0)
			return;
		TData[] temp = Arrays.copyOf(ary, ary.length);
		mergeSort(ary, temp, 0, ary.length - 1);
	}
	
	private void mergeSort(TData[] ary, TData[] temp, int left, int right) {
		if (right > left) {
			int mid = (left + right)/2;
			mergeSort(ary, temp, left, mid);
			mergeSort(ary, temp, mid + 1, right);
			sortmerge(ary, temp, left, mid + 1, right);
		}
	}
	
	private void sortmerge(TData[] ary, TData[] temp, int left, int mid, int right) {
		int left_end, size, temp_pos;
		
		temp_pos = left;
		left_end = mid - 1;
		//right_start = mid + 1;
		size = right - left + 1;
		
		while (left <= left_end && mid <= right) {
			if (ary[left].compareTo(ary[mid]) <= 0) {
				temp[temp_pos] = ary[left];
				temp_pos += 1;	//move temp_pos to the right by one
				left += 1;		//also move left endpoint to the right by one
			} else {
				temp[temp_pos] = ary[mid];
				temp_pos += 1;	//move temp_pos to the right by one
				mid += 1;		//move mid to the right by one
			}
		}
		
		while (left <= left_end) {
			temp[temp_pos] = ary[left];
			left += 1;		//also move left endpoint to the right by one
			temp_pos += 1; 	//move temp_pos to the right by one
		}
		
		while (mid <= right) {
			temp[temp_pos] = ary[mid];
			mid += 1;		//move mid to the right by one
			temp_pos += 1; 	//move temp_pos to the right by one
		}
		
		for (int i = 0; i < size; i++) {
			ary[right] = temp[right];
			right -= 1;
		}
	}
}
