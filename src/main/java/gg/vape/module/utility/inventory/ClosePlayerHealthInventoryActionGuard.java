package gg.vape.module.utility.inventory;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.inventory.InventoryActionGuard;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.RotationUtil;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.World;

public class ClosePlayerHealthInventoryActionGuard
extends InventoryActionGuard {
    public Entity W;
    public double s = 99.0;

    @Override
    public void i(EntityLivingBase entityLivingBase) {
        boolean bl;
        World world = entityLivingBase.getWorld();
        if (world.isNull() || this.o != null && this.o.isNotNull() && !world.equals(this.o)) {
            this.L();
            return;
        }
        for (Object e : Minecraft.theWorld().X()) {
            if (!MappedClasses.lG.isInstance(e)) continue;
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(e);
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            double d = RotationUtil.y(entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h(), entityOtherPlayerMP.z(), entityOtherPlayerMP.N(), entityOtherPlayerMP.h());
            if (!(d <= 2.0) || !(d < this.s)) continue;
            this.s = d;
            this.W = entityOtherPlayerMP;
        }
        if (this.s != 99.0) {
            this.g();
        } else {
            this.L();
        }
        if (this.P) {
            if (RotationUtil.D(entityLivingBase, 10) == 0) {
                this.L();
            } else if (RotationUtil.d(entityLivingBase)) {
                ++this.U;
                if (this.U >= 40) {
                    this.L();
                } else {
                    this.U = 0;
                }
            }
            if (this.F > 0) {
                --this.F;
            } else {
                this.L();
            }
        }
        RayTraceResult rayTraceResult = Minecraft.p$src$Lgg_vape_wrapper_impl_RayTraceResult_$5rw6n0();
        double d = entityLivingBase.w$src$F$15l9epb();
        boolean bl2 = d < this.K || entityLivingBase.V$src$I$fk0dv5() == 20;
        boolean bl3 = bl = rayTraceResult.isNotNull() && rayTraceResult.getEntity().isNotNull() && entityLivingBase.Y$src$Z$154rldp();
        if (bl || bl2) {
            this.g();
        }
        this.K = d;
        this.o = world;
    }

    @Override
    public void L() {
        this.P = false;
        this.K = -999.0;
        this.o = Minecraft.theWorld();
        this.U = 0;
        this.F = 0;
        this.s = 99.0;
        this.W = null;
    }

    public ClosePlayerHealthInventoryActionGuard(int n) {
        super(n);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

