package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventVisGraphComputeVisibility;
import gg.vape.event.impl.EventVisGraphSetOpaqueCube;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;

public class VisGraphXRayVisibilityMappingTask
extends JavassistMappingTask {
    private static final String c = "($r) $event.getVisibility()";

    @Override
    public void c() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().Cg.V;
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventVisGraphComputeVisibility.class);
        eventInjectionSpec.H(c);
        this.O(eventInjectionSpec);
        MappingMethod mappingMethod2 = Vape.INSTANCE.getMappings().Cg.o;
        EventInjectionSpec eventInjectionSpec2 = new EventInjectionSpec(mappingMethod2, EventVisGraphSetOpaqueCube.class);
        eventInjectionSpec2.Z(true);
        this.O(eventInjectionSpec2);
    }

    public VisGraphXRayVisibilityMappingTask() {
        super(MappedClasses.Y7);
    }
}

