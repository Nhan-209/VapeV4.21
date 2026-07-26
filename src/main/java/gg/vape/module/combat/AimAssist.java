package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.AimAssistRotationSubModule;
import gg.vape.module.combat.AimAssistTargetingSubModule;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private int t = 0;
    private final BooleanValue a;
    public ModeValue F;
    protected final ModeValue p;
    public final ModeOption r;
    public final ModeOption b;
    private final AimAssistTargetingSubModule v;
    public final ModeOption k;
    public final ModeOption P;
    private final NumberValue L;
    private final AimAssistRotationSubModule Z = new AimAssistRotationSubModule(this, "Simple");
    private final LimitValue I;
    private final NumberValue K;
    private final BooleanValue C;
    public ModeValue S;
    private final LimitValue O;
    private final BooleanValue j;
    public final ModeOption H;
    private final BooleanValue o;
    private final BooleanValue D;
    private final ModeOption J;
    private final EntityTargetFilterValue c;
    private final NumberValue A;
    private final BooleanValue Y;
    public final ModeOption s;
    private final NumberValue V;

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
        if (this.S.K() == this.k) {
            arrayList.sort(new EntityAngleComparator());
        } else if (this.S.K() == this.J) {
            arrayList.sort(new EntityDistanceComparator());
        } else if (this.S.K() == this.P) {
            arrayList.sort(new EntityArmorValueComparator());
        } else if (this.S.K() == this.s) {
            arrayList.sort(new EntityEquipmentValueComparator());
        } else if (this.S.K() == this.H) {
            arrayList.sort(new EntityHealthComparator());
        }
        if (!arrayList.isEmpty()) {
            return (EntityLivingBase)arrayList.get(0);
        }
        return null;
    }

    private boolean C(EntityLivingBase entityLivingBase) {
        if (this.C.L().booleanValue()) {
            ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
            if (!this.I.isValid(itemStack, false)) {
                return false;
            }
            return this.c.c(entityLivingBase);
        }
        return this.c.c(entityLivingBase);
    }

    public BooleanValue R() {
        return this.a;
    }

    public BooleanValue r$src$Lgg_vape_value_BooleanValue_$f5ztnc() {
        return this.Y;
    }

    public NumberValue F$src$Lgg_vape_value_NumberValue_$cqv0bx() {
        return this.A;
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
        if (Minecraft.thePlayer().getDistanceToEntity(entityLivingBase) >= (float)((Double)this.V.K()).intValue()) {
            return false;
        }
        if (RotationUtil.a(Minecraft.thePlayer(), entityLivingBase) > ((Double)this.K.K()).intValue() / 2) {
            return false;
        }
        if (Vape.INSTANCE.getFriendManager().isFriend(entityLivingBase)) {
            return false;
        }
        if (entityLivingBase.equals(Minecraft.thePlayer().S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12())) {
            return false;
        }
        return this.C(entityLivingBase);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public NumberValue w$src$Lgg_vape_value_NumberValue_$cwexni() {
        return this.L;
    }

    private boolean a$src$Z$5ejvbf() {
        if (!this.C.L().booleanValue()) {
            return true;
        }
        ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
        return this.I.isValid(itemStack, false);
    }

    public AimAssist() {
        super("AimAssist", -327674, Category.g, "Smoothly aims to closest valid target");
        this.v = new AimAssistTargetingSubModule(this, "Adaptive");
        this.p = ModeValue.create((Object)this, "Mode", "Simple - Lightweight smooth aiming\nAdaptive - Advanced tracking with adaptive behavior", (ModeSelection)this.Z.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.Z.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.v.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx());
        this.c = EntityTargetFilterValue.W(this);
        this.Y = BooleanValue.create(this, "Require mouse down", true, "Only aim while mouse is down");
        this.j = BooleanValue.create(this, "Aim vertically", false, "Aims up and down as well");
        this.a = BooleanValue.create(this, "Strafe increase", false, "Increase speed while strafing away from target");
        this.D = BooleanValue.create(this, "Check block break", false, "Prevents from aiming while breaking blocks");
        this.o = BooleanValue.create(this, "Break blocks whitelist", false);
        this.O = LimitValue.n(this, "aimassist-blockbreak-items", "Items", LimitValue.r, Arrays.asList(new ItemLimitData("pickaxes"), new ItemLimitData("shovels")));
        this.C = BooleanValue.create(this, "Limit to items", false, "AimAssist functions only while holding selected items");
        this.I = LimitValue.N(this, "aimassist-alloweditems", "Allowed Items", LimitValue.r, new ItemLimitData("swords"));
        this.A = NumberValue.create(this, "Vertical speed", "#.#", "", 1.0, 5.0, 10.0);
        this.L = NumberValue.create(this, "Horizontal speed", "#.#", "", 1.0, 5.0, 10.0);
        this.K = NumberValue.create(this, "Max angle", "#", "", 1.0, 180.0, 360.0, 1.0, "Maximum allowed angle to still aim at target");
        this.V = NumberValue.create(this, "Distance", "#.#", "", 1.0, 5.0, 8.0, 0.1, "Maximum distance allowed to still aim at target");
        this.J = new ModeOption("Distance");
        this.k = new ModeOption("Yaw");
        this.s = new ModeOption("Armor");
        this.P = new ModeOption("Threat");
        this.H = new ModeOption("Health");
        this.S = ModeValue.create((Object)this, "Target mode", "How Aimassist should prioritize targets\nArmor/Threat will default to Distance for non player targets", (ModeSelection)this.k, this.k, this.J, this.s, this.P, this.H);
        this.b = new ModeOption("Center");
        this.r = new ModeOption("Closest");
        this.F = ModeValue.create((Object)this, "Target area", "Where Aimassist will aim towards\nCenter: Center of entity\nClosest: Closest position on entity hitbox", (ModeSelection)this.b, this.b, this.r);
        this.j.K(this.A);
        this.C.K(this.I);
        this.C.l(this.I);
        this.o.l(this.O);
        this.o.K(this.O);
        this.D.K(this.o);
        this.addValue(this.p, this.c, this.Y, this.a, this.D, this.o, this.O, this.j, this.A, this.L, this.K, this.V, this.C, this.I, this.F, this.S);
        this.L.C(0);
    }

    public BooleanValue U() {
        return this.j;
    }

    @Override
    public String r() {
        return this.L.c();
    }

    @Nullable
    public EntityLivingBase q$src$Lgg_vape_wrapper_impl_EntityLivingBase_$8dbhmm() {
        if (this.Z.J$src$Z$gcqtyf()) {
            return this.Z.v();
        }
        if (this.v.J$src$Z$gcqtyf()) {
            return this.v.S$src$Lgg_vape_wrapper_impl_EntityLivingBase_$15eeuu3();
        }
        return null;
    }

    public NumberValue Q$src$Lgg_vape_value_NumberValue_$5j6eyg() {
        return this.K;
    }

    public NumberValue W() {
        return this.V;
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
        boolean bl = this.D.L();
        if (bl && this.o.L().booleanValue()) {
            bl = this.O.A(entityPlayerSP.getHeldItemHand());
        }
        if (bl) {
            boolean bl2;
            RayTraceResult rayTraceResult = RayTraceUtil.o();
            boolean bl3 = bl2 = rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block());
            if (bl2) {
                this.t = 250;
                return false;
            }
            if (this.t > 0) {
                --this.t;
            }
            if (this.t > 0) {
                return false;
            }
        }
        return this.a$src$Z$5ejvbf();
    }

    public EntityLivingBase a_xa_0_q() {
        return this.q$src$Lgg_vape_wrapper_impl_EntityLivingBase_$8dbhmm();
    }
}

