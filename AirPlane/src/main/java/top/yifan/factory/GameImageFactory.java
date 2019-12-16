package top.yifan.factory;

import top.yifan.util.FileLoadUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 游戏图片工厂
 *
 * @author star
 */
public final class GameImageFactory {

    private GameImageFactory() {
    }

    public static Image createImage(String filePath) {
        return createImageIcon(filePath).getImage();
    }

    public static ImageIcon createImageIcon(String filePath) {
        assert filePath != null : "filePath can not be empty";

        URL url = FileLoadUtil.loadAsURL(filePath);

        return new ImageIcon(url);
    }

}
