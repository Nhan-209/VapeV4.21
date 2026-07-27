package gg.vape.module.blatant.scaffold;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreEntityUpdate;
import gg.vape.event.impl.EventRender2D;
import gg.vape.input.KeyBindingHelper;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.blatant.Scaffold;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;

public class ScaffoldEdgeSneakHelper
extends SubModule<Scaffold> {
    private final TimerUtil sneakTimer;
    private long sneakDelayMs;
    private final Scaffold scaffold;
    private final RandomValue sneakDelay = RandomValue.G(this, "Sneak delay", "#", "", 0.0, 100.0, 200.0, 500.0, 1.0, "Delay until standing after sneaking");
    private boolean sneakKeyWasDown;

    @Override
    public void onEnable() {
        this.sneakTimer.reset();
        this.sneakDelayMs = (long)this.sneakDelay.B();
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        Scaffold.Access.V$src$V$dhg0vg(this.scaffold);
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void X(EventPreEntityUpdate eventPreEntityUpdate) {
        boolean bl;
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (!Scaffold.Access.G((Scaffold)this.getParent())) {
            return;
        }
        if (!eventPreEntityUpdate.getEntity().equals(Minecraft.thePlayer())) {
            return;
        }
        String threadName = "Client thread";
        if (ForgeVersion.MC_1_16_5.d()) {
            threadName = "Render thread";
        }
        if (!Thread.currentThread().getName().equals(threadName)) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding sneakKey = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        if (entityPlayerSP.S$src$Z$151gttj()) {
            SharedModuleControlClaims.I.X(this.scaffold);
            return;
        }
        if (Scaffold.Access.J$src$Z$dauhuk(this.scaffold) && (double)entityPlayerSP.V() < Scaffold.Access.V$src$D$dhg0fy(this.scaffold)) {
            SharedModuleControlClaims.I.X(this.scaffold);
            return;
        }
        this.sneakKeyWasDown = sneakKey.isKeyDown();
        boolean shouldSneak = false;
        float forwardInput = 0.0f;
        KeyBinding forwardKey = gameSettings.s();
        KeyBinding backKey = gameSettings.Y();
        if (ClientSettings.B(forwardKey)) {
            forwardInput += -1.0f;
        }
        if (ClientSettings.B(backKey)) {
            forwardInput += 1.0f;
        }
        boolean notMovingForward;
        boolean atEdge = notMovingForward = forwardInput <= 0.0f;
        if (notMovingForward && entityPlayerSP.b$src$Z$fqlxe4()) {
            AxisAlignedBB boundingBox;
            if (ForgeVersion.MC_1_8_9.d()) {
                boundingBox = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            } else {
                AxisAlignedBB currentBox = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
                boundingBox = currentBox.copy();
            }
            double motionX = entityPlayerSP.t();
            double offsetY = ForgeVersion.MC_1_20_6.d() ? 1.0 : -1.0;
            double motionZ = entityPlayerSP.T();
            AxisAlignedBB checkBox = boundingBox.expand(-0.2, 0.0, -0.2).k(motionX, offsetY, motionZ);
            int collisionCount = Minecraft.theWorld().i(entityPlayerSP, checkBox).size();
            if (collisionCount == 0) {
                shouldSneak = true;
                SharedModuleControlClaims.I.d(this.scaffold);
            }
        }
        boolean skipTimerReset = false;
        if (SharedModuleControlClaims.I.U(this.scaffold) && (forwardInput > 0.0f || !shouldSneak && this.sneakTimer.hasTimeElapsed(500L))) {
            SharedModuleControlClaims.I.X(this.scaffold);
        }
        if (!shouldSneak && !this.sneakTimer.hasTimeElapsed(this.sneakDelayMs) && this.sneakDelayMs > 30L) {
            shouldSneak = true;
            skipTimerReset = true;
        }
        if (shouldSneak && entityPlayerSP.b$src$Z$fqlxe4()) {
            if (!entityPlayerSP.P()) {
                this.sneakDelayMs = (long)this.sneakDelay.B();
            }
            KeyBindingHelper.d(sneakKey, true);
            if (!skipTimerReset) {
                this.sneakTimer.reset();
            }
        } else if (!this.sneakKeyWasDown) {
            KeyBindingHelper.d(sneakKey, false);
        }
    }

    @EventHandler
    public void g(EventPostTick eventPostTick) {
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (!Scaffold.Access.G((Scaffold)this.getParent())) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.S$src$Z$151gttj()) {
            return;
        }
        if (Scaffold.Access.J$src$Z$dauhuk(this.scaffold) && (double)entityPlayerSP.V() < Scaffold.Access.V$src$D$dhg0fy(this.scaffold)) {
            return;
        }
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding sneakKey = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        KeyBindingHelper.d(sneakKey, this.sneakKeyWasDown);
    }

    public ScaffoldEdgeSneakHelper(Mod mod, String string) {
        super(mod, string);
        this.scaffold = (Scaffold)this.getParent();
        this.sneakTimer = new TimerUtil();
        this.addValue(this.sneakDelay);
    }
}
