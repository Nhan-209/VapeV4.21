package gg.vape.ui.click.component;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.IconShape;
import gg.vape.ui.click.component.ShapeIconComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public class FriendModuleInteractiveComponent
extends InteractiveComponent {
    private Color Mw;
    private final ColorAnimation I;
    private static final double ML = 0.65;
    private Color Mo;
    private Color K;
    private final double MO;
    private final SimpleTextLabelComponent M8;
    private static final double MC = 8.0;
    private static final float Q = 3.0f;
    @Nullable
    private final Supplier<Integer> MX;
    private static final double b = 3.0;
    private Color MQ;
    private static final double M1 = 0.75;
    private static final String cb = "selectedSupplier";
    private Color MV;
    private final ColorAnimation MR;
    private static final double Mb = 18.0;
    private final IconGlyphComponent Ms;
    private static final double Mu = 6.0;
    private static final double MD = 6.0;
    private Color ME;
    private final Supplier<Boolean> MI;
    private static final double Mg = 6.0;
    private Color MW;
    private final ShapeIconComponent MT;
    private final IconGlyphComponent Mm;
    private Color v;

    public IconGlyphComponent h() {
        return this.Ms;
    }

    public SimpleTextLabelComponent J$src$Lgg_vape_ui_click_component_SimpleTextLabelCompo$hscme() {
        return this.M8;
    }

    public FriendModuleInteractiveComponent(String string, @Nullable String string2, Supplier<Boolean> supplier, @Nullable Supplier<Integer> supplier2, @Nullable String string3) {
        this.Mo = this.Mw = new Color(115, 113, 115);
        this.MQ = this.MV = new Color(209, 209, 209);
        this.ME = this.MW = Color.WHITE;
        this.K = Color.WHITE;
        this.v = Color.WHITE;
        this.getClass();
        this.MR = new ColorAnimation(0.15, FriendModuleInteractiveComponent.J.t, FriendModuleInteractiveComponent.J.E);
        this.getClass();
        this.I = new ColorAnimation(0.15, new Color(34, 33, 34, 0), new Color(34, 33, 34));
        this.MI = Objects.requireNonNull(supplier, cb);
        this.MX = supplier2;
        this.Y(18.0);
        this.d(false);
        this.o(true);
        this.Mm = new IconGlyphComponent(string2 != null ? string2 : "", 6.0f, 6.0f, this.Mw);
        if (string2 != null) {
            this.Mm.r(true);
            this.H(this.Mm);
        } else {
            this.Mm.Z(false);
        }
        this.M8 = new SimpleTextLabelComponent(string, 0.75, this.Mw);
        this.M8.g(0.0f);
        this.M8.z(0.0f);
        this.M8.c(0);
        this.H(this.M8);
        this.MT = new ShapeIconComponent(IconShape.CIRCLE, "0", 8.0, 0.0, 0.0, 3.0f, FriendModuleInteractiveComponent.J.d, Color.WHITE, 0.65);
        if (supplier2 != null) {
            this.MT.x(supplier2);
            this.MT.Z(false);
            this.H(this.MT);
        } else {
            this.MT.Z(false);
        }
        this.Ms = new IconGlyphComponent(string3, 1.5f, 3.0f, this.Mw);
        if (string3 != null) {
            this.Ms.r(true);
            this.H(this.Ms);
            this.MO = 3.0;
        } else {
            this.Ms.Z(false);
            this.MO = 0.0;
        }
    }

    public void X(Color color) {
        this.v = color;
    }

    public void d(Color color) {
        this.MV = color;
    }

    public void m(String string) {
        this.M8.G(string);
    }

    public Color z() {
        return this.Mo;
    }

    @Override
    public void H() {
        double d;
        boolean bl = Boolean.TRUE.equals(this.MI.get());
        boolean bl2 = this.w$src$Z$e457mb();
        this.MR.u(bl2 && !bl);
        this.I.u(bl);
        double d2 = this.G$src$D$1b2f02a();
        double d3 = this.n();
        double d4 = this.A();
        double d5 = this.L();
        if (bl) {
            GuiRenderPrimitives.B(d2, d3, d4, d5, this.I.getInterpolatedColor(), 3.0f);
        } else if (bl2) {
            GuiRenderPrimitives.B(d2, d3, d4, d5, this.MR.getInterpolatedColor(), 3.0f);
        }
        Color color = this.Mo;
        Color color2 = this.Mw;
        if (bl) {
            color = this.ME;
            color2 = this.MW;
        } else if (bl2) {
            color = this.MQ;
            color2 = this.MV;
        }
        double d6 = d2 + 6.0;
        if (this.Mm.V$src$Z$1xhop3l()) {
            this.Mm.S(color);
            this.Mm.K(d6);
            this.Mm.S(d3 + (d5 - 6.0) / 2.0);
            d6 += 12.0;
        }
        double d7 = d2 + d4 - 6.0;
        if (this.Ms.V$src$Z$1xhop3l()) {
            d = d7 - this.MO;
            this.Ms.S(color2);
            this.Ms.K(d);
            this.Ms.S(d3 + (d5 - this.MO) / 2.0);
            d7 = d - 6.0;
        }
        if (this.MX != null) {
            Integer n = this.MX.get();
            int n2 = Math.max(0, n != null ? n : 0);
            boolean bl3 = this.k$src$Z$affn7c() && n2 > 0;
            this.MT.Z(bl3);
            if (bl3) {
                Color color3 = bl ? this.v : this.K;
                double d8 = this.MT.o$src$Lgg_vape_ui_click_component_IconShape_$20egvb() == IconShape.CIRCLE ? 8.0 : 12.0;
                double d9 = d7 - d8;
                double d10 = d3 + (d5 - 8.0) / 2.0;
                d7 = d9 - 6.0;
                this.MT.K(d9);
                this.MT.S(d10);
                this.MT.o(d8);
                this.MT.Y(8.0);
                this.MT.O(color3);
                this.MT.c();
            }
        }
        d = Math.max(0.0, d7 - d6);
        this.M8.T$src$V$1orl066(color2);
        this.M8.o(d);
        this.M8.Y(d5);
        this.M8.K(d6);
        this.M8.S(d3);
    }

    public void r(Color color) {
        this.Mo = color;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void U(Color color) {
        this.Mw = color;
    }

    public Supplier<Boolean> K$src$Ljava_util_function_Supplier_$ozx6xa() {
        return this.MI;
    }

    public ColorAnimation d$src$Lgg_vape_ui_click_animation_ColorAnimation_$dc38du() {
        return this.I;
    }

    @Nullable
    public Supplier<Integer> U$src$Ljava_util_function_Supplier_$j0fpso() {
        return this.MX;
    }

    public void W(Color color) {
        this.MW = color;
    }

    public Color Q$src$Ljava_awt_Color_$tezaj8() {
        return this.K;
    }

    private boolean k$src$Z$affn7c() {
        if (ClientSettings.fW == null || ClientSettings.fW.fc == null) {
            return true;
        }
        return ClientSettings.fW.fc.L();
    }

    public double F$src$D$9v38q1() {
        return this.MO;
    }

    public Color q$src$Ljava_awt_Color_$q5er5g() {
        return this.Mw;
    }

    public void M(Color color) {
        this.ME = color;
    }

    public Color W$src$Ljava_awt_Color_$1nvo7ri() {
        return this.v;
    }

    public void z(Color color) {
        this.K = color;
    }

    public Color E() {
        return this.MV;
    }

    public void C(Color color) {
        this.MQ = color;
    }

    public Color v() {
        return this.ME;
    }

    public ColorAnimation B$src$Lgg_vape_ui_click_animation_ColorAnimation_$15kpmz4() {
        return this.MR;
    }

    public Color g$src$Ljava_awt_Color_$mqbx32() {
        return this.MW;
    }

    public ShapeIconComponent Y$src$Lgg_vape_ui_click_component_ShapeIconComponent_$1mbomq8() {
        return this.MT;
    }

    public FriendModuleInteractiveComponent(String string, @Nullable String string2, Supplier<Boolean> supplier) {
        this(string, string2, supplier, null, null);
    }

    public IconGlyphComponent W() {
        return this.Mm;
    }

    public Color Y$src$Ljava_awt_Color_$1acv66k() {
        return this.MQ;
    }
}

