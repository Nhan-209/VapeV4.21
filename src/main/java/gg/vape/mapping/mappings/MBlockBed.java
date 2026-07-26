package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MBlock;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MBlockBed
extends Mapping {
    private MappingMethod M;
    private MappingField V;

    public static Object X(MBlockBed mBlockBed) {
        return mBlockBed.y();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private Object y() {
        return this.V.getObject(null);
    }

    public static Object i(MBlockBed mBlockBed, Object object, Object object2) {
        return mBlockBed.f(object, object2);
    }

    private Object f(Object object, Object object2) {
        return this.M.L(null, object, object2);
    }

    public MBlockBed() {
        this(MBlock.m());
    }

    private MBlockBed(GuiComponent[] guiComponentArray) {
        super(MappedClasses.YE);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class clazz = MappedClasses.Vh;
                boolean bl = true;
                String string = "PART";
                MBlockBed mBlockBed = this;
                this.V = mBlockBed.u(string, bl, clazz);
            }
            return;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            Class[] classArray = new Class[]{MappedClasses.zJ, MappedClasses.lf};
            Class clazz = MappedClasses.q0;
            boolean bl = true;
            String string = "getBedDirection";
            MBlockBed mBlockBed = this;
            this.M = mBlockBed.x(string, bl, clazz, classArray);
        }
        if (ForgeVersion.MC_1_7_10.Y()) {
            Class clazz = MappedClasses.Vh;
            boolean bl = true;
            String string = "PART";
            MBlockBed mBlockBed = this;
            this.V = mBlockBed.u(string, bl, clazz);
        }
    }
}
