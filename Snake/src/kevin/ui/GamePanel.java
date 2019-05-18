	package kevin.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

import kevin.config.GameConfig;
import kevin.control.GameControl;
import kevin.control.PlayerControl;
import kevin.dto.GameDto;
import kevin.entity.Food;
import kevin.factory.GameFactory;

/**
 * ��Ϸ�����
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	/**
	 * ����ߴ�
	 */
	private static final int SIZE=GameConfig.getFrameConfig().getSize();
	
	/**
	 * ��ť�Ŀ��
	 */
	private static final int BUTTON_W=GameFactory.getButtonConfig().getWidth();
	
	/**
	 * ��ť�ĸ߶�
	 */
	private static final int BUTTON_H=GameFactory.getButtonConfig().getHeight();
	
	/**
	 * ��ʼ��ť
	 */
	private JButton startButton;
	
	/**
	 * ���ð�ť
	 */
	private JButton configButton;
	
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto gameDto;
	
	/**
	 * ��Ϸ������
	 */
	private GameControl gameControl;

	public GamePanel(GameDto gameDto,GameControl gameControl) {
		//�������ɲ���(���Ĭ������ʽ����)
		this.setLayout(null);
		//��ʼ������Դ
		this.gameDto=gameDto;
		//��ʼ����Ϸ������
		this.gameControl=gameControl;
		//��Ӽ��̼���
		this.addKeyListener(new PlayerControl(gameControl));
		//��ʼ����ť
		this.initButton();
	}
	
	/**
	 * ��ʼ����ť
	 */
	private void initButton(){
		//��ʼ����ʼ��ť
		this.startButton=new JButton(GameImage.START_BUTTON);
		//���ÿ�ʼ��ťλ��
		this.startButton.setBounds(GameFactory.getButtonConfig().getStartX(),
				                   GameFactory.getButtonConfig().getStartY(),
				                   BUTTON_W,BUTTON_H);
		//��ӵ����
		this.add(startButton);
		
		//����¼�����
		this.startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ʼ��Ϸ
				gameControl.startGame();
				//���ؽ���
				requestFocus();
			}
		});
		
		//��ʼ�����ð�ť
		this.configButton=new JButton(GameImage.CONFIG_BUTTON);
		//�������ð�ť��λ��
		this.configButton.setBounds(GameFactory.getButtonConfig().getConfigX(),
				                    GameFactory.getButtonConfig().getConfigY(),
				                    BUTTON_W,BUTTON_H);
		//��ӵ����
		this.add(configButton);
		
		//����¼�����
		this.configButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ʾ��Ϸ���ô���
				gameControl.showPlayerConfig();
			}
		});
	}
	
	/**
	 * ��ťѡ��
	 * @param off true�����ܵ��
	 */
	public void buttonSwitch(boolean off) {
		this.startButton.setEnabled(off);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//���û��෽��
		super.paintComponent(g);
		//���Ʊ���
		this.drawBackGround(g);
		//����ש��(�ϰ���)
		this.drawRectBrick(g);
		//�����Ϸ��ʼ���ͻ����ߺ�ʳ��
		if(this.gameDto.getSnake()!=null){
			//������
			this.drawSnake(g);
			//����ʳ��
			this.drawFood(g);
			//��Ϸ����ʱ��������ĸ
			if(!this.gameDto.isStart()){
				this.drawGameOver(g);
			}
		}
		//��Ϸ��ͣʱ����
		if(this.gameDto.isPause()){
			//��ȡͼƬ
			Image pauseImage = GameImage.PAUSE;
			//��������
			int x=this.getWidth()-pauseImage.getWidth(null)>>1;
			int y=this.getHeight()-pauseImage.getHeight(null)>>1;
			//����ͼƬ
			g.drawImage(pauseImage,x, y,null);
		}
		
		//���Ƶ÷�
		g.setColor(Color.gray);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString("�÷֣�"+this.gameDto.getSorce(),600,640);
	}

	/**
	 * ���Ʊ���
	 * @param g ���ʶ���
	 */
	private void drawBackGround(Graphics g){
		//���Ʊ���
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0,GameDto.MAP_ROW*SIZE,GameDto.MAP_COL*SIZE);
	}
	
	/**
	 * ����ש��
	 */
	private void drawRectBrick(Graphics g){
		//��ȡ��Ϸ��ͼ
		boolean[][] gameMap = this.gameDto.getGameMap();
		//����ש��
		for(int x=0;x<gameMap.length;x++){
			for(int y=0;y<gameMap[x].length;y++){
				if(gameMap[x][y]){
					g.setColor(Color.gray);
					g.fill3DRect(x*SIZE,y*SIZE,SIZE,SIZE,true);
				}
			}
		}
	}
	
	/**
	 * ��������
	 * @param g
	 */
	private void drawSnake(Graphics g) {
		//��ȡ�߽ڵ�
		LinkedList<Point> snakeList = this.gameDto.getSnake().getSnakeList();
		//��������ڵ����
		Point body;
		//���û�����ɫ
		g.setColor(Color.yellow);
		//������
		for(int i=1;i<snakeList.size();i++){
			body= snakeList.get(i);
			g.fill3DRect(body.x*SIZE, body.y*SIZE,SIZE,SIZE,true);
		}
		//��ȡ��ͷ�ڵ�
		Point head= snakeList.getFirst();
		//����ͷ
		g.setColor(Color.cyan);
		g.fill3DRect(head.x*SIZE, head.y*SIZE,SIZE,SIZE,true);
		
	}
	
	/**
	 * ����ʳ��
	 * @param g
	 */
	private void drawFood(Graphics g){
		//��ȡʳ�����
		Food food = this.gameDto.getFood();
		//��ȡʳ������
		Point point = food.getfoodPoint();
		//����ʳ��
		g.setColor(Color.green);
		g.fill3DRect(point.x*SIZE, point.y*SIZE,SIZE,SIZE,true);
	}
	
	/**
	 * ����gameOver
	 * @param g
	 */
	private void drawGameOver(Graphics g){
		g.setColor(Color.gray);
		//���������ʽ
		g.setFont(new Font("����",Font.BOLD,100));
		//��������
		g.drawString("GameOver",256,300);
	}
}
