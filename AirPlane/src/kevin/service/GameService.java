package kevin.service;

/**
 * 游戏业务逻辑类
 * @author kevin
 */
public interface GameService {
    /**
     * 向上
     */
    public void keyUp();

    /**
     * 向下
     */
    public void keyDown();

    /**
     * 向左
     */
    public void keyLeft();

    /**
     * 向右
     */
    public void keyRight();

    /**
     * 暂停键
     */
    public void keyPause();

    /**
     * 开始游戏
     */
    public void startGame();

    /**
     * 改变飞机形态
     */
    public void changePlaneImg();

    /**
     * 发射子弹
     */
    public void shot();

}
