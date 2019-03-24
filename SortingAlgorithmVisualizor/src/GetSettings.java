import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class GetSettings implements ActionListener {

	private JFrame frame;
	private Controller controller;

	private JRadioButton bubbleSort;

	private JRadioButton circular;
	private JRadioButton rectangular;

	private JSlider width;
	private JSlider height;
	private JSlider radius;
	private JSlider amount_of_numbers;

	private JSlider timing_slider;

	private JCheckBox unique_checkbox;

	private JCheckBox fast_checkbox;
	
	private JButton submit;
	
	private Settings settings;

	public GetSettings(Controller controller) {
		this.controller = controller;
		initialFrameSetup();

		sortSetup();
		displaySetup();
		dimensionsSetup();
		timingSetup();
		uniqueSetup();
		fastSetup();
		settingsSetup();
		submitSetup();

		postFrameSetup();
		// sort, display, w/h or r, timing, unique, fast
	}

	public void start() {
		frame.setVisible(true);
	}
	
	public Settings getSettings() {
		settings.setIsRectangular(rectangular.isSelected());
		settings.setWidth(width.getValue());
		settings.setHeight(height.getValue());
		settings.setRadius(radius.getValue());
		settings.setNumberOfLines(amount_of_numbers.getValue());
		settings.setTiming(timing_slider.getValue());
		settings.setUnique(unique_checkbox.isSelected());
		settings.setFrameSkip(fast_checkbox.isSelected());
		
		return settings;
	}
	
	private void settingsSetup() {
		settings = new Settings(rectangular.isSelected(), width.getValue(), height.getValue(),
								radius.getValue(), amount_of_numbers.getValue(), timing_slider.getValue(),
								unique_checkbox.isSelected(), fast_checkbox.isSelected());
	}
	
	private void sortSetup() {
		// Select Sort - JRadioButton
		JPanel sort = new JPanel();
		bubbleSort = new JRadioButton();
		sort.add(bubbleSort);
		bubbleSort.setText("Bubblesort");
		bubbleSort.setSelected(true);
		bubbleSort.addActionListener(this);

		frame.add(sort);
	}

	private void displaySetup() {
		// round/square - view - JRadioButton
		JPanel display = new JPanel();
		circular = new JRadioButton();
		rectangular = new JRadioButton();
		display.add(rectangular);
		display.add(circular);

		circular.setText("Circular");
		circular.addActionListener(this);

		rectangular.setText("Rectangular");
		rectangular.addActionListener(this);

		rectangular.setSelected(true);

		frame.add(display);
	}

	private void dimensionsSetup() {
		// 3 JSliders
		JPanel dimensions = new JPanel(new BorderLayout());

		JPanel dimensions_rectangular = new JPanel(new BorderLayout());
		JPanel dimensions_circular = new JPanel(new BorderLayout());

		JPanel width_panel = new JPanel();
		JPanel height_panel = new JPanel();
		JPanel radius_panel = new JPanel();
		JPanel number_of_lines_panel = new JPanel();

		JLabel width_label = new JLabel("Width: ");
		JLabel height_label = new JLabel("Height: ");
		JLabel radius_label = new JLabel("Radius: ");
		JLabel lines_label = new JLabel("Lines: ");

		width = new JSlider(100, 1000);
		height = new JSlider(100, 1000);
		radius = new JSlider(100, 500);
		amount_of_numbers = new JSlider(100, 1000);

		width_panel.add(width_label);
		width_panel.add(width);

		height_panel.add(height_label);
		height_panel.add(height);

		radius_panel.add(radius_label);
		radius_panel.add(radius);

		number_of_lines_panel.add(lines_label);
		number_of_lines_panel.add(amount_of_numbers);

		dimensions_rectangular.add(width_panel, BorderLayout.PAGE_START);
		dimensions_rectangular.add(height_panel, BorderLayout.PAGE_END);

		dimensions_circular.add(radius_panel, BorderLayout.PAGE_START);
		dimensions_circular.add(number_of_lines_panel, BorderLayout.PAGE_END);

		dimensions.add(dimensions_rectangular, BorderLayout.PAGE_START);
		dimensions.add(dimensions_circular, BorderLayout.PAGE_END);

		Hashtable width_height_table = new Hashtable();
		width_height_table.put(new Integer(100), new JLabel("100"));
		width_height_table.put(new Integer(550), new JLabel("550"));
		width_height_table.put(new Integer(1000), new JLabel("1000"));

		Hashtable radius_table = new Hashtable();
		radius_table.put(new Integer(100), new JLabel("100"));
		radius_table.put(new Integer(300), new JLabel("300"));
		radius_table.put(new Integer(500), new JLabel("500"));

		width.setLabelTable(width_height_table);
		height.setLabelTable(width_height_table);
		radius.setLabelTable(radius_table);
		amount_of_numbers.setLabelTable(width_height_table);

		width.setPaintLabels(true);
		height.setPaintLabels(true);
		radius.setPaintLabels(true);
		amount_of_numbers.setPaintLabels(true);

		radius.setEnabled(false);
		amount_of_numbers.setEnabled(false);

		frame.add(dimensions);
	}

	private void timingSetup() {
		// JSlider
		JPanel timing = new JPanel();

		JLabel timing_label = new JLabel("Timing (ms): ");
		timing_slider = new JSlider(1, 150);

		timing.add(timing_label);
		timing.add(timing_slider);

		Hashtable timing_table = new Hashtable();
		timing_table.put(new Integer(1), new JLabel("1"));
		timing_table.put(new Integer(150), new JLabel("150"));

		timing_slider.setLabelTable(timing_table);
		timing_slider.setPaintLabels(true);

		frame.add(timing);
	}

	private void uniqueSetup() {
		// Checkbox
		JPanel unique = new JPanel();

		unique_checkbox = new JCheckBox();

		unique.add(unique_checkbox);

		unique_checkbox.setText("Unique Lengths");

		frame.add(unique);
	}

	private void fastSetup() {
		// Checkbox
		JPanel fast = new JPanel();

		fast_checkbox = new JCheckBox();

		fast.add(fast_checkbox);

		fast_checkbox.setText("Frame Skip");

		frame.add(fast);
	}

	private void initialFrameSetup() {
		frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	private void postFrameSetup() {
		frame.pack();
	}

	private void submitSetup() {
		submit = new JButton();
		JPanel submit_panel = new JPanel();
		submit.setText("Start");
		submit.addActionListener(this);
		submit_panel.add(submit);
		
		frame.add(submit_panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == circular) {

			if (!circular.isSelected())
				circular.setSelected(true);
			else {
				rectangular.setSelected(false);
				width.setEnabled(false);
				height.setEnabled(false);
				radius.setEnabled(true);
				amount_of_numbers.setEnabled(true);
			}

		} else if (source == rectangular) {

			if (!rectangular.isSelected())
				rectangular.setSelected(true);
			else {
				circular.setSelected(false);
				width.setEnabled(true);
				height.setEnabled(true);
				radius.setEnabled(false);
				amount_of_numbers.setEnabled(false);
			}
		} else  if (source == submit) {
			stop();
		}

	}
	
	private void stop() {
		frame.setVisible(false);
		controller.settingsAreSelected();
	}
	
}
