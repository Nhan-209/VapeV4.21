package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventEntityJoinWorld;
import gg.vape.event.impl.EventPlayerUseItem;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.utility.autopearl.AutoPearlAimLock;
import gg.vape.module.utility.autopearl.AutoPearlPointRotationController;
import gg.vape.module.utility.autopearl.AutoPearlRotationController;
import gg.vape.module.utility.autopearl.AutoPearlState;
import gg.vape.module.utility.autopearl.AutoPearlTrackedPearl;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MathUtil;
import gg.vape.utils.ProjectilePitchUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityEnderPearl;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AutoPearl
extends Mod {
    private final TimerUtil cooldownTimer;
    private final Set<Integer> handledPearlIds;
    private final BooleanValue verticalCheckValue;
    private AutoPearlState state;
    private final ModeValue modeValue;
    private static final float MAX_PLAYER_DISTANCE = 10.0f;
    private final TimerUtil throwDelayTimer;
    private final BooleanValue limitToItemsValue;
    private static final long MODULE_ID = -5394869744341811456L;
    private final ModeOption aggroMode = new ModeOption("Aggro");
    private int savedHotbarSlot;
    private static final boolean DEBUG = false;
    private final TimerUtil stateTimeoutTimer;
    private final Map<Integer, AutoPearlTrackedPearl> trackedPearls;
    private final TimerUtil pearlUseTimer;
    private final NumberValue cooldownValue;
    private final NumberValue aimSpeedValue = NumberValue.create(this, "Aim speed", "#.#", "", 0.1, 5.0, 15.0, 0.5, "How quickly your aim moves towards the pearl");
    private final BooleanValue silentAimValue;
    private final ModeOption onBindMode;
    private final RotationControlClaim rotationClaim = SharedModuleControlClaims.I;
    private final NumberValue minHealthValue;
    @Nullable
    private AutoPearlAimLock aimLock = null;
    private final LimitValue allowedItemsValue = LimitValue.N(this, "autopearl-alloweditems", "Allowed items", LimitValue.r, new ItemLimitData("swords"), new ItemLimitData("ender pearl"), new ItemLimitData("hand"));
    private final NumberValue distanceLimitValue;
    private AutoPearlTrackedPearl pendingPearl;
    private boolean keyBindPressed = false;
    private final NumberValue angleLimitValue;

    private boolean shouldThrowAt(AutoPearlTrackedPearl autoPearlTrackedPearl) {
        EntityEnderPearl entityEnderPearl = autoPearlTrackedPearl.P();
        EntityPlayer entityPlayer = autoPearlTrackedPearl.A();
        if (!this.hasPearlInInventory()) {
            return false;
        }
        if (!this.isHoldingAllowedItem()) {
            return false;
        }
        if (!this.hasEnoughHealth()) {
            return false;
        }
        if (this.isOnCooldown()) {
            return false;
        }
        if (this.isAimLockInvalid()) {
            return false;
        }
        if (!this.isPearlActive(entityEnderPearl)) {
            return false;
        }
        if (!this.isNotSelf(entityPlayer)) {
            return false;
        }
        Vec3 vec3 = autoPearlTrackedPearl.a();
        if (!this.isPearlFartherThanOwner(vec3, entityPlayer)) {
            return false;
        }
        if (!this.isLandingValid(vec3)) {
            return false;
        }
        return this.isPearlUnhandled(entityEnderPearl);
    }

    private void resetState() {
        if (this.aimLock != null && RotationManager.b.w() == this.aimLock.r()) {
            RotationManager.b.v(this.aimLock.r());
        }
        this.rotationClaim.X(this);
        this.aimLock = null;
        this.pendingPearl = null;
        this.state = AutoPearlState.ACQUIRING_PEARL;
        this.stateTimeoutTimer.reset();
    }


    private boolean isNotSelf(EntityPlayer entityPlayer) {
        return !entityPlayer.isInstance(MappedClasses.z5);
    }

    private boolean isPearlInRange(Vec3 vec3) {
        double d;
        double d2;
        double d3 = vec3.getX();
        double d4 = vec3.getY();
        double d5 = vec3.getZ();
        float f = Math.abs(this.angleToPoint(d3, d5));
        if ((double)f > (d2 = (Double)this.angleLimitValue.K() / 2.0)) {
            return false;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        double d6 = entityPlayerSP.i(d3, d4, d5);
        if (d6 <= (d = ((Double)this.distanceLimitValue.K()).doubleValue())) {
            return false;
        }
        if (!this.verticalCheckValue.L().booleanValue()) {
            return true;
        }
        double d7 = entityPlayerSP.N();
        double d8 = d4 - d7;
        return d8 < 7.0;
    }

    @Override
    public void onDisable() {
        this.resetState();
    }

    private void beginAimLock(AutoPearlTrackedPearl autoPearlTrackedPearl) {
        this.stateTimeoutTimer.reset();
        Vec3 vec3 = autoPearlTrackedPearl.a();
        FixedRotationController fixedRotationController = this.buildRotationController(vec3);
        this.aimLock = new AutoPearlAimLock(autoPearlTrackedPearl.P(), autoPearlTrackedPearl.A(), fixedRotationController, vec3, null);
        this.state = AutoPearlState.ACQUIRING_AIMLOCK;
    }

    @EventHandler
    public void onWorldChange(EventWorldChange eventWorldChange) {
        this.resetState();
    }

    private boolean isOnCooldown() {
        long l = ((Double)this.cooldownValue.K()).longValue();
        if ((double)l <= 0.0) {
            return false;
        }
        long l2 = this.cooldownTimer.getLastMS();
        long l3 = l2 / 1000L;
        return l3 < l;
    }

    private boolean isPearlActive(EntityEnderPearl entityEnderPearl) {
        if (entityEnderPearl.b$src$Z$fqlxe4()) {
            return false;
        }
        return !entityEnderPearl.M$src$Z$ff28xj();
    }

    private boolean isHoldingAllowedItem() {
        Item item;
        if (!this.limitToItemsValue.L().booleanValue()) {
            return true;
        }
        ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
        if (!itemStack.isNull() && (item = itemStack.getItem()).isInstance(MappedClasses.ZH)) {
            return true;
        }
        return this.allowedItemsValue.isValid(itemStack, false);
    }

    @EventHandler
    public void onWorldChange2(EventWorldChange eventWorldChange) {
        this.resetState();
    }

    @Override
    public void onEnable() {
        this.stateTimeoutTimer.reset();
        if (this.onBindMode.o()) {
            this.resetState();
        }
    }

    private boolean hasEnoughHealth() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || entityPlayerSP.M$src$Z$ff28xj()) {
            return false;
        }
        float f = ((Double)this.minHealthValue.K()).floatValue();
        float f2 = entityPlayerSP.w$src$F$15l9epb();
        return f2 >= f;
    }

    @Nullable
    private FixedRotationController buildRotationController(Vec3 vec3) {
        FixedRotationController fixedRotationController;
        Float f = this.computeThrowPitch(vec3);
        if (f == null) {
            return null;
        }
        FixedRotationController fixedRotationController2 = fixedRotationController = this.silentAimValue.L() != false ? new AutoPearlRotationController(this, f) : new AutoPearlPointRotationController(this, vec3, f);
        if (fixedRotationController instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)fixedRotationController).J(vec3);
        }
        fixedRotationController.k(true);
        fixedRotationController.t(0.5f);
        fixedRotationController.A(false);
        fixedRotationController.U(true);
        fixedRotationController.w(false);
        fixedRotationController.z(true);
        fixedRotationController.s(true);
        fixedRotationController.Y(((Double)this.aimSpeedValue.K()).floatValue() * 0.2f);
        fixedRotationController.D(true);
        if (fixedRotationController instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)fixedRotationController).b(false);
        }
        return fixedRotationController;
    }

    @EventHandler
    public void onPlayerUseItem(EventPlayerUseItem eventPlayerUseItem) {
        if (((Double)this.cooldownValue.K()).longValue() <= 0L) {
            return;
        }
        ItemStack itemStack = eventPlayerUseItem.getItemStack();
        if (itemStack.isNull()) {
            return;
        }
        Item item = itemStack.getItem();
        if (item.isNull() || !item.isInstance(MappedClasses.ZH)) {
            return;
        }
        this.pearlUseTimer.reset();
    }

    private boolean isPearlFartherThanOwner(@NotNull Vec3 vec3, @NotNull EntityPlayer entityPlayer) {
        double d;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        double d2 = entityPlayerSP.i(vec3.getX(), vec3.getY(), vec3.getZ());
        return d2 > (d = (double)entityPlayerSP.getDistanceToEntity(entityPlayer));
    }

    @Nullable
    private EntityPlayer findNearestPlayer(EntityEnderPearl entityEnderPearl) {
        List list = Minecraft.theWorld().S();
        EntityPlayer entityPlayer = null;
        float f = Float.MAX_VALUE;
        for (Object e : list) {
            EntityPlayer entityPlayer2;
            float f2;
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.Yl) || !((f2 = (entityPlayer2 = new EntityPlayer(entity)).getDistanceToEntity(entityEnderPearl)) < f) || !(f2 <= 10.0f)) continue;
            entityPlayer = entityPlayer2;
            f = f2;
        }
        return entityPlayer;
    }

    private boolean isAimLockInvalid() {
        if (this.aimLock == null) {
            return false;
        }
        return !AutoPearlAimLock.E(this.aimLock).V$src$Z$lb4tvc();
    }

    private float angleToPoint(double d, double d2) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d3 = d - entityPlayerSP.z();
        double d4 = d2 - entityPlayerSP.h();
        double d5 = d4 < 0.0 && d3 < 0.0 ? 90.0 + Math.toDegrees(Math.atan(d4 / d3)) : (d4 < 0.0 && d3 > 0.0 ? -90.0 + Math.toDegrees(Math.atan(d4 / d3)) : Math.toDegrees(-Math.atan(d3 / d4)));
        return MathUtil.wrapAngleTo180(-(entityPlayerSP.J() - (float)d5));
    }

    private boolean isPearlUnhandled(EntityEnderPearl entityEnderPearl) {
        return !this.handledPearlIds.contains(entityEnderPearl.S());
    }

    @Nullable
    private AutoPearlTrackedPearl trackPearl(EntityEnderPearl entityEnderPearl) {
        AutoPearlTrackedPearl autoPearlTrackedPearl = this.trackedPearls.get(entityEnderPearl.S());
        if (autoPearlTrackedPearl != null) {
            return autoPearlTrackedPearl;
        }
        EntityPlayer entityPlayer = this.findNearestPlayer(entityEnderPearl);
        if (entityPlayer == null || entityPlayer.isNull()) {
            return null;
        }
        autoPearlTrackedPearl = new AutoPearlTrackedPearl(entityEnderPearl, entityPlayer, null);
        this.trackedPearls.put(entityEnderPearl.S(), autoPearlTrackedPearl);
        return autoPearlTrackedPearl;
    }

    private int findPearlSlot() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return -1;
        }
        Container container = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        if (container.isNull()) {
            return -1;
        }
        InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (inventoryPlayer.isNull()) {
            return -1;
        }
        for (int i = 36; i < 45; ++i) {
            Item item;
            Slot slot = container.getSlot(i);
            if (!slot.v() || !(item = slot.I().getItem()).isInstance(MappedClasses.ZH)) continue;
            return i;
        }
        return -1;
    }

    public void tryThrowAt(AutoPearlTrackedPearl autoPearlTrackedPearl) {
        if (!this.shouldThrowAt(autoPearlTrackedPearl)) {
            return;
        }
        this.beginAimLock(autoPearlTrackedPearl);
    }

    @Nullable
    private Float computeThrowPitch(Vec3 vec3) {
        float f;
        double d;
        double d2;
        double d3;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP == null || entityPlayerSP.isNull()) {
            return null;
        }
        float f2 = entityPlayerSP.J();
        float f3 = entityPlayerSP.V();
        double d4 = entityPlayerSP.z();
        double d5 = entityPlayerSP.U();
        double d6 = entityPlayerSP.h();
        float f4 = ProjectilePitchUtil.calculatePitch(d4 -= (double)(MathUtil.cos(f2 / 180.0f * (float)Math.PI) * 0.16f), d5 -= (double)0.1f, d6 -= (double)(MathUtil.sin(f2 / 180.0f * (float)Math.PI) * 0.16f), d3 = vec3.getX(), d2 = vec3.getY(), d = vec3.getZ());
        if (Float.isNaN(f4)) {
            return null;
        }
        float f5 = -90.0f;
        float f6 = 90.0f;
        float f7 = f = f4 < 0.0f ? f4 - 5.0f : (f4 > 0.0f ? f4 + 5.0f : f4);
        if (f < f5) {
            f = f5;
        } else if (f > f6) {
            f = f6;
        }
        return Float.valueOf(f);
    }

    @Nullable
    private List<AutoPearlTrackedPearl> collectTrackedPearls() {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return null;
        }
        ArrayList<EntityEnderPearl> arrayList = new ArrayList<EntityEnderPearl>();
        ArrayList<EntityPlayer> arrayList2 = new ArrayList<EntityPlayer>();
        for (Object object : worldClient.S()) {
            if (MappedClasses.Zg.isInstance(object)) {
                arrayList.add(new EntityEnderPearl(object));
                continue;
            }
            if (!MappedClasses.Yl.isInstance(object)) continue;
            arrayList2.add(new EntityPlayer(object));
        }
        ArrayList arrayList3 = new ArrayList();
        for (EntityEnderPearl entityEnderPearl : arrayList) {
            AutoPearlTrackedPearl autoPearlTrackedPearl = this.trackedPearls.get(entityEnderPearl.S());
            if (autoPearlTrackedPearl != null) {
                arrayList3.add(autoPearlTrackedPearl);
                continue;
            }
            float f = Float.MAX_VALUE;
            EntityPlayer entityPlayer = null;
            for (EntityPlayer entityPlayer2 : arrayList2) {
                float f2 = entityPlayer2.getDistanceToEntity(entityEnderPearl);
                if (!(f2 < f) || !(f2 <= 10.0f)) continue;
                f = f2;
                entityPlayer = entityPlayer2;
            }
            if (entityPlayer == null) continue;
            autoPearlTrackedPearl = new AutoPearlTrackedPearl(entityEnderPearl, entityPlayer, null);
            this.trackedPearls.put(entityEnderPearl.S(), autoPearlTrackedPearl);
            arrayList3.add(autoPearlTrackedPearl);
        }
        Collections.reverse(arrayList3);
        return arrayList3;
    }

    @EventHandler
    public void onEntityJoinWorld(EventEntityJoinWorld eventEntityJoinWorld) {
        Entity entity = eventEntityJoinWorld.getEntity();
        if (!entity.isInstance(MappedClasses.Zg)) {
            return;
        }
        EntityEnderPearl entityEnderPearl = new EntityEnderPearl(entity.getObject());
        AutoPearlTrackedPearl autoPearlTrackedPearl = this.trackPearl(entityEnderPearl);
        if (autoPearlTrackedPearl == null) {
            return;
        }
        EntityPlayer entityPlayer = autoPearlTrackedPearl.A();
        if (!entityPlayer.isNull()) {
            if (entityPlayer.isInstance(MappedClasses.z5)) {
                if (((Double)this.cooldownValue.K()).longValue() > 0L) {
                    long l = 1000L;
                    long l2 = this.pearlUseTimer.getLastMS();
                    if (l2 <= 1000L) {
                        this.cooldownTimer.reset();
                    }
                }
            } else {
                this.pendingPearl = autoPearlTrackedPearl;
            }
        }
    }

    private boolean hasPearlInInventory() {
        int n = this.findPearlSlot();
        return n != -1;
    }

    private boolean isStateStillValid() {
        if (!this.isHoldingAllowedItem()) {
            return false;
        }
        if (!this.hasEnoughHealth()) {
            return false;
        }
        if (this.aimLock != null) {
            Vec3 vec3 = this.aimLock.b();
            return vec3 == null || vec3.isNull() || this.isLandingValid(vec3);
        }
        return true;
    }

    private void logDebug(String string) {
    }

    private boolean isLandingValid(Vec3 vec3) {
        if (vec3 == null || vec3.isNull()) {
            return false;
        }
        if (!this.isPearlInRange(vec3)) {
            return false;
        }
        Float f = this.computeThrowPitch(vec3);
        return f != null;
    }

    public AutoPearl() {
        super("AutoPearl", (int)MODULE_ID, Category.Y, "Aims and throws a pearl at an enemies pearl trajectory.");
        this.trackedPearls = new HashMap<Integer, AutoPearlTrackedPearl>();
        this.handledPearlIds = new HashSet<Integer>();
        this.throwDelayTimer = new TimerUtil();
        this.cooldownValue = NumberValue.E(this, "Pearl cooldown", "#.#", "sec", 0.0, 1.0, 15.0, "Minimum delay between pearl throws");
        this.distanceLimitValue = NumberValue.create(this, "Distance limit", "#.#", "m", 0.0, 6.0, 10.0, 0.1, "The minimum distance a pearl needs to land away from you\nin order to pearl towards it.");
        this.angleLimitValue = NumberValue.create(this, "Angle limit", "#", "", 30.0, 180.0, 360.0, 5.0, "Maximum angle from your crosshair a pearl can be\nin order to be chased");
        this.limitToItemsValue = BooleanValue.create(this, "Limit to items", true, "AutoPearl only functions while holding selected items");
        this.minHealthValue = NumberValue.create(this, "Min health", "#", "HP", 1.0, 5.0, 20.0, 1.0, "Minimum amount of health you must have\nin order to throw a pearl");
        this.onBindMode = new ModeOption("On bind");
        this.modeValue = ModeValue.create((Object)this, "Mode", "On bind - searches for thrown pearls and throws upon pressing bind\nAggro - Throws pearl as soon as enemy throws theirs", (ModeSelection)this.onBindMode, this.onBindMode, this.aggroMode);
        this.silentAimValue = BooleanValue.create(this, "Silent aim", false, "Uses Silent Aim system");
        this.stateTimeoutTimer = new TimerUtil();
        this.cooldownTimer = new TimerUtil();
        this.pearlUseTimer = new TimerUtil();
        this.verticalCheckValue = BooleanValue.create(this, "Vertical check", false, "Doesn't attempt to chase pearls that are landing a certain amount above your current Y position.");
        this.state = AutoPearlState.ACQUIRING_PEARL;
        this.limitToItemsValue.K(this.allowedItemsValue);
        this.limitToItemsValue.l(this.allowedItemsValue);
        this.addValue(this.modeValue, this.aimSpeedValue, this.angleLimitValue, this.minHealthValue, this.distanceLimitValue, this.verticalCheckValue, this.cooldownValue, this.silentAimValue, this.limitToItemsValue, this.allowedItemsValue);
        this.rotationClaim.l(this, 7);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        AutoPearlState previousState = this.state;
        boolean done = false;
        if (!this.state.equals((Object)AutoPearlState.ACQUIRING_PEARL) && this.stateTimeoutTimer.hasTimeElapsed(5000L)) {
            this.resetState();
        }
        block7: do {
            previousState = this.state;
            if (!(this.state.equals((Object)AutoPearlState.ACQUIRING_PEARL) || this.state.equals((Object)AutoPearlState.PENDING_RESET) || this.isStateStillValid())) {
                this.resetState();
                done = true;
                break;
            }
            switch (this.state) {
                case ACQUIRING_PEARL: {
                    if (this.aimLock != null) {
                        this.state = AutoPearlState.ACQUIRING_AIMLOCK;
                        break;
                    }
                    if (this.onBindMode.o()) {
                        List<AutoPearlTrackedPearl> trackedPearls = this.collectTrackedPearls();
                        for (AutoPearlTrackedPearl trackedPearl : trackedPearls) {
                            if (!this.shouldThrowAt(trackedPearl)) continue;
                            this.tryThrowAt(trackedPearl);
                            break;
                        }
                        if (!this.state.equals((Object)previousState)) continue block7;
                        this.Y(false);
                        break;
                    }
                    if (this.pendingPearl == null) break;
                    if (this.shouldThrowAt(this.pendingPearl)) {
                        this.tryThrowAt(this.pendingPearl);
                    }
                    this.pendingPearl = null;
                    break;
                }
                case ACQUIRING_AIMLOCK: {
                    boolean claimed;
                    boolean claimResult = claimed = this.rotationClaim.U(this) || this.rotationClaim.h(this, this.silentAimValue.L());
                    if (!claimed) continue block7;
                    RotationManager.b.S(this.aimLock.r());
                    this.handledPearlIds.add(this.aimLock.P().S());
                    this.state = AutoPearlState.PENDING_AIMJOB;
                    break;
                }
                case PENDING_AIMJOB: {
                    if (!this.aimLock.r().V$src$Z$lb4tvc()) break;
                    this.state = AutoPearlState.PENDING_THROW;
                    this.throwDelayTimer.reset();
                    break;
                }
                case PENDING_THROW: {
                    if (!this.throwDelayTimer.hasTimeElapsed(100L)) break;
                    Object player = Minecraft.thePlayer();
                    Object inventory = ((EntityPlayer)player).V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
                    if (((Wrapper)player).isNull() || ((Wrapper)inventory).isNull()) continue block7;
                    int pearlSlot = this.findPearlSlot();
                    if (pearlSlot == -1) {
                        this.Y(false);
                        break;
                    }
                    int hotbarIndex = pearlSlot - 36;
                    this.savedHotbarSlot = ((InventoryPlayer)inventory).v();
                    ((InventoryPlayer)inventory).g(hotbarIndex);
                    KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBindingHelper.v(keyBinding, true, true);
                    this.keyBindPressed = true;
                    this.cooldownTimer.reset();
                    this.state = AutoPearlState.PENDING_RESET;
                    done = true;
                    break;
                }
                case PENDING_RESET: {
                    Object keyBind = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBindingHelper.v((KeyBinding)keyBind, false, false);
                    Object player = Minecraft.thePlayer();
                    Object inventory = ((EntityPlayer)player).V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
                    if (((Wrapper)player).isNull() || ((Wrapper)inventory).isNull()) continue block7;
                    ((InventoryPlayer)inventory).g(this.savedHotbarSlot);
                    this.resetState();
                    if (!this.onBindMode.o()) break;
                    this.Y(false);
                    done = true;
                }
            }
        } while (!previousState.equals((Object)this.state) && !done);
    }
}
