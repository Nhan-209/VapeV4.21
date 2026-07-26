package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MRenderManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MRenderPlayer
extends Mapping {
    private MappingField N;
    public MappingMethod M;
    private MappingField z;
    public MappingMethod E;
    public MappingMethod Y;
    private MappingMethod O;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MRenderPlayer() {
        this(MRenderManager.O());
    }

    private MRenderPlayer(String[] stringArray) {
        super(MappedClasses.D0);
        String[] stringArray2 = stringArray;
        if (ForgeVersion.MC_1_7_10.Y()) {
            if (ForgeVersion.MC_1_16_5.d()) {
                if (ForgeVersion.MC_1_21_10.d()) {
                    this.Y = null;
                } else if (ForgeVersion.MC_1_21_4.d()) {
                    Class[] classArray = new Class[]{MappedClasses.uo, MappedClasses.DQ, MappedClasses.ZK, Integer.TYPE};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = true;
                    String string = "render";
                    Class clazz2 = MappedClasses.Fq;
                    MRenderPlayer mRenderPlayer = this;
                    this.Y = mRenderPlayer.W(clazz2, string, bl, clazz, classArray);
                } else {
                    Class[] classArray = new Class[]{MappedClasses.zt, Float.TYPE, Float.TYPE, MappedClasses.DQ, MappedClasses.ZK, Integer.TYPE};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = true;
                    String string = "render";
                    MRenderPlayer mRenderPlayer = this;
                    this.Y = mRenderPlayer.Y(string, bl, clazz, classArray);
                }
                Class clazz = MappedClasses.V6;
                boolean bl = true;
                String string = "entityModel";
                Class clazz3 = MappedClasses.Fq;
                MRenderPlayer mRenderPlayer = this;
                this.z = mRenderPlayer.X(clazz3, string, bl, clazz);
            } else {
                Class[] classArray = new Class[]{MappedClasses.zt, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "doRender";
                MRenderPlayer mRenderPlayer = this;
                this.Y = mRenderPlayer.Y(string, bl, clazz, classArray);
                Class[] classArray2 = new Class[]{};
                Class clazz4 = MappedClasses.ud;
                boolean bl2 = true;
                String string2 = "getMainModel";
                MRenderPlayer mRenderPlayer2 = this;
                this.O = this.Y(string2, bl2, clazz4, classArray2);
            }
        } else {
            Class[] classArray = new Class[]{MappedClasses.zt, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = true;
            String string = "doRender";
            MRenderPlayer mRenderPlayer = this;
            this.Y = mRenderPlayer.Y(string, bl, clazz, classArray);
            Class clazz5 = MappedClasses.zV;
            boolean bl3 = true;
            String string3 = "modelBipedMain";
            MRenderPlayer mRenderPlayer3 = this;
            this.N = this.J(string3, bl3, clazz5);
            if (Wrapper.G) {
                Class[] classArray3 = new Class[]{MappedClasses.zm, Float.TYPE};
                Class<Void> clazz6 = Void.TYPE;
                boolean bl4 = true;
                String string4 = "renderEquippedItems";
                MRenderPlayer mRenderPlayer4 = this;
                this.E = this.Y(string4, bl4, clazz6, classArray3);
            } else {
                Class[] classArray4 = new Class[]{MappedClasses.zt, Float.TYPE};
                Class<Void> clazz7 = Void.TYPE;
                boolean bl5 = true;
                String string5 = "renderEquippedItems";
                MRenderPlayer mRenderPlayer5 = this;
                this.E = this.Y(string5, bl5, clazz7, classArray4);
            }
            Class[] classArray5 = new Class[]{MappedClasses.zt, Integer.TYPE, Float.TYPE};
            Class<Integer> clazz8 = Integer.TYPE;
            boolean bl6 = true;
            String string6 = "shouldRenderPass";
            MRenderPlayer mRenderPlayer6 = this;
            this.M = this.Y(string6, bl6, clazz8, classArray5);
        }
    }

    public static Object Z(MRenderPlayer mRenderPlayer, Object object) {
        return mRenderPlayer.A(object);
    }

    private Object A(Object object) {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.z.getObject(object);
        }
        if (ForgeVersion.MC_1_7_10.Y()) {
            return this.O.L(object, new Object[0]);
        }
        return this.N.getObject(object);
    }
}

