package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEntityRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class MEntityRendererFogDensityHook
extends Mapping {
    public MappingMethod H;
    private MappingMethod u;

    public MEntityRendererFogDensityHook() {
        this(MEntityRenderer.X());
    }

    private MEntityRendererFogDensityHook(int n) {
        super(MappedClasses.Dq);
        int n2 = n;
        if (ForgeVersion.MC_1_21_6.d()) {
            Class[] classArray = new Class[]{MappedClasses.uw};
            Class clazz = MappedClasses.qk;
            boolean bl = true;
            String string = "getBuffer";
            MEntityRendererFogDensityHook mEntityRendererFogDensityHook = this;
            this.u = mEntityRendererFogDensityHook.Y(string, bl, clazz, classArray);
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            this.H = null;
        } else if (Minecraft.G) {
            if (ForgeVersion.MC_1_20_6.d()) {
                Class[] classArray = new Class[]{MappedClasses.lt, MappedClasses.uw, Float.TYPE, Boolean.TYPE, Float.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "setupFog";
                MEntityRendererFogDensityHook mEntityRendererFogDensityHook = this;
                this.H = mEntityRendererFogDensityHook.x(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{MappedClasses.lt, MappedClasses.uw, Float.TYPE, Boolean.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "setupFog";
                MEntityRendererFogDensityHook mEntityRendererFogDensityHook = this;
                this.H = mEntityRendererFogDensityHook.x(string, bl, clazz, classArray);
            }
        } else {
            Class[] classArray = new Class[]{MappedClasses.lt, MappedClasses.uw, Float.TYPE, Boolean.TYPE, Float.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = false;
            String string = "setupFog";
            MEntityRendererFogDensityHook mEntityRendererFogDensityHook = this;
            this.H = mEntityRendererFogDensityHook.x(string, bl, clazz, classArray); 
        }
    }

    public Object v(Object object, Object object2) {
        return this.u.L(object, object2);
    }

}
