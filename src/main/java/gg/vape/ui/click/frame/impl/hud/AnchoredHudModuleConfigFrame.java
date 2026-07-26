package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.render.hud.HudModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.hud.AnchoredHudModuleConfigCloseHeaderButton;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameOpenConfigClickHandler;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrameHeaderActionComponent;
import gg.vape.wrapper.impl.Minecraft;

public class AnchoredHudModuleConfigFrame<T extends InteractiveComponent>
extends Frame {
    private T wM;
    private double wK;
    private final SimpleTextLabelComponent wm;
    private String wG = "OverlaySettingsFrame";
    private HudModule wJ;
    private static boolean wS;
    private double wv;

    public static boolean x$src$Z$x2ngyz() {
        boolean bl = AnchoredHudModuleConfigFrame.C$src$Z$w9idiu();
        return false;
    }

    public HudModule s$src$Lgg_vape_module_render_hud_HudModule_$14buku() {
        return this.wJ;
    }

    public void h() {
        if (((GuiComponent)this.wM).G$src$D$1b2f02a() != this.wv || ((GuiComponent)this.wM).n() != this.wK) {
            double d = ((GuiComponent)this.wM).n();
            FrameComponent frameComponent = ((GuiComponent)this.wM).B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
            if (frameComponent.k$src$Z$if6xeb()) {
                d = Math.min(d, frameComponent.n() + frameComponent.d$src$D$ibccpu() - ((GuiComponent)this.wM).L());
                d = Math.max(d, frameComponent.n() + frameComponent.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
            }
            if (((GuiComponent)this.wM).G$src$D$1b2f02a() + ((GuiComponent)this.wM).A() + this.A() > (double)Minecraft.G().T()) {
                this.M(((GuiComponent)this.wM).G$src$D$1b2f02a() - this.A() + 13.0, d);
            } else {
                this.M(((GuiComponent)this.wM).G$src$D$1b2f02a() + ((GuiComponent)this.wM).A() - 13.0, d);
            }
            this.wv = ((GuiComponent)this.wM).G$src$D$1b2f02a();
            this.wK = ((GuiComponent)this.wM).n();
        }
    }

    @Override
    public void v() {
    }

    @Override
    public void Y() {
        PublicProfilesFrameHeaderActionComponent publicProfilesFrameHeaderActionComponent = (PublicProfilesFrameHeaderActionComponent)this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc();
        if (!publicProfilesFrameHeaderActionComponent.K$src$Ljava_lang_String_$bvh3j6().equalsIgnoreCase(this.getName())) {
            publicProfilesFrameHeaderActionComponent.j(this.getName());
        }
        boolean bl = false;
        for (GuiComponent guiComponent : this.f()) {
            if (guiComponent == this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() || guiComponent == this.wm) continue;
            bl = true;
            break;
        }
        this.wm.Z(!bl);
        this.l$src$V$1mibm4x();
    }

    @Override
    public String getName() {
        return this.wG;
    }

    public T T$src$Lgg_vape_ui_click_component_gui_InteractiveCompo$1wph4d9() {
        return this.wM;
    }

    public AnchoredHudModuleConfigFrame(T t) {
        this.T(AnchoredHudModuleConfigFrame.J.i);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.wM = t;
        this.Y(false);
        this.N(true);
        this.Z(false);
        this.L(false, false);
        AnchoredHudModuleConfigCloseHeaderButton anchoredHudModuleConfigCloseHeaderButton = new AnchoredHudModuleConfigCloseHeaderButton(this, this, "settingdots", this.wG, 0.7);
        anchoredHudModuleConfigCloseHeaderButton.O$src$Lgg_vape_ui_click_component_SquareIconButtonComp$z3cp96().l$src$Ljava_util_List_$7yhdmw().clear();
        anchoredHudModuleConfigCloseHeaderButton.O$src$Lgg_vape_ui_click_component_SquareIconButtonComp$z3cp96().r(new HudModuleFrameOpenConfigClickHandler(this));
        this.Y(anchoredHudModuleConfigCloseHeaderButton);
        this.wm = new SimpleTextLabelComponent("No settings available", 0.75, AnchoredHudModuleConfigFrame.J.h);
        this.wm.Z(false);
        this.wm.Q(false);
        this.h(this.wm, new Object[0]);
    }

    public void G(HudModule hudModule) {
        this.wJ = hudModule;
        this.j(hudModule.getName());
    }

    @Override
    public double L() {
        double d = 0.0;
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l()) continue;
            d += guiComponent.L();
        }
        return d;
    }

    public static void M(boolean bl) {
        wS = bl;
    }

    public void j(String string) {
        this.wG = string;
    }

    static {
        AnchoredHudModuleConfigFrame.M(true);
    }

    public static boolean C$src$Z$w9idiu() {
        return wS;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

