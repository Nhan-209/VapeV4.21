package gg.vape.ui.click.component.value;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.value.StringMapEntryComponent;
import gg.vape.ui.click.component.value.StringMapEntryListFrame;
import gg.vape.ui.click.component.value.StringMapEntryRemoveHandler;
import gg.vape.ui.click.component.value.StringMapKeyInputComponent;
import gg.vape.ui.click.component.value.StringMapValueInputComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.value.StringMapValue;
import java.awt.Color;
import java.util.Map;

public class StringMapValueComponent
extends GuiComponent {
    private boolean Q;
    private boolean G;
    private final Frame b;
    private final TextInputComponentBase R;
    private final StringMapValue a;
    private final SimpleTextLabelComponent O;
    private final TextButton o;
    private final TextInputComponentBase i;

    @Override
    public void F() {
    }

    static TextInputComponentBase r(StringMapValueComponent stringMapValueComponent) {
        return stringMapValueComponent.i;
    }

    @Override
    public void J() {
        super.J();
    }

    static void P(StringMapValueComponent stringMapValueComponent) {
        stringMapValueComponent.e();
    }

    @Override
    public double x() {
        return 50.0;
    }

    private void e() {
        Map<String, String> map = this.a.K();
        this.b.t$src$V$zbu1jn();
        for (String string : map.keySet()) {
            String string2 = map.get(string);
            StringMapEntryComponent stringMapEntryComponent = new StringMapEntryComponent(string, string2);
            stringMapEntryComponent.N(new StringMapEntryRemoveHandler(this, stringMapEntryComponent));
            this.b.H(stringMapEntryComponent);
        }
    }

    @Override
    public double C() {
        return 62 + Math.min(this.b.f().size(), 4) * 19;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void I() {
    }

    static StringMapValue L(StringMapValueComponent stringMapValueComponent) {
        return stringMapValueComponent.a;
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 2.0, this.n() + 2.0, this.A() - 4.0, this.L() - 4.0, StringMapValueComponent.J.r);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + this.A() / 2.0, this.n() + 28.0, 5.0, 1.0f, StringMapValueComponent.J.l);
        this.O.K(this.G$src$D$1b2f02a() + 2.0);
        this.O.S(this.n() + 2.0);
        this.R.d(false);
        this.R.r(false);
        this.R.K(this.G$src$D$1b2f02a());
        this.R.S(this.n() + 13.0);
        this.i.d(false);
        this.i.r(false);
        this.i.K(this.G$src$D$1b2f02a());
        this.i.S(this.n() + 28.0);
        this.o.T(StringMapValueComponent.J.r);
        this.o.o(28.0);
        this.o.Y(12.0);
        this.o.K(this.G$src$D$1b2f02a() + this.A() - 33.0);
        this.o.S(this.n() + 46.0);
        this.b.K(this.G$src$D$1b2f02a() + 3.0);
        this.b.S(this.n() + 60.0);
        this.b.o(this.A() - 5.0);
        this.b.Y(68.0);
        this.b.t(68.0);
        this.b.d(false);
        this.b.T(new Color(255, 255, 255, 0));
        this.b.P(true);
        this.b.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.b.N(true);
        this.b.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.b.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().I(false);
        this.b.l$src$V$1mibm4x();
        if (this.G) {
            this.e();
            this.G = false;
        }
    }

    public StringMapValueComponent(StringMapValue stringMapValue) {
        this.a = stringMapValue;
        this.O = new SimpleTextLabelComponent(stringMapValue.getName());
        this.R = new StringMapKeyInputComponent(this, stringMapValue.A());
        this.i = new StringMapValueInputComponent(this, stringMapValue.x());
        this.o = new TextButton("ADD", StringMapValueComponent.J.l);
        this.o.s(() -> {
            stringMapValue.E(this.R.i$src$Ljava_lang_String_$1n2xf3k(), this.i.i$src$Ljava_lang_String_$1n2xf3k());
            this.e();
            this.R.k("");
            this.i.k("");
        });
        this.b = new StringMapEntryListFrame(this);
        this.H(this.O, this.R, this.i, this.o, this.b);
        this.G = true;
    }

    @Override
    public void u() {
    }

}
