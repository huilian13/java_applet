package top.yifan.ui;

import top.yifan.constant.GameImage;

import java.awt.Graphics;

/**
 * 数据库窗口层
 *
 * @author star
 */
public class DataBaseLayer extends AbstractDataLayer {

    public DataBaseLayer(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    @Override
    public void paint(Graphics g) {
        // 绘制层窗口
        this.createWindow(g);
        // 绘制数据值槽
        this.showData(GameImage.DATA, this.gameDto.getDbRecode(), g);

    }


}
