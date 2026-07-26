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
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.Minecraft;

public class AnimationsBlockingState
extends AnimationsMode {
    private boolean s = false;
    private long I;
    private final RandomValue o = RandomValue.G(this, "Chance", "#", "%", 0.0, 70.0, 90.0, 100.0, 1.0, "Chance that a click will blockhit\n(Blocks per second = Your CPS * Chance)");

    public AnimationsBlockingState(Mod mod, String string) {
        super(mod, string);
        this.addValue(this.o);
    }

    @Override
    public boolean i() {
        if (!((Animations)this.getParent()).a$src$Z$ucwq0q()) {
            return false;
        }
        if (((Animations)this.getParent()).n$src$Z$uk21qf() && !ClientSettings.V()) {
            return false;
        }
        return this.o.B() >= Math.random() * 100.0;
    }

    public void u(boolean bl) {
        if (this.s != bl) {
            this.s = bl;
            this.I = 0L;
            Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362().setPressed(bl);
        }
    }

    @Override
    public String r() {
        return this.o.c();
    }

    @Override
    public boolean M() {
        return this.s;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        boolean bl;
        if (this.c()) {
            return;
        }
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        boolean bl2 = eventPreTick.getThePlayer().c$src$I$15a9iwo() > AttackPacketTimingTracker.a.Z() + 1;
        boolean bl3 = bl = this.I > 0L && System.currentTimeMillis() >= this.I;
        if (bl2 || bl) {
            this.u(false);
            return;
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void l(EventMouseButton eventMouseButton) {
        if (!eventMouseButton.getButtonState()) {
            return;
        }
        if (this.c()) {
            return;
        }
        int n = -100 + eventMouseButton.getButton();
        if (eventMouseButton.getButtonState() && n == Minecraft.gameSettings().F().getKeyCode()) {
            if (!this.i()) {
                return;
            }
            if (!this.s && !eventMouseButton.getThePlayer().o$src$Z$1iprrmi()) {
                this.u(true);
                this.I = System.currentTimeMillis() + 50L;
            }
        }
    }

    private boolean c() {
        LeftClicker leftClicker = Vape.INSTANCE.getModManager().getMod(LeftClicker.class);
        if (leftClicker.r$src$Z$14eylz9()) {
            return true;
        }
        SilentAura silentAura = Vape.INSTANCE.getModManager().getMod(SilentAura.class);
        return silentAura.r$src$Z$14eylz9();
    }
}

