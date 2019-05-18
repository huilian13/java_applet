package kevin.ui;

import java.awt.Graphics;

public class DiskLayer extends DataLayer{

	public DiskLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void paint(Graphics g) {
		//绘制层窗口
		this.createWindow(g);
		//绘制数据值槽
		this.showData(GameImage.DISK, this.gameDto.getDiskRecode(), g);
	}

	
}
