package top.yifan.thread.runnable;

import top.yifan.constant.GameConstant;
import top.yifan.dto.GameDTO;
import top.yifan.entity.Bullet;

/**
 * BulletMoveRunnable
 *
 * @author star
 */
public class BulletMoveRunnable implements MoveRunnable {

    private GameDTO gameDto;

    private Bullet bullet;

    private int speed;

    public BulletMoveRunnable(GameDTO gameDto, Bullet bullet, int speed) {
        this.gameDto = gameDto;
        this.bullet = bullet;
        this.speed = speed;
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                // 间隔 50ms
                Thread.sleep(GameConstant.SLEEP);
                // 当子弹碰到边界就消失
                if (!this.gameDto.isPause() && !bullet.move(0, -this.speed)) {
                    bullet.setLive(false);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
