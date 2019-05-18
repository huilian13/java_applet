package kevin.util;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * 音乐类
 * @author kevin
 */
public class MusicUtil {
    /**
     * 播放MP3格式的方法
     * @param filePath 音乐文件路径
     */
    public static final void audioMp3(String filePath){
        try {
            //创建字符输入流对象
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filePath));
            //创建播放对象
            Player player = new Player(buffer);
            //播放音乐
            player.play();
            //关闭流
            player.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
