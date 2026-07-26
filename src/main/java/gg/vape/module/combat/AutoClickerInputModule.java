package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.click.AutoClickerTimingState;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.input.KeyBindingInputState;
import gg.vape.input.KeyboardInput;
import gg.vape.input.MouseInput;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;

public class AutoClickerInputModule
extends Mod {
    private final AutoClickerTimingState c;
    private int V = -1;
    private final TimerUtil F = new TimerUtil();
    private final NumberValue p = NumberValue.create(this, "CPS", "#.#", "", 1.0, 15.0, 20.0);

    private boolean g(double d) {
        double d2 = Math.max(Math.min(d, 1.0), 0.0);
        return Math.random() <= d2;
    }

    @EventHandler
    public final void H(EventPreRenderTick eventPreRenderTick) {
        if (!KeyBindingInputState.l()) {
            return;
        }
        this.c.Z((int)((Double)this.p.K() - 1.0), (int)((Double)this.p.K() + 1.0));
        if (!this.F.hasTimeElapsed(this.c.Y()) && (Double)this.p.K() < 20.0) {
            return;
        }
        GuiScreen guiScreen = Minecraft.currentScreen();
        boolean bl = guiScreen.isInstance(MappedClasses.Ft);
        boolean bl2 = guiScreen.isNull();
        if (bl && !bl2) {
            boolean bl3;
            boolean bl4 = bl3 = KeyboardInput.isKeyDown(160) || KeyboardInput.isKeyDown(161);
            if (bl3 && guiScreen.isNotNull()) {
                GuiContainer guiContainer = new GuiContainer(guiScreen);
                this.R(guiContainer);
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public AutoClickerInputModule() {
        super("InventoryFill", -12288, Category.M, "Clicks items in inventory while holding shift");
        this.c = new AutoClickerTimingState(Vape.INSTANCE.getAccountTier());
        this.addValue(this.p);
    }

    public void R(GuiContainer guiContainer) {
        ItemStack itemStack;
        boolean bl;
        int n = -1;
        int n2 = MouseInput.N() * guiContainer.g() / Minecraft.J();
        int n3 = guiContainer.k() - MouseInput.u() * guiContainer.k() / Minecraft.h() - 1;
        Slot slot = guiContainer.getSlotAtPosition(n2, n3);
        int n4 = guiContainer.p();
        int n5 = guiContainer.v();
        boolean bl2 = bl = n2 < n4 || n3 < n5 || n2 >= n4 + guiContainer.x() || n3 >= n5 + guiContainer.b();
        if (slot.isNotNull()) {
            n = slot.g();
        }
        if (bl) {
            n = -1;
        }
        if (n >= 0 && (itemStack = RotationUtil.Z()).isNull() && this.V != n) {
            KeyBindingInputState.k();
            KeyBindingInputState.r();
            if (this.g(0.8)) {
                this.V = n;
            }
            this.F.reset();
        }
    }
}

