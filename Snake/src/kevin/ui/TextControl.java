package kevin.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

/**
 * ���Ƽ������
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class TextControl extends JTextField{
	/**
	 * ��ֵ��
	 */
	private int keyCode;
	
	/**
	 * ������
	 */
	private String methodName;
	
	public TextControl(int x,int y,int width,int height,String methodName){
		//���������λ��
		this.setBounds(x, y, width, height);
		//��ʼ��������
		this.methodName=methodName;
		//��Ӽ��̼���
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				setKeyCode(e.getKeyCode());
			}
		});
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		//���������ʾ
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
