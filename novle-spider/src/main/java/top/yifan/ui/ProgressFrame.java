package top.yifan.ui;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * 进度条界面
 *
 * @author star
 **/

public class ProgressFrame {

    private static ProgressFrame progressFrame = null;

    private JFrame frame;

    private Container contentPane;

    private JProgressBar progressBar;

    private ProgressFrame() {
        frame = new JFrame();
        frame.setBounds(this.createRectangle());
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(false);
        frame.setResizable(false);

        contentPane = frame.getContentPane();
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);
        progressBar.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.green);
        contentPane.add(progressBar);

        frame.setVisible(true);
    }

    private Rectangle createRectangle() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        // 获取屏幕高度和宽度
        int width = dimension.width;
        int height = dimension.height;
        Rectangle progressRec = new Rectangle(width / 5 * 2, height / 2, width / 5, 20);

        return progressRec;
    }

    /**
     * 单例模式（双重检查）
     *
     * @return
     */
    public static ProgressFrame getInstance() {
        if (progressFrame == null) {
            synchronized (ProgressFrame.class) {
                if (progressFrame == null) {
                    progressFrame = new ProgressFrame();
                }
            }

        }
        return progressFrame;
    }

    public void stopProgressBar() {
        frame.dispose();
    }

    public void setProgress(double value, int progress) {
        if (value >= 1) {
            progressBar.setString(new DecimalFormat("#.000").format(value) + "%");
        } else {
            progressBar.setString("0" + new DecimalFormat("#.000").format(value) + "%");
        }
        progressBar.setValue(progress);
    }
}

