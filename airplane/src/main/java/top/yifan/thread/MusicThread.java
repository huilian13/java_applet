package top.yifan.thread;


import javazoom.jl.decoder.JavaLayerException;
import top.yifan.util.MusicUtil;

import java.io.IOException;

/**
 * 音效线程
 *
 * @author star
 */
public class MusicThread extends Thread {
    /**
     * 文件路径
     */
    private String path;

    public MusicThread(String path) {
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
