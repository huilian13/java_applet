package kevin.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import kevin.config.GameConfig;
import kevin.entity.GameRect;

/**
 * ��Ϸ��
 * 
 * @author kevin
 *
 */
public class GameLayer extends Layer {

	/**
	 * ������ƫ����
	 */
	private final int RECT_SIZE = GameConfig.getFrameConfig().getRectSize();

	/**
	 * ����ͼƬ��ĩβid
	 */
	private final int LOSE_INDEX=GameConfig.getFrameConfig().getLoseIndex();
	
	public GameLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g) {
		// ���Ʋ㴰��
		this.createWindow(g);
		//��ȡ�������
		GameRect gameRect = this.gameDto.getGameRect();
		//�жϷ�������Ƿ�Ϊ��
		if(gameRect==null){
			return;
		}
		// ��ȡ��������
		Point[] rectPoint = gameRect.getRectPoint();
		// ������Ӱ
		this.drawShadow(rectPoint, g);
		// ������Ϸ�����
		this.drawGameRect(rectPoint, g);
		// ���Ƶ�ͼ
		this.drawGameMap(g);
		
		//������ͣͼ
		if(this.gameDto.isPause()){
			//��ȡͼƬ
			Image pauseImg = GameImage.PAUSE;
			//��ȡ����
			int x=this.width-pauseImg.getWidth(null)>>1;
			int y=this.height-pauseImg.getHeight(null)>>1;
			//���л���
			g.drawImage(pauseImg,this.x+x,this.y+y,null);
		}

	}

	/**
	 * ������Ӱ
	 * @param rectPoint �����������
	 * @param isShutDown true��������Ӱ
	 * @param g ���ʶ���
	 */
	private void drawShadow(Point[] rectPoint, Graphics g) {
		//�ر���Ӱ
		if(!this.gameDto.isShadow()){
			return;
		}
		//��߽�
		int leftX = rectPoint[0].x;
		//�ұ߽�
		int rightX = rectPoint[0].x;
		for (Point p : rectPoint) {
            leftX=leftX<p.x?leftX:p.x;
            rightX=rightX>p.x?rightX:p.x;
		}
		//������Ӱ
		g.drawImage(GameImage.SHADOW,
				    this.x+BORDER_SIZE+(leftX<<RECT_SIZE),
				    this.y+BORDER_SIZE, 
				    rightX-leftX+1<<RECT_SIZE,
				    this.height-(BORDER_SIZE<<1), null);
	}

	/**
	 * ������Ϸ����
	 * @param rectPoint �����������
	 * @param g ���ʶ���
	 */
	private void drawGameRect(Point[] rectPoint, Graphics g) {
		// ��ȡ������
		int rectCode = this.gameDto.getGameRect().getRectCode();
		// ���Ʒ���
		for (int i = 0, length = rectPoint.length; i < length; i++) {
			this.drawRectByCode(rectPoint[i].x, rectPoint[i].y, rectCode + 1, g);
		}
	}

	/**
	 * ������Ϸ��ͼ
	 * @param g
	 */
	private void drawGameMap(Graphics g) {
		// ���Ƶ�ͼ
		boolean[][] gameMap = this.gameDto.getGameMap();
		// ��ȡ��Ϸ�ȼ�
		int lv = this.gameDto.getRank();
		// ��ȡ����id
		int imgIndex = lv == 0 ? 0 : (lv - 1) % 7 + 1;
		//���õ�ͼ��ɫ
		for (int x = 0, length = gameMap.length; x < length; x++) {
			for (int y = 0, len = gameMap[x].length; y < len; y++) {
				if (gameMap[x][y]) {
					// ���Ʒ���
					this.drawRectByCode(y, x, imgIndex, g);
				}
			}
		}
	}

	/**
	 * ���Ʒ���
	 * @param x �����x����
	 * @param y �����y����
	 * @param imgId �����ID
	 * @param g ���ʶ���
	 */
	private void drawRectByCode(int x, int y, int imgId, Graphics g) {
		//��ȡ����ͼƬ��id
		imgId=this.gameDto.isGameStart()?imgId:LOSE_INDEX;
		// ���Ʒ���
		g.drawImage(GameImage.RECT, this.x + (x << RECT_SIZE) + BORDER_SIZE, 
				this.y + (y << RECT_SIZE) + BORDER_SIZE,
				this.x + (x + 1 << RECT_SIZE) + BORDER_SIZE, 
				this.y + (y + 1 << RECT_SIZE) + BORDER_SIZE, 
				imgId << RECT_SIZE, 0,(imgId + 1) << RECT_SIZE, 1 << RECT_SIZE, null);
	}
	
}
