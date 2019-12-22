package top.yifan.dto;

import top.yifan.config.GameConfig;
import top.yifan.entity.Food;
import top.yifan.entity.Snake;

import java.awt.*;
import java.util.Random;

/**
 * 游戏数据源
 *
 * @author star
 */
public class GameDto {

    /**
     * 蛇对象
     */
    private Snake snake;

    /**
     * 食物
     */
    private Food food;

    /**
     * 游戏地图
     */
    private boolean[][] gameMap;

    /**
     * 移动速度
     */
    private long speed;

    /**
     * 游戏开始
     */
    private boolean isStart;

    /**
     * 暂停
     */
    private boolean isPause;

    /**
     * 得分
     */
    private int score;

    /**
     * 地图的行数
     */
    public static final int MAP_ROW = GameConfig.getSystemConfig().getMaxX();

    /**
     * 地图的列数
     */
    public static final int MAP_COL = GameConfig.getSystemConfig().getMaxY();

    /**
     * 障碍物数
     */
    private static final int ROCK_NUM = GameConfig.getSystemConfig().getRockNum();

    /**
     * 随机数生成器
     */
    private Random random = new Random();

    public GameDto() {
        // 初始化实体数据
        this.initEntityParam();
    }

    /**
     * 初始化实体数据
     */
    public void initEntityParam() {
        // 初始化游戏地图
        this.initGameMap();
        // 创建食物
        this.createFood();
        // 默认未暂停
        this.isPause = false;
        // 初始化分数
        this.score = 0;
        // 默认移动速度为1秒间隔（简单模式）
        this.speed = 1000;
    }

    /**
     * 初始化游戏地图
     */
    private void initGameMap() {
        // 初始化游戏地图
        this.gameMap = new boolean[MAP_ROW][MAP_COL];
        // 设置地图边界
        for (int x = 0; x < MAP_ROW; x++) {
            for (int y = 0; y < MAP_COL; y++) {
                if (x == 0 || x == MAP_ROW - 1 || y == 0 || y == MAP_COL - 1) {
                    gameMap[x][y] = true;
                }
            }
        }

        // 当游戏开始时，才设置障碍物
        if (!this.isStart) {
            return;
        }
        // 设置障碍物
        int count = 0;
        while (count < ROCK_NUM) {
            int x = random.nextInt(MAP_ROW);
            int y = random.nextInt(MAP_COL);
            if (gameMap[x][y] || (x == 4 && y == 4)) {
                continue;
            }
            gameMap[x][y] = true;
            count++;
        }
    }

    /**
     * 创建食物
     */
    public void createFood() {
        // 声明坐标
        int x, y;
        while (true) {
            // 食物坐标
            x = random.nextInt(MAP_ROW);
            y = random.nextInt(MAP_COL);
            //判断食物是否生成在砖块上
            if (gameMap[x][y]) {
                continue;
            }
            // 创建食物
            this.food = new Food(new Point(x, y));
            break;
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isPause() {
        return isPause;
    }

    public void changePause() {
        this.isPause = !this.isPause;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(boolean[][] gameMap) {
        this.gameMap = gameMap;
    }

}
