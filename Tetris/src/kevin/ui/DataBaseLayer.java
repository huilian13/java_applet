package kevin.ui;

import java.awt.Graphics;

public class DataBaseLayer extends DataLayer{

	public DataBaseLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void paint(Graphics g) {
		//���Ʋ㴰��
		this.createWindow(g);
		//��������ֵ��
		this.showData(GameImage.DATA,this.gameDto.getDbRecode(), g);
	
	}
	

}
