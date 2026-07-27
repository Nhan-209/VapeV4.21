package gg.vape.module.combat;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;

public class SprintReset
extends Mod {
    private final NumberValue chance = NumberValue.E(this, "Chance", "#", "%", 0.0, 90.0, 100.0, "Chance of WTapping when hitting a target");
    private final TimerUtil rePressTimer;
    private final NumberValue releaseDelay = NumberValue.create(this, "Release delay", "#", "", 0.0, 0.0, 500.0, 50.0, "Delay before releasing W key after hitting a target");
    private boolean releasePending;
    private final TimerUtil releaseTimer;
    private final NumberValue rePressDelay = NumberValue.create(this, "Re-press delay", "#", "", 0.0, 0.0, 500.0, 50.0, "Delay before re-pressing W key after releasing it");
    private final BooleanValue selectHits = BooleanValue.create(this, "Select hits", true, "Only WTap when the target is vulnerable");
    private boolean rePressPending;
    private static final long MODULE_ID = -5147998889622254014L;

    private void handleRePress() {
        if (this.rePressTimer.hasTimeElapsed(((Double)this.rePressDelay.K()).longValue())) {
            KeyBinding keyBinding = Minecraft.gameSettings().Y();
            boolean bl = ClientSettings.B(keyBinding);
            if (bl) {
                keyBinding.setPressed(true);
            }
            this.rePressPending = false;
        }
    }


    @EventHandler
    public void onPreAttack(EventPreAttack eventPreAttack) {
        boolean bl = Packet.h();
        if (bl) {
            int n;
            boolean bl2;
            boolean bl3 = eventPreAttack.getTarget().isInstance(MappedClasses.lG);
            if (!bl3) {
                return;
            }
            boolean bl4 = this.releasePending;
            if (bl4 || (bl2 = this.rePressPending)) {
                return;
            }
            boolean bl5 = this.selectHits.L();
            if (bl5 && (n = eventPreAttack.getTarget().V$src$I$fk0dv5()) > 14) {
                return;
            }
            if (this.F$src$Z$oodzg7()) {
                this.releasePending = true;
                this.releaseTimer.reset();
                this.handleRelease();
            }
            return;
        }
        boolean bl6 = eventPreAttack.getTarget().isInstance(MappedClasses.lG);
        boolean bl7 = bl6;
        boolean bl8 = bl7;
        boolean bl9 = bl8;
        if (bl9) {
            this.releasePending = true;
            this.releaseTimer.reset();
            this.handleRelease();
        }
    }

    public SprintReset() {
        super("WTap", (int)MODULE_ID, Category.g);
        this.releaseTimer = new TimerUtil();
        this.rePressTimer = new TimerUtil();
        this.addValue(this.chance, this.releaseDelay, this.rePressDelay, this.selectHits);
        this.releaseDelay.C(0);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        boolean bl = Packet.A();
        if (bl) {
            boolean bl2 = Minecraft.currentScreen().isNull();
            boolean bl3 = bl2;
            if (bl3) {
                this.handleRePress();
                return;
            }
            return;
        }
        boolean bl4 = Minecraft.currentScreen().isNull();
        if (!bl4) {
            return;
        }
        boolean bl5 = this.releasePending;
        if (bl5) {
            this.handleRelease();
            return;
        }
        if (this.rePressPending) {
            this.handleRePress();
            return;
        }
    }

    private void handleRelease() {
        if (this.releaseTimer.hasTimeElapsed(((Double)this.releaseDelay.K()).longValue())) {
            KeyBinding keyBinding = Minecraft.gameSettings().Y();
            keyBinding.setPressed(false);
            this.releasePending = false;
            this.rePressTimer.reset();
            this.rePressPending = true;
        }
    }

    @Override
    public String r() {
        return this.releaseDelay.c();
    }

    public boolean F$src$Z$oodzg7() {
        return (Double)this.chance.K() >= Math.random() * 100.0;
    }
}
