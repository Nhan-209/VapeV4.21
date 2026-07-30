package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.mappings.MCPacketUseEntityPacket;

public class UseEntityPacketBridge
extends Packet {
    private static final Vec3 y = new Vec3(null);

    public boolean u() {
        if (ForgeVersion.MC_26_1.d()) {
            return !this.J() && !this.V();
        }
        return this.U().equals(CPacketUseEntity.M());
    }

    public boolean S() {
        if (ForgeVersion.MC_26_1.d()) {
            return this.J();
        }
        return this.U().equals(CPacketUseEntity.T());
    }

    public int w() {
        return UseEntityPacketBridge.vapeInstance.getMappings().Co.d(this.I);
    }

    public String A$src$Ljava_lang_String_$jiwkol() {
        if (this.S()) {
            return "ATTACK";
        }
        if (this.V()) {
            return "INTERACT_AT";
        }
        if (this.u()) {
            return "INTERACT";
        }
        return "UNKNOWN";
    }

    public Vec3 O() {
        if (ForgeVersion.MC_26_1.d()) {
            if (this.J()) {
                return y;
            }
            Object object = MCPacketUseEntityPacket.Q(UseEntityPacketBridge.vapeInstance.getMappings().Co, this.I);
            return object == null ? y : new Vec3(object);
        }
        if (ForgeVersion.MC_1_20_6.d()) {
            CPacketUseEntityAction cPacketUseEntityAction = new CPacketUseEntityAction(MCPacketUseEntityPacket.k(UseEntityPacketBridge.vapeInstance.getMappings().Co, this.I), null);
            if (cPacketUseEntityAction.w().equals(CPacketUseEntity.o())) {
                CPacketUseEntityActionPacket cPacketUseEntityActionPacket = new CPacketUseEntityActionPacket(cPacketUseEntityAction.getObject());
                return cPacketUseEntityActionPacket.C();
            }
            return y;
        }
        return new Vec3(MCPacketUseEntityPacket.Q(UseEntityPacketBridge.vapeInstance.getMappings().Co, this.I));
    }

    public void A(int n) {
        UseEntityPacketBridge.vapeInstance.getMappings().Co.O(this.I, n);
    }

    public UseEntityPacketBridge(Object object) {
        super(object);
    }

    public Entity C(World world) {
        return world.V(this.w());
    }

    public CPacketUseEntity U() {
        if (ForgeVersion.MC_26_1.d()) {
            throw new IllegalStateException("Use helper methods for 26.1 packet actions");
        }
        if (ForgeVersion.MC_1_17.d()) {
            CPacketUseEntityAction cPacketUseEntityAction = new CPacketUseEntityAction(MCPacketUseEntityPacket.k(UseEntityPacketBridge.vapeInstance.getMappings().Co, this.I), null);
            return cPacketUseEntityAction.w();
        }
        return new CPacketUseEntity(MCPacketUseEntityPacket.k(UseEntityPacketBridge.vapeInstance.getMappings().Co, this.I), null);
    }

    public static boolean h(Packet packet) {
        return packet != null && (packet.isInstance(MappedClasses.Fa) || ForgeVersion.MC_26_1.d() && packet.isInstance(MappedClasses.ZW));
    }

    public boolean V() {
        if (ForgeVersion.MC_26_1.d()) {
            if (this.J()) {
                return false;
            }
            Object object = MCPacketUseEntityPacket.Q(UseEntityPacketBridge.vapeInstance.getMappings().Co, this.I);
            return object != null;
        }
        return this.U().equals(CPacketUseEntity.o());
    }

    private static IllegalStateException a(IllegalStateException illegalStateException) {
        return illegalStateException;
    }

    private boolean J() {
        return ForgeVersion.MC_26_1.d() && this.isInstance(MappedClasses.ZW);
    }
}

