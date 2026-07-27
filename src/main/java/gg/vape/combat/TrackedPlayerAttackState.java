package gg.vape.combat;

import gg.vape.combat.AttackStrengthTracker;
import gg.vape.mapping.MappedClasses;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EffectRenderer;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.SoundAwareEntityFX;
import java.util.List;

public class TrackedPlayerAttackState {
    private int b = (int)d;
    private int g;
    private float X = 20.0f;
    private boolean l;
    private EntityPlayer j;
    private static final String c = "crit";
    private final TimerUtil z = new TimerUtil();
    private float q = 5.0f;
    private static final long d = -3278579833910591468L;
    private boolean w;
    private int v;
    private float H;
    private boolean C;
    private ItemStack f;

    static ItemStack V(TrackedPlayerAttackState trackedPlayerAttackState, ItemStack itemStack) {
        trackedPlayerAttackState.f = itemStack;
        return trackedPlayerAttackState.f;
    }

    static float C(TrackedPlayerAttackState trackedPlayerAttackState, float f) {
        trackedPlayerAttackState.q = f;
        return trackedPlayerAttackState.q;
    }

    static int L(TrackedPlayerAttackState trackedPlayerAttackState, int n) {
        trackedPlayerAttackState.v = n;
        return trackedPlayerAttackState.v;
    }

    static float j(TrackedPlayerAttackState trackedPlayerAttackState, float f) {
        trackedPlayerAttackState.X = f;
        return trackedPlayerAttackState.X;
    }

    public void u() {
        boolean bl;
        ++this.v;
        this.z.reset();
        if (this.X > 20.0f) {
            this.X = 20.0f;
        }
        boolean bl2 = AttackStrengthTracker.B.s() && this.Q(this.j.N() - this.j.W(), this.j.b$src$Z$fqlxe4());
        boolean bl3 = bl = AttackStrengthTracker.B.v() && this.T();
        if (this.w) {
            this.H += 0.3f;
            if (this.l && this.v <= 11) {
                List<SoundAwareEntityFX> list;
                boolean bl4 = false;
                EffectRenderer effectRenderer = Minecraft.z();
                if (effectRenderer.isNotNull() && (list = effectRenderer.getParticleEmitters()).size() > 0) {
                    for (SoundAwareEntityFX soundAwareEntityFX : list) {
                        if (!soundAwareEntityFX.Z$src$Lgg_vape_wrapper_impl_EnumParticleTypes_$1aa3947().K().equalsIgnoreCase(c) || this.j.isNull() || !((double)this.j.getDistanceToEntity(soundAwareEntityFX) < 1.1) || !(soundAwareEntityFX.N() < this.j.N() + 2.5) || RotationUtil.S(this.j, soundAwareEntityFX)) continue;
                        bl4 = true;
                        break;
                    }
                }
                float f = AttackStrengthTracker.j(this.f, this.j, this.C, bl4);
                this.X -= f;
                this.l = false;
            } else if (!bl2 && !bl && this.j.U$src$Z$fjglof()) {
                this.X -= AttackStrengthTracker.r(this.j, false, 1.0f);
            }
            this.w = false;
            this.C = false;
        }
    }

    static int R(TrackedPlayerAttackState trackedPlayerAttackState, int n) {
        trackedPlayerAttackState.g = n;
        return trackedPlayerAttackState.g;
    }

    static int h(TrackedPlayerAttackState trackedPlayerAttackState, int n) {
        trackedPlayerAttackState.b = n;
        return trackedPlayerAttackState.b;
    }

    static float T(TrackedPlayerAttackState trackedPlayerAttackState, float f) {
        trackedPlayerAttackState.H = f;
        return trackedPlayerAttackState.H;
    }


    public TrackedPlayerAttackState(EntityPlayer entityPlayer) {
        this.j = entityPlayer;
    }

    static boolean W(TrackedPlayerAttackState trackedPlayerAttackState, boolean bl) {
        trackedPlayerAttackState.w = bl;
        return trackedPlayerAttackState.w;
    }

    static float J(TrackedPlayerAttackState trackedPlayerAttackState) {
        return trackedPlayerAttackState.X;
    }

    static TimerUtil H(TrackedPlayerAttackState trackedPlayerAttackState) {
        return trackedPlayerAttackState.z;
    }

    private boolean T() {
        boolean bl = false;
        if (this.X >= 20.0f) {
            this.g = 0;
        }
        if (this.X > 0.0f && this.X < 20.0f) {
            ++this.g;
            if (this.g >= 80 && (!this.j.U$src$Z$fjglof() || Minecraft.thePlayer().i(PotionRegistry.W))) {
                this.X += 1.0f;
                this.H += 3.0f;
                this.g = 0;
            }
        } else if (this.b <= 0) {
            ++this.g;
            if (this.g >= 80) {
                if (this.X > 1.0f) {
                    this.X -= 1.0f;
                    bl = true;
                }
                this.g = 0;
            }
        } else {
            this.g = 0;
        }
        return bl;
    }

    public boolean Q(double d, boolean bl) {
        PotionEffect potionEffect;
        float f;
        int n;
        float f2 = (float)(this.j.N() - this.j.W());
        if (ForgeVersion.MC_1_7_10.Y()) {
            Block block;
            int n2 = MathUtil.floor(this.j.z());
            int n3 = MathUtil.floor(this.j.N() - (double)0.2f);
            n = MathUtil.floor(this.j.h());
            BlockPos blockPos = BlockPos.create(n2, n3, n);
            Block block2 = Minecraft.theWorld().getBlockState(blockPos).getBlock();
            if (BlockUtil.p(block2) && ((block = Minecraft.theWorld().getBlockState(blockPos.d$src$Lgg_vape_wrapper_impl_BlockPos_$6vry9r()).getBlock()).isInstance(MappedClasses.V7) || block.isInstance(MappedClasses.lx) || block.isInstance(MappedClasses.YY))) {
                blockPos = blockPos.d$src$Lgg_vape_wrapper_impl_BlockPos_$6vry9r();
                block2 = Minecraft.theWorld().getBlockState(blockPos).getBlock();
            }
            f2 += this.j.M$src$F$ff28gb();
            this.j.L(d, bl, block2, blockPos);
        } else {
            this.j.q(d, bl);
        }
        if (this.j.b$src$Z$fqlxe4() && f2 > 0.0f && (n = MathUtil.ceil(f2 - 3.0f - (f = (potionEffect = this.j.b(PotionRegistry.Z)).isNotNull() ? (float)(potionEffect.L() + 1) : 0.0f))) > 0) {
            this.X -= (float)n;
            return true;
        }
        return false;
    }

    static boolean m(TrackedPlayerAttackState trackedPlayerAttackState, boolean bl) {
        trackedPlayerAttackState.C = bl;
        return trackedPlayerAttackState.C;
    }

    static boolean D(TrackedPlayerAttackState trackedPlayerAttackState, boolean bl) {
        trackedPlayerAttackState.l = bl;
        return trackedPlayerAttackState.l;
    }

    static EntityPlayer n(TrackedPlayerAttackState trackedPlayerAttackState, EntityPlayer entityPlayer) {
        trackedPlayerAttackState.j = entityPlayer;
        return trackedPlayerAttackState.j;
    }
}

