package gg.vape.module.render.proj;

import gg.vape.mapping.mappings.MEnderPearlProjectileBridge;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityEnderPearl;

public class EnderPearlProjectileBridge
extends EntityEnderPearl {
    public EnderPearlProjectileBridge(Object object) {
        super(object);
    }

    public Entity C() {
        return new Entity(MEnderPearlProjectileBridge.w(EnderPearlProjectileBridge.c.getMappings().CH, this.getObject()));
    }
}

