package kevin.factory;

import java.util.HashMap;

import kevin.config.ButtonConfig;
import kevin.config.GameConfig;

/**
 * ����ģʽ
 * @author kevin
 *
 */
public class GameFactory {
	/**
	 * ˽�л����캯��
	 */
	private GameFactory(){}
	
	/**
	 * ��ť����
	 */
	private static ButtonConfig buttonConfig=null;
	
	/**
	 * ���ݼ���
	 */
	private static HashMap<String,String> dataMap=new HashMap<String, String>();
	
	static{
		try {
			//��ť����
			buttonConfig=GameConfig.getFrameConfig().getButtonConfig();
			//���ݼ���
			dataMap=GameConfig.getDataConfig().getDataMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ButtonConfig getButtonConfig() {
		return buttonConfig;
	}

	public static HashMap<String, String> getDataMap() {
		return dataMap;
	}
	
}
