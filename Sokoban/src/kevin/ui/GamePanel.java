package kevin.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import kevin.config.GameConfig;
import kevin.control.GameControl;
import kevin.control.PlayerControl;
import kevin.dto.GameDto;
import kevin.entity.Coop;
import kevin.entity.SheepBox;
/**
 * ��Ϸ���
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto gameDto;
	
	/**
	 * ͼƬ�ߴ�
	 */
	private final int SIZE=GameConfig.getFrameConfig().getSize();
	
	public GamePanel(GameDto gameDto,GameControl gameControl) {
		//��ʼ����Ϸ����Դ
		this.gameDto=gameDto;
		//ע������¼�������
		this.addKeyListener(new PlayerControl(gameControl));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//������Ϸ����
		g.drawImage(GameImage.BG_IMG,0,0,null);
		//��������(��̫��)
		g.drawImage(this.gameDto.getWolfImage(),this.gameDto.getWolf().getX()*SIZE,this.gameDto.getWolf().getY()*SIZE,null);
		//�������ӣ���
		List<SheepBox> boxList = this.gameDto.getBoxList();
		for(SheepBox box:boxList){
			g.drawImage(GameImage.BOX,box.getX()*SIZE,box.getY()*SIZE,null);
		}
		//�����ϰ������
		int[][] gameMap = this.gameDto.getGameMap();
		for(int i=0;i<gameMap.length;i++){
			for(int j=0;j<gameMap[i].length;j++){
				if(gameMap[i][j]==1){
					g.drawImage(GameImage.BLOCK,j*SIZE,i*SIZE,null);
				}
			}
		}
		//��������
		List<Coop> coopList = this.gameDto.getCoopList();
		for(Coop coop:coopList){
			g.drawImage(GameImage.TARGET,coop.getX()*SIZE,coop.getY()*SIZE,null);
		}
		//��ӹرհ�ť
		this.addButton(new JButton(GameImage.BTN_IMG));
		//���ؿ��ƽ���
		this.requestFocus();
	}
	
	/**
	 * �����갴ť
	 */
	private final void addButton(JButton button){
		//������걳����ɫ
		button.setBackground(Color.white);
		//�������¼�
		button.addMouseListener(new ButtonAdapter());
		//���ð�ťλ��
		button.setBounds(750,0,60,20);
		//����ť��ӵ����
		this.add(button);
		
	}
}
