package top.yifan.thread.runnable;


import javazoom.jl.decoder.JavaLayerException;
import top.yifan.util.MusicUtil;

import java.io.IOException;

/**
 * 音效运行器
 *
 * @author star
 */
public class MusicRunnable implements Runnable {
    /**
     * 文件路径
     */
    private String path;

    public MusicRunnable(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            // 播放音乐
            MusicUtil.audioMp3(path);
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }
}
