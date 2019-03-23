import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Viewer extends JPanel implements ActionListener {

	private Sort sorter;
	private int width, height;
	private Timer timer;
	private Color curr_color;

	public Viewer(Sort sorter, int width, int height, int timing) {
		if (timing < 1 || timing > 500) throw new RuntimeException("Timing must be between 1 and 100 inclusive");
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