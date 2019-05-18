package kevin.service;

/**
 * 游戏业务逻辑层
 * @author kevin
 *
 */
public interface GameService {

	/**
	 * 控制键上
	 */
	public boolean keyUp();
	
	/**
	 * 控制键下
	 */
	public boolean keyDown();
	
	/**
	 * 控制键左
	 */
	public boolean keyLeft();
	
	/**
	 * 控制键右
	 */
	public boolean keyRight();
	
	/**
	 * 三角（作弊）
	 */
	public boolean keyFunUp();
	
	/**
	 * 大叉（瞬间下落）
	 */
	public boolean keyFunDown();
	
	/**
	 * 方块（阴影）
	 */
	public boolean keyFunLeft();
	
	/**
	 * 圆圈（暂停）
	 */
	public boolean keyFunRight();
	
	/**
	 * 开始游戏
	 */
	public void startGame();
	
}
