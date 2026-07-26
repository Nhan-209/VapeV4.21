package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.ClientChunkProvider;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClientBridge;
import java.util.List;

public class WorldClient
extends World {
    private static String o;

    public void D(int n, Entity entity) {
        WorldClient.c.getMappingsCompat().ha.Z(this.I, n, entity);
    }

    public List S() {
        return super.z();
    }

    public ClientChunkProvider U() {
        return new ClientChunkProvider(WorldClient.c.getMappingsCompat().ha.y(this.I));
    }

    public static void J(String string) {
        o = string;
    }

    @Override
    public Entity V(int n) {
        return new Entity(WorldClient.c.getMappingsCompat().ha.t(this.I, n));
    }

    public WorldClientBridge F() {
        return new WorldClientBridge(WorldClient.c.getMappingsCompat().ha.u(this.I));
    }

    public static String b() {
        return o;
    }

    public WorldClient(Object object) {
        super(object);
    }

    static {
        if (WorldClient.b() == null) {
            WorldClient.J("hSIdQb");
        }
    }
}

