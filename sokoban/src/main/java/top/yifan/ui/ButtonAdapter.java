package top.yifan.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 鼠标适配器
 *
 * @author star
 */
public class ButtonAdapter extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        System.exit(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getComponent();
        button.setBackground(Color.red);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getComponent();
        button.setBackground(Color.white);
    }

}
