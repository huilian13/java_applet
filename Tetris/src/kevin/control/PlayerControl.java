package kevin.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerControl extends KeyAdapter{
  
	private GameControl gameControl;
	
	public PlayerControl(GameControl gameControl){
		this.gameControl=gameControl;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		this.gameControl.actionByKey(e.getKeyCode());
	}
}
