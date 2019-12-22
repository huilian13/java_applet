package top.yifan.dto;

import top.yifan.config.GameConfig;
import top.yifan.constant.FigureType;
import top.yifan.constant.GameImageConstant;
import top.yifan.entity.Coop;
import top.yifan.entity.SheepBox;
import top.yifan.entity.Wolf;
import top.yifan.util.FileLoadUtil;

import java.awt.*;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 游戏数据载体
 *
 * @author star
 */
public class GameDto {


    /**
     * 人物（灰太狼）
     */
    private Wolf wolf;

    /**
     * 人物图片
     */
    private Image wolfImage;

    /**
     * 游戏地图集合
     */
    private List<int[][]> gameMapList;

    /**
     * 游戏地图
     */
    private int[][] gameMap;

    /**
     * 箱子的位置数组
     */
    private int[][] coopPlace;

    /**
     * 箱子集合（羊）
     */
    private List<SheepBox> boxList;

    /**
     * 笼子集合
     */
    private List<Coop> coopList;

    /**
     * 游戏是否结束
     */
    private boolean isOver;

    /**
     * 对象数目
     */
    private int objectNumber;

    /**
     * 地图文件对象
     */
    private List<File> files;

    /**
     * 地图行数
     */
    private static final int ROW = GameConfig.getSystemConfig().getMaxY();

    /**
     * 地图列数
     */
    private static final int COL = GameConfig.getSystemConfig().getMaxX();

    /**
     * 地图数据路径
     */
    private static final String MAP_PATH = "data/gameMap.dat";

    public GameDto() {
        //初始化地图数组
        this.initGameMapList();
        //初始化数据源
        this.initDto(0);
    }

    /**
     * 初始化数据源
     */
    public void initDto(int num) {
        //初始化箱子位置数组
        this.coopPlace = new int[ROW][COL];
        //设置默认为正面图
        this.wolfImage = GameImageConstant.WOLF_B;
        //初始化箱子和笼子集合
        this.initEntityObject(num);
        //初始化文件集合对象
        this.initFiles();
        //初始化数目
        this.objectNumber = this.coopList.size();
        //游戏默认为未完成
        this.isOver = false;
    }

    /**
     * 数组初始化
     */
    @SuppressWarnings("unchecked")
    private void initGameMapList() {
        //初始化集合
        this.gameMapList = new ArrayList<>();
        try {
            //创建对象输入流
            ObjectInputStream read = new ObjectInputStream(FileLoadUtil.loadAsStream(MAP_PATH));
            //读取对象
            this.gameMapList = (ArrayList<int[][]>) read.readObject();
            //关闭流
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化箱子和笼子的集合
     */
    private void initEntityObject(int index) {
        // 初始化地图
        this.gameMap = this.gameMapList.get(index);
        // 初始化箱子集合
        this.boxList = new ArrayList<>();
        // 初始化笼子集合
        this.coopList = new ArrayList<>();
        for (int x = 0; x < gameMap.length; x++) {
            for (int y = 0; y < gameMap[x].length; y++) {
                // 初始化箱子
                if (gameMap[x][y] == FigureType.BOX.getValue()) {
                    this.boxList.add(new SheepBox(y, x));
                }
                // 初始化笼子
                if (gameMap[x][y] == FigureType.COOP.getValue()) {
                    this.coopList.add(new Coop(y, x));
                    this.coopPlace[x][y] = FigureType.COOP.getValue();
                }
                // 初始化人物
                if (gameMap[x][y] == FigureType.PLAYER.getValue()) {
                    this.wolf = new Wolf(y, x);
                }
            }
        }
    }

    /**
     * 初始化文件数组对象
     */
    private void initFiles() {
        // 获取地图文件对象
        File file = new File(FileLoadUtil.loadAsURL("graphics").getFile());
        // 初始化文件集合
        this.files = new ArrayList<>();
        // 获取所有子文件
        File[] listFiles = file.listFiles();
        for (File f : listFiles) {
            if (f.isDirectory()) {
                // 将文件夹对象存入集合中
                this.files.add(f);
            }
        }
    }

    public Wolf getWolf() {
        return wolf;
    }

    public void setWolfImage(Image wolfImage) {
        this.wolfImage = wolfImage;
    }

    public Image getWolfImage() {
        return wolfImage;
    }

    public List<SheepBox> getBoxList() {
        return boxList;
    }

    public List<Coop> getCoopList() {
        return coopList;
    }

    public List<int[][]> getGameMapList() {
        return gameMapList;
    }

    public int[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(int[][] gameMap) {
        this.gameMap = gameMap;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean isOver) {
        this.isOver = isOver;
    }

    public int[][] getCoopPlace() {
        return coopPlace;
    }

    public int getObjectNumber() {
        return objectNumber;
    }

    public List<File> getFiles() {
        return files;
    }

/*	public static void main(String[] args) throws Exception{
		// 0 表示空地，1 表示障碍物，2 表示箱子，4 表示笼子，8 表示人物（狼）
        List<int[][]> gameMapList=new ArrayList<int[][]>();

		gameMapList.add(new int[][]{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,0,0,0,1,1,1,0,2,0,0,0,0,1},
			{1,0,1,0,0,0,0,0,1,0,0,1,1,1,0,1},
			{1,0,1,0,8,0,1,1,1,0,0,0,0,1,4,1},
			{1,0,1,0,0,0,1,0,0,0,2,1,1,1,4,1},
			{1,0,1,0,0,0,1,1,1,0,0,0,0,1,4,1},
			{1,0,1,0,0,0,0,0,0,0,0,1,1,1,0,1},
			{1,0,1,0,0,0,0,0,0,0,2,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	});

		gameMapList.add(new int[][]{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,1,1,1,1,1,1,1,0,0,0,1,1,0,1},
			{1,0,1,0,0,0,0,0,1,0,0,1,1,0,0,1},
			{1,0,1,0,1,0,1,1,1,0,0,0,1,2,4,1},
			{1,0,1,0,0,0,0,0,0,0,0,0,0,2,4,1},
			{1,0,1,0,1,0,1,1,1,0,0,0,1,2,4,1},
			{1,0,1,0,0,1,0,0,0,0,0,1,1,0,0,1},
			{1,0,1,1,1,1,0,0,0,0,0,0,1,8,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	});

		gameMapList.add(new int[][]{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,1,1,0,0,0,0,0,0,1,0,0,1,1,1},
			{1,0,1,1,0,1,1,1,1,0,1,0,1,1,0,1},
			{1,1,4,0,2,1,1,1,1,0,1,1,1,0,0,1},
			{1,4,4,2,0,2,0,0,0,0,0,8,1,0,0,1},
			{1,4,4,0,2,0,2,0,0,0,0,1,0,0,0,1},
			{1,1,1,1,1,1,0,0,1,1,1,0,1,0,0,1},
			{1,0,1,0,0,1,1,1,0,0,0,1,1,0,0,1},
			{1,0,1,1,1,1,0,0,0,0,0,0,1,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	});
		gameMapList.add(new int[][]{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1},
			{1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,1},
			{1,1,1,0,0,0,0,0,1,0,0,0,1,0,0,1},
			{1,1,1,0,4,0,1,1,1,1,0,1,1,1,0,1},
			{1,1,1,0,4,0,0,0,0,1,1,0,0,0,1,1},
			{1,2,2,2,0,4,0,0,0,1,1,0,0,0,1,1},
			{1,2,2,2,4,1,4,0,1,1,0,1,1,1,0,1},
			{1,1,1,1,0,1,0,4,0,1,0,0,1,0,0,1},
			{1,0,0,1,0,0,8,0,0,1,0,0,1,0,0,1},
			{1,0,0,1,1,1,1,1,1,1,0,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	});

		gameMapList.add(new int[][]{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,1,1,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,1,1,1,0,0,1,1,1,0,0,1,1,0,1},
			{1,0,1,0,1,2,0,2,0,1,0,1,1,0,0,1},
			{1,0,1,1,0,2,0,1,0,1,0,0,1,0,0,1},
			{1,0,1,0,0,0,0,0,0,1,0,1,0,0,1,1},
			{1,0,1,0,0,1,2,0,0,1,0,0,1,0,0,1},
			{1,0,1,8,0,2,0,0,0,1,0,0,1,0,0,1},
			{1,0,1,0,2,0,0,2,0,0,0,0,0,1,0,1},
			{1,0,1,1,1,1,1,1,1,1,1,4,0,1,1,1},
			{1,0,0,0,0,0,4,4,4,4,4,4,0,1,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	});

		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("data/gameMap.dat"));
		out.writeObject(gameMapList);
		out.close();
	}*/
}
