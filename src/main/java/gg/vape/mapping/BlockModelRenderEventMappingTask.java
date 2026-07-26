package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventBlockModelRender;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;

public class BlockModelRenderEventMappingTask
extends JavassistMappingTask {
    public BlockModelRenderEventMappingTask() {
        super(MappedClasses.VU);
    }

    @Override
    public void c() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().hE.y;
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventBlockModelRender.class);
        eventInjectionSpec.d("$0, $1, $2, $3, $4, $5, $6");
        eventInjectionSpec.H("$event.getResult()");
        this.O(eventInjectionSpec);
    }
}

