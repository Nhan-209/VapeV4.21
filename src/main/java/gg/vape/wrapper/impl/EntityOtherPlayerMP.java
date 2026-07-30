package gg.vape.wrapper.impl;

public class EntityOtherPlayerMP
extends AbstractClientPlayer {
    public EntityOtherPlayerMP(Object object) {
        super(object);
    }

    public static EntityOtherPlayerMP create(World world, GameProfile _g_02) {
        return new EntityOtherPlayerMP(EntityOtherPlayerMP.vapeInstance.getMappingsMapperCompat().h_.T(world.getObject(), _g_02.getObject()));
    }
}

