package gg.vape.ui.click.frame.impl.main;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.impl.ClientSettingsComponentFactory;
import gg.vape.ui.click.frame.impl.ClientSettingsFrameSectionLabelComponent;
import gg.vape.ui.click.frame.impl.ThemeComponentGroupKey;
import gg.vape.ui.click.frame.impl.main.ClickGuiLayer;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrameHeaderActionComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSectionTabComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClickGuiMainFrameHeader
extends FrameHeaderComponent {
    private final GlyphIconComponent I;
    public static final double v = 10.0;
    private final ClickGuiMainFrameHeaderActionComponent R = new ClickGuiMainFrameHeaderActionComponent();
    private final GlyphIconComponent qv;
    private final List<ClickGuiSectionTabComponent> G;
    private final List<GlyphIconComponent> i;
    private final GlyphIconComponent o;
    public static final double K = 5.0;
    public static final double q8 = 6.0;
    public static final double O = 40.0;
    private final GlyphIconComponent Q;


    private static void lambda$null$5(GuiComponent[] guiComponentArray, PanelComponent panelComponent) {
        if (guiComponentArray != null) {
            double d = Math.max(0.0, panelComponent.A());
            for (GuiComponent guiComponent : guiComponentArray) {
                guiComponent.o(d);
                guiComponent.q(d);
                panelComponent.h(guiComponent, new Object[0]);
            }
        }
    }

    private void lambda$new$1() {
        Vape.INSTANCE.getSyncThread().requestSave();
        this.I.Z(false);
    }

    private static void lambda$new$0() {
        ClientSettings.fW.I(ClientSettings.a);
        Vape.INSTANCE.getPublicProfileSettings().n.setValue(Vape.INSTANCE.getPublicProfileSettings().M);
        Vape.INSTANCE.getPublicProfileSettings().n.setValue(Vape.INSTANCE.getPublicProfileSettings().P);
        Vape.INSTANCE.getNotificationManager().k("Refreshed", "StandaloneGUI refreshed", 1000L);
    }

    public void Q(ClickGuiSectionTabComponent clickGuiSectionTabComponent) {
        this.G.add(clickGuiSectionTabComponent);
        this.H(clickGuiSectionTabComponent);
    }

    private static void lambda$null$6(ThemeComponentGroupKey themeComponentGroupKey, ClickGuiMainFrame clickGuiMainFrame, String string, String string2) {
        Map<ThemeComponentGroupKey, GuiComponent[]> map = ClientSettingsComponentFactory.d(J, Vape.INSTANCE.getClientSettings(), ClientSettings.fW, true);
        GuiComponent[] guiComponentArray = map.get(themeComponentGroupKey);
        clickGuiMainFrame.Z(ClickGuiOverlaySpec.q().e(string).D(arg_0 -> ClickGuiMainFrameHeader.lambda$null$4(string2, arg_0)).N(arg_0 -> ClickGuiMainFrameHeader.lambda$null$5(guiComponentArray, arg_0)).r(ClickGuiOverlayTransitionMode.PUSH).w());
    }

    private static void lambda$new$8(ClickGuiMainFrame clickGuiMainFrame) {
        clickGuiMainFrame.Z(ClickGuiOverlaySpec.q().e("Settings").D(ClickGuiMainFrameHeader::lambda$null$3).N(arg_0 -> ClickGuiMainFrameHeader.lambda$null$7(clickGuiMainFrame, arg_0)).w());
    }

    @Override
    public void H() {
        double d;
        if (!Vape.INSTANCE.getPublicProfileSettings().o.L().booleanValue() && Vape.INSTANCE.getSyncThread().hasPendingSave() && System.currentTimeMillis() > Vape.INSTANCE.getSyncThread().getLastSaveTime() + 60000L) {
            this.I.Z(true);
        } else {
            this.I.Z(false);
        }
        double d2 = this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0() != null ? this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().G$src$D$1b2f02a() : this.G$src$D$1b2f02a();
        double d3 = this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0() != null ? this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().n() : this.n();
        double d4 = this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0() != null ? this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().A() : this.A();
        this.K(d2);
        this.S(d3);
        this.o(d4);
        double d5 = this.G$src$D$1b2f02a();
        double d6 = this.n();
        double d7 = this.A();
        double d8 = this.L();
        GuiRenderPrimitives.p(d5, d6, d7, d8, ClickGuiMainFrameHeader.J.r, false, 2.0f, 1.0f, 0.0f, ClickGuiMainFrameHeader.J.B, 3);
        GuiRenderPrimitives.C(d5, d6 + d8 - 1.0, d7, 0.5, ClickGuiMainFrameHeader.J.s);
        this.R.K(d5 + 6.0);
        this.R.S(d6 + 6.0);
        this.R.y(6.5f);
        double d9 = d6 + 5.0;
        int n = 0;
        for (int i = this.i.size() - 1; i >= 0; --i) {
            GlyphIconComponent glyphIconComponent = this.i.get(i);
            if (!glyphIconComponent.V$src$Z$1xhop3l()) continue;
            d = d5 + d7 - 10.0 - 5.0 - (double)n * 15.0;
            glyphIconComponent.K(d);
            glyphIconComponent.S(d9);
            ++n;
        }
        double d10 = d5 + 6.0;
        d = d6 + d8 - 20.0 - 2.0;
        for (ClickGuiSectionTabComponent clickGuiSectionTabComponent : this.G) {
            double d11 = clickGuiSectionTabComponent.V$src$D$1ysbxe7();
            clickGuiSectionTabComponent.o(d11);
            clickGuiSectionTabComponent.Y(20.0);
            clickGuiSectionTabComponent.K(d10);
            clickGuiSectionTabComponent.S(d);
            d10 += d11 + 12.0;
        }
    }

    private static void lambda$null$4(String string, ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.B(string != null ? string : "newsettings");
    }

    private static void lambda$null$3(ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.B("newsettings");
    }

    private static void lambda$null$7(ClickGuiMainFrame clickGuiMainFrame, PanelComponent panelComponent) {
        Object object;
        Map<ThemeComponentGroupKey, GuiComponent[]> map = ClientSettingsComponentFactory.d(J, Vape.INSTANCE.getClientSettings(), ClientSettings.fW, true);
        for (Map.Entry<ThemeComponentGroupKey, GuiComponent[]> object22 : map.entrySet()) {
            object = object22.getKey();
            ThemeComponentGroupKey groupKey = (ThemeComponentGroupKey)object;
            String string = groupKey.h();
            String string2 = groupKey.u();
            ClientSettingsFrameSectionLabelComponent clientSettingsFrameSectionLabelComponent = new ClientSettingsFrameSectionLabelComponent(string);
            double d = Math.max(0.0, panelComponent.A());
            clientSettingsFrameSectionLabelComponent.o(d);
            clientSettingsFrameSectionLabelComponent.q(d);
            clientSettingsFrameSectionLabelComponent.Y(18.0);
            clientSettingsFrameSectionLabelComponent.T(ClickGuiMainFrameHeader.J.m);
            clientSettingsFrameSectionLabelComponent.r(() -> ClickGuiMainFrameHeader.lambda$null$6(groupKey, clickGuiMainFrame, string, string2));
            panelComponent.h(clientSettingsFrameSectionLabelComponent, new Object[0]);
        }
        List<GuiComponent> list = ClientSettingsComponentFactory.M(J, Vape.INSTANCE.getClientSettings(), ClientSettings.fW, true);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            object = (GuiComponent)iterator.next();
            ((GuiComponent)object).q(panelComponent.A());
            ((GuiComponent)object).o(panelComponent.A());
            panelComponent.h((GuiComponent)object, new Object[0]);
        }
    }

    public ClickGuiMainFrameHeader(ClickGuiMainFrame clickGuiMainFrame) {
        super(clickGuiMainFrame);
        this.o = new GlyphIconComponent("weapons", 6.0, 6.0, 10.0, 10.0, ClickGuiMainFrameHeader.J.V, ClickGuiMainFrameHeader.J.f, null);
        this.I = new GlyphIconComponent("newsync", 6.0, 6.0, 10.0, 10.0, ClickGuiMainFrameHeader.J.V, ClickGuiMainFrameHeader.J.f, null);
        this.Q = new GlyphIconComponent("newoverlays_2x", 6.0, 6.0, 10.0, 10.0, ClickGuiMainFrameHeader.J.V, ClickGuiMainFrameHeader.J.f, null);
        this.qv = new GlyphIconComponent("newsettings", 6.0, 6.0, 10.0, 10.0, ClickGuiMainFrameHeader.J.V, ClickGuiMainFrameHeader.J.f, null);
        this.G = new ArrayList<ClickGuiSectionTabComponent>();
        this.i = new ArrayList<GlyphIconComponent>();
        this.Y(40.0);
        this.d(false);
        this.o.q(true);
        this.o.R(true);
        this.o.s(ClickGuiMainFrameHeader::lambda$new$0);
        this.I.q(true);
        this.I.R(true);
        this.I.w("Save your profiles to the cloud");
        this.I.s(this::lambda$new$1);
        this.Q.q(true);
        this.Q.R(true);
        this.Q.E(ClickGuiMainFrameHeader.J.t, ClickGuiMainFrameHeader.J.M);
        this.Q.i(5.0f);
        this.Q.s(ClickGuiMainFrameHeader::lambda$new$2);
        this.qv.q(true);
        this.qv.R(true);
        this.qv.E(ClickGuiMainFrameHeader.J.t, ClickGuiMainFrameHeader.J.M);
        this.qv.i(5.0f);
        this.qv.s(() -> ClickGuiMainFrameHeader.lambda$new$8(clickGuiMainFrame));
        this.i.addAll(Arrays.asList(this.I, this.Q, this.qv));
        this.H(this.R, this.I, this.Q, this.qv);
    }

    private static void lambda$new$2() {
        ClientSettings.f5.G(ClickGuiLayer.OVERLAYS);
    }
}
