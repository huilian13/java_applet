package top.yifan.window;

import top.yifan.control.GameControl;
import top.yifan.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 游戏得分窗口
 *
 * @author star
 */
public class GamePointsSaveFrame extends JFrame {

    private static final long serialVersionUID = 2112551780764995649L;

    /**
     * 游戏控制器
     */
    private GameControl gameControl;

    /**
     * 本地按钮
     */
    private JButton diskButton;

    /**
     * 数据库按钮
     */
    private JButton dataButton;

    /**
     * 分数标签
     */
    private JLabel point;

    /**
     * 姓名输入框
     */
    private JTextField textName;

    /**
     * 错误信息提示
     */
    private JLabel errorMsg;

    public GamePointsSaveFrame(GameControl gameControl) {
        // 初始化游戏控制器
        this.gameControl = gameControl;
        // 设置窗口标题
        this.setTitle("保存记录");
        // 设置大小并居中
        FrameUtil.frameCenter(300, 150, this);
        // 创建面板内容
        this.createCom();
        // 添加事件监听
        this.addActionEvent();

    }

    /**
     * 显示窗口
     *
     * @param score 玩家分数
     */
    public void showWindow(int score) {
        // 显示分数
        this.point.setText("您的得分：" + score);
        // 显示窗口
        this.setVisible(true);
    }

    /**
     * 添加事件监听
     */
    private void addActionEvent() {
        // 添加按钮事件
        this.diskButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取玩家姓名
                String name = textName.getText();
                if (name.length() > 10) {
                    errorMsg.setText("请输入10位以下的名字");
                } else if (name == null || "".equals(name)) {
                    errorMsg.setText("名字不能为空");
                } else {
                    // 关闭窗口
                    setVisible(false);
                    // 保存分数
                    gameControl.savePointToDisk(name);
                }
            }
        });

        this.dataButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取玩家姓名
                String name = textName.getText();
                if (name.length() > 10) {
                    errorMsg.setText("请输入10位以下的名字");
                } else if (name == null || "".equals(name)) {
                    errorMsg.setText("名字不能为空");
                } else {
                    // 关闭窗口
                    setVisible(false);
                    // 保存分数
                    gameControl.savePointToDB(name);
                }
            }

        });

    }

    /**
     * 创建面板内容
     */
    private void createCom() {
        // 创建分数面板
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // 初始化分数标签
        this.point = new JLabel();
        // 初始化错误信息标签
        this.errorMsg = new JLabel();
        this.errorMsg.setForeground(Color.red);
        // 添加到面板
        scorePanel.add(this.point);
        scorePanel.add(this.errorMsg);
        // 将面板添加到窗口
        this.add(scorePanel, BorderLayout.NORTH);

        // 创建姓名面板
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // 添加姓名标签
        namePanel.add(new JLabel("您的姓名："));
        // 初始化姓名输入框
        this.textName = new JTextField(10);
        // 添加到面板
        namePanel.add(this.textName);
        // 添加到窗口
        this.add(namePanel, BorderLayout.CENTER);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        // 添加地点标签
        buttonPanel.add(new JLabel("保存到："), FlowLayout.LEFT);
        // 初始化按钮
        this.dataButton = new JButton("数据库");
        this.diskButton = new JButton("本地磁盘");
        // 将按钮添加到面板
        buttonPanel.add(dataButton, FlowLayout.CENTER);
        buttonPanel.add(diskButton, FlowLayout.CENTER);
        // 添加到窗口
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

}
