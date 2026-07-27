package gg.vape.friend.ping;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ping.OnlineFriendPingMarker;
import gg.vape.protocol.packet.PingTargetData;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.World;
import org.jetbrains.annotations.Nullable;

public class EntityPingMarker
extends OnlineFriendPingMarker {
    @Nullable
    private Entity n;
    @Nullable
    private Long U;
    private final int p;

    public static double[] P(Entity entity) {
        return new double[]{entity.c(), entity.A() + ((double)entity.Y() + 0.15), entity.Z()};
    }

    public EntityPingMarker(OnlineFriend onlineFriend, @Nullable Long l, int n, double[] dArray) {
        super(onlineFriend, dArray);
        this.U = l;
        this.p = n;
        this.u(18.0);
        this.Z(18.0);
    }


    public EntityPingMarker(OnlineFriend onlineFriend, @Nullable Long l, Entity entity) {
        super(onlineFriend, EntityPingMarker.P(entity));
        this.U = l;
        this.p = entity.S();
        this.n = entity;
        this.u(18.0);
        this.Z(18.0);
    }

    @Override
    public void w(World world) {
        Entity entity;
        if (this.n != null && (this.n.isNull() || this.n.M$src$Z$ff28xj())) {
            this.n = null;
        }
        if (this.n == null && (entity = world.V(this.p)) != null && entity.isNotNull() && !entity.M$src$Z$ff28xj()) {
            this.n = entity;
        }
        if (this.n != null && this.n.isNotNull()) {
            this.n(EntityPingMarker.P(this.n));
        }
    }

    @Override
    public PingTargetData T() {
        return PingTargetData.a(this.U, this.p, this.Z(), this.N(), this.F());
    }
}

