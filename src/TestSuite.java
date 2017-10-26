import sort.search.MergeSort;

public class TestSuite {

	public static void main(String[] args) {
		
//		UniqueString unString = new UniqueString();
//		boolean result = unString.isStringUnique("abcfgulknm");
//		System.out.println(result);
		
//		StringPermutations strPerms = new StringPermutations();
//		boolean res = strPerms.areTwoStringsPerms("abc", "ab");
//		System.out.println(res);
		
//		int arr[] = {12, 11, 13, 5, 6, 7};
//		 
//        System.out.println("Given Array");
//        for(int i = 0; i < arr.length; i++) {
//        	System.out.println(arr[i]);
//        }
// 
//        MergeSort ob = new MergeSort();
//        ob.sort(arr, 0, arr.length-1);
// 
//        System.out.println("\nSorted array");
//        for(int i = 0; i < arr.length; i++) {
//        	System.out.println(arr[i]);
//        }
		
//		Compress compress = new Compress();
//		String result = compress.compressString("aaabbcc");
//		System.out.println(result);
		
		int[][] table = new int[4][4];
		int count = 0;
		for (int row = 0; row < 4; row ++) {
		    for (int col = 0; col < 4; col++) {
		        table[row][col] = count;
		        count++;
		    }
		}
		
		for (int row = 0; row < 4; row ++) {
		    for (int col = 0; col < 4; col++) {
		    	System.out.print(table[row][col]);
		    	System.out.print(" ");
		    }
		    System.out.print("\n");
		}
		
		System.out.println("--------------------Ans-----------------");
		
		StringsArrays sa = new StringsArrays();
		int[][] result = sa.setZeroes(table);
		
		for (int row = 0; row < 4; row ++) {
		    for (int col = 0; col < 4; col++) {
		    	System.out.print(result[row][col]);
		    	System.out.print(" ");
		    }
		    System.out.print("\n");
		}
	}

}
