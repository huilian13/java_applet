package top.yifan.util;

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

    public static URL loadAsURL(String filePath) {
        URL resourceAsURL = Thread.currentThread()
                .getContextClassLoader()
                .getResource(filePath);

        return resourceAsURL;
    }

    public static InputStream loadAsStream(String filePath) {
        InputStream resourceAsStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filePath);

        return resourceAsStream;
    }


}
