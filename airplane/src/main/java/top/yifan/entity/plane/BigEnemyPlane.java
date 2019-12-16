package top.yifan.entity.plane;

import top.yifan.constant.GameConstant;
import top.yifan.dto.GameDTO;
import top.yifan.constant.GameImagePathConstant;
import top.yifan.factory.GameImageFactory;
import top.yifan.thread.actuator.ThreadActuator;
import top.yifan.thread.runnable.PlaneMoveRunnable;

/**
 * 大敌机
 *
 * @author star
 */
public class BigEnemyPlane extends EnemyPlane {
    /**
     * 数据源
     */
    private GameDTO gameDto;

    /**
     * 飞机的宽度和高度
     */
    private static final int PLANE_HEIGHT = GameImageFactory.createImage(GameImagePathConstant.ENEMY_BIG).getHeight(null);

    private static final int PLANE_WIDTH = GameImageFactory.createImage(GameImagePathConstant.ENEMY_BIG).getWidth(null);

    /**
     * 飞行速度
     */
    private static final int SPEED = 2;

    public BigEnemyPlane(GameDTO gameDto) {
        super((int) (Math.random() * (GameConstant.WIDTH - PLANE_WIDTH)), -PLANE_HEIGHT);
        // 初始化数据源
        this.gameDto = gameDto;
        // 初始化所有参数
        this.initParam();
        // 当对象创建时启动线程
        ThreadActuator.execute(new PlaneMoveRunnable(this.gameDto, this, SPEED));
    }

    /**
     * 初始化所有参数
     */
    private void initParam() {
        // 默认飞机存在
        this.isLive = true;
        // 初始化话生命值
        this.lifeValue = 3;
        // 初始化分值
        this.scoreValue = 10;
        // 初始化飞机图片
        this.planeImg = GameImageFactory.createImage(GameImagePathConstant.ENEMY_BIG);
        // 初始化宽度
        this.width = PLANE_WIDTH;
        // 初始化高度
        this.height = PLANE_HEIGHT;
    }
}
