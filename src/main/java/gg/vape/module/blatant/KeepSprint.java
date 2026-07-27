package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostAttack;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventSetSprinting;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.Scaffold;
import gg.vape.utils.MathUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class KeepSprint
extends Mod {
    private final NumberValue Y = new NumberValue(this, "Retain factor", 0.95, 0.6, 1.0, "#.##", "");
    private TimerUtil a;
    private double H;
    private boolean K;
    private boolean k;
    private final BooleanValue C = new BooleanValue((Object)this, "Reset sprint", false);
    private Scaffold v;
    private double b;
    private static final long r = -249991328817855756L;

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.k) {
            eventPrePlayerTick.getThePlayer().R(false);
            this.K = true;
            this.k = false;
        }
    }


    @Override
    public String r() {
        return this.Y.c();
    }

    @EventHandler
    public void onSetSprinting(EventSetSprinting eventSetSprinting) {
        if (this.K && eventSetSprinting.isNewStateSprinting()) {
            eventSetSprinting.setCancelled(true);
            this.K = false;
        }
    }

    private boolean t(EntityPlayerSP entityPlayerSP) {
        float f = entityPlayerSP.F();
        if (f > 0.0f) {
            double d;
            float f2 = entityPlayerSP.J();
            float f3 = -MathUtil.sin(f2 * (float)Math.PI / 180.0f);
            float f4 = MathUtil.cos(f2 * (float)Math.PI / 180.0f);
            double d2 = entityPlayerSP.t();
            double d3 = d2 * (double)f3 + (d = entityPlayerSP.T()) * (double)f4;
            return d3 > 0.0;
        }
        return false;
    }

    private boolean A(EntityPlayerSP entityPlayerSP) {
        if (entityPlayerSP.isNull()) {
            return false;
        }
        return !this.v.o$src$Z$dv6vsx() && entityPlayerSP.F() > 0.0f && !entityPlayerSP.P() && entityPlayerSP.Y$src$Lgg_vape_wrapper_impl_FoodStats_$fakh1z().getFoodLevel() > 6 && !entityPlayerSP.r();
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    @EventHandler
    public void v(EventPreAttack eventPreAttack) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.v == null) {
            this.v = Vape.INSTANCE.getModManager().getMod(Scaffold.class);
        }
        if (this.v.o$src$Z$dv6vsx()) {
            return;
        }
        this.H = entityPlayerSP.t();
        this.b = entityPlayerSP.T();
    }

    public KeepSprint() {
        super("KeepSprint", (int)r, Category.w, "Prevents you from losing sprint when attacking");
        this.a = new TimerUtil();
        this.addValue(this.Y, this.C);
    }

    @EventHandler
    public void A(EventPostAttack eventPostAttack) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.v == null) {
            this.v = Vape.INSTANCE.getModManager().getMod(Scaffold.class);
        }
        if (this.v.o$src$Z$dv6vsx()) {
            return;
        }
        if (eventPostAttack.getThePlayer().F() <= 0.0f) {
            return;
        }
        if (!entityPlayerSP.b$src$Z$fqlxe4() && !this.t(entityPlayerSP)) {
            return;
        }
        double d = 0.6;
        if (entityPlayerSP.t() == this.H * d && entityPlayerSP.T() == this.b * d && !entityPlayerSP.r()) {
            double d2 = (Double)this.Y.K();
            double d3 = entityPlayerSP.t() / d;
            double d4 = entityPlayerSP.T() / d;
            double d5 = d3 * d2;
            double d6 = d4 * d2;
            entityPlayerSP.r(d5);
            entityPlayerSP.i(d6);
            if (this.C.L().booleanValue()) {
                if (this.a.hasTimeElapsed(500L)) {
                    this.k = true;
                    this.a.reset();
                }
            } else {
                entityPlayerSP.R(true);
            }
        }
    }
}

