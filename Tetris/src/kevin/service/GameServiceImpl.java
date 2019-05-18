package kevin.service;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

import kevin.config.GameConfig;
import kevin.dto.GameDto;
import kevin.entity.GameRect;
import kevin.factory.GameFactory;
import kevin.utils.MusicUtil;
/**
 * 游戏业务逻辑层接口
 * @author kevin
 *
 */
public class GameServiceImpl implements GameService{
	
	/**
	 * 游戏数据源
	 */
	private GameDto gameDto;
	
	/**
	 * 音乐线程
	 */
	private ActionMusic music;
	
	/**
	 * 随机数生产器
	 */
	private Random random=new Random();
	
	/**
	 * 随机数范围大小
	 */
	private static final int RAND_SIZE=GameFactory.getRectNumber();
	
	/**
	 * 方块类型数目
	 */
	private static final int MAX_TYPE=GameFactory.getRectNumber();
	
	/**
	 * 最大消行数
	 */
	private static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();
	
	public GameServiceImpl(GameDto gameDto){
		//创建游戏数据源
		this.gameDto=gameDto;
		
	}

	@Override
	public boolean keyUp() {
		//暂停
		if(this.gameDto.isPause()){
			return true;
		}
		//线程锁
		synchronized (this.gameDto) {
			//方块旋转
			this.gameDto.getGameRect().rectRound(this.gameDto.getGameMap());
			return true;
		}
		
	}

	@Override
	public boolean keyDown() {
		//暂停
		if(this.gameDto.isPause()){
			return true;
		}
		synchronized (this.gameDto) {
			//获取游戏地图
			boolean[][] gameMap = this.gameDto.getGameMap();
			//方块向下移动,判断是否移动成功
			if(this.gameDto.getGameRect().rectMove(0,1,gameMap)){
				return false;
			}
			//获取方块数组
			Point[] rectPoint = this.gameDto.getGameRect().getRectPoint();
			//堆叠方块到地图上
			for(int i=0,length=rectPoint.length;i<length;i++){
				//当方块无法移动时，位置为true
				gameMap[rectPoint[i].y][rectPoint[i].x]=true;
				
			}
			//产生方块堆叠音乐
		    this.music=new ActionMusic("music/regret.wav");
		    this.music.start();
			//消行得分
			int plusExp = this.plusExp();
			//增加经验值，并升级加分
			this.rankUpAndPlusScore(plusExp);
			//设置地图信息
			this.gameDto.setGameMap(gameMap);
			//刷新方块
			this.gameDto.getGameRect().initRect(this.gameDto.getRemind());
			//提示下一个方块
			this.gameDto.setRemind(new Random().nextInt(MAX_TYPE));
			//检查游戏是否失败
			if(this.isGameOver()){
				//设置游戏的开始状态
				this.gameDto.setGameStart(false);
			}
			return true;
		}
	}
	
	@Override
	public boolean keyLeft() {
		//暂停
		if(this.gameDto.isPause()){
			return true;
		}
		synchronized (this.gameDto) {
			//方块向左移动
			this.gameDto.getGameRect().rectMove(-1,0,this.gameDto.getGameMap());
			return true;
		}
	}

	@Override
	public boolean keyRight() {
		//暂停
		if(this.gameDto.isPause()){
			return true;
		}
		synchronized (this.gameDto) {
			//方块向右移动
			this.gameDto.getGameRect().rectMove(1,0,this.gameDto.getGameMap());
			return true;
		}
		
	}
	
	/**
	 * 升级加分操作
	 */
	private void rankUpAndPlusScore(int plusExp){
		//获取当前的等级
		int rank = this.gameDto.getRank();
		//获取当前消行数
		int rmline = this.gameDto.getNewRmline();
		//判断是否满足升级
		if(rmline%LEVEL_UP+plusExp>=LEVEL_UP){
			//升级
			this.gameDto.setRank(++rank);
		}
		//更新消行数
		this.gameDto.setNewRmline(rmline+plusExp);
		//获取加分表
		HashMap<Integer,Integer> scoreList = GameConfig.getSystemConfig().getScoreList();
		if(scoreList.containsKey(plusExp)){
			//总分数
			int totalPoints=this.gameDto.getNewScore()+scoreList.get(plusExp);
			//更新分数
			this.gameDto.setNewScore(totalPoints);
		}
	}

	/**
	 * 消行得分
	 */
	private int plusExp() {
		//获取地图信息
		boolean[][] gameMap = this.gameDto.getGameMap();
		//初始化消行数
		int lineNum=0;
		//扫描地图，判断是否可以消行
		for(int y=0;y<gameMap.length;y++){
			if(this.isCanRemoveLine(y, gameMap)){
				//产生消行音乐
				this.music=new ActionMusic("music/rmline.wav");
				this.music.start();
				//消行操作
				this.removeLine(y,gameMap);
				//增加消行数
				lineNum++;
			}
		}
		return lineNum;
	}
	
	/**
	 * 消行操作
	 */
	private void removeLine(int rowNumber,boolean[][] gameMap){
		for(int x=0;x<GameConfig.getSystemConfig().getMaxX();x++){
			for(int y=rowNumber;y>0;y--){
				gameMap[y][x]=gameMap[y-1][x];
			}
			//第0行置为false
			gameMap[0][x]=false;
		}
	}
	
	/**
	 * 判断是否可以消行
	 */
	private boolean isCanRemoveLine(int y,boolean[][] gameMap) {
		for(int x=0;x<GameConfig.getSystemConfig().getMaxX();x++){
			//只要该行有一个空（false），就不消行
			if(!gameMap[y][x]){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检查游戏是否失败
	 */
	private boolean isGameOver(){
		//获取游戏地图
		boolean[][] gameMap = this.gameDto.getGameMap();
		//获取游戏方块
	    Point[] gameRect = this.gameDto.getGameRect().getRectPoint();
		for(int i=0;i<gameRect.length;i++){
			//如果下落方块与地图有重合，游戏就失败
			if(gameMap[gameRect[i].y][gameRect[i].x]){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 作弊键
	 */
	@Override
	public boolean keyFunUp() {
		//得分
		this.rankUpAndPlusScore(4);
		return true;
	}
	
	/**
	 * 瞬间下落键
	 */
	@Override
	public boolean keyFunDown() {
		//暂停
		if(this.gameDto.isPause()){
			return true;
		}
		//瞬间下落
		while(!keyDown());
		return true;
	}
	
	/**
	 * 阴影开关键
	 */
	@Override
	public boolean keyFunLeft() {
		//设置阴影状态
		this.gameDto.changeShadow();
		return true;
	}
	
	/**
	 * 暂停键
	 */
	@Override
	public boolean keyFunRight() {
		// 设置暂停状态
		this.gameDto.changePause();
		return true;
	}
	
	@Override
	public void startGame() {
		//初始化游戏方块
		this.gameDto.setGameRect(new GameRect(random.nextInt(RAND_SIZE)));
		//随机初始化下一个方块
		this.gameDto.setRemind(random.nextInt(RAND_SIZE));
		//将游戏状态设置为开始
		this.gameDto.setGameStart(true); 
		//初始化数据源
		this.gameDto.initEntityParam();
			
	}
	
	/**
	 * 动作音乐内部类
	 * @author kevin
	 *
	 */
	private class ActionMusic extends Thread{
		/**
		 * 文件路径
		 */
		private String filePath;
		
		public ActionMusic(String filePath) {
			super();
			this.filePath = filePath;
		}

		@Override
		public void run() {
			MusicUtil.audioWav(filePath);
		}
	}
	
}
