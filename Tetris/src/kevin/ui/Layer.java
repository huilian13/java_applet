package kevin.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import kevin.config.GameConfig;
import kevin.dto.GameDto;
/**
 * �㴰����
 * @author kevin
 *
 */
public abstract class Layer {

	/**
	 * ����x����
	 */
	protected final int x;
	
	/**
	 * ����y����
	 */
	protected final int y;
	
	/**
	 * ���ڿ��
	 */
	protected final int width;
	
	/**
	 * ���ڸ߶�
	 */
	protected final int height;

	/**
	 * ֵ�۵�����
	 */
	private final int EXP_W;
	
	/**
	 * ��Ϸ����Դ
	 */
	protected GameDto gameDto;

	/**
	 * ����ֵ�۸߶�
	 */
	protected static final int RECT_EXP_H=GameImage.RECT_EXP.getHeight(null);
	
	/**
	 * ����ֵ�ۿ��
	 */
	protected static final int RECT_EXP_W=GameImage.RECT_EXP.getWidth(null);	
	
	/**
	 * �㴰�ڳߴ�
	 */
	protected static final int BORDER_SIZE=GameConfig.getFrameConfig().getSize();
	
	/**
	 * �ڱ߾�
	 */
	protected static final int PADDING=GameConfig.getFrameConfig().getPadding();
	
	/**
	 * �㴰��
	 */
	private static final Image WINDOW_IMAGE=GameImage.WINDOW;
	
	/**
	 * �㴰�ڵĿ��
	 */
	private static final int IMG_W=WINDOW_IMAGE.getWidth(null);
	 
	/**
	 * �㴰�ڵĸ߶�
	 */
	private static final int IMG_H=WINDOW_IMAGE.getHeight(null);
	
	/**
	 * ��ȡͼƬ�����ֵĿ��
	 */
	protected static final int NUM_W=GameImage.NUMBERS.getWidth(null)/10;
	
	/**
	 * ����ͼƬ�ĸ߶�
	 */
    protected static final int NUM_H=GameImage.NUMBERS.getHeight(null);
	
	protected Layer(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//��ʼ��ֵ�۵�����
		EXP_W=this.width-(PADDING<<1);
	}
	
	/**
	 * ���ƴ���
	 * @param g ���ʶ���
	 */
	protected void createWindow(Graphics g){
		//���ϲ���
		g.drawImage(WINDOW_IMAGE,this.x,this.y,this.x+BORDER_SIZE,this.y+BORDER_SIZE,0,0,BORDER_SIZE,BORDER_SIZE,null);
		//���ϲ���
		g.drawImage(WINDOW_IMAGE,this.x+BORDER_SIZE,this.y,this.x-BORDER_SIZE+width,this.y+BORDER_SIZE,BORDER_SIZE,0,IMG_W-BORDER_SIZE,BORDER_SIZE,null);
		//���ϲ���
		g.drawImage(WINDOW_IMAGE,this.x-BORDER_SIZE+width,this.y,this.x+width,this.y+BORDER_SIZE,IMG_W-BORDER_SIZE,0,IMG_W,BORDER_SIZE,null);
		//���в���
		g.drawImage(WINDOW_IMAGE,this.x,this.y+BORDER_SIZE,this.x+BORDER_SIZE,this.y-BORDER_SIZE+height,0,BORDER_SIZE,BORDER_SIZE,IMG_H-BORDER_SIZE,null);
		//�в�
		g.drawImage(WINDOW_IMAGE,this.x+BORDER_SIZE,this.y+BORDER_SIZE,this.x-BORDER_SIZE+width,this.y-BORDER_SIZE+height,BORDER_SIZE,BORDER_SIZE,IMG_W-BORDER_SIZE,IMG_H-BORDER_SIZE,null);
		//���в���
		g.drawImage(WINDOW_IMAGE,this.x-BORDER_SIZE+width,this.y+BORDER_SIZE,this.x+width,this.y-BORDER_SIZE+height,IMG_W-BORDER_SIZE,BORDER_SIZE,IMG_W,IMG_H-BORDER_SIZE,null);
		//���²���
		g.drawImage(WINDOW_IMAGE,this.x,this.y-BORDER_SIZE+height,this.x+BORDER_SIZE,this.y+height,0,IMG_H-BORDER_SIZE,BORDER_SIZE,IMG_H,null);
		//���²���
		g.drawImage(WINDOW_IMAGE,this.x+BORDER_SIZE,this.y-BORDER_SIZE+height,this.x-BORDER_SIZE+width,this.y+height, BORDER_SIZE,IMG_H-BORDER_SIZE,IMG_W-BORDER_SIZE,IMG_H,null);
		//���²���
		g.drawImage(WINDOW_IMAGE, this.x-BORDER_SIZE+width,this.y-BORDER_SIZE+height,this.x+width,this.y+height,IMG_W-BORDER_SIZE,IMG_H-BORDER_SIZE,IMG_W,IMG_H,null);
	}
	
	
	/**
     * ���������
     * @param x ���Ͻ�x����
     * @param y ���Ͻ�y����
     * @param num ��ʾ������
     * @param bitCount ����λ��
     * @param g ���ʶ���
     */
	protected void drawNumberLeftPad(int x,int y,int num,int bitCount,Graphics g){
		//������ת��Ϊ�ַ�����
		String number = Integer.toString(num);
		//������ӡ����
		for(int i=0;i<bitCount;i++){
			if(bitCount-i<=number.length()){
				//��ȡ�ַ����±�
				int index=i+number.length()-bitCount;
				//���ַ���Ϊ����
				int bit = number.charAt(index)-'0';
				//��������
				g.drawImage(GameImage.NUMBERS, 
						this.x+x+NUM_W*i, 
						this.y+y,
						this.x+x+NUM_W*(i+1), 
						this.y+y+NUM_H, 
						bit*NUM_W, 0, (bit+1)*NUM_W,NUM_H,null);
			}
			
		}
	}
	

	/**
	 * ����ֵ��
	 * @param g ���ʶ���
	 */
	protected void drawSlot(int expY,String title,String numbers,double percent,Graphics g){
		//��ʼ����ʼ��x����
		int startX=this.x+PADDING;
		//����ֵ�����
		g.setColor(Color.black);
		g.fill3DRect(startX,expY,EXP_W,RECT_EXP_H+4, true);
		//����ֵ���в�
		g.setColor(Color.white);
		g.fill3DRect(startX+1,expY+1,EXP_W-2,RECT_EXP_H+2,true);
		//����ֵ���ڲ�
		g.setColor(Color.black);
		g.fill3DRect(startX+2,expY+2,EXP_W-4,RECT_EXP_H,true);
		//��ȡ���
		int w=(int)(percent*(EXP_W-4));
		//��ȡ��ɫx����
		int colorX=(int)((percent*RECT_EXP_W-1));
		//����ֵ��
		g.drawImage(GameImage.RECT_EXP, 
				startX+2, expY+2, 
				startX+2+w, expY+2+RECT_EXP_H, 
			     colorX,0,colorX+1,RECT_EXP_H,null);
		
		//���Ʊ���
		g.setColor(Color.white);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString(title,startX,expY+23);
		//��������
		if(numbers!=null){
			//��������
			for(int i=0;i<5;i++){
				if(5-i<=numbers.length()){
					int index=i+numbers.length()-5;
					String num=numbers.substring(index,index+1);
					g.drawString(num,startX+240+(10*i), expY+23);
				}
			}
		}
	}
	
	/**
	 * ������Ϸ����Դ
	 * @param gameDto
	 */
	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}

	/**
	 * ˢ�²㴰��
	 * @author kevin
	 * @param g ���ʶ���
	 */
	public abstract void paint(Graphics g);
}
