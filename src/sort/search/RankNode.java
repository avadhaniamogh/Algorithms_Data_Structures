package sort.search;

public class RankNode {
	
	int left_size = 0;
	int data = 0;
	RankNode left = null, right = null;
	RankNode(int d) {
		data = d;
	}
	
	public void insert(int d) {
		if(d <= data) {
			if(left != null) {
				left.insert(d);
			} else {
				left = new RankNode(d);
			}
			left_size++;
		} else {
			if(right != null) {
				right.insert(d);;
			} else {
				right = new RankNode(d);
			}
		}
	}
	
	public int getRank(int d) {
		
		if(d < data) {
			if(left == null)
				return -1;
			return left.getRank(d);
		} else if(d == data) {
			return left_size;
		} else {
			if(right == null)
				return -1;
			return left_size + 1 + right.getRank(d);
		}
	}

}
