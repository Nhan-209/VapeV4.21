package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;

public class NetworkPacketEventMappingTask
extends JavassistMappingTask {
    public NetworkPacketEventMappingTask() {
        super(MappedClasses.FO);
    }

    @Override
    public void c() {
        this.k();
    }


    private void k() {
        EventInjectionSpec eventInjectionSpec;
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().Do.a;
        MappingMethod mappingMethod2 = Vape.INSTANCE.getMappings().Do.O;
        if (mappingMethod != null && !mappingMethod.h()) {
            eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventPacketReceive.class);
            eventInjectionSpec.d("$0, $2");
            eventInjectionSpec.V("$2 = (" + MappedClasses.Fm.getName() + ") $event.getPacketInstance();");
            this.O(eventInjectionSpec);
        }
        eventInjectionSpec = new EventInjectionSpec(mappingMethod2, EventPacketSend.class);
        eventInjectionSpec.d("$0, $1");
        eventInjectionSpec.V("$1 = (" + MappedClasses.Fm.getName() + ") $event.getPacketInstance();");
        this.O(eventInjectionSpec);
    }
}

