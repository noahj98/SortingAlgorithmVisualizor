import java.util.ArrayList;
import java.util.Random;

abstract public interface Sort {

	Random r = new Random();

	static int[] makeRandomArray(int width, int height, boolean unique) {
		int[] arr = new int[width];

		if (unique) {
			double j = (double) height / (double) width;
			ArrayList<Integer> arr_list = new ArrayList<>();
			arr_list.ensureCapacity(width);
			for (int i = 0; i < width; i++) {
				arr_list.add((int) ((i+1)*j));
			}
			arr = randomizeArray(arr_list);
		} else {
			for (int i = 0; i < width; i++) {
				arr[i] = r.nextInt(height+1);
			}
		}

		return arr;
	}

	static int[] randomizeArray(ArrayList<Integer> arr_list) {
		int[] arr = new int[arr_list.size()];
		for (int i = 0; !arr_list.isEmpty(); i++) {
			arr[i] = arr_list.remove(r.nextInt(arr_list.size()));
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
