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
    private final CachedBoolean friendCache;
    @Nullable
    private ModelPlayer modelPlayer;
    private final CachedBoolean enemyCache;
    @Nullable
    private final EntityPlayer entityPlayer;
    private static int[] sharedColorArray;
    @Nullable
    private String cachedName;
    private final CachedBoolean facingCache;
    private final int settingsId;
    private final CachedFloat attackStrengthCache;
    @Nullable
    private MutableColor fillColor;
    private final CachedFloat healthCache;
    @Nullable
    private String cachedTypeName;
    @Nullable
    private String cachedNameTag;
    private final CachedBoolean onGroundCache;
    private final CachedBoolean visibleCache;
    private List<PotionEffect> potionEffects;
    @Nullable
    private ItemStack heldItem;
    private final CachedFloat distanceCache;
    private final CachedFloat progressCache;
    private final CachedBoolean glowingCache;
    private final CachedBoolean enabledCache = new CachedBoolean();
    private EntityLivingBase entity;
    @Nullable
    private ItemStack bestWeapon;
    private EntityPlayerSP viewer;
    private final CachedBoolean visibilityCache;
    @Nullable
    private MutableColor outlineColor;
    private final CachedFloat maxHealthCache;
    private final CachedBoolean attackableCache;

    public String k() {
        if (this.cachedName == null) {
            this.cachedName = this.entity.getName();
        }
        return this.cachedName;
    }

    public boolean Y() {
        if (!this.visibilityCache.r()) {
            this.visibilityCache.I(this.viewer.canEntityBeSeen(this.entity));
        }
        return (Boolean)this.visibilityCache.F();
    }

    @Nullable
    public EntityPlayer T() {
        return this.entityPlayer;
    }

    public boolean R() {
        if (!this.attackableCache.r()) {
            if (this.entityPlayer != null) {
                this.attackableCache.I(Vape.INSTANCE.getClientSettings().e(this.viewer, this.entity));
            } else {
                this.attackableCache.I(false);
            }
        }
        return (Boolean)this.attackableCache.F();
    }

    public float I() {
        if (!this.healthCache.r()) {
            this.healthCache.I(Float.valueOf(this.entity.p()));
        }
        return ((Float)this.healthCache.F()).floatValue();
    }

    public boolean g() {
        if (!this.onGroundCache.r()) {
            this.onGroundCache.I(this.entity.J$src$Z$fdev5g());
        }
        return (Boolean)this.onGroundCache.F();
    }

    public float U() {
        if (!this.progressCache.r()) {
            this.progressCache.I(Float.valueOf(this.entity.Y()));
        }
        return ((Float)this.progressCache.F()).floatValue();
    }

    public static void c(int[] nArray) {
        sharedColorArray = nArray;
    }

    public int U$src$I$1xrslp6() {
        return this.settingsId;
    }

    public boolean o$src$Z$1y639j7() {
        if (!this.facingCache.r()) {
            this.facingCache.I(RotationUtil.k(this.entity));
        }
        return (Boolean)this.facingCache.F();
    }

    public void v(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        this.entity = entityLivingBase;
        this.viewer = entityPlayerSP;
        this.enabledCache.S();
        this.visibleCache.S();
        this.visibilityCache.S();
        this.onGroundCache.S();
        this.facingCache.S();
        this.glowingCache.S();
        this.attackableCache.S();
        this.friendCache.S();
        this.enemyCache.S();
        this.attackStrengthCache.S();
        this.maxHealthCache.S();
        this.healthCache.S();
        this.distanceCache.S();
        this.progressCache.S();
        this.cachedName = null;
        this.cachedTypeName = null;
        this.heldItem = null;
        this.bestWeapon = null;
        this.cachedNameTag = null;
        this.fillColor = null;
        this.outlineColor = null;
        this.modelPlayer = null;
        this.potionEffects = null;
    }

    public String K() {
        if (this.cachedNameTag == null && this.entityPlayer != null) {
            this.cachedNameTag = Vape.INSTANCE.getModManager().getMod(NameTags.class).Q(this.viewer, this, this.entityPlayer);
        }
        return this.cachedNameTag;
    }

    public static int[] y$src$AI$1d1vtc1() {
        return sharedColorArray;
    }

    @Nullable
    public ItemStack c() {
        if (this.heldItem == null) {
            ItemStack itemStack = this.entity.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
            this.heldItem = itemStack.isNotNull() ? itemStack : null;
        }
        return this.heldItem;
    }

    public List<PotionEffect> A(EntityLivingBase entityLivingBase) {
        if (this.potionEffects == null) {
            this.potionEffects = new ArrayList<PotionEffect>();
            for (Object e : entityLivingBase.B$src$Ljava_util_Collection_$1uxz2f9()) {
                PotionEffect potionEffect = new PotionEffect(e);
                if (potionEffect.k() <= 0) continue;
                this.potionEffects.add(potionEffect);
            }
        }
        return this.potionEffects;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException exception) {
        return exception;
    }

    @Nullable
    public MutableColor E(boolean bl) {
        if (bl) {
            if (this.outlineColor == null) {
                this.outlineColor = Vape.INSTANCE.getClientSettings().B(this, true, true);
            }
            return this.outlineColor;
        }
        if (this.fillColor == null) {
            this.fillColor = Vape.INSTANCE.getClientSettings().y(this, false);
        }
        return this.fillColor;
    }

    public RenderEntityContext(int n, EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        this.visibleCache = new CachedBoolean();
        this.visibilityCache = new CachedBoolean();
        this.onGroundCache = new CachedBoolean();
        this.facingCache = new CachedBoolean();
        this.glowingCache = new CachedBoolean();
        this.attackableCache = new CachedBoolean();
        this.friendCache = new CachedBoolean();
        this.enemyCache = new CachedBoolean();
        this.attackStrengthCache = new CachedFloat();
        this.maxHealthCache = new CachedFloat();
        this.healthCache = new CachedFloat();
        this.distanceCache = new CachedFloat();
        this.progressCache = new CachedFloat();
        this.settingsId = n;
        this.entity = entityLivingBase;
        this.viewer = entityPlayerSP;
        boolean bl = entityLivingBase.isInstance(MappedClasses.Yl);
        this.entityPlayer = bl ? new EntityPlayer(entityLivingBase) : null;
    }

    public boolean K$src$Z$1xmao67() {
        if (!this.friendCache.r()) {
            if (this.entityPlayer != null) {
                this.friendCache.I(Vape.INSTANCE.getFriendManager().E(this.k()));
            } else {
                this.friendCache.I(false);
            }
        }
        return (Boolean)this.friendCache.F();
    }

    @Nullable
    public ItemStack e$src$Lgg_vape_wrapper_impl_ItemStack_$hhijkm() {
        ItemStack itemStack;
        Item item;
        ItemStack itemStack2;
        if (this.bestWeapon == null && this.entityPlayer != null && (itemStack2 = this.c()) != null && itemStack2.isNotNull() && (ItemStackScoreUtil.h(item = this.c().getItem()) || ItemStackScoreUtil.I(item)) && ((itemStack = this.bestWeapon) == null || ItemStackScoreUtil.I$src$F$dh3k81(itemStack2) > ItemStackScoreUtil.I$src$F$dh3k81(itemStack))) {
            this.bestWeapon = itemStack2;
        }
        return this.bestWeapon;
    }

    public float t() {
        if (!this.attackStrengthCache.r()) {
            if (this.entityPlayer != null) {
                this.attackStrengthCache.I(Float.valueOf(AttackStrengthTracker.B.S(this.entityPlayer)));
            } else {
                this.attackStrengthCache.I(Float.valueOf(this.entity.w$src$F$15l9epb()));
            }
        }
        return ((Float)this.attackStrengthCache.F()).floatValue();
    }

    public boolean f() {
        if (!this.enemyCache.r()) {
            if (this.entityPlayer != null) {
                this.enemyCache.I(Vape.INSTANCE.getEnemyManager().q(this.k()));
            } else {
                this.enemyCache.I(false);
            }
        }
        return (Boolean)this.enemyCache.F();
    }

    static {
        if (RenderEntityContext.y$src$AI$1d1vtc1() != null) {
            RenderEntityContext.c(new int[5]);
        }
    }

    public float y() {
        if (!this.maxHealthCache.r()) {
            this.maxHealthCache.I(Float.valueOf(this.entity.I$src$F$14vyvep()));
        }
        return ((Float)this.maxHealthCache.F()).floatValue();
    }

    public boolean A() {
        if (!this.glowingCache.r()) {
            this.glowingCache.I(this.entity.P());
        }
        return (Boolean)this.glowingCache.F();
    }

    public ModelPlayer z() {
        if (this.modelPlayer == null && this.entityPlayer != null) {
            this.modelPlayer = this.entityPlayer.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86();
        }
        return this.modelPlayer;
    }

    public double e() {
        if (!this.distanceCache.r()) {
            this.distanceCache.I(Float.valueOf(this.viewer.getDistanceToEntity(this.entity)));
        }
        return ((Float)this.distanceCache.F()).floatValue();
    }

    public String o() {
        if (this.cachedTypeName == null) {
            this.cachedTypeName = this.entity.Q().C();
        }
        return this.cachedTypeName;
    }

    public boolean P() {
        if (!this.enabledCache.r()) {
            this.enabledCache.I(ClientSettings.B(this.settingsId));
        }
        return (Boolean)this.enabledCache.F();
    }

    public boolean D() {
        if (!this.visibleCache.r()) {
            this.visibleCache.I(Vape.INSTANCE.getClientSettings().J(this.entity));
        }
        return (Boolean)this.visibleCache.F();
    }
}
