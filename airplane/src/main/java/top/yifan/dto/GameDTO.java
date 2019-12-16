package top.yifan.dto;

import top.yifan.config.GameConfig;
import top.yifan.constant.GameConstant;
import top.yifan.entity.*;
import top.yifan.entity.plane.*;
import top.yifan.constant.GameImagePathConstant;
import top.yifan.factory.GameImageFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 游戏数据载体
 *
 * @author star
 */
public class GameDTO implements Runnable {

    /**
     * 我方飞机
     */
    private HeroPlane heroPlane;

    /**
     * 敌方飞机
     */
    private List<EnemyPlane> enemyPlaneList;

    /**
     * 爆炸
     */
    private List<Bomb> bombList;

    /**
     * 生命奖励
     */
    private List<PlaneLife> lifeList;

    /**
     * 暂停标志
     */
    private boolean pause;

    /**
     * 开始标志
     */
    private boolean start;

    /**
     * 我方飞机图片
     */
    private Image planeImg;

    /**
     * 速度
     */
    private int heroSpeed;

    /**
     * 下一次时间毫秒数
     */
    private long nextTime;

    /**
     * 得分
     */
    private int score;

    /**
     * 击中的敌机数量
     */
    private int shotEnemyNum;

    /**
     * 击中标准数
     */
    private static final int NORMAL_NUM = GameConfig.getSystemConfig().getNormalNum();

    public GameDTO() {
        // 初始化参数
        this.initAllParam();
        // 启动线程
        Thread data = new Thread(this);
        data.start();
    }

    /**
     * 初始化所有的数据
     */
    public void initAllParam() {
        // 初始化爆炸集合
        this.bombList = new ArrayList<>();
        // 初始化飞机集合
        this.enemyPlaneList = new ArrayList<>();
        // 初始化奖励集合
        this.lifeList = new ArrayList<>();
        // 初始化我方飞机
        this.initHeroPlaneInfo();
        // 初始化飞机速度
        this.heroSpeed = GameConfig.getSystemConfig().getSpeed();
        // 默认不暂停
        this.pause = false;
        // 默认游戏未开始
        this.start = false;
        // 初始化时间
        this.nextTime = 0;
    }

    /**
     * 初始化飞机
     */
    public void initHeroPlaneInfo() {
        // 获取背景图
        Image bgImage = GameImageFactory.createImage(GameImagePathConstant.BACKGROUND);
        // 初始化飞机图片
        this.planeImg = GameImageFactory.createImage(GameImagePathConstant.HERO);
        // 获取坐标
        int x = bgImage.getWidth(null) - this.planeImg.getWidth(null) >> 1;
        int y = bgImage.getHeight(null) - this.planeImg.getHeight(null);
        // 初始化飞机
        this.heroPlane = new HeroPlane(x, y, this);
        // 飞机得分
        this.score = 0;
        // 击中敌机数量
        this.shotEnemyNum = 0;
    }

    /**
     * 随机产生飞机
     *
     * @return
     */
    private EnemyPlane randomCreatePlane() {
        // 通过产生随机数，创建不同的 plane 对象
        int select = (int) (Math.random() * 10);
        switch (select) {
            case 0:
            case 1:
            case 2:
                return new BigEnemyPlane(this);
            case 3:
                // 每次击中 10 个大飞机，就创建一个 BoosEnemyPlane 对象
                if (this.shotEnemyNum >= NORMAL_NUM) {
                    // 置零
                    this.shotEnemyNum = 0;
                    // 创建终极飞机
                    return new BoosEnemyPlane(this);
                }
            default:
                return new SmallEnemyPlane(this);
        }
    }

    /**
     * 在集合中添加新的飞机（每隔 1s 添加一个）
     */
    private void addNewPlane() {
        // 获取当前时间毫秒数
        long nowTime = System.currentTimeMillis();
        if (nowTime >= this.nextTime) {
            // 添加飞机
            this.enemyPlaneList.add(this.randomCreatePlane());
            // 间隔 1s
            nextTime += 1000;
        }
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                if (this.start && !this.pause) {
                    // 产生新的飞机
                    this.addNewPlane();
                }
                // 间隔 50ms
                Thread.sleep(GameConstant.SLEEP);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public HeroPlane getHeroPlane() {
        return heroPlane;
    }

    /**
     * 改变变量 pause 值
     */
    public void changePause() {
        this.pause = !this.pause;
    }

    public void setHeroPlane(HeroPlane heroPlane) {
        this.heroPlane = heroPlane;
    }

    public List<EnemyPlane> getEnemyPlaneList() {
        return enemyPlaneList;
    }

    public void setEnemyPlaneList(List<EnemyPlane> enemyPlaneList) {
        this.enemyPlaneList = enemyPlaneList;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void setBombList(List<Bomb> bombList) {
        this.bombList = bombList;
    }

    public Image getPlaneImg() {
        return planeImg;
    }

    public void setPlaneImg(Image planeImg) {
        this.planeImg = planeImg;
    }

    public boolean isPause() {
        return pause;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getHeroSpeed() {
        return heroSpeed;
    }

    public void setHeroSpeed(int heroSpeed) {
        this.heroSpeed = heroSpeed;
    }

    public long getNextTime() {
        return nextTime;
    }

    public void setNextTime(long nextTime) {
        this.nextTime = nextTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getShotEnemyNum() {
        return shotEnemyNum;
    }

    public void shotEnemyNumUp() {
        this.shotEnemyNum++;
    }

    public List<PlaneLife> getLifeList() {
        return lifeList;
    }

    public void setLifeList(ArrayList<PlaneLife> lifeList) {
        this.lifeList = lifeList;
    }
}
