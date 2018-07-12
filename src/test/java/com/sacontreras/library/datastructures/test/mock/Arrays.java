package com.sacontreras.library.datastructures.test.mock;

import java.text.ParseException;

public class Arrays {

	public static final Integer[] i_ary_expected = new Integer[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
	
	//Expected yield of traversals:
	//	pre-order (visit, left, right):
	//		30, 20, 10, 5, 15, 25, 45, 35, 40, 55, 50		
	public static final Integer[] i_ary_expected_preorder = new Integer[] {30, 20, 10, 5, 15, 25, 45, 35, 40, 55, 50};

	//in-order (left, visit, right):
	//		5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55
	//simulate manual BST-insertion using above array in this order: 
	//{
	//	30 --> root, 
	//	20 --> root.left, 
	//	45 --> root.right, 
	//	10 --> root.left.left, 
	//	25 --> root.left.right
	//	5  --> root.left.left.left
	//  15 --> root.left.left.right
	//  35 --> root.right.left
	//  55 --> root.right.right
	//  40 --> root.right.left.right
	//  50 --> root.right.right.left
	//}
	//Graphically:
	//			  30
	//	   	   /     \
	//	      /	      \
	//	     20       45
	//		/  \     /  \
	//	   /    \   /    \
	//	  10    25 35     55
	//	 /  \       \    /
	//  /    \       \  /
	// 5     15      40 50
	public static final Integer[] i_ary_expected_inorder = i_ary_expected;

	//post-order (left, right, vist):
	//		5, 15, 10, 25, 20, 40, 35, 50, 55, 45, 30 
	public static final Integer[] i_ary_expected_postorder = new Integer[] {5, 15, 10, 25, 20, 40, 35, 50, 55, 45, 30};

	//level-order (breadt-first, left, right):
	//		30, 20, 45, 10, 25, 35, 55, 5, 15, 40, 50
	public static final Integer[] i_ary_expected_levelorder = new Integer[] {30, 20, 45, 10, 25, 35, 55, 5, 15, 40, 50};

	
	public static Trees.CPerson[] person_ary_expected_preorder;
	public static Trees.CPerson[] person_ary_expected_inorder;
	static {
		try {
			Arrays.person_ary_expected_preorder = new Trees.CPerson[] {
				new Trees.CPerson("S", "C", "01010101"), 	
				new Trees.CPerson("J", "C", "03080301"), 
				new Trees.CPerson("P", "C", "03170817"), 
				new Trees.CPerson("L", "C", "03000120"),
				new Trees.CPerson("M", "C", "03050213"),
				new Trees.CPerson("S", "C", "01000101"),
				new Trees.CPerson("W", "C", "03101031"), 
				new Trees.CPerson("s", "C", "01010101"), 
				new Trees.CPerson("J", "W", "01110401"), 
				new Trees.CPerson("D", "D", "00590503"), 
				new Trees.CPerson("K", "W", "01111107"),	
			};
			Arrays.person_ary_expected_inorder = new Trees.CPerson[] {
				Arrays.person_ary_expected_preorder[1], 				//new CPerson("J", "C", "00080301"),
				Arrays.person_ary_expected_preorder[3],					//new CPerson("L", "C", "03000120"),
				Arrays.person_ary_expected_preorder[4],					//new CPerson("M", "C", "03050213"), 
				Arrays.person_ary_expected_preorder[2],					//new CPerson("P", "C", "00170817"), 
				Arrays.person_ary_expected_preorder[5],					//new CPerson("S", "C", "01000101"),
				Arrays.person_ary_expected_preorder[0],					//new CPerson("S", "C", "00010101"),
				Arrays.person_ary_expected_preorder[6],					//new CPerson("W", "C", "03101031"),
				Arrays.person_ary_expected_preorder[7],					//new CPerson("s", "C", "01010101"),
				Arrays.person_ary_expected_preorder[9],					//new CPerson("D", "D", "00590503"),
				Arrays.person_ary_expected_preorder[8],					//new CPerson("J", "W", "01110401"),
				Arrays.person_ary_expected_preorder[10],				//new CPerson("K", "W", "01111107")
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
