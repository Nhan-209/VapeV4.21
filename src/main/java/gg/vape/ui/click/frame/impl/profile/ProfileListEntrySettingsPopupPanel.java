package gg.vape.ui.click.frame.impl.profile;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileGlyphIconPanel;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

class ProfileListEntrySettingsPopupPanel
extends PanelComponent {
    final ProfileGlyphIconPanel cU;
    final GlyphIconComponent cM;
    final ProfileListEntryComponent ca;
    final TextInputComponentBase cc;
    final TruncatedTextComponent cf;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void c() {
        this.cf.K(this.G$src$D$1b2f02a() + this.A() / 2.0 - this.cf.u$src$D$ivbecn() / 2.0);
        this.cf.S(this.n() + 18.0);
        this.cM.K(this.cf.G$src$D$1b2f02a() - 8.0);
        this.cM.S(this.cf.n() + 1.0);
        this.cc.K(this.G$src$D$1b2f02a() + this.A() / 2.0 - this.cc.A() / 2.0);
        this.cc.S(this.cf.n() - 8.0);
        this.cf.Z(!this.cc.V$src$Z$1xhop3l());
        this.cM.Z(!this.cc.V$src$Z$1xhop3l() && this.w$src$Z$e457mb());
        if (this.cU != null) {
            this.cU.K(this.cf.G$src$D$1b2f02a() + this.cf.A() + 3.0);
            this.cU.S(this.cf.n() + 1.0);
            this.cU.Z(!this.cc.V$src$Z$1xhop3l());
        }
        super.c();
        if (this.cc.V$src$Z$1xhop3l()) {
            GuiRenderPrimitives.a(this.cc.G$src$D$1b2f02a() + 10.0, this.cc.n() + 17.0, this.cc.A() - 31.0, 1.0f, ProfileListEntrySettingsPopupPanel.J.y);
        }
    }

    ProfileListEntrySettingsPopupPanel(ProfileListEntryComponent profileListEntryComponent, double d, double d2, TruncatedTextComponent truncatedTextComponent, GlyphIconComponent glyphIconComponent, TextInputComponentBase textInputComponentBase, ProfileGlyphIconPanel profileGlyphIconPanel) {
        super(d, d2);
        this.ca = profileListEntryComponent;
        this.cf = truncatedTextComponent;
        this.cM = glyphIconComponent;
        this.cc = textInputComponentBase;
        this.cU = profileGlyphIconPanel;
    }
}
