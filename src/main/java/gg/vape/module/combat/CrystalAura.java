package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.MinecraftVersionConstraint;
import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;
import gg.vape.module.combat.CrystalAuraPlacementSubModule;
import gg.vape.module.combat.CrystalAuraTargetSubModule;
import gg.vape.module.combat.crystalaura.ExplosionType;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.Freecam;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.hud.ActiveModuleStackFrame;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MathUtil;
import gg.vape.utils.datas.DirectionalPosition;
import gg.vape.utils.math.NumericMathUtil;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.Enchantments;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLiving;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.MonsterAttributesBridge;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.World;

public class CrystalAura
extends Mod {
    public static final float Z = 0.2f;
    public final ModeValue J;
    public static final float v = 2.0f;
    private CrystalAuraPlacementSubModule A = new CrystalAuraPlacementSubModule(this, "Auto");
    private static Freecam L;
    public static final float r = 25.0f;
    private CrystalAuraTargetSubModule S = new CrystalAuraTargetSubModule(this, "Manual");
    private final RotationControlClaim t = SharedModuleControlClaims.I;
    public static final float F = 20.0f;
    private static final int Y;

    public RotationManager I$src$Lgg_vape_rotation_RotationManager_$10bv4gd() {
        return RotationManager.b;
    }

    static {
        Y = 4;
    }

    public float j(EntityPlayerSP entityPlayerSP, float f) {
        f = this.B(entityPlayerSP, f);
        f = this.b(entityPlayerSP, f);
        return Math.max(f, 0.0f);
    }

    public int z(EntityPlayerSP entityPlayerSP) {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
            if (!this.p(itemStack)) continue;
            return i;
        }
        return -1;
    }

    public static float E(float f, float f2, float f3) {
        float f4 = 2.0f + f3 / 4.0f;
        float f5 = NumericMathUtil.w(f2 - f / f4, f2 * 0.2f, 20.0f);
        float f6 = f5 / 25.0f;
        float f7 = 1.0f - f6;
        return f * f7;
    }

    public boolean W() {
        if (L == null) {
            L = Vape.INSTANCE.getModManager().getMod(Freecam.class);
        }
        return L != null && L.r$src$Z$14eylz9() || this.t.e(this) && !this.t.h(this, true);
    }

    protected float b(EntityPlayerSP entityPlayerSP, float f) {
        if (entityPlayerSP.i(PotionRegistry.P)) {
            int n = (entityPlayerSP.b(PotionRegistry.P).L() + 1) * 5;
            int n2 = 25 - n;
            float f2 = f * (float)n2;
            f = Math.max(f2 / 25.0f, 0.0f);
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        float f3 = CrystalAura.w(entityPlayerSP);
        if (f3 > 0.0f) {
            f = CrystalAura.Y(f, f3);
        }
        return f;
    }

    @Override
    public String r() {
        if (this.A.J$src$Z$gcqtyf() && this.A.r$src$Z$14eylz9()) {
            return this.A.r();
        }
        if (this.S.J$src$Z$gcqtyf() && this.S.r$src$Z$14eylz9()) {
            return this.S.r();
        }
        return "";
    }

    public boolean u(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        if (itemMappingEntry != null) {
            return "minecraft:end_crystal".equals(itemMappingEntry.M()) || "end_crystal".equals(itemMappingEntry.q());
        }
        return itemStack.f().toLowerCase().contains("end_crystal");
    }

    public RotationControlClaim X$src$Lgg_vape_rotation_RotationControlClaim_$1j4bdqm() {
        return this.t;
    }

    public int Q(EntityPlayerSP entityPlayerSP) {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
            if (!this.u(itemStack)) continue;
            return i;
        }
        return -1;
    }

    public boolean n(Vec3 vec3, ExplosionType explosionType, EntityPlayerSP entityPlayerSP, World world, boolean bl, double d) {
        float f;
        float f2 = this.k(entityPlayerSP, explosionType, vec3, world);
        if (f2 <= 0.0f) {
            return true;
        }
        if (bl && (f = entityPlayerSP.w$src$F$15l9epb() + entityPlayerSP.p()) <= f2) {
            return false;
        }
        return (double)f2 <= d;
    }

    public Vec3 B(DirectionalPosition directionalPosition) {
        switch (directionalPosition.X()) {
            case 1: {
                return Vec3.create((double)directionalPosition.B() + 0.5, directionalPosition.E() + 1, (double)directionalPosition.A() + 0.5);
            }
            case 0: {
                return Vec3.create((double)directionalPosition.B() + 0.5, directionalPosition.E(), (double)directionalPosition.A() + 0.5);
            }
            case 2: {
                return Vec3.create((double)directionalPosition.B() + 0.5, (double)directionalPosition.E() + 0.75, directionalPosition.A());
            }
            case 3: {
                return Vec3.create((double)directionalPosition.B() + 0.5, (double)directionalPosition.E() + 0.75, directionalPosition.A() + 1);
            }
            case 4: {
                return Vec3.create(directionalPosition.B(), (double)directionalPosition.E() + 0.75, (double)directionalPosition.A() + 0.5);
            }
            case 5: {
                return Vec3.create(directionalPosition.B() + 1, (double)directionalPosition.E() + 0.75, (double)directionalPosition.A() + 0.5);
            }
        }
        return Vec3.create((double)directionalPosition.B() + 0.5, directionalPosition.E() + 1, (double)directionalPosition.A() + 0.5);
    }

    @Override
    public ModDisplayInfo J() {
        if (this.A.J$src$Z$gcqtyf() && this.A.r$src$Z$14eylz9()) {
            return this.A.J();
        }
        return null;
    }

    public int U(EntityPlayerSP entityPlayerSP) {
        int n = 0;
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
            if (!this.u(itemStack)) continue;
            n += itemStack.t();
        }
        return n;
    }

    protected float B(EntityPlayerSP entityPlayerSP, float f) {
        f = CrystalAura.E(f, (float)entityPlayerSP.o(MonsterAttributesBridge.L()), (float)entityPlayerSP.o(MonsterAttributesBridge.m$src$Lgg_vape_wrapper_impl_Holder_$1lgjxui()));
        return f;
    }

    public float E(float f, double d, float f2) {
        float f3 = f;
        double d2 = (1.0 - d) * (double)f2;
        double d3 = ForgeVersion.MC_1_12_2.d() ? 7.0 : 8.0;
        return (float)((d2 * d2 + d2) / 2.0 * d3 * (double)f3 + 1.0);
    }

    public float k(EntityPlayerSP entityPlayerSP, ExplosionType explosionType, Vec3 vec3, World world) {
        double d;
        float f = explosionType.I() * 2.0f;
        double d2 = vec3.getX();
        double d3 = vec3.getY();
        double d4 = vec3.getZ();
        double d5 = entityPlayerSP.z();
        double d6 = entityPlayerSP.N();
        double d7 = entityPlayerSP.h();
        int n = NumericMathUtil.r(d2 - (double)f - 1.0);
        int n2 = NumericMathUtil.r(d2 + (double)f + 1.0);
        int n3 = NumericMathUtil.r(d3 - (double)f - 1.0);
        int n4 = NumericMathUtil.r(d3 + (double)f + 1.0);
        int n5 = NumericMathUtil.r(d4 - (double)f - 1.0);
        int n6 = NumericMathUtil.r(d4 + (double)f + 1.0);
        if (MathUtil.e(d5, (double)n, (double)n2) && MathUtil.e(d6, (double)n3, (double)n4) && MathUtil.e(d7, (double)n5, (double)n6) && (d = ForgeVersion.MC_1_16_5.d() ? Math.sqrt(entityPlayerSP.g(vec3)) / (double)f : entityPlayerSP.i(d2, d3, d4) / (double)f) <= 1.0) {
            float f2 = CrystalAura.O(vec3, entityPlayerSP, world);
            float f3 = this.E(f, d, f2);
            return this.j(entityPlayerSP, f3);
        }
        return -1.0f;
    }

    public CrystalAura() {
        super("CrystalAura", -4263937, Category.g, "Automatically places crystals on obsidian and breaks them for you.");
        this.J = ModeValue.create((Object)this, "Mode", "Auto - Automatically finds targets and places/breaks crystals\nManual - Hold right-click on obsidian with crystal to place and break crystals", (ModeSelection)this.A.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.A.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.S.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx());
        this.P(this.J, new MinecraftVersionConstraint[0]);
        this.t.l(this, 6);
    }

    public static float O(Vec3 vec3, Entity entity, World world) {
        AxisAlignedBB axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        double d = axisAlignedBB.getMinX();
        double d2 = axisAlignedBB.getMinY();
        double d3 = axisAlignedBB.getMinZ();
        double d4 = axisAlignedBB.getMaxX();
        double d5 = axisAlignedBB.getMaxY();
        double d6 = axisAlignedBB.getMaxZ();
        double d7 = 1.0 / ((d4 - d) * 2.0 + 1.0);
        double d8 = 1.0 / ((d5 - d2) * 2.0 + 1.0);
        double d9 = 1.0 / ((d6 - d3) * 2.0 + 1.0);
        double d10 = (1.0 - Math.floor(1.0 / d7) * d7) / 2.0;
        double d11 = (1.0 - Math.floor(1.0 / d9) * d9) / 2.0;
        if (!(d7 < 0.0 || d8 < 0.0 || d9 < 0.0)) {
            int n = 0;
            int n2 = 0;
            for (double d12 = 0.0; d12 <= 1.0; d12 += d7) {
                for (double d13 = 0.0; d13 <= 1.0; d13 += d8) {
                    for (double d14 = 0.0; d14 <= 1.0; d14 += d9) {
                        double d15;
                        double d16;
                        double d17 = NumericMathUtil.S(d12, d, d4);
                        Vec3 vec32 = Vec3.create(d17 + d10, d16 = NumericMathUtil.S(d13, d2, d5), (d15 = NumericMathUtil.S(d14, d3, d6)) + d11);
                        if (world.K(vec32, vec3, false, true, false, entity).getTypeOfHit().equals(RayTraceResult_type.miss())) {
                            ++n;
                        }
                        ++n2;
                    }
                }
            }
            return (float)n / (float)n2;
        }
        return 0.0f;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void onEnable() {
        ClientSettings.g(ActiveModuleStackFrame.class).c(this);
    }

    public static float Y(float f, float f2) {
        float f3 = NumericMathUtil.w(f2, 0.0f, 20.0f);
        return f * (1.0f - f3 / 25.0f);
    }

    @Override
    public void onDisable() {
        ClientSettings.g(ActiveModuleStackFrame.class).w(this);
        this.t.X(this);
    }

    public boolean X(BlockState blockState) {
        if (blockState.isNull()) {
            return false;
        }
        String string = blockState.getBlock().U().toLowerCase();
        return string.contains("obsidian") || string.contains("bedrock");
    }

    public static float w(EntityLivingBase entityLivingBase) {
        int n;
        int n2 = 0;
        int n3 = EnchantmentHelper.a(Enchantments.c(), new EntityLiving(entityLivingBase.getObject()));
        if (n3 > 0) {
            n2 += n3;
        }
        if ((n = EnchantmentHelper.a(Enchantments.N(), new EntityLiving(entityLivingBase.getObject()))) > 0) {
            n2 += 2 * n;
        }
        return n2;
    }

    public boolean p(ItemStack itemStack) {
        if (itemStack.isNull() || !itemStack.getItem().isInstance(MappedClasses.Vw)) {
            return false;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        if (itemMappingEntry != null) {
            return "minecraft:obsidian".equals(itemMappingEntry.M()) || "obsidian".equals(itemMappingEntry.q());
        }
        return itemStack.f().toLowerCase().contains("obsidian");
    }
}

