package top.yifan.ui;

import java.awt.Graphics;

/**
 * 按钮窗口层
 *
 * @author star
 */
public class ButtonLayer extends AbstractLayer {

    public ButtonLayer(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    @Override
    public void paint(Graphics g) {
        this.createWindow(g);

    }

}
