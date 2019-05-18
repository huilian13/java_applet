package kevin.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kevin.control.GameControl;
import kevin.util.FrameUtil;

/**
 * ��Ϸ��������
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GameFinishFrame extends JFrame{
	
	/**
	 * ��Ϸ������
	 */
	private GameControl gameControl;
	
	JButton nextButton= new JButton("������һ��");
	
	JButton exitBtn=new JButton("������Ϸ");
	
	/**
	 * ��Ϸ����
	 */
	private int index=0;
	
	public GameFinishFrame(GameControl gameControl){
		//��ʼ����Ϸ������
		this.gameControl=gameControl;
		//���ñ���
		this.setTitle("��Ϸ���");
		//�����Ϸ��ɵ����
		this.add(this.createFinishPanel(),BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(this.createButtonPanel(),BorderLayout.SOUTH);
		//���ô��ڴ�С������
		FrameUtil.frameCenter(300,150, this);
	}

	/**
	 * ������ť���
	 * @return ������
	 */
	private JPanel createButtonPanel() {
		// �������
		final JPanel buttonpPanel=new JPanel();
		//����¼�����
		this.nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(index>=4){
					nextButton.setText("����ͨ��");
					//ˢ�»���
					buttonpPanel.repaint();
					return;
				}
				// ������һ�ص���Ϸ����
				gameControl.startNextGame(++index);
				
			}
		});
		//����¼�����
		this.exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//������Ϸ
				gameControl.setOver();
				
			}
		});
		//��Ӱ�ť
		buttonpPanel.add(this.nextButton);
		
		buttonpPanel.add(this.exitBtn);
		return buttonpPanel;
	}

	/**
	 * ������ʾ��Ϸ������
	 * @return ������
	 */
	private JPanel createFinishPanel() {
		//�������
		JPanel panel=new JPanel(){
			@Override
			public void paint(Graphics g) {
				//����
				Image overImg=GameImage.OVER;
				int x=this.getWidth()-overImg.getWidth(null)>>1;
				int y=this.getHeight()-overImg.getHeight(null)>>1;
				//������Ϸ�����ʾ
				g.drawImage(overImg,x,y, null);
			}
		};
	      return panel;
	}

}
