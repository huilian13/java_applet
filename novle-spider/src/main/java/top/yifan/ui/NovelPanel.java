package top.yifan.ui;

import top.yifan.control.NovelCrawlerControl;
import top.yifan.dto.NovelSpiderDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * 小说窗口主面板
 *
 * @author star
 **/
public class NovelPanel extends JPanel {

    /**
     * 表格组件
     */
    public JTable table;

    /**
     * 表格模式对象
     */
    public DefaultTableModel tableModel;

    /**
     * 数据传输对象
     */
    private NovelSpiderDto novelSpiderDto;

    /**
     * 爬取控制器
     */
    private NovelCrawlerControl novelCrawlerControl;

    public NovelPanel(NovelSpiderDto novelSpiderDto, NovelCrawlerControl novelCrawlerControl) {
        // 初始化数据源
        this.novelSpiderDto = novelSpiderDto;
        // 初始化控制器
        this.novelCrawlerControl = novelCrawlerControl;
        // 初始化所有组件
        this.initComponent();
        // 添加事件监听器
        this.table.addMouseListener(this.createMouseAdapter());
    }

    /**
     * 初始化所有组件
     */
    private void initComponent() {
        // 消除布局管理器
        this.setLayout(null);
        // 设置表格列名
        String[] columns = {"序号", "书名", "作者", "最新章节", "状态"};
        String[][] datas = new String[][]{};
        this.tableModel = new DefaultTableModel(datas, columns);
        // 创建表格
        this.table = new JTable(tableModel);
        table.setFont(new Font("宋体", Font.PLAIN, 13));
        // 创建滑动面板
        JScrollPane scrollPane = new JScrollPane();
        // 设置背景颜色
        scrollPane.setBackground(Color.white);
        // TODO 配置文件
        scrollPane.setBounds(25, 0, 750, 400);
        this.add(scrollPane);
        scrollPane.setViewportView(table);
    }

    /**
     * 创建鼠标适配器
     *
     * @return
     */
    private MouseAdapter createMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // 获取右键点击在表格中的行号
                    int rowPoint = table.rowAtPoint(e.getPoint());
                    if (rowPoint != -1) {
                        // 获取小说名
                        String novelName = (String) table.getValueAt(rowPoint, 1);
                        // 设置行选择坐标
                        table.setRowSelectionInterval(rowPoint, rowPoint);
                        // 创建右键菜单
                        JPopupMenu menu = new JPopupMenu();
                        JMenuItem downLoadItem = new JMenuItem("下载到桌面");
                        JMenuItem browserItem = new JMenuItem("在浏览器打开");
                        menu.add(downLoadItem);
                        menu.add(browserItem);
                        menu.show(table, e.getX(), e.getY());
                        // 添加事件监听器
                        downLoadItem.addActionListener((event) -> {
                            // 获取章节数
                            int chapterNumber = novelCrawlerControl.getChapterLength(novelName);
                            System.out.println("章节总数：" + chapterNumber);
                            // 下载线程
                            Thread downloadThread = null;
                            // 分段下载
                            for (int i = 0; i <= chapterNumber; i += 20) {
                                int leftRange = i;
                                int rightRange = i + 20;
                                downloadThread = new Thread(() -> {
                                    // 下载到桌面
                                    downloadToDesktop(novelName, leftRange, Math.min(rightRange, chapterNumber));

                                });
                                downloadThread.start();
                            }
                        });

                        browserItem.addActionListener(e1 -> {
                            // 以浏览器方式打开
                            openWithBrowser(novelName);
                        });
                    }
                }
            }
        };
    }

    /**
     * 以浏览器的方式打开
     */
    public void openWithBrowser(String novelName) {
        if (Desktop.isDesktopSupported()) {
            try {
                // 获取桌面对象
                Desktop desktop = Desktop.getDesktop();
                // 打开浏览器
                desktop.browse(new URL(novelCrawlerControl.getNovelByName(novelName).getNovelUrl()).toURI());
            } catch (Exception e2) {
                System.out.println("浏览器打开失败！");
            }
        }
    }

    /**
     * 下载到桌面
     */
    public void downloadToDesktop(String urlName, int leftRange, int rightRange) {
        this.novelCrawlerControl.downloadChapterContent(urlName, leftRange, rightRange);
    }
}
