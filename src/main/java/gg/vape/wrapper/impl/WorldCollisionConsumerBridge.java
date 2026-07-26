package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import java.util.function.Consumer;

public class WorldCollisionConsumerBridge
extends Wrapper {
    public WorldCollisionConsumerBridge(Object object) {
        super(object);
    }

    public void p(AxisAlignedBB ts_22, Consumer consumer) {
        WorldCollisionConsumerBridge.c.getMappingsMapperCompat().CM.V(this.I, ts_22.getObject(), consumer);
    }
}

