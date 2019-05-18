package kevin.ui;

import java.awt.Graphics;
import java.awt.Image;
/**
 * ������ʾ��
 * @author kevin
 *
 */
public class RemindLayer extends Layer{

	public RemindLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void paint(Graphics g) {
		//���Ʋ㴰��
		this.createWindow(g);
		//��ȡ��ʾ�����id
		int remind = this.gameDto.getRemind();
		//��ȡ����ͼƬ
		Image image = GameImage.NEXT_RECT[remind];
		//��Ϸ��ʼʱ������ʾ����
		if(this.gameDto.isGameStart()){
			//���л�����ʾ����
			this.drawImageAtCenter(image, g);
		}
	}
	
	/**
	 * ���л��Ʒ���
	 * @param image ͼƬ����
	 * @param g ���ʶ���
	 */
	private void drawImageAtCenter(Image image,Graphics g){
		//ͼƬx����
		int x=this.x+(this.width-image.getWidth(null)>>1);
		//ͼƬy����
		int y=this.y+(this.height-image.getHeight(null)>>1);
		//����ͼƬ
		g.drawImage(image,x,y, null);
	}

}
