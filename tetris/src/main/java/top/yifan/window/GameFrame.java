package top.yifan.window;

import top.yifan.config.GameConfig;
import top.yifan.utils.FrameUtil;

import javax.swing.*;

/**
 * 游戏界面窗口
 *
 * @author star
 */
public class GameFrame extends JFrame {

    private static final long serialVersionUID = -4947331430432116725L;

    public GameFrame(GamePanel gamePanel) {
        // 设置标题
        this.setTitle(GameConfig.getFrameConfig().getTitle());
        // 设置窗口可见
        this.setVisible(true);
        // 设置默认面板
        this.setContentPane(gamePanel);
        // 设置窗口大小并居中
        FrameUtil.frameCenter(GameConfig.getFrameConfig().getWidth(), GameConfig.getFrameConfig().getHeight(), this);
        // 关闭窗口时退出 jvm
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
