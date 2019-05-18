package kevin.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import kevin.config.GameConfig;
import kevin.dto.Player;

public abstract class DataLayer extends Layer{
	
	/**
	 * �������
	 */
	private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();
	
	/**
	 * ֵ�۸߶�
	 */
	private static final int RECT_H=RECT_EXP_H+4;
	
	/**
	 * ֵ�ۿ�ʼ��y����
	 */
	private static int START_Y=0; 
	
	/**
	 * ֵ�ۼ��
	 */
	private static int RECT_PAD=0;

	protected DataLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	protected void showData(Image img,List<Player> players,Graphics g){
		//��ʼ��ֵ�ۼ��
		RECT_PAD=(this.height-RECT_H*MAX_ROW-(PADDING<<1)-img.getHeight(null))/MAX_ROW;
		//��ʼ��ֵ�ۿ�ʼ��y����
		START_Y=this.y+img.getHeight(null)+PADDING+RECT_PAD;
		//��������ͼƬ
		g.drawImage(img,this.x+PADDING, this.y+PADDING,null);
		//��ȡ��ǰ��ҷ���
		double newScore = (double)this.gameDto.getNewScore();
		//������Ҷ���
		Player player=null;
		//������ֵ����
		double percent=0.0;
		//����ֵ��
		for(int i=0;i<MAX_ROW;i++){
			//��ȡ��Ҷ���
			player=players.get(i);
			//��ȡ��ҷ���
			int recodeScore=player.getScore();
			//�����ֵ
			if(recodeScore!=0){
				percent=newScore/recodeScore;
				//�ж�percent�Ƿ����1
				percent=percent>1?1.0:percent;
			}else {
				percent=0;
			}
			//�жϵ÷��Ƿ���0
			String score=recodeScore==0?null:String.valueOf(recodeScore);
			this.drawSlot(START_Y+(RECT_H+RECT_PAD)*i, player.getName(),score,percent, g);
		}
		
	}
	
	@Override
	public abstract void paint(Graphics g);
}
