package gg.vape.module.utility.inventory;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.RotationUtil;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.World;
import java.lang.invoke.MethodHandles;

public class InventoryActionGuard {
    public int F;
    private static final long b;
    int M;
    public World o;
    public boolean P;
    int J;
    public int U;
    private static final long a;
    public double K;

    public void g() {
        this.P = true;
        this.F = this.J;
    }

    public InventoryActionGuard(int n) {
        long l = a ^ 0x5A0ECCB6A085L;
        this.M = (int)b;
        this.J = n;
    }

    public void L() {
        this.P = false;
        this.K = -999.0;
        this.o = Minecraft.theWorld();
        this.U = 0;
        this.F = 0;
    }

    public boolean l() {
        return this.P;
    }

    public void i(EntityLivingBase entityLivingBase) {
        boolean bl;
        boolean bl2;
        double d = entityLivingBase.w$src$F$15l9epb();
        World world = entityLivingBase.getWorld();
        if (world.isNull() || this.o != null && this.o.isNotNull() && !world.equals(this.o)) {
            this.L();
            return;
        }
        if (this.P) {
            if (this.F > 0) {
                --this.F;
            } else {
                bl2 = RotationUtil.d(entityLivingBase);
                if (bl2) {
                    ++this.U;
                    if (this.U >= 5) {
                        this.L();
                        return;
                    }
                    if (!RotationUtil.H(entityLivingBase) && !RotationUtil.F(entityLivingBase)) {
                        this.L();
                        return;
                    }
                    this.g();
                } else {
                    this.U = 0;
                    if (RotationUtil.D(entityLivingBase, this.M) == 0) {
                        this.L();
                        return;
                    }
                }
            }
        }
        bl2 = d < this.K || entityLivingBase.V$src$I$fk0dv5() == 20;
        RayTraceResult rayTraceResult = Minecraft.p$src$Lgg_vape_wrapper_impl_RayTraceResult_$5rw6n0();
        boolean bl3 = bl = rayTraceResult.isNotNull() && rayTraceResult.getEntity().isNotNull() && entityLivingBase.Y$src$Z$154rldp();
        if (bl2 || bl) {
            this.g();
        }
        this.o = world;
        this.K = d;
    }

    static {
        a = ZkmLongKeyState.a(-6226687703500341076L, -7256017301923789720L, MethodHandles.lookup().lookupClass()).a(114042472694841L);
        long l = a ^ 0x6B72078F9BA8L;
        b = 1665370352093495306L;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

