package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MEntityRenderer;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTickEvent_Phase
extends Mapping {
    private final MappingField Z;
    private final MappingField V;
    private MappingField m;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static Object i(MTickEvent_Phase mTickEvent_Phase) {
        return mTickEvent_Phase.R();
    }

    private Object z() {
        return this.Z.getObject(null);
    }

    public MTickEvent_Phase() {
        this(MEntityRenderer.X());
    }

    private MTickEvent_Phase(int n) {
        super(MappedClasses.uw);
        if (n != 0) {
            Class clazz = MappedClasses.uw;
            boolean bl = true;
            String string = "FOG_TERRAIN";
            MTickEvent_Phase mTickEvent_Phase = this;
            this.Z = mTickEvent_Phase.u(string, bl, clazz);
            this.V = null;
            return;
        }
        if (ForgeVersion.MC_1_21_6.d()) {
            Class clazz = MappedClasses.uw;
            boolean bl = true;
            String string = "NONE";
            MTickEvent_Phase mTickEvent_Phase = this;
            this.V = mTickEvent_Phase.u(string, bl, clazz);
            Class clazz2 = MappedClasses.uw;
            boolean bl2 = true;
            String string2 = "WORLD";
            MTickEvent_Phase mTickEvent_Phase2 = this;
            this.Z = this.u(string2, bl2, clazz2);
        } else {
            Class clazz = MappedClasses.uw;
            boolean bl = true;
            String string = "FOG_SKY";
            MTickEvent_Phase mTickEvent_Phase = this;
            this.V = mTickEvent_Phase.u(string, bl, clazz);
            Class clazz3 = MappedClasses.uw;
            boolean bl3 = true;
            String string3 = "FOG_TERRAIN";
            MTickEvent_Phase mTickEvent_Phase3 = this;
            this.Z = this.u(string3, bl3, clazz3);
        }
    }

    private Object R() {
        return this.V.getObject(null);
    }

    public static Object a(MTickEvent_Phase mTickEvent_Phase) {
        return mTickEvent_Phase.z();
    }
}

