package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.AimAssistRotationSubModule;
import gg.vape.module.combat.AimAssistTargetingSubModule;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.EntityAngleComparator;
import gg.vape.utils.EntityArmorValueComparator;
import gg.vape.utils.EntityDistanceComparator;
import gg.vape.utils.EntityEquipmentValueComparator;
import gg.vape.utils.EntityHealthComparator;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PlayerControllerMP;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.Nullable;

public class AimAssist
extends Mod {
    private int blockBreakCooldown = 0;
    private final BooleanValue strafeIncrease;
    public ModeValue F;
    protected final ModeValue mode;
    public final ModeOption r;
    public final ModeOption centerMode;
    private final AimAssistTargetingSubModule adaptiveTargeting;
    public final ModeOption yawMode;
    public final ModeOption threatMode;
    private final NumberValue horizontalSpeed;
    private final AimAssistRotationSubModule simpleRotation = new AimAssistRotationSubModule(this, "Simple");
    private final LimitValue allowedItems;
    private final NumberValue maxAngle;
    private final BooleanValue limitToItems;
    public ModeValue targetMode;
    private final LimitValue blockBreakItems;
    private final BooleanValue aimVertically;
    public final ModeOption healthMode;
    private final BooleanValue breakBlocksWhitelist;
    private final BooleanValue checkBlockBreak;
    private final ModeOption distanceMode;
    private final EntityTargetFilterValue targetFilter;
    private final NumberValue verticalSpeed;
    private final BooleanValue requireMouseDown;
    public final ModeOption armorMode;
    private final NumberValue distance;

    @Nullable
    public EntityLivingBase M$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1qf3v8a() {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return null;
        }
        ArrayList<Entity> arrayList = new ArrayList<Entity>();
        ArrayList arrayList2 = new ArrayList(Minecraft.theWorld().z());
        for (Object e : arrayList2) {
            EntityLivingBase entityLivingBase;
            Entity entity = new Entity(e);
            if (ClientSettings.H && entity.isInstance(MappedClasses.FT) || !entity.isInstance(MappedClasses.zm) || !this.o(entityLivingBase = new EntityLivingBase(e))) continue;
            arrayList.add(entityLivingBase);
        }
        if (this.targetMode.K() == this.yawMode) {
            arrayList.sort(new EntityAngleComparator());
        } else if (this.targetMode.K() == this.distanceMode) {
            arrayList.sort(new EntityDistanceComparator());
        } else if (this.targetMode.K() == this.threatMode) {
            arrayList.sort(new EntityArmorValueComparator());
        } else if (this.targetMode.K() == this.armorMode) {
            arrayList.sort(new EntityEquipmentValueComparator());
        } else if (this.targetMode.K() == this.healthMode) {
            arrayList.sort(new EntityHealthComparator());
        }
        if (!arrayList.isEmpty()) {
            return (EntityLivingBase)arrayList.get(0);
        }
        return null;
    }

    private boolean passesItemFilter(EntityLivingBase entityLivingBase) {
        if (this.limitToItems.L().booleanValue()) {
            ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
            if (!this.allowedItems.isValid(itemStack, false)) {
                return false;
            }
            return this.targetFilter.c(entityLivingBase);
        }
        return this.targetFilter.c(entityLivingBase);
    }

    public BooleanValue R() {
        return this.strafeIncrease;
    }

    public BooleanValue r$src$Lgg_vape_value_BooleanValue_$f5ztnc() {
        return this.requireMouseDown;
    }

    public NumberValue F$src$Lgg_vape_value_NumberValue_$cqv0bx() {
        return this.verticalSpeed;
    }

    public boolean o(EntityLivingBase entityLivingBase) {
        if (entityLivingBase.isNull()) {
            return false;
        }
        if (entityLivingBase.equals(Minecraft.thePlayer())) {
            return false;
        }
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f || entityLivingBase.M$src$Z$ff28xj()) {
            return false;
        }
        if (Minecraft.thePlayer().getDistanceToEntity(entityLivingBase) >= (float)((Double)this.distance.K()).intValue()) {
            return false;
        }
        if (RotationUtil.a(Minecraft.thePlayer(), entityLivingBase) > ((Double)this.maxAngle.K()).intValue() / 2) {
            return false;
        }
        if (Vape.INSTANCE.getFriendManager().isFriend(entityLivingBase)) {
            return false;
        }
        if (entityLivingBase.equals(Minecraft.thePlayer().S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12())) {
            return false;
        }
        return this.passesItemFilter(entityLivingBase);
    }


    public NumberValue w$src$Lgg_vape_value_NumberValue_$cwexni() {
        return this.horizontalSpeed;
    }

    private boolean hasRequiredItem() {
        if (!this.limitToItems.L().booleanValue()) {
            return true;
        }
        ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
        return this.allowedItems.isValid(itemStack, false);
    }

    public AimAssist() {
        super("AimAssist", -327674, Category.g, "Smoothly aims to closest valid target");
        this.adaptiveTargeting = new AimAssistTargetingSubModule(this, "Adaptive");
        this.mode = ModeValue.create((Object)this, "Mode", "Simple - Lightweight smooth aiming\nAdaptive - Advanced tracking with adaptive behavior", (ModeSelection)this.simpleRotation.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.simpleRotation.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.adaptiveTargeting.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx());
        this.targetFilter = EntityTargetFilterValue.W(this);
        this.requireMouseDown = BooleanValue.create(this, "Require mouse down", true, "Only aim while mouse is down");
        this.aimVertically = BooleanValue.create(this, "Aim vertically", false, "Aims up and down as well");
        this.strafeIncrease = BooleanValue.create(this, "Strafe increase", false, "Increase speed while strafing away from target");
        this.checkBlockBreak = BooleanValue.create(this, "Check block break", false, "Prevents from aiming while breaking blocks");
        this.breakBlocksWhitelist = BooleanValue.create(this, "Break blocks whitelist", false);
        this.blockBreakItems = LimitValue.n(this, "aimassist-blockbreak-items", "Items", LimitValue.r, Arrays.asList(new ItemLimitData("pickaxes"), new ItemLimitData("shovels")));
        this.limitToItems = BooleanValue.create(this, "Limit to items", false, "AimAssist functions only while holding selected items");
        this.allowedItems = LimitValue.N(this, "aimassist-alloweditems", "Allowed Items", LimitValue.r, new ItemLimitData("swords"));
        this.verticalSpeed = NumberValue.create(this, "Vertical speed", "#.#", "", 1.0, 5.0, 10.0);
        this.horizontalSpeed = NumberValue.create(this, "Horizontal speed", "#.#", "", 1.0, 5.0, 10.0);
        this.maxAngle = NumberValue.create(this, "Max angle", "#", "", 1.0, 180.0, 360.0, 1.0, "Maximum allowed angle to still aim at target");
        this.distance = NumberValue.create(this, "Distance", "#.#", "", 1.0, 5.0, 8.0, 0.1, "Maximum distance allowed to still aim at target");
        this.distanceMode = new ModeOption("Distance");
        this.yawMode = new ModeOption("Yaw");
        this.armorMode = new ModeOption("Armor");
        this.threatMode = new ModeOption("Threat");
        this.healthMode = new ModeOption("Health");
        this.targetMode = ModeValue.create((Object)this, "Target mode", "How Aimassist should prioritize targets\nArmor/Threat will default to Distance for non player targets", (ModeSelection)this.yawMode, this.yawMode, this.distanceMode, this.armorMode, this.threatMode, this.healthMode);
        this.centerMode = new ModeOption("Center");
        this.r = new ModeOption("Closest");
        this.F = ModeValue.create((Object)this, "Target area", "Where Aimassist will aim towards\nCenter: Center of entity\nClosest: Closest position on entity hitbox", (ModeSelection)this.centerMode, this.centerMode, this.r);
        this.aimVertically.K(this.verticalSpeed);
        this.limitToItems.K(this.allowedItems);
        this.limitToItems.l(this.allowedItems);
        this.breakBlocksWhitelist.l(this.blockBreakItems);
        this.breakBlocksWhitelist.K(this.blockBreakItems);
        this.checkBlockBreak.K(this.breakBlocksWhitelist);
        this.addValue(this.mode, this.targetFilter, this.requireMouseDown, this.strafeIncrease, this.checkBlockBreak, this.breakBlocksWhitelist, this.blockBreakItems, this.aimVertically, this.verticalSpeed, this.horizontalSpeed, this.maxAngle, this.distance, this.limitToItems, this.allowedItems, this.F, this.targetMode);
        this.horizontalSpeed.C(0);
    }

    public BooleanValue U() {
        return this.aimVertically;
    }

    @Override
    public String r() {
        return this.horizontalSpeed.c();
    }

    @Nullable
    public EntityLivingBase q$src$Lgg_vape_wrapper_impl_EntityLivingBase_$8dbhmm() {
        if (this.simpleRotation.J$src$Z$gcqtyf()) {
            return this.simpleRotation.v();
        }
        if (this.adaptiveTargeting.J$src$Z$gcqtyf()) {
            return this.adaptiveTargeting.S$src$Lgg_vape_wrapper_impl_EntityLivingBase_$15eeuu3();
        }
        return null;
    }

    public NumberValue Q$src$Lgg_vape_value_NumberValue_$5j6eyg() {
        return this.maxAngle;
    }

    public NumberValue W() {
        return this.distance;
    }

    public boolean K() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        PlayerControllerMP playerControllerMP = Minecraft.playerController();
        if (entityPlayerSP.isNull() || playerControllerMP.isNull()) {
            return false;
        }
        if (SharedModuleControlClaims.l.s()) {
            return false;
        }
        boolean bl = this.checkBlockBreak.L();
        if (bl && this.breakBlocksWhitelist.L().booleanValue()) {
            bl = this.blockBreakItems.A(entityPlayerSP.getHeldItemHand());
        }
        if (bl) {
            boolean bl2;
            RayTraceResult rayTraceResult = RayTraceUtil.o();
            boolean bl3 = bl2 = rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block());
            if (bl2) {
                this.blockBreakCooldown = 250;
                return false;
            }
            if (this.blockBreakCooldown > 0) {
                --this.blockBreakCooldown;
            }
            if (this.blockBreakCooldown > 0) {
                return false;
            }
        }
        return this.hasRequiredItem();
    }

    public EntityLivingBase a_xa_0_q() {
        return this.q$src$Lgg_vape_wrapper_impl_EntityLivingBase_$8dbhmm();
    }
}

