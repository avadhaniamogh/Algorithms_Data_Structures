
public class Compress {
	
	public String compressString(String str) {
		StringBuilder builder = new StringBuilder();
		
		int consecutiveCount = 0;
		for(int i = 0; i < str.length(); i++) {
			consecutiveCount++;
			
			if(i+1 >= str.length() || str.charAt(i) != str.charAt(i+1)) {
				builder.append(""+ str.charAt(i) + consecutiveCount);
				consecutiveCount = 0;
			}
		}
		
		return str.length() <= builder.length() ? str : builder.toString(); 
	}

}
