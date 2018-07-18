import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.test.mock.Arrays;
import com.sacontreras.library.datastructures.test.mock.Trees;
import com.sacontreras.library.datastructures.test.mock.Trees.CIntegerBinaryTree;
import com.sacontreras.library.datastructures.test.mock.Trees.CIntegerBinaryTreeNode;
import com.sacontreras.library.datastructures.test.mock.Trees.CIntegerBinaryTreeTraversalListener;
import com.sacontreras.library.datastructures.tree.CBinaryTree;
import com.sacontreras.library.datastructures.tree.CBinaryTreeNode;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;
import com.sacontreras.library.util.Transform;

public class Datastructures_CBinaryTree_Test {
	
	

	@Test
	@DisplayName("test_CBinaryTree")
	public void test_CBinaryTree() {
		Trees.CIntegerBinaryTree intBinaryTree = new Trees.CIntegerBinaryTree();
		
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
		b_expect = true;
		b_result = intBinaryTree.isFull();
		assertEquals(
			b_expect,
			b_result
		);
		
		Trees.CIntegerBinaryTreeNode newRoot = null;
		
		i_expect = -1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 0;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isEmpty(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		
		// 30 --> root, 
		// 20 --> root.left, 
		// 45 --> root.right
		//Graphically:
		//			  30
		//	   	   /     \
		//	      /	      \
		//	     20       45
		newRoot = (Trees.CIntegerBinaryTreeNode)Trees.CIntegerBinaryTree.make(
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[5]),
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[3]), 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[8])
		);
		i_expect = 0;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 1;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isEmpty(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isComplete(newRoot, 0, 3);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isHeightBalanced(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		
		// 25 --> root.left.right 
		//Graphically:
		//			  30
		//	   	   /     \
		//	      /	      \
		//	     20       45
		//		   \     
		//	        \ 
		//	        25
		Trees.CIntegerBinaryTree.make(
			newRoot.getLeft(), 
			null, 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[4])
		);
		i_expect = 1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 4;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isComplete(newRoot, 0, 4);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isHeightBalanced(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		
		// 25 --> root.left.right
		//Graphically:
		//			  30
		//	   	   /     \
		//	      /	      \
		//	     20       45
		//		/  \     
		//	   /    \ 
		//	  10    25
		Trees.CIntegerBinaryTree.make(
			newRoot.getLeft(), 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[1]), 
			newRoot.getLeft().getRight()
		);
		i_expect = 1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 5;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isComplete(newRoot, 0, 5);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isHeightBalanced(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		 
		// 5 --> root.left.left.left
		//Graphically:
		//			  30
		//	   	   /     \
		//	      /	      \
		//	     20       45
		//		/  \     
		//	   /    \   
		//	  10    25 
		//	 /         
		//  /           
		// 5     
		Trees.CIntegerBinaryTree.make(
			newRoot.getLeft().getLeft(), 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[0]), 
			null
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 6;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isComplete(newRoot, 0, 6);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isHeightBalanced(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		
		// 5 --> root.left.left.left
		//Graphically:
		//			  30
		//	   	   /     \
		//	      /	      \
		//	     20       45
		//		/  \     
		//	   /    \   
		//	  10    25 
		//	 /  \       
		//  /    \       
		// 5     15
		Trees.CIntegerBinaryTree.make(
			newRoot.getLeft().getLeft(), 
			newRoot.getLeft().getLeft().getLeft(), 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[2])
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getLeft().getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 7;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isComplete(newRoot, 0, 7);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isHeightBalanced(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		
		// 35 --> root.right.left
		// 55 --> root.right.right
		//Graphically:
		//			  30
		//	   	   /     \
		//	      /	      \
		//	     20       45
		//		/  \     /  \
		//	   /    \   /    \
		//	  10    25 35     55
		//	 /  \       
		//  /    \       
		// 5     15      
		Trees.CIntegerBinaryTree.make(
			newRoot.getRight(), 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[6]), 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[10])
		);
		i_expect = 1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 9;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isComplete(newRoot, 0, 9);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isHeightBalanced(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		
		// 40 --> root.right.left.right
		//Graphically:
		//			  30
		//	   	   /     \
		//	      /	      \
		//	     20       45
		//		/  \     /  \
		//	   /    \   /    \
		//	  10    25 35     55
		//	 /  \       \    
		//  /    \       \  
		// 5     15      40
		Trees.CIntegerBinaryTree.make(
			newRoot.getRight().getLeft(), 
			null, 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[7])
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight().getLeft().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight().getLeft().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 10;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isComplete(newRoot, 0, 10);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isHeightBalanced(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		
		// 50 --> root.right.right.left
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
		Trees.CIntegerBinaryTree.make(
			newRoot.getRight().getRight(), 
			new Trees.CIntegerBinaryTreeNode(Arrays.i_ary_expected[9]), 
			null
		);
		i_expect = 2;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight().getRight().getLeft());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = -1;
		i_result = Trees.CIntegerBinaryTree.depth(newRoot.getRight().getRight().getRight());
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 11;
		i_result = Trees.CIntegerBinaryTree.size(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		i_expect = 3;
		i_result = Trees.CIntegerBinaryTree.height(newRoot);
		assertEquals(
			i_expect,
			i_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isFull(newRoot);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = Trees.CIntegerBinaryTree.isComplete(newRoot, 0, 11);
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = Trees.CIntegerBinaryTree.isHeightBalanced(newRoot);
		assertEquals(
			b_expect,
			b_result
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
		b_expect = false;
		b_result = intBinaryTree.isEmpty();
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = intBinaryTree.isFull();
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = false;
		b_result = intBinaryTree.isComplete();
		assertEquals(
			b_expect,
			b_result
		);
		b_expect = true;
		b_result = intBinaryTree.isHeightBalanced();
		assertEquals(
			b_expect,
			b_result
		);
		
		//pre-order traversal
		Trees.CIntegerBinaryTreeTraversalListener intBinaryTreeTraversalListener = new Trees.CIntegerBinaryTreeTraversalListener(Trees.CIntegerBinaryTree.class.getSimpleName(), "preOrder");
		intBinaryTree.traversePreOrder(intBinaryTreeTraversalListener);
		Integer[] int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = Arrays.i_ary_expected_preorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < Arrays.i_ary_expected_preorder.length; i++) {
			i_expect = Arrays.i_ary_expected_preorder[i];
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
			i_expect = Arrays.i_ary_expected_preorder[n];
			i_result = iterator_preorder.next();
			assertEquals(
				i_expect,
				i_result
			);
		}
		i_expect = Arrays.i_ary_expected_preorder.length;
		i_result = n + 1;
		assertEquals(
			i_expect,
			i_result
		);
		
		//in-order traversal
		intBinaryTreeTraversalListener = new Trees.CIntegerBinaryTreeTraversalListener(Trees.CIntegerBinaryTree.class.getSimpleName(), "inOrder");
		intBinaryTree.traverseInOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = Arrays.i_ary_expected_inorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < Arrays.i_ary_expected_inorder.length; i++) {
			i_expect = Arrays.i_ary_expected_inorder[i];
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
			i_expect = Arrays.i_ary_expected_inorder[n];
			i_result = iterator_inorder.next();
			assertEquals(
				i_expect,
				i_result
			);
		}
		i_expect = Arrays.i_ary_expected_inorder.length;
		i_result = n + 1;
		assertEquals(
			i_expect,
			i_result
		);
		
		//post-order traversal
		intBinaryTreeTraversalListener = new Trees.CIntegerBinaryTreeTraversalListener(Trees.CIntegerBinaryTree.class.getSimpleName(), "postOrder");
		intBinaryTree.traversePostOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = Arrays.i_ary_expected_postorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < Arrays.i_ary_expected_postorder.length; i++) {
			i_expect = Arrays.i_ary_expected_postorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
		
		//level-order traversal
		intBinaryTreeTraversalListener = new Trees.CIntegerBinaryTreeTraversalListener(Trees.CIntegerBinaryTree.class.getSimpleName(), "levelOrder");
		intBinaryTree.traverseLevelOrder(intBinaryTreeTraversalListener);
		int_ary = Transform.to_array(intBinaryTreeTraversalListener.q_visit_order);
		i_expect = Arrays.i_ary_expected_levelorder.length;
		i_result = int_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < Arrays.i_ary_expected_levelorder.length; i++) {
			i_expect = Arrays.i_ary_expected_levelorder[i];
			i_result = int_ary[i];
			assertEquals(
				i_expect,
				i_result
			);
		}
	}
}
