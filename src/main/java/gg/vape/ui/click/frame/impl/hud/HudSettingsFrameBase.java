package gg.vape.ui.click.frame.impl.hud;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.AnimatedIconButtonComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.CollapsibleFrame;
import gg.vape.ui.click.frame.SettingsFrameHeaderComponent;
import gg.vape.ui.click.frame.impl.hud.AnchoredHudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.value.Value;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class HudSettingsFrameBase
extends HudModuleFrameBase
implements CollapsibleFrame {
    private static final String qb = "wrap";
    private boolean TJ = true;
    List<GuiComponent> Tj = new ArrayList<GuiComponent>();
    private boolean T3 = false;

    @Override
    public boolean q() {
        return this.TJ;
    }

    @Override
    public double v$src$D$1l3l1d1() {
        return super.v$src$D$1l3l1d1() - 1.0;
    }

    private void wr() {
        boolean bl = this.N$src$Z$1ad1ggw() && !this.L$src$Z$1v7qi9z();
        this.P(bl);
        this.W(bl);
    }

    public List<GuiComponent> x$src$Ljava_util_List_$vc8bfc() {
        return this.Tj;
    }

    @Override
    protected void k() {
        AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent> anchoredHudModuleConfigFrame = this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s();
        this.w8();
        anchoredHudModuleConfigFrame.S();
        for (GuiComponent guiComponent : this.x$src$Ljava_util_List_$vc8bfc()) {
            if (guiComponent.r$src$Lgg_vape_value_Value_$fdf20y() == null) continue;
            this.I(guiComponent);
            guiComponent.Z(true);
            anchoredHudModuleConfigFrame.h(guiComponent, new Object[0]);
        }
        anchoredHudModuleConfigFrame.Z(true);
        anchoredHudModuleConfigFrame.t(170.0);
        anchoredHudModuleConfigFrame.H(true);
        if (!ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().Y().contains(anchoredHudModuleConfigFrame)) {
            ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().q(anchoredHudModuleConfigFrame);
        }
    }

    @Override
    public void w() {
        this.TJ = !this.TJ;
        this.w2();
    }

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void M(GuiComponent ... guiComponentArray) {
        Collections.addAll(this.x$src$Ljava_util_List_$vc8bfc(), guiComponentArray);
        this.H(guiComponentArray);
        this.w2();
    }

    @Override
    public double U$src$D$muzvq3() {
        double d = super.U$src$D$muzvq3();
        if (this.q()) {
            return d;
        }
        double d2 = 0.0;
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l() || this.Tj.contains(guiComponent) || guiComponent.equals(this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc())) continue;
            d2 += guiComponent.L();
        }
        return d - d2;
    }

    public AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent> j$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$1mjj2p3() {
        return this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s();
    }

    public void Q$src$V$1vahh5c() {
    }

    @Override
    public RectData Q() {
        if (this.N$src$Z$1ad1ggw()) {
            return this.i$src$Lfunc_skidline_RectData_$1ykrzel();
        }
        return super.Q();
    }

    private void e() {
        if (!this.T3 && this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null) {
            this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().Z(false);
            this.T3 = true;
            this.w2();
        }
    }

    public HudSettingsFrameBase(String string, String string2) {
        super(string2);
        this.T(HudSettingsFrameBase.J.i);
        this.K(300.0);
        this.S(100.0);
        this.Z(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(qb);
        this.Y(new SettingsFrameHeaderComponent(this, string, string2));
        this.W();
        this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s().j(this.getName());
    }

    public boolean P$src$Z$1v9xonf() {
        return this.L$src$Z$1v7qi9z();
    }

    protected boolean L$src$Z$1v7qi9z() {
        return this.N$src$Z$1ad1ggw() && Vape.INSTANCE.getPublicProfileSettings().P.o();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.N$src$Z$1ad1ggw()) {
            this.F(guiMouseEvent);
            return;
        }
        super.g(guiMouseEvent);
    }

    private void wx() {
        this.s$src$V$1axdubt();
    }

    private void wP() {
        if (this.T3 && this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null) {
            this.w8();
            this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().Z(true);
            this.T3 = false;
            this.i(false);
            this.M$src$Lgg_vape_ui_click_component_AnimatedIconButtonCo$12x9cix().Z(false);
            this.d$src$Lgg_vape_ui_click_component_AnimatedIconButtonCo$69zuia().Z(false);
            if (this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s().V$src$Z$1xhop3l()) {
                this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s().Z(false);
            }
            this.w2();
        }
    }

    @Override
    public double L() {
        if (this.N$src$Z$1ad1ggw()) {
            return Math.max(26.0, super.L());
        }
        return this.q() ? this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() : super.L();
    }

    @Override
    public float r$src$F$35g3yx() {
        if (!this.N$src$Z$1ad1ggw()) {
            return 1.0f;
        }
        return super.r$src$F$35g3yx();
    }

    protected void o$src$V$7f79jo() {
        if (this.p$src$Z$1avqgn6()) {
            return;
        }
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.b$src$Ljava_awt_Color_$t24dz2(), 1.5f, 1.0f, 1.0f);
    }

    private void w2() {
        for (GuiComponent guiComponent : this.x$src$Ljava_util_List_$vc8bfc()) {
            boolean bl;
            boolean bl2 = bl = !this.TJ;
            if (this.N$src$Z$1ad1ggw() && guiComponent.r$src$Lgg_vape_value_Value_$fdf20y() != null) {
                bl = false;
            }
            guiComponent.Z(bl);
            guiComponent.T(HudSettingsFrameBase.J.r);
            Value value = guiComponent.r$src$Lgg_vape_value_Value_$fdf20y();
            if (value == null || value.getParent() == null) continue;
            Color color = value.q$src$Ljava_awt_Color_$1ibcet6() == null ? HudSettingsFrameBase.J.r.darker() : value.q$src$Ljava_awt_Color_$1ibcet6();
            guiComponent.T(color);
        }
        this.l$src$V$1mibm4x();
    }

    @Override
    public Color R(Color color, int n) {
        if (!this.N$src$Z$1ad1ggw()) {
            return color;
        }
        return super.R(color, n);
    }

    @Override
    public void H(boolean bl) {
        this.wr();
        super.H(bl);
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        if (this.N$src$Z$1ad1ggw()) {
            this.I(guiMouseEvent);
            return;
        }
        super.D(guiMouseEvent);
    }

    @Override
    protected void h() {
        this.w8();
        this.i(false);
        this.Z(false);
    }

    @Override
    public boolean Z$src$Z$16e8vsp() {
        if (this.N$src$Z$1ad1ggw()) {
            return false;
        }
        return super.Z$src$Z$16e8vsp();
    }

    @Override
    public double i$src$D$uqmc0b() {
        return super.i$src$D$uqmc0b() + 1.0;
    }

    @Override
    public void H() {
        this.wr();
        if (this.N$src$Z$1ad1ggw()) {
            this.e();
            this.o$src$V$7f79jo();
            this.wx();
            this.p$src$V$1avqgjq();
            if (this.w$src$Z$e457mb() || this.p$src$Z$1avqgn6() || this.r$src$Z$1awu1tw()) {
                this.Z$src$V$1ajmzhs();
            }
        } else {
            this.wP();
        }
        super.H();
    }

    private void w8() {
        AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent> anchoredHudModuleConfigFrame = this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s();
        for (GuiComponent guiComponent : this.x$src$Ljava_util_List_$vc8bfc()) {
            if (guiComponent.r$src$Lgg_vape_value_Value_$fdf20y() == null || guiComponent.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != anchoredHudModuleConfigFrame) continue;
            anchoredHudModuleConfigFrame.I(guiComponent);
            this.h(guiComponent, new Object[0]);
        }
        this.w2();
    }

    @Override
    public void N$src$V$bhucvl() {
        this.w8();
        super.N$src$V$bhucvl();
    }
}

