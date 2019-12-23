package top.yifan.ui;

import top.yifan.constant.GameImage;

import java.awt.Graphics;

public class DataDiskLayer extends AbstractDataLayer {

    public DataDiskLayer(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    @Override
    public void paint(Graphics g) {
        // 绘制层窗口
        this.createWindow(g);
        // 绘制数据值槽
        this.showData(GameImage.DISK, this.gameDto.getDiskRecode(), g);
    }


}
