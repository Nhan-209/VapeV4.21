package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.Sprint;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.SPacketEntityVelocity;

public class NoSlow
extends Mod {
    private boolean pendingVelocity;
    private final BooleanValue limitItems = BooleanValue.create(this, "Limit Items", false, "Limits to whitelisted items only.");
    private final LimitValue whitelist = LimitValue.N(this, "noslowdown-whitelist", "Whitelisted", LimitValue.r, new ItemLimitData("swords"));
    private static final long MOD_ID = -7214429765927550220L;

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    public NoSlow() {
        super("NoSlowdown", (int)MOD_ID, Category.A, "Prevents slowing down when\nblocking or using items.");
        this.limitItems.K(this.whitelist);
        this.addValue(this.limitItems, this.whitelist);
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        Packet packet = eventPacketReceive.getPacket();
        if (packet.isInstance(MappedClasses.qe)) {
            this.pendingVelocity = true;
        }
        if (packet.isInstance(MappedClasses.YX)) {
            SPacketEntityVelocity sPacketEntityVelocity = new SPacketEntityVelocity(packet);
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (entityPlayerSP.isNotNull() && sPacketEntityVelocity.getEntityId() == entityPlayerSP.S()) {
                this.pendingVelocity = true;
            }
        }
    }

    @EventHandler
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.pendingVelocity) {
            if (entityPlayerSP.b$src$Z$fqlxe4()) {
                this.pendingVelocity = false;
            }
            return;
        }
        if (entityPlayerSP.h$src$Z$ftwoya()) {
            return;
        }
        double d = entityPlayerSP.movementInput().D();
        double d2 = entityPlayerSP.movementInput().T();
        float f = entityPlayerSP.J();
        if (!(!entityPlayerSP.l$src$Z$1io4duf() || this.limitItems.L().booleanValue() && !this.whitelist.A(entityPlayerSP.getHeldItemHand()) || Math.abs(d2) != (double)0.2f && Math.abs(d) != (double)0.2f)) {
            if (Vape.INSTANCE.getModManager().getState(Sprint.class)) {
                entityPlayerSP.R(true);
            }
            if (Math.abs(d2) == (double)0.2f) {
                if (d2 > 0.0) {
                    entityPlayerSP.movementInput().M(1.0f);
                } else if (d2 < 0.0) {
                    entityPlayerSP.movementInput().M(-1.0f);
                }
            }
            if (Math.abs(d) == (double)0.2f) {
                if (d > 0.0) {
                    entityPlayerSP.movementInput().B(1.0f);
                } else if (d < 0.0) {
                    entityPlayerSP.movementInput().B(-1.0f);
                }
            }
            if (Math.abs(entityPlayerSP.movementInput().T()) != 1.0f && d > 0.0) {
                d *= entityPlayerSP.B$src$Z$f90iek() ? (double)1.3f : 1.0;
            }
            d2 = d != 0.0 ? (d2 *= 0.5) : (d2 *= 0.85);
            entityPlayerSP.r(d * Math.cos(Math.toRadians(f + 90.0f)) + d2 * Math.sin(Math.toRadians(f + 90.0f)));
            entityPlayerSP.i(d * Math.sin(Math.toRadians(f + 90.0f)) - d2 * Math.cos(Math.toRadians(f + 90.0f)));
        }
    }

    private static ObfuscatedRuntimeException passthrough(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

