package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MCPacketUseEntityAction
extends Mapping {
    private static final String b = "getType";
    private final MappingMethod p;

    public MCPacketUseEntityAction() {
        super(MappedClasses.lw);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.D5;
        String string = b;
        MCPacketUseEntityAction mCPacketUseEntityAction = this;
        this.p = this.methodBuilder(string, clazz, classArray).setSkipAccessorGeneration(true).buildMethod();
    }

    public Object f(Object object) {
        return this.p.invokeObject(object, new Object[0]);
    }
}

