package kevin.service;

import java.util.List;

import kevin.dto.GameDto;
import kevin.entity.SheepBox;
import kevin.entity.Wolf;
import kevin.ui.GameImage;

/**
 * ҵ���߼���
 * @author kevin
 *
 */
public class GameServiceImpl implements GameService{
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto gameDto;
	
	public GameServiceImpl(GameDto gameDto){
		//��ʼ����Ϸ����Դ
		this.gameDto=gameDto;
	}
	
	@Override
	public void keyUp() {
		//�������ﱳ��ͼ
		this.gameDto.setWolfImage(GameImage.WOLF_A);
		//��ȡ�����µ�λ��
		Wolf wolf = this.gameDto.getWolf();
		//��ȡ���е�����
		List<SheepBox> boxList = this.gameDto.getBoxList();
		//��ȡ��Ϸ��ͼ
		int[][] gameMap=this.gameDto.getGameMap();
		for (SheepBox box : boxList) {
			if (box.getX() == wolf.getX() && box.getY() == wolf.getY() - 1) {
				// �ж������Ƿ������ϰ�����������������ϰ�ʱ�����ﲻ���ƶ�
				if (!box.moveBox(0, -1, gameMap)) {
					return;
				}
				//�����ƶ����޸����ӵı��
				if (gameMap[wolf.getY() - 2][wolf.getX()] != 1) {
					gameMap[wolf.getY() - 2][wolf.getX()] = 2;
					gameMap[wolf.getY() - 1][wolf.getX()] = 0;
				}
				//���µ�ͼ
				this.gameDto.setGameMap(gameMap);
			}
		}
		//�����ƶ�
	    this.gameDto.getWolf().move(0,-1,gameMap);

	}

	@Override
	public void keyDown() {
		//������������ͼ
		this.gameDto.setWolfImage(GameImage.WOLF_B);
		//��ȡ�����µ�λ��
		Wolf wolf = this.gameDto.getWolf();
		//��ȡ���е�����
		List<SheepBox> boxList = this.gameDto.getBoxList();
		//��ȡ��Ϸ��ͼ
		int[][] gameMap=this.gameDto.getGameMap();
		//��������
		for (SheepBox box : boxList) {
			if (box.getX() == wolf.getX() && box.getY() == wolf.getY() + 1) {
				// �ж������Ƿ������ϰ�����������������ϰ�ʱ�����ﲻ���ƶ�
				if (!box.moveBox(0, 1, gameMap)) {
					return;
				}
				//�޸����ӱ��
				if (gameMap[wolf.getY() + 2][wolf.getX()] != 1) {
					gameMap[wolf.getY() + 2][wolf.getX()] = 2;
					gameMap[wolf.getY() + 1][wolf.getX()] = 0;

				}
				this.gameDto.setGameMap(gameMap);
			}
		}
		//�����ƶ�
		this.gameDto.getWolf().move(0,1,gameMap);
	}

	@Override
	public void keyLeft() {
		//������������ͼ
		this.gameDto.setWolfImage(GameImage.WOLF_L);
		//��ȡ�����µ�λ��
		Wolf wolf = this.gameDto.getWolf();
		//��ȡ��Ϸ��ͼ
		int[][] gameMap=this.gameDto.getGameMap();
		//��ȡ���е�����
		List<SheepBox> boxList = this.gameDto.getBoxList();
		//��������
		for(SheepBox box:boxList){
			if(box.getX()==wolf.getX()-1&&box.getY()==wolf.getY()){
			  //�ж������Ƿ������ϰ�����������������ϰ�ʱ�����ﲻ���ƶ�
			  if(!box.moveBox(-1,0,gameMap)){
				  return;
			  }
			//�޸����ӱ��
			  if(gameMap[wolf.getY()][wolf.getX()-2]!=1){
				  gameMap[wolf.getY()][wolf.getX()-2]=2;
				  gameMap[wolf.getY()][wolf.getX()-1]=0;
			  }
			  this.gameDto.setGameMap(gameMap);
			}
		}
		//�����ƶ�
		this.gameDto.getWolf().move(-1,0,gameMap);
	}

	@Override
	public void keyRight() {
		//������������ͼ
		this.gameDto.setWolfImage(GameImage.WOLF_R);
		//��ȡ�����µ�λ��
		Wolf wolf = this.gameDto.getWolf();
		//��ȡ��Ϸ��ͼ
		int[][] gameMap=this.gameDto.getGameMap();
		//��ȡ���е�����
		List<SheepBox> boxList = this.gameDto.getBoxList();
		for (SheepBox box : boxList) {
			if (box.getX() == wolf.getX() + 1 && box.getY() == wolf.getY()) {
				//�ж������Ƿ������ϰ�� ���������������ϰ�ʱ�����ﲻ���ƶ�
				if (!box.moveBox(1, 0, gameMap)) {
					return;
				}
				//�޸����ӱ��
				if (gameMap[wolf.getY()][wolf.getX() + 2] != 1) {
					gameMap[wolf.getY()][wolf.getX() + 2] = 2;
					gameMap[wolf.getY()][wolf.getX() + 1] = 0;
				}
				//������Ϸ��ͼ
				this.gameDto.setGameMap(gameMap);
			}
		}
		//�����ƶ�
		this.gameDto.getWolf().move(1,0,gameMap);
	}

	@Override
	public boolean gameFinish() {
		//��ȡ���е�����
		List<SheepBox> boxList = this.gameDto.getBoxList();
		//��ȡ��Ϸ��ͼ
		int[][] coopMap = this.gameDto.getCoopPlace();
		//������ʱ���Ӷ���
		SheepBox box=null;
		//ͳ�ƽ������ӵ�������
		int count=0;
		for(int i=0,length=boxList.size();i<length;i++){
			//��ȡ���Ӷ���
			box=boxList.get(i);
			if(coopMap[box.getY()][box.getX()]==4){
				count++;
			}
		}
		//�����Ϸ
		if(count==this.gameDto.getObjectNumber()){
			this.gameDto.setOver(true);
			return true;
		}
		return false;
	}

}
