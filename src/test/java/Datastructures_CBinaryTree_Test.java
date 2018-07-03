import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.datastructures.tree.CBinaryTree;
import com.sacontreras.library.datastructures.tree.CBinaryTreeNode;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;

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

		@Override
		public void onNodeVisted(Integer data) {
			System.out.println(String.format("onNodeVisted: value: %d", data));
		}

		@Override
		public void onNullNode() {
			System.out.println("onNullNode");
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

		int[] i_ary_expected = new int[]{1, 2, 3, 4, 5};
		//simulate manual BST-insertion using above array in this order: 
		//{
		//	4 --> root, 
		//	1 --> root.left, 
		//	5 --> root.right, 
		//	2 --> root.left.right, 
		//	3 --> root.left.right.right
		//}
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
		
		newRoot = (CIntegerBinaryTreeNode)CIntegerBinaryTree.make(
			new CIntegerBinaryTreeNode(i_ary_expected[3]), 
			null, 
			null
		);
		i_expect = 0;
		i_result = CIntegerBinaryTree.depth(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 1;
		i_result = CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 0;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		CIntegerBinaryTree.make(
			newRoot, 
			new CIntegerBinaryTreeNode(i_ary_expected[0]), 
			null
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
		i_expect = -1;
		i_result = CIntegerBinaryTree.depth(newRoot.getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
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
		
		CIntegerBinaryTree.make(
			newRoot, 
			newRoot.getLeft(), 
			new CIntegerBinaryTreeNode(i_ary_expected[4])
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
		
		CIntegerBinaryTree.make(
			newRoot.getLeft(), 
			null, 
			new CIntegerBinaryTreeNode(i_ary_expected[1])
		);
		i_expect = 1;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
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
		i_expect = 4;
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
		
		CIntegerBinaryTree.make(
			newRoot.getLeft().getRight(), 
			null, 
			new CIntegerBinaryTreeNode(i_ary_expected[2])
		);
		i_expect = 2;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft().getRight().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = CIntegerBinaryTree.depth(newRoot.getLeft().getRight().getRight());
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
		i_expect = 3;
		i_result = CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		
		intBinaryTree.make(newRoot);
		i_expect = 5;
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
		
		intBinaryTree.traverseInOrder(new CIntegerBinaryTreeTraversalListener());
		
		//in-order traversal should yield, in this case: 1, 2, 3, 4, 5 - since we manually inserted in BST order
	}
}
