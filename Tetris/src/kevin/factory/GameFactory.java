package kevin.factory;

import kevin.config.GameConfig;

/**
 * ��Ϸ������
 * @author kevin
 *
 */
public class GameFactory {
	 //˽�л����캯��
     private GameFactory(){};
     
     /**
      * �������Ŀ
      */
     private static int rectNumber;
     
     static{
    	 try {
    		 //��ʼ��������Ŀ
    		 rectNumber=GameConfig.getSystemConfig().getRectPoints().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
     }

	public static int getRectNumber() {
		return rectNumber;
	}
     
}
