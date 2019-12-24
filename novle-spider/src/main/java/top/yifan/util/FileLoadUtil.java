package top.yifan.util;

import javax.swing.*;
import java.net.URL;

/**
 * 文件加载工具类
 *
 * @author star
 */
public final class FileLoadUtil {

    private FileLoadUtil() {

    }

    public static ImageIcon loadAsImageIcon(String filePath) {
        URL resource = Thread.currentThread()
                .getContextClassLoader()
                .getResource(filePath);

        return new ImageIcon(resource);
    }
}
