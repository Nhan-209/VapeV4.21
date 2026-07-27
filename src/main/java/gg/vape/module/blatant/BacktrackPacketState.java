package gg.vape.module.blatant;

import gg.vape.utils.MathUtil;
import gg.vape.utils.datas.PlayerLocationSnapshot;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.NetworkPlayerInfo;
import gg.vape.wrapper.impl.SEntityPacket;
import gg.vape.wrapper.impl.SPacketEntity;
import gg.vape.wrapper.impl.Vec3;

public class BacktrackPacketState {
    private long encodedZ;
    private long encodedX;
    private long encodedY;

    public void K(SPacketEntity spacketEntity) {
        this.encodedX = spacketEntity.H();
        this.encodedY = spacketEntity.M();
        this.encodedZ = spacketEntity.B();
    }

    public void W(NetworkPlayerInfo networkPlayerInfo) {
        Vec3 vec3 = networkPlayerInfo.e().u();
        this.encodedX = MathUtil.floor(vec3.getX() * BacktrackPacketState.positionScale());
        this.encodedY = MathUtil.floor(vec3.getY() * BacktrackPacketState.positionScale());
        this.encodedZ = MathUtil.floor(vec3.getZ() * BacktrackPacketState.positionScale());
    }

    public static BacktrackPacketState J(Entity entity) {
        return new BacktrackPacketState(MathUtil.floor(entity.double_z() * BacktrackPacketState.positionScale()), MathUtil.floor(entity.double_N() * BacktrackPacketState.positionScale()), MathUtil.floor(entity.double_h() * BacktrackPacketState.positionScale()));
    }

    private static double positionScale() {
        double d = ForgeVersion.MC_1_21_4.d() ? 4096.0 : 32.0;
        return d;
    }

    public PlayerLocationSnapshot g() {
        return new PlayerLocationSnapshot((double)this.encodedX / BacktrackPacketState.positionScale(), (double)this.encodedY / BacktrackPacketState.positionScale(), (double)this.encodedZ / BacktrackPacketState.positionScale());
    }


    public void Z(SEntityPacket sentityPacket) {
        this.encodedX += (long)sentityPacket.Y();
        this.encodedY += (long)sentityPacket.x();
        this.encodedZ += (long)sentityPacket.E();
    }

    public BacktrackPacketState(int x, int y, int z) {
        this.encodedX = x;
        this.encodedY = y;
        this.encodedZ = z;
    }
}

