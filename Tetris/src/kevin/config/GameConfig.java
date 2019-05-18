package kevin.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
	/**
	 * ˽�л����캯�����޷���������
	 */
	private GameConfig(){}
	
	/**
	 * ��Ϸ��������
	 */
    private static FrameConfig frameConfig=null;
    
    /**
     * ��Ϸ��������
     */
    private static DataConfig dataConfig=null;
    
    /**
     * ��Ϸ��������
     */
    private static SystemConfig systemConfig=null;
    
    /**
     * �����ļ�·��
     */
    private static final String PATH="data/config.xml";
    
    private static final boolean IS_DEBUG=false;
    
    /**
     * ��̬��ʼ��
     */
    static{
	    	try {
	    		if(IS_DEBUG){
	    			//��ȡ�ļ���ȡ��
	    			SAXReader reader=new SAXReader();
	    			//��ȡxml�ļ�
	    			Document doc= reader.read(new File(PATH));
	    			//��ȡ��Ԫ��
	    			Element gameRoot = doc.getRootElement();
	    			//������Ϸ�������ö���
	    			frameConfig=new FrameConfig(gameRoot.element("frame"));
	    			//������Ϸ�������ö���
	    			dataConfig=new DataConfig(gameRoot.element("data"));
	    			//����ϵͳ���ö���
	    			systemConfig=new SystemConfig(gameRoot.element("system"));
	    		}else {
	    			//��������������,��ȡ��������
					ObjectInputStream read=new ObjectInputStream(new FileInputStream("data/frameConfig.dat"));
					frameConfig = (FrameConfig)read.readObject();
					//�ر���
					read.close();
					//��ȡ��������
					read=new ObjectInputStream(new FileInputStream("data/dataConfig.dat"));
					dataConfig=(DataConfig)read.readObject();
					//�ر���
					read.close();
					//��ȡϵͳ����
					read=new ObjectInputStream(new FileInputStream("data/systemConfig.dat"));
					systemConfig=(SystemConfig)read.readObject();
					//�ر���
					read.close();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
         }

	public static FrameConfig getFrameConfig() {
		return frameConfig;
	}

	public static DataConfig getDataConfig() {
		return dataConfig;
	}

	public static SystemConfig getSystemConfig() {
		return systemConfig;
	}

}
