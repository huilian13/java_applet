package top.yifan.ui;

import top.yifan.constant.GameImage;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 方块提示层窗口
 *
 * @author star
 */
public class RemindLayer extends AbstractLayer {

    public RemindLayer(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    @Override
    public void paint(Graphics g) {
        // 绘制层窗口
        this.createWindow(g);
        // 获取提示方块的 id
        int remind = this.gameDto.getRemind();
        // 获取方块图片
        Image image = GameImage.NEXT_RECT[remind];
        // 游戏开始时绘制提示方块
        if (this.gameDto.isGameStart()) {
            // 居中绘制提示方块
            this.drawImageAtCenter(image, g);
        }
    }

    /**
     * 居中绘制方块
     *
     * @param image 图片对象
     * @param g     画笔对象
     */
    private void drawImageAtCenter(Image image, Graphics g) {
        // 图片 x 坐标
        int x = this.x + (this.width - image.getWidth(null) >> 1);
        // 图片 y 坐标
        int y = this.y + (this.height - image.getHeight(null) >> 1);
        // 绘制图片
        g.drawImage(image, x, y, null);
    }

}
