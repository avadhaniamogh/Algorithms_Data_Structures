package graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphMain {

	public static void main(String[] args) {

				Solutions sol = new Solutions();
				int[] arr = {2, 3, 5, 11, 16, 19};
				TreeNode root = sol.createMinimalBST(arr);
				StringBuilder s1 = new StringBuilder();
				GraphSolutions gsol = new GraphSolutions();
				gsol.getPreOrderString(root, s1);
				System.out.println(s1.toString());
				sol.preorder(root);
		//		System.out.println("----------inorder--------");
		//		
		//		ArrayList<LinkedList<TreeNode>> lists = sol.createLevelLinkedList(root);
		//		for(LinkedList<TreeNode> list : lists) {
		//			for(int i = 0; i < list.size(); i++) {
		//				TreeNode n = list.get(i);
		//				System.out.println(n.data);
		//				System.out.println("----list----");
		//			}
		//		}

//		String[] arr = new String[5];
//		System.out.println(arr[0]);
	}

}
