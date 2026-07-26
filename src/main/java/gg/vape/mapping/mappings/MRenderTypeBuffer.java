package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MMinecraft;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MRenderTypeBuffer
extends Mapping {
    private MappingMethod L;
    private MappingMethod l;
    private MappingMethod Z;

    public static void n(MRenderTypeBuffer mRenderTypeBuffer, Object object) {
        mRenderTypeBuffer.Z(object);
    }

    public MRenderTypeBuffer() {
        this(MMinecraft.Q());
    }

    private MRenderTypeBuffer(String[] stringArray) {
        super(MappedClasses.ZK);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_21_0.v()) {
                Class[] classArray = new Class[]{};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "endBatch";
                Class clazz2 = MappedClasses.lp;
                MRenderTypeBuffer mRenderTypeBuffer = this;
                this.Z = mRenderTypeBuffer.W(clazz2, string, bl, clazz, classArray);
            }
            return;
        }
        if (ForgeVersion.MC_1_21_0.v()) {
            Class[] classArray = new Class[]{MappedClasses.lX};
            Class clazz = MappedClasses.lp;
            boolean bl = true;
            String string = "getImpl";
            MRenderTypeBuffer mRenderTypeBuffer = this;
            this.L = mRenderTypeBuffer.x(string, bl, clazz, classArray);
        }
        Class[] classArray = new Class[]{};
        Class<Void> clazz = Void.TYPE;
        boolean bl = true;
        String string = "finish";
        Class clazz3 = MappedClasses.lp;
        MRenderTypeBuffer mRenderTypeBuffer = this;
        this.l = mRenderTypeBuffer.W(clazz3, string, bl, clazz, classArray);
        if (ForgeVersion.MC_1_17.d()) {
            Class[] classArray2 = new Class[]{};
            Class<Void> clazz4 = Void.TYPE;
            boolean bl2 = true;
            String string2 = "endBatch";
            Class clazz5 = MappedClasses.lp;
            MRenderTypeBuffer mRenderTypeBuffer2 = this;
            this.Z = this.W(clazz5, string2, bl2, clazz4, classArray2);
        }
    }

    private void z(Object object) {
        this.Z.F(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void Q(MRenderTypeBuffer mRenderTypeBuffer, Object object) {
        mRenderTypeBuffer.z(object);
    }

    public static Object t(MRenderTypeBuffer mRenderTypeBuffer, Object object) {
        return mRenderTypeBuffer.c(object);
    }

    private Object c(Object object) {
        return this.L.L(null, object);
    }

    private void Z(Object object) {
        this.l.F(object);
    }
}

