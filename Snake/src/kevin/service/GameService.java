package kevin.service;

/**
 * ��Ϸҵ���߼��ӿ�
 * @author kevin
 *
 */
public interface GameService{

	/**
	 * �ؼ���
	 */
	public void keyUp();
	
	/**
	 * �ؼ���
	 */
	public void keyDown();
	
	/**
	 * �ؼ���
	 */
	public void keyLeft();
	
	/**
	 * �ؼ���
	 */
	public void keyRight();
	
	/**
	 * ��ͣ
	 */
	public void keyFun();
	
	/**
	 * ������Ϸ
	 */
	public void startGame();
	
	/**
	 * �����ƶ�
	 */
	public void directionMove();
	
}
