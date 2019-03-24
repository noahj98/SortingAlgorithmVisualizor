
public class Settings {
	
	private boolean is_rectangular;
	private int width;
	private int height;
	private int radius;
	private int number_of_lines;
	private int timing;
	private boolean unique_numbers;
	private boolean frame_skip;
	
	public Settings(boolean rect, int w, int h, int r, int num, int t, boolean unique, boolean skip) {
		this.is_rectangular = rect;
		this.width = w;
		this.height = h;
		this.radius = r;
		this.number_of_lines = num;
		this.timing = t;
		this.unique_numbers = unique;
		this.frame_skip = skip;
	}
	
	public void setIsRectangular(boolean rect) {
		this.is_rectangular = rect;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setRadius(int r) {
		this.radius = r;
	}
	
	public void setNumberOfLines(int n) {
		number_of_lines = n;
	}
	
	public void setTiming(int t) {
		this.timing = t;
	}
	
	public void setUnique(boolean unique) {
		this.unique_numbers = unique;
	}
	
	public void setFrameSkip(boolean fs) {
		this.frame_skip = fs;
	}
	
	public boolean isRectangular() {
		return is_rectangular;
	}
	
	public int getWidth() {
		if (!isRectangular()) throw new RuntimeException("Circle can't have a width");
		return width;
	}
	
	public int getHeight() {
		if (!isRectangular()) throw new RuntimeException("Circle can't have a width");
		return height;
	}
	
	public int getRadius() {
		if (isRectangular()) throw new RuntimeException("Rectangle is true, circular undefined");
		return radius;
	}
	
	public int getNumberOfLines() {
		if (isRectangular()) throw new RuntimeException("Rectangular is true, circular undefined");
		return number_of_lines;
	}
	
	public int getTiming() {
		return timing;
	}
	
	public boolean isUnique() {
		return unique_numbers;
	}
	
	public boolean isFrameSkip() {
		return frame_skip;
	}
	
}
