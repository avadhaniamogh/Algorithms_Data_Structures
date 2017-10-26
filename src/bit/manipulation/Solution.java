package bit.manipulation;

public class Solution {
	
	public int updateBits(int n, int m, int i, int j) {
		
		int allones = ~0;
		int left = allones << j+1;
		
		int right = (1 << i) - 1;
		
		int mask = left | right;
		
		int n_cleared = n & mask;
		int m_shifted = m << i;
		
		return n_cleared | m_shifted;
		
	}

}
