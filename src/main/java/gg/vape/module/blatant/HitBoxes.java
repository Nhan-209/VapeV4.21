package gg.vape.module.blatant;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.UseEntityPacketBridge;
import gg.vape.wrapper.impl.Vec3;

public class HitBoxes
extends Mod {
    private final NumberValue Z = NumberValue.create((Object)this, "Expand amount", "#.##", "", 0.0, 0.35, 1.0, 0.01);
    private static final long k = -170962805321564187L;

    public HitBoxes() {
        super("HitBoxes", (int)k, Category.w, "Expands entities hitboxes");
        this.addValue(this.Z);
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        UseEntityPacketBridge useEntityPacketBridge;
        if (ForgeVersion.MC_1_8_9.v()) {
            return;
        }
        if (UseEntityPacketBridge.h(eventPacketSend.getPacket()) && (useEntityPacketBridge = new UseEntityPacketBridge(eventPacketSend.getPacket())).V()) {
            double d;
            double d2;
            double d3;
            double d4;
            double d5;
            Vec3 vec3 = useEntityPacketBridge.O();
            Entity entity = useEntityPacketBridge.C(Minecraft.theWorld());
            double d6 = (Double)this.Z.K();
            double d7 = entity.b();
            AxisAlignedBB axisAlignedBB = entity.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().expand(d7, d7, d7);
            AxisAlignedBB axisAlignedBB2 = entity.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().expand(d7 + d6, d7 + d6, d7 + d6);
            double d8 = axisAlignedBB.getMaxX() - axisAlignedBB.getMinX();
            double d9 = d8 / 2.0;
            double d10 = axisAlignedBB2.getMaxX() - axisAlignedBB2.getMinX();
            double d11 = d10 / 2.0;
            if (Math.abs(vec3.getX()) > d9) {
                d5 = Math.min(1.0, Math.abs(vec3.getX()) / d11);
                d4 = d9 * d5;
                if (d5 == 1.0) {
                    vec3.N(vec3.getX() > 0.0 ? d9 : -d9);
                } else {
                    vec3.N(vec3.getX() > 0.0 ? d4 : -d4);
                }
            }
            d5 = axisAlignedBB.getMaxZ() - axisAlignedBB.getMinZ();
            d4 = d5 / 2.0;
            double d12 = axisAlignedBB2.getMaxZ() - axisAlignedBB2.getMinZ();
            double d13 = d12 / 2.0;
            if (Math.abs(vec3.getZ()) > d4) {
                d3 = Math.min(1.0, Math.abs(vec3.getZ()) / d13);
                d2 = d4 * d3;
                if (d3 == 1.0) {
                    vec3.Z(vec3.getZ() > 0.0 ? d4 : -d4);
                } else {
                    vec3.Z(vec3.getZ() > 0.0 ? d2 : -d2);
                }
            }
            d2 = d3 = axisAlignedBB.getMaxY() - entity.N();
            double d14 = axisAlignedBB.getMinY() - entity.N();
            double d15 = d = axisAlignedBB2.getMaxY() - entity.N();
            if (vec3.getY() > d2) {
                double d16 = Math.min(1.0, Math.abs(vec3.getY()) / d15);
                double d17 = d2 * d16;
                if (d16 == 1.0) {
                    vec3.m(d2);
                } else {
                    vec3.m(d17);
                }
            } else if (vec3.getY() < d14) {
                double d18 = d14 - d6;
                double d19 = vec3.getY() / d18;
                if (d19 >= 1.0) {
                    vec3.m(d14);
                } else {
                    double d20 = d14 * d19;
                    vec3.m(d20);
                }
            }
        }
    }

    public float z() {
        if (!this.r$src$Z$14eylz9()) {
            return 0.0f;
        }
        return ((Double)this.Z.K()).floatValue();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

