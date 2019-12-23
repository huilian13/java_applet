package top.yifan.utils;

/**
 * 游戏功能类
 *
 * @author star
 */
public final class GameFunction {

    private GameFunction() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * 获取等待时间
     *
     * @param rank 当前等级
     * @return 等待时间
     */
    public static long getSleepTime(int rank) {
        // 通过等级计算等待时间
        long sleep = (-40 * rank + 740);
        // 判断时间
        sleep = sleep < 200 ? 200 : sleep;
        return sleep;
    }
}

