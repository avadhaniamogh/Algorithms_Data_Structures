import java.util.HashMap;
import java.util.Map;

public class PermutationPalindromw {
	
	public boolean isPermutationOfPalindrome(String phrase) {
		char[] strArray = phrase.toCharArray();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(char a : strArray) {
			if(map.containsKey(a)) {
				int count = map.get(a);
				map.put(a, count+1);
			} else {
				map.put(a, 1);
			}
		}
		boolean oddMaxFlag = false;
		
		for(Map.Entry<Character, Integer> entry : map.entrySet()) {
			if(entry.getValue() % 2 != 0) {
				if(oddMaxFlag) {
					return false;
				} else {
					oddMaxFlag = true;
				}
			}
		}
		return true;
		
	}

}
