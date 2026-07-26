package gg.vape.utils.cache;

public class CachedValue<T> {
    private boolean y = false;
    private static String D;
    private T n;

    public void I(T t) {
        this.n = t;
        this.y = true;
    }

    public boolean r() {
        return this.y;
    }

    public static String k() {
        return D;
    }

    public T F() {
        return this.n;
    }

    public static void m(String string) {
        D = string;
    }

    public void S() {
        this.y = false;
    }

    static {
        if (CachedValue.k() == null) {
            CachedValue.m("zYblo");
        }
    }
}

