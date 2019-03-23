
public class BubbleSort implements Sort {

	int[] arr;
	int width;
	int curr_i;
	int curr_j;
	boolean finished;
	boolean doFast;

	public BubbleSort(int width, int height, boolean unique, boolean doFast) {
		if (width < 10 || height < 10 || width > 1500 || height > 1500)
			throw new IllegalArgumentException("width/height must be > 10 and < 1500 inclusive");

		arr = Sort.makeRandomArray(width, height, unique);
		curr_i = 0;
		curr_j = 0;
		this.width = width;
		finished = false;
		this.doFast = doFast;
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
		if (isFinished())
			return;

		if (doFast) {
			for (int j = 0; j < width - 1 - curr_i; j++) {
				if (checkForSwap(j, j + 1))
					Sort.swap(arr, j, j + 1);
			}
			curr_i++;
		} else {
			if (checkForSwap(curr_j, curr_j+1)) Sort.swap(arr,  curr_j,  curr_j + 1);
			curr_j++;
			if (curr_j >= width - 1 - curr_i) {
				curr_j = 0;
				curr_i++;
			}
		}
		if (curr_i == width) {
			finished = true;
		}
	}

}
