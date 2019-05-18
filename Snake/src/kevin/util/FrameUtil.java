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
	  * ���ô��ڴ�С������
	  * @param width  ���ڵĿ���
	  * @param height ���ڵĸ߶�
	  * @param frame  ���ڶ���
	  */
     public static void frameCenter(int width,int height,JFrame frame){
    	 //��ȡ��Ļ��ȡ��
    	 Toolkit tool = Toolkit.getDefaultToolkit();
    	 //��ȡ��Ļ����
    	 Dimension d = tool.getScreenSize();
    	 //��ȡ��Ļ�Ŀ���
    	 int x=(int)d.getWidth();
    	 //��ȡ��Ļ�ĸ߶�
    	 int y=(int)d.getHeight()-64;
    	 //���ô��ڵ�λ��
    	 frame.setBounds(x-width>>1, y-height>>1, width, height);
    	 //���ô��ڲ��ɵ�
    	 frame.setResizable(false);
     }
}