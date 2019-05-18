package kevin.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kevin.config.GameConfig;
import kevin.entity.GameRect;
import kevin.utils.GameFunction;

/**
 * ��Ϸ��������
 * @author kevin
 *
 */
public class GameDto {

	/**
	 * ���ݿ��¼
	 */
	private List<Player> dbRecode;
	
	/**
	 * ���ؼ�¼
	 */
	private List<Player> diskRecode;
	
	/**
	 * ��Ϸ����
	 */
	private GameRect gameRect;
	
	/**
	 * ��Ϸ��ͼ
	 */
	private boolean[][] gameMap;
	
	/**
	 * ��ʾ����
	 */
	private int remind;
	
	/**
	 * ��Ϸ�ȼ�
	 */
	private int rank;
	
	/**
	 * ��ǰ����
	 */
	private int newScore;
	
	/**
	 * ��ǰ������
	 */
	private int newRmline;
	
	/**
	 * ��Ϸ��ʼ
	 */
	private boolean gameStart;
	
	/**
	 * �Ƿ���Ӱ
	 */
	private boolean isShadow;
	
	/**
	 * �Ƿ���ͣ
	 */
	private boolean isPause;
	
	/**
	 * �����ٶ�
	 */
	private long speedTime;
	
	/**
	 * ��ͼ����
	 */
	private static final int GAMEZONE_ROW=GameConfig.getSystemConfig().getMaxY();
	
	/**
	 * ��ͼ����
	 */
	private static final int GAMEZONE_COL=GameConfig.getSystemConfig().getMaxX();
	
	/**
	 * �������
	 */
	private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();

	public GameDto() {
		//��ʼ������
		this.initEntityParam();
	}

	/**
	 * ��ʼ������
	 */
	public void initEntityParam(){
		//��ʼ����Ϸ��ͼ
	    this.gameMap=new boolean[GAMEZONE_ROW][GAMEZONE_COL];
	    //��ʼ������
	    this.newScore=0;
	    //��ʼ���ȼ�
	    this.rank=0;
	    //��ʼ��������
	    this.newRmline=0;
		//Ĭ�Ͽ�����Ӱ
		this.isShadow=true;
		//Ĭ����Ϸδ��ͣ
		this.isPause=false;
		//��ʼ����������ȴ�ʱ��
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
		//��������ȴ�ʱ��
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
	 * �����������Դ
	 * @param palyers �������
	 * @return ������ݶ���
	 */
	private List<Player> setFillRecode(List<Player> players){
		//�������Ϊ�գ��򴴽�����
		if(players==null){
			players=new ArrayList<Player>();
		}
		//������ݲ���5���������5��
		while(players.size()<MAX_ROW){
			players.add(new Player("NO Data", 0));
		}
		//����
		Collections.sort(players);
		return players;
	}
}
