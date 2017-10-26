package graph;

import java.util.Stack;

public class DepthFirstSearch {
	
	public void dfs(Node root) {
		if(root == null)
			return;
		
		Stack<Node> s = new Stack<Node>();
		
		s.push(root);
		//visit(root);
		root.visited = true;
		while(!s.isEmpty()) {
			Node top = (Node) s.peek();
			for(Node next : top.getUnvisitedNodes()) {
				s.push(next);
				//visit(next);
				next.visited = true;
			}
			s.pop();
		}
	}

}
