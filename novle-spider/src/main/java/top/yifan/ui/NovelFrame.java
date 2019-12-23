package top.yifan.ui;

import top.yifan.util.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 小说主界面
 *
 * @author star
 **/
public class NovelFrame extends JFrame {

    public NovelFrame(NovelPanel novelPanel, NovelSearchPanel novelSearchPanel) {
        // 设置窗口标题
        this.setTitle("小说下载器");
        // 设置搜索面板
        this.add(novelSearchPanel, BorderLayout.NORTH);
        // 设置主面板
        this.add(novelPanel, BorderLayout.CENTER);
        // 设置窗口大小并居中  TODO 配置文件
        FrameUtil.frameCenter(this, 800, 510);
    }

}
