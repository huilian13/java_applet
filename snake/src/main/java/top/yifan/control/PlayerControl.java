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
        this.gameControl = gameControl;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.gameControl.actionByKey(e.getKeyCode());
    }

}

