package kevin.service;
/**
 * ҵ���߼���ӿ�
 * @author kevin
 *
 */
public interface GameService {
	
	/**
	 * ��������
	 */
	public void keyUp();
	
	/**
	 * ��������
	 */
	public void keyDown();
	
	/**
	 * ��������
	 */
	public void keyLeft();
	
	/**
	 * ��������
	 */
	public void keyRight();
	
	/**
	 * ��Ϸ����ж�
	 */
	public boolean gameFinish();
}
