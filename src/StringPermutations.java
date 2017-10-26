import java.util.HashMap;
import java.util.Map;

public class StringPermutations {
	
	public boolean areTwoStringsPerms(String str1, String str2) {
		HashMap<Character, Integer> str1Map = new HashMap<>();
		char[] str1CharArray = str1.toCharArray();
		for(char a : str1CharArray) {
			if(str1Map.containsKey(a)) {
				int count = str1Map.get(a);
				count += 1;
				str1Map.put(a, count);
			} else {
				str1Map.put(a, 1);
			}
		}
		
		char[] str2CharArray = str2.toCharArray();
		for(char b : str2CharArray) {
			if(str1Map.containsKey(b)) {
				int count = str1Map.get(b);
				count -= 1;
				str1Map.put(b, count);
			}
		}
		
		for(Map.Entry<Character, Integer> entry : str1Map.entrySet()) {
			if(entry.getValue() != 0) {
				return false;
			}
		}
		
		return true;
	}

}
