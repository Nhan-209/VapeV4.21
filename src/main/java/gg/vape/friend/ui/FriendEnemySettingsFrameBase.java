package gg.vape.friend.ui;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.CollapsibleFrame;
import gg.vape.ui.click.frame.OutlinedFrameBase;
import gg.vape.ui.click.frame.ToggleableFrameHeaderComponent;

public abstract class FriendEnemySettingsFrameBase
extends OutlinedFrameBase
implements CollapsibleFrame {
    protected ToggleableFrameHeaderComponent KG;
    private static final String eb = "wrap";
    private boolean Kk = false;

    @Override
    public void w() {
        this.Kk = !this.Kk;
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.j$src$Z$dapde9()) continue;
            guiComponent.Z(!this.Kk);
        }
        if (this.Kk) {
            this.KG.L(false);
        }
        this.l$src$V$1mibm4x();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean q() {
        return this.Kk;
    }

    public FriendEnemySettingsFrameBase(String string, String string2, double d) {
        this.T(FriendEnemySettingsFrameBase.J.i);
        this.K(300.0);
        this.S(100.0);
        this.Z(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(eb);
        this.KG = new ToggleableFrameHeaderComponent(this, string, string2, d);
        this.Y(this.KG);
    }

    public ToggleableFrameHeaderComponent A$src$Lgg_vape_ui_click_frame_ToggleableFrameHeaderCom$hw06ag() {
        return this.KG;
    }

    public void U(GuiComponent ... guiComponentArray) {
        this.KG.q(guiComponentArray);
    }

    public FriendEnemySettingsFrameBase(String string, String string2) {
        this(string, string2, 1.0);
    }
}

