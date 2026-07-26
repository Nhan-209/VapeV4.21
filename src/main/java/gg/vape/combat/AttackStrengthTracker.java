package gg.vape.combat;

import gg.vape.Vape;
import gg.vape.combat.TrackedPlayerAttackState;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventEntityJoinWorld;
import gg.vape.event.impl.EventLivingUpdate;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPotion;
import gg.vape.wrapper.impl.EnumCreatureAttribute;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemAttributeModifiers;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttackStrengthTracker
implements EventListener {
    private HashMap<Integer, TrackedPlayerAttackState> w = new HashMap();
    private Object i;
    public static AttackStrengthTracker B = new AttackStrengthTracker();
    private static int[] e;
    private List<EntityPotion> I = new ArrayList<EntityPotion>();

    private static float e(ItemStack itemStack, EntityPlayer entityPlayer, boolean bl, boolean bl2) {
        ItemAttributeModifiers itemAttributeModifiers;
        float f = 1.0f;
        if (itemStack.isNotNull() && (itemAttributeModifiers = itemStack.o()).i() > 0) {
            int n = ForgeVersion.MC_1_12_2.L() ? 1 : 0;
            AttributeModifier attributeModifier = new AttributeModifier(itemAttributeModifiers.f().toArray()[n]);
            f += (float)attributeModifier.getAmount();
        }
        float f2 = 0.0f;
        f2 = EnchantmentHelper.C(itemStack, EnumCreatureAttribute.R());
        if (f > 0.0f || f2 > 0.0f) {
            if (bl2 && f > 0.0f) {
                f *= 1.5f;
            }
            float f3 = AttackStrengthTracker.y(entityPlayer, bl, f += f2);
            return f3;
        }
        return 0.0f;
    }

    @EventHandler
    public void onUpdate(EventLivingUpdate eventLivingUpdate) {
        TrackedPlayerAttackState trackedPlayerAttackState;
        if (eventLivingUpdate.getEntity().isInstance(MappedClasses.Yl) && Minecraft.thePlayer().getDistanceToEntity(eventLivingUpdate.getEntity()) < 6.0f && (trackedPlayerAttackState = (TrackedPlayerAttackState)this.w.getOrDefault(eventLivingUpdate.getEntity().S(), null)) != null) {
            TrackedPlayerAttackState.W(trackedPlayerAttackState, true);
        }
    }

    @EventHandler
    public void L(EventEntityJoinWorld eventEntityJoinWorld) {
        if (eventEntityJoinWorld.getEntity().isInstance(MappedClasses.Yl)) {
            TrackedPlayerAttackState trackedPlayerAttackState = this.w.getOrDefault(eventEntityJoinWorld.getEntity().S(), new TrackedPlayerAttackState(new EntityPlayer(eventEntityJoinWorld.getEntity())));
            if (trackedPlayerAttackState != null) {
                TrackedPlayerAttackState.n(trackedPlayerAttackState, new EntityPlayer(eventEntityJoinWorld.getEntity().getObject()));
                if (TrackedPlayerAttackState.H(trackedPlayerAttackState).hasTimeElapsed(10000L)) {
                    TrackedPlayerAttackState.j(trackedPlayerAttackState, 20.0f);
                    TrackedPlayerAttackState.h(trackedPlayerAttackState, 20);
                    TrackedPlayerAttackState.R(trackedPlayerAttackState, 0);
                    TrackedPlayerAttackState.T(trackedPlayerAttackState, 0.0f);
                    TrackedPlayerAttackState.C(trackedPlayerAttackState, 5.0f);
                    TrackedPlayerAttackState.V(trackedPlayerAttackState, null);
                    TrackedPlayerAttackState.D(trackedPlayerAttackState, false);
                    TrackedPlayerAttackState.H(trackedPlayerAttackState).reset();
                }
            }
            return;
        }
        if (!eventEntityJoinWorld.getEntity().isInstance(MappedClasses.Zf)) {
            return;
        }
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        EntityPotion entityPotion = new EntityPotion(eventEntityJoinWorld.getEntity());
        if (entityPotion.getPotion().isNull()) {
            return;
        }
        boolean bl = ItemStackScoreUtil.i(entityPotion.getPotion());
        if (!bl) {
            return;
        }
        this.I.add(new EntityPotion(eventEntityJoinWorld.getEntity().getObject()));
    }

    @EventHandler
    public void J(EventPreAttack eventPreAttack) {
        TrackedPlayerAttackState trackedPlayerAttackState;
        if (eventPreAttack.getTarget().isInstance(MappedClasses.Yl) && (trackedPlayerAttackState = (TrackedPlayerAttackState)this.w.getOrDefault(eventPreAttack.getTarget().S(), null)) != null) {
            TrackedPlayerAttackState.m(trackedPlayerAttackState, new EntityPlayer(eventPreAttack.getTarget().getObject()).o$src$Z$1iprrmi());
            TrackedPlayerAttackState.L(trackedPlayerAttackState, 0);
            TrackedPlayerAttackState.D(trackedPlayerAttackState, true);
            TrackedPlayerAttackState.V(trackedPlayerAttackState, Minecraft.thePlayer().B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt());
        }
    }

    static float r(EntityPlayer entityPlayer, boolean bl, float f) {
        return AttackStrengthTracker.y(entityPlayer, bl, f);
    }

    static {
        AttackStrengthTracker.k(new int[3]);
    }

    private static double V(Entity entity, double d, double d2, double d3) {
        double d4 = entity.z() - d;
        double d5 = entity.N() - d2;
        double d6 = entity.h() - d3;
        return d4 * d4 + d5 * d5 + d6 * d6;
    }

    private static float m(int n, double d) {
        return (int)(d * (double)(4 << n) + 0.5);
    }

    public boolean g() {
        return Vape.INSTANCE.getClientSettings().B.L();
    }

    public boolean v() {
        return Vape.INSTANCE.getClientSettings().J.L();
    }

    private static float y(EntityPlayer entityPlayer, ItemStack itemStack, double d, double d2, double d3, boolean bl) {
        ItemSplashPotion itemSplashPotion;
        double d4 = AttackStrengthTracker.V(entityPlayer, d, d2, d3);
        int n = 0;
        if (itemStack.getItem().isInstance(MappedClasses.Di) && (itemSplashPotion = new ItemSplashPotion(itemStack.getItem().getObject())).getRawPotionEffects(itemStack) != null && ItemSplashPotion.isSplashPotion(itemStack)) {
            for (PotionEffect potionEffect : itemSplashPotion.getPotionEffects(itemStack)) {
                if (potionEffect.C() != 6) continue;
                n = potionEffect.L();
            }
        }
        if (d4 < 16.0) {
            double d5 = 1.0 - Math.sqrt(d4) / 4.0;
            if (bl) {
                d5 = 1.0;
            }
            return AttackStrengthTracker.m(n, d5);
        }
        return 0.0f;
    }

    static float j(ItemStack itemStack, EntityPlayer entityPlayer, boolean bl, boolean bl2) {
        return AttackStrengthTracker.e(itemStack, entityPlayer, bl, bl2);
    }

    @EventHandler
    public void f(EventPostTick eventPostTick) {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        if (this.i == null) {
            this.i = worldClient.getObject();
        }
        if (!worldClient.getObject().equals(this.i)) {
            this.I.clear();
            this.w.clear();
            this.i = worldClient.getObject();
        }
        for (Object object : worldClient.z()) {
            if (!MappedClasses.Yl.isAssignableFrom(object.getClass()) || MappedClasses.z5.isAssignableFrom(object.getClass())) continue;
            EntityPlayer entity = new EntityPlayer(object);
            if (this.w.containsKey(entity.S())) {
                TrackedPlayerAttackState trackedPlayerAttackState = this.w.get(entity.S());
                trackedPlayerAttackState.u();
                continue;
            }
            this.w.put(entity.S(), new TrackedPlayerAttackState(entity));
        }
        ArrayList arrayList = new ArrayList();
        for (EntityPotion entityPotion : this.I) {
            if (worldClient.z().contains(entityPotion.getObject())) continue;
            double d = entityPotion.z();
            double d2 = entityPotion.N();
            double d3 = entityPotion.h();
            for (Map.Entry<Integer, TrackedPlayerAttackState> entry : this.w.entrySet()) {
                Entity entity = ((World)worldClient).V(entry.getKey());
                if (!entity.isNotNull() || !entity.isInstance(MappedClasses.Yl)) continue;
                boolean bl = false;
                if (entityPotion.N() > entity.N() + 0.5 && (double)entity.getDistanceToEntity(entityPotion) < 2.2 && entityPotion.l() >= 5) {
                    bl = true;
                }
                float f = AttackStrengthTracker.y(new EntityPlayer(entity.getObject()), entityPotion.getPotion(), d, d2, d3, bl);
                TrackedPlayerAttackState trackedPlayerAttackState = entry.getValue();
                TrackedPlayerAttackState.j(trackedPlayerAttackState, TrackedPlayerAttackState.J(trackedPlayerAttackState) + f);
            }
            arrayList.add(entityPotion);
        }
        if (arrayList.size() > 0) {
            this.I.removeAll(arrayList);
        }
    }

    public float S(EntityPlayer entityPlayer) {
        if (!this.g()) {
            return entityPlayer.w$src$F$15l9epb();
        }
        TrackedPlayerAttackState trackedPlayerAttackState = this.w.get(entityPlayer.S());
        return trackedPlayerAttackState != null ? TrackedPlayerAttackState.J(trackedPlayerAttackState) : entityPlayer.w$src$F$15l9epb();
    }

    private static float y(EntityPlayer entityPlayer, boolean bl, float f) {
        if (bl && f > 0.0f) {
            f = (1.0f + f) * 0.5f;
        }
        f = RotationUtil.m(entityPlayer, f);
        f = RotationUtil.j((EntityLivingBase)entityPlayer, f);
        return f;
    }

    public static void k(int[] nArray) {
        e = nArray;
    }

    public static int[] Y() {
        return e;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean s() {
        return Vape.INSTANCE.getClientSettings().I.L();
    }
}

