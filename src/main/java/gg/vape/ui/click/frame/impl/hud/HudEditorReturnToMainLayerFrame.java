package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.HudEditorReturnToMainLayerHeaderComponent;
import gg.vape.wrapper.impl.Minecraft;

public class HudEditorReturnToMainLayerFrame
extends Frame {
    private boolean D9;
    private final HudEditorReturnToMainLayerHeaderComponent DM;

    public HudEditorReturnToMainLayerHeaderComponent Q$src$Lgg_vape_ui_click_frame_impl_hud_HudEditorReturn$el2t19() {
        return this.DM;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void Y() {
        this.T(HudEditorReturnToMainLayerFrame.J.i);
        ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().v(this);
        this.Z$src$V$1vz8z77();
    }

    @Override
    public String getName() {
        return "LegitToggleFrame";
    }

    public void Z$src$V$1vz8z77() {
        this.M((double)Minecraft.J() / 4.0 / Vape.INSTANCE.getClientSettings().s() - this.A() / 2.0, 7.0);
    }

    @Override
    public void F() {
        this.D9 = true;
    }

    @Override
    public void u() {
        if (this.D9 && !this.w$src$Z$e457mb()) {
            this.D9 = false;
        }
    }

    public HudEditorReturnToMainLayerFrame() {
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.I2 = false;
        this.Z(true);
        this.DM = new HudEditorReturnToMainLayerHeaderComponent(this);
        this.Y(this.DM);
    }

    @Override
    public boolean V$src$Z$1xhop3l() {
        return true;
    }

    @Override
    public void v() {
    }
}

