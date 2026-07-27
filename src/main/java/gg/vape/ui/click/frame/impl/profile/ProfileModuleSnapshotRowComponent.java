package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.Profile;
import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class ProfileModuleSnapshotRowComponent
extends GuiComponent {
    private final ProfileModuleSnapshot i;
    private Color I;
    private Color O;
    private final ProfileSnapshot K;
    private Color b;
    private final Profile a;
    private Color G;
    private Color v;
    private Color Q;

    public ProfileSnapshot q$src$Lgg_vape_config_ProfileSnapshot_$tqzg71() {
        return this.K;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        ProfileSnapshotFrame profileSnapshotFrame = ClientSettings.g(ProfileSnapshotFrame.class);
        profileSnapshotFrame.V(this.K);
        profileSnapshotFrame.I(this.i);
        if (ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() instanceof ClickGuiFrameManager) {
            ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
            profileSnapshotFrame.A(clickGuiFrameManager);
            clickGuiFrameManager.K(profileSnapshotFrame);
        } else {
            ClientSettings.fW.I(ClientSettings.fr);
        }
    }

    @Override
    public double x() {
        return 110.0;
    }

    public void v(Color color, Color color2, Color color3, Color color4) {
        this.Q = color;
        this.v = color2;
        this.I = color3;
        this.O = color4;
    }

    private void K$src$V$8xpxfy() {
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public void H() {
        double d;
        String string;
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        double d2 = this.G$src$D$1b2f02a();
        this.getClass();
        GuiRenderPrimitives.d(d2 + 5.0, this.n() + 1.0, this.A() - 11.0, this.L() - 2.0, this.Q);
        if (this.w$src$Z$e457mb()) {
            double d3 = this.G$src$D$1b2f02a();
            this.getClass();
            GuiRenderPrimitives.P(d3 + 5.0, this.n() + 1.0, this.A() - 11.0, this.L() - 2.0, this.v, 2.0f, 0.8f, 1.0f);
        }
        double d4 = -1.0;
        double d5 = (float)(this.G$src$D$1b2f02a() + this.A()) - 14.0f;
        SmoothFontRenderer smoothFontRenderer2 = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.633);
        if (this.i.n()) {
            string = this.i.I();
            d = smoothFontRenderer2.N(string);
            double d6 = Math.max(8.0, d) + 6.0;
            double d7 = d6 - d;
            d4 = this.G$src$D$1b2f02a() + this.A() - 24.0 - d / 2.0;
            GuiRenderPrimitives.B(d4, this.n() + 6.0, d6, 8.0, this.v, 2.0f);
            smoothFontRenderer2.d(string, d4 + d7 / 2.0, this.n() + 8.0, this.I);
        }
        if (this.i.Q()) {
            this.b = J.z();
            this.G = ColorUtil.j();
            string = "ON";
            d = 14.0;
            d4 = d4 == -1.0 ? d5 - 11.0 : (d4 -= d + 2.0);
            GuiRenderPrimitives.B(d4, this.n() + 6.0, d, 8.0, this.b, 2.0f);
            smoothFontRenderer2.d(string, d4 + 3.0, this.n() + 8.0, this.G);
        }
        double d8 = this.G$src$D$1b2f02a() + 10.0;
        double d9 = this.A() - 8.0;
        if (d4 != -1.0) {
            d9 = d4 - d8 - 4.0;
        }
        TruncatedTextComponent truncatedTextComponent = new TruncatedTextComponent(this.i.getName(), "...", d9, 0.9, this.O, false);
        truncatedTextComponent.V(d8, this.n() + 10.0 - smoothFontRenderer.d(this.i.G().getName()) / 2.0);
    }

    public ProfileModuleSnapshot F$src$Lgg_vape_config_ProfileModuleSnapshot_$6toklw() {
        return this.i;
    }


    public Profile j$src$Lgg_vape_config_Profile_$1w1xpa8() {
        return this.a;
    }

    public ProfileModuleSnapshotRowComponent(double d, Profile profile, ProfileSnapshot profileSnapshot, ProfileModuleSnapshot profileModuleSnapshot) {
        this.Q = ProfileModuleSnapshotRowComponent.J.m;
        this.v = ProfileModuleSnapshotRowComponent.J.l;
        this.I = ProfileModuleSnapshotRowComponent.J.Z;
        this.b = J.z();
        this.G = ColorUtil.j();
        this.O = ProfileModuleSnapshotRowComponent.J.A;
        this.a = profile;
        this.i = profileModuleSnapshot;
        this.K = profileSnapshot;
        this.o(d);
    }
}

