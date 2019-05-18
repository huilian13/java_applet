package kevin.window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextControl extends JTextField{
	
	/**
	 * ����ֵ
	 */
	private int keyCode;
	
	/**
	 * ������
	 */
	private String methodName;

	public TextControl(int x,int y,int width,int height,String methodName){
		//�����ı����λ��
		this.setBounds(x, y, width, height);
		//��ʼ��������
		this.methodName=methodName;
		//����¼�����
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//���ð���
				setKeyCode(e.getKeyCode());
			}
			
		});
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode){
		this.keyCode=keyCode;
		//�����������Ϣ
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}
	
	public String getMethodName() {
		return methodName;
	}
	
}
