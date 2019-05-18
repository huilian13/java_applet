package kevin.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import kevin.control.GameControl;
import kevin.ui.GameImage;
import kevin.utils.FrameUtil;

@SuppressWarnings("serial")
public class GameSetting extends JFrame{
	
	/**
	 * ��Ϸ������
	 */
	private GameControl gameControl ;

	/**
	 * ȷ����ť
	 */
	private JButton btnOK=new JButton("ȷ��");
	
	/**
	 * ȡ����ť
	 */
	private JButton btnCancel=new JButton("ȡ��");
	
	/**
	 * Ӧ�ð�ť
	 */
	private JButton btnApply=new JButton("Ӧ��");
	
	/**
	 * ������ʾ��Ϣ
	 */
	private JLabel errorMessage=new JLabel();
	
	/**
	 * �������������
	 */
	private TextControl[] textControls=new TextControl[8];
	
	/**
	 * ����������
	 */
	private JLabel[] textLabel;
	
	/**
	 * �б�ģʽ
	 */
	private DefaultListModel<String> skinData;
	
	/**
	 * �б�
	 */
	private  JList<?> skinList;
	
	/**
	 * Ԥ��ͼ
	 */
	private Image[] skinViewList;
	
	/**
	 * ����������
	 */
	private static final String[] METHOD_NAMES=new String[]{
			"keyRight","keyUp","keyLeft","keyDown",
			"keyFunLeft","keyFunUp","keyFunRight","keyFunDown",
	};
	
	public GameSetting(GameControl gameControl){
		//������Ŀ
		this.setTitle("��Ϸ����");
		//���ñ߽粼�ֹ�����
		this.setLayout(new BorderLayout());
		//��ʼ����Ϸ������
		this.gameControl=gameControl;
		//��ʼ��������
		this.initTextLabel();
		//��ʼ�����������
		this.initKeyText();
		//��������
		this.add(this.createMainPanel(),BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(this.creatButtonPanel(),BorderLayout.SOUTH);
		//��������
		FrameUtil.frameCenter(680,365,this);
		//��Ӵ����¼�����
		this.addWindowEvent();
	}
	
	/**
	 * ��Ӵ����¼�
	 */
	private void addWindowEvent(){
		//����¼�����
		this.addWindowListener(new WindowAdapter() {
			/**
			 * �������ڹر�ʱ����
			 */
		    @Override
			public void windowClosing(WindowEvent e) {
				// ���ڹر�ʱ��ˢ����������Ϸ
		    	gameControl.setOver();
			}
		   
		    /**
		     * ����ͼ�껯ʱ����
		     */
		    @Override
		    public void windowIconified(WindowEvent e) {
		    	// ���ڹر�ʱ��ˢ����������Ϸ
		    	gameControl.setOver();
		    }
			
		});
	}
	/**
	 * ��ʼ����������
	 */
	private void initTextLabel(){
		this.textLabel=new JLabel[]{
          new JLabel("��"),new JLabel("��"),new JLabel("��"),new JLabel("��"),
          new JLabel("��Ӱ"),new JLabel("����"),new JLabel("��ͣ"),new JLabel("˲��")
		};
	}
	

	/**
	 * ��ʼ�����������
	 */
	private void initKeyText() {
		int x=20;
		int y=52;
		int width=64;
		int height=20;
		for(int i=0;i<4;i++){
			this.textControls[i]=new TextControl(x, y, width,height,METHOD_NAMES[i]);
			y+=32;
		}
		x=570;
		y=52;
		for(int i=4;i<8;i++){
			this.textControls[i]=new TextControl(x, y, width,height,METHOD_NAMES[i]);
			y+=32;
		}
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("data/control.dat"));
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> keysMap= (HashMap<Integer,String>)in.readObject();
			in.close();
			Set<Entry<Integer,String>> entrySet = keysMap.entrySet();
			for(Entry<Integer,String> entry:entrySet){
				for(TextControl text:this.textControls){
					if(text.getMethodName().equals(entry.getValue())){
						text.setKeyCode(entry.getKey());
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������ť���
	 * @return ������
	 */                                                                                                  
	private JPanel creatButtonPanel() {
		//�������
		JPanel panel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//���ô�����Ϣ��ǩ
		this.errorMessage.setForeground(Color.red);
        panel.add(this.errorMessage);
        //���ȷ����ť
        panel.add(this.btnOK);
        //���ȡ����ť
        panel.add(this.btnCancel);
        //���Ӧ�ð�ť
        panel.add(this.btnApply);
        
		//��ӡ�ȷ����ť���¼�����
		this.btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//д�밴ť������Ϣ
				if(writeConfig()){
					//�رմ���
					setVisible(false);
					//�رմ��ں�ˢ����Ϸ
					gameControl.setOver();
				}
			}
		});
		//��ӡ�ȡ����ť���¼�����
		this.btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//�رմ���
				setVisible(false);
				//�رմ��ں�ˢ����Ϸ
				gameControl.setOver();
				
			}
		});
		//��ӡ�Ӧ�ð�ť���¼�����
		this.btnApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// д������
				writeConfig();
				//��Ƥ����ˢ�»���
				gameControl.repaint();
				
			}
		});
		//�رմ��ں�ˢ����Ϸ
		return panel;
	}

	/**
	 * ���������
	 * @return ѡ�������
	 */
	private JTabbedPane createMainPanel() {
		//����ѡ����
		JTabbedPane tabbedPane=new JTabbedPane();
		tabbedPane.addTab("��������",this.createControlPanel());
		tabbedPane.addTab("Ƥ������",this.creatSkinPanel());
		
		return tabbedPane;
	}
	
	/**
	 * ����Ƥ���������
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Component creatSkinPanel() {
		//�������
		final JPanel panel=new JPanel(new BorderLayout());
		//�����б�ģ��
		this.skinData=new DefaultListModel<String>();
		//����ͼƬ�ļ�����
		File dir=new File(GameImage.GRAPHICS_PATH);
		//��ȡͼƬ�ļ��µ������ļ�
		File[] files = dir.listFiles();
		//��ʼ��Ԥ��ͼ����
		this.skinViewList=new Image[files.length];
		for(int i=0,length=files.length;i<length;i++){
			//���ļ�����ӵ��б�
			skinData.addElement(files[i].getName());
			//���Ԥ��ͼ
			this.skinViewList[i]=new ImageIcon(files[i].getPath()+"/view.png").getImage();
			
		}
        //�����б�
		this.skinList=new JList(this.skinData);
        //���б����������
        skinList.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//ˢ�»���
        		panel.repaint();
        	}
		});
        //����Ĭ��Ԥ��ͼ
        skinList.setSelectedIndex(0);
        //Ƥ��Ԥ�����
        JPanel skinViewPanel=new JPanel(){
        	@Override
        	public void paint(Graphics g) {
        		//��ȡԤ��ͼ
        		Image viewImage= skinViewList[skinList.getSelectedIndex()];
        		//��������
        		int x=this.getWidth()-viewImage.getWidth(null)>>1;
        		int y=this.getHeight()-viewImage.getHeight(null)>>1;
        		//����Ԥ��ͼ
        		g.drawImage(viewImage,x,y,null);
        	}
        };
        //����б����
        panel.add(new JScrollPane(skinList),BorderLayout.WEST);
        //���Ԥ����嵽���
        panel.add(skinViewPanel,BorderLayout.CENTER);
		return panel;
	}

	/**
	 * ���������������
	 * @return ������
	 */
	private Component createControlPanel() {
		//�������
		JPanel panel=new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(GameImage.PSP,0,0,null);
			}
		};
		//���ò��ֹ�����
		panel.setLayout(null);
		//�����ı����
		TextControl text=null;
		//������ǩ���
		JLabel label=null;
		for(int i=0;i<textControls.length;i++){
			//��ȡ�ı�
			text=textControls[i];
			//��ȡ��ǩ
			label=textLabel[i];
			//���ñ�ǩλ��
			if(i<4){
				label.setBounds(text.getX()-20,text.getY(),20,text.getHeight());
			}else{
				label.setBounds(text.getX()+text.getWidth(),text.getY(),30,text.getHeight());
			}
			//������
			panel.add(label);
			panel.add(text);
		}
		return panel;
	}
	
	/**
	 * д�밴ť������Ϣ
	 */
	private boolean writeConfig(){
		//������ֵ�Լ���
		HashMap<Integer,String> keySet=new HashMap<Integer, String>();
		for(int i=0,length=this.textControls.length;i<length;i++){
			int keyCode=this.textControls[i].getKeyCode();
			//δ֪����
			if(keyCode==0){
				this.errorMessage.setText("����ť");
				return false;
			}
			//��ӵ�����
			keySet.put(keyCode,this.textControls[i].getMethodName());
		}
		//�����ظ�
		if(keySet.size()!=8){
			this.errorMessage.setText("��ť�ظ�");
			return false;
		}
		try {
			//��ȡƤ��·��
			String skinPath= this.skinData.get(this.skinList.getSelectedIndex());
			//����Ƥ��
			GameImage.setGameSkin(skinPath);
			//�������������
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("data/control.dat"));
			//���ļ�д�����
			out.writeObject(keySet);
			//�ر���
			out.close();
		} catch (Exception e) {
			this.errorMessage.setText(e.getMessage());
			return false;
		} 
		//д��ɹ�����ȥ����ʾ��Ϣ
		this.errorMessage.setText(null);
		return true;
	}

}
