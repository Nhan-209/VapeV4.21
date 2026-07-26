package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.Event;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.AttackKeyController;
import gg.vape.input.InputEventDispatcher;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.AutoMace;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.AttackCooldownUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.LimitValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import java.util.Collections;
import java.util.Random;

public class AutoClicker
extends Mod {
    public final EntityTargetFilterValue k = EntityTargetFilterValue.W(this);
    private final TimerUtil c;
    private boolean V = false;
    public final NumberValue S;
    private boolean H = false;
    private final BooleanValue j;
    private int v = 0;
    public final BooleanValue F;
    private final TimerUtil b;
    public final LimitValue Y;
    private boolean K = false;
    private long O = 0L;
    private boolean Z = false;
    private int U = -1;
    private static final long hb = -4105462348079232239L;
    public final BooleanValue J;
    private final TimerUtil t;
    private final Random o;
    private boolean P = false;
    private final RandomValue D = RandomValue.G(this, "Extra delay", "#", "ticks", -20.0, 0.0, 0.0, 20.0, 0.1, "Extra delay after attack cooldown(in ticks)\nNegative values will attack before cooldown is complete");
    public final NumberValue C;
    public final BooleanValue r;
    private int A = -1;
    private final RandomValue L = RandomValue.G(this, "Mouse over delay", "#", "ms", 0.0, 0.0, 0.0, 200.0, 10.0, "Delay after your crosshair reaches a target before attacking");
    private int I = 0;
    public final BooleanValue s;
    public final BooleanValue p;

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (eventPreTick.getThePlayer().isNull()) {
            return;
        }
        int n = eventPreTick.getThePlayer().c$src$I$15a9iwo();
        boolean bl = this.v <= 0 && n > 0;
        this.v = n;
        if (this.J.L().booleanValue() && this.Z && !ClientSettings.H$src$Z$9w16bz(Minecraft.gameSettings().F())) {
            this.Z = false;
            this.v();
        }
        if (this.j.L().booleanValue() && this.V && this.U != -1 && bl) {
            this.V = false;
        }
        if (!this.c.hasTimeElapsed(50L)) {
            return;
        }
        if (this.P) {
            AttackKeyController.Q();
            this.P = false;
            return;
        }
        if (!this.R$src$Z$blf69y(true)) {
            return;
        }
        float f = (float)(-this.D.B());
        f += (float)this.I;
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity())) {
            Entity entity = rayTraceResult.getEntity();
            if (this.V(entity)) {
                if (this.m(entity, bl) && this.F(entity) && this.U(f)) {
                    AttackKeyController.Q();
                    this.P = AttackKeyController.u(this);
                }
            } else {
                this.T();
                this.G();
            }
        } else {
            this.T();
            this.G();
            if (this.H && this.j$src$Z$ehk4ss() && this.U(f)) {
                AttackKeyController.Q();
                this.P = AttackKeyController.u(this);
                this.H = false;
            }
        }
    }

    private void T() {
        this.A = -1;
        this.O = 0L;
        this.t.reset();
    }

    private boolean V(Entity entity) {
        if (this.F.L().booleanValue()) {
            EntityOtherPlayerMP entityOtherPlayerMP;
            boolean bl = true;
            AutoMace autoMace = Vape.INSTANCE.getModManager().getMod(AutoMace.class);
            if (autoMace != null && autoMace.r$src$Z$14eylz9() && autoMace.F$src$Z$1746r4n()) {
                bl = false;
            }
            if (bl && entity.isInstance(MappedClasses.lG) && RotationUtil.n(entityOtherPlayerMP = new EntityOtherPlayerMP(entity.getObject()))) {
                return false;
            }
        }
        return this.k.c(entity);
    }

    private boolean U(float f) {
        if (this.K()) {
            return true;
        }
        return AttackCooldownUtil.T(f);
    }

    private boolean R$src$Z$blf69y(boolean bl) {
        Wrapper wrapper;
        if (Minecraft.currentScreen().isNotNull() || !InputEventDispatcher.getInstance().getFocusState().isFocused()) {
            return false;
        }
        if (bl && (!InputEventDispatcher.getInstance().getFocusState().isFocused() || this.J.L().booleanValue() && !this.Z)) {
            return false;
        }
        if (this.p.L().booleanValue() && !this.Y.isValid((ItemStack)(wrapper = Minecraft.thePlayer().getHeldItemHand()), false)) {
            return false;
        }
        if (this.r.L().booleanValue() && !((Entity)(wrapper = Minecraft.thePlayer())).b$src$Z$fqlxe4()) {
            boolean bl2;
            double d = ((Entity)wrapper).N() - ((Entity)wrapper).W();
            boolean bl3 = bl2 = d < 0.0;
            if (!bl2) {
                return false;
            }
        }
        return true;
    }

    private boolean K() {
        if (!RotationUtil.u(Minecraft.thePlayer())) {
            return false;
        }
        AutoMace autoMace = Vape.INSTANCE.getModManager().getMod(AutoMace.class);
        if (!autoMace.r$src$Z$14eylz9()) {
            ItemStack itemStack = Minecraft.thePlayer().i(EnumHand.M());
            return itemStack.isNotNull() && itemStack.getItem().isInstance(MappedClasses.zx) && RotationUtil.u(Minecraft.thePlayer());
        }
        return autoMace.a$src$Z$17j175e();
    }

    private boolean F(Entity entity) {
        int n = entity.S();
        if (this.A != n) {
            this.A = n;
            this.O = (long)this.L.B();
            this.t.reset();
        }
        return this.t.hasTimeElapsed(this.O);
    }

    private void v() {
        this.U = -1;
        this.V = false;
        this.K = false;
        this.b.reset();
    }

    private boolean m(Entity entity, boolean bl) {
        if (!this.j.L().booleanValue()) {
            this.v();
            return true;
        }
        int n = entity.S();
        if (this.U != n) {
            this.U = n;
            this.V = true;
        }
        this.K = false;
        this.b.reset();
        if (this.V && bl) {
            this.V = false;
        }
        return !this.V;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.Z = false;
        this.H = false;
        this.I = 0;
        this.T();
        this.v();
        if (this.P) {
            AttackKeyController.Q();
            this.P = false;
            return;
        }
    }

    public AutoClicker() {
        super("Triggerbot", (int)hb, Category.g, "");
        this.j = BooleanValue.create(this, "Select first hit", false, "Waits for the opponent to hit you first before attacking");
        this.J = BooleanValue.create(this, "Require mouse down", false);
        this.s = BooleanValue.create(this, "Ignore activation click", false, "Ignores first manual click\n(unless already hovering a valid target with attack ready)");
        this.r = BooleanValue.create(this, "Air crits", false, "Won't attack in air unless you will crit");
        this.F = BooleanValue.create(this, "Shield check", false, "Won't attack players blocking with shield\nUsing HitSwap module with axes will override this behavior");
        this.C = NumberValue.create(this, "Target miss chance", "#", "%", 0.0, 0.0, 100.0, 1.0, "Chance to attack without hovering a valid target when attack is ready");
        this.S = NumberValue.create(this, "Early hit chance", "#", "%", 0.0, 0.0, 100.0, 1.0, "Chance to attack earlier than attack is ready");
        this.p = BooleanValue.create(this, "Limit to items", false, "Trigger functions only while holding selected items");
        this.Y = LimitValue.n(this, "trigger-alloweditems", "Allowed Items", LimitValue.r, Collections.emptyList());
        this.c = new TimerUtil();
        this.t = new TimerUtil();
        this.b = new TimerUtil();
        this.o = new Random();
        this.J.K(this.s);
        this.p.K(this.Y);
        this.addValue(this.k, this.D, this.L, this.J, this.s, this.r, this.F, this.j, this.C, this.S, this.p, this.Y);
    }

    private boolean j$src$Z$ehk4ss() {
        return this.j.L() == false || this.U == -1 || !this.V;
    }

    private void G() {
        if (!this.j.L().booleanValue() || this.U == -1) {
            return;
        }
        if (!this.K) {
            this.K = true;
            this.b.reset();
            return;
        }
        if (this.b.hasTimeElapsed(1000L)) {
            this.v();
        }
    }

    @EventHandler
    public void H(EventKeyPress eventKeyPress) {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!eventKeyPress.isKeybinding(Minecraft.gameSettings().F())) {
            return;
        }
        this.o(eventKeyPress.isDown(), eventKeyPress);
    }

    @EventHandler
    public void j(EventMouseButton eventMouseButton) {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!eventMouseButton.isKeybinding(Minecraft.gameSettings().F())) {
            return;
        }
        this.o(eventMouseButton.getButtonState(), eventMouseButton);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void o(boolean bl, Event event) {
        if (this.J.L().booleanValue() && bl && !this.Z) {
            this.Z = true;
            if (this.s.L().booleanValue() && this.R$src$Z$blf69y(false)) {
                RayTraceResult rayTraceResult;
                boolean bl2 = false;
                if (!this.U(-this.D.s$src$I$vi2lk8())) {
                    bl2 = true;
                }
                if ((rayTraceResult = RotationManager.b.n()).isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss())) {
                    bl2 = true;
                }
                if (bl2) {
                    event.setCancelled(true);
                }
            }
            return;
        }
        if (bl) {
            int n;
            this.c.reset();
            int n2 = (int)((Double)this.S.K()).doubleValue();
            this.I = n2 > 0 && this.o.nextInt(100) < n2 ? 2 + this.o.nextInt(2) : 0;
            RayTraceResult rayTraceResult = RotationManager.b.n();
            boolean bl3 = false;
            if (rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity())) {
                Entity entity = rayTraceResult.getEntity();
                boolean bl4 = bl3 = entity.isNotNull() && this.V(entity);
            }
            this.H = bl3 ? (n = (int)((Double)this.C.K()).doubleValue()) > 0 && this.o.nextInt(100) < n : false;
        }
    }
}

