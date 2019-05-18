package com.kevin.ui;

import com.kevin.util.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 2018-06-14 9:58
 * 小说主界面
 *
 * @author kevin
 **/
public class NovelFrame extends JFrame {

    public NovelFrame(NovelPanel novelPanel,NovelSearchPanel novelSearchPanel) {
        //设置窗口标题
        this.setTitle("小说下载器");
        //设置搜索面板
        this.add(novelSearchPanel, BorderLayout.NORTH);
        //设置主面板
        this.add(novelPanel, BorderLayout.CENTER);
        //设置窗口大小并居中  TODO 配置文件
        FrameUtil.framCenter(this, 800, 510);
    }

}
