import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.Utils;
import com.sacontreras.library.datastructures.queue.CLinkedListQueue;
import com.sacontreras.library.datastructures.tree.CBinarySearchTree;
import com.sacontreras.library.datastructures.tree.COrderedBinaryTree;
import com.sacontreras.library.datastructures.tree.IBinaryTreeTraversalListener;

public class Datastructures_CBinarySearchTree_Test {
	private class CIntegerBinarySearchTree extends CBinarySearchTree<Integer> {
	}
	
	private class CIntegerBinarySearchTreeTraversalListener implements IBinaryTreeTraversalListener<Integer> {
		private final String order;
		public final CLinkedListQueue<Integer> q_visit_order = new CLinkedListQueue<Integer>();
		
		public CIntegerBinarySearchTreeTraversalListener(final String order) {
			this.order = order;
		}
		
		@Override
		public void onNodeVisted(Integer data) {
			System.out.println(String.format("CIntegerBinarySearchTreeTraversalListener::onNodeVisted-%s: value: %d", order, data));
			q_visit_order.enqueue(data);
		}

		@Override
		public void onNullNode() {
			System.out.println(String.format("CIntegerBinarySearchTreeTraversalListener::onNullNode-%s", order));
		}
	}
	
	@Test
	@DisplayName("test_CBinarySearchTree")
	public void test_CBinarySearchTree() {
		CIntegerBinarySearchTree intBinarySearchTree = new CIntegerBinarySearchTree();
		
		int[] i_ary_expected_preorder = new int[]{
			30, 20, 10, 5, 15, 25, 45, 35, 40, 55, 50
		};
		
		for (int i = 0; i < i_ary_expected_preorder.length; i++)
			intBinarySearchTree.insert(i_ary_expected_preorder[i]);
		
		//in-order (left, visit, right):
		//		5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55
		int[] i_ary_expected_inorder = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
		
		//pre-order traversal
		CIntegerBinarySearchTreeTraversalListener intBinarySearchTreeTraversalListener = new CIntegerBinarySearchTreeTraversalListener("preOrder");
		intBinarySearchTree.traversePreOrder(intBinarySearchTreeTraversalListener);
		BoxedType<Integer>[] boxint_ary = Utils.to_array(intBinarySearchTreeTraversalListener.q_visit_order);
		int i_expect = i_ary_expected_preorder.length;
		int i_result = boxint_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < i_ary_expected_preorder.length; i++) {
			i_expect = i_ary_expected_preorder[i];
			i_result = boxint_ary[i].getValue();
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
		intBinarySearchTreeTraversalListener = new CIntegerBinarySearchTreeTraversalListener("inOrder");
		intBinarySearchTree.traverseInOrder(intBinarySearchTreeTraversalListener);
		boxint_ary = Utils.to_array(intBinarySearchTreeTraversalListener.q_visit_order);
		i_expect = i_ary_expected_inorder.length;
		i_result = boxint_ary.length;
		assertEquals(
			i_expect,
			i_result
		);
		for (int i = 0; i < i_ary_expected_inorder.length; i++) {
			i_expect = i_ary_expected_inorder[i];
			i_result = boxint_ary[i].getValue();
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
		
		intBinarySearchTreeTraversalListener = new CIntegerBinarySearchTreeTraversalListener("levelOrder");
		intBinarySearchTree.traverseLevelOrder(intBinarySearchTreeTraversalListener);
	}
}
