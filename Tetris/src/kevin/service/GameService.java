package kevin.service;

/**
 * ��Ϸҵ���߼���
 * @author kevin
 *
 */
public interface GameService {

	/**
	 * ���Ƽ���
	 */
	public boolean keyUp();
	
	/**
	 * ���Ƽ���
	 */
	public boolean keyDown();
	
	/**
	 * ���Ƽ���
	 */
	public boolean keyLeft();
	
	/**
	 * ���Ƽ���
	 */
	public boolean keyRight();
	
	/**
	 * ���ǣ����ף�
	 */
	public boolean keyFunUp();
	
	/**
	 * ��棨˲�����䣩
	 */
	public boolean keyFunDown();
	
	/**
	 * ���飨��Ӱ��
	 */
	public boolean keyFunLeft();
	
	/**
	 * ԲȦ����ͣ��
	 */
	public boolean keyFunRight();
	
	/**
	 * ��ʼ��Ϸ
	 */
	public void startGame();
	
}
