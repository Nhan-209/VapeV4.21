package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.mapping.EventRender3DCallback;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;

public class Render3DEventMappingTask
extends JavassistMappingTask {
    private static final String c = "$1";

    public Render3DEventMappingTask() {
        super(MappedClasses.zs);
    }

    @Override
    public void c() {
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().Df.T;
        this.k(mappingMethod, EventRender3DCallback.class, c);
    }
}

