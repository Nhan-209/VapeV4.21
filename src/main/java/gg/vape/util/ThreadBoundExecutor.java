package gg.vape.util;

import gg.vape.ui.click.component.GuiComponent;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.NotNull;

public class ThreadBoundExecutor
implements Executor {
    private final Queue<Runnable> V = new ArrayDeque<Runnable>();
    private static GuiComponent[] T;
    private Runnable h;
    private Thread P;

    public static void G(GuiComponent[] upArray) {
        T = upArray;
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public synchronized void runPending() {
        try {
            if (this.P == null) {
                this.P = Thread.currentThread();
            }
        }
        catch (Throwable throwable) {
            throw ThreadBoundExecutor.sneakyThrow(ThreadBoundExecutor.a(throwable));
        }
        while ((this.h = this.V.poll()) != null) {
            try {
                this.h.run();
            }
            catch (Throwable throwable) {}
        }
    }

    public Thread getOwnerThread() {
        return this.P;
    }

    public static GuiComponent[] J() {
        return T;
    }

    @Override
    public synchronized void execute(@NotNull Runnable runnable) {
        boolean onOwnerThread = this.P != null && Thread.currentThread().equals(this.P);
        if (onOwnerThread) {
            runnable.run();
            return;
        }
        this.V.offer(runnable);
    }

    private static RuntimeException sneakyThrow(Throwable throwable) {
        ThreadBoundExecutor.throwUnchecked(throwable);
        throw new AssertionError((Object)"unreachable");
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwUnchecked(Throwable throwable) throws T {
        throw (T)throwable;
    }

    static {
        if (ThreadBoundExecutor.J() != null) {
            ThreadBoundExecutor.G(new GuiComponent[1]);
        }
    }
}
