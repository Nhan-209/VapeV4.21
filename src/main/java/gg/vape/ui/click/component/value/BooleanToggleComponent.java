package gg.vape.ui.click.component.value;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.animation.ThemeColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.value.BooleanStateAdapter;
import gg.vape.ui.click.component.value.BooleanToggleComponentClickMouseListener;
import gg.vape.ui.click.component.value.CompactListValueComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.value.BooleanValue;
import gg.vape.value.ConditionalValue;
import gg.vape.value.ListValue;
import gg.vape.value.Value;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class BooleanToggleComponent
extends PanelComponent
implements BooleanStateAdapter<BooleanToggleComponent> {
    private boolean Qa;
    protected double Q0;
    List<String> QO;
    private String QL;
    private final float QK = 4.0f;
    private CompactListValueComponent Qn;
    protected Color QE;
    private float QA = 6.0f;
    private boolean Qw;
    private final ColorAnimation Qv;
    private final BooleanValue QD;
    private final float Qf = 1.0f;
    private final DoubleAnimation QU;
    protected String QR;
    private final ThemeColorAnimation QF;
    private final float Qp = 6.0f;

    @Override
    public double C() {
        return 15.0 + (double)(this.F$src$Ljava_util_List_$1e3p1ge().size() - 1) * this.O(this.Q0).d(this.I$src$Ljava_lang_String_$1ewzbgi());
    }

    public CompactListValueComponent G$src$Lgg_vape_ui_click_component_value_CompactListVal$1o8zcka() {
        return this.Qn;
    }

    public boolean i$src$Z$1d37ezg() {
        return this.QU.I$src$Z$c48gtw();
    }

    public void k$src$V$5mynh8() {
        if (this.QD != null) {
            if (this.QD.L().equals(this.i$src$Z$1d37ezg())) {
                if (!this.QD.b(this.QD.L() == false)) {
                    return;
                }
                this.QD.o(this.QD.L() == false);
            }
            if (this.QD.q$src$Ljava_util_List_$fyau59().size() > 0 && this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != null) {
                this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().l$src$V$1mibm4x();
            }
        }
        this.QF.J();
        this.QU.J();
    }

    private boolean R$src$Z$1dz9xdl() {
        if (!(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa() instanceof ClickGuiMainFrame) || this.QD == null) {
            return false;
        }
        List<Value> list = this.QD.q$src$Ljava_util_List_$fyau59();
        return list.size() == 1 && list.get(0) instanceof ListValue;
    }

    public BooleanToggleComponent(String string) {
        this(string, 0.9);
    }

    @Override
    public void c() {
        Color color;
        super.c();
        if (this.l$src$Z$1e669r3()) {
            this.onDisable();
            this.s$src$V$1uam4mz();
            String string = this.i$src$Z$1d37ezg() ? "ON" : "OFF";
            SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Q0);
            smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + this.A() - 4.0 - smoothFontRenderer.N(string), this.n() + this.L() / 2.0 - smoothFontRenderer.d(string) / 2.0, this.i$src$Z$1d37ezg() ? ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1() : BooleanToggleComponent.J.Z);
            return;
        }
        this.l$src$V$1mibm4x();
        this.QA = 5.0f;
        double d = this.G$src$D$1b2f02a() + this.A() - 10.0 - (double)this.QA;
        double d2 = this.n() + this.L() / 2.0 - 3.0;
        Color color2 = color = this.QF.q() > 0.0 ? this.QF.getInterpolatedColor() : this.Qv.getInterpolatedColor();
        if (this.w$src$Z$e457mb() && this.Qw && this.QF.q() > 0.0) {
            color = ColorUtil.N(color, 30.0);
        }
        this.s$src$V$1uam4mz();
        GuiRenderPrimitives.j(d - 1.0, d2 - 0.5, 12.5, 7.0, color);
        GuiRenderPrimitives.V((float)d + 1.0f + (float)this.QU.getInterpolatedValue().doubleValue(), (float)d2 + 1.0f, 4.0, (float)(0.8 / Vape.INSTANCE.getClientSettings().s()), BooleanToggleComponent.J.i);
        if (this.r$src$Lgg_vape_value_Value_$fdf20y() != null && this.r$src$Lgg_vape_value_Value_$fdf20y() instanceof ConditionalValue && !((ConditionalValue)this.r$src$Lgg_vape_value_Value_$fdf20y()).q$src$Ljava_util_List_$fyau59().isEmpty() && ((ConditionalValue)this.r$src$Lgg_vape_value_Value_$fdf20y()).P() && this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != null) {
            FrameComponent frameComponent = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
            List<Value> list = ((ConditionalValue)this.r$src$Lgg_vape_value_Value_$fdf20y()).q$src$Ljava_util_List_$fyau59();
            GuiComponent guiComponent = null;
            boolean bl = false;
            for (GuiComponent guiComponent2 : frameComponent.f()) {
                if (guiComponent2.equals(this)) {
                    bl = true;
                    continue;
                }
                if (!list.contains(guiComponent2.r$src$Lgg_vape_value_Value_$fdf20y()) || !bl) continue;
                guiComponent = guiComponent2;
                break;
            }
            if (guiComponent != null && !this.R$src$Z$1dz9xdl()) {
                Color color3 = guiComponent.d();
                ImageRenderer.E(color3, (float)(this.G$src$D$1b2f02a() + this.A() / 8.0), (float)(this.n() + this.L() - 2.0), "dropdownnotch", 7.0f, 3.0f, false);
            }
        }
    }

    protected void n$src$V$1tjvir5() {
        if (this.QD != null && !this.QD.L().equals(this.i$src$Z$1d37ezg()) && !this.U$src$Z$1e0xb5o()) {
            this.k$src$V$5mynh8();
        }
    }

    @Override
    public BooleanToggleComponent Y(boolean bl) {
        this.Qa = bl;
        return this;
    }

    public BooleanToggleComponent(String string, double d) {
        this(string, d, null);
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.Qv.J();
        }
    }

    protected void s$src$V$1uam4mz() {
        SmoothFontRenderer smoothFontRenderer = this.O(this.Q0);
        List<String> list = this.F$src$Ljava_util_List_$1e3p1ge();
        double d = smoothFontRenderer.d(this.QR) * (double)list.size();
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        for (String string : list) {
            if (!string.equals(" ")) {
                smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2(), d2, this.QE);
            }
            d2 += d / (double)list.size();
        }
    }

    public BooleanValue M$src$Lgg_vape_value_BooleanValue_$1ruml8g() {
        return this.QD;
    }

    @Override
    public void onEnable() {
        this.Qv.J();
    }

    public BooleanToggleComponent(String string, double d, BooleanValue booleanValue) {
        super(0.0, 0.0);
        this.Qv = new ColorAnimation(0.15, BooleanToggleComponent.J.K, BooleanToggleComponent.J.W);
        this.QF = new ThemeColorAnimation(0.15, BooleanToggleComponent.J.W);
        this.QU = new DoubleAnimation(0.15, 0.0, this.QA - 1.0f);
        this.QE = BooleanToggleComponent.J.Z;
        this.Qw = false;
        this.QL = "";
        this.QO = new ArrayList<String>();
        this.QR = string;
        this.Q0 = d;
        this.QD = booleanValue;
        if (booleanValue != null) {
            this.C(booleanValue);
            this.Z$src$V$1e3oa11();
            this.w(booleanValue.w$src$Ljava_lang_String_$ikqblg());
        }
        this.S(0);
        this.j(new BooleanToggleComponentClickMouseListener(this));
        this.n$src$V$1tjvir5();
        this.Q$src$V$11xzx98();
    }

    public String I$src$Ljava_lang_String_$1ewzbgi() {
        return this.QR;
    }

    @Override
    public void u() {
        this.n$src$V$1tjvir5();
    }


    public void G(boolean bl) {
        this.Qw = bl;
    }

    private List<String> F$src$Ljava_util_List_$1e3p1ge() {
        String string = this.I$src$Ljava_lang_String_$1ewzbgi();
        if (string.equals(this.QL)) {
            return this.QO;
        }
        SmoothFontRenderer smoothFontRenderer = this.O(this.Q0);
        String string2 = Vape.INSTANCE.getFontSelector().W().s(this.I$src$Ljava_lang_String_$1ewzbgi());
        String[] stringArray = string2.split(" ");
        double d = this.A() - 20.0;
        ArrayList<String> arrayList = new ArrayList<String>();
        double d2 = 0.0;
        String string3 = "";
        for (String string4 : stringArray) {
            double d3 = d2 + smoothFontRenderer.N(string4 + " ");
            if (d3 > d) {
                d2 = 0.0;
                arrayList.add(string3);
                string3 = string4 + " ";
                continue;
            }
            d2 = d3;
            string3 = string3 + string4 + " ";
        }
        arrayList.add(string3);
        this.QO = arrayList;
        this.QL = string;
        return arrayList;
    }

    @Override
    public boolean l$src$Z$1e669r3() {
        return this.Qa;
    }

    public void N() {
        if (this.Qa) {
            return;
        }
        this.k$src$V$5mynh8();
    }

    public void Q$src$V$11xzx98() {
        if (this.QD != null) {
            if (this.QD.L().booleanValue()) {
                this.QF.C();
                this.QU.C();
            } else {
                this.QF.O();
                this.QU.O();
            }
        }
    }

    public void h(boolean bl) {
        if (this.QD != null) {
            this.QD.o(bl);
            if (this.QD.q$src$Ljava_util_List_$fyau59().size() > 0 && this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != null) {
                this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().l$src$V$1mibm4x();
            }
        }
        if (bl) {
            this.QF.c();
            this.QU.c();
        } else {
            this.QF.Z();
            this.QU.Z();
        }
    }

    public DoubleAnimation n$src$Lgg_vape_ui_click_animation_DoubleAnimation_$12lr9ge() {
        return this.QU;
    }

    public void Z$src$V$1e3oa11() {
        if (this.QD.G() != null) {
            this.Qn = new CompactListValueComponent(this.QD.G());
            this.h(this.Qn, "alignright, offsetX 19, offsetY 3");
            this.l$src$V$1mibm4x();
        }
    }

    public boolean U$src$Z$1e0xb5o() {
        return !this.QU.getInterpolatedValue().equals(this.QU.getStartValue()) && !this.QU.getInterpolatedValue().equals(this.QU.getEndValue());
    }

    @Override
    public double x() {
        return 110.0;
    }

    public BooleanToggleComponent(BooleanValue booleanValue) {
        this(booleanValue != null ? booleanValue.o() : null, 0.9, booleanValue);
    }
}

