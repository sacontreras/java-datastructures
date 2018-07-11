package com.sacontreras.library.datastructures.test.mock;

import java.text.ParseException;

public class Arrays {

	public static final Integer[] i_ary_expected = new Integer[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
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
	//Graphically, we have:
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
	
	//Expected yield of traversals:
	//	pre-order (visit, left, right):
	//		30, 20, 10, 5, 15, 25, 45, 35, 40, 55, 50		
	public static final Integer[] i_ary_expected_preorder = new Integer[] {30, 20, 10, 5, 15, 25, 45, 35, 40, 55, 50};

	//in-order (left, visit, right):
	//		5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55
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
				new Trees.CPerson("Steven", "Contreras", "19710615"), 	
				new Trees.CPerson("Jackson", "Contreras", "20080327"), 
				new Trees.CPerson("Pepper", "Contreras", "20170817"), 
				new Trees.CPerson("Lucas", "Contreras", "20000620"),
				new Trees.CPerson("Myah", "Contreras", "20050913"),
				new Trees.CPerson("Steven", "Contreras", "19710614"),
				new Trees.CPerson("Wuffers", "Contreras", "20101031"), 
				new Trees.CPerson("steven", "Contreras", "19710615"), 
				new Trees.CPerson("Jessica", "Wellington", "19810423"), 
				new Trees.CPerson("Deborah", "Costantino", "19590503"), 
				new Trees.CPerson("Justin", "Wellington", "19881107"),	
			};
			Arrays.person_ary_expected_inorder = new Trees.CPerson[] {
				Arrays.person_ary_expected_preorder[1], 				//new CPerson("Jackson", "Contreras", "20080327"),
				Arrays.person_ary_expected_preorder[3],					//new CPerson("Lucas", "Contreras", "20000620"),
				Arrays.person_ary_expected_preorder[4],					//new CPerson("Myah", "Contreras", "20050913"), 
				Arrays.person_ary_expected_preorder[2],					//new CPerson("Pepper", "Contreras", "20170817"), 
				Arrays.person_ary_expected_preorder[5],					//new CPerson("Steven", "Contreras", "19710614"),
				Arrays.person_ary_expected_preorder[0],					//new CPerson("Steven", "Contreras", "19710615"),
				Arrays.person_ary_expected_preorder[6],					//new CPerson("Wuffers", "Contreras", "20101031"),
				Arrays.person_ary_expected_preorder[7],					//new CPerson("steven", "Contreras", "19710615"),
				Arrays.person_ary_expected_preorder[9],					//new CPerson("Deborah", "Costantino", "19590503"),
				Arrays.person_ary_expected_preorder[8],					//new CPerson("Jessica", "Wellington", "19810423"),
				Arrays.person_ary_expected_preorder[10],				//new CPerson("Justin", "Wellington", "19881107")
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
