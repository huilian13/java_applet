package kevin.entity;

import kevin.config.GameConfig;

/**
 * 爆炸类
 * @author kevin
 */
public class Bomb {
    /**
     * x坐标
     */
    private int x;

    /**
     * y坐标
     */
    private int y;

    /**
     * 爆炸范围
     */
    private int bombRange;

    /**
     * 爆炸生命值
     */
    private int lifeValue;

    /**
     * 爆炸存在标志
     */
    private boolean isLive;

    public Bomb(int x, int y,int bombRange) {
        this.x = x;
        this.y = y;
        this.bombRange=bombRange;
        //初始化生命值
        this.lifeValue= GameConfig.getSystemConfig().getBomb_life();
        //默认子弹存在
        this.isLive=true;
    }

    /**
     * 生命值减少
     */
    public void lifeDown(){
        if(this.lifeValue>0){
            this.lifeValue--;
        }else {
            this.isLive=false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBombRange(){return bombRange;}

    public int getLifeValue() {
        return lifeValue;
    }

    public boolean isLive() {
        return isLive;
    }
}
