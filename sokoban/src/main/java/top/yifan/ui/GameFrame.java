package top.yifan.ui;

import top.yifan.config.GameConfig;
import top.yifan.constant.GameImageConstant;
import top.yifan.util.FrameUtil;

import javax.swing.*;

/**
 * 游戏窗口界面
 *
 * @author star
 */
public class GameFrame extends JFrame {

    public GameFrame(GamePanel gamePanel) {
        // 设置标题
        this.setTitle(GameConfig.getFrameConfig().getTitle());
        // 设置图标
        this.setIconImage(GameImageConstant.TITLE);
        // 设置默认面板
        this.setContentPane(gamePanel);
        // 设置窗口大小并居中
        FrameUtil.frameCenter(GameConfig.getFrameConfig().getWidth(), GameConfig.getFrameConfig().getHeight(), this);
        // 去除窗口边框修饰
        this.setUndecorated(true);
        // 设置窗口可以显示
        this.setVisible(true);
        // 关闭时退出 jvm
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
