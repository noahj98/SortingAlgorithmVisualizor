import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.Timer;

public class RectangularViewer extends JPanel implements Viewer, ActionListener {

	private Sort sorter;
	private int width, height;
	private Timer timer;
	private Color curr_color;

	public RectangularViewer(Sort sorter, int width, int height, int timing) {
		if (width < 100 || height < 100 || width > 1000 || height > 1000) throw new RuntimeException("Width/Height must be > 100, < 1000 (inclusive)");
		if (timing < 1 || timing > 150) throw new RuntimeException("Timing must be between 1 and 150 (inclusive)");
		this.sorter = sorter;
		this.width = width;
		this.height = height;
		curr_color = Color.WHITE;
		setup();
		timer = new Timer(timing, this);
	}

	
	public void start() {
		timer.start();
	}

	private void setup() {
		setOpaque(true);
		setFocusable(true);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(this.width, this.height));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.translate(0, height);
		
		int[] arr = sorter.getArray();
		g.setColor(curr_color);
				
		for (int i = 0; i < arr.length; i++) {
			g.drawLine(i, 0, i, -arr[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (sorter.isFinished()) {
			timer.stop();
			curr_color = Color.GREEN;
		}
		sorter.doStep();
		repaint();
	}

}