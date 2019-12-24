package top.yifan.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool
 *
 * @author star
 */
public class ThreadPool {

    private ThreadPool() {

    }

    public static ThreadPoolExecutor getInstance() {
        return TreadPoolHolder.INSTANCE;
    }

    private static class TreadPoolHolder {

        private static final int corePoolSize = 10;

        private static final int maximumPoolSize = Integer.MAX_VALUE;

        private static final long keepAliveTime = 60;

        private static final ThreadPoolExecutor INSTANCE = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime,
                TimeUnit.SECONDS, new SynchronousQueue<>());
    }
}
