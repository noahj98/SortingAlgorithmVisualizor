
public class BubbleSort implements Sort {
	
	int[] arr;
	int width;
	int curr_i;
	boolean finished;
	
	public BubbleSort(int width, int height) {
		if (width < 1 || height < 1) throw new IllegalArgumentException("width/height must be > 0");
		
		arr = Sort.makeRandomArray(width, height);
		curr_i = 0;
		this.width = width;
		finished = false;
		
	}
	
	public int[] getArray() {
		return arr;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	private boolean checkForSwap(int a, int b) {
		return arr[b] < arr[a];
	}
	
	public void doStep() {
		if (isFinished()) return;
		
		for (int j = 0; j < width-1-curr_i; j++) {
			if (checkForSwap(j, j+1)) Sort.swap(arr, j, j+1);
		}
		curr_i++;
		if (curr_i == width) {
			finished = true;
		}
	}

}
