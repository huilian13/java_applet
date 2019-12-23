package top.yifan.utils;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;

/**
 * 文件加载工具类
 *
 * @author star
 */
public final class FileLoadUtil {

    private FileLoadUtil() {
        throw new IllegalStateException("Cannot create instance of static util class");
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

    /**
     * 加载文件为 URL 对象
     *
     * @param filePath 文件路径
     * @return 文件 URL
     */
    public static URL loadAsURL(String filePath) {
        URL url = Thread.currentThread()
                .getContextClassLoader()
                .getResource(filePath);

        return url;
    }

    /**
     * 加载文件为 ImageIcon 对象
     *
     * @param filePath 文件路径
     * @return ImageIcon 对象
     */
    public static ImageIcon loadAsImageIcon(String filePath) {
        ImageIcon imageIcon = new ImageIcon(loadAsURL(filePath));

        return imageIcon;
    }

    /**
     * 加载文件为 Image 对象
     *
     * @param filePath 文件路径
     * @return Image 对象
     */
    public static Image loadAsImage(String filePath) {
        return loadAsImageIcon(filePath).getImage();
    }
}
