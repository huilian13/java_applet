package top.yifan.ui;

import top.yifan.config.GameConfig;
import top.yifan.util.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏窗口
 *
 * @author star
 */
public class GameFrame extends JFrame {

    public GameFrame(GamePanel gamePanel) throws HeadlessException {
        // 设置标题
        this.setTitle("飞机大战1.0");
        // 设置默认面板
        this.setContentPane(gamePanel);
        // 设置窗口位置大小
        FrameUtil.frameCenter(GameConfig.getFrameConfig().getWidth(),
                GameConfig.getFrameConfig().getHeight(), this);
    }
}
