package gg.vape.module.render;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPreTick;
import gg.vape.module.Mod;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.combat.LeftClicker;
import gg.vape.module.combat.SilentAura;
import gg.vape.module.render.Animations;
import gg.vape.module.render.animations.AnimationsMode;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.Minecraft;

public class AnimationsBlockingState
extends AnimationsMode {
    private boolean blocking = false;
    private long releaseTime;
    private final RandomValue chance = RandomValue.G(this, "Chance", "#", "%", 0.0, 70.0, 90.0, 100.0, 1.0, "Chance that a click will blockhit\n(Blocks per second = Your CPS * Chance)");

    public AnimationsBlockingState(Mod mod, String string) {
        super(mod, string);
        this.addValue(this.chance);
    }

    @Override
    public boolean i() {
        if (!((Animations)this.getParent()).a$src$Z$ucwq0q()) {
            return false;
        }
        if (((Animations)this.getParent()).n$src$Z$uk21qf() && !ClientSettings.V()) {
            return false;
        }
        return this.chance.B() >= Math.random() * 100.0;
    }

    public void u(boolean bl) {
        if (this.blocking != bl) {
            this.blocking = bl;
            this.releaseTime = 0L;
            Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362().setPressed(bl);
        }
    }

    @Override
    public String r() {
        return this.chance.c();
    }

    @Override
    public boolean M() {
        return this.blocking;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        boolean bl;
        if (this.isAutoClickerActive()) {
            return;
        }
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        boolean bl2 = eventPreTick.getThePlayer().c$src$I$15a9iwo() > AttackPacketTimingTracker.a.Z() + 1;
        boolean bl3 = bl = this.releaseTime > 0L && System.currentTimeMillis() >= this.releaseTime;
        if (bl2 || bl) {
            this.u(false);
            return;
        }
    }


    @EventHandler
    public void l(EventMouseButton eventMouseButton) {
        if (!eventMouseButton.getButtonState()) {
            return;
        }
        if (this.isAutoClickerActive()) {
            return;
        }
        int n = -100 + eventMouseButton.getButton();
        if (eventMouseButton.getButtonState() && n == Minecraft.gameSettings().F().getKeyCode()) {
            if (!this.i()) {
                return;
            }
            if (!this.blocking && !eventMouseButton.getThePlayer().o$src$Z$1iprrmi()) {
                this.u(true);
                this.releaseTime = System.currentTimeMillis() + 50L;
            }
        }
    }

    private boolean isAutoClickerActive() {
        LeftClicker leftClicker = Vape.INSTANCE.getModManager().getMod(LeftClicker.class);
        if (leftClicker.r$src$Z$14eylz9()) {
            return true;
        }
        SilentAura silentAura = Vape.INSTANCE.getModManager().getMod(SilentAura.class);
        return silentAura.r$src$Z$14eylz9();
    }
}

