package top.yifan.ui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 控制键输入框
 *
 * @author star
 */
@SuppressWarnings("serial")
public class TextControl extends JTextField {
    /**
     * 键值码
     */
    private int keyCode;

    /**
     * 方法名
     */
    private String methodName;

    public TextControl(int x, int y, int width, int height, String methodName) {
        // 设置输入框位置
        this.setBounds(x, y, width, height);
        // 初始化方法名
        this.methodName = methodName;
        // 添加键盘监听
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
        // 在输入框显示
        this.setText(KeyEvent.getKeyText(this.keyCode));
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
