package top.yifan.service;

/**
 * 游戏业务逻辑接口
 *
 * @author star
 */
public interface GameService {

    /**
     * 控键上
     */
    public void keyUp();

    /**
     * 控键下
     */
    public void keyDown();

    /**
     * 控键左
     */
    public void keyLeft();

    /**
     * 控键右
     */
    public void keyRight();

    /**
     * 暂停
     */
    public void keyFun();

    /**
     * 启动游戏
     */
    public void startGame();

    /**
     * 自由移动
     */
    public void directionMove();

}
