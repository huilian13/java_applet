package top.yifan.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 玩家控制器
 *
 * @author star
 */
public class PlayerControl extends KeyAdapter {

    /**
     * 游戏控制器
     */
    private GameControl gameControl;

    public PlayerControl(GameControl gameControl) {
        super();
        //初始化游戏控制器
        this.gameControl = gameControl;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //控制游戏人物方向
        this.gameControl.actionKeys(e.getKeyCode());
    }

}
