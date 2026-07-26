package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPotionEffectCheck;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Potion;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionEntry;
import gg.vape.wrapper.impl.PotionRegistry;

public class AntiDebuff
extends Mod {
    private final BooleanValue D;
    private boolean H;
    private final BooleanValue U = BooleanValue.create(this, "Remove Nausea", true);
    private final BooleanValue F = BooleanValue.create(this, "Remove Blindness", true);
    private final BooleanValue t = BooleanValue.create(this, "Remove Slowness", true);

    @EventHandler
    public void w(EventPostRenderTick eventPostRenderTick) {
        this.H = false;
    }

    @EventHandler
    public void F(EventPreRenderTick eventPreRenderTick) {
        this.H = true;
    }

    public AntiDebuff() {
        super("AntiDebuff", -256, Category.k, "Removes negative visual potion effects");
        this.D = BooleanValue.create(this, "Remove Effects", false, "Removes non-visual effects\nCan be detected by anti-cheat");
        this.R(false);
        this.addValue(this.U, this.F, this.t, this.D);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        EntityPlayerSP entityPlayerSP = eventPrePlayerTick.getThePlayer();
        if (this.U.L().booleanValue()) {
            entityPlayerSP.q(PotionRegistry.X.D());
        }
        if (this.F.L().booleanValue() && this.D.L().booleanValue()) {
            entityPlayerSP.q(PotionRegistry.K.D());
        }
        if (this.t.L().booleanValue() && this.D.L().booleanValue()) {
            PotionEntry potionEntry = PotionRegistry.o;
            if (ForgeVersion.MC_1_16_5.v()) {
                PotionEffect potionEffect = entityPlayerSP.b(potionEntry);
                if (potionEffect.isNotNull()) {
                    potionEntry.t(entityPlayerSP, entityPlayerSP.z$src$Ljava_lang_Object_$1k68ls2(), potionEffect.L());
                }
            } else {
                potionEntry.t(entityPlayerSP, entityPlayerSP.z$src$Ljava_lang_Object_$1k68ls2(), 0);
            }
            entityPlayerSP.q(potionEntry.D());
        }
    }

    @EventHandler
    public void i(EventPotionEffectCheck eventPotionEffectCheck) {
        if (!this.H) {
            return;
        }
        if (!eventPotionEffectCheck.getEntity().equals(eventPotionEffectCheck.getThePlayer())) {
            return;
        }
        Potion potion = eventPotionEffectCheck.getPotion();
        boolean bl = false;
        if (this.F.L().booleanValue() && PotionRegistry.K.q(potion)) {
            bl = true;
        }
        if (bl) {
            eventPotionEffectCheck.setActive(false);
            eventPotionEffectCheck.setCancelled(true);
        }
    }
}

