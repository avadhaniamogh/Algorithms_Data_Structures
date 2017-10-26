package sort.search;

public class MergeSort {

	public void merge(int[] arr, int start, int mid, int end) {
		
		int size_l = mid - start+1;
		int size_r = end - mid;
		
		int[] N1 = new int[size_l];
		int[] N2 = new int[size_r];
		
		for(int i = 0; i < size_l; i++) {
			N1[i] = arr[start + i];
		}
		for(int j = 0; j < size_r; j++) {
			N2[j] = arr[mid+1 + j];
		}
			
		int i = 0; int j = 0;
		int k = start;
		
		while(i < size_l && j < size_r) {
			if(N1[i] <= N2[j]) {
				arr[k] = N1[i];
				i++;
			} else {
				arr[k] = N2[j];
				j++;
			}
			k++;
		}
		
		while(i < size_l) {
			arr[k] = N1[i];
			i++;
			k++;
		}
		
		while(j < size_r) {
			arr[k] = N2[j];
			j++;
			k++;
		}
	}

	public void sort(int[] arr, int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;

			sort(arr, start, mid);
			sort(arr, mid+1, end);
			
			merge(arr, start, mid, end);
		}
	}

}
