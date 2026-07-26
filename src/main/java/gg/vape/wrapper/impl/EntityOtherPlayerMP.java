package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.AbstractClientPlayer;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.World;

public class EntityOtherPlayerMP
extends AbstractClientPlayer {
    public EntityOtherPlayerMP(Object object) {
        super(object);
    }

    public static EntityOtherPlayerMP create(World world, GameProfile _g_02) {
        return new EntityOtherPlayerMP(EntityOtherPlayerMP.c.getMappingsMapperCompat().h_.T(world.getObject(), _g_02.getObject()));
    }
}

