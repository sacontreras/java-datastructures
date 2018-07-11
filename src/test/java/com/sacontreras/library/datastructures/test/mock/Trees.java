package com.sacontreras.library.datastructures.test.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.CBinarySearchTree;
import com.sacontreras.library.datastructures.tree.CBinaryTree;
import com.sacontreras.library.datastructures.tree.CBinaryTreeNode;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;

public class Trees {

	public static class CIntegerBinaryTreeNode extends CBinaryTreeNode<Integer> {
		public CIntegerBinaryTreeNode(int i) {
			super(i);
		}
	}

	public static class CIntegerBinaryTree extends CBinaryTree<Integer> {
		public CIntegerBinaryTree(CIntegerBinaryTreeNode root) {
			super(root);
		}
	
		public CIntegerBinaryTree() {
			super();
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

	public static class CPerson {	
		public final String firstname;
		public final String lastname;
		public final Date birthdate;
		
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

	public static final SimpleDateFormat birthdate_format = new SimpleDateFormat("yyyyMMdd");

}
