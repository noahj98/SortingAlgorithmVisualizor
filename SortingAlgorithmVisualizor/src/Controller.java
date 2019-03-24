import javax.swing.JFrame;

public class Controller {
	
	private GetSettings settings_frame;
	private Settings settings;
	private JFrame sort_frame;
	private Viewer viewer;
	
	public Controller() {
		initialSetup();
	}
	
	public void settingsAreSelected() {
		settings = settings_frame.getSettings();
		if (settings.isRectangular()) {
			RectangularViewer viewer = new RectangularViewer(new BubbleSort(settings.getWidth(), settings.getHeight(), settings.isUnique(), settings.isFrameSkip()), settings.getWidth(), settings.getHeight(), settings.getTiming());
			sort_frame.add(viewer);
			start();
			viewer.start();
		} else {
			CircularViewer viewer = new CircularViewer(new BubbleSort(settings.getNumberOfLines(), settings.getRadius(), settings.isUnique(), settings.isFrameSkip()), settings.getRadius(), settings.getTiming());
			sort_frame.add(viewer);
			start();
			viewer.start();
		}
	}
	
	private void start() {
		sort_frame.pack();
		sort_frame.setVisible(true);
	}
	
	private void initialSetup() {
		sort_frame = new JFrame();
		sort_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sort_frame.setResizable(false);
		sort_frame.setLocationRelativeTo(null);
		
		settings_frame = new GetSettings(this);
		settings_frame.start();
	}
	
}
