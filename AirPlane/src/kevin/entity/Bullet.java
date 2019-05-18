package kevin.entity;

import kevin.dto.Constant;
import kevin.dto.GameDto;
import kevin.gameThread.MusicThread;
import kevin.util.CrashUtil;

/**
 * 子弹类
 * @author kevin
 */
public class Bullet extends Flyer implements Runnable{
    /**
     * 游戏数据源
     */
    private GameDto gameDto;

    /**
     * 子弹是否存在
     */
    private boolean isLive;

    /**
     * 子弹线程
     */
    private Thread bulletThread;

    /**
     * 音乐线程
     */
    private MusicThread musicThread;

    /**
     * 子弹的速度
     */
    private static final int SPEED=8;

    public Bullet(int x, int y, GameDto gameDto) {
        super(x, y);
        //初始化数据源
        this.gameDto=gameDto;
        //默认存在
        this.isLive=true;
        //启动线程
        this.bulletThread=new Thread(this);
        bulletThread.start();
    }

    /**
     * 子弹移动
     */
    @Override
    protected boolean move(int moveX, int moveY){
        //移动后的y值
        int newY=this.y+moveY;
        //如果超出边界则停止移动
        if(newY<0){
            return false;
        }
        //改变坐标
        this.x+=moveX;
        this.y+=moveY;
        return true;
    }

    /**
     * 子弹击中单架飞机
     * @param plane 飞机对象
     */
    public boolean hitPlane(Plane plane){
        //判断是否发生碰撞
        if(CrashUtil.crash(plane,this.x,this.y,Constant.BULLET_WIDTH,Constant.BULLET_HEIGHT)){
            //子弹消失
            this.isLive=false;
            //生命值减1
            plane.lifeValue--;
            //产生击中音效
            musicThread=new MusicThread("gameMusic/enemy_down.mp3");
            musicThread.start();
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        try {
            for(;;){
                //间隔50ms
                 Thread.sleep(Constant.SLEEP);
                //当子弹碰到边界就消失
                if(!this.gameDto.isPause()&&!this.move(0,-SPEED)){
                    this.isLive=false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLive() {
        return isLive;
    }
}
