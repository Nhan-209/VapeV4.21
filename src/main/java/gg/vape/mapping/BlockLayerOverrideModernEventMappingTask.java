package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventBlockLayerOverrideModern;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;

public class BlockLayerOverrideModernEventMappingTask
extends JavassistMappingTask {
    public BlockLayerOverrideModernEventMappingTask() {
        super(MappedClasses.qa);
    }

    @Override
    public void c() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().qg.S;
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventBlockLayerOverrideModern.class);
        eventInjectionSpec.d("$0");
        eventInjectionSpec.H("($r) $event.getBlockLayer()");
        this.O(eventInjectionSpec);
    }
}

