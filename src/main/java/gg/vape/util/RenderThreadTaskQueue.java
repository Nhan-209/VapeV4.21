package gg.vape.util;

import gg.vape.Vape;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RenderThreadTaskQueue {
    private static final String b = "Error executing RenderScheduler task";
    private static final long z = 5000000L;
    private static final Queue<Runnable> e = new ConcurrentLinkedQueue<Runnable>();

    private static Exception a(Exception exception) {
        return exception;
    }

    public static void M(Runnable runnable) {
        e.add(runnable);
    }

    public static void t() {
        if (e.isEmpty()) {
            return;
        }
        long l = System.nanoTime();
        while (!e.isEmpty()) {
            Runnable runnable = e.poll();
            if (runnable != null) {
                try {
                    runnable.run();
                }
                catch (Exception exception) {
                    Vape.debugLog(b);
                    Vape.logThrowable(exception);
                }
            }
            if (System.nanoTime() - l <= 5000000L) continue;
            break;
        }
    }
}

