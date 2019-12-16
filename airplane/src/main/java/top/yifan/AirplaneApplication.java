package top.yifan;

import javazoom.jl.decoder.JavaLayerException;
import top.yifan.constant.GameConstant;
import top.yifan.control.GameControl;
import top.yifan.util.MusicUtil;

import java.io.IOException;

/**
 * 启动类
 *
 * @author kevin
 */
public class AirplaneApplication {

    public static void main(String[] arg) {
        new GameControl();
        new MainThread().start();
    }

    /**
     * 游戏主线程内部类
     */
    private static class MainThread extends Thread {

        @Override
        public void run() {
            try {
                for (; ; ) {
                    MusicUtil.audioMp3(GameConstant.MUSIC_PATH_MAP.get("fight"));
                }
            } catch (JavaLayerException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
