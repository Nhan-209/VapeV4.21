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
    private final TimerUtil K;
    private long O;
    private final Scaffold D;
    private final RandomValue Z = RandomValue.G(this, "Sneak delay", "#", "", 0.0, 100.0, 200.0, 500.0, 1.0, "Delay until standing after sneaking");
    private boolean b;

    @Override
    public void onEnable() {
        this.K.reset();
        this.O = (long)this.Z.B();
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        Scaffold.Access.V$src$V$dhg0vg(this.D);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
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
        String string = "Client thread";
        if (ForgeVersion.MC_1_16_5.d()) {
            string = "Render thread";
        }
        if (!Thread.currentThread().getName().equals(string)) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding keyBinding = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        if (entityPlayerSP.S$src$Z$151gttj()) {
            SharedModuleControlClaims.I.X(this.D);
            return;
        }
        if (Scaffold.Access.J$src$Z$dauhuk(this.D) && (double)entityPlayerSP.V() < Scaffold.Access.V$src$D$dhg0fy(this.D)) {
            SharedModuleControlClaims.I.X(this.D);
            return;
        }
        this.b = keyBinding.isKeyDown();
        boolean bl2 = false;
        float f = 0.0f;
        KeyBinding keyBinding2 = gameSettings.s();
        KeyBinding keyBinding3 = gameSettings.Y();
        if (ClientSettings.B(keyBinding2)) {
            f += -1.0f;
        }
        if (ClientSettings.B(keyBinding3)) {
            f += 1.0f;
        }
        boolean bl3 = bl = f <= 0.0f;
        if (bl && entityPlayerSP.b$src$Z$fqlxe4()) {
            AxisAlignedBB axisAlignedBB;
            if (ForgeVersion.MC_1_8_9.d()) {
                axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            } else {
                AxisAlignedBB axisAlignedBB2 = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
                axisAlignedBB = axisAlignedBB2.copy();
            }
            double d = entityPlayerSP.t();
            double d2 = ForgeVersion.MC_1_20_6.d() ? 1.0 : -1.0;
            double d3 = entityPlayerSP.T();
            AxisAlignedBB axisAlignedBB3 = axisAlignedBB.expand(-0.2, 0.0, -0.2).k(d, d2, d3);
            int n = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB3).size();
            if (n == 0) {
                bl2 = true;
                SharedModuleControlClaims.I.d(this.D);
            }
        }
        boolean bl4 = false;
        if (SharedModuleControlClaims.I.U(this.D) && (f > 0.0f || !bl2 && this.K.hasTimeElapsed(500L))) {
            SharedModuleControlClaims.I.X(this.D);
        }
        if (!bl2 && !this.K.hasTimeElapsed(this.O) && this.O > 30L) {
            bl2 = true;
            bl4 = true;
        }
        if (bl2 && entityPlayerSP.b$src$Z$fqlxe4()) {
            if (!entityPlayerSP.P()) {
                this.O = (long)this.Z.B();
            }
            KeyBindingHelper.d(keyBinding, true);
            if (!bl4) {
                this.K.reset();
            }
        } else if (!this.b) {
            KeyBindingHelper.d(keyBinding, false);
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
        if (Scaffold.Access.J$src$Z$dauhuk(this.D) && (double)entityPlayerSP.V() < Scaffold.Access.V$src$D$dhg0fy(this.D)) {
            return;
        }
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding keyBinding = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        KeyBindingHelper.d(keyBinding, this.b);
    }

    public ScaffoldEdgeSneakHelper(Mod mod, String string) {
        super(mod, string);
        this.D = (Scaffold)this.getParent();
        this.K = new TimerUtil();
        this.addValue(this.Z);
    }
}
