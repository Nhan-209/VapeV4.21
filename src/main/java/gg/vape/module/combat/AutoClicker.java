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
    public final EntityTargetFilterValue targetFilter = EntityTargetFilterValue.W(this);
    private final TimerUtil attackTimer;
    private boolean waitingForFirstHit = false;
    public final NumberValue earlyHitChance;
    private boolean missAttackPending = false;
    private final BooleanValue selectFirstHit;
    private int lastHurtTime = 0;
    public final BooleanValue shieldCheck;
    private final TimerUtil selectTimer;
    public final LimitValue allowedItems;
    private boolean graceActive = false;
    private long mouseOverDelayMs = 0L;
    private boolean mouseDown = false;
    private int selectTargetId = -1;
    private static final long MODULE_ID = -4105462348079232239L;
    public final BooleanValue requireMouseDown;
    private final TimerUtil mouseOverTimer;
    private final Random random;
    private boolean releasePending = false;
    private final RandomValue extraDelay = RandomValue.G(this, "Extra delay", "#", "ticks", -20.0, 0.0, 0.0, 20.0, 0.1, "Extra delay after attack cooldown(in ticks)\nNegative values will attack before cooldown is complete");
    public final NumberValue targetMissChance;
    public final BooleanValue airCrits;
    private int mouseOverTargetId = -1;
    private final RandomValue mouseOverDelayValue = RandomValue.G(this, "Mouse over delay", "#", "ms", 0.0, 0.0, 0.0, 200.0, 10.0, "Delay after your crosshair reaches a target before attacking");
    private int earlyHitTicks = 0;
    public final BooleanValue ignoreActivationClick;
    public final BooleanValue limitToItems;

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (eventPreTick.getThePlayer().isNull()) {
            return;
        }
        int n = eventPreTick.getThePlayer().c$src$I$15a9iwo();
        boolean bl = this.lastHurtTime <= 0 && n > 0;
        this.lastHurtTime = n;
        if (this.requireMouseDown.L().booleanValue() && this.mouseDown && !ClientSettings.H$src$Z$9w16bz(Minecraft.gameSettings().F())) {
            this.mouseDown = false;
            this.resetSelectState();
        }
        if (this.selectFirstHit.L().booleanValue() && this.waitingForFirstHit && this.selectTargetId != -1 && bl) {
            this.waitingForFirstHit = false;
        }
        if (!this.attackTimer.hasTimeElapsed(50L)) {
            return;
        }
        if (this.releasePending) {
            AttackKeyController.Q();
            this.releasePending = false;
            return;
        }
        if (!this.canAttack(true)) {
            return;
        }
        float f = (float)(-this.extraDelay.B());
        f += (float)this.earlyHitTicks;
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity())) {
            Entity entity = rayTraceResult.getEntity();
            if (this.isValidTarget(entity)) {
                if (this.updateSelectFirstHit(entity, bl) && this.hasMouseOverDelayElapsed(entity) && this.isAttackReady(f)) {
                    AttackKeyController.Q();
                    this.releasePending = AttackKeyController.u(this);
                }
            } else {
                this.resetMouseOverState();
                this.updateSelectGrace();
            }
        } else {
            this.resetMouseOverState();
            this.updateSelectGrace();
            if (this.missAttackPending && this.isSelectFirstHitSatisfied() && this.isAttackReady(f)) {
                AttackKeyController.Q();
                this.releasePending = AttackKeyController.u(this);
                this.missAttackPending = false;
            }
        }
    }

    private void resetMouseOverState() {
        this.mouseOverTargetId = -1;
        this.mouseOverDelayMs = 0L;
        this.mouseOverTimer.reset();
    }

    private boolean isValidTarget(Entity entity) {
        if (this.shieldCheck.L().booleanValue()) {
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
        return this.targetFilter.c(entity);
    }

    private boolean isAttackReady(float f) {
        if (this.canBypassCooldown()) {
            return true;
        }
        return AttackCooldownUtil.T(f);
    }

    private boolean canAttack(boolean bl) {
        Wrapper wrapper;
        if (Minecraft.currentScreen().isNotNull() || !InputEventDispatcher.getInstance().getFocusState().isFocused()) {
            return false;
        }
        if (bl && (!InputEventDispatcher.getInstance().getFocusState().isFocused() || this.requireMouseDown.L().booleanValue() && !this.mouseDown)) {
            return false;
        }
        if (this.limitToItems.L().booleanValue() && !this.allowedItems.isValid((ItemStack)(wrapper = Minecraft.thePlayer().getHeldItemHand()), false)) {
            return false;
        }
        if (this.airCrits.L().booleanValue() && !((Entity)(wrapper = Minecraft.thePlayer())).b$src$Z$fqlxe4()) {
            boolean bl2;
            double d = ((Entity)wrapper).N() - ((Entity)wrapper).W();
            boolean bl3 = bl2 = d < 0.0;
            if (!bl2) {
                return false;
            }
        }
        return true;
    }

    private boolean canBypassCooldown() {
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

    private boolean hasMouseOverDelayElapsed(Entity entity) {
        int n = entity.S();
        if (this.mouseOverTargetId != n) {
            this.mouseOverTargetId = n;
            this.mouseOverDelayMs = (long)this.mouseOverDelayValue.B();
            this.mouseOverTimer.reset();
        }
        return this.mouseOverTimer.hasTimeElapsed(this.mouseOverDelayMs);
    }

    private void resetSelectState() {
        this.selectTargetId = -1;
        this.waitingForFirstHit = false;
        this.graceActive = false;
        this.selectTimer.reset();
    }

    private boolean updateSelectFirstHit(Entity entity, boolean bl) {
        if (!this.selectFirstHit.L().booleanValue()) {
            this.resetSelectState();
            return true;
        }
        int n = entity.S();
        if (this.selectTargetId != n) {
            this.selectTargetId = n;
            this.waitingForFirstHit = true;
        }
        this.graceActive = false;
        this.selectTimer.reset();
        if (this.waitingForFirstHit && bl) {
            this.waitingForFirstHit = false;
        }
        return !this.waitingForFirstHit;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.mouseDown = false;
        this.missAttackPending = false;
        this.earlyHitTicks = 0;
        this.resetMouseOverState();
        this.resetSelectState();
        if (this.releasePending) {
            AttackKeyController.Q();
            this.releasePending = false;
            return;
        }
    }

    public AutoClicker() {
        super("Triggerbot", (int)MODULE_ID, Category.g, "");
        this.selectFirstHit = BooleanValue.create(this, "Select first hit", false, "Waits for the opponent to hit you first before attacking");
        this.requireMouseDown = BooleanValue.create(this, "Require mouse down", false);
        this.ignoreActivationClick = BooleanValue.create(this, "Ignore activation click", false, "Ignores first manual click\n(unless already hovering a valid target with attack ready)");
        this.airCrits = BooleanValue.create(this, "Air crits", false, "Won't attack in air unless you will crit");
        this.shieldCheck = BooleanValue.create(this, "Shield check", false, "Won't attack players blocking with shield\nUsing HitSwap module with axes will override this behavior");
        this.targetMissChance = NumberValue.create(this, "Target miss chance", "#", "%", 0.0, 0.0, 100.0, 1.0, "Chance to attack without hovering a valid target when attack is ready");
        this.earlyHitChance = NumberValue.create(this, "Early hit chance", "#", "%", 0.0, 0.0, 100.0, 1.0, "Chance to attack earlier than attack is ready");
        this.limitToItems = BooleanValue.create(this, "Limit to items", false, "Trigger functions only while holding selected items");
        this.allowedItems = LimitValue.n(this, "trigger-alloweditems", "Allowed Items", LimitValue.r, Collections.emptyList());
        this.attackTimer = new TimerUtil();
        this.mouseOverTimer = new TimerUtil();
        this.selectTimer = new TimerUtil();
        this.random = new Random();
        this.requireMouseDown.K(this.ignoreActivationClick);
        this.limitToItems.K(this.allowedItems);
        this.addValue(this.targetFilter, this.extraDelay, this.mouseOverDelayValue, this.requireMouseDown, this.ignoreActivationClick, this.airCrits, this.shieldCheck, this.selectFirstHit, this.targetMissChance, this.earlyHitChance, this.limitToItems, this.allowedItems);
    }

    private boolean isSelectFirstHitSatisfied() {
        return this.selectFirstHit.L() == false || this.selectTargetId == -1 || !this.waitingForFirstHit;
    }

    private void updateSelectGrace() {
        if (!this.selectFirstHit.L().booleanValue() || this.selectTargetId == -1) {
            return;
        }
        if (!this.graceActive) {
            this.graceActive = true;
            this.selectTimer.reset();
            return;
        }
        if (this.selectTimer.hasTimeElapsed(1000L)) {
            this.resetSelectState();
        }
    }

    @EventHandler
    public void onKeyPress(EventKeyPress eventKeyPress) {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!eventKeyPress.isKeybinding(Minecraft.gameSettings().F())) {
            return;
        }
        this.handleInput(eventKeyPress.isDown(), eventKeyPress);
    }

    @EventHandler
    public void onMouseButton(EventMouseButton eventMouseButton) {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!eventMouseButton.isKeybinding(Minecraft.gameSettings().F())) {
            return;
        }
        this.handleInput(eventMouseButton.getButtonState(), eventMouseButton);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException exception) {
        return exception;
    }

    private void handleInput(boolean bl, Event event) {
        if (this.requireMouseDown.L().booleanValue() && bl && !this.mouseDown) {
            this.mouseDown = true;
            if (this.ignoreActivationClick.L().booleanValue() && this.canAttack(false)) {
                RayTraceResult rayTraceResult;
                boolean bl2 = false;
                if (!this.isAttackReady(-this.extraDelay.s$src$I$vi2lk8())) {
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
            this.attackTimer.reset();
            int n2 = (int)((Double)this.earlyHitChance.K()).doubleValue();
            this.earlyHitTicks = n2 > 0 && this.random.nextInt(100) < n2 ? 2 + this.random.nextInt(2) : 0;
            RayTraceResult rayTraceResult = RotationManager.b.n();
            boolean bl3 = false;
            if (rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity())) {
                Entity entity = rayTraceResult.getEntity();
                boolean bl4 = bl3 = entity.isNotNull() && this.isValidTarget(entity);
            }
            this.missAttackPending = bl3 ? (n = (int)((Double)this.targetMissChance.K()).doubleValue()) > 0 && this.random.nextInt(100) < n : false;
        }
    }
}
