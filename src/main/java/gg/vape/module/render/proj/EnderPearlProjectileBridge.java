package gg.vape.module.render.proj;

import gg.vape.mapping.mappings.MEnderPearlProjectileBridge;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityEnderPearl;

public class EnderPearlProjectileBridge
extends EntityEnderPearl {
    public EnderPearlProjectileBridge(Object handle) {
        super(handle);
    }

    public Entity getOwnerEntity() {
        return new Entity(MEnderPearlProjectileBridge.w(EnderPearlProjectileBridge.vapeInstance.getMappings().CH, this.getObject()));
    }
}
