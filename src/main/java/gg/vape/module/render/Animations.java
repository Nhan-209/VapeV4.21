package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventRenderFirstPersonItemPost;
import gg.vape.event.impl.EventRenderFirstPersonItemPre;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.AnimationsBlockingState;
import gg.vape.module.render.animations.AnimationsMode;
import gg.vape.module.render.animations.DamageResponsiveAnimationsMode;
import gg.vape.module.render.animations.LegacyBlockingPacketBufferedAnimationsMode;
import gg.vape.module.render.animations.SwordUseMouseGuardAnimationsMode;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.SubModuleValue;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import org.lwjgl.opengl.GL11;

public class Animations
extends Mod {
    private int A;
    TimerUtil r;
    private static final long o = -1033271949203766542L;
    private final ModeValue C;
    private final SubModuleValue<DamageResponsiveAnimationsMode> S;
    private final SubModuleValue<AnimationsBlockingState> F = new AnimationsBlockingState(this, "Manual").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
    private final SubModuleValue<LegacyBlockingPacketBufferedAnimationsMode> j;
    public final BooleanValue D;
    public final BooleanValue Z;
    public final NumberValue I;
    private final SubModuleValue<AnimationsMode> H;
    public final NumberValue v;

    @EventHandler
    public void E(EventPreRenderTick eventPreRenderTick) {
        int[] nArray = ClientSettings.A();
        if (ForgeVersion.MC_1_7_10.A() || !this.x()) {
            return;
        }
        if (eventPreRenderTick.getThePlayer().isNull()) {
            return;
        }
        ItemStack itemStack = eventPreRenderTick.getThePlayer().getHeldItemHand();
        if (itemStack.isNotNull() && ItemStackScoreUtil.h(itemStack.getItem())) {
            this.A = eventPreRenderTick.getThePlayer().j$src$I$1in0s92();
        }
        eventPreRenderTick.getThePlayer().N(10);
    }

    public boolean a$src$Z$ucwq0q() {
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        return Minecraft.thePlayer().getHeldItemHand().isNotNull() && ItemStackScoreUtil.h(Minecraft.thePlayer().getHeldItemHand().getItem());
    }

    public boolean c() {
        return this.r$src$Z$14eylz9() && this.V$src$Lgg_vape_module_render_animations_AnimationsMode$1evu1tq().i();
    }

    private boolean x() {
        return false;
    }

    @EventHandler
    public void Y(EventRenderFirstPersonItemPre eventRenderFirstPersonItemPre) {
        if (ForgeVersion.MC_1_8_9.A() || !this.x()) {
            return;
        }
        if (eventRenderFirstPersonItemPre.getThePlayer().isNotNull() && eventRenderFirstPersonItemPre.getThePlayer().j$src$I$1in0s92() > 0) {
            GL11.glPushMatrix();
            return;
        }
        float f = Minecraft.thePlayer().L(Minecraft.getTimer().renderPartialTicks());
        float f2 = -0.4f * MathUtil.sin(MathUtil.sqrt(f) * (float)Math.PI);
        float f3 = 0.2f * MathUtil.sin(MathUtil.sqrt(f) * (float)Math.PI * 2.0f);
        float f4 = -0.2f * MathUtil.sin(f * (float)Math.PI);
        GL11.glTranslatef((float)(-f2), (float)(-f3), (float)(-f4));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean n$src$Z$uk21qf() {
        return this.D.L();
    }

    @Override
    public String r() {
        if (this.C.K() == this.F) {
            return this.F.getInstance().r();
        }
        if (this.C.K() == this.j) {
            return this.j.getInstance().r();
        }
        return this.E();
    }

    @EventHandler
    public void i(EventRenderFirstPersonItemPost eventRenderFirstPersonItemPost) {
        if (ForgeVersion.MC_1_8_9.A() || !this.x()) {
            return;
        }
        if (eventRenderFirstPersonItemPost.getThePlayer().isNotNull() && eventRenderFirstPersonItemPost.getThePlayer().j$src$I$1in0s92() > 0) {
            GL11.glPopMatrix();
            float f = eventRenderFirstPersonItemPost.p;
            float f2 = Minecraft.thePlayer().L(Minecraft.getTimer().renderPartialTicks());
            GL11.glTranslatef((float)0.56f, (float)-0.52f, (float)-0.71999997f);
            GL11.glTranslatef((float)0.0f, (float)(f * -0.6f), (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float f3 = MathUtil.sin(f2 * f2 * (float)Math.PI);
            float f4 = MathUtil.sin(MathUtil.sqrt(f2) * (float)Math.PI);
            GL11.glRotatef((float)(f3 * -20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(f4 * -20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(f4 * -80.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
            return;
        }
        GL11.glTranslatef((float)-0.5f, (float)0.2f, (float)0.0f);
        GL11.glRotatef((float)30.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)-80.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
    }

    public EntityLivingBase C(double d, double d2) {
        EntityLivingBase entityLivingBase = RotationUtil.u(d2, d / 2.0);
        if (entityLivingBase == null) {
            return null;
        }
        if (RotationUtil.o(Minecraft.thePlayer(), entityLivingBase, d2, d / 2.0, true)) {
            return entityLivingBase;
        }
        return null;
    }

    public Animations() {
        super("BlockHit", (int)o, Category.g, "Automatically blockhit");
        this.S = new DamageResponsiveAnimationsMode(this, "Predict").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.H = new SwordUseMouseGuardAnimationsMode(this, "Auto").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.j = new LegacyBlockingPacketBufferedAnimationsMode(this, "Lag").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.D = BooleanValue.create(this, "Require mouse down", true, "Require block to be pressed to blockhit");
        this.Z = BooleanValue.create(this, "Ignore manual block", true, "Prevents manually blocking, useful for holding right click to activate");
        this.v = NumberValue.E(this, "Angle", "#", "", 0.0, 90.0, 360.0, "Max target angle to blockhit");
        this.I = NumberValue.E(this, "Distance", "#.#", "", 0.0, 5.0, 6.0, "Max target distance to blockhit");
        this.C = ModeValue.create((Object)this, "Mode", this.F, this.F, this.S, this.H, this.j);
        this.r = new TimerUtil();
        this.C.Z$src$Lgg_vape_value_Value_$16i62fx("Manual: Blockhit based on your CPS\nPredict: Predicts when a player can hit you and blocks ahead of time\nAuto: Legacy auto mode from AutoClicker\nLag: Lags you after blocking to maximize server side block time\n");
        this.D.K(this.Z);
        this.C.f(this.j, this.Z);
        this.C.f(this.j, this.v);
        this.C.f(this.j, this.I);
        this.addValue(this.C, this.D, this.Z, this.v, this.I);
    }

    public AnimationsMode V$src$Lgg_vape_module_render_animations_AnimationsMode$1evu1tq() {
        return (AnimationsMode)((SubModuleValue)this.C.K()).getInstance();
    }

    public boolean F$src$Z$ty29zz() {
        return this.V$src$Lgg_vape_module_render_animations_AnimationsMode$1evu1tq().M();
    }

    @EventHandler
    public void e(EventPostRenderTick eventPostRenderTick) {
        int[] nArray = ClientSettings.A();
        if (ForgeVersion.MC_1_7_10.A() || !this.x()) {
            return;
        }
        if (eventPostRenderTick.getThePlayer().isNull()) {
            return;
        }
        if (this.A != -1) {
            eventPostRenderTick.getThePlayer().N(this.A);
            this.A = -1;
        }
    }

    @Override
    public String E() {
        return this.C.c();
    }

    public EntityLivingBase j$src$Lgg_vape_wrapper_impl_EntityLivingBase_$m2mrxi() {
        return this.C(90.0, 5.0);
    }
}

