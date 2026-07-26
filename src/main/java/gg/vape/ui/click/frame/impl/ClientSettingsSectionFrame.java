package gg.vape.ui.click.frame.impl;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ColorDividerComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrame;
import gg.vape.ui.click.frame.impl.ClientSettingsSectionFrameCloseSearchClickHandler;
import gg.vape.ui.click.frame.impl.ClientSettingsSectionFrameCloseSettingsClickHandler;
import gg.vape.ui.click.frame.impl.SettingsFrameHeaderComponent;

public class ClientSettingsSectionFrame
extends Frame {
    private String mR = "Settings";

    public ClientSettingsSectionFrame() {
        this.T(ClientSettingsSectionFrame.J.i);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        ClientSettingsSearchFrame clientSettingsSearchFrame = ClientSettings.g(ClientSettingsSearchFrame.class);
        this.Y(clientSettingsSearchFrame.L());
        this.o(clientSettingsSearchFrame.A());
        this.s$src$V$yca8r0();
        this.H(new ColorDividerComponent(ClientSettingsSectionFrame.J.l));
        this.Z(false);
        this.L(false, false);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ClientSettingsSectionFrame z(String string) {
        this.mR = string;
        return this;
    }

    @Override
    public void v() {
    }

    @Override
    public void Y() {
        this.Y(238.0);
    }

    @Override
    public String getName() {
        return this.mR;
    }

    public void s$src$V$yca8r0() {
        if (this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null) {
            this.I(this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc());
        }
        SettingsFrameHeaderComponent settingsFrameHeaderComponent = new SettingsFrameHeaderComponent(this, this.mR);
        settingsFrameHeaderComponent.n(new ClientSettingsSectionFrameCloseSettingsClickHandler(this));
        settingsFrameHeaderComponent.f(new ClientSettingsSectionFrameCloseSearchClickHandler(this));
        this.Y(settingsFrameHeaderComponent);
    }
}

