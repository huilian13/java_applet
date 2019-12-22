package top.yifan.service;


import top.yifan.constant.FigureType;
import top.yifan.constant.GameImageConstant;
import top.yifan.dto.GameDto;
import top.yifan.entity.SheepBox;
import top.yifan.entity.Wolf;

import java.util.List;

/**
 * 游戏业务逻辑
 *
 * @author star
 */
public class GameServiceImpl implements GameService {
    /**
     * 游戏数据源
     */
    private GameDto gameDto;

    public GameServiceImpl(GameDto gameDto) {
        // 初始化游戏数据源
        this.gameDto = gameDto;
    }

    @Override
    public void keyUp() {
        // 设置人物背面图
        this.gameDto.setWolfImage(GameImageConstant.WOLF_A);
        // 获取人物新的位置
        Wolf wolf = this.gameDto.getWolf();
        // 获取所有的箱子
        List<SheepBox> boxList = this.gameDto.getBoxList();
        // 获取游戏地图
        int[][] gameMap = this.gameDto.getGameMap();
        for (SheepBox box : boxList) {
            if (box.getX() == wolf.getX() && box.getY() == wolf.getY() - 1) {
                // 判断箱子是否可以移动
                if (!box.moveBox(0, -1, gameMap)) {
                    return;
                }
                // 修改人物与箱子的标记，如果人物与箱子前面没有障碍物，则向前移动 1 步
                if (gameMap[wolf.getY() - 2][wolf.getX()] != FigureType.BARRIER.getValue()) {
                    gameMap[wolf.getY() - 2][wolf.getX()] = FigureType.BOX.getValue();
                    gameMap[wolf.getY() - 1][wolf.getX()] = FigureType.SPACE.getValue();
                }
                // 更新地图
                this.gameDto.setGameMap(gameMap);
            }
        }
        // 人物移动
        this.gameDto.getWolf().move(0, -1, gameMap);

    }

    @Override
    public void keyDown() {
        // 设置人物正面图
        this.gameDto.setWolfImage(GameImageConstant.WOLF_B);
        // 获取人物新的位置
        Wolf wolf = this.gameDto.getWolf();
        // 获取所有的箱子
        List<SheepBox> boxList = this.gameDto.getBoxList();
        // 获取游戏地图
        int[][] gameMap = this.gameDto.getGameMap();
        // 遍历箱子
        for (SheepBox box : boxList) {
            if (box.getX() == wolf.getX() && box.getY() == wolf.getY() + 1) {
                // 判断箱子是否可以移动
                if (!box.moveBox(0, 1, gameMap)) {
                    return;
                }
                // 修改人物与箱子的标记，如果人物与箱子前面没有障碍物，则向前移动 1 步
                if (gameMap[wolf.getY() + 2][wolf.getX()] != FigureType.BARRIER.getValue()) {
                    gameMap[wolf.getY() + 2][wolf.getX()] = FigureType.BOX.getValue();
                    gameMap[wolf.getY() + 1][wolf.getX()] = FigureType.SPACE.getValue();

                }
                this.gameDto.setGameMap(gameMap);
            }
        }
        // 人物移动
        this.gameDto.getWolf().move(0, 1, gameMap);
    }

    @Override
    public void keyLeft() {
        // 设置人物左面图
        this.gameDto.setWolfImage(GameImageConstant.WOLF_L);
        // 获取人物新的位置
        Wolf wolf = this.gameDto.getWolf();
        // 获取游戏地图
        int[][] gameMap = this.gameDto.getGameMap();
        // 获取所有的箱子
        List<SheepBox> boxList = this.gameDto.getBoxList();
        // 遍历箱子
        for (SheepBox box : boxList) {
            if (box.getX() == wolf.getX() - 1 && box.getY() == wolf.getY()) {
                // 判断箱子是否可以移动
                if (!box.moveBox(-1, 0, gameMap)) {
                    return;
                }
                // 修改人物与箱子的标记，如果人物与箱子前面没有障碍物，则向前移动 1 步
                if (gameMap[wolf.getY()][wolf.getX() - 2] != FigureType.BARRIER.getValue()) {
                    gameMap[wolf.getY()][wolf.getX() - 2] = FigureType.BOX.getValue();
                    gameMap[wolf.getY()][wolf.getX() - 1] = FigureType.SPACE.getValue();
                }
                this.gameDto.setGameMap(gameMap);
            }
        }
        // 人物移动
        this.gameDto.getWolf().move(-1, 0, gameMap);
    }

    @Override
    public void keyRight() {
        // 设置人物右面图
        this.gameDto.setWolfImage(GameImageConstant.WOLF_R);
        // 获取人物新的位置
        Wolf wolf = this.gameDto.getWolf();
        // 获取游戏地图
        int[][] gameMap = this.gameDto.getGameMap();
        // 获取所有的箱子
        List<SheepBox> boxList = this.gameDto.getBoxList();
        for (SheepBox box : boxList) {
            if (box.getX() == wolf.getX() + 1 && box.getY() == wolf.getY()) {
                // 判断箱子是否可以移动
                if (!box.moveBox(1, 0, gameMap)) {
                    return;
                }
                // 修改人物与箱子的标记，如果人物与箱子前面没有障碍物，则向前移动 1 步
                if (gameMap[wolf.getY()][wolf.getX() + 2] != FigureType.BARRIER.getValue()) {
                    gameMap[wolf.getY()][wolf.getX() + 2] = FigureType.BOX.getValue();
                    gameMap[wolf.getY()][wolf.getX() + 1] = FigureType.SPACE.getValue();
                }
                // 更新游戏地图
                this.gameDto.setGameMap(gameMap);
            }
        }
        // 人物移动
        this.gameDto.getWolf().move(1, 0, gameMap);
    }

    @Override
    public boolean gameFinish() {
        // 获取所有的箱子
        List<SheepBox> boxList = this.gameDto.getBoxList();
        // 获取游戏地图
        int[][] coopMap = this.gameDto.getCoopPlace();
        // 声明临时箱子对象
        SheepBox box = null;
        // 统计进入笼子的箱子数
        int count = 0;
        for (int i = 0, length = boxList.size(); i < length; i++) {
            // 获取箱子对象
            box = boxList.get(i);
            if (coopMap[box.getY()][box.getX()] == FigureType.COOP.getValue()) {
                count++;
            }
        }
        // 完成游戏
        if (count == this.gameDto.getObjectNumber()) {
            this.gameDto.setOver(true);
            return true;
        }
        return false;
    }

}
