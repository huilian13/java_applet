package com.kevin.ui;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * 2018-06-17 15:59
 * 进度条界面
 *
 * @author kevin
 **/

public class ProgressFrame {
    private static final Dimension DEMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = DEMENSION.width;
    public static final int HEIGHT = DEMENSION.height;
    private static Rectangle progressRec = new Rectangle(WIDTH / 5 * 2, HEIGHT / 2, WIDTH / 5, 20);

    private static ProgressFrame progressFrame = null;
    private static JFrame frame;
    private static Container contentPane;
    private static JProgressBar progressBar;

    private ProgressFrame() {
        frame = new JFrame();
        frame.setBounds(progressRec);
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

    public static ProgressFrame getInstance(){
        if (progressFrame == null){
            progressFrame = new ProgressFrame();
        }
        return progressFrame;
    }

    public static void stopProgressBar() {
        frame.dispose();
    }

    public static void setProgress(double value, int progress) {
        if (value >= 1) {
            progressBar.setString(String.valueOf(new DecimalFormat("#.000").format(value)) + "%");
        } else {
            progressBar.setString("0" + String.valueOf(new DecimalFormat("#.000").format(value)) + "%");
        }
        progressBar.setValue(progress);
    }
}

