package top.yifan.util;

import javax.swing.*;
import java.io.InputStream;
import java.net.URL;

/**
 * 文件加载工具类
 *
 * @author star
 */
public final class FileLoadUtil {

    private FileLoadUtil() {

    }

    public static InputStream loadAsStream(String filePath) {
         return Thread.currentThread()
                 .getContextClassLoader()
                 .getResourceAsStream(filePath);
    }

    public static ImageIcon loadAsImageIcon(String filePath) {

        return new ImageIcon(loadAsURL(filePath));
    }

    public static URL loadAsURL(String filePath) {
        URL url = Thread.currentThread()
                .getContextClassLoader()
                .getResource(filePath);

        return url;
    }
}
