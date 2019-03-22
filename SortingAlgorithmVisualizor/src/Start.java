import javax.swing.JFrame;

public class Start {
	public static void main(String[] args) {
		
		int width = 500, height = 500, radius = 500;
		
		Sort sorter = new BubbleSort(width, height);
		
		JFrame frame = new JFrame("Bubblesort");
//		CircularViewer viewer = new CircularViewer(sorter, radius);
		Viewer viewer = new Viewer(sorter, width, height);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(viewer);
		frame.pack();
		frame.setVisible(true);
		
		viewer.start();
	}
}