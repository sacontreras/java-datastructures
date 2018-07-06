import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.test.TestUtils;
import com.sacontreras.library.datastructures.test.TestUtils.CIntegerBinarySearchTree;
import com.sacontreras.library.datastructures.test.TestUtils.CIntegerBinaryTreeTraversalListener;
import com.sacontreras.library.datastructures.test.TestUtils.CPerson;
import com.sacontreras.library.datastructures.test.TestUtils.CPersonBinarySearchTree;
import com.sacontreras.library.datastructures.test.TestUtils.CPersonBinarySearchTreeTraversalListener;
import com.sacontreras.library.util.Transform;

public class Datastructures_CBinarySearchTree_Test {
	@Test
	@DisplayName("test_CBinarySearchTree")
	public void test_CBinarySearchTree() {
		CIntegerBinarySearchTree intBinarySearchTree = CIntegerBinarySearchTree.fromArray(TestUtils.i_ary_expected_preorder);

		
		//pre-order traversal
		CIntegerBinaryTreeTraversalListener intBinarySearchTreeTraversalListener = new CIntegerBinaryTreeTraversalListener(CIntegerBinarySearchTree.class.getSimpleName(), "preOrder");
		intBinarySearchTree.traversePreOrder(intBinarySearchTreeTraversalListener);
		Integer[] int_ary = Transform.to_array(intBinarySearchTreeTraversalListener.q_visit_order);
		int i_expect = TestUtils.i_ary_expected_preorder.length;
		int i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < TestUtils.i_ary_expected_preorder.length; i++) {
			i_expect = TestUtils.i_ary_expected_preorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//pre-order iteration:
		Iterator<Integer> iterator_preorder = intBinarySearchTree.iterator_preorder();
		int n = -1;
		while (iterator_preorder.hasNext()) {
			n++;
			i_expect = TestUtils.i_ary_expected_preorder[n];
			i_result = iterator_preorder.next();
			assertEquals(
				i_expect,
				i_result
			);
		}
		i_expect = TestUtils.i_ary_expected_preorder.length;
		i_result = n + 1;
		assertEquals(
			i_expect,
			i_result
		);
		
		//in-order traversal
		intBinarySearchTreeTraversalListener = new CIntegerBinaryTreeTraversalListener(CIntegerBinarySearchTree.class.getSimpleName(), "inOrder");
		intBinarySearchTree.traverseInOrder(intBinarySearchTreeTraversalListener);
		int_ary = Transform.to_array(intBinarySearchTreeTraversalListener.q_visit_order);
		i_expect = TestUtils.i_ary_expected_inorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < TestUtils.i_ary_expected_inorder.length; i++) {
			i_expect = TestUtils.i_ary_expected_inorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//in-order iteration:
		Iterator<Integer> iterator_inorder = intBinarySearchTree.iterator_inorder();
		n = -1;
		while (iterator_inorder.hasNext()) {
			n++;
			i_expect = TestUtils.i_ary_expected_inorder[n];
			i_result = iterator_inorder.next();
			assertEquals(
				i_expect,
				i_result
			);
		}
		i_expect = TestUtils.i_ary_expected_inorder.length;
		i_result = n + 1;
		assertEquals(
			i_expect,
			i_result
		);
		
		intBinarySearchTreeTraversalListener = new CIntegerBinaryTreeTraversalListener(CIntegerBinarySearchTree.class.getSimpleName(), "levelOrder");
		intBinarySearchTree.traverseLevelOrder(intBinarySearchTreeTraversalListener);
	}
	
	@Test
	@DisplayName("test_BinarySearch_not_comparable")
	public void test_BinarySearch_not_comparable() {
		CPersonBinarySearchTree personBinarySearchTree = CPersonBinarySearchTree.fromArray(TestUtils.person_ary_expected_preorder);
		
		//pre-order traversal
		CPersonBinarySearchTreeTraversalListener personBinarySearchTreeTraversalListener = new CPersonBinarySearchTreeTraversalListener("preOrder");
		personBinarySearchTree.traversePreOrder(personBinarySearchTreeTraversalListener);
		CPerson[] person_ary = Transform.to_array(personBinarySearchTreeTraversalListener.q_visit_order);
		int i_expect = TestUtils.i_ary_expected_preorder.length;
		int i_result = person_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		CPerson 
			p_expect = null,
			p_result = null;
		for (int i = 0; i < TestUtils.person_ary_expected_preorder.length; i++) {
			p_expect = TestUtils.person_ary_expected_preorder[i];
			p_result = person_ary[i];
			assertEquals(
				p_expect,
				p_result
			);
		}
		
		//in-order traversal
		personBinarySearchTreeTraversalListener = new CPersonBinarySearchTreeTraversalListener("inOrder");
		personBinarySearchTree.traverseInOrder(personBinarySearchTreeTraversalListener);
		person_ary = Transform.to_array(personBinarySearchTreeTraversalListener.q_visit_order);
		i_expect = TestUtils.person_ary_expected_inorder.length;
		i_result = person_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < TestUtils.person_ary_expected_inorder.length; i++) {
			p_expect = TestUtils.person_ary_expected_inorder[i];
			p_result = person_ary[i];
			assertEquals(
				p_expect,
				p_result
			);
		}
		
		//post-order traversal
		personBinarySearchTreeTraversalListener = new CPersonBinarySearchTreeTraversalListener("postOrder");
		personBinarySearchTree.traversePostOrder(personBinarySearchTreeTraversalListener);
		
		//level-order traversal
		personBinarySearchTreeTraversalListener = new CPersonBinarySearchTreeTraversalListener("levelOrder");
		personBinarySearchTree.traverseLevelOrder(personBinarySearchTreeTraversalListener);
		
		//TODO: add remaining traversal validation
	}
}
