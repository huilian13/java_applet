package kevin.config;

import java.io.Serializable;
/**
 * ≤„¥∞ø⁄≈‰÷√¿‡
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class LayerConfig implements Serializable{
	
	private String className;
	
	private int x;
	
	private int y;
	
	private int width;
	
	private int height;

	public LayerConfig(String className, int x, int y, int width, int height) {
		this.className = className;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public String getClassName() {
		return className;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
