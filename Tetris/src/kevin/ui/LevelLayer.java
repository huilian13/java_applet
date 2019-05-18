package kevin.ui;

import java.awt.Graphics;
/**
 * ��Ϸ�ȼ���
 * @author kevin
 *
 */
public class LevelLayer extends Layer{
	
	/**
	 * ���ֵ�x����
	 */
	private final int NUM_X;
	
	/**
	 * ���ֵ�y����
	 */
	private final int NUM_Y;

	public LevelLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		//��ʼ�����ֵ�x����
		NUM_X=64;
		//��ʼ�����ֵ�y����
		NUM_Y=GameImage.RANK.getHeight(null)+(PADDING<<1);
				
		
	}

	@Override
	public void paint(Graphics g) {
		//���Ʋ㴰��
		this.createWindow(g);
		//��������ͼƬ
		g.drawImage(GameImage.RANK, this.x+PADDING, this.y+PADDING,null);
		//��������
		this.drawNumberLeftPad(NUM_X,NUM_Y,this.gameDto.getRank(),3, g);
	}
	
    
}
