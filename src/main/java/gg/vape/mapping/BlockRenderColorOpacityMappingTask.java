package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventBlockRenderColorOpacity;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;

public class BlockRenderColorOpacityMappingTask
extends JavassistMappingTask {
    private static final String c = "$0, $1, $2, $3, $4";

    public BlockRenderColorOpacityMappingTask() {
        super(MappedClasses.lX);
    }

    @Override
    public void c() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().qZ.b;
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventBlockRenderColorOpacity.class);
        eventInjectionSpec.Z(false);
        eventInjectionSpec.d(c);
        this.O(eventInjectionSpec);
    }
}

