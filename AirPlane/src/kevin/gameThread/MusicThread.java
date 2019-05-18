package kevin.gameThread;

import kevin.util.MusicUtil;

/**
 * 音效线程类
 * @author kevin
 */
public class MusicThread extends Thread{
    /**
     * 文件路径
     */
    private String path;

    public MusicThread(String path){
        this.path=path;
    }

    @Override
    public void run() {
        //播放音乐
        MusicUtil.audioMp3(path);
    }
}
