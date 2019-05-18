package kevin.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import kevin.config.GameConfig;
import kevin.config.LayerConfig;
import kevin.control.GameControl;
import kevin.control.PlayerControl;
import kevin.dto.GameDto;
import kevin.ui.GameImage;
import kevin.ui.Layer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	/**
	 * ��ť�Ŀ�
	 */
	private static final int BUTTON_W=GameConfig.getFrameConfig().getButtonConfig().getWidth();
	
	/**
	 * ��ť�ĸ�
	 */
	private static final int BUTTON_H=GameConfig.getFrameConfig().getButtonConfig().getHeight();

	/**
	 * �㴰�ڼ���
	 */
	private List<Layer> layers=null;
	
	/**
	 * ��Ϸ���ƶ���
	 */
	private GameControl gameControl;
	
	/**
	 * ��ʼ��ť
	 */
	private JButton startButton;
	
	/**
	 * ���ð�ť
	 */
	private JButton settingButton;
	
	public GamePanel(GameDto gameDto,GameControl gameControl){
		//���ò��ֹ�����Ϊ���ɲ���
		this.setLayout(null);
		//��ʼ����Ϸ���ƶ���
		this.gameControl=gameControl;
		//��ʼ���㴰��
		this.initLayer(gameDto);
		//��ʼ���������
		this.initAction();
		//��ʼ����ť
		this.initButton();
	}
	
	/**
	 * ��ʼ����ť
	 */
	private void initButton(){
		//��ʼ����ʼ��ť
		this.startButton=new JButton(GameImage.START_BTN);
		//���ÿ�ʼ��ť��λ��
		this.startButton.setBounds(GameConfig.getFrameConfig().getButtonConfig().getStartX(),
				                   GameConfig.getFrameConfig().getButtonConfig().getStartY(), 
				                   BUTTON_W,BUTTON_H);
		//��ӵ����
		this.add(this.startButton);
		//��ʼ�����ð�ť
		this.settingButton=new JButton(GameImage.SETTING_BTN);
		//���ð�ťλ��
		this.settingButton.setBounds(GameConfig.getFrameConfig().getButtonConfig().getSettingX(),
				                     GameConfig.getFrameConfig().getButtonConfig().getSettingY(), 
				                     BUTTON_W,BUTTON_H);
		//��ӵ����
		this.add(settingButton);
		
		//��ӡ���ʼ��ť�����¼�����
		this.startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ������Ϸ
				gameControl.startGame();
				requestFocus();
			}
		});
		
		//��ӡ����ð�ť�����¼�����
		this.settingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ʾ���ÿ�
				gameControl.showPlayerConfig();
			}
		});
	}
	
	/**
	 * ��ʼ���������
	 */
	private void initAction(){
		//����¼�����
		this.addKeyListener(new PlayerControl(this.gameControl));
	}
	
	/**
	 * ���ư�ť�Ƿ���Ե��
	 * @param off
	 */
    public void buttonSwitch(boolean off){
		this.startButton.setEnabled(off);
	}
    
	/**
	 * ��ʼ���㴰��
	 */
	private void initLayer(GameDto gameDto){
		try {
			//��ȡ���в㴰�����ö���
			List<LayerConfig> layerConfigs = GameConfig.getFrameConfig().getLayerConfigs();
			//��ʼ���㴰�ڼ���
			this.layers=new ArrayList<Layer>(layerConfigs.size());
			//�����㴰�����ö���
			LayerConfig layerConfig=null;
			//���������
			Layer layer=null;
			//�������в㴰�ڶ���
			for(int i=0,length=layerConfigs.size();i<length;i++){
				//��ȡ�����㴰�����ö���
				layerConfig = layerConfigs.get(i);
				//��ȡ����ֽ���
			    Class<?> clazz = Class.forName(layerConfig.getClassName());	
			    //��ȡ��Ĺ��캯��
			    Constructor<?> constructor = clazz.getConstructor(int.class,int.class,int.class,int.class);
			    //�������
			    layer=(Layer)constructor.newInstance(layerConfig.getX(),layerConfig.getY(),layerConfig.getWidth(),layerConfig.getHeight());
			    //�ڲ������������Ϸ����Դ
			    layer.setGameDto(gameDto);
			    //��������ӵ�����
			    this.layers.add(layer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//���û��෽��
		super.paintComponent(g);
		//ѭ��ˢ����Ϸ����
    	for(int i=0,length=this.layers.size();i<length;this.layers.get(i++).paint(g));
    	
	}
}
