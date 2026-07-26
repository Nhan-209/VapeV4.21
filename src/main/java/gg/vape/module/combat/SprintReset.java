package gg.vape.module.combat;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;

public class SprintReset
extends Mod {
    private final NumberValue H = NumberValue.E(this, "Chance", "#", "%", 0.0, 90.0, 100.0, "Chance of WTapping when hitting a target");
    private final TimerUtil Y;
    private final NumberValue V = NumberValue.create(this, "Release delay", "#", "", 0.0, 0.0, 500.0, 50.0, "Delay before releasing W key after hitting a target");
    private boolean U;
    private final TimerUtil F;
    private final NumberValue s = NumberValue.create(this, "Re-press delay", "#", "", 0.0, 0.0, 500.0, 50.0, "Delay before re-pressing W key after releasing it");
    private final BooleanValue L = BooleanValue.create(this, "Select hits", true, "Only WTap when the target is vulnerable");
    private boolean o;
    private static final long k = -5147998889622254014L;

    private void c() {
        if (this.Y.hasTimeElapsed(((Double)this.s.K()).longValue())) {
            KeyBinding keyBinding = Minecraft.gameSettings().Y();
            boolean bl = ClientSettings.B(keyBinding);
            if (bl) {
                keyBinding.setPressed(true);
            }
            this.o = false;
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void F(EventPreAttack eventPreAttack) {
        boolean bl = Packet.h();
        if (bl) {
            int n;
            boolean bl2;
            boolean bl3 = eventPreAttack.getTarget().isInstance(MappedClasses.lG);
            if (!bl3) {
                return;
            }
            boolean bl4 = this.U;
            if (bl4 || (bl2 = this.o)) {
                return;
            }
            boolean bl5 = this.L.L();
            if (bl5 && (n = eventPreAttack.getTarget().V$src$I$fk0dv5()) > 14) {
                return;
            }
            if (this.F$src$Z$oodzg7()) {
                this.U = true;
                this.F.reset();
                this.G();
            }
            return;
        }
        boolean bl6 = eventPreAttack.getTarget().isInstance(MappedClasses.lG);
        boolean bl7 = bl6;
        boolean bl8 = bl7;
        boolean bl9 = bl8;
        if (bl9) {
            this.U = true;
            this.F.reset();
            this.G();
        }
    }

    public SprintReset() {
        super("WTap", (int)k, Category.g);
        this.F = new TimerUtil();
        this.Y = new TimerUtil();
        this.addValue(this.H, this.V, this.s, this.L);
        this.V.C(0);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        boolean bl = Packet.A();
        if (bl) {
            boolean bl2 = Minecraft.currentScreen().isNull();
            boolean bl3 = bl2;
            if (bl3) {
                this.c();
                return;
            }
            return;
        }
        boolean bl4 = Minecraft.currentScreen().isNull();
        if (!bl4) {
            return;
        }
        boolean bl5 = this.U;
        if (bl5) {
            this.G();
            return;
        }
        if (this.o) {
            this.c();
            return;
        }
    }

    private void G() {
        if (this.F.hasTimeElapsed(((Double)this.V.K()).longValue())) {
            KeyBinding keyBinding = Minecraft.gameSettings().Y();
            keyBinding.setPressed(false);
            this.U = false;
            this.Y.reset();
            this.o = true;
        }
    }

    @Override
    public String r() {
        return this.V.c();
    }

    public boolean F$src$Z$oodzg7() {
        return (Double)this.H.K() >= Math.random() * 100.0;
    }
}

