package top.yifan.thread.actuator;

import top.yifan.thread.pool.ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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
        Future<?> future = executor.submit(runnable);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
