package kevin.ui;

import java.awt.Graphics;
import java.awt.Image;

/**
 * ��Ҳ㴰��
 * @author kevin
 *
 */
public class AboutLayer extends Layer{

	public AboutLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void paint(Graphics g) {
		//���Ʋ㴰��
		this.createWindow(g);
		//��ȡͼƬ
		Image img=GameImage.NAME;
		int centerX=this.width-img.getWidth(null)>>1;
		int centerY=this.height-img.getHeight(null)>>1;
		//�����������
		g.drawImage(img,this.x+centerX,this.y+centerY, null);
	}

}
