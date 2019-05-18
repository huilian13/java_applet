package kevin.main;

import kevin.control.GameControl;
import kevin.dto.Constant;
import kevin.gameThread.MusicThread;
import kevin.ui.GameFrame;
import kevin.util.MusicUtil;

/**
 * 主函数类
 * @author kevin
 */
public class AirplaneGame {
    public static void main(String[] arg){
        new GameControl();
        new MainThread().start();
    }

    /**
     * 游戏主线程内部类
     */
    private static class MainThread extends Thread{
        @Override
        public void run() {
            for(;;){
                MusicUtil.audioMp3(Constant.PATH_MAP.get("fight"));
            }
        }
    }
}
