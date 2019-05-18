package kevin.entity;

import kevin.dto.Constant;
import kevin.dto.GameDto;
import kevin.ui.GameImage;

/**
 * 大敌机
 */
public class BigPlane extends Enemy implements Runnable{
    /**
     * 飞机线程
     */
    private Thread planeThread;

    /**
     * 数据源
     */
    private GameDto gameDto;

    /**
     * 飞机的宽度和高度
     */
    private static final int PLANE_HEIGHT=GameImage.ENEMY_BIG.getHeight(null);

    private static final int PLANE_WIDTH=GameImage.ENEMY_BIG.getWidth(null);

    public BigPlane(GameDto gameDto) {
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
        //初始化话生命值
        this.lifeValue=3;
        //初始化分值
        this.scoreValue=10;
        //初始化飞机图片
        this.planeImg= GameImage.ENEMY_BIG;
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
                if(!this.gameDto.isPause()&&!this.move(0,2)){
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
