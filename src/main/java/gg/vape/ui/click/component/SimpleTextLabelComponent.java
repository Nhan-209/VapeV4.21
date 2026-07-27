package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public class SimpleTextLabelComponent
extends GuiComponent {
    private float I;
    @Nullable
    private Supplier<String> a;
    private float i;
    private Color Q;
    private boolean v;
    protected String O;
    private static String[] K;
    protected double G;
    private int R;

    @Override
    public void F() {
    }

    public String c$src$Ljava_lang_String_$1q00otb() {
        return this.O;
    }

    public SimpleTextLabelComponent(String string) {
        this(string, 0.75);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }


    @Nullable
    public Supplier<String> A$src$Ljava_util_function_Supplier_$ylcwqv() {
        return this.a;
    }

    public double y$src$D$idacv3() {
        return this.O(this.G).d(this.O);
    }

    public void c(int n) {
        this.R = n;
    }

    public void z(float f) {
        this.i = f;
    }

    public void g(float f) {
        this.I = f;
    }

    public void l(boolean bl) {
        this.v = bl;
    }

    public double h() {
        return this.O(this.G).N(this.O);
    }

    @Override
    public double C() {
        return this.O(this.G).d("A") + (double)this.R;
    }

    @Override
    public void u() {
    }

    public void G(String string) {
        this.O = string;
    }

    @Override
    public double x() {
        return this.O(this.G).N(this.O);
    }

    public void f(@Nullable Supplier<String> supplier) {
        this.a = supplier;
        if (supplier != null) {
            this.G(supplier.get());
        }
    }

    public void T$src$V$1orl066(Color color) {
        this.Q = color;
    }

    public double g$src$D$i3e26l() {
        return this.G;
    }

    @Override
    public void I() {
    }

    public static String[] S$src$ALjava_lang_String_$1c8hx4() {
        return K;
    }

    static {
        SimpleTextLabelComponent.G((String[])null);
    }

    public SimpleTextLabelComponent(String string, double d) {
        this.Q = SimpleTextLabelComponent.J.h;
        this.I = 5.0f;
        this.i = 0.0f;
        this.R = 0;
        this.O = string;
        this.G = d;
    }

    public static void G(String[] stringArray) {
        K = stringArray;
    }

    public boolean e() {
        return this.v;
    }

    public SimpleTextLabelComponent(String string, double d, Color color, boolean bl) {
        this(string, d, color);
        this.v = bl;
    }

    @Override
    public void H() {
        Supplier<String> supplier = this.a;
        if (supplier != null) {
            this.G(supplier.get());
        }
        SmoothFontRenderer smoothFontRenderer = this.v ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.G) : this.O(this.G);
        double d = smoothFontRenderer.d("A");
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        smoothFontRenderer.d(this.O, this.G$src$D$1b2f02a() + (double)this.I, d2 + (double)this.i, this.Q);
    }

    public Color G$src$Ljava_awt_Color_$11jgid7() {
        return this.Q;
    }

    public void i(double d) {
        this.G = d;
    }

    public SimpleTextLabelComponent(String string, double d, Color color) {
        this(string, d);
        this.T$src$V$1orl066(color);
    }
}
