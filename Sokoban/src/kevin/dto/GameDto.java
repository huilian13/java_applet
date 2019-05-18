package kevin.dto;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import kevin.config.GameConfig;
import kevin.entity.Coop;
import kevin.entity.SheepBox;
import kevin.entity.Wolf;
import kevin.ui.GameImage;

/**
 * ��Ϸ��������
 * @author kevin
 *
 */
public class GameDto {
	
	
	/**
	 * �����̫�ǣ�
	 */
	private Wolf wolf;
	
	/**
	 * ����ͼƬ
	 */
	private Image wolfImage;
	
	/**
	 * ��Ϸ��ͼ����
	 */
	private List<int[][]> gameMapList;
	
	/**
	 * ��Ϸ��ͼ
	 */
	private int[][] gameMap;
	
	/**
	 * ���ӵ�λ������
	 */
	private int[][] coopPlace;
	
	/**
	 * ���Ӽ��ϣ���
	 */
	private List<SheepBox> boxList;
	
	/**
	 * ���Ӽ���
	 */
	private List<Coop> coopList;
	
	/**
	 * ��Ϸ�Ƿ����
	 */
	private boolean isOver;
	
	/**
	 * ������Ŀ
	 */
	private int objectNumber;
	
	/**
	 * ��ͼ�ļ�����
	 */
	private List<File> files;
	
	/**
	 * ��ͼ����
	 */
	private static final int ROW=GameConfig.getSystemConfig().getMaxY();
	
	/**
	 * ��ͼ����
	 */
	private static final int COL=GameConfig.getSystemConfig().getMaxX();
	
	/**
	 * ��ͼ����·��
	 */
	private static final String MAP_PATH="data/gameMap.dat";
	
	public GameDto() {
		//��ʼ����ͼ����
		this.initGameMapList();
		//��ʼ������Դ
		this.initDto(0);
	}
	
	/**
	 * ��ʼ������Դ
	 */
	public void initDto(int num){
		//��ʼ������λ������
		this.coopPlace=new int[ROW][COL];
		//����Ĭ��Ϊ����ͼ
		this.wolfImage=GameImage.WOLF_B;
		//��ʼ�����Ӻ����Ӽ���
		this.initEntityObject(num);
		//��ʼ���ļ����϶���
		this.initFiles();
		//��ʼ����Ŀ
		this.objectNumber=this.coopList.size();
		//��ϷĬ��Ϊδ���
		this.isOver=false;
	}
	
	/**
	 * �����ʼ��
	 */
	@SuppressWarnings("unchecked")
	private void initGameMapList(){
		//��ʼ������
		this.gameMapList=new ArrayList<int[][]>();
		try {
			//��������������
			ObjectInputStream read=new ObjectInputStream(new FileInputStream(MAP_PATH));
			//��ȡ����
			this.gameMapList=(ArrayList<int[][]>)read.readObject();
			//�ر���
			read.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * ��ʼ�����Ӻ����ӵļ���
	 */
	private void initEntityObject(int index){
		//��ʼ����ͼ
		this.gameMap=this.gameMapList.get(index);
		//��ʼ�����Ӽ���
		this.boxList=new ArrayList<SheepBox>();
		//��ʼ�����Ӽ���
		this.coopList=new ArrayList<Coop>();
		for(int x=0;x<gameMap.length;x++){
			for(int y =0;y<gameMap[x].length;y++){
				if(gameMap[x][y]==2){
					//��ʼ������
					this.boxList.add(new SheepBox(y,x));
				}
				
				if(gameMap[x][y]==4){
					this.coopList.add(new Coop(y,x));
					//��ʼ������λ��
					this.coopPlace[x][y]=4;
				}
				
				if(gameMap[x][y]==8){
					//��ʼ������
					this.wolf=new Wolf(y,x);
				}
			}
		}
	}
	
	/**
	 * ��ʼ���ļ��������
	 */
	private void initFiles(){
		//��ȡ��ͼ�ļ�����
	    File file=new File("graphics");
	    //��ʼ���ļ�����
	    this.files=new ArrayList<File>();
	    //��ȡ�������ļ�
	    File[] listFiles = file.listFiles();
	    for(File f:listFiles){
	    	if(f.isDirectory()){
	    		//���ļ��ж�����뼯����
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
		//0��ʾ�յأ�1��ʾ�ϰ��2��ʾ����,8��ʾ����ǣ�
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
		System.out.println("ccccc");
	}*/
}
