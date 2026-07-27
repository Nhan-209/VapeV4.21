package gg.vape.ui.click.frame.impl;

import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrame;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeaderActionClickHandler;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeaderClearModuleSelectionClickHandler;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeaderCollapseToggleClickHandler;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeaderNextModuleClickHandler;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeaderSearchIconClickHandler;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeaderSearchLabelClickHandler;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RenderUtils;

public class ModuleCategoryFrameHeader
extends FrameHeaderComponent {
    private TextLabel K = new TextLabel("", 0.75);
    private int ZG = 0;
    private IconButtonComponent Q;
    private TextLabel G;
    private String R;
    private String O;
    private IconButtonComponent v;
    private IconButtonComponent o;
    private TextLabel I = new TextLabel("", 0.75);
    private ModuleCategoryFrame i;

    @Override
    public void I() {
    }

    @Override
    public void F() {
    }

    public void y(int n) {
        this.ZG = n;
    }

    public static ModuleCategoryFrame e(ModuleCategoryFrameHeader moduleCategoryFrameHeader) {
        return moduleCategoryFrameHeader.i;
    }

    public void h() {
        if (ClientSettings.Y) {
            ClientSettings.Y = false;
            ClientSettings.M$src$V$1giazqf();
        }
        if (this.i.N$src$Lgg_vape_module_Mod_$1rbaf6a() != null) {
            this.i.G(null);
        }
        ClientSettings.l(this.i.G$src$Lgg_vape_module_Category_$qyt4o7());
        for (GuiComponent guiComponent : this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().f()) {
            if (guiComponent instanceof FrameHeaderComponent) continue;
            if (!(guiComponent instanceof ModuleComponent)) {
                guiComponent.Z(false);
                continue;
            }
            guiComponent.Z(!this.i.q() && ((ModuleComponent)guiComponent).N$src$Lgg_vape_module_Mod_$rb0ew8().O());
        }
        this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().l$src$V$1mibm4x();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }


    public ModuleCategoryFrameHeader(ModuleCategoryFrame moduleCategoryFrame, String string, String string2) {
        super(moduleCategoryFrame);
        this.G = new TextLabel("Edit");
        this.o = new IconButtonComponent("newhide", 0.7);
        this.v = new IconButtonComponent("moduleback");
        this.Q = new IconButtonComponent("upcollapse", 0.3);
        this.i = moduleCategoryFrame;
        this.O = string;
        this.R = string2;
        this.o.r(new ModuleCategoryFrameHeaderSearchIconClickHandler(this));
        this.o.w("Edit hidden modules");
        this.v.r(new ModuleCategoryFrameHeaderClearModuleSelectionClickHandler(this));
        this.G.r(new ModuleCategoryFrameHeaderSearchLabelClickHandler(this));
        this.Q.r(new ModuleCategoryFrameHeaderCollapseToggleClickHandler(this, moduleCategoryFrame));
        this.H(this.G, this.v, this.o, this.Q, this.I, this.K);
    }

    @Override
    public void u() {
        if (this.i.N$src$Lgg_vape_module_Mod_$1rbaf6a() != null && ClientSettings.Y) {
            this.i.G(null);
        }
    }

    public void s(Mod mod) {
        if (mod == null) {
            this.K.d("");
            return;
        }
        this.K.d("< " + mod.getName().toUpperCase().substring(0, 3));
        this.K.s(new ModuleCategoryFrameHeaderActionClickHandler(this, mod));
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        double d = smoothFontRenderer.d(this.R);
        double d2 = this.n() + this.L() / 2.0 - d / 2.0 + 1.0;
        float f = (float)ImageRenderer.m(this.O) / 3.5f;
        float f2 = (float)ImageRenderer.j(this.O) / 3.5f;
        double d3 = (float)this.G$src$D$1b2f02a() + 6.0f;
        double d4 = this.n() + this.L() / 2.0 - (double)(f2 / 2.0f) + 1.0;
        smoothFontRenderer.d(this.R, d3 + (double)f + 4.0, d2, ModuleCategoryFrameHeader.J.A);
        if (this.i.N$src$Lgg_vape_module_Mod_$1rbaf6a() != null && !ClientSettings.Y) {
            this.o.Z(false);
            this.G.Z(false);
            this.v.G(ModuleCategoryFrameHeader.J.A);
            this.v.Z(true);
            this.v.K(this.G$src$D$1b2f02a() + 5.0);
            this.v.S(this.n());
            this.v.Y(this.L());
            this.Q.Z(false);
            this.K.Z(true);
            this.I.Z(true);
            this.I.K(this.G$src$D$1b2f02a() + this.A() - 5.0 - this.I.A());
            this.I.S(this.n());
            this.I.Y(this.L());
            this.K.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - this.I.A() - this.K.A());
            this.K.S(this.n());
            this.K.Y(this.L());
        } else {
            this.K.Z(false);
            this.I.Z(false);
            this.Q.Z(true);
            ImageRenderer.E(ModuleCategoryFrameHeader.J.A, (float)d3, (float)d4, this.O, f, f2, false);
            this.v.Z(false);
            if (ClientSettings.Y) {
                this.o.Z(false);
                this.G.y(0.75);
                this.G.Z(true);
                this.G.d("Done");
                this.G.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - 16.0 - smoothFontRenderer.N(this.G.L$src$Ljava_lang_String_$1ncdwqb()) / 2.0);
                this.G.S(this.n());
                this.G.Y(this.L());
            } else {
                boolean bl = this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().Q().Z(RenderUtils.h());
                if (((ModuleCategoryFrameHeader)this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc()).g$src$I$pd0er5() > 0) {
                    double d5 = smoothFontRenderer.N("" + this.ZG);
                    if (bl) {
                        smoothFontRenderer.d("" + this.ZG, this.G$src$D$1b2f02a() + this.A() - 5.0 - 16.0 - 3.0 - d5, d2, ModuleCategoryFrameHeader.J.Z);
                    }
                    this.o.H("newhide");
                } else {
                    this.o.H("newedit");
                }
                this.o.Z(bl);
                this.G.Z(false);
                this.o.G(ClientSettings.Y ? ModuleCategoryFrameHeader.J.f : null);
                this.o.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - 16.0);
                this.o.S(this.n() + 1.0);
                this.o.Y(this.L());
            }
            this.Q.K(this.G$src$D$1b2f02a() + this.A() - 7.5 - 8.0);
            this.Q.S(this.n());
            this.Q.Y(this.L());
            this.Q.H(this.i.q() ? "downexpand" : "upcollapse");
        }
    }

    public void S(Mod mod) {
        if (mod == null) {
            this.I.d("");
            return;
        }
        this.I.d(mod.getName().toUpperCase().substring(0, 3) + " >");
        this.I.s(new ModuleCategoryFrameHeaderNextModuleClickHandler(this, mod));
    }

    public int g$src$I$pd0er5() {
        return this.ZG;
    }
}

