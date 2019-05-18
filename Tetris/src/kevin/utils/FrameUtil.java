package kevin.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * ���ڹ�����
 * @author kevin
 *
 */
public class FrameUtil {

	/**
	 * ���ô��ڴ�С������
	 * @param width ���ڵĿ��
	 * @param height ���ڵĸ߶�
	 * @param frame
	 */
	public static void frameCenter(int width,int height,JFrame frame){
		//��ȡ��Ļ��ȡ����
		Toolkit tool=Toolkit.getDefaultToolkit();
		//��ȡ��Ļ����
		Dimension screenSize = tool.getScreenSize();
		//��ȡ��Ļ�Ŀ�Ⱥ͸߶�
		int x=(int)screenSize.getWidth();
		int y=(int)screenSize.getHeight();
		//���ô��ھ���
		frame.setBounds(x-width>>1,(y-height>>1)-32,width,height);
		//���ڲ��ɵ�
		frame.setResizable(false);
	}
}
