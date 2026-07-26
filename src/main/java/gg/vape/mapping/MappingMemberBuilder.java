package gg.vape.mapping;

import gg.vape.mapping.Mapping;
import gg.vape.module.MinecraftVersionConstraint;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class MappingMemberBuilder<T extends MappingMemberBuilder, C> {
    private MinecraftVersionConstraint R;
    private String t;
    private boolean L;
    private Class z;
    private boolean u;
    private final Map<MinecraftVersionConstraint, Class<?>> p;
    private boolean H;
    private static int[] F;
    private Mapping C;
    private final Map<MinecraftVersionConstraint, Boolean> T;
    private Class<?> o;
    private final Map<MinecraftVersionConstraint, String> K;
    private final Map<MinecraftVersionConstraint, Class> f = new LinkedHashMap<MinecraftVersionConstraint, Class>();

    public static void D(int[] nArray) {
        F = nArray;
    }

    public MappingMemberBuilder() {
        this.K = new LinkedHashMap<MinecraftVersionConstraint, String>();
        this.T = new LinkedHashMap<MinecraftVersionConstraint, Boolean>();
        this.p = new LinkedHashMap();
    }

    public T H(boolean bl) {
        this.u = bl;
        return (T)this;
    }

    public String Y() {
        if (!this.K.isEmpty()) {
            for (Map.Entry<MinecraftVersionConstraint, String> entry : this.K.entrySet()) {
                if (!entry.getKey().y()) continue;
                return entry.getValue();
            }
        }
        return this.t;
    }

    public abstract C F();

    public T X(MinecraftVersionConstraint minecraftVersionConstraint, Class<?> clazz) {
        this.p.put(minecraftVersionConstraint, clazz);
        return (T)this;
    }

    public Class<?> X() {
        if (!this.p.isEmpty()) {
            for (Map.Entry<MinecraftVersionConstraint, Class<?>> entry : this.p.entrySet()) {
                if (!entry.getKey().y()) continue;
                return entry.getValue();
            }
        }
        return this.o;
    }

    public Mapping w() {
        return this.C;
    }

    public static int[] N() {
        return F;
    }

    public T v(String string) {
        this.t = string;
        return (T)this;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public T A(MinecraftVersionConstraint minecraftVersionConstraint, String string) {
        this.K.put(minecraftVersionConstraint, string);
        return (T)this;
    }

    public T F(boolean bl) {
        this.H = bl;
        return (T)this;
    }

    public T T(MinecraftVersionConstraint minecraftVersionConstraint) {
        this.R = minecraftVersionConstraint;
        return (T)this;
    }

    public T Q(MinecraftVersionConstraint minecraftVersionConstraint, Class clazz) {
        this.f.put(minecraftVersionConstraint, clazz);
        return (T)this;
    }

    public boolean c() {
        return this.R != null && this.R.y();
    }

    static {
        if (MappingMemberBuilder.N() != null) {
            MappingMemberBuilder.D(new int[3]);
        }
    }

    public boolean z$src$Z$103hrpe() {
        if (!this.T.isEmpty()) {
            for (Map.Entry<MinecraftVersionConstraint, Boolean> entry : this.T.entrySet()) {
                if (!entry.getKey().y()) continue;
                return entry.getValue();
            }
        }
        return this.L;
    }

    public Class F$src$Ljava_lang_Class_$100ldxh() {
        if (!this.f.isEmpty()) {
            for (Map.Entry<MinecraftVersionConstraint, Class> entry : this.f.entrySet()) {
                if (!entry.getKey().y()) continue;
                return entry.getValue();
            }
        }
        return this.z;
    }

    public boolean B() {
        return this.H;
    }

    public T i(MinecraftVersionConstraint minecraftVersionConstraint, boolean bl) {
        this.T.put(minecraftVersionConstraint, bl);
        return (T)this;
    }

    public T e(Mapping mapping) {
        this.C = mapping;
        return (T)this;
    }

    public boolean D() {
        return this.u;
    }

    public T l(Class<?> clazz) {
        this.o = clazz;
        return (T)this;
    }

    public T y(Class clazz) {
        this.z = clazz;
        return (T)this;
    }

    public T S(boolean bl) {
        this.L = bl;
        return (T)this;
    }
}

