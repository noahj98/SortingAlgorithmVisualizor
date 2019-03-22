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

	public CircularViewer(Sort sorter, int radius) {
		this.sorter = sorter;
		this.radius = radius;
		curr_color = Color.WHITE;
		setup();
		timer = new Timer(1, this);
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
				x2 = (int) (arr[i] * Math.cos(-Math.PI / 2 + 2 * Math.PI * i / arr.length));
				y2 = (int) (arr[i] * Math.sin(-Math.PI / 2 + 2 * Math.PI * i / arr.length));
				g.drawLine(0, 0, x2, y2);
			}
		} else {
			int[] x_vals = new int[3];
			int[] y_vals = new int[3];
			x_vals[0] = 0;
			y_vals[0] = 0;
			x_vals[1] = (int) (arr[0] * Math.cos(-Math.PI / 2));
			y_vals[1] = (int) (arr[0] * Math.sin(-Math.PI / 2));
			
			for (int i = 1; i < arr.length - 1; i++) {
				x_vals[2] = (int) (arr[i] * Math.cos(-Math.PI / 2 + 2 * Math.PI * i / arr.length));
				y_vals[2] = (int) (arr[i] * Math.sin(-Math.PI / 2 + 2 * Math.PI * i / arr.length));
				
				g.fillPolygon(x_vals, y_vals, 3);
				
				x_vals[1] = x_vals[2];
				y_vals[1] = y_vals[2];
			}
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