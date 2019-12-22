package top.yifan.ui;

import top.yifan.config.GameConfig;
import top.yifan.constant.GameImageConstant;
import top.yifan.control.GameControl;
import top.yifan.control.PlayerControl;
import top.yifan.dto.GameDto;
import top.yifan.entity.Coop;
import top.yifan.entity.SheepBox;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * 窗口面板
 *
 * @author star
 */
public class GamePanel extends JPanel {
    /**
     * 游戏数据源
     */
    private GameDto gameDto;

    /**
     * 图片尺寸
     */
    private final int SIZE=GameConfig.getFrameConfig().getSize();

    public GamePanel(GameDto gameDto,GameControl gameControl) {
        // 初始化游戏数据源
        this.gameDto=gameDto;
        // 注册键盘事件监听器
        this.addKeyListener(new PlayerControl(gameControl));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制游戏背景
        g.drawImage(GameImageConstant.BG_IMG,0,0,null);
        // 绘制人物(灰太狼)
        g.drawImage(this.gameDto.getWolfImage(),this.gameDto.getWolf().getX()*SIZE,this.gameDto.getWolf().getY()*SIZE,null);
        // 绘制箱子（羊）
        List<SheepBox> boxList = this.gameDto.getBoxList();
        for(SheepBox box:boxList){
            g.drawImage(GameImageConstant.BOX,box.getX()*SIZE,box.getY()*SIZE,null);
        }
        // 绘制障碍物（树）
        int[][] gameMap = this.gameDto.getGameMap();
        for(int i=0;i<gameMap.length;i++){
            for(int j=0;j<gameMap[i].length;j++){
                if(gameMap[i][j]==1){
                    g.drawImage(GameImageConstant.BLOCK,j*SIZE,i*SIZE,null);
                }
            }
        }
        // 绘制笼子
        List<Coop> coopList = this.gameDto.getCoopList();
        for(Coop coop:coopList){
            g.drawImage(GameImageConstant.TARGET,coop.getX()*SIZE,coop.getY()*SIZE,null);
        }
        // 添加关闭按钮
        this.addButton(new JButton(GameImageConstant.BTN_IMG));
        // 返回控制焦点
        this.requestFocus();
    }

    /**
     * 添加鼠标按钮
     */
    private final void addButton(JButton button){
        // 设置鼠标背景颜色
        button.setBackground(Color.white);
        // 添加鼠标事件
        button.addMouseListener(new ButtonAdapter());
        // 设置按钮位置
        button.setBounds(750,0,60,20);
        // 将按钮添加到面板
        this.add(button);

    }
}
