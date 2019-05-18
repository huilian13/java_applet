package kevin.service;
/**
 * 业务逻辑层接口
 * @author kevin
 *
 */
public interface GameService {
	
	/**
	 * 控制向上
	 */
	public void keyUp();
	
	/**
	 * 控制向下
	 */
	public void keyDown();
	
	/**
	 * 控制向左
	 */
	public void keyLeft();
	
	/**
	 * 控制向右
	 */
	public void keyRight();
	
	/**
	 * 游戏完成判断
	 */
	public boolean gameFinish();
}
