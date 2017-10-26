package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sol {
	
	public int rotatedSortedSerch(int[] nums, int target) {
		
		if(nums.length <= 0)
			return -1;
		
		int pivot = findPivot(nums, target, 0, nums.length-1);
		if(pivot == -1) {
			return binarySearch(nums, target, 0, nums.length-1);
		}
		
		if(nums[pivot] == target) {
			return pivot;
		}
		if(nums[0] > target) {
			return binarySearch(nums, target, pivot+1, nums.length-1);
		} else {
			return binarySearch(nums, target, 0, pivot-1);
		}
	}
	
	public int findPivot(int[] nums, int target, int low, int high) {
		if(high < low)
			return -1;
		
		if(high == low)
			return low;
		
		int mid = (low + high) / 2;
		if(mid < high && nums[mid] > nums[mid+1]) {
			return mid;
		}
		if(mid > low && nums[mid] < nums[mid-1]) {
			return mid-1;
		}
		if(nums[low] >= nums[mid]) {
			return findPivot(nums, target, low, mid-1);
		} else {
			return findPivot(nums, target, mid+1, high);
		}
	}
	
	public int binarySearch(int[] nums, int target, int low, int high) {
		if(high < low)
			return -1;
		int mid = (low + high) / 2;
		
		if(nums[mid] == target) {
			return mid;
		} else if(nums[mid] > target) {
			return binarySearch(nums, target, low, mid-1);
		} else {
			return binarySearch(nums, target, mid+1, high);
		}
	}
	
	public void getLongestCommonSubstring(String a, String b) {
		int m = a.length();
		int n = b.length();
		
		int max = 0;
		int[][] table = new int[m+1][n+1];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(a.charAt(i) == b.charAt(j)) {
					if(i == 0 || j == 0) {
						table[i][j] = 1;
					} else {
						table[i][j] = table[i-1][j-1] + 1;
					}
				}
				if(table[i][j] > max) {
					max = table[i][j];
				}
			}
		}
	}
	
	public int getLongestCommonSubsequence(String a, String b) {
		int m = a.length();
		int n = b.length();
		
		int[][] table = new int[m+1][n+1];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(a.charAt(i) == b.charAt(j)) {
					if(i == 0 || j == 0) {
						table[i][j] = 1;
					} else {
						table[i][j] = table[i-1][j-1] + 1;
					}
				} else {
					if(i == 0) {
						table[i][j] = Math.max(0, table[i][j-1]);
					} else if(j == 0) {
						table[i][j] = Math.max(table[i-1][j], 0);
					} else {
						table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
					}
				}
			}
		}
		
		int index = table[m][n];
		int to_be_used_later = index;
		
		char[] res = new char[index+1];
		
		res[index] = '\0';
		
		int i = m, j = n;
		while(i > 0 && j > 0) {
			if(a.charAt(i-1) == b.charAt(j-1)) {
				res[index-1] = a.charAt(i-1);
				
				i--;
				j--;
				index--;
			} else {
				if(table[i-1][j] > table[i][j-1]) {
					i--;
				} else {
					j--;
				}
			}
		}
		
		for(int k = 0; k < to_be_used_later; k++) {
			System.out.println(res[k]);
		}
		
		return table[m][n];
	}
	
//	public List<List<Integer>> levelOrderTraversal(TreeNode root) {
//		ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
//		List<Integer> oneLevel = new ArrayList<>();
//		
//		Queue current = new LinkedList();
//		Queue next = new LinkedList();
//		current.add(root);
//		while(!current.isEmpty()) {
//			TreeNode node = (TreeNode) current.remove();
//			
//			if(node.left != null)
//				next.add(node.left);
//			if(node.right != null)
//				next.add(node.right);
//			
//			oneLevel.add(node.data);
//			if(current.isEmpty()) {
//				current = next;
//				al.add((ArrayList<Integer>) oneLevel);
//				next = new LinkedList();
//				oneLevel = new ArrayList<>();
//			}
//		}
//		return (List)al;
//	}
	
	public String getDataInLevelOrderPosition(TreeNode root, int position) {
		int count = 0;
		
		Queue current = new LinkedList();
		Queue next = new LinkedList();
		
		current.add(root);
		while(!current.isEmpty()) {
			TreeNode node = (TreeNode) current.remove();
			
			if(count == position)
				return node.data;
			
			if(node.left != null)
				next.add(node.left);
			if(node.right != null)
				next.add(node.right);
			
			if(current.isEmpty()) {
				current = next;
				next = new LinkedList();
			}
			count += 1;
		}
		return "";
	}
	
public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> pyr = new ArrayList<>();
        
        for(int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    row.add(j, 1);
                } else {
                    int put = (pyr.get(i-1)).get(j-1) + (pyr.get(i-1)).get(j);
                    row.add(j, put);
                }
            }
            pyr.add(row);
        }
        
        return pyr;
    }

	public int getIntegerComplement(int n) {
		
		String binary_rep = Integer.toBinaryString(n);
		char[] binary_char_arr = binary_rep.toCharArray();
		char[] res_arr = new char[binary_char_arr.length];
		int j = 0;
		for(int i = 0; i <= binary_char_arr.length-1; i++) {
			char c = binary_char_arr[i];
			if(c == '1') {
				res_arr[j] = '0'; 
			} else if(c == '0') {
				res_arr[j] = '1';
			}
			j++;
		}
		String str = new String(res_arr);
//		System.out.println(str);
		int ans = Integer.parseInt(str, 2);
		return ans;
	}
	
	public String mergeStrings(String a, String b) {
		
		char[] a_arr = a.toCharArray();
		char[] b_arr = b.toCharArray();
		
		char[] res_arr = new char[a.length()+b.length()];
		
		int i = 0;
		int j = 0;
		int k = 0;
		while((i < a_arr.length) && (j < b_arr.length)) {
			if(k % 2 == 0) {
				res_arr[k] = a_arr[i];
				i++;
			} else {
				res_arr[k] = b_arr[j];
				j++;
			}
			k++;
		}
		
		while(i < a_arr.length) {
			res_arr[k] = a_arr[i];
			i++;
			k++;
		}
		
		while(j < b_arr.length) {
			res_arr[k] = b_arr[j];
			j++;
			k++;
		}
		String res = new String(res_arr);
		return res;
	}
	
	public String[] sortedNames(String[] names) {
		String[] dec_names = new String[names.length];
		String[] res_names = new String[names.length];
		
		for(int i = 0; i < names.length; i++) {
			String name = names[i];
			String[] name_split = name.split(" ");
			if(name_split[1] != null) {
				String dec = romanToDecimal(name_split[1]);
				String dec_full = name_split[0] + " " + dec;
				dec_names[i] = dec_full;
			}
		}
		Arrays.sort(dec_names);
		int i = 0;
		for(String name : dec_names) {
			String[] name_split = name.split(" ");
			if(name_split[1] != null) {
				String rom = decimalToRoman(Integer.valueOf(name_split[1]));
				String rom_full = name_split[0] + " " + rom;
				res_names[i] = rom_full;
			}
			i++;
		}
		
		for(String name : res_names) {
			System.out.println(name);
		}
		return null;
	}
	
	public int valueOf(char c) {
		switch(c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		default: 
			return -1;
		}
	}
	
	public String romanToDecimal(String str) {
		int res = 0;
		
		for(int i = 0; i < str.length(); i++) {
			int s1 = valueOf(str.charAt(i));
			
			if(i+1 < str.length()) {
				int s2 = valueOf(str.charAt(i+1));
				
				if(s1 >= s2) {
					res = res + s1;
				} else {
					res = res + s2 - s1;
					i++;
				}
			} else {
				res = res + s1;
				i++;
			}
		}
		return String.valueOf(res);
	}
	
	public String decimalToRoman(int num) {
		StringBuilder sb = new StringBuilder();
		HashMap<Integer, String> map = new HashMap<>();
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");
		int[] nums = new int[] {50, 40, 10, 9, 5, 4, 1};
		for(int i = 0; i < nums.length; i++) {
			while(num >= nums[i]) {
				num -= nums[i];
				sb.append(map.get(nums[i]));
			}
		}
		return sb.toString();
	}
	
	public String[] simpleSort(String[] names) {
		Arrays.sort(names);
		for(String name : names) {
			System.out.println(name);
		}
		return names;
	}

}
