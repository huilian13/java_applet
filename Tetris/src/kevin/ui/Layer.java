package kevin.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import kevin.config.GameConfig;
import kevin.dto.GameDto;
/**
 * 层窗口类
 * @author kevin
 *
 */
public abstract class Layer {

	/**
	 * 窗口x坐标
	 */
	protected final int x;
	
	/**
	 * 窗口y坐标
	 */
	protected final int y;
	
	/**
	 * 窗口宽度
	 */
	protected final int width;
	
	/**
	 * 窗口高度
	 */
	protected final int height;

	/**
	 * 值槽的外宽度
	 */
	private final int EXP_W;
	
	/**
	 * 游戏数据源
	 */
	protected GameDto gameDto;

	/**
	 * 矩形值槽高度
	 */
	protected static final int RECT_EXP_H=GameImage.RECT_EXP.getHeight(null);
	
	/**
	 * 矩形值槽宽度
	 */
	protected static final int RECT_EXP_W=GameImage.RECT_EXP.getWidth(null);	
	
	/**
	 * 层窗口尺寸
	 */
	protected static final int BORDER_SIZE=GameConfig.getFrameConfig().getSize();
	
	/**
	 * 内边距
	 */
	protected static final int PADDING=GameConfig.getFrameConfig().getPadding();
	
	/**
	 * 层窗口
	 */
	private static final Image WINDOW_IMAGE=GameImage.WINDOW;
	
	/**
	 * 层窗口的宽度
	 */
	private static final int IMG_W=WINDOW_IMAGE.getWidth(null);
	 
	/**
	 * 层窗口的高度
	 */
	private static final int IMG_H=WINDOW_IMAGE.getHeight(null);
	
	/**
	 * 获取图片中数字的宽度
	 */
	protected static final int NUM_W=GameImage.NUMBERS.getWidth(null)/10;
	
	/**
	 * 数字图片的高度
	 */
    protected static final int NUM_H=GameImage.NUMBERS.getHeight(null);
	
	protected Layer(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//初始化值槽的外宽度
		EXP_W=this.width-(PADDING<<1);
	}
	
	/**
	 * 绘制窗口
	 * @param g 画笔对象
	 */
	protected void createWindow(Graphics g){
		//左上部分
		g.drawImage(WINDOW_IMAGE,this.x,this.y,this.x+BORDER_SIZE,this.y+BORDER_SIZE,0,0,BORDER_SIZE,BORDER_SIZE,null);
		//中上部分
		g.drawImage(WINDOW_IMAGE,this.x+BORDER_SIZE,this.y,this.x-BORDER_SIZE+width,this.y+BORDER_SIZE,BORDER_SIZE,0,IMG_W-BORDER_SIZE,BORDER_SIZE,null);
		//右上部分
		g.drawImage(WINDOW_IMAGE,this.x-BORDER_SIZE+width,this.y,this.x+width,this.y+BORDER_SIZE,IMG_W-BORDER_SIZE,0,IMG_W,BORDER_SIZE,null);
		//左中部分
		g.drawImage(WINDOW_IMAGE,this.x,this.y+BORDER_SIZE,this.x+BORDER_SIZE,this.y-BORDER_SIZE+height,0,BORDER_SIZE,BORDER_SIZE,IMG_H-BORDER_SIZE,null);
		//中部
		g.drawImage(WINDOW_IMAGE,this.x+BORDER_SIZE,this.y+BORDER_SIZE,this.x-BORDER_SIZE+width,this.y-BORDER_SIZE+height,BORDER_SIZE,BORDER_SIZE,IMG_W-BORDER_SIZE,IMG_H-BORDER_SIZE,null);
		//右中部分
		g.drawImage(WINDOW_IMAGE,this.x-BORDER_SIZE+width,this.y+BORDER_SIZE,this.x+width,this.y-BORDER_SIZE+height,IMG_W-BORDER_SIZE,BORDER_SIZE,IMG_W,IMG_H-BORDER_SIZE,null);
		//左下部分
		g.drawImage(WINDOW_IMAGE,this.x,this.y-BORDER_SIZE+height,this.x+BORDER_SIZE,this.y+height,0,IMG_H-BORDER_SIZE,BORDER_SIZE,IMG_H,null);
		//中下部分
		g.drawImage(WINDOW_IMAGE,this.x+BORDER_SIZE,this.y-BORDER_SIZE+height,this.x-BORDER_SIZE+width,this.y+height, BORDER_SIZE,IMG_H-BORDER_SIZE,IMG_W-BORDER_SIZE,IMG_H,null);
		//右下部分
		g.drawImage(WINDOW_IMAGE, this.x-BORDER_SIZE+width,this.y-BORDER_SIZE+height,this.x+width,this.y+height,IMG_W-BORDER_SIZE,IMG_H-BORDER_SIZE,IMG_W,IMG_H,null);
	}
	
	
	/**
     * 左填充数字
     * @param x 左上角x坐标
     * @param y 左上角y坐标
     * @param num 显示的数字
     * @param bitCount 数字位数
     * @param g 画笔对象
     */
	protected void drawNumberLeftPad(int x,int y,int num,int bitCount,Graphics g){
		//将整型转换为字符串型
		String number = Integer.toString(num);
		//左填充打印数字
		for(int i=0;i<bitCount;i++){
			if(bitCount-i<=number.length()){
				//获取字符串下标
				int index=i+number.length()-bitCount;
				//将字符变为整型
				int bit = number.charAt(index)-'0';
				//绘制数字
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
	 * 绘制值槽
	 * @param g 画笔对象
	 */
	protected void drawSlot(int expY,String title,String numbers,double percent,Graphics g){
		//初始化起始的x坐标
		int startX=this.x+PADDING;
		//绘制值槽外层
		g.setColor(Color.black);
		g.fill3DRect(startX,expY,EXP_W,RECT_EXP_H+4, true);
		//绘制值槽中部
		g.setColor(Color.white);
		g.fill3DRect(startX+1,expY+1,EXP_W-2,RECT_EXP_H+2,true);
		//绘制值槽内层
		g.setColor(Color.black);
		g.fill3DRect(startX+2,expY+2,EXP_W-4,RECT_EXP_H,true);
		//获取宽度
		int w=(int)(percent*(EXP_W-4));
		//获取颜色x坐标
		int colorX=(int)((percent*RECT_EXP_W-1));
		//绘制值槽
		g.drawImage(GameImage.RECT_EXP, 
				startX+2, expY+2, 
				startX+2+w, expY+2+RECT_EXP_H, 
			     colorX,0,colorX+1,RECT_EXP_H,null);
		
		//绘制标题
		g.setColor(Color.white);
		g.setFont(new Font("黑体",Font.BOLD,20));
		g.drawString(title,startX,expY+23);
		//绘制数字
		if(numbers!=null){
			//左填充对齐
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
	 * 设置游戏数据源
	 * @param gameDto
	 */
	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}

	/**
	 * 刷新层窗口
	 * @author kevin
	 * @param g 画笔对象
	 */
	public abstract void paint(Graphics g);
}
