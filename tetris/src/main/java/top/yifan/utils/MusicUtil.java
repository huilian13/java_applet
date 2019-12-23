package top.yifan.utils;

import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;

/**
 * 音乐播放工具类
 *
 * @author sz7v
 */
public final class MusicUtil {

    private MusicUtil() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * 播放 WAV 格式的方法
     */
    public static void audioWav(String filePath) {

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(FileLoadUtil.loadAsStream(filePath))) {
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[512];
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 播放 MP3 格式的方法
     */
    public static void audioMp3(String filePath) {
        Player player = null;
        try (BufferedInputStream buffer = new BufferedInputStream(FileLoadUtil.loadAsStream(filePath))) {
            player = new Player(buffer);
            player.play();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (player != null){
                player.close();
            }
        }
    }

}
