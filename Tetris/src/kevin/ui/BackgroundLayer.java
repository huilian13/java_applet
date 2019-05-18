package kevin.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import kevin.config.GameConfig;

public class BackgroundLayer extends Layer{
	
	 
	public BackgroundLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void paint(Graphics g) {
		// ±³¾°Í¼Æ¬¼¯ºÏ
		List<Image> bgImages=GameImage.BG_IMG;
		//È¡Ä££¬Ñ­»·¸ü»»±³¾°
		int index=this.gameDto.getRank()%bgImages.size();
		//»æÖÆ±³¾°Í¼
		g.drawImage(bgImages.get(index),0,0,GameConfig.getFrameConfig().getWidth(),GameConfig.getFrameConfig().getHeight(),null);
	}

}
