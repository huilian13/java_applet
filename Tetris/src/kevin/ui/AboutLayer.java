package kevin.ui;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 玩家层窗口
 * @author kevin
 *
 */
public class AboutLayer extends Layer{

	public AboutLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void paint(Graphics g) {
		//绘制层窗口
		this.createWindow(g);
		//获取图片
		Image img=GameImage.NAME;
		int centerX=this.width-img.getWidth(null)>>1;
		int centerY=this.height-img.getHeight(null)>>1;
		//绘制玩家名称
		g.drawImage(img,this.x+centerX,this.y+centerY, null);
	}

}
