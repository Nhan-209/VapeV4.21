package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventBlockFluidRender;
import gg.vape.event.impl.EventBlockRenderBounds;
import gg.vape.event.impl.EventLegacyXRayRenderFaceXNeg;
import gg.vape.event.impl.EventLegacyXRayRenderFaceXPos;
import gg.vape.event.impl.EventLegacyXRayRenderFaceYNeg;
import gg.vape.event.impl.EventLegacyXRayRenderFaceYPos;
import gg.vape.event.impl.EventLegacyXRayRenderFaceZNeg;
import gg.vape.event.impl.EventLegacyXRayRenderFaceZPos;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;

public class RenderBlocksEventMappingTask
extends JavassistMappingTask {
    public RenderBlocksEventMappingTask() {
        super(MappedClasses.q5);
    }

    @Override
    public void c() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().i.G;
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventBlockRenderBounds.class);
        eventInjectionSpec.d("$0, $1");
        eventInjectionSpec.H("false");
        this.O(eventInjectionSpec);
        mappingMethod = Vape.INSTANCE.getMappings().i.w;
        eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventBlockFluidRender.class);
        eventInjectionSpec.d("$0, $1, $2, $3, $4");
        eventInjectionSpec.H("$event.isResult()");
        this.O(eventInjectionSpec);
        mappingMethod = Vape.INSTANCE.getMappings().i.I;
        eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventLegacyXRayRenderFaceYNeg.class);
        eventInjectionSpec.d("$1");
        this.O(eventInjectionSpec);
        mappingMethod = Vape.INSTANCE.getMappings().i.x;
        eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventLegacyXRayRenderFaceYPos.class);
        eventInjectionSpec.d("$1");
        this.O(eventInjectionSpec);
        mappingMethod = Vape.INSTANCE.getMappings().i.e;
        eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventLegacyXRayRenderFaceZNeg.class);
        eventInjectionSpec.d("$1");
        this.O(eventInjectionSpec);
        mappingMethod = Vape.INSTANCE.getMappings().i.b;
        eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventLegacyXRayRenderFaceZPos.class);
        eventInjectionSpec.d("$1");
        this.O(eventInjectionSpec);
        mappingMethod = Vape.INSTANCE.getMappings().i.y;
        eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventLegacyXRayRenderFaceXNeg.class);
        eventInjectionSpec.d("$1");
        this.O(eventInjectionSpec);
        mappingMethod = Vape.INSTANCE.getMappings().i.X;
        eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventLegacyXRayRenderFaceXPos.class);
        eventInjectionSpec.d("$1");
        this.O(eventInjectionSpec);
    }
}

