package gg.vape.ui.click.component;

import gg.vape.Vape;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.TooltipTextSegment;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;

public class ToolTips
extends SimpleTextLabelComponent {
    private GuiComponent qS;
    private TooltipTextSegment o;
    private boolean b = false;


    public ToolTips(GuiComponent guiComponent, String string, double d, Color color, boolean bl) {
        super(string, d, color, bl);
        this.qS = guiComponent;
    }

    public GuiComponent z() {
        return this.qS;
    }

    @Override
    public void H() {
        double d;
        String[] stringArray;
        if (!this.qS.w$src$Z$e457mb() || !this.qS.t() || !this.qS.V$src$Z$1xhop3l() || this.qS.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa() != null && !this.qS.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().i$src$Z$1f7f2w6()) {
            this.qS.m$src$V$1x64v7f();
            return;
        }
        SmoothFontRenderer smoothFontRenderer = this.O(this.G);
        double d2 = this.n() + 2.0;
        double d3 = 0.0;
        double d4 = 0.0;
        if (this.o != null) {
            d3 = this.o.Y() + 3.0;
            d4 = this.o.B();
        }
        String string = Vape.INSTANCE.getFontSelector().W().s(this.O);
        for (String string2 : stringArray = string.split("\n")) {
            double d5 = smoothFontRenderer.N(string2);
            if (d5 > d4) {
                d4 = d5;
            }
            d3 += smoothFontRenderer.d(string2) + 1.0;
        }
        double d6 = this.G$src$D$1b2f02a() + 8.0;
        if (d6 + d4 + 8.0 > (double)Minecraft.J() / Vape.INSTANCE.getClientSettings().s() / 2.0) {
            d6 = d6 - d4 - 6.0 - 12.0;
        }
        if ((d = this.n()) + d3 + 2.5 + 2.0 > (double)Minecraft.h() / Vape.INSTANCE.getClientSettings().s() / 2.0) {
            d = d - d3 - 2.5;
        }
        GuiRenderPrimitives.I(d6, d, d4 + 6.0, d3 + 2.5, ToolTips.J.r, true, 2.0f, 1.0f, 6.0f, ToolTips.J.i);
        GuiRenderPrimitives.P(d6, d, d4 + 6.0, d3 + 2.5, new Color(35, 35, 35), 2.0f, 0.75f, 1.0f);
        if (this.o != null) {
            this.o.o(this.G$src$D$1b2f02a() + 11.0, d2);
            d += this.o.Y() + 2.0;
        }
        d += 2.0;
        for (String string3 : stringArray) {
            smoothFontRenderer.d(string3, d6 + 3.0, d, ToolTips.J.Z);
            d += smoothFontRenderer.d(string3) + 1.0;
        }
        this.b = false;
    }

    public void s(boolean bl) {
        this.b = bl;
    }

    public ToolTips(GuiComponent guiComponent, String string, double d, Color color, boolean bl, String string2, double d2, Color color2, boolean bl2) {
        super(string, d, color, bl);
        this.qS = guiComponent;
        this.o = new TooltipTextSegment(this, string2, d2, color2, bl2);
    }

    public ToolTips(GuiComponent guiComponent, String string) {
        super(string, 0.75);
        this.qS = guiComponent;
    }

    public boolean k() {
        return this.b;
    }
}

