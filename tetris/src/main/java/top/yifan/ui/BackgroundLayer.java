package top.yifan.ui;

import top.yifan.config.GameConfig;
import top.yifan.constant.GameImage;

import java.awt.*;
import java.util.List;

/**
 * 背景层窗口
 *
 * @author star
 */
public class BackgroundLayer extends AbstractLayer {

    public BackgroundLayer(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    @Override
    public void paint(Graphics g) {
        // 背景图片集合
        List<Image> bgImages = GameImage.BG_IMG;
        // 取模，循环更换背景
        int index = this.gameDto.getRank() % bgImages.size();
        // 绘制背景图
        g.drawImage(bgImages.get(index), 0, 0, GameConfig.getFrameConfig().getWidth(), GameConfig.getFrameConfig().getHeight(), null);
    }

}

