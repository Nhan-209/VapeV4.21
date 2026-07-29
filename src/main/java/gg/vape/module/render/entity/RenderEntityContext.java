package gg.vape.module.render.entity;

import gg.vape.Vape;
import gg.vape.combat.AttackStrengthTracker;
import gg.vape.config.ClientSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.NameTags;
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
    @Nullable
    private String cachedName;
    private final CachedBoolean invisibleWithoutEquipmentCache;
    private final int entityId;
    private final CachedFloat effectiveHealthCache;
    @Nullable
    private MutableColor fillColor;
    private final CachedFloat healthCache;
    @Nullable
    private String cachedTypeName;
    @Nullable
    private String cachedNameTag;
    private final CachedBoolean invisibleCache;
    private final CachedBoolean botCache;
    private List<PotionEffect> potionEffects;
    @Nullable
    private ItemStack heldItem;
    private final CachedFloat distanceCache;
    private final CachedFloat heightCache;
    private final CachedBoolean sneakingCache;
    private final CachedBoolean syntheticEntityCache = new CachedBoolean();
    private EntityLivingBase entity;
    @Nullable
    private ItemStack bestWeapon;
    private EntityPlayerSP viewer;
    private final CachedBoolean visibilityCache;
    @Nullable
    private MutableColor outlineColor;
    private final CachedFloat maxHealthCache;
    private final CachedBoolean attackableCache;

    public String getName() {
        if (this.cachedName == null) {
            this.cachedName = this.entity.getName();
        }
        return this.cachedName;
    }

    public boolean canViewerSee() {
        if (!this.visibilityCache.r()) {
            this.visibilityCache.I(this.viewer.canEntityBeSeen(this.entity));
        }
        return (Boolean)this.visibilityCache.F();
    }

    @Nullable
    public EntityPlayer getEntityPlayer() {
        return this.entityPlayer;
    }

    public boolean isAttackable() {
        if (!this.attackableCache.r()) {
            if (this.entityPlayer != null) {
                this.attackableCache.I(Vape.INSTANCE.getClientSettings().e(this.viewer, this.entity));
            } else {
                this.attackableCache.I(false);
            }
        }
        return (Boolean)this.attackableCache.F();
    }

    public float getHealth() {
        if (!this.healthCache.r()) {
            this.healthCache.I(Float.valueOf(this.entity.p()));
        }
        return ((Float)this.healthCache.F()).floatValue();
    }

    public boolean isInvisible() {
        if (!this.invisibleCache.r()) {
            this.invisibleCache.I(this.entity.J$src$Z$fdev5g());
        }
        return (Boolean)this.invisibleCache.F();
    }

    public float getHeight() {
        if (!this.heightCache.r()) {
            this.heightCache.I(Float.valueOf(this.entity.Y()));
        }
        return ((Float)this.heightCache.F()).floatValue();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public boolean isInvisibleWithoutEquipment() {
        if (!this.invisibleWithoutEquipmentCache.r()) {
            this.invisibleWithoutEquipmentCache.I(RotationUtil.k(this.entity));
        }
        return (Boolean)this.invisibleWithoutEquipmentCache.F();
    }

    public void update(EntityLivingBase entity, EntityPlayerSP viewer) {
        this.entity = entity;
        this.viewer = viewer;
        this.syntheticEntityCache.S();
        this.botCache.S();
        this.visibilityCache.S();
        this.invisibleCache.S();
        this.invisibleWithoutEquipmentCache.S();
        this.sneakingCache.S();
        this.attackableCache.S();
        this.friendCache.S();
        this.enemyCache.S();
        this.effectiveHealthCache.S();
        this.maxHealthCache.S();
        this.healthCache.S();
        this.distanceCache.S();
        this.heightCache.S();
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

    public String getNameTag() {
        if (this.cachedNameTag == null && this.entityPlayer != null) {
            this.cachedNameTag = Vape.INSTANCE.getModManager().getMod(NameTags.class).Q(this.viewer, this, this.entityPlayer);
        }
        return this.cachedNameTag;
    }

    @Nullable
    public ItemStack getHeldItem() {
        if (this.heldItem == null) {
            ItemStack itemStack = this.entity.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
            this.heldItem = itemStack.isNotNull() ? itemStack : null;
        }
        return this.heldItem;
    }

    public List<PotionEffect> getPotionEffects() {
        if (this.potionEffects == null) {
            this.potionEffects = new ArrayList<>();
            for (Object e : this.entity.B$src$Ljava_util_Collection_$1uxz2f9()) {
                PotionEffect potionEffect = new PotionEffect(e);
                if (potionEffect.k() <= 0) continue;
                this.potionEffects.add(potionEffect);
            }
        }
        return this.potionEffects;
    }


    @Nullable
    public MutableColor getRenderColor(boolean outline) {
        if (outline) {
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

    public RenderEntityContext(int entityId, EntityLivingBase entity, EntityPlayerSP viewer) {
        this.botCache = new CachedBoolean();
        this.visibilityCache = new CachedBoolean();
        this.invisibleCache = new CachedBoolean();
        this.invisibleWithoutEquipmentCache = new CachedBoolean();
        this.sneakingCache = new CachedBoolean();
        this.attackableCache = new CachedBoolean();
        this.friendCache = new CachedBoolean();
        this.enemyCache = new CachedBoolean();
        this.effectiveHealthCache = new CachedFloat();
        this.maxHealthCache = new CachedFloat();
        this.healthCache = new CachedFloat();
        this.distanceCache = new CachedFloat();
        this.heightCache = new CachedFloat();
        this.entityId = entityId;
        this.entity = entity;
        this.viewer = viewer;
        boolean isPlayer = entity.isInstance(MappedClasses.Yl);
        this.entityPlayer = isPlayer ? new EntityPlayer(entity) : null;
    }

    public boolean isFriend() {
        if (!this.friendCache.r()) {
            if (this.entityPlayer != null) {
                this.friendCache.I(Vape.INSTANCE.getFriendManager().E(this.getName()));
            } else {
                this.friendCache.I(false);
            }
        }
        return (Boolean)this.friendCache.F();
    }

    @Nullable
    public ItemStack getBestWeapon() {
        if (this.bestWeapon == null && this.entityPlayer != null) {
            ItemStack heldItem = this.getHeldItem();
            if (heldItem != null && heldItem.isNotNull()) {
                Item item = heldItem.getItem();
                if (ItemStackScoreUtil.h(item) || ItemStackScoreUtil.I(item)) {
                    this.bestWeapon = heldItem;
                }
            }
        }
        return this.bestWeapon;
    }

    public float getEffectiveHealth() {
        if (!this.effectiveHealthCache.r()) {
            if (this.entityPlayer != null) {
                this.effectiveHealthCache.I(Float.valueOf(AttackStrengthTracker.INSTANCE.getEstimatedHealth(this.entityPlayer)));
            } else {
                this.effectiveHealthCache.I(Float.valueOf(this.entity.w$src$F$15l9epb()));
            }
        }
        return ((Float)this.effectiveHealthCache.F()).floatValue();
    }

    public boolean isEnemy() {
        if (!this.enemyCache.r()) {
            if (this.entityPlayer != null) {
                this.enemyCache.I(Vape.INSTANCE.getEnemyManager().q(this.getName()));
            } else {
                this.enemyCache.I(false);
            }
        }
        return (Boolean)this.enemyCache.F();
    }

    public float getMaxHealth() {
        if (!this.maxHealthCache.r()) {
            this.maxHealthCache.I(Float.valueOf(this.entity.I$src$F$14vyvep()));
        }
        return ((Float)this.maxHealthCache.F()).floatValue();
    }

    public boolean isSneaking() {
        if (!this.sneakingCache.r()) {
            this.sneakingCache.I(this.entity.P());
        }
        return (Boolean)this.sneakingCache.F();
    }

    public ModelPlayer getModelPlayer() {
        if (this.modelPlayer == null && this.entityPlayer != null) {
            this.modelPlayer = this.entityPlayer.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86();
        }
        return this.modelPlayer;
    }

    public double getDistance() {
        if (!this.distanceCache.r()) {
            this.distanceCache.I(Float.valueOf(this.viewer.getDistanceToEntity(this.entity)));
        }
        return ((Float)this.distanceCache.F()).floatValue();
    }

    public String getTypeName() {
        if (this.cachedTypeName == null) {
            this.cachedTypeName = this.entity.Q().getFormattedText();
        }
        return this.cachedTypeName;
    }

    public boolean isSyntheticEntity() {
        if (!this.syntheticEntityCache.r()) {
            this.syntheticEntityCache.I(ClientSettings.B(this.entityId));
        }
        return (Boolean)this.syntheticEntityCache.F();
    }

    public boolean isBot() {
        if (!this.botCache.r()) {
            this.botCache.I(Vape.INSTANCE.getClientSettings().J(this.entity));
        }
        return (Boolean)this.botCache.F();
    }
}
