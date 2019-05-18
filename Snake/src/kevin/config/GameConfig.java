package kevin.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ��Ϸ����
 * @author kevin
 *
 */
public class GameConfig {
	/**
	 * ��������
	 */
	private static FrameConfig frameConfig=null;
	
	/**
	 * ϵͳ����
	 */
	private static SystemConfig systemConfig=null;
	
	/**
	 * ��������
	 */
	private static DataConfig dataConfig=null;

	/**
	 * �����ļ�·��
	 */
	private static final String PATH="data/config.xml";
	
	private GameConfig(){}
	
	static{
		try {
			//�����ļ���ȡ��
			SAXReader reader=new SAXReader();
			//��ȡxml�ļ�
			Document doc = reader.read(PATH);
			//��ȡ��Ԫ��
			Element root = doc.getRootElement();
			//��ʼ����������
			frameConfig=new FrameConfig(root.element("frame"));
			//��ʼ��ϵͳ����
			systemConfig=new SystemConfig(root.element("system"));
			//��ʼ����������
			dataConfig=new DataConfig(root.element("data"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FrameConfig getFrameConfig() {
		return frameConfig;
	}

	public static SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public static DataConfig getDataConfig() {
		return dataConfig;
	}

}
