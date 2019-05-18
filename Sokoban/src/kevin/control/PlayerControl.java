package kevin.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * ��ҿ�����
 * @author kevin
 *
 */
public class PlayerControl extends KeyAdapter{
	/**
	 * ��Ϸ������
	 */
	private GameControl gameControl;

	public PlayerControl(GameControl gameControl) {
		super();
		//��ʼ����Ϸ������
		this.gameControl = gameControl;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//������Ϸ���﷽��
		this.gameControl.actionKeys(e.getKeyCode());
	}
	
}
