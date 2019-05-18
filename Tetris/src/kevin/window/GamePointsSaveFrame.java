package kevin.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kevin.control.GameControl;
import kevin.utils.FrameUtil;

/**
 * ��Ϸ�÷ִ���
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GamePointsSaveFrame extends JFrame{
	
	/**
	 * ��Ϸ������
	 */
	private GameControl gameControl;
	
    /**
     * ���ذ�ť
     */
	private JButton diskButton;
	
	/**
	 * ���ݿⰴť
	 */
	private JButton dataButton;
	
	/**
	 * ������ǩ
	 */
	private JLabel point;
	
	/**
	 * ���������
	 */
	private JTextField textName;
	
	/**
	 * ������Ϣ��ʾ
	 */
	private JLabel errorMsg;
	
	public GamePointsSaveFrame(GameControl gameControl){
		//��ʼ����Ϸ������
		this.gameControl=gameControl;
		//���ô��ڱ���
		this.setTitle("�����¼");
		//���ô�С������
		FrameUtil.frameCenter(300,150, this);
		//�����������
		this.createCom();
		//����¼�����
		this.addActionEvent();

	}
	
	/**
	 * ��ʾ����
	 * @param score ��ҷ���
	 */
	public void showWindow(int score){
		//��ʾ����
		this.point.setText("���ĵ÷֣�"+score);
		//��ʾ����
		this.setVisible(true);
	}
	
	/**
	 * ����¼�����
	 */
	private void addActionEvent() {
		//��Ӱ�ť�¼�
		this.diskButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�������
				String name=textName.getText();
				if(name.length()>10){
					errorMsg.setText("������10λ���µ�����");
				}else if(name==null||"".equals(name)){
					errorMsg.setText("���ֲ���Ϊ��");
				}else {
					//�رմ���
					setVisible(false);
					//�������
					gameControl.savePointToDisk(name);
				}
			}
		});
		
		this.dataButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�������
				String name=textName.getText();
				if(name.length()>10){
					errorMsg.setText("������10λ���µ�����");
				}else if(name==null||"".equals(name)){
					errorMsg.setText("���ֲ���Ϊ��");
				}else {
					//�رմ���
					setVisible(false);
					//�������
					gameControl.savePointToDB(name);
				}
			}
				
		});
		
	}

	/**
	 * �����������
	 */
	private void createCom() {
		//�����������
		JPanel scorePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		//��ʼ��������ǩ
		this.point=new JLabel();
		//��ʼ��������Ϣ��ǩ
		this.errorMsg=new JLabel();
		this.errorMsg.setForeground(Color.red);
		//��ӵ����
		scorePanel.add(this.point);
		scorePanel.add(this.errorMsg);
		//�������ӵ�����
		this.add(scorePanel,BorderLayout.NORTH);
		
		//�����������
		JPanel namePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		//���������ǩ
		namePanel.add(new JLabel("����������"));
		//��ʼ�����������
		this.textName=new JTextField(10);
		//��ӵ����
		namePanel.add(this.textName);
		//��ӵ�����
		this.add(namePanel,BorderLayout.CENTER);
		
		//������ť���
		JPanel buttonPanel=new JPanel();
		//��ӵص��ǩ
		buttonPanel.add(new JLabel("���浽��"),FlowLayout.LEFT);
		//��ʼ����ť
		this.dataButton=new JButton("���ݿ�");
		this.diskButton=new JButton("���ش���");
		//����ť��ӵ����
		buttonPanel.add(dataButton,FlowLayout.CENTER);
		buttonPanel.add(diskButton,FlowLayout.CENTER);
		//��ӵ�����
		this.add(buttonPanel,BorderLayout.SOUTH);
		
	}
	
}
