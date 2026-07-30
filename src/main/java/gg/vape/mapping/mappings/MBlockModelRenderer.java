package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MBlockModelRenderer
extends Mapping {
    public MappingMethod P;
    public MappingMethod y;

    public boolean a(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, boolean bl, long l) {
        if (ForgeVersion.MC_1_12_2.d()) {
            return this.P.invokeBoolean(object, object2, object3, object5, object6, object7, bl, l);
        }
        if (Wrapper.isNativeAvailable) {
            return this.P.invokeBoolean(object, object2, object3, object4, object6, object7, bl);
        }
        return this.P.invokeBoolean(object, object2, object3, object4, object6, object7, bl);
    }


    public MBlockModelRenderer() {
        this(MEntityRenderer.X());
    }

    private MBlockModelRenderer(int n) {
        super(MappedClasses.VU);
        int n2 = n;
        if (ForgeVersion.MC_1_12_2.d()) {
            if (ForgeVersion.MC_1_16_5.v()) {
                Class[] classArray = new Class[]{MappedClasses.zR, MappedClasses.lc, MappedClasses.Vv, MappedClasses.lf, MappedClasses.lX, Boolean.TYPE, Long.TYPE};
                Class<Boolean> clazz = Boolean.TYPE;
                boolean bl = Wrapper.isNativeAvailable;
                String string = "func_187493_a";
                MBlockModelRenderer mBlockModelRenderer = this;
                this.y = mBlockModelRenderer.Y(string, bl, clazz, classArray);
                Class[] classArray2 = new Class[]{MappedClasses.zR, MappedClasses.lc, MappedClasses.Vv, MappedClasses.lf, MappedClasses.lX, Boolean.TYPE, Long.TYPE};
                Class<Boolean> clazz2 = Boolean.TYPE;
                boolean bl2 = Wrapper.isNativeAvailable;
                String string2 = "func_187498_b";
                MBlockModelRenderer mBlockModelRenderer2 = this;
                this.P = this.Y(string2, bl2, clazz2, classArray2);
            }
        } else {
            if (Vape.INSTANCE.isVanillaMinecraftPresent()) {
                Class[] classArray = new Class[]{MappedClasses.zR, MappedClasses.lc, MappedClasses.Vv, MappedClasses.lf, MappedClasses.lX, Boolean.TYPE};
                Class<Boolean> clazz = Boolean.TYPE;
                boolean bl = true;
                String string = "renderModel";
                MBlockModelRenderer mBlockModelRenderer = this;
                this.y = mBlockModelRenderer.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{MappedClasses.zR, MappedClasses.lc, MappedClasses.Vv, MappedClasses.lf, MappedClasses.lX, Boolean.TYPE};
                Class<Boolean> clazz = Boolean.TYPE;
                boolean bl = Wrapper.isNativeAvailable;
                String string = "func_178267_a";
                MBlockModelRenderer mBlockModelRenderer = this;
                this.y = mBlockModelRenderer.Y(string, bl, clazz, classArray);
            }
            if (Wrapper.isNativeAvailable && !Vape.INSTANCE.isVanillaMinecraftPresent()) {
                Class[] classArray = new Class[]{MappedClasses.zR, MappedClasses.lc, MappedClasses.Zk, MappedClasses.lf, MappedClasses.lX, Boolean.TYPE};
                Class<Boolean> clazz = Boolean.TYPE;
                boolean bl = false;
                String string = "a";
                MBlockModelRenderer mBlockModelRenderer = this;
                this.P = mBlockModelRenderer.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{MappedClasses.zR, MappedClasses.lc, MappedClasses.Zk, MappedClasses.lf, MappedClasses.lX, Boolean.TYPE};
                Class<Boolean> clazz = Boolean.TYPE;
                boolean bl = true;
                String string = "renderModelAmbientOcclusion";
                MBlockModelRenderer mBlockModelRenderer = this;
                this.P = mBlockModelRenderer.Y(string, bl, clazz, classArray); 
            }
        }
    }
}

