package dynamic;

import java.util.ArrayList;
import java.util.List;

public class DynamicMain {

	public static void main(String[] args) {
		Solutions sol = new Solutions();
		
		int[] arr = {1, 2, 3};
		List<List<Integer>> res = sol.subsets(arr);
		
		System.out.println(res);
	}

}
