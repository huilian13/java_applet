package kevin.util;

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
	 * ���ô��ڵ�λ�úʹ�С
	 * @param width ���ڿ��
	 * @param height ���ڸ߶�
	 * @param frame  ���ڶ���
	 */
	public static void frameCenter(int width,int height,JFrame frame){
		//��ȡ��Ļ��ȡ��
		Toolkit toolKit=Toolkit.getDefaultToolkit();
		//��ȡ��Ļ������
		Dimension d=toolKit.getScreenSize();
		//��ȡ��Ļ�Ŀ��
		int x=(int)d.getWidth();
		//��ȡ��Ļ�ĸ߶�
		int y=(int)d.getHeight();
		//���ô��ڴ�С��������ʾ
		frame.setBounds(x-width>>1, y-height>>1, width, height);
		//���ô��ڲ��ɵ�
		frame.setResizable(false);
	}
}
