package practo;
import java.io.*;
import java.util.*;

public class Solution {

	int calcDroneMinEnergy(int[][] route) {
		// your code goes here
		int last = route[0][2];
		int first = route[0][2];
		for(int i = 1; i < route.length; i++) {
			if(route[i][2] > last) {
				last = route[i][2];
			}
		}
		return (last-first) > 0 ? (last-first) : 0;
	}

	//	  public static void main(String[] args) {
	//	    Solution sol = new Solution();
	//	    int[][] route = new int[][] {{0, 2, 10},
	//	                                 {3, 5, 0},
	//	                                 {10, 12, 15},
	//	                                 {10, 10, 8}};
	//	    int res = sol.calcDroneMinEnergy(route);
	//	    System.out.println(res);
	//	  }

	static double root(double x, int n) {
		// your code goes here
		// Set start and end for binary search
		double start = 0, end = x;

		// Set precision
		double e = 0.001;

		while (true)
		{
			double mid = (start + end)/2;
			double error = Math.abs(x - mid);

			// If error is less than e then mid is
			// our answer so return mid
			if (error <= e)
				return mid;

			// If mid*mid*mid is greater than n set
			// end = mid
			if (Math.pow(mid, n) > x)
				end = mid;

			// If mid*mid*mid is less than n set
			// start = mid
			else
				start = mid;
		}

	}
	
	public String[][] countWord(String document) {
		String[] list = document.split(" ");
		HashMap<String, Integer> map = new HashMap<>();
		
		for(String word : list) {
			word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
			if(map.containsKey(word)) {
				int count = map.get(word);
				map.put(word, count+1);
			} else {
				map.put(word, 1);
			}
		}
		
		String[][] result = new String[map.size()][2];
		int i = 0;
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = String.valueOf(entry.getValue());
			String[] one_entry = new String[2];
			one_entry[0] = key;
			one_entry[1] = value;
			result[i] = one_entry;
			i++;
		}
		
		return result;
	}

	public static void main(String[] args) {
//		double ans = root(27, 3);
//		System.out.println(ans);
		
		String doc = "Practice! Practice makes perfect. So let's practice well well well well";
		Solution sol = new Solution();
		String[][] wordCount = sol.countWord(doc);
		for(String[] entry : wordCount) {
			System.out.println(entry[0] + " " + entry[1]);
		}
		
	}

}
