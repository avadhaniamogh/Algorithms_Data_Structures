package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class Solutions {

	// Array is sorted
	TreeNode createMinimalBST(int arr[]) {
		return createMinimalBST(arr, 0, arr.length-1);
	}

	private TreeNode createMinimalBST(int[] arr, int start, int end) {
		if(end < start)
			return null;
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = createMinimalBST(arr, start, mid-1);
		node.right = createMinimalBST(arr, mid+1, end);

		return node;
	}

	public void preorder(TreeNode root) {
		if(root != null) {
			System.out.println(root.data);
			preorder(root.left);
			preorder(root.right);
		}
	}
	
	public void inorder(TreeNode root) {
		if(root != null) {
			preorder(root.left);
			System.out.println(root.data);
			preorder(root.right);
		}
	}
	
	public ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
		createLevelLinkedList(root, lists, 0);
		return lists;
	}
	
	public void createLevelLinkedList(TreeNode node, ArrayList<LinkedList<TreeNode>> lists, int level) {
		
		if(node == null)
			return;
		
		LinkedList<TreeNode> list = null;
		if(lists.size() == level) {
			list = new LinkedList<TreeNode>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(node);
		createLevelLinkedList(node.left, lists, level+1);
		createLevelLinkedList(node.right, lists, level+1);
		
	}
	
	Integer last_seen = null;
	public boolean checkBST(TreeNode node) {
		
		if(!checkBST(node.left))
			return false;
		
		if(last_seen != null && node.data <= last_seen) {
			return false;
		}
		last_seen = node.data;
		
		
		if(!checkBST(node.right))
			return false;
		
		return true;
	}
	
	public boolean validateBST(TreeNode node) {
		if(node == null)
			return false;
		
		return validateBST(node, null, null);
	}
	
	public boolean validateBST(TreeNode node, Integer min, Integer max) {
		
		if(node == null)
			return true;
		
		if((min != null && node.data <= min) || (max != null && node.data > max))
			return false;
		
		if(!validateBST(node.left, min, node.data) || (!validateBST(node.right, node.data, max)))
			return false;
	
		return true;
	}
	
	public TreeNode inorderSuccessor(TreeNode node) {
		
		if(node == null)
			return null;
		
		if(node.right != null) {
			node = node.right;
			while(node.left != null) {
				node = node.left;
			}
			return node;
		} else {
			while(node.parent != null && node.parent.right == node) {
				node = node.parent;
			}
			return node.parent;
		}
	}
	
	public TreeNode commonAncestorsWithoutParent(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null || p == null || q == null)
			return null;
		
		return ancestorHelper(root, p, q);
	}
	
	public TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null)
			return root;
		
		boolean isLeftp = covers(root.left, p);
		boolean isLeftq = covers(root.left, q);
		if(isLeftp != isLeftq) {
			return root;
		}
		
		TreeNode childside = isLeftp ? root.left : root.right;
		return ancestorHelper(childside, p, q);
	}
	
	public boolean covers(TreeNode node, TreeNode x) {
		if(node == null)
			return false;
		if(node == x)
			return true;
		return covers(node.left, x) || covers(node.right, x);
	}
	
	public TreeNode commonAncestorsOptimum(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null || p == null || q == null)
			return null;
		
		if((!covers(root, p)) || (!covers(root, q))) {
			return null;
		}
		if(covers(q, p))
			return q;
		if(covers(p, q))
			return p;
		
		return commonAncestorHelper(root, p, q);
	}
	
	public TreeNode commonAncestorHelper(TreeNode node, TreeNode p, TreeNode q) {
		
		if(node == null)
			return null;
		if(node == p && node == q)
			return node;
		
		TreeNode x = commonAncestorHelper(node.left, p, q);
		if(x != null && x != p && x != q)
			return x;
		
		TreeNode y = commonAncestorHelper(node.right, p, q);
		if(y != null && y != p && y != q)
			return y;
		
		if(x != null && y != null) {
			return node;
		} else if (node == p || node == q) {
			return node;
		} else {
			return x == null ? y : x;
		}
		
	}
	
	public void test() {
		HashSet<Character> hash = new HashSet<Character>();
		char a = 'a';
	}
	

}
