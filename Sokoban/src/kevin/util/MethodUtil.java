package kevin.util;

import kevin.config.GameConfig;

/**
 * ����������
 * @author kevin
 *
 */
public class MethodUtil {
	 /**
     * �߽緶Χ
     */
    private static final int minX=GameConfig.getSystemConfig().getMinX();
    
    private static final int maxX=GameConfig.getSystemConfig().getMaxX();
    
    private static final int minY=GameConfig.getSystemConfig().getMinY();
    
    private static final int maxY=GameConfig.getSystemConfig().getMaxY();
    
    /**
	 * �߽��ж�
	 * @return
	 */
	public static boolean IsOverMap(int x,int y,int[][] treeMap){
		return x>=minX&&x<maxX&&y>=minY&&y<maxY&&treeMap[y][x]!=1;
	}
}
