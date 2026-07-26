package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventBlockLayerOverride;
import gg.vape.event.impl.EventBlockRenderLayerGate;
import gg.vape.event.impl.EventBlockShouldRender;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;

public class BlockRenderLayerEventMappingTask
extends JavassistMappingTask {
    public BlockRenderLayerEventMappingTask() {
        super(MappedClasses.Zk);
    }

    @Override
    public void c() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().qg.J;
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventBlockShouldRender.class);
        eventInjectionSpec.d("$0");
        eventInjectionSpec.H("true");
        this.O(eventInjectionSpec);
        MappingMethod mappingMethod2 = Vape.INSTANCE.getMappings().qg.S;
        EventInjectionSpec eventInjectionSpec2 = new EventInjectionSpec(mappingMethod2, EventBlockLayerOverride.class);
        eventInjectionSpec2.d("$0");
        eventInjectionSpec2.H("($r) $event.getBlockLayer()");
        this.O(eventInjectionSpec2);
        MappingMethod mappingMethod3 = Vape.INSTANCE.getMappings().qg.M;
        EventInjectionSpec eventInjectionSpec3 = new EventInjectionSpec(mappingMethod3, EventBlockRenderLayerGate.class);
        eventInjectionSpec3.Z(true);
        eventInjectionSpec3.H("($r) 1");
        this.O(eventInjectionSpec3);
    }
}

