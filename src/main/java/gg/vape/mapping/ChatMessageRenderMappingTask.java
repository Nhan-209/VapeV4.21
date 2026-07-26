package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventChatMessageRender;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class ChatMessageRenderMappingTask
extends JavassistMappingTask {
    @Override
    public void c() {
        this.H();
    }

    public ChatMessageRenderMappingTask() {
        super(MappedClasses.d);
    }

    private void H() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().qd.T;
        if (mappingMethod != null && !mappingMethod.h()) {
            EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(mappingMethod, EventChatMessageRender.class);
            eventInjectionSpec.d("$0, $1, $2, $3");
            eventInjectionSpec.V("$1 = (" + MappedClasses.Yr.getName() + ") $event.getOutputContentComponent();");
            this.O(eventInjectionSpec);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

