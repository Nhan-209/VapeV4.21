package gg.vape.module;

import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.HashMap;
import java.util.Map;

public class VisibleModuleList<T> {
    private final HashMap<T, Long> i = new HashMap();
    private final long e;

    private void l() {
        long l = System.currentTimeMillis();
        this.i.entrySet().removeIf(entry -> this.lambda$cleanUp$0(l, (Map.Entry)entry));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void N(T t) {
        this.l();
        this.i.put(t, System.currentTimeMillis());
    }

    public VisibleModuleList(long l) {
        this.e = l;
    }

    public void m(T t) {
        this.l();
        this.i.remove(t);
    }

    public boolean Y(T t) {
        this.l();
        return this.i.containsKey(t);
    }

    public void R() {
        this.i.clear();
    }

    public int w() {
        this.l();
        return this.i.size();
    }

    private boolean lambda$cleanUp$0(long l, Map.Entry entry) {
        boolean bl = l - (Long)entry.getValue() > this.e;
        return bl;
    }
}

