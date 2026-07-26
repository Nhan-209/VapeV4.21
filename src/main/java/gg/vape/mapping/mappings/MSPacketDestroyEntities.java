package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MMappedClassSlotNRegistration;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MSPacketDestroyEntities
extends Mapping {
    private MappingField a;

    public int[] K(Object object) {
        return this.a.getIntArray(object);
    }

    public MSPacketDestroyEntities() {
        this(MMappedClassSlotNRegistration.r());
    }

    private MSPacketDestroyEntities(int n) {
        super(MappedClasses.qc);
        int n2 = n;
        Class<int[]> clazz = int[].class;
        boolean bl = false;
        String string = "a";
        MSPacketDestroyEntities mSPacketDestroyEntities = this;
        this.a = this.J(string, bl, clazz);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

