package kevin.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kevin.config.GameConfig;
import kevin.entity.GameRect;
import kevin.utils.GameFunction;

/**
 * 游戏数据载体
 * @author kevin
 *
 */
public class GameDto {

	/**
	 * 数据库记录
	 */
	private List<Player> dbRecode;
	
	/**
	 * 本地记录
	 */
	private List<Player> diskRecode;
	
	/**
	 * 游戏方块
	 */
	private GameRect gameRect;
	
	/**
	 * 游戏地图
	 */
	private boolean[][] gameMap;
	
	/**
	 * 提示方块
	 */
	private int remind;
	
	/**
	 * 游戏等级
	 */
	private int rank;
	
	/**
	 * 当前分数
	 */
	private int newScore;
	
	/**
	 * 当前消行数
	 */
	private int newRmline;
	
	/**
	 * 游戏开始
	 */
	private boolean gameStart;
	
	/**
	 * 是否阴影
	 */
	private boolean isShadow;
	
	/**
	 * 是否暂停
	 */
	private boolean isPause;
	
	/**
	 * 下落速度
	 */
	private long speedTime;
	
	/**
	 * 地图行数
	 */
	private static final int GAMEZONE_ROW=GameConfig.getSystemConfig().getMaxY();
	
	/**
	 * 地图列数
	 */
	private static final int GAMEZONE_COL=GameConfig.getSystemConfig().getMaxX();
	
	/**
	 * 最大行数
	 */
	private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();

	public GameDto() {
		//初始化参数
		this.initEntityParam();
	}

	/**
	 * 初始化参数
	 */
	public void initEntityParam(){
		//初始化游戏地图
	    this.gameMap=new boolean[GAMEZONE_ROW][GAMEZONE_COL];
	    //初始化分数
	    this.newScore=0;
	    //初始化等级
	    this.rank=0;
	    //初始化消行数
	    this.newRmline=0;
		//默认开启阴影
		this.isShadow=true;
		//默认游戏未暂停
		this.isPause=false;
		//初始化方块下落等待时间
		this.speedTime=GameFunction.getSleepTime(this.rank);
	}
	
	public List<Player> getDbRecode() {
		return dbRecode;
	}

	public void setDbRecode(List<Player> dbRecode) {
		this.dbRecode = this.setFillRecode(dbRecode);
	}

	public List<Player> getDiskRecode() {
		return diskRecode;
	}

	public void setDiskRecode(List<Player> diskRecode) {
		this.diskRecode = this.setFillRecode(diskRecode);
	}

	public GameRect getGameRect() {
		return gameRect;
	}

	public void setGameRect(GameRect gameRect) {
		this.gameRect = gameRect;
	}

	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public int getRemind() {
		return remind;
	}

	public void setRemind(int remind) {
		this.remind = remind;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
		//计算下落等待时间
		this.speedTime=GameFunction.getSleepTime(this.rank);
	}

	public int getNewScore() {
		return newScore;
	}

	public void setNewScore(int newScore) {
		this.newScore = newScore;
	}

	public int getNewRmline() {
		return newRmline;
	}

	public void setNewRmline(int newRmline) {
		this.newRmline = newRmline;
	}
	
	public boolean isGameStart() {
		return gameStart;
	}

	public void setGameStart(boolean gameStart) {
		this.gameStart =gameStart;
	}

	public boolean isShadow() {
		return isShadow;
	}

	public void changeShadow() {
		this.isShadow = !this.isShadow;
	}

	public boolean isPause() {
		return isPause;
	}

	public void changePause() {
		this.isPause = !this.isPause;
	}

	public long getSpeedTime() {
		return speedTime;
	}

	/**
	 * 设置玩家数据源
	 * @param palyers 玩家数据
	 * @return 玩家数据对象
	 */
	private List<Player> setFillRecode(List<Player> players){
		//如果集合为空，则创建集合
		if(players==null){
			players=new ArrayList<Player>();
		}
		//如果数据不满5条，则加满5条
		while(players.size()<MAX_ROW){
			players.add(new Player("NO Data", 0));
		}
		//排序
		Collections.sort(players);
		return players;
	}
}
