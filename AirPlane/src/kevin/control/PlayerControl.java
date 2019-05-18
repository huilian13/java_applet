package kevin.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 玩家控制器
 * @author kevin
 */
public class PlayerControl extends KeyAdapter{
    /**
     * 游戏控制器
     */
    private GameControl gameControl;

    public PlayerControl(GameControl gameControl) {
        this.gameControl=gameControl;
    }

    @Override
    public void keyPressed(KeyEvent e) {
       this.gameControl.keyActionList(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.gameControl.keyFunction(e.getKeyCode());
    }
}
