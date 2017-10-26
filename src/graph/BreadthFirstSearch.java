package graph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
	
	public void bfs(Node root) {
		
		if(root == null)
			return;
		
		Queue q = new LinkedList();
		q.add(root);
		//visit(root);
		root.visited = true;
		while(!q.isEmpty()) {
			Node node = (Node) q.remove();
			for(Node nextNode : node.getUnvisitedNodes()) {
				q.add(nextNode);
				//visit(nextNode);
				nextNode.visited = true;
			}
		}
	}
	
	public boolean search(Node start, Node end) {
		
		if(start == null || end == null)
			return false;
		
		if(start == end)
			return true;
		
		Queue q = new LinkedList();
		q.add(start);
		start.visited = true;
		
		while(!q.isEmpty()) {
			Node node = (Node) q.remove();
			for(Node nextNode : node.getUnvisitedNodes()) {
				if(nextNode.visited == false) {
					if(nextNode == end) {
						return true;
					} else {
						q.add(nextNode);
						nextNode.visited = true;
					}
				}
			}
		}
		return false;
	}
	
	public int getHeight(TreeNode node) {
		if(node == null)
			return -1;
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}
	
	public boolean isBalanced(TreeNode root) {
		
		if(root == null)
			return true;
		
		int diffHeight = Math.abs(getHeight(root.left) - getHeight(root.right));
		if(diffHeight > 1) {
			return false;
		} else {
			return isBalanced(root.left) && isBalanced(root.right);
		}
	}
	
	public int checkHeight(TreeNode node) {
		if(node == null)
			return -1;
		
		int leftHeight = checkHeight(node.left);
		if(leftHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		
		int rightHeight = checkHeight(node.right);
		if(rightHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		
		int diff = Math.abs(leftHeight - rightHeight);
		if(diff > 1) {
			return Integer.MIN_VALUE;
		} else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
		
	}
	
	public boolean checkHeightBalanced(TreeNode root) {
		if(checkHeight(root) == Integer.MIN_VALUE)
			return false;
		return true;
	}

}
