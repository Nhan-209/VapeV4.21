package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class MAtomicReferenceArrayBridge
extends Mapping {
    private static final String b = "chunks";
    private MappingField O;

    public MAtomicReferenceArrayBridge() {
        super(MappedClasses.zd);
        Class<AtomicReferenceArray> clazz = AtomicReferenceArray.class;
        boolean bl = true;
        String string = b;
        MAtomicReferenceArrayBridge mAtomicReferenceArrayBridge = this;
        this.O = this.J(string, bl, clazz);
    }

    public Object G(Object object) {
        return this.O.getObject(object);
    }
}

