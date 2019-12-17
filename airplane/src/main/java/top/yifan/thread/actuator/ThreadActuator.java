package top.yifan.thread.actuator;

import top.yifan.thread.pool.ThreadPool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadActuator
 *
 * @author star
 */
public final class ThreadActuator {

    private ThreadActuator() {
    }

    public static void execute(Runnable runnable) {
        ThreadPoolExecutor executor = ThreadPool.getExecutor();
        executor.execute(runnable);
    }


}
