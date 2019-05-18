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
 * ��Ϸҵ���߼���ӿ�
 * @author kevin
 *
 */
public class GameServiceImpl implements GameService{
	
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto gameDto;
	
	/**
	 * �����߳�
	 */
	private ActionMusic music;
	
	/**
	 * �����������
	 */
	private Random random=new Random();
	
	/**
	 * �������Χ��С
	 */
	private static final int RAND_SIZE=GameFactory.getRectNumber();
	
	/**
	 * ����������Ŀ
	 */
	private static final int MAX_TYPE=GameFactory.getRectNumber();
	
	/**
	 * ���������
	 */
	private static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();
	
	public GameServiceImpl(GameDto gameDto){
		//������Ϸ����Դ
		this.gameDto=gameDto;
		
	}

	@Override
	public boolean keyUp() {
		//��ͣ
		if(this.gameDto.isPause()){
			return true;
		}
		//�߳���
		synchronized (this.gameDto) {
			//������ת
			this.gameDto.getGameRect().rectRound(this.gameDto.getGameMap());
			return true;
		}
		
	}

	@Override
	public boolean keyDown() {
		//��ͣ
		if(this.gameDto.isPause()){
			return true;
		}
		synchronized (this.gameDto) {
			//��ȡ��Ϸ��ͼ
			boolean[][] gameMap = this.gameDto.getGameMap();
			//���������ƶ�,�ж��Ƿ��ƶ��ɹ�
			if(this.gameDto.getGameRect().rectMove(0,1,gameMap)){
				return false;
			}
			//��ȡ��������
			Point[] rectPoint = this.gameDto.getGameRect().getRectPoint();
			//�ѵ����鵽��ͼ��
			for(int i=0,length=rectPoint.length;i<length;i++){
				//�������޷��ƶ�ʱ��λ��Ϊtrue
				gameMap[rectPoint[i].y][rectPoint[i].x]=true;
				
			}
			//��������ѵ�����
		    this.music=new ActionMusic("music/regret.wav");
		    this.music.start();
			//���е÷�
			int plusExp = this.plusExp();
			//���Ӿ���ֵ���������ӷ�
			this.rankUpAndPlusScore(plusExp);
			//���õ�ͼ��Ϣ
			this.gameDto.setGameMap(gameMap);
			//ˢ�·���
			this.gameDto.getGameRect().initRect(this.gameDto.getRemind());
			//��ʾ��һ������
			this.gameDto.setRemind(new Random().nextInt(MAX_TYPE));
			//�����Ϸ�Ƿ�ʧ��
			if(this.isGameOver()){
				//������Ϸ�Ŀ�ʼ״̬
				this.gameDto.setGameStart(false);
			}
			return true;
		}
	}
	
	@Override
	public boolean keyLeft() {
		//��ͣ
		if(this.gameDto.isPause()){
			return true;
		}
		synchronized (this.gameDto) {
			//���������ƶ�
			this.gameDto.getGameRect().rectMove(-1,0,this.gameDto.getGameMap());
			return true;
		}
	}

	@Override
	public boolean keyRight() {
		//��ͣ
		if(this.gameDto.isPause()){
			return true;
		}
		synchronized (this.gameDto) {
			//���������ƶ�
			this.gameDto.getGameRect().rectMove(1,0,this.gameDto.getGameMap());
			return true;
		}
		
	}
	
	/**
	 * �����ӷֲ���
	 */
	private void rankUpAndPlusScore(int plusExp){
		//��ȡ��ǰ�ĵȼ�
		int rank = this.gameDto.getRank();
		//��ȡ��ǰ������
		int rmline = this.gameDto.getNewRmline();
		//�ж��Ƿ���������
		if(rmline%LEVEL_UP+plusExp>=LEVEL_UP){
			//����
			this.gameDto.setRank(++rank);
		}
		//����������
		this.gameDto.setNewRmline(rmline+plusExp);
		//��ȡ�ӷֱ�
		HashMap<Integer,Integer> scoreList = GameConfig.getSystemConfig().getScoreList();
		if(scoreList.containsKey(plusExp)){
			//�ܷ���
			int totalPoints=this.gameDto.getNewScore()+scoreList.get(plusExp);
			//���·���
			this.gameDto.setNewScore(totalPoints);
		}
	}

	/**
	 * ���е÷�
	 */
	private int plusExp() {
		//��ȡ��ͼ��Ϣ
		boolean[][] gameMap = this.gameDto.getGameMap();
		//��ʼ��������
		int lineNum=0;
		//ɨ���ͼ���ж��Ƿ��������
		for(int y=0;y<gameMap.length;y++){
			if(this.isCanRemoveLine(y, gameMap)){
				//������������
				this.music=new ActionMusic("music/rmline.wav");
				this.music.start();
				//���в���
				this.removeLine(y,gameMap);
				//����������
				lineNum++;
			}
		}
		return lineNum;
	}
	
	/**
	 * ���в���
	 */
	private void removeLine(int rowNumber,boolean[][] gameMap){
		for(int x=0;x<GameConfig.getSystemConfig().getMaxX();x++){
			for(int y=rowNumber;y>0;y--){
				gameMap[y][x]=gameMap[y-1][x];
			}
			//��0����Ϊfalse
			gameMap[0][x]=false;
		}
	}
	
	/**
	 * �ж��Ƿ��������
	 */
	private boolean isCanRemoveLine(int y,boolean[][] gameMap) {
		for(int x=0;x<GameConfig.getSystemConfig().getMaxX();x++){
			//ֻҪ������һ���գ�false�����Ͳ�����
			if(!gameMap[y][x]){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * �����Ϸ�Ƿ�ʧ��
	 */
	private boolean isGameOver(){
		//��ȡ��Ϸ��ͼ
		boolean[][] gameMap = this.gameDto.getGameMap();
		//��ȡ��Ϸ����
	    Point[] gameRect = this.gameDto.getGameRect().getRectPoint();
		for(int i=0;i<gameRect.length;i++){
			//������䷽�����ͼ���غϣ���Ϸ��ʧ��
			if(gameMap[gameRect[i].y][gameRect[i].x]){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ���׼�
	 */
	@Override
	public boolean keyFunUp() {
		//�÷�
		this.rankUpAndPlusScore(4);
		return true;
	}
	
	/**
	 * ˲�������
	 */
	@Override
	public boolean keyFunDown() {
		//��ͣ
		if(this.gameDto.isPause()){
			return true;
		}
		//˲������
		while(!keyDown());
		return true;
	}
	
	/**
	 * ��Ӱ���ؼ�
	 */
	@Override
	public boolean keyFunLeft() {
		//������Ӱ״̬
		this.gameDto.changeShadow();
		return true;
	}
	
	/**
	 * ��ͣ��
	 */
	@Override
	public boolean keyFunRight() {
		// ������ͣ״̬
		this.gameDto.changePause();
		return true;
	}
	
	@Override
	public void startGame() {
		//��ʼ����Ϸ����
		this.gameDto.setGameRect(new GameRect(random.nextInt(RAND_SIZE)));
		//�����ʼ����һ������
		this.gameDto.setRemind(random.nextInt(RAND_SIZE));
		//����Ϸ״̬����Ϊ��ʼ
		this.gameDto.setGameStart(true); 
		//��ʼ������Դ
		this.gameDto.initEntityParam();
			
	}
	
	/**
	 * ���������ڲ���
	 * @author kevin
	 *
	 */
	private class ActionMusic extends Thread{
		/**
		 * �ļ�·��
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
