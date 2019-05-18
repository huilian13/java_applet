package kevin.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class ButtonAdapter extends MouseAdapter{

	@Override
	public void mousePressed(MouseEvent e) {
		System.exit(0);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton button = (JButton)e.getComponent();
		button.setBackground(Color.red);
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		JButton button=(JButton)e.getComponent();
		button.setBackground(Color.white);
	}
		
}
