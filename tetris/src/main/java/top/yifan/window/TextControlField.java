package top.yifan.window;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 文本控制域
 *
 * @author star
 */
public class TextControlField extends JTextField{

	private static final long serialVersionUID = 7013893275034734330L;

	/**
	 * 按键值
	 */
	private int keyCode;

	/**
	 * 方法名
	 */
	private String methodName;

	public TextControlField(int x, int y, int width, int height, String methodName){
		// 设置文本框的位置
		this.setBounds(x, y, width, height);
		// 初始化方法名
		this.methodName=methodName;
		// 添加事件监听
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// 设置按键
				setKeyCode(e.getKeyCode());
			}

		});
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode){
		this.keyCode=keyCode;
		// 设置输入框信息
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}

	public String getMethodName() {
		return methodName;
	}

}
