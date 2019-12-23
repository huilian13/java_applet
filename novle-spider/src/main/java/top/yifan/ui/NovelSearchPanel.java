package top.yifan.ui;

import org.jsoup.helper.StringUtil;
import top.yifan.control.NovelCrawlerControl;
import top.yifan.dto.NovelSpiderDto;
import top.yifan.util.FileLoadUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 小说搜索面板
 *
 * @author star
 **/
public class NovelSearchPanel extends JPanel {
    /**
     * 文本对象
     */
    private JTextField textField;

    /**
     * 按钮
     */
    private JButton searchButton;

    /**
     * 数据传输对象
     */
    private NovelSpiderDto novelSpiderDto;

    /**
     * 爬取控制器
     */
    private NovelCrawlerControl novelCrawlerControl;

    public NovelSearchPanel(NovelSpiderDto novelSpiderDto, NovelCrawlerControl novelCrawlerControl) {
        this.novelSpiderDto = novelSpiderDto;
        this.novelCrawlerControl = novelCrawlerControl;
        // 初始化组件
        this.initComponent();
        // 给按钮添加事件监听器
        this.searchButton.addMouseListener(this.createMouseAdapter());
    }

    /**
     * 初始化所有组件
     */
    private void initComponent() {
        // 创建标签组件
        JLabel textLabel = new JLabel(FileLoadUtil.loadAsImageIcon("images/string.png"));
        // 创建文本组件
        this.textField = new JTextField(32);
        // 设置输入的字体格式
        this.textField.setFont(new Font("宋体", Font.PLAIN, 20));
        // 创建按钮
        this.searchButton = new JButton(FileLoadUtil.loadAsImageIcon("images/search.png"));
        this.add(textLabel);
        this.add(textField);
        this.add(searchButton);
    }

    /**
     * 创建鼠标适配器
     *
     * @return
     */
    private MouseAdapter createMouseAdapter() {
        // 创建鼠标适配器
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // 获取输入的内容
                    String novelName = textField.getText();
                    if (!StringUtil.isBlank(novelName)) {
                        novelCrawlerControl.searchNovel(novelName);
                        novelCrawlerControl.showTableRow();
                    }
                }
            }
        };
        return mouseAdapter;
    }
}
