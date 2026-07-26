package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventWorldTime;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;

public class WorldTimeEventMappingTask
extends JavassistMappingTask {
    @Override
    public void c() {
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(Vape.INSTANCE.getMappings().Dw.m, EventWorldTime.class);
        eventInjectionSpec.d("$0");
        eventInjectionSpec.H("$event.getWorldTime()");
        this.O(eventInjectionSpec);
    }

    public WorldTimeEventMappingTask() {
        super(MappedClasses.FP);
    }
}

