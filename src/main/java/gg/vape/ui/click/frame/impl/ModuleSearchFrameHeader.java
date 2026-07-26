package gg.vape.ui.click.frame.impl;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.HalfScaleIconButtonComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.input.ModuleSearchInputComponent;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.impl.ModuleSearchFrame;
import gg.vape.ui.click.frame.impl.ModuleSearchFrameHeaderRefreshListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleListPanel;
import gg.vape.ui.click.frame.impl.hud.HudModuleOverviewListFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.value.ModuleNameSuggestionProvider;
import java.awt.Color;
import java.util.List;

public class ModuleSearchFrameHeader
extends FrameHeaderComponent {
    private final ModuleSearchFrame I;
    private final IconButtonComponent v;
    private final SquareIconButtonComponent R;
    private final IconButtonComponent G = new IconButtonComponent("newsearch", 0.9);
    private final ModuleSearchInputComponent K;

    public void K$src$V$10w6uwu() {
        this.I.G(null);
        ClientSettings.g(ModuleSearchFrame.class).D(this.K.i$src$Ljava_lang_String_$1n2xf3k());
    }

    @Override
    public double C() {
        return 16.0;
    }

    public void l$src$V$11ec2hr() {
        List<GuiComponent> list = ClientSettings.g(ModuleSearchFrame.class).f();
        for (GuiComponent guiComponent : list) {
            if (!(guiComponent instanceof ModuleComponent)) continue;
            ModuleComponent moduleComponent = (ModuleComponent)guiComponent;
            moduleComponent.l$src$V$mb5y86();
        }
    }

    @Override
    public void I() {
    }

    private void lambda$new$1() {
        this.s();
    }

    @Override
    public void Y(double d) {
        super.Y(d);
    }

    private void lambda$new$0() {
        ClientSettings.fT = this.K;
    }

    @Override
    public void u() {
        this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().l$src$V$1mibm4x();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public IconButtonComponent G$src$Lgg_vape_ui_click_component_IconButtonComponent_$1pnwa51() {
        return this.v;
    }

    public ModuleSearchFrameHeader(ModuleSearchFrame moduleSearchFrame) {
        super(moduleSearchFrame);
        this.v = new HalfScaleIconButtonComponent("legit_switch");
        this.R = new SquareIconButtonComponent("newclose");
        this.K = new ModuleSearchInputComponent(this);
        this.I = moduleSearchFrame;
        this.o(true);
        this.G.r(this::lambda$new$0);
        this.R.r(this::lambda$new$1);
        this.K.s(new ModuleSearchFrameHeaderRefreshListener(this));
        this.v.r(this::lambda$new$2);
        ModuleNameSuggestionProvider moduleNameSuggestionProvider = new ModuleNameSuggestionProvider(true);
        moduleNameSuggestionProvider.setComparator(null);
        this.K.E(moduleNameSuggestionProvider);
        this.H(this.G, this.R, this.K, this.v);
    }

    public void s() {
        this.l$src$V$11ec2hr();
        this.K.k("");
        this.K$src$V$10w6uwu();
    }

    @Override
    public void F() {
    }

    @Override
    public void H() {
        this.I.p();
        boolean bl = ((ModeSelection)ClientSettings.fW.fz.K()).equals(ClientSettings.fW.c);
        if (bl) {
            if (ClientSettings.fW.O.L().booleanValue()) {
                this.v.Z(true);
                this.v.K(this.G$src$D$1b2f02a() + 3.0);
                this.v.S(this.n() + 1.0);
                this.v.o(18.0);
                this.v.Y(this.L());
                this.v.W(true);
                this.v.d(true);
                this.v.T(Color.RED);
                if (this.v.w$src$Z$e457mb()) {
                    this.v.G(J.z().brighter());
                } else {
                    this.v.G(J.z());
                }
            } else {
                this.v.Z(false);
            }
            this.K.W(true);
            double d = this.G$src$D$1b2f02a() + 6.0;
            if (this.v.V$src$Z$1xhop3l()) {
                d = this.v.G$src$D$1b2f02a() + this.v.A() + 4.0;
                GuiRenderPrimitives.d(this.v.G$src$D$1b2f02a() + this.v.A() + 1.0, this.n() + 6.0, 6.0, 0.75f, new Color(100, 100, 100, 255));
            }
            this.K.K(d);
            this.K.S(this.n());
            this.K.o(this.v.V$src$Z$1xhop3l() ? this.A() - this.v.A() - this.R.A() - 4.0 : this.A() - this.R.A() - 3.0);
            this.K.Y(this.L());
            if (!this.K.i$src$Ljava_lang_String_$1n2xf3k().isEmpty()) {
                this.G.Z(false);
                this.R.Z(true);
                this.R.K(this.G$src$D$1b2f02a() + this.A() - 13.0);
                this.R.S(this.n() + 1.0);
                this.R.o(10.0);
                this.R.Y(this.L());
            } else {
                this.R.Z(false);
                this.G.Z(true);
                this.G.K(this.G$src$D$1b2f02a() + this.A() - 14.0);
                this.G.S(this.n() + 0.5);
                this.G.o(10.0);
                this.G.Y(this.L());
            }
            this.K.Z(true);
            this.o(110.0);
            this.q(110.0);
            this.P(true);
            return;
        }
        if (ClientSettings.fW.O.L().booleanValue()) {
            this.v.Z(true);
            this.v.K(this.G$src$D$1b2f02a() + 3.0);
            this.v.S(this.n() + 1.0);
            this.v.o(18.0);
            this.v.Y(this.L());
            this.v.W(true);
            this.v.d(true);
            this.v.T(Color.RED);
            if (this.v.w$src$Z$e457mb()) {
                this.v.G(J.z().brighter());
            } else {
                this.v.G(J.z());
            }
        } else {
            this.v.Z(false);
        }
        this.K.W(true);
        double d = this.G$src$D$1b2f02a() + 6.0;
        if (this.v.V$src$Z$1xhop3l()) {
            // empty if block
        }
        this.K.K(d);
        this.K.S(this.n());
        this.K.o(this.v.V$src$Z$1xhop3l() ? this.A() - this.v.A() - this.R.A() - 4.0 : this.A() - this.R.A() - 3.0);
        this.K.Y(this.L());
        if (!this.K.i$src$Ljava_lang_String_$1n2xf3k().isEmpty()) {
            this.G.Z(false);
            this.R.Z(true);
            this.R.K(this.G$src$D$1b2f02a() + this.A() - 13.0);
            this.R.S(this.n() + 1.0);
            this.R.o(10.0);
            this.R.Y(this.L());
        } else {
            this.R.Z(false);
            this.G.Z(true);
            this.G.K(this.G$src$D$1b2f02a() + this.A() - 14.0);
            this.G.S(this.n() + 0.5);
            this.G.o(10.0);
            this.G.Y(this.L());
        }
        boolean bl2 = this.K.V$src$Z$1xhop3l();
        this.K.Z(false);
        this.G.Z(false);
        if (bl2) {
            this.s();
        }
        this.o(22.0);
        this.q(22.0);
        this.P(true);
    }

    public ModuleSearchInputComponent A$src$Lgg_vape_ui_click_component_input_ModuleSearchIn$1efzz7n() {
        return this.K;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    private void lambda$new$2() {
        this.K.k("");
        this.K$src$V$10w6uwu();
        if (HudModuleSelectorFrame.W_) {
            ClientSettings.g(HudModuleOverviewListFrame.class).w$src$V$1pyk8v9();
        } else {
            ClientSettings.g(HudModuleListPanel.class).N$src$V$wrn2a4();
        }
        ClientSettings.fW.I(ClientSettings.t);
    }
}

