package gg.vape.module.utility.inventory;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.inventory.InventoryActionGuard;
import gg.vape.utils.RotationUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.World;
import java.util.Iterator;

public class NearbyPlayerInventoryActionGuard
extends InventoryActionGuard {
    public double G = 99.0;
    public Entity W;

    @Override
    public void i(EntityLivingBase entityLivingBase) {
        boolean bl;
        World world = entityLivingBase.getWorld();
        if (world.isNull() || this.o != null && this.o.isNotNull() && !world.equals(this.o)) {
            this.L();
            return;
        }
        boolean bl2 = RotationUtil.o(entityLivingBase, 10.0, 60.0, true);
        Iterator<?> object = Minecraft.theWorld().X().iterator();
        while (object.hasNext()) {
            Object e = object.next();
            if (!MappedClasses.lG.isInstance(e)) continue;
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(e);
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            double d = RotationUtil.y(entityPlayerSP.z(), 0.0, entityPlayerSP.h(), entityOtherPlayerMP.z(), 0.0, entityOtherPlayerMP.h());
            if (!(d <= 7.0) || !(d < this.G)) continue;
            this.G = d;
            this.W = entityOtherPlayerMP;
        }
        if (this.G != 99.0 || bl2) {
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
        boolean bl3 = bl = rayTraceResult.isNotNull() && rayTraceResult.getEntity().isNotNull() && entityLivingBase.Y$src$Z$154rldp();
        if (bl) {
            this.g();
        }
        this.o = world;
    }

    public NearbyPlayerInventoryActionGuard(int n) {
        super(n);
    }

    @Override
    public void L() {
        this.P = false;
        this.o = Minecraft.theWorld();
        this.U = 0;
        this.F = 0;
        this.G = 99.0;
        this.W = null;
    }

}
