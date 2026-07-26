package gg.vape.wrapper.impl;

import gg.vape.utils.MutableFloatTriple;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockRayTraceResult;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Vec3;

public class CPacketPlayerBlockPlacement
extends Wrapper {
    public float d() {
        if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
            return this.d$src$Lgg_vape_utils_MutableFloatTriple_$uj7uxi().D();
        }
        return CPacketPlayerBlockPlacement.c.getMappings().D3.u(this.getObject());
    }

    public Object l$src$Ljava_lang_Object_$1lmatan() {
        if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
            return CPacketPlayerBlockPlacement.c.getMappings().D3.N(this.I);
        }
        throw new UnsupportedOperationException("Unimplemented");
    }

    public ItemStack Q$src$Lgg_vape_wrapper_impl_ItemStack_$16phjq1() {
        if (ForgeVersion.MC_1_12_2.d()) {
            return Minecraft.thePlayer().getHeldItemHand();
        }
        return new ItemStack(CPacketPlayerBlockPlacement.c.getMappings().D3.p(this.I));
    }

    public BlockRayTraceResult l() {
        if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
            return new BlockRayTraceResult(CPacketPlayerBlockPlacement.c.getMappings().D3.b(this.I));
        }
        throw new UnsupportedOperationException("Unimplemented");
    }

    public int K() {
        if (ForgeVersion.MC_1_21_11.d()) {
            return CPacketPlayerBlockPlacement.c.getMappings().D3.I(this.I);
        }
        return -1;
    }

    public float N() {
        if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
            return this.d$src$Lgg_vape_utils_MutableFloatTriple_$uj7uxi().Q();
        }
        return CPacketPlayerBlockPlacement.c.getMappings().D3.j(this.getObject());
    }

    public MutableFloatTriple d$src$Lgg_vape_utils_MutableFloatTriple_$uj7uxi() {
        if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
            BlockRayTraceResult blockRayTraceResult = this.l();
            BlockPos blockPos = blockRayTraceResult.getBlockPos();
            Vec3 vec3 = blockRayTraceResult.getHitVec();
            float f = (float)(vec3.getX() - (double)blockPos.P());
            float f2 = (float)(vec3.getY() - (double)blockPos.o());
            float f3 = (float)(vec3.getZ() - (double)blockPos.d());
            return new MutableFloatTriple(f, f2, f3);
        }
        return new MutableFloatTriple(this.d(), this.q(), this.N());
    }

    public float q() {
        if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
            return this.d$src$Lgg_vape_utils_MutableFloatTriple_$uj7uxi().H();
        }
        return CPacketPlayerBlockPlacement.c.getMappings().D3.h(this.getObject());
    }

    private static UnsupportedOperationException a(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
    }

    public CPacketPlayerBlockPlacement(Object object) {
        super(object);
    }

    public BlockPos Q() {
        if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
            return this.l().getBlockPos();
        }
        return new BlockPos(CPacketPlayerBlockPlacement.c.getMappings().D3.V(this.getObject()));
    }

    public int d$src$I$17a761m() {
        if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
            return this.l().Z();
        }
        return CPacketPlayerBlockPlacement.c.getMappings().D3.s(this.getObject());
    }
}

