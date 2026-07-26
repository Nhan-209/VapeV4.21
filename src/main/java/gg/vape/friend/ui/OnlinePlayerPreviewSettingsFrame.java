package gg.vape.friend.ui;

import com.google.gson.JsonObject;
import gg.vape.friend.ui.OnlinePlayerPreviewComponent;
import gg.vape.friend.ui.OnlinePlayerPreviewDividerComponent;
import gg.vape.friend.ui.OnlinePlayerPreviewSettings;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.NumberSliderComponent;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionsFrame;

public class OnlinePlayerPreviewSettingsFrame
extends HudSettingsFrameBase {
    private boolean SI;
    private static GuiComponent[] ST;
    private final NumberSliderComponent SB;
    private final OnlinePlayerPreviewSettings S5 = new OnlinePlayerPreviewSettings();
    private final NumberSliderComponent SC;
    private final NumberSliderComponent S0;
    private final BooleanToggleComponent S4;

    @Override
    public void t(JsonObject jsonObject) {
        super.t(jsonObject);
        ClientSettings.g(QuickActionsFrame.class).N$src$Lgg_vape_ui_click_frame_impl_quickactions_QuickA$1smecqc().h(this.V$src$Z$1xhop3l());
    }

    @Override
    public void Y() {
        if (this.SB.W$src$Z$38isfa()) {
            if (!this.SI) {
                this.SI = true;
            }
        } else if (this.SI) {
            this.SI = false;
            this.H(true);
        }
    }

    public OnlinePlayerPreviewSettings B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92() {
        return this.S5;
    }

    @Override
    public String getName() {
        return "Rearview";
    }

    static {
        OnlinePlayerPreviewSettingsFrame.i(null);
    }

    public OnlinePlayerPreviewSettingsFrame() {
        super("newrearview", "Rearview");
        this.SB = new NumberSliderComponent(this.S5.d);
        this.SC = new NumberSliderComponent(this.S5.p);
        this.S0 = new NumberSliderComponent(this.S5.W);
        this.S4 = new BooleanToggleComponent(this.S5.A);
        this.SB.T(OnlinePlayerPreviewSettingsFrame.J.r);
        this.SC.T(OnlinePlayerPreviewSettingsFrame.J.r);
        this.S0.T(OnlinePlayerPreviewSettingsFrame.J.r);
        this.S4.T(OnlinePlayerPreviewSettingsFrame.J.r);
        this.M(this.SB, this.SC, this.S0, this.S4, new OnlinePlayerPreviewDividerComponent(this, OnlinePlayerPreviewSettingsFrame.J.l));
        this.h(new OnlinePlayerPreviewComponent(this), new Object[0]);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static GuiComponent[] r$src$ALgg_vape_ui_click_component_GuiComponent_$1vb3zts() {
        return ST;
    }

    public static void i(GuiComponent[] guiComponentArray) {
        ST = guiComponentArray;
    }
}

