package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.event.impl.EventPreMove;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.Material;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;

public class LongJump
extends Mod {
    private boolean S;
    private static final long o = 3997974486500807233L;
    private double A;
    private final BooleanValue P;
    private double O;
    private boolean t;
    private final NumberValue a = NumberValue.create(this, "Boost", "#.#", "", 3.0, 4.1, 5.0);

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private double A(EntityPlayerSP entityPlayerSP) {
        double d = 0.28730000691562896;
        if (entityPlayerSP.i(PotionRegistry.U) && entityPlayerSP.b(PotionRegistry.U).k() > 5) {
            int n = entityPlayerSP.b(PotionRegistry.U).L();
            d *= 1.0 + 0.15 * (double)(n + 1);
        }
        return d;
    }

    @EventHandler
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        EntityPlayerSP entityPlayerSP = eventPreMotion.getThePlayer();
        this.A = Math.hypot(entityPlayerSP.z() - entityPlayerSP.f(), entityPlayerSP.h() - entityPlayerSP.R());
        if (this.P.L().booleanValue()) {
            if (!this.t && entityPlayerSP.u$src$Z$g120nz() && this.S) {
                this.F();
            }
            if (!this.S && this.m(entityPlayerSP)) {
                this.S = true;
            }
        }
    }

    private boolean m(EntityPlayerSP entityPlayerSP) {
        GameSettings gameSettings = Minecraft.gameSettings();
        return gameSettings.Y().u() || gameSettings.s().u() || gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg().u() || gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3().u() || entityPlayerSP.movementInput().D() != 0.0f || entityPlayerSP.movementInput().T() != 0.0f;
    }

    @EventHandler
    public void u(EventPreMove eventPreMove) {
        EntityPlayerSP entityPlayerSP = eventPreMove.getThePlayer();
        if (entityPlayerSP.F() == 0.0f && entityPlayerSP.N$src$F$14ypudi() == 0.0f || eventPreMove.getWorld().isNull() || entityPlayerSP.h$src$Z$ftwoya()) {
            this.O = this.A(entityPlayerSP);
            return;
        }
        boolean bl = entityPlayerSP.Y$src$Lgg_vape_wrapper_impl_FoodStats_$fakh1z().getFoodLevel() >= 6;
        boolean bl2 = eventPreMove.getWorld().getBlockState(BlockPos.D(entityPlayerSP.z(), entityPlayerSP.N() - 0.1, entityPlayerSP.h())).getBlock().c() == 0.98f;
        double d = this.O = this.A(entityPlayerSP) * (entityPlayerSP.d(Material.w()) ? 0.5 : (entityPlayerSP.P() ? 0.8 : (entityPlayerSP.Q$src$Z$fh9faz() || entityPlayerSP.h$src$Z$ftwoya() ? 0.54 : (bl2 ? 2.4 : (bl ? 1.0 : 0.765)))));
        if (entityPlayerSP.b$src$Z$fqlxe4()) {
            if (this.t) {
                this.O = d * (Double)this.a.K();
                entityPlayerSP.k((double)0.42f);
                eventPreMove.setY(0.42f);
            } else {
                this.O = d;
            }
        } else if (this.t) {
            double d2 = 0.3303950079529733;
            if (entityPlayerSP.i(PotionRegistry.U) && entityPlayerSP.b(PotionRegistry.U).k() > 5) {
                int n = entityPlayerSP.b(PotionRegistry.U).L();
                d2 *= 1.0 + 0.15 * (double)(n + 1);
            }
            this.O = this.A - 0.666 * (this.A - d2);
            this.S = true;
        } else {
            this.O = this.A - this.A / 50.0;
        }
        this.t = entityPlayerSP.b$src$Z$fqlxe4();
        this.O = Math.max(this.O, d);
        eventPreMove.setX(-(Math.sin(this.q(entityPlayerSP)) * this.O));
        eventPreMove.setZ(Math.cos(this.q(entityPlayerSP)) * this.O);
    }

    public LongJump() {
        super("LongJump", (int)o, Category.w, "Does not work on a advanced anti-cheat servers.");
        this.P = BooleanValue.create(this, "Toggle", true, "Toggles off after touching the ground.");
        this.addValue(this.a, this.P);
        this.O = 0.27999999999999997;
        this.t = false;
        this.A = 0.0;
    }

    @Override
    public void onEnable() {
        Vape.INSTANCE.getClientSettings().k(this);
        this.O = 0.27999999999999997;
        this.t = false;
        this.A = 0.0;
        this.S = false;
    }

    private float q(EntityPlayerSP entityPlayerSP) {
        float f = entityPlayerSP.J();
        if (entityPlayerSP.F() < 0.0f) {
            f += 180.0f;
        }
        float f2 = entityPlayerSP.F() < 0.0f ? -0.5f : (entityPlayerSP.F() > 0.0f ? 0.5f : 1.0f);
        if (entityPlayerSP.N$src$F$14ypudi() > 0.0f) {
            f -= 90.0f * f2;
        }
        if (entityPlayerSP.N$src$F$14ypudi() < 0.0f) {
            f += 90.0f * f2;
        }
        return f *= (float)Math.PI / 180;
    }
}

