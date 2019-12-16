package top.yifan.entity.plane;

import top.yifan.constant.GameConstant;
import top.yifan.dto.GameDTO;
import top.yifan.entity.Bullet;
import top.yifan.entity.PlaneLife;
import top.yifan.factory.GameImageFactory;
import top.yifan.thread.MusicThread;
import top.yifan.constant.GameImagePathConstant;
import top.yifan.util.CrashUtil;

import java.util.ArrayList;

/**
 * 英雄飞机
 *
 * @author kevin
 */
public class HeroPlane extends Plane {
    /**
     * 数据源
     */
    private GameDTO gameDto;

    /**
     * 音效线程
     */
    private MusicThread musicThread;

    /**
     * 边界值大小
     */
    private static final int MIN_X = 0;

    private static final int MIN_Y = 0;

    private static final int MAX_X = GameConstant.WIDTH;

    private static final int MAX_Y = GameConstant.HEIGHT;

    /**
     * 闪烁值
     */
    private int sparkValue = GameConstant.SPARK_VALUE;

    /**
     * 飞机的宽度和高度
     */
    private static final int PLANE_HEIGHT = GameImageFactory.createImage(GameImagePathConstant.HERO).getHeight(null);

    private static final int PLANE_WIDTH = GameImageFactory.createImage(GameImagePathConstant.HERO).getWidth(null);

    public HeroPlane(int x, int y, GameDTO gameDto) {
        super(x, y);
        // 初始化数据源
        this.gameDto = gameDto;
        // 初始化参数
        this.initHeroParam();

    }

    /**
     * 初始化飞机参数
     */
    private void initHeroParam() {
        // 在创建飞机对象时初始化子弹
        this.bullets = new ArrayList<>();
        // 默认飞机存在
        this.isLive = true;
        // 初始化飞机的高度
        this.width = PLANE_WIDTH;
        // 初始化飞机的宽度
        this.height = PLANE_HEIGHT;
        // 初始化生命值
        this.lifeValue = 3;
    }

    /**
     * 物体移动
     *
     * @param moveX x偏移量
     * @param moveY y偏移量
     * @return true表示可以移动
     */
    @Override
    public boolean move(int moveX, int moveY) {
        // 计算更新的坐标
        int newX = this.x + moveX;
        int newY = this.y + moveY;
        // 判断飞机是否超出范围
        if (this.isOverMap(newX, newY)) {
            // 不可移动
            return false;
        }
        this.x += moveX;
        this.y += moveY;
        return true;
    }

    /**
     * 开火（发射子弹）
     */
    public void fire() {
        // 计算子弹的坐标
        int x = this.x + (PLANE_WIDTH - GameConstant.BULLET_WIDTH >> 1);
        int y = this.y - GameConstant.BULLET_HEIGHT;
        // 创建子弹
        Bullet bullet = new Bullet(x, y, this.gameDto);
        // 添加子弹
        this.bullets.add(bullet);
    }

    /**
     * 碰撞到敌方飞机
     *
     * @return
     */
    public boolean hitEnemyPlane(EnemyPlane enemyPlane) {
        // 判断是否发生碰撞
        if (CrashUtil.crash(enemyPlane, this.x, this.y, PLANE_WIDTH, PLANE_HEIGHT)) {
            // 飞机消失
            enemyPlane.setLive(false);
            // 产生击中音效
            this.musicThread = new MusicThread(GameConstant.MUSIC_PATH_MAP.get("bullet"));
            this.musicThread.start();
            return true;
        }
        return false;
    }

    /**
     * 碰撞到生命图片
     *
     * @return
     */
    public boolean hitPlaneLife(PlaneLife planeLife) {
        // 检测是否碰撞
        if (CrashUtil.crash(planeLife, this.x, this.y, PLANE_WIDTH, PLANE_HEIGHT)) {
            // 产生音效
            this.musicThread = new MusicThread(GameConstant.MUSIC_PATH_MAP.get("lifeUp"));
            this.musicThread.start();
            return true;
        }
        return false;
    }

    /**
     * 边界判断
     */
    private boolean isOverMap(int newX, int newY) {
        return newX < MIN_X || newX + PLANE_WIDTH > MAX_X || newY < MIN_Y || newY + PLANE_HEIGHT > MAX_Y;
    }

    /**
     * 闪烁值减 1
     */
    public void sparkDown() {
        this.sparkValue--;
    }

    /**
     * 生命值减 1
     */
    public void lifeValueDown() {
        this.lifeValue--;
    }

    /**
     * 生命值加 1
     */
    public void lifeValueUp() {
        this.lifeValue++;
    }

    public int getSparkValue() {
        return sparkValue;
    }

    public void setSparkValue(int sparkValue) {
        this.sparkValue = sparkValue;
    }
}
