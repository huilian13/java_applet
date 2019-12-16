package top.yifan.thread.runnable;

import top.yifan.constant.GameConstant;
import top.yifan.dto.GameDTO;
import top.yifan.entity.plane.Plane;

/**
 * 飞机移动运行器
 *
 * @author star
 */
public class PlaneMoveRunnable implements MoveRunnable {

    private GameDTO gameDto;

    private Plane plane;

    private int moveSpeed;

    public PlaneMoveRunnable(GameDTO gameDto, Plane plane, int moveSpeed) {
        this.gameDto = gameDto;
        this.plane = plane;
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                // 间隔 50ms
                Thread.sleep(GameConstant.SLEEP);
                // 物体移动
                if (!this.gameDto.isPause() && !plane.move(0, moveSpeed)) {
                    // 当移出窗口时，物体消失
                    plane.setLive(false);
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
