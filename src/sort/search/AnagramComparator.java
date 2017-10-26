package sort.search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class AnagramComparator implements Comparator<String> {
	
	public String sort(String s) {
		char[] array = s.toCharArray();
		Arrays.sort(array);
		return new String(array);
	}

	@Override
	public int compare(String o1, String o2) {
		
		return sort(o1).compareTo(sort(o2));
	}

}
