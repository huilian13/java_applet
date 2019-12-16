package top.yifan.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * 音乐工具类
 *
 * @author star
 */
public final class MusicUtil {

    private MusicUtil() {

    }

    /**
     * 播放 MP3 格式的方法
     *
     * @param filePath 音乐文件路径
     */
    public static final void audioMp3(String filePath) throws JavaLayerException, IOException {
        BufferedInputStream buffer = null;
        Player player = null;
        try {
            // 创建字符输入流对象
            buffer = new BufferedInputStream(FileLoadUtil.loadAsStream(filePath));
            // 创建播放对象
            player = new Player(buffer);
            // 播放音乐
            player.play();
        } finally {
            // 关闭流
            player.close();
            try {
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
