import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CircularViewer extends JPanel implements ActionListener {

	private Sort sorter;
	private int radius;
	private Timer timer;
	private Color curr_color;

	public CircularViewer(Sort sorter, int radius, int timing) {
		if (radius < 5 || radius > 625) throw new RuntimeException("Radius must be > 5 and < 625 inclusive");
		if (timing < 1 || timing > 500) throw new RuntimeException("Timing must be between 1 and 100 inclusive");
		this.sorter = sorter;
		this.radius = radius;
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
		setPreferredSize(new Dimension(this.radius * 2, this.radius * 2));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(radius, radius);

		int[] arr = sorter.getArray();
		g.setColor(curr_color);

		int x2, y2;
		if (!sorter.isFinished()) {
			for (int i = 0; i < arr.length; i++) {
				x2 = (int) (arr[i] * Math.cos(-Math.PI / 2.0 + 2.0 * Math.PI * (i + 1.0) / arr.length));
				y2 = (int) (arr[i] * Math.sin(-Math.PI / 2.0 + 2.0 * Math.PI * (i + 1.0) / arr.length));
				g.drawLine(0, 0, x2, y2);
			}
		} else {
			int[] x_vals = new int[3];
			int[] y_vals = new int[3];
			x_vals[0] = 0;
			y_vals[0] = 0;
			x_vals[1] = (int) (arr[0] * Math.cos(-Math.PI / 2.0 + 2.0 * Math.PI * (1.0) / arr.length));
			y_vals[1] = (int) (arr[0] * Math.sin(-Math.PI / 2.0 + 2.0 * Math.PI * (1.0) / arr.length));
			
			for (int i = 1; i < arr.length; i++) {
				x_vals[2] = (int) (arr[i] * Math.cos(-Math.PI / 2.0 + 2.0 * Math.PI * (i + 1.0) / arr.length));
				y_vals[2] = (int) (arr[i] * Math.sin(-Math.PI / 2.0 + 2.0 * Math.PI * (i + 1.0) / arr.length));
				
				g.fillPolygon(x_vals, y_vals, 3);
				
				x_vals[1] = x_vals[2];
				y_vals[1] = y_vals[2];
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (sorter.isFinished()) {
			timer.stop();
			curr_color = Color.GREEN;
		}
		sorter.doStep();
		repaint();
	}

}