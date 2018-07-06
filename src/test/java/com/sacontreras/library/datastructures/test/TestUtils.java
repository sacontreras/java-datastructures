package com.sacontreras.library.datastructures.test;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.test.TestUtils.CPerson;
import com.sacontreras.library.datastructures.tree.CBinarySearchTree;
import com.sacontreras.library.datastructures.tree.CBinaryTree;
import com.sacontreras.library.datastructures.tree.CBinaryTreeNode;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;

public class TestUtils {
	
	public static class CIntegerBinaryTree extends CBinaryTree<Integer> {
		public CIntegerBinaryTree(CIntegerBinaryTreeNode root) {
			super(root);
		}

		public CIntegerBinaryTree() {
			super();
		}
	}
	public static class CIntegerBinaryTreeNode extends CBinaryTreeNode<Integer> {
		public CIntegerBinaryTreeNode(int i) {
			super(i);
		}
	}
	public static class CIntegerBinaryTreeTraversalListener implements IBinaryTreeTraversalListener<Integer> {
		private final String tag;
		private final String order;
		
		public final CLinkedListQueue<Integer> q_visit_order = new CLinkedListQueue<Integer>();
		
		public CIntegerBinaryTreeTraversalListener(final String tag, final String order) {
			this.tag = tag;
			this.order = order;
		}
		
		@Override
		public void onNodeVisted(Integer data, DISPOSITION disp, Integer data_parent) {
			System.out.println(String.format("%s::onNodeVisted-%s: value: %d, disp: %s, (node) predecessor: %s", tag, order, data, disp.name(), data_parent));
			q_visit_order.enqueue(data);
		}

		@Override
		public void onNullNode() {
			System.out.println(String.format("%s::onNullNode-%s", tag, order));
		}
	}
	
	//note that this class is a bit misleading since Integer is inherently comparable
	//	but in order to really demonstrate use of comparable, we should use a type that is NOT necessarily comparable and flesh out the compare() override accordingly
	public static class CIntegerComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	}
		
	public static class CIntegerBinarySearchTree extends CBinarySearchTree<Integer> {
		public CIntegerBinarySearchTree() {
			super(new CIntegerComparator());
		}
		
		public static CIntegerBinarySearchTree fromArray(final Integer[] ary_data) {
			CIntegerBinarySearchTree bst = new CIntegerBinarySearchTree(); 
			if (ary_data != null && ary_data.length > 0) {
				for (int i = 0; i < ary_data.length; i++)
					bst.add(ary_data[i]);
			}
			return bst;
		}
	}
	
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
	
	
	
	
	
	
	
	public static final SimpleDateFormat birthdate_format = new SimpleDateFormat("yyyyMMdd");
	public static class CPerson {	
		private final String firstname;
		private final String lastname;
		private final Date birthdate;
		
		public CPerson(final String firstname, final String lastname, final Date birthdate) {
			this.firstname = firstname;
			this.lastname = lastname;
			this.birthdate = birthdate;
		}
		public CPerson(final String firstname, final String lastname, final String s_birthdate) throws ParseException {
			this(firstname, lastname, birthdate_format.parse(s_birthdate));
		}
		private CPerson() {
			this(null, null, (Date)null);
		}
	}
	
	public static class CPersonComparator implements Comparator<CPerson> {
		@Override
		public int compare(CPerson o1, CPerson o2) {
			int comparison = o1.lastname.compareTo(o2.lastname);
			switch (comparison) {
				case 0: {//same last name, so compare first names
					comparison = o1.firstname.compareTo(o2.firstname);
					switch (comparison) {
						case 0: {//same last names, so compare birthdates
							return o1.birthdate.compareTo(o2.birthdate);
						}
						default: return comparison;
					}
				}
				default: return comparison;
			}
		}
	}
	
	public static class CPersonBinarySearchTree extends CBinarySearchTree<CPerson> {
		public CPersonBinarySearchTree() {
			super(new CPersonComparator());
		}
		
		public static CPersonBinarySearchTree fromArray(final CPerson[] ary_person) {
			CPersonBinarySearchTree person_bst = new CPersonBinarySearchTree(); 
			if (ary_person != null && ary_person.length > 0) {
				for (int i = 0; i < ary_person.length; i++)
					person_bst.add(ary_person[i]);
			}
			return person_bst;
		}
	}
	
	public static class CPersonBinarySearchTreeTraversalListener implements IBinaryTreeTraversalListener<CPerson> {
		private final String order;
		public final CLinkedListQueue<CPerson> q_visit_order = new CLinkedListQueue<CPerson>();
		
		public CPersonBinarySearchTreeTraversalListener(final String order) {
			this.order = order;
		}
		
		@Override
		public void onNodeVisted(CPerson person, DISPOSITION disp, CPerson person_parent) {
			System.out.println(
				String.format(
					"CPersonBinarySearchTreeTraversalListener::onNodeVisted-%s: person: %s, %s, %tF; disp: %s; (node) predecessor: %s, %s, %tF", 
					order, 
					person.lastname, 
					person.firstname, 
					person.birthdate,
					disp.name(),
					person_parent != null ? person_parent.lastname : null,
					person_parent != null ? person_parent.firstname : null, 
					person_parent != null ? person_parent.birthdate : null
				)
			);
			q_visit_order.enqueue(person);
		}

		@Override
		public void onNullNode() {
			//System.out.println(String.format("CIntegerBinarySearchTreeTraversalListener::onNullNode-%s", order));
		}
	}
	
	//pre-prder amounts to insertion order
	public static CPerson[] person_ary_expected_preorder; 
	public static CPerson[] person_ary_expected_inorder;
	static {
		try {
			person_ary_expected_preorder = new CPerson[] {
				new CPerson("Steven", "Contreras", "19710615"), 	
				new CPerson("Jackson", "Contreras", "20080327"), 
				new CPerson("Pepper", "Contreras", "20170817"), 
				new CPerson("Lucas", "Contreras", "20000620"),
				new CPerson("Myah", "Contreras", "20050913"),
				new CPerson("Steven", "Contreras", "19710614"),
				new CPerson("Wuffers", "Contreras", "20101031"), 
				new CPerson("steven", "Contreras", "19710615"), 
				new CPerson("Jessica", "Wellington", "19810423"), 
				new CPerson("Deborah", "Costantino", "19590503"), 
				new CPerson("Justin", "Wellington", "19881107"),	
			};
			person_ary_expected_inorder = new CPerson[] {
				person_ary_expected_preorder[1], 					//new CPerson("Jackson", "Contreras", "20080327"),
				person_ary_expected_preorder[3],					//new CPerson("Lucas", "Contreras", "20000620"),
				person_ary_expected_preorder[4],					//new CPerson("Myah", "Contreras", "20050913"), 
				person_ary_expected_preorder[2],					//new CPerson("Pepper", "Contreras", "20170817"), 
				person_ary_expected_preorder[5],					//new CPerson("Steven", "Contreras", "19710614"),
				person_ary_expected_preorder[0],					//new CPerson("Steven", "Contreras", "19710615"),
				person_ary_expected_preorder[6],					//new CPerson("Wuffers", "Contreras", "20101031"),
				person_ary_expected_preorder[7],					//new CPerson("steven", "Contreras", "19710615"),
				person_ary_expected_preorder[9],					//new CPerson("Deborah", "Costantino", "19590503"),
				person_ary_expected_preorder[8],					//new CPerson("Jessica", "Wellington", "19810423"),
				person_ary_expected_preorder[10],					//new CPerson("Justin", "Wellington", "19881107")
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
