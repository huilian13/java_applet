package kevin.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ��Ϸ������
 * @author kevin
 *
 */
public class GameConfig {
	/**
	 * ��������
	 */
    private static FrameConfig frameConfig;
    
    /**
     * ϵͳ����
     */
    private static SystemConfig systemConfig;
    
    /**
     * ��ȡxml�����ļ�
     */
    static{
    	try {
			//����xml��ȡ��
			SAXReader reader=new SAXReader();
			//��ȡxml�ļ�
			Document doc = reader.read("data/config.xml");
			//��ȡ����ǩ
			Element game = doc.getRootElement();
			//��ʼ����������
			frameConfig=new FrameConfig(game.element("frame"));
			//��ʼ��ϵͳ����
			systemConfig=new SystemConfig(game.element("system"));
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
	
}
