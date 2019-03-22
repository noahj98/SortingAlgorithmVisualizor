import java.util.Random;

abstract public interface Sort {
	
	Random r = new Random();
	
	static int[] makeRandomArray(int width, int height) {
		int[] arr = new int[width];
		for (int i = 0; i < width; i++) {
			arr[i] = r.nextInt(height);
		}
		return arr;
	}
	
	static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public boolean isFinished();
	public void doStep();
	public int[] getArray();
	
}
