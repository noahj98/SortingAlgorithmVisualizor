import javax.swing.JFrame;

public class Start {
	public static void main(String[] args) throws InterruptedException {
		int width = 200;       //  > 10, < 1500
		int height = 200;      //  > 10, < 1500
		int radius = 300;      //  > 5, < 625
		int timing = 15;        //  > 1, < 100
		boolean unique = true; //  true = only unique line lengths, false = not unique line lengths
		boolean fast = true;   //  true = skip some of the sorting, false = show every step
		
		//For Bubblesort, "width" controls # numbers, height controls max length of each line
		
//		Sort sorter = new BubbleSort(width, height, unique, fast);  //Viewer
		Sort sorter = new BubbleSort(500, radius, unique, fast);   //Circular Viewer
		
		CircularViewer viewer = new CircularViewer(sorter, radius, timing);
//		Viewer viewer = new Viewer(sorter, width, height, timing);

		JFrame frame = new JFrame("Bubblesort");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(viewer);
		frame.pack();
		frame.setVisible(true);
		
		viewer.start();
	}
}