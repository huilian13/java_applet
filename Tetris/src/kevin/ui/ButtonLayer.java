package kevin.ui;

import java.awt.Graphics;

public class ButtonLayer extends Layer{

	public ButtonLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		
	}

}
