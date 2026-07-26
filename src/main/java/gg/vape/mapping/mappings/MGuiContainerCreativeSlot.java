package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSlot;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MGuiContainerCreativeSlot
extends Mapping {
    private final MappingField i;

    public MGuiContainerCreativeSlot() {
        this(MSlot.z());
    }

    private MGuiContainerCreativeSlot(int[] nArray) {
        super(MappedClasses.VG);
        if (nArray != null) {
            Class clazz = MappedClasses.YQ;
            boolean bl = true;
            String string = "slot";
            MGuiContainerCreativeSlot mGuiContainerCreativeSlot = this;
            this.i = mGuiContainerCreativeSlot.J(string, bl, clazz);
            return;
        }
        if (ForgeVersion.MC_1_7_10.L()) {
            Class clazz = MappedClasses.YQ;
            boolean bl = Wrapper.G;
            String string = "field_148332_b";
            MGuiContainerCreativeSlot mGuiContainerCreativeSlot = this;
            this.i = mGuiContainerCreativeSlot.J(string, bl, clazz);
        } else {
            Class clazz = MappedClasses.YQ;
            boolean bl = true;
            String string = "slot";
            MGuiContainerCreativeSlot mGuiContainerCreativeSlot = this;
            this.i = mGuiContainerCreativeSlot.J(string, bl, clazz); 
        }
    }

    public Object q(Object object) {
        return this.i.getObject(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
