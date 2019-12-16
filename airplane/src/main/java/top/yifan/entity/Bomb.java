package top.yifan.entity;

import top.yifan.config.GameConfig;

/**
 * 爆炸类
 *
 * @author star
 */
public class Bomb {
    /**
     * x 坐标
     */
    private int x;

    /**
     * y 坐标
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

    public Bomb(int x, int y, int bombRange) {
        this.x = x;
        this.y = y;
        this.bombRange = bombRange;
        // 初始化生命值
        this.lifeValue = GameConfig.getSystemConfig().getBomb_life();
        // 默认爆炸存在
        this.isLive = true;
    }

    /**
     * 生命值减少
     */
    public void lifeDown() {
        if (this.lifeValue > 0) {
            this.lifeValue--;
            return;
        }
        // 生命不存在
        this.isLive = false;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBombRange() {
        return bombRange;
    }

    public int getLifeValue() {
        return lifeValue;
    }

    public boolean isLive() {
        return isLive;
    }
}
