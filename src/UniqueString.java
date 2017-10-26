
public class UniqueString {
	
	public boolean isStringUnique(String str) {
		for(int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			for(int j = i+1; j < str.length(); j++) {
				char b = str.charAt(j);
				if (a == b) {
					return false;
				}
			}
		}
		return true;
	}

}
