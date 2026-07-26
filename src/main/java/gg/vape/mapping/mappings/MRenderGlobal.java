package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEntityRenderer;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.List;

public class MRenderGlobal
extends Mapping {
    public MappingMethod a;
    private final MappingMethod v;
    public MappingMethod c;
    public MappingMethod T;
    public MappingMethod P;
    public MappingMethod H;

    public MRenderGlobal() {
        super(MappedClasses.zs);
        Class[] classArray = new Class[]{};
        Class<Void> clazz = Void.TYPE;
        boolean bl = true;
        String string = "loadRenderers";
        MRenderGlobal mRenderGlobal = this;
        this.v = this.Y(string, bl, clazz, classArray);
        int n = MEntityRenderer.X();
        if (ForgeVersion.MC_1_7_10.L()) {
            Class[] classArray2 = new Class[]{MappedClasses.zm, Boolean.TYPE};
            Class<Boolean> clazz2 = Boolean.TYPE;
            boolean bl2 = true;
            String string2 = "updateRenderers";
            MRenderGlobal mRenderGlobal2 = this;
            this.P = this.Y(string2, bl2, clazz2, classArray2);
        } else if (ForgeVersion.MC_1_20_6.d()) {
            Class[] classArray3 = new Class[]{MappedClasses.lt};
            Class<Void> clazz3 = Void.TYPE;
            boolean bl3 = true;
            String string3 = "compileSections";
            MRenderGlobal mRenderGlobal3 = this;
            this.c = this.Y(string3, bl3, clazz3, classArray3);
        } else {
            Class[] classArray4 = new Class[]{Long.TYPE};
            Class<Void> clazz4 = Void.TYPE;
            boolean bl4 = true;
            String string4 = "updateChunks";
            MRenderGlobal mRenderGlobal4 = this;
            this.c = this.Y(string4, bl4, clazz4, classArray4);
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            Class[] classArray5 = new Class[]{MappedClasses.lt, MappedClasses.qh, MappedClasses.uy, MappedClasses.z6};
            Class<Void> clazz5 = Void.TYPE;
            boolean bl5 = true;
            String string5 = "extractVisibleEntities";
            MRenderGlobal mRenderGlobal5 = this;
            this.a = this.Y(string5, bl5, clazz5, classArray5);
        } else if (ForgeVersion.MC_1_21_4.d()) {
            Class[] classArray6 = new Class[]{MappedClasses.DQ, MappedClasses.lp, MappedClasses.lt, MappedClasses.uy, List.class};
            Class<Void> clazz6 = Void.TYPE;
            boolean bl6 = true;
            String string6 = "renderEntities";
            MRenderGlobal mRenderGlobal6 = this;
            this.H = this.Y(string6, bl6, clazz6, classArray6);
        }
        if (ForgeVersion.MC_1_20_6.d()) {
            if (ForgeVersion.MC_26_1.d()) {
                Class[] classArray7 = new Class[]{MappedClasses.Ds, MappedClasses.uy, Boolean.TYPE, MappedClasses.zf, MappedClasses.ZA, MappedClasses.qk, MappedClasses.FC, Boolean.TYPE, MappedClasses.VY};
                Class<Void> clazz7 = Void.TYPE;
                boolean bl7 = true;
                String string7 = "renderLevel";
                MRenderGlobal mRenderGlobal7 = this;
                this.T = this.Y(string7, bl7, clazz7, classArray7);
            } else {
                Class[] classArray8 = new Class[]{Float.TYPE, Long.TYPE, Boolean.TYPE, MappedClasses.lt, MappedClasses.FW, MappedClasses.zH, MappedClasses.qr, MappedClasses.qr};
                Class<Void> clazz8 = Void.TYPE;
                boolean bl8 = true;
                String string8 = "renderLevel";
                MRenderGlobal mRenderGlobal8 = this;
                this.T = this.Y(string8, bl8, clazz8, classArray8);
            }
        } else if (ForgeVersion.MC_1_16_5.d()) {
            Class[] classArray9 = new Class[]{MappedClasses.DQ, Float.TYPE, Long.TYPE, Boolean.TYPE, MappedClasses.lt, MappedClasses.FW, MappedClasses.zH, MappedClasses.qr};
            Class<Void> clazz9 = Void.TYPE;
            boolean bl9 = Wrapper.G;
            String string9 = "func_228426_a_";
            MRenderGlobal mRenderGlobal9 = this;
            this.T = this.Y(string9, bl9, clazz9, classArray9);
        }
    }

    private void H(Object object) {
        this.v.F(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void d(MRenderGlobal mRenderGlobal, Object object) {
        mRenderGlobal.H(object);
    }
}

