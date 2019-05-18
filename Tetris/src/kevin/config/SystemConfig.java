package kevin.config;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;
/**
 * ϵͳ������
 * @author kevin
 *
 */

@SuppressWarnings("serial")
public class SystemConfig implements Serializable{
	
	/**
	 * ��ͼ���x����
	 */
	private int maxX;
	
	/**
	 * ��ͼ���y����
	 */
	private int maxY;
	
	/**
	 * ��������
	 */
	private int levelUp;
	
	/**
	 * ����Ԫ�ؼ���
	 */
	private List<Point[]> rectPoints;
	
	/**
	 * ������ת���ͼ���
	 */
	private List<Boolean> roundType;
	
	/**
	 * �ӷֱ���
	 */
	private HashMap<Integer,Integer> scoreList;
	
	public SystemConfig(Element system){
		//��ȡ���x����
		this.maxX=Integer.parseInt(system.attributeValue("maxX"));
		//��ȡ���y����
		this.maxY=Integer.parseInt(system.attributeValue("maxY"));
		//��ȡ��������
		this.levelUp=Integer.parseInt(system.attributeValue("levelUp"));
		//��ȡ��������
		this.rectConfig(system);
		//��ȡ�ӷֱ�����
		this.scoreConfig(system);
	}

	/**
	 * �ӷ�����
	 * @param system ��ǩ����
	 */
	@SuppressWarnings("unchecked")
	private void scoreConfig(Element system) {
		//��ʼ���ӷֱ�
		this.scoreList=new HashMap<Integer, Integer>();
		// ��ȡplusScore��ǩ
		List<Element> plusScore = system.elements("plusScore");
		//������
		int line=0;
		//�÷���
		int score=0;
		//����
		for(Element e:plusScore){
			//��ȡ������
			line=Integer.parseInt(e.attributeValue("line"));
			//��ȡ�÷�
			score=Integer.parseInt(e.attributeValue("score"));
			//��ӵ��ӷֱ���
			this.scoreList.put(line,score);
		}
		
	}

	/**
	 * ��������
	 * @param system ��ǩ����
	 */
	@SuppressWarnings("unchecked")
	private void rectConfig(Element system) {
		//��ȡ���е�rectԪ��
		List<Element> rectConfig = system.elements("rect");
		//��ʼ�����鼯��
		this.rectPoints=new ArrayList<Point[]>(rectConfig.size());
		//��ʼ����ת���ͼ���
		this.roundType=new ArrayList<Boolean>();
		//��������
		for(Element rect:rectConfig){
			//�Ƿ���ת
			this.roundType.add(Boolean.parseBoolean(rect.attributeValue("round")));
			//ͨ��rect��ǩ��ȡ���е�point��ǩ
			List<Element> pointConfig= rect.elements("point");
			//��ȡpoints���ϵĳ���
			int len=pointConfig.size();
			//����point��������
			Point[] points=new Point[len];
			//��ʼ��point��������
			for(int j=0;j<len;j++){
				int x=Integer.parseInt(pointConfig.get(j).attributeValue("x"));
				int y=Integer.parseInt(pointConfig.get(j).attributeValue("y"));
				points[j]=new Point(x,y);
			}
			//��point���������ӵ�����
			this.rectPoints.add(points);
			
		}
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getLevelUp() {
		return levelUp;
	}

	public List<Point[]> getRectPoints() {
		return rectPoints;
	}

	public List<Boolean> getRoundType() {
		return roundType;
	}

	public HashMap<Integer, Integer> getScoreList() {
		return scoreList;
	}
	
}
