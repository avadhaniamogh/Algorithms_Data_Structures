package dynamic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solutions {

	public int fibonacci(int n) {
		if(n < 0)
			return 0;
		if(n == 0)
			return 0;
		if(n == 1)
			return 1;
		return fibonacci(n-1) + fibonacci(n-2);
	}

	public int fib_top_down(int n) {
		return fib_td(n, new int[n+1]);
	}

	public int fib_td(int n, int[] memo) {
		if(n == 0 || n == 1)
			return n;

		if(memo[n] == 0)
			memo[n] = fib_td(n-1, memo) + fib_td(n-2, memo);

		return memo[n];
	}

	public int fib_bu(int n) {
		if(n == 0 || n == 1)
			return n;

		//		int[] temp = new int[n];
		//		temp[0] = 0;
		//		temp[1] = 1;
		//		for(int i = 2; i < n; i++) {
		//			temp[i] = temp[i-1] + temp[i-2];
		//		}
		//		return temp[n-1] + temp[n-2];

		int a = 0;
		int b = 1;
		for(int i = 0; i < n; i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		return a + b;
	}

	public int countStep(int n) {
		int[] memo = new int[n+1];
		Arrays.fill(memo, -1);
		return countStep(n, memo);
	}

	public int countStep(int n, int[] memo) {
		if(n < 0)
			return 0;
		if(n == 0) {
			return 1;
		}
		if(memo[n] > -1) {
			return memo[n];
		} else {
			memo[n] = countStep(n-1, memo) + countStep(n-2, memo) + countStep(n-3, memo);
			return memo[n];
		}
	}

	public ArrayList<Point> getPath(boolean[][] maze) {
		if(maze == null | maze.length == 0)
			return null;

		ArrayList<Point> path = new ArrayList<>();
		HashMap<Point, Boolean> map = new HashMap<>();
		if(getPath(maze, maze.length, maze[0].length, path, map)) {
			return path;
		} else {
			return null;
		}
	}

	public boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path, HashMap<Point, Boolean> map) {

		if(row < 0 || col < 0 || !maze[row][col])
			return false;

		Point p = new Point(row, col);

		boolean isAtOrigin = false;
		boolean success = false;
		if(row == 0 && col == 0) {
			isAtOrigin = true;
		}

		if(map.containsKey(p)) {
			return map.get(p);
		}

		if(isAtOrigin || getPath(maze, row-1, col, path, map) || getPath(maze, row, col-1, path, map)) {
			path.add(p);
			success = true;
		}
		map.put(p, success);
		return success;
	}

	public int findMagicIndexSortedDistinct(int[] array) {
		return findMagicIndexSortedDistinct(array, 0, array.length - 1);
	}

	public int findMagicIndexSortedDistinct(int[] array, int start, int end) {
		if(start > end)
			return -1;

		int mid = (start + end) / 2;
		if(array[mid] == mid) {
			return mid;
		} else if(array[mid] < mid) {
			return findMagicIndexSortedDistinct(array, mid+1, end);
		} else if(array[mid] > mid) {
			return findMagicIndexSortedDistinct(array, start, mid-1);
		}
		return -1;
	}

	public int findMagicIndexSorted(int[] array) {
		return findMagicIndexSorted(array, 0, array.length-1);
	}

	public int findMagicIndexSorted(int[] array, int start, int end) {
		if(start > end)
			return -1;

		int midIndex = (start + end) / 2;
		if(array[midIndex] == midIndex) {
			return midIndex;
		} else if(array[midIndex] < midIndex) {
			int rightIndex = Math.max(midIndex+1, array[midIndex]);
			return findMagicIndexSorted(array, rightIndex, end);
		} else if(array[midIndex] > midIndex) {
			int leftIndex = Math.min(midIndex-1, array[midIndex]);
			return findMagicIndexSorted(array, start, leftIndex);
		}
		return -1;
	}

	public ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) {

		return getSubsets(set, set.size()-1);
	}

	public ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int n) {

		ArrayList<ArrayList<Integer>> subsets;
		if(n == -1) {
			subsets = new ArrayList<>();
			ArrayList<Integer> empty = new ArrayList<Integer>();
			subsets.add(empty);
		} else {
			subsets = getSubsets(set, n-1);
			int item = set.get(n);
			ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<>();
			for(ArrayList<Integer> subset : subsets) {
				ArrayList<Integer> newSubset = new ArrayList<>();
				newSubset.addAll(subset);
				newSubset.add(item);
				moreSubsets.add(newSubset);
			}
			subsets.addAll(moreSubsets);
		}
		return subsets;
	}

	public ArrayList<String> getPermutations(String str) {
		if(str == null)
			return null;

		ArrayList<String> permutations = new ArrayList<>();
		if(str.length() == 0) {
			permutations.add("");
			return permutations;
		}


		char first = str.charAt(0);
		String rest = str.substring(1);
		ArrayList<String> words = getPermutations(rest);
		for(String word : words) {
			for(int i = 0; i < word.length(); i++) {
				String perm = insertCharAt(word, first, i);
				permutations.add(perm);
			}
		}
		return permutations;
	}

	public String insertCharAt(String word, char c, int pos) {
		String part1 = word.substring(0, pos);
		String part2 = word.substring(pos);
		return part1 + c + part2;
	}

	public Set<String> generateParens(int remaining) {
		Set<String> combinations = new HashSet<String>();
		if(remaining == 0) {
			combinations.add("");
			return combinations;
		} else {
			Set<String> prev = generateParens(remaining-1);
			for(String comb : prev) {
				char[] charArray = comb.toCharArray();
				for(int j = 0; j < charArray.length; j++) {
					if(charArray[j] == '(') {
						String s = insertAt(comb, j+1);
						combinations.add(s);
					}
				}
				combinations.add("()" + comb);
			}
			return combinations;
		}
	}

	public String insertAt(String s, int i) {
		String part1 = s.substring(0, i);
		String part2 = s.substring(i);
		return part1 + "()" + part2;
	}

	enum Color {Black, White, Blue, Green, Yellow, Red};

	public Color[][] paintFill(Color[][] screen, int row, int col, Color newColor) {

		if(screen[row][col] == newColor)
			return screen;
		return paintFill(screen, row, col, screen[row][col], newColor);
	}

	public Color[][] paintFill(Color[][] screen, int row, int col, Color oldColor, Color newColor) {
		if(row < 0 || row > screen.length || col < 0 || col > screen[0].length)
			return null;
		if(screen[row][col] == newColor) {
			return screen;
		} else {
			screen[row][col] = newColor;
			paintFill(screen, row-1, col, oldColor, newColor);
			paintFill(screen, row+1, col, oldColor, newColor);
			paintFill(screen, row, col-1, oldColor, newColor);
			paintFill(screen, row-1, col+1, oldColor, newColor);
			return screen;
		}
	}

	public List<List<Integer>> subsets(int[] nums) {
		
		return subsets(nums, nums.length-1);
	}
	
	public List<List<Integer>> subsets(int[] nums, int n) {
		List<List<Integer>> allSubsets;
		
		if(n == -1) {
			allSubsets = new ArrayList<>();
			List<Integer> zeroSet = new ArrayList<>();
			allSubsets.add(zeroSet);
		} else {
			allSubsets = subsets(nums, n-1);
			int item = nums[n];
			List<List<Integer>> moreSubsets = new ArrayList<>();
			for(List<Integer> sub : allSubsets) {
				List<Integer> copy = new ArrayList<>();
				copy.addAll(sub);
				copy.add(item);
				moreSubsets.add(copy);
			}
			allSubsets.addAll(moreSubsets);
		}
		return allSubsets;
	}
}
