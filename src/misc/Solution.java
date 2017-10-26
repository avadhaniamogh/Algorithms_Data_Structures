package misc;

public class Solution {
	
	int lis(int[] arr) {
		int n = arr.length;
		
		int[] temp = new int[n];
		
		for(int i = 0; i < n; i++)
			temp[i] = 1;
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j]) {
					if(temp[i] < (temp[j] + 1)) {
						temp[i] = temp[j] + 1;
					}
				}
			}
		}
		
		int max = 0;
		int lis_val = 0;
		int max_index = -1;
		for(int i = 0; i < n; i++) {
			if(max < temp[i]) {
				max = temp[i];
				max_index = i;
			}
		}
		lis_val = max;
		
		for(int i = max_index; i >= 0; i--) {
			if(temp[i] == lis_val) {
				System.out.print(arr[i] + "  ");
				lis_val--;
			}
			if(lis_val < 1) {
				break;
			}
		}
		
		
		return max;
	}
	
	public String longestCommonPrefix(String[] strs) {
        if(strs.length <= 0)
            return "";
        char[] resultCharArray = strs[0].toCharArray();
        if(strs.length <= 0)
            return null;
        
        int n = resultCharArray.length - 1;
        for(String str : strs) {
            char[] strCharArray = str.toCharArray();
            int i = 0, j = 0;
            if(strCharArray.length <= n)
                n = strCharArray.length - 1;
            while((i < resultCharArray.length) && (j < strCharArray.length)) {
                if(resultCharArray[i] != strCharArray[j]) {
                	if((i-1) < n)
                		n = i - 1;
                    break;
                }
                i++;
                j++;
            }
            System.out.println(n);
        }
        StringBuilder answer = new StringBuilder();
        for(int k = 0; k <= n; k++) {
            answer.append(resultCharArray[k]);
        }
        System.out.println(n);
        return answer.toString();
    }
	
	public String longestCommonPrefixSol(String[] strs) {
	    if (strs.length == 0) return "";
	    String prefix = strs[0];
	    for (int i = 1; i < strs.length; i++)
	        while (strs[i].indexOf(prefix) != 0) {
	            prefix = prefix.substring(0, prefix.length() - 1);
	            if (prefix.isEmpty()) return "";
	        }        
	    return prefix;
	}

}
