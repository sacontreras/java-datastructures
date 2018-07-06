import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.test.TestUtils;
import com.sacontreras.library.datastructures.test.TestUtils.CIntegerBinaryTree;
import com.sacontreras.library.datastructures.test.TestUtils.CIntegerBinaryTreeNode;
import com.sacontreras.library.datastructures.test.TestUtils.CIntegerBinaryTreeTraversalListener;
import com.sacontreras.library.datastructures.tree.CBinaryTree;
import com.sacontreras.library.datastructures.tree.CBinaryTreeNode;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;
import com.sacontreras.library.util.Transform;

public class Datastructures_CBinaryTree_Test {
	
	

	@Test
	@DisplayName("test_CBinaryTree")
	public void test_CBinaryTree() {
		CIntegerBinaryTree intBinaryTree = new CIntegerBinaryTree();
		
		boolean 
			b_expect = true,
			b_result = intBinaryTree.isEmpty();
		assertEquals(
			b_expect,
			b_result
		);
		int 
			i_expect = 0,
			i_result = intBinaryTree.size();
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = intBinaryTree.height();
		assertEquals(
			i_expect,
			i_result
		);
		
		CIntegerBinaryTreeNode newRoot = null;
		
		i_expect = -1;
		i_result = CIntegerBinaryTree.depth(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 0;
		i_result = CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		// 30 --> root, 
		// 20 --> root.left, 
		// 45 --> root.right
		newRoot = (CIntegerBinaryTreeNode)CIntegerBinaryTree.make(
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[5]),
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[3]), 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[8])
		);
		i_expect = 0;
		i_result = CIntegerBinaryTree.depth(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 1;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 1;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 1;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		// 10 --> root.left.left, 
		// 25 --> root.left.right
		CIntegerBinaryTree.make(
			newRoot.getLeft(), 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[1]), 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[4])
		);
		i_expect = 1;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 5;
		i_result = CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		// 5 --> root.left.left.left, 
		// 15 --> root.left.left.right
		CIntegerBinaryTree.make(
			newRoot.getLeft().getLeft(), 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[0]), 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[2])
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft().getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft().getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 7;
		i_result = CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		// 35 --> root.right.left
		// 55 --> root.right.right
		CIntegerBinaryTree.make(
			newRoot.getRight(), 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[6]), 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[10])
		);
		i_expect = 1;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 9;
		i_result = CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		// 40 --> root.right.left.right
		CIntegerBinaryTree.make(
			newRoot.getRight().getLeft(), 
			null, 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[7])
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight().getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight().getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 10;
		i_result = CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		// 50 --> root.right.right.left
		CIntegerBinaryTree.make(
			newRoot.getRight().getRight(), 
			new CIntegerBinaryTreeNode(TestUtils.i_ary_expected[9]), 
			null
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight().getRight().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight().getRight().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 11;
		i_result = CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		intBinaryTree.make(newRoot);
		i_expect = 11;
		i_result = intBinaryTree.size();
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = intBinaryTree.height();
		assertEquals(
			i_expect,
			i_result
		);
		
		//pre-order traversal
		CIntegerBinaryTreeTraversalListener intBinaryTreeTraversalListener = new CIntegerBinaryTreeTraversalListener(CIntegerBinaryTree.class.getSimpleName(), "preOrder");
		intBinaryTree.traversePreOrder(intBinaryTreeTraversalListener);
		Integer[] int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = TestUtils.i_ary_expected_preorder.length;
		i_result = int_ary.length;
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
		
//		//pre-order iteration:
		Iterator<Integer> iterator_preorder = intBinaryTree.iterator_preorder();
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
		intBinaryTreeTraversalListener = new CIntegerBinaryTreeTraversalListener(CIntegerBinaryTree.class.getSimpleName(), "inOrder");
		intBinaryTree.traverseInOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
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
		Iterator<Integer> iterator_inorder = intBinaryTree.iterator_inorder();
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
		
		//post-order traversal
		intBinaryTreeTraversalListener = new CIntegerBinaryTreeTraversalListener(CIntegerBinaryTree.class.getSimpleName(), "postOrder");
		intBinaryTree.traversePostOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = TestUtils.i_ary_expected_postorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < TestUtils.i_ary_expected_postorder.length; i++) {
			i_expect = TestUtils.i_ary_expected_postorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//level-order traversal
		intBinaryTreeTraversalListener = new CIntegerBinaryTreeTraversalListener(CIntegerBinaryTree.class.getSimpleName(), "levelOrder");
		intBinaryTree.traverseLevelOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = TestUtils.i_ary_expected_levelorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < TestUtils.i_ary_expected_levelorder.length; i++) {
			i_expect = TestUtils.i_ary_expected_levelorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
	}
}
