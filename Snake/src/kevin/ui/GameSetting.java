package kevin.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import kevin.config.GameConfig;
import kevin.control.GameControl;
import kevin.util.FrameUtil;

/**
 * ��Ϸ���ô���
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GameSetting extends JFrame{
	/**
	 * ��Ϸ������
	 */
	private GameControl gameControl;

	/**
	 * ȷ����ť
	 */
	private JButton btnOK=new JButton("ȷ��");
	
	/**
	 * Ӧ�ð�ť
	 */
	private JButton btnApply=new JButton("Ӧ��");
	
	/**
	 * ȡ����ť
	 */
	private JButton btnCancel=new JButton("ȡ��");
	
	/**
	 * ���Ѷ����á���ǩ
	 */
	private JLabel rankLabel=new JLabel("�Ѷ�����:");
	
	/**
	 * ����������á���ǩ
	 */
	private JLabel controLabel=new JLabel("���������:");
	
	/**
	 * ������ʾ��ǩ
	 */
	private JLabel errorMessage=new JLabel();
	
	/**
	 * ��ǩ����
	 */
	private TextControl[] textControls=new TextControl[5];
	
	/**
	 * ������
	 */
	private static final String[] METHOD_NAME={"keyUp","keyDown","keyLeft","keyRight","keyFun"};
	
	/**
	 * ������
	 */
	private static final String[] KEY_NAME={"��","��","��","��","��ͣ"};
	
	/**
	 * �ļ�·��
	 */
	private static final String PATH="data/control.dat";
	
	public GameSetting(GameControl gameControl){
		//��ʼ������Դ
		this.gameControl=gameControl;
		//���ñ���
		this.setTitle("��Ϸ����");
		//���ñ߽粼��
		this.setLayout(new BorderLayout());
		//��ʼ�������
		this.initKeyText();
		//����Ѷ��������
		this.add(this.createRankPanel(),BorderLayout.NORTH);
		//��������
		this.add(this.createMainPanel(),BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(this.createButtonPanle(),BorderLayout.SOUTH);
		//���ô���λ�ô�С
		FrameUtil.frameCenter(356,306,this);
		//��Ӵ����¼�����
		this.addWindowActions();
	}
	
	/**
	 * ��Ӵ����¼�����
	 */
	private void addWindowActions(){
		//��Ӽ�����
		this.addWindowListener(new WindowAdapter() {
			/**
			 * ���ڹر�ʱ����
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				//�������ڷ��ؽ���
				gameControl.configOver();
			}
			
			/**
			 * ����ͼ�껯ʱ����
			 */
			@Override
			public void windowIconified(WindowEvent e) {
				//�������ڷ��ؽ���
				gameControl.configOver();
			}
			
		});
	}
	
	/**
	 * ��ʼ�����������
	 */
	private void initKeyText(){
		int x=120;
		int y=32;
		int width=64;
		int height=20;
		for(int i=0;i<this.textControls.length;i++){
			this.textControls[i]=new TextControl(x,y,width,height,METHOD_NAME[i]);
			y+=32;
		}
		
		//����������
		ObjectInputStream objectIn=null;
		try {
			//��������������
			objectIn=new ObjectInputStream(new FileInputStream(PATH));
			//��ȡ����
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> keyMap= (HashMap<Integer, String>)objectIn.readObject();
			//ת����Set����
			Set<Entry<Integer,String>> entrySet = keyMap.entrySet();
			//����
			for(Entry<Integer,String> e:entrySet){
				for(TextControl text:this.textControls){
					if(text.getMethodName().equals(e.getValue())){
						text.setKeyCode(e.getKey());
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				objectIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �����Ѷ��������
	 * @return
	 */
	private JPanel createRankPanel() {
		//�������
		JPanel rankPanel=new JPanel();
		//���ñ�����ɫ
		rankPanel.setBackground(Color.cyan);
		//������ʽ����(�����)
		rankPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//������ѡ��ť
		JRadioButton radioButton1=new JRadioButton("��",true);
		JRadioButton radioButton2=new JRadioButton("һ��");
		JRadioButton radioButton3=new JRadioButton("����");
		//����ButtonGroup����ʵ�ֻ���
		ButtonGroup group=new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton3);
		//����¼�������
		radioButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���ü�ģʽ���ٶ�
				gameControl.setSpeed(GameConfig.getSystemConfig().getSlowSpeed());
				
			}
		});
		radioButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//����һ��ģʽ���ٶ�
				gameControl.setSpeed(GameConfig.getSystemConfig().getNormalSpeed());
				
			}
		});
		
		radioButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��������ģʽ���ٶ�
				gameControl.setSpeed(GameConfig.getSystemConfig().getFastSpeed());
				
			}
		});
		//��ӵ����
		rankPanel.add(rankLabel);
		rankPanel.add(radioButton1);
		rankPanel.add(radioButton2);
		rankPanel.add(radioButton3);
		return rankPanel;
	}
	
	/**
	 * ������ť���
	 * @return ������
	 */
	private JPanel createButtonPanle() {
		//������ť���
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//���ñ�����ɫ
		buttonPanel.setBackground(Color.cyan);
		//������ʾ��ǩ��ɫ
		this.errorMessage.setForeground(Color.red);
		//�����ʾ��ǩ
		buttonPanel.add(errorMessage);
		//��ӡ�ȷ������ť
		buttonPanel.add(btnOK);
		//��ӡ�Ӧ�á���ť
		buttonPanel.add(btnApply);
		//��ӡ�ȡ������ť
		buttonPanel.add(btnCancel);
		
		//����¼�����
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//д�������ļ�
				if(writeConfig()){
					//�رմ���
					setVisible(false);
					//�ر��Ӵ��ڣ�ˢ����Ϸ
					gameControl.configOver();
				}
			}
		});
		
		//����¼�����
		btnApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//д�������ļ�
				writeConfig();
				
			}
		});
		
		//����¼�����
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//�ر��Ӵ��ڣ�ˢ����Ϸ
				gameControl.configOver();
				
			}
		});
		return buttonPanel;
	}

	/**
	 * ���������
	 * @return ������
	 */
	private JPanel createMainPanel() {
		//���������
		JPanel mainPanel=new JPanel();
		//�������ɲ���
		mainPanel.setLayout(null);
		//���ñ�����ɫ
		mainPanel.setBackground(Color.cyan);
		//��ӱ�ǩ
		this.controLabel.setBounds(0, 0,100,20);
		mainPanel.add(this.controLabel);
		//���������ʾ�����
		for(int i=0;i<this.textControls.length;i++){
			//��ȡ��������
			TextControl text = this.textControls[i];
			//������ǩ����
			JLabel label=new JLabel(KEY_NAME[i]);
			//����λ��
			label.setBounds(text.getX()-30,text.getY(),30,20);
			//��ӵ����
			mainPanel.add(text);
			mainPanel.add(label);
		}
		return mainPanel;
	}
	
	/**
	 * д�������ļ� 
	 */
	protected boolean writeConfig() {
		//������ֵ�Լ���
		HashMap<Integer,String> keyMap=new HashMap<Integer, String>();
		for(int i=0,length=this.textControls.length;i<length;i++){
			//��ȡ���������
			TextControl text=this.textControls[i];
			if(text.getKeyCode()==0){
				this.errorMessage.setText("���󰴼�");
				return false;
			}
			keyMap.put(text.getKeyCode(),text.getMethodName());
		}
		if(keyMap.size()!=5){
			this.errorMessage.setText("�����ظ�");
			return false;
		}
		try {
			//���������
			ObjectOutputStream objectOut=new ObjectOutputStream(new FileOutputStream(PATH));
			//д���ļ�
			objectOut.writeObject(keyMap);
			//�ر���Դ 
			objectOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�����Ϣ
		this.errorMessage.setText(null);
		return true;
	}
}
