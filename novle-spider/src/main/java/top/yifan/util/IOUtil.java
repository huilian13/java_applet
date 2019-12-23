package top.yifan.util;

import java.io.*;

/**
 * IO 流工具
 *
 * @author star
 **/
public final class IOUtil {
    /**
     * 不允许实例化
     */
    private IOUtil() {
    }

    /**
     * 写出文件
     *
     * @param filename
     */
    public static void writeToFile(File filename, String content) {
        // 字符输出流
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));) {
            // 输出内容
            bw.write(content);
            // 刷新
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
