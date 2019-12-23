package top.yifan.ui;

import top.yifan.constant.GameImage;

import java.awt.*;

/**
 * 游戏等级层窗口
 *
 * @author star
 */
public class LevelLayer extends AbstractLayer {

    /**
     * 数字的x坐标
     */
    private final int NUM_X;

    /**
     * 数字的y坐标
     */
    private final int NUM_Y;

    public LevelLayer(int x, int y, int width, int height) {
        super(x, y, width, height);
        // 初始化数字的 x 坐标
        NUM_X = 64;
        // 初始化数字的 y 坐标
        NUM_Y = GameImage.RANK.getHeight(null) + (PADDING << 1);


    }

    @Override
    public void paint(Graphics g) {
        // 绘制层窗口
        this.createWindow(g);
        // 绘制字体图片
        g.drawImage(GameImage.RANK, this.x + PADDING, this.y + PADDING, null);
        // 绘制数字
        this.drawNumberLeftPad(NUM_X, NUM_Y, this.gameDto.getRank(), 3, g);
    }

}
