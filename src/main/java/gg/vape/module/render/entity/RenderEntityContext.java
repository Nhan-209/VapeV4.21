package gg.vape.module.render.entity;

import gg.vape.Vape;
import gg.vape.combat.AttackStrengthTracker;
import gg.vape.config.ClientSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.NameTags;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.MutableColor;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.cache.CachedBoolean;
import gg.vape.utils.cache.CachedFloat;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.ModelPlayer;
import gg.vape.wrapper.impl.PotionEffect;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class RenderEntityContext {
    private final CachedBoolean F;
    @Nullable
    private ModelPlayer B;
    private final CachedBoolean N;
    @Nullable
    private final EntityPlayer A;
    private static int[] e;
    @Nullable
    private String z;
    private final CachedBoolean G;
    private final int s;
    private final CachedFloat O;
    @Nullable
    private MutableColor C;
    private final CachedFloat n;
    @Nullable
    private String R;
    @Nullable
    private String Q;
    private final CachedBoolean o;
    private final CachedBoolean J;
    private List<PotionEffect> k;
    @Nullable
    private ItemStack p;
    private final CachedFloat P;
    private final CachedFloat t;
    private final CachedBoolean L;
    private final CachedBoolean c = new CachedBoolean();
    private EntityLivingBase K;
    @Nullable
    private ItemStack X;
    private EntityPlayerSP r;
    private final CachedBoolean u;
    @Nullable
    private MutableColor v;
    private final CachedFloat j;
    private final CachedBoolean D;

    public String k() {
        if (this.z == null) {
            this.z = this.K.getName();
        }
        return this.z;
    }

    public boolean Y() {
        if (!this.u.r()) {
            this.u.I(this.r.canEntityBeSeen(this.K));
        }
        return (Boolean)this.u.F();
    }

    @Nullable
    public EntityPlayer T() {
        return this.A;
    }

    public boolean R() {
        if (!this.D.r()) {
            if (this.A != null) {
                this.D.I(Vape.INSTANCE.getClientSettings().e(this.r, this.K));
            } else {
                this.D.I(false);
            }
        }
        return (Boolean)this.D.F();
    }

    public float I() {
        if (!this.n.r()) {
            this.n.I(Float.valueOf(this.K.p()));
        }
        return ((Float)this.n.F()).floatValue();
    }

    public boolean g() {
        if (!this.o.r()) {
            this.o.I(this.K.J$src$Z$fdev5g());
        }
        return (Boolean)this.o.F();
    }

    public float U() {
        if (!this.t.r()) {
            this.t.I(Float.valueOf(this.K.Y()));
        }
        return ((Float)this.t.F()).floatValue();
    }

    public static void c(int[] nArray) {
        e = nArray;
    }

    public int U$src$I$1xrslp6() {
        return this.s;
    }

    public boolean o$src$Z$1y639j7() {
        if (!this.G.r()) {
            this.G.I(RotationUtil.k(this.K));
        }
        return (Boolean)this.G.F();
    }

    public void v(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        this.K = entityLivingBase;
        this.r = entityPlayerSP;
        this.c.S();
        this.J.S();
        this.u.S();
        this.o.S();
        this.G.S();
        this.L.S();
        this.D.S();
        this.F.S();
        this.N.S();
        this.O.S();
        this.j.S();
        this.n.S();
        this.P.S();
        this.t.S();
        this.z = null;
        this.R = null;
        this.p = null;
        this.X = null;
        this.Q = null;
        this.C = null;
        this.v = null;
        this.B = null;
        this.k = null;
    }

    public String K() {
        if (this.Q == null && this.A != null) {
            this.Q = Vape.INSTANCE.getModManager().getMod(NameTags.class).Q(this.r, this, this.A);
        }
        return this.Q;
    }

    public static int[] y$src$AI$1d1vtc1() {
        return e;
    }

    @Nullable
    public ItemStack c() {
        if (this.p == null) {
            ItemStack itemStack = this.K.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
            this.p = itemStack.isNotNull() ? itemStack : null;
        }
        return this.p;
    }

    public List<PotionEffect> A(EntityLivingBase entityLivingBase) {
        if (this.k == null) {
            this.k = new ArrayList<PotionEffect>();
            for (Object e : entityLivingBase.B$src$Ljava_util_Collection_$1uxz2f9()) {
                PotionEffect potionEffect = new PotionEffect(e);
                if (potionEffect.k() <= 0) continue;
                this.k.add(potionEffect);
            }
        }
        return this.k;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    public MutableColor E(boolean bl) {
        if (bl) {
            if (this.v == null) {
                this.v = Vape.INSTANCE.getClientSettings().B(this, true, true);
            }
            return this.v;
        }
        if (this.C == null) {
            this.C = Vape.INSTANCE.getClientSettings().y(this, false);
        }
        return this.C;
    }

    public RenderEntityContext(int n, EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        this.J = new CachedBoolean();
        this.u = new CachedBoolean();
        this.o = new CachedBoolean();
        this.G = new CachedBoolean();
        this.L = new CachedBoolean();
        this.D = new CachedBoolean();
        this.F = new CachedBoolean();
        this.N = new CachedBoolean();
        this.O = new CachedFloat();
        this.j = new CachedFloat();
        this.n = new CachedFloat();
        this.P = new CachedFloat();
        this.t = new CachedFloat();
        this.s = n;
        this.K = entityLivingBase;
        this.r = entityPlayerSP;
        boolean bl = entityLivingBase.isInstance(MappedClasses.Yl);
        this.A = bl ? new EntityPlayer(entityLivingBase) : null;
    }

    public boolean K$src$Z$1xmao67() {
        if (!this.F.r()) {
            if (this.A != null) {
                this.F.I(Vape.INSTANCE.getFriendManager().E(this.k()));
            } else {
                this.F.I(false);
            }
        }
        return (Boolean)this.F.F();
    }

    @Nullable
    public ItemStack e$src$Lgg_vape_wrapper_impl_ItemStack_$hhijkm() {
        ItemStack itemStack;
        Item item;
        ItemStack itemStack2;
        if (this.X == null && this.A != null && (itemStack2 = this.c()) != null && itemStack2.isNotNull() && (ItemStackScoreUtil.h(item = this.c().getItem()) || ItemStackScoreUtil.I(item)) && ((itemStack = this.X) == null || ItemStackScoreUtil.I$src$F$dh3k81(itemStack2) > ItemStackScoreUtil.I$src$F$dh3k81(itemStack))) {
            this.X = itemStack2;
        }
        return this.X;
    }

    public float t() {
        if (!this.O.r()) {
            if (this.A != null) {
                this.O.I(Float.valueOf(AttackStrengthTracker.B.S(this.A)));
            } else {
                this.O.I(Float.valueOf(this.K.w$src$F$15l9epb()));
            }
        }
        return ((Float)this.O.F()).floatValue();
    }

    public boolean f() {
        if (!this.N.r()) {
            if (this.A != null) {
                this.N.I(Vape.INSTANCE.getEnemyManager().q(this.k()));
            } else {
                this.N.I(false);
            }
        }
        return (Boolean)this.N.F();
    }

    static {
        if (RenderEntityContext.y$src$AI$1d1vtc1() != null) {
            RenderEntityContext.c(new int[5]);
        }
    }

    public float y() {
        if (!this.j.r()) {
            this.j.I(Float.valueOf(this.K.I$src$F$14vyvep()));
        }
        return ((Float)this.j.F()).floatValue();
    }

    public boolean A() {
        if (!this.L.r()) {
            this.L.I(this.K.P());
        }
        return (Boolean)this.L.F();
    }

    public ModelPlayer z() {
        if (this.B == null && this.A != null) {
            this.B = this.A.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86();
        }
        return this.B;
    }

    public double e() {
        if (!this.P.r()) {
            this.P.I(Float.valueOf(this.r.getDistanceToEntity(this.K)));
        }
        return ((Float)this.P.F()).floatValue();
    }

    public String o() {
        if (this.R == null) {
            this.R = this.K.Q().C();
        }
        return this.R;
    }

    public boolean P() {
        if (!this.c.r()) {
            this.c.I(ClientSettings.B(this.s));
        }
        return (Boolean)this.c.F();
    }

    public boolean D() {
        if (!this.J.r()) {
            this.J.I(Vape.INSTANCE.getClientSettings().J(this.K));
        }
        return (Boolean)this.J.F();
    }
}

