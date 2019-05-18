package kevin.util;

import kevin.entity.Flyer;
import kevin.gameThread.MusicThread;

/**
 * 碰撞算法
 */
public class CrashUtil {
    /**
     * 碰撞方法
     * @param flyer 飞行物对象
     * @param x x坐标
     * @param y y坐标
     * @param width 碰撞物的宽度
     * @param height 碰撞物的高度
     * @return true代表发生碰撞
     */
    public static boolean crash(Flyer flyer,int x, int y, int width,int height){
        int minX=flyer.getX()-width;
        int maxX=flyer.getX()+flyer.getWidth();
        int minY=flyer.getY()-height;
        int maxY=flyer.getY()+flyer.getHeight();
        //判断是否发生碰撞
        if(x>minX&&x<maxX&&y>minY&&y<maxY){
            return true;
        }
        return false;
    }
}
