package graph;

public class GraphSolutions {
	
	public TreeNode findCommonAncestorsWithLinkToParents(TreeNode p, TreeNode q) {
		int diff = getDepth(p) - getDepth(q);
		
		TreeNode shorter = getDepth(p) > getDepth(q) ? q : p; 
		TreeNode longer = getDepth(p) > getDepth(q) ? p : q;
		
		while(diff > 0) {
			longer = longer.parent;
			diff--;
		}
		
		while(shorter != longer && shorter != null && longer != null) {
			shorter = shorter.parent;
			longer = longer.parent;
		}
		return longer;
	}
	
	public int getDepth(TreeNode node) {
		int depth = 0;
		while(node != null) {
			node = node.parent;
			depth++;
		}
		return depth;
	}
	
	public TreeNode commonAncestorsTwo(TreeNode root, TreeNode p, TreeNode q) {
		if((!covers(root, p) || (!covers(root, q)))) {
			return null;
		}
		if(covers(p, q))
			return p;
		if(covers(q, p))
			return q;
		
		TreeNode sibling = getSibling(p);
		TreeNode parent = p.parent;
		while(!covers(sibling, q)) {
			parent = parent.parent;
			sibling = getSibling(parent);
		}
		return parent;
	}
	
	public boolean covers(TreeNode node, TreeNode x) {
		if(node == null)
			return false;
		if(node == x)
			return true;
		return covers(node.left, x) || covers(node.right, x);
	}
	
	public TreeNode getSibling(TreeNode node) {
		if(node == null)
			return null;
		TreeNode parent = node.parent;
		return parent.left == node ? parent.right : parent.left; 
	}
	
	public boolean checkSubTree(TreeNode node1, TreeNode node2) {
		
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		
		getPreOrderString(node1, s1);
		getPreOrderString(node2, s2);
		
		return s1.indexOf(s2.toString()) != -1;
	}
	
	public void getPreOrderString(TreeNode node, StringBuilder sb) {
		if(node == null) {
			sb.append("X");
			return;
		}
		sb.append(node.data + " ");
		getPreOrderString(node.left, sb);
		getPreOrderString(node.right, sb);
	}
	
	public boolean checkSubtreeOptimalSpace(TreeNode node1, TreeNode node2) {
		if(node2 == null)
			return true;
		return check(node1, node2);
	}
	
	public boolean check(TreeNode node1, TreeNode node2) {
		
		if(node1 == null) {
			return false;
		} else if(node1.data == node2.data && matchTrees(node1, node2)) {
			return true;
		}
		
		return check(node1.left, node2) || check(node1.right, node2);
	}
	
	public boolean matchTrees(TreeNode node1, TreeNode node2) {
		if(node1 == null && node2 == null) {
			return true;
		} else if(node1 == null || node2 == null) {
			return false;
		} else if(node1.data != node2.data) {
			return false;
		} else {
			return matchTrees(node1.left, node2) || matchTrees(node1.right, node2);
		}
	}
	
	public int countTotalPaths(TreeNode root, int totalSum) {
		if(root == null)
			return 0;
		
		int pathsFromNode = countTotalPathsFromNode(root, totalSum, 0);
		
		int leftPaths = countTotalPaths(root.left, totalSum);
		int rightPaths = countTotalPaths(root.right, totalSum);
		
		return pathsFromNode + leftPaths + rightPaths;
		
	}
	
	public int countTotalPathsFromNode(TreeNode node, int totalSum, int currentSum) {
		if(node == null)
			return 0;
		
		currentSum += node.data;
		
		int totalPaths = 0;
		if(currentSum == totalSum)
			totalPaths += 1;
		
		totalPaths += countTotalPathsFromNode(node.left, totalSum, currentSum);
		totalPaths += countTotalPathsFromNode(node.right, totalSum, currentSum);
		
		return totalPaths;
	}
	
}
