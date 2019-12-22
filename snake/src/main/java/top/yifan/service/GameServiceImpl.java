package top.yifan.service;

import top.yifan.constant.Direction;
import top.yifan.constant.FileURLConstant;
import top.yifan.dto.GameDto;
import top.yifan.entity.Snake;
import top.yifan.factory.GameFactory;
import top.yifan.util.FileLoadUtil;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;


/**
 * 游戏业务逻辑实现类
 *
 * @author kevin
 */
public class GameServiceImpl implements GameService {
    /**
     * 游戏数据源
     */
    private GameDto gameDto;

    /**
     * 移动方向键值对
     */
    private HashMap<Integer, Method> directionMap;

    /**
     * 蛇节点
     */
    private LinkedList<Point> snakeList;


    public GameServiceImpl(GameDto gameDto) {
        //初始化数据源
        this.gameDto = gameDto;
        //初始化键值对
        this.InitDirectionMap();

    }

    /**
     * 初始化方向键值对
     */
    @SuppressWarnings("unchecked")
    private void InitDirectionMap() {
        //创建对象(将方向映射到方法上)
        this.directionMap = new HashMap<Integer, Method>();
        //声明对象流
        ObjectInputStream objectIn = null;
        try {
            //创建输入流对象
            objectIn = new ObjectInputStream(FileLoadUtil.loadAsStream(FileURLConstant.DIRECTION_PATH));
            //读取文件
            HashMap<Integer, String> map = (HashMap<Integer, String>) objectIn.readObject();
            System.out.println(map.toString());
            //将map集合转换为set集合
            Set<Entry<Integer, String>> entrySet = map.entrySet();
            for (Entry<Integer, String> entry : entrySet) {
                //将键值添加到方向集合中
                this.directionMap.put(entry.getKey(), this.getClass().getMethod(entry.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyUp() {
        // 暂停时，方向控制无效
        if (this.gameDto.isPause()) {
            return;
        }
        // 移动
        boolean canMove = this.gameDto.getSnake().snakeMove(0, -1, Direction.UP.getDirection());
        // 获取蛇节点
        this.snakeList = this.gameDto.getSnake().getSnakeList();
        // 如果吃到食物，就生成新的食物
        if (this.isEatFood(snakeList)) {
            // 吃到食物加5分
            this.gameDto.setScore(this.gameDto.getScore() + 5);
            // 生成食物
            this.gameDto.createFood();
            return;
        }
        // 如果蛇移动
        if (canMove) {
            // 没有吃到食物就去除末尾节点
            snakeList.removeLast();
        }
        // 检查游戏状态
        this.checkGameOver(snakeList);
    }

    @Override
    public void keyDown() {
        // 暂停时无法控制
        if (this.gameDto.isPause()) {
            return;
        }
        // 移动
        boolean canMove = this.gameDto.getSnake().snakeMove(0, 1, Direction.DOWN.getDirection());
        // 获取蛇节点
        this.snakeList = this.gameDto.getSnake().getSnakeList();
        // 如果吃到食物，就生成新的食物
        if (this.isEatFood(snakeList)) {
            // 吃到食物加 5 分
            this.gameDto.setScore(this.gameDto.getScore() + 5);
            // 生成食物
            this.gameDto.createFood();
            return;
        }
        // 如果蛇移动
        if (canMove) {
            // 没有吃到食物就去除末尾节点
            snakeList.removeLast();
        }
        // 检查游戏状态
        this.checkGameOver(snakeList);
    }

    @Override
    public void keyLeft() {
        // 暂停时无法控制
        if (this.gameDto.isPause()) {
            return;
        }
        // 移动
        boolean canMove = this.gameDto.getSnake().snakeMove(-1, 0, Direction.LEFT.getDirection());
        // 获取蛇节点
        this.snakeList = this.gameDto.getSnake().getSnakeList();
        // 如果吃到食物，就生成新的食物
        if (this.isEatFood(snakeList)) {
            // 吃到食物加5分
            this.gameDto.setScore(this.gameDto.getScore() + 5);
            // 生成食物
            this.gameDto.createFood();
            return;
        }
        // 如果蛇移动
        if (canMove) {
            // 没有吃到食物就去除末尾节点
            snakeList.removeLast();
        }
        // 检查游戏状态
        this.checkGameOver(snakeList);
    }

    @Override
    public void keyRight() {
        // 暂停时无法控制
        if (this.gameDto.isPause()) {
            return;
        }
        // 移动
        boolean canMove = this.gameDto.getSnake().snakeMove(1, 0, Direction.RIGHT.getDirection());
        // 获取蛇节点
        this.snakeList = this.gameDto.getSnake().getSnakeList();
        // 如果吃到食物，就生成新的食物
        if (this.isEatFood(snakeList)) {
            // 吃到食物加 5 分
            this.gameDto.setScore(this.gameDto.getScore() + 5);
            // 生成食物
            this.gameDto.createFood();
            return;
        }
        // 如果蛇移动
        if (canMove) {
            // 没有吃到食物就去除末尾节点
            snakeList.removeLast();
        }
        // 检查游戏状态
        this.checkGameOver(snakeList);

    }

    @Override
    public void keyFun() {
        // 暂停
        this.gameDto.changePause();
    }

    @Override
    public void startGame() {
        // 将游戏设置为开始
        this.gameDto.setStart(true);
        // 创建蛇对象
        this.gameDto.setSnake(new Snake());
        // 初始化所有的数据源
        this.gameDto.initEntityParam();

    }

    @Override
    public void directionMove() {
        try {
            // 获取方向值
            int currentDirection = this.gameDto.getSnake().getCurrentDirection();
            // 根据方向值移动
            if (this.directionMap.containsKey(currentDirection)) {
                // 执行方法
                this.directionMap.get(currentDirection).invoke(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 吃掉食物
     */
    private boolean isEatFood(LinkedList<Point> snakeList) {
        // 获取蛇头
        Point snakeHead = snakeList.getFirst();
        // 获取食物坐标
        Point foodPoint = this.gameDto.getFood().getfoodPoint();
        // 如果蛇头与食物重合，就代表吃到食物
        if (foodPoint.equals(snakeHead)) {
            return true;
        }
        // 没有吃到食物
        return false;
    }

    /**
     * 检查游戏
     */
    private void checkGameOver(LinkedList<Point> snakeList) {
        // 检查游戏是否失败
        if (this.isGameOver(snakeList)) {
            // 游戏失败
            this.gameDto.setStart(false);
        }
    }

    /**
     * 判断游戏是否失败
     *
     * @return
     */
    private boolean isGameOver(LinkedList<Point> snakeList) {
        // 获取头节点
        Point snakeHead = snakeList.getFirst();
        // 获取游戏地图信息
        boolean[][] gameMap = this.gameDto.getGameMap();
        if (gameMap[snakeHead.x][snakeHead.y]) {
            // 游戏结束
            return true;
        }

        for (int i = 1, length = snakeList.size(); i < length; i++) {
            if (snakeHead.equals(snakeList.get(i))) {
                return true;
            }
        }
        // 游戏进行
        return false;
    }

}
