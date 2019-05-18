package kevin.entity;

import kevin.dto.Constant;
import kevin.dto.GameDto;
import kevin.ui.GameImage;

import java.util.Random;

/**
 * 飞机生命值
 */
public class PlaneLife extends Flyer implements Runnable {
    /**
     * 数据源
     */
    private GameDto gameDto;

    /**
     * 方向
     */
    private int direct;

    /**
     *  是否存在
     */
    private boolean isLive;

    /**
     * 生命线程
     */
    private Thread lifeThread;

    public PlaneLife(int x, int y, GameDto gameDto) {
        super(x,y);
        //初始化数据源
        this.gameDto=gameDto;
        //初始化实例参数
        this.initAllParam();
        //奖励对象创建时启动线程
        this.lifeThread=new Thread(this);
        this.lifeThread.start();
    }

    /**
     * 初始化实例参数
     */
    private void initAllParam(){
        //初始化方向
        this.direct=0;
        //初始化存在标志
        this.isLive=true;
        //初始化宽度
        this.width= GameImage.LIFE.getWidth(null);
        //初始化高度
        this.height=GameImage.LIFE.getHeight(null);
    }

    @Override
    protected boolean move(int moveX, int moveY) {
        int newX=this.x+moveX;
        int newY=this.y+moveY;
        if(newX>=0&&newX+this.width<=Constant.WIDTH&&newY== Constant.HEIGHT){
            return false;
        }
        this.x+=moveX;
        this.y+=moveY;
        return true;
    }

    @Override
    public void run() {
        try {
            for(;;){
                //间隔50ms
                Thread.sleep(Constant.SLEEP);
                //物体移动
                if(!this.gameDto.isPause()){
                    switch (this.direct){
                        case 0:
                            for(int i=0;i<5;i++){
                                this.isLive=this.move(-3,0);
                            }
                            break;
                        case 1:
                            for(int i=0;i<5;i++){
                                this.isLive=this.move(3,0);
                            }
                            break;
                        default:
                            for(int i=0;i<5;i++){
                                this.isLive=this.move(0,2);
                            }
                            break;
                    }
                    //如果消失就退出线程
                    if(!this.isLive){
                        break;
                    }
                    //随机产生方向
                    this.direct=new Random().nextInt(4);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
