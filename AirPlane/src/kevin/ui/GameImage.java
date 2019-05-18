package kevin.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏图片泪类
 * @author kevin
 */
public class GameImage {
    /**
     * 私有化构造函数
     */
    private GameImage() {}

    /**
     * 背景图
     */
    public static final Image BACKGROUND=new ImageIcon("graphics/background/gameMap.png").getImage();

    /**
     * 我方飞机图
     */
    public static final Image HERO=new ImageIcon("graphics/plane/hero/airplane.png").getImage();

    public static final Image HERO_BOM=new ImageIcon("graphics/plane/hero/airplaneBom.png").getImage();

    /**
     * 敌机图片
     */
    public static final Image ENEMY_SMALL=new ImageIcon("graphics/plane/enemy/enemy1.png").getImage();
    public static final Image ENEMY_BIG=new ImageIcon("graphics/plane/enemy/enemy2.png").getImage();
    public static final Image ENEMY_BOOS=new ImageIcon("graphics/plane/enemy/boos.png").getImage();

    /**
     * 子弹图片
     */
    public static final Image BULLET=new ImageIcon("graphics/plane/bullet.png").getImage();

    /**
     * 开始图片
     */
    public static final Image START=new ImageIcon("graphics/other/start.png").getImage();

    /**
     * 开始按钮图片
     */
    public static final ImageIcon START_MENU=new ImageIcon("graphics/other/start_menu.png");

    /**
     * 返回按钮图片
     */
    public static final ImageIcon BACK=new ImageIcon("graphics/other/back.png");

    /**
     * 结束图片
     */
    public static final Image GAMEOVER=new ImageIcon("graphics/other/gameOver.png").getImage();

    /**
     * 暂停图片
     */
    public static final Image PAUSE=new ImageIcon("graphics/other/pause.png").getImage();

    /**
     * 生命图片
     */
    public static final Image LIFE=new ImageIcon("graphics/plane/life.png").getImage();

    /**
     * 爆炸效果图
     */
    public static Image[] BOMBS=null;

    static {
        //静态初始化爆炸效果图数组
        BOMBS=new Image[3];
        for(int i=0;i<BOMBS.length;i++){
            BOMBS[i]=new ImageIcon("graphics/bomb/bomb_"+i+".gif").getImage();
        }
    }

}
