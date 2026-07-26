package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.mapping.EventPreRenderEntityForgeCallback;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;

public class LegacyEntityRenderPreEventMappingTask
extends JavassistMappingTask {
    private static final String c = "$0, $1, $3, $4, $5";

    public LegacyEntityRenderPreEventMappingTask() {
        super(MappedClasses.x);
    }

    @Override
    public void c() {
        this.k(Vape.INSTANCE.getMappings().qB.v, EventPreRenderEntityForgeCallback.class, c);
    }
}

