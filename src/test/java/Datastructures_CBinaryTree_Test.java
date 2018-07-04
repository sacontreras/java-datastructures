import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.CBinaryTree;
import com.sacontreras.library.datastructures.tree.CBinaryTreeNode;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;
import com.sacontreras.library.util.Transform;

public class Datastructures_CBinaryTree_Test {
	
	private class CIntegerBinaryTree extends CBinaryTree<Integer> {
		public CIntegerBinaryTree(CIntegerBinaryTreeNode root) {
			super(root);
		}

		public CIntegerBinaryTree() {
			super();
		}
	}
	private class CIntegerBinaryTreeNode extends CBinaryTreeNode<Integer> {
		public CIntegerBinaryTreeNode(int i) {
			super(i);
		}
	}
	private class CIntegerBinaryTreeTraversalListener implements IBinaryTreeTraversalListener<Integer> {
		private final String order;
		public final CLinkedListQueue<Integer> q_visit_order = new CLinkedListQueue<Integer>();
		
		public CIntegerBinaryTreeTraversalListener(final String order) {
			this.order = order;
		}
		
		@Override
		public void onNodeVisted(Integer data) {
			System.out.println(String.format("onNodeVisted-%s: value: %d", order, data));
			q_visit_order.enqueue(data);
		}

		@Override
		public void onNullNode() {
			System.out.println(String.format("onNullNode-%s", order));
		}
	}

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

		int[] i_ary_expected = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
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
		int[] i_ary_expected_preorder = new int[]{
			30, 20, 10, 5, 15, 25, 45, 35, 40, 55, 50
		};
		//in-order (left, visit, right):
		//		5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55
		int[] i_ary_expected_inorder = i_ary_expected;
		//post-order (left, right, vist):
		//		5, 15, 10, 25, 20, 40, 35, 50, 55, 45, 30 
		int[] i_ary_expected_postorder = new int[]{
			5, 15, 10, 25, 20, 40, 35, 50, 55, 45, 30
		};
		//level-order (breadt-first, left, right):
		//		30, 20, 45, 10, 25, 35, 55, 5, 15, 40, 50
		int[] i_ary_expected_levelorder = new int[]{
			30, 20, 45, 10, 25, 35, 55, 5, 15, 40, 50
		};
		
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
			new CIntegerBinaryTreeNode(i_ary_expected[5]),
			new CIntegerBinaryTreeNode(i_ary_expected[3]), 
			new CIntegerBinaryTreeNode(i_ary_expected[8])
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
			new CIntegerBinaryTreeNode(i_ary_expected[1]), 
			new CIntegerBinaryTreeNode(i_ary_expected[4])
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
			new CIntegerBinaryTreeNode(i_ary_expected[0]), 
			new CIntegerBinaryTreeNode(i_ary_expected[2])
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
			new CIntegerBinaryTreeNode(i_ary_expected[6]), 
			new CIntegerBinaryTreeNode(i_ary_expected[10])
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
			new CIntegerBinaryTreeNode(i_ary_expected[7])
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
			new CIntegerBinaryTreeNode(i_ary_expected[9]), 
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
		CIntegerBinaryTreeTraversalListener intBinaryTreeTraversalListener = new CIntegerBinaryTreeTraversalListener("preOrder");
		intBinaryTree.traversePreOrder(intBinaryTreeTraversalListener);
		Integer[] int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = i_ary_expected_preorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < i_ary_expected_preorder.length; i++) {
			i_expect = i_ary_expected_preorder[i];
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
			i_expect = i_ary_expected_preorder[n];
			i_result = iterator_preorder.next();
			assertEquals(
				i_expect,
				i_result
			);
		}
		i_expect = i_ary_expected_preorder.length;
		i_result = n + 1;
		assertEquals(
			i_expect,
			i_result
		);
		
		//in-order traversal
		intBinaryTreeTraversalListener = new CIntegerBinaryTreeTraversalListener("inOrder");
		intBinaryTree.traverseInOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = i_ary_expected_inorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < i_ary_expected_inorder.length; i++) {
			i_expect = i_ary_expected_inorder[i];
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
			i_expect = i_ary_expected_inorder[n];
			i_result = iterator_inorder.next();
			assertEquals(
				i_expect,
				i_result
			);
		}
		i_expect = i_ary_expected_inorder.length;
		i_result = n + 1;
		assertEquals(
			i_expect,
			i_result
		);
		
		//post-order traversal
		intBinaryTreeTraversalListener = new CIntegerBinaryTreeTraversalListener("postOrder");
		intBinaryTree.traversePostOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = i_ary_expected_postorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < i_ary_expected_postorder.length; i++) {
			i_expect = i_ary_expected_postorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//level-order traversal
		intBinaryTreeTraversalListener = new CIntegerBinaryTreeTraversalListener("levelOrder");
		intBinaryTree.traverseLevelOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = i_ary_expected_levelorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < i_ary_expected_levelorder.length; i++) {
			i_expect = i_ary_expected_levelorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
	}
}
