package top.yifan.thread.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool
 *
 * @author star
 */
public final class ThreadPool {

    private static final int corePoolSize = 6;

    private static final int maximumPoolSize = Integer.MAX_VALUE;

    private static final long keepAliveTime = 60;

    private ThreadPool() {
    }

    private static class ThreadSinglePool {

        private ThreadPoolExecutor executor;

        private ThreadSinglePool() {
        }

        public ThreadPoolExecutor getInstance() {
            this.executor = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                    new SynchronousQueue<>());
            return executor;
        }
    }

    private static class ThreadSinglePoolHolder {

        private static final ThreadSinglePool INSTANCE = new ThreadSinglePool();
    }

    public static ThreadPoolExecutor getExecutor() {

        return ThreadSinglePoolHolder.INSTANCE.getInstance();
    }


}
