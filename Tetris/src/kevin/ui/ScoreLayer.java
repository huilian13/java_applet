package kevin.ui;

import java.awt.Graphics;

import kevin.config.GameConfig;
/**
 * ��Ϸ�÷ֲ㴰��
 * @author kevin
 *
 */
public class ScoreLayer extends Layer{
	/**
	 * ������ʾ��x����
	 */
	private final int COM_X;
	
	/**
	 * ��ʼx����
	 */
	private final int START_X;
	
	/**
	 * ���������y����
	 */
	private final int SCORE_Y;
	
	/**
	 * ���б����y����
	 */
	private final int RMLINE_Y;
	
	/**
	 * ֵ�۵�y����
	 */
	private final int EXP_Y;
	
	/**
	 * ���ֵ����λ��
	 */
	private static final int NUM_BIT=5;
	
	/**
	 * ���������
	 */
	private static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();
	
	public ScoreLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		//��ʼ��������ʾ��x����
		COM_X=this.width-NUM_W*NUM_BIT-PADDING;
		//��ȡ����ĸ߶�
		int imgH=GameImage.SCORE.getHeight(null);
		//��ʼ����ʼ��x����
		START_X=this.x+PADDING;
		//��ʼ�������������y����
		SCORE_Y=PADDING;
		//��ʼ��������ʾ��y����
		RMLINE_Y=imgH+(PADDING<<1);
		//��ʼ��ֵ�۵�y����
		EXP_Y=this.y+RMLINE_Y+imgH+PADDING;
		
	}

	@Override
	public void paint(Graphics g) {
		//���Ʋ㴰��
		this.createWindow(g);
		//���Ʊ��������
		this.drawTitleAndNum(g);
		//��ȡ������
		int rmline=this.gameDto.getNewRmline();
		//����ֵ��
		this.drawSlot(EXP_Y,"��һ��",null,(double)(rmline%LEVEL_UP)/(double)LEVEL_UP,g);
	}
	
	/**
	 * ���Ʊ��������
	 * @param g ���ʶ���
	 */
	private void drawTitleAndNum(Graphics g){
		//���ƴ��ڱ��⣨������
		g.drawImage(GameImage.SCORE, START_X, this.y+SCORE_Y,null);
		//���Ʒ���
		this.drawNumberLeftPad(COM_X,SCORE_Y,this.gameDto.getNewScore(),NUM_BIT, g);
		//���ƴ��ڱ��⣨���У�
		g.drawImage(GameImage.RMLINE,START_X, this.y+RMLINE_Y,null);
		//����������Ŀ
		this.drawNumberLeftPad(COM_X, RMLINE_Y,this.gameDto.getNewRmline(), NUM_BIT, g);
	}
	
}
