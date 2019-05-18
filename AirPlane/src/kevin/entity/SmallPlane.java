package kevin.entity;

import kevin.dto.Constant;
import kevin.dto.GameDto;
import kevin.ui.GameImage;

/**
 * 小敌机
 */
public class SmallPlane extends Enemy implements Runnable{
    /**
     * 游戏数据源
     */
    private GameDto gameDto;

    /**
     * 飞机线程
     */
    private Thread planeThread;

    /**
     * 飞机的宽度和高度
     */
    private static final int PLANE_HEIGHT=GameImage.ENEMY_SMALL.getHeight(null);

    private static final int PLANE_WIDTH=GameImage.ENEMY_SMALL.getWidth(null);

    public SmallPlane(GameDto gameDto) {
        //随机创建飞机对象
        super((int)(Math.random()*(Constant.WIDTH-PLANE_WIDTH)),-PLANE_HEIGHT);
        //初始化数据源
        this.gameDto=gameDto;
        //初始化所有参数
        this.initParam();
        //当对象创建时启动线程
        this.planeThread=new Thread(this);
        this.planeThread.start();

    }

    /**
     * 初始化所有参数
     */
    private void initParam(){
        //默认飞机存在
        this.isLive=true;
        //初始化生命值
        this.lifeValue=1;
        //初始化分值
        this.scoreValue=5;
        //初始化飞机图片
        this.planeImg= GameImage.ENEMY_SMALL;
        //初始化宽度
        this.width=PLANE_WIDTH;
        //初始化高度
        this.height=PLANE_HEIGHT;

    }

    @Override
    public void run() {
        try {
            for(;;){
                //间隔50ms
                Thread.sleep(Constant.SLEEP);
                    //物体移动
                    if(!this.gameDto.isPause()&&!this.move(0,3)){
                        //当移出窗口时，物体消失
                        this.isLive=false;
                        break;
                    }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
