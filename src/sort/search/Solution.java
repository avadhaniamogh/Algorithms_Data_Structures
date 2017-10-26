package sort.search;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;

public class Solution {

	public void merge(int[] a, int[] b, int lengthA, int lengthB) {

		int lastIndexA = lengthA - 1;
		int lastIndexB = lengthB - 1;
		int mergedLastIndex = lastIndexA + lastIndexB - 1;

		while(lastIndexB >= 0) {
			if(lastIndexA >= 0 && a[lastIndexA] > b[lastIndexB]) {
				a[mergedLastIndex] = a[lastIndexA];
				lastIndexA--;
			} else {
				a[mergedLastIndex] = b[lastIndexB];
				lastIndexB--;
			}
			mergedLastIndex--;
		}
	}

	public void anagramCompare(String[] array) {
		Arrays.sort(array, new AnagramComparator());
	}

	public int search(int[] list, int value) {

		int index = 0;
		while(list[index] != -1 && list[index] < value) {
			index *= 2;
		}

		int high = list[index];
		int low = 0;
		while(low <= high) {
			int mid = (low + high) / 2;

			if(list[mid] < value) {
				low = mid + 1;
			} else if (list[mid] > value || list[mid] == -1) {
				high = mid - 1;
			} else if (list[mid] == value) {
				return mid;
			}
		}
		return -1;
	}

	public int sparseStringSearch(String[] array, String str) {
		if(array == null || str == null || str == "") {
			return -1;
		}
		return sparseStringSearch(array, str, 0, array.length);
	}

	public int sparseStringSearch(String[] array, String str, int low, int high) {
		if(high < low)
			return -1;

		int mid = (low + high) / 2;

		if(array[mid].isEmpty()) {
			int left = mid-1;
			int right = mid+1;
			while(true) {
				if(left < low && right > high)
					return -1;
				if(!array[left].isEmpty() && left >= low) {
					mid = left;
					break;
				} else if(!array[right].isEmpty() && right <= high) {
					mid = right;
					break;
				} 
				left--;
				right++;
			}
		}
		
		if(array[mid].equals(str)) {
			return mid;
		} else if(array[mid].compareTo(str) < 0) {
			return sparseStringSearch(array, str, mid+1, high);
		} else {
			return sparseStringSearch(array, str, low, mid-1);
		}
	}
	
	public void printDuplicates(int[] array) {
		BitSet bitset = new BitSet(32000);
		
		for(int i = 0; i < array.length; i++) {
			int num = array[i];
			int num_0 = num - 1;
			if(bitset.get(num_0)) {
				System.out.println(num);
			} else {
				bitset.set(num_0);
			}
		}
	}
	
	public void sortValleyPeak(int[] array) {
		Arrays.sort(array);
		for(int i = 1; i < array.length; i+=2) {
			int temp = array[i];
			array[i] = array[i-1];
			array[i-1] = temp;
		}
	}
	
	public void countSort(int[] nums) {
		
		int[] output = new int[nums.length];
		int[] count_array = new int[3];
		
		for(int i = 0; i < nums.length; i++) {
			int val = nums[i];
			count_array[val] += 1;
		}
		for(int i = 0; i < count_array.length; i++) {
			if(i > 0)
				count_array[i] += count_array[i-1];
//			System.out.println(count_array[i]);
		}
		for(int i = nums.length; i > 0; i--) {
			int val = nums[i-1];
			int index = count_array[val];
			output[index-1] = val;
			count_array[val] -= 1;
		}
		
		for(int i = 0; i < output.length; i++) {
			System.out.println(output[i]);
		}
	}
}
