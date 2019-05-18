package kevin.dto;

import kevin.config.GameConfig;
import kevin.entity.*;
import kevin.ui.GameImage;

import java.awt.*;
import java.util.ArrayList;

/**
 * 游戏数据载体
 * @author kevin
 */
public class GameDto implements Runnable{

    /**
     * 我方飞机
     */
    private Hero hero;

    /**
     * 敌方飞机
     */
    private ArrayList<Enemy> enemyList;

    /**
     * 爆炸
     */
    private ArrayList<Bomb> bombList;

    /**
     * 生命奖励
     */
    private ArrayList<PlaneLife> lifeList;

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
    private int speed= GameConfig.getSystemConfig().getSpeed();

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
    private static final int NORMAL_NUM=GameConfig.getSystemConfig().getNormalNum();

    public GameDto() {
        //初始化参数
        this.initAllParam();
        //启动线程
        Thread data=new Thread(this);
        data.start();
    }

    /**
     * 初始化所有的数据
     */
    public void initAllParam(){
        //初始化爆炸集合
        this.bombList=new ArrayList<Bomb>();
        //初始化飞机集合
        this.enemyList=new ArrayList<Enemy>();
        //初始化奖励集合
        this.lifeList=new ArrayList<PlaneLife>();
        //初始化我方飞机
        this.initHeroPlaneInfo();
        //默认不暂停
        this.pause=false;
        //默认游戏未开始
        this.start=false;
        //初始化时间
        this.nextTime=0;
    }

    /**
     * 初始化飞机
     */
    public void initHeroPlaneInfo(){
        //获取背景图
        Image bgImage=GameImage.BACKGROUND;
        //初始化飞机图片
        this.planeImg=GameImage.HERO;
        //获取坐标
        int x= bgImage.getWidth(null)-this.planeImg.getWidth(null)>>1;
        int y=bgImage.getHeight(null)-this.planeImg.getHeight(null);
        //初始化飞机
        this.hero=new Hero(x,y,this);
        //飞机得分
        this.score=0;
        //击中敌机数量
        this.shotEnemyNum=0;
    }

    /**
     * 随机产生飞机
     * @return
     */
    private Enemy randomCreatePlane(){
        //产生随机数
        int select=(int)(Math.random()*10);
        switch (select){
            case 0:
            case 1:
            case 2:
                return new BigPlane(this);
            case 3:
                if(this.shotEnemyNum>=NORMAL_NUM){
                    //当击中10个大飞机，就置零
                    this.shotEnemyNum=0;
                    //创建终极飞机
                    return new BoosPlane(this);
                }
            default:
                return new SmallPlane(this);
        }
    }

    /**
     * 在集合中添加新的飞机（每隔1s添加一个）
     */
    private void addNewPlane(){
        //获取当前时间毫秒数
        long nowTime=System.currentTimeMillis();
        if(nowTime>=this.nextTime){
            //添加飞机
            this.enemyList.add(this.randomCreatePlane());
            //间隔1s
            nextTime+=1000;
        }
    }

    @Override
    public void run() {
        try {
            for(;;){
                if(this.start&&!this.pause){
                    //添加飞机
                    this.addNewPlane();
                }
                //间隔50ms
                Thread.sleep(Constant.SLEEP);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(ArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public ArrayList<Bomb> getBombList() {
        return bombList;
    }

    public void setBombList(ArrayList<Bomb> bombList) {
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

    /**
     * 改变变量pause值
     */
    public void changePause() {
        this.pause = !this.pause;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public ArrayList<PlaneLife> getLifeList() {
        return lifeList;
    }

    public void setLifeList(ArrayList<PlaneLife> lifeList) {
        this.lifeList = lifeList;
    }
}
