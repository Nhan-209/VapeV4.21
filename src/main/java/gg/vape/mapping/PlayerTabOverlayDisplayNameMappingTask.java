package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventPlayerTabOverlayDisplayName;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;

public class PlayerTabOverlayDisplayNameMappingTask
extends JavassistMappingTask {
    @Override
    public void c() {
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(Vape.INSTANCE.getMappings().hP.O, EventPlayerTabOverlayDisplayName.class);
        eventInjectionSpec.d("$0, $1");
        eventInjectionSpec.H("$event.getDisplayNameInstance()");
        this.O(eventInjectionSpec);
    }

    public PlayerTabOverlayDisplayNameMappingTask() {
        super(MappedClasses.lF);
    }
}

