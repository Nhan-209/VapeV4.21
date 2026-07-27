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
import gg.vape.rotation.RotationManager;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.MovementInput;

public class LegitScaffoldMode
extends SubModule<Scaffold> {
    private long sneakDelayMs;
    private boolean sneakKeyWasDown;
    private Scaffold scaffold;
    private final TimerUtil standDelayTimer;
    protected final BooleanValue requireSneak;
    private final RandomValue sneakDelay = RandomValue.G(this, "Sneak delay", "#", "", 0.0, 100.0, 200.0, 500.0, 1.0, "Delay until standing after sneaking");
    private boolean standingFlag = false;
    private final TimerUtil edgeSneakTimer;

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        Scaffold.Access.V$src$V$dhg0vg(this.scaffold);
    }

    public boolean shouldScaffold(EntityPlayerSP entityPlayerSP) {
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        if (this.requireSneak.L().booleanValue() && !ClientSettings.B(Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0())) {
            return false;
        }
        if (entityPlayerSP.S$src$Z$151gttj()) {
            return false;
        }
        if (Scaffold.Access.J$src$Z$dauhuk(this.scaffold) && (double)entityPlayerSP.V() < Scaffold.Access.V$src$D$dhg0fy(this.scaffold)) {
            return false;
        }
        return Scaffold.Access.G((Scaffold)this.getParent());
    }


    public LegitScaffoldMode(Mod mod, String string) {
        super(mod, string);
        this.requireSneak = BooleanValue.create(this, "Require sneak", false, "Must be holding sneak to scaffold");
        this.scaffold = (Scaffold)this.getParent();
        this.edgeSneakTimer = new TimerUtil();
        this.standDelayTimer = new TimerUtil();
        this.addValue(this.sneakDelay, this.requireSneak);
    }

    @Override
    public void onEnable() {
        this.sneakDelayMs = (long)this.sneakDelay.B();
    }

    @EventHandler
    public void onPreEntityUpdate(EventPreEntityUpdate eventPreEntityUpdate) {
        boolean atEdge;
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
        if (!this.shouldScaffold(entityPlayerSP)) {
            return;
        }
        MovementInput movementInput = entityPlayerSP.movementInput();
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding sneakKey = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        this.sneakKeyWasDown = ClientSettings.B(sneakKey);
        boolean shouldSneak = false;
        float forwardInput = movementInput.D();
        if (RotationManager.b.u()) {
            forwardInput = 0.0f;
            if (ClientSettings.B(gameSettings.Y())) {
                forwardInput += 1.0f;
            }
            if (ClientSettings.B(gameSettings.s())) {
                forwardInput -= 1.0f;
            }
        }
        boolean notMovingForward = atEdge = forwardInput <= 0.0f;
        if (forwardInput > 0.0f) {
            atEdge = false;
        }
        if (atEdge && entityPlayerSP.b$src$Z$fqlxe4()) {
            AxisAlignedBB boundingBox;
            if (ForgeVersion.MC_1_8_9.d()) {
                boundingBox = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            } else {
                AxisAlignedBB currentBox = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
                boundingBox = currentBox.copy();
            }
            double motionX = entityPlayerSP.t();
            double offsetY = -1.0;
            double motionZ = entityPlayerSP.T();
            AxisAlignedBB checkBox = boundingBox.expand(-0.2, 0.0, -0.2).k(motionX, offsetY, motionZ);
            int collisionCount = Minecraft.theWorld().i(entityPlayerSP, checkBox).size();
            if (collisionCount == 0) {
                shouldSneak = true;
            }
        }
        boolean skipTimerReset = false;
        if (!shouldSneak && !this.edgeSneakTimer.hasTimeElapsed(this.sneakDelayMs) && this.sneakDelayMs > 30L) {
            shouldSneak = true;
            skipTimerReset = true;
        }
        if (entityPlayerSP.b$src$Z$fqlxe4()) {
            if (shouldSneak) {
                if (!entityPlayerSP.P()) {
                    this.sneakDelayMs = (long)this.sneakDelay.B();
                }
                KeyBindingHelper.d(sneakKey, true);
                this.standDelayTimer.reset();
                if (!skipTimerReset) {
                    this.edgeSneakTimer.reset();
                }
            } else if (this.requireSneak.L().booleanValue()) {
                if (!this.standDelayTimer.hasTimeElapsed(1000L) && forwardInput < 0.0f) {
                    KeyBindingHelper.d(sneakKey, false);
                }
            } else if (!this.sneakKeyWasDown) {
                KeyBindingHelper.d(sneakKey, false);
            }
        }
    }

    @EventHandler
    public void onPostTick(EventPostTick eventPostTick) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!this.shouldScaffold(entityPlayerSP)) {
            return;
        }
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding sneakKey = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        KeyBindingHelper.d(sneakKey, this.sneakKeyWasDown);
        this.standingFlag = false;
    }
}
