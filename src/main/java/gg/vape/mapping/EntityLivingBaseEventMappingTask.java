package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventEntityRendererRayTrace;
import gg.vape.event.impl.EventPostEntityUpdate;
import gg.vape.event.impl.EventPostLivingTravel;
import gg.vape.event.impl.EventPotionEffectCheck;
import gg.vape.event.impl.EventPreEntityUpdate;
import gg.vape.event.impl.EventPreLivingTravel;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class EntityLivingBaseEventMappingTask
extends JavassistMappingTask {
    public EntityLivingBaseEventMappingTask() {
        super(MappedClasses.zm);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void c() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().Rr.V;
        this.c(mappingMethod, EventPreEntityUpdate.class, "$0");
        this.k(mappingMethod, EventPostEntityUpdate.class, "$0");
        MappingMethod mappingMethod2 = Vape.INSTANCE.getMappings().hx.U;
        this.c(mappingMethod2, EventPreLivingTravel.class, "$0");
        this.k(mappingMethod2, EventPostLivingTravel.class, "$0");
        if (ForgeVersion.MC_1_8_9.L()) {
            EventInjectionSpec eventInjectionSpec;
            if (!Vape.INSTANCE.isLabyModPresent()) {
                eventInjectionSpec = new EventInjectionSpec(Vape.INSTANCE.getMappings().Rr.jL, EventEntityRendererRayTrace.class);
                eventInjectionSpec.d("$0, $1");
                eventInjectionSpec.H("($r) $event.getVec();");
                this.O(eventInjectionSpec);
            }
            eventInjectionSpec = new EventInjectionSpec(Vape.INSTANCE.getMappings().hx.s, EventPotionEffectCheck.class);
            eventInjectionSpec.d("$0, $1");
            eventInjectionSpec.H("($r) $event.isActive();");
            this.O(eventInjectionSpec);
        }
    }
}

