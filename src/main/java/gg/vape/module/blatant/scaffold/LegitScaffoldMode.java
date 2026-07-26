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
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private long L;
    private boolean o;
    private Scaffold F;
    private final TimerUtil I;
    protected final BooleanValue V;
    private final RandomValue Z = RandomValue.G(this, "Sneak delay", "#", "", 0.0, 100.0, 200.0, 500.0, 1.0, "Delay until standing after sneaking");
    private boolean v = false;
    private final TimerUtil s;

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        Scaffold.Access.V$src$V$dhg0vg(this.F);
    }

    public boolean W(EntityPlayerSP entityPlayerSP) {
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        if (this.V.L().booleanValue() && !ClientSettings.B(Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0())) {
            return false;
        }
        if (entityPlayerSP.S$src$Z$151gttj()) {
            return false;
        }
        if (Scaffold.Access.J$src$Z$dauhuk(this.F) && (double)entityPlayerSP.V() < Scaffold.Access.V$src$D$dhg0fy(this.F)) {
            return false;
        }
        return Scaffold.Access.G((Scaffold)this.getParent());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public LegitScaffoldMode(Mod mod, String string) {
        super(mod, string);
        this.V = BooleanValue.create(this, "Require sneak", false, "Must be holding sneak to scaffold");
        this.F = (Scaffold)this.getParent();
        this.s = new TimerUtil();
        this.I = new TimerUtil();
        this.addValue(this.Z, this.V);
    }

    @Override
    public void onEnable() {
        this.L = (long)this.Z.B();
    }

    @EventHandler
    public void e(EventPreEntityUpdate eventPreEntityUpdate) {
        boolean bl;
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
        if (!this.W(entityPlayerSP)) {
            return;
        }
        MovementInput movementInput = entityPlayerSP.movementInput();
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding keyBinding = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        this.o = ClientSettings.B(keyBinding);
        boolean bl2 = false;
        float f = movementInput.D();
        if (RotationManager.b.u()) {
            f = 0.0f;
            if (ClientSettings.B(gameSettings.Y())) {
                f += 1.0f;
            }
            if (ClientSettings.B(gameSettings.s())) {
                f -= 1.0f;
            }
        }
        boolean bl3 = bl = f <= 0.0f;
        if (f > 0.0f) {
            bl = false;
        }
        if (bl && entityPlayerSP.b$src$Z$fqlxe4()) {
            AxisAlignedBB axisAlignedBB;
            if (ForgeVersion.MC_1_8_9.d()) {
                axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            } else {
                AxisAlignedBB axisAlignedBB2 = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
                axisAlignedBB = axisAlignedBB2.copy();
            }
            double d = entityPlayerSP.t();
            double d2 = -1.0;
            double d3 = entityPlayerSP.T();
            AxisAlignedBB axisAlignedBB3 = axisAlignedBB.expand(-0.2, 0.0, -0.2).k(d, d2, d3);
            int n = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB3).size();
            if (n == 0) {
                bl2 = true;
            }
        }
        boolean bl4 = false;
        if (!bl2 && !this.s.hasTimeElapsed(this.L) && this.L > 30L) {
            bl2 = true;
            bl4 = true;
        }
        if (entityPlayerSP.b$src$Z$fqlxe4()) {
            if (bl2) {
                if (!entityPlayerSP.P()) {
                    this.L = (long)this.Z.B();
                }
                KeyBindingHelper.d(keyBinding, true);
                this.I.reset();
                if (!bl4) {
                    this.s.reset();
                }
            } else if (this.V.L().booleanValue()) {
                if (!this.I.hasTimeElapsed(1000L) && f < 0.0f) {
                    KeyBindingHelper.d(keyBinding, false);
                }
            } else if (!this.o) {
                KeyBindingHelper.d(keyBinding, false);
            }
        }
    }

    @EventHandler
    public void k(EventPostTick eventPostTick) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!this.W(entityPlayerSP)) {
            return;
        }
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding keyBinding = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        KeyBindingHelper.d(keyBinding, this.o);
        this.v = false;
    }
}
