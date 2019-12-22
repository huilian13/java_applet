package top.yifan.util;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;

/**
 * 文件加载工具
 *
 * @author star
 */
public final class FileLoadUtil {

    private FileLoadUtil() {

    }

    /**
     * 加载文件为 Image
     *
     * @param filePath 文件路径
     * @return Image 对象
     */
    public static Image loadAsImage(String filePath) {
        ImageIcon imageIcon = new ImageIcon(loadAsURL(filePath));

        return imageIcon.getImage();
    }

    /**
     * 加载文件为流的形式
     *
     * @param filePath 文件路径
     * @return 文件流
     */
    public static InputStream loadAsStream(String filePath) {
        InputStream stream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filePath);

        return stream;
    }

    public static URL loadAsURL(String filePath) {
        URL url = Thread.currentThread()
                .getContextClassLoader()
                .getResource(filePath);

        return url;
    }
}
