
public class StringsArrays {
	
	public int[][] rotate(int[][] matrix, int n) {
		
		for(int layer = 0; layer < n/2; ++layer) {
			int first = layer;
			int last = n - 1 - layer;
			
			for(int i = first; i < last; ++i) {
				int offset = i - first;
				
				int top = matrix[i][first];
				
				matrix[i][first] = matrix[first][last-offset];
				
				matrix[first][last-offset] = matrix[last][last-offset];
				
				matrix[last][last-offset] = matrix[last][first-offset];
				
				matrix[last][first-offset] = top;
			}
		}
		
		return matrix;
	}
	
	
	public int[][] setZeroes(int[][] matrix) {
		boolean[] rowZeroes = new boolean[matrix.length];
		boolean[] colZeroes = new boolean[matrix[0].length];
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(matrix[i][j] == 0) {
					rowZeroes[i] = true;
					colZeroes[j] = true;
				}
			}
		}
		
		for(int i = 0; i < rowZeroes.length; i++) {
			if(rowZeroes[i]) {
				nullifyRows(matrix, i);
			}
		}
		
		for(int i = 0; i < colZeroes.length; i++) {
			if(colZeroes[i]) {
				nulligyCols(matrix, i);
			}
		}
		
		return matrix;
	}
	
	public void nullifyRows(int[][] matrix, int i) {
		for(int j = 0; j < matrix.length; j++) {
			matrix[i][j] = 0;
		}
	}
	
	public void nulligyCols(int[][] matrix, int i) {
		for(int j = 0; j < matrix.length; j++) {
			matrix[j][i] = 0;
		}
	}
	
}
