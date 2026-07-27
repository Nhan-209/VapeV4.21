package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.Nullable;

public class HudOverlayEntryInteractiveComponent
extends InteractiveComponent {
    private static final Color v = new Color(255, 255, 255, 10);
    private String b;
    private static final float sE = 6.0f;
    @Nullable
    private Runnable I;
    private static final Color sl = new Color(0, 0, 0, 63);
    @Nullable
    private Class<? extends Frame> K;
    public static final double sm = 22.0;
    private static final float sK = 4.0f;
    @Nullable
    private BooleanSupplier Q;
    private final IconGlyphComponent sx;


    public void p(String string) {
        this.b = string;
        this.w(string);
    }

    public void A(@Nullable BooleanSupplier booleanSupplier) {
        this.Q = booleanSupplier;
    }

    public void K(@Nullable Class<? extends Frame> clazz) {
        this.K = clazz;
    }

    private boolean m$src$Z$bagb1u() {
        if (this.Q != null) {
            return this.Q.getAsBoolean();
        }
        if (this.K == null) {
            return false;
        }
        Frame frame = ClientSettings.g(this.K);
        return frame != null && frame.V$src$Z$1xhop3l();
    }

    @Override
    public void H() {
        boolean bl = this.m$src$Z$bagb1u();
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.A();
        Color color = ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1();
        GuiRenderPrimitives.I(d, d2 - 1.0, d3, d3, ColorUtil.N(color, 40.0), false, 4.0f, 1.0f, 8.0f, HudOverlayEntryInteractiveComponent.J.u);
        GuiRenderPrimitives.I(d, d2 + 1.0, d3, d3, ColorUtil.N(color, -15.0), false, 4.0f, 1.0f, 8.0f, HudOverlayEntryInteractiveComponent.J.u);
        GuiRenderPrimitives.I(d, d2, d3, d3, color, false, 4.0f, 1.0f, 8.0f, HudOverlayEntryInteractiveComponent.J.u);
        Color color2 = HudOverlayEntryInteractiveComponent.J.W;
        color2 = J.B();
        this.sx.S(color2);
        this.sx.K(d + (d3 - 6.0) / 2.0);
        this.sx.S(d2 + (d3 - 6.0) / 2.0);
    }

    public HudOverlayEntryInteractiveComponent(String string, String string2) {
        this.sx = new IconGlyphComponent(string, 6.0f, 6.0f);
        this.sx.r(true);
        this.sx.o(6.0);
        this.sx.Y(6.0);
        this.b = string2;
        this.w(string2);
        this.o(22.0);
        this.Y(22.0);
        this.d(false);
        this.o(true);
        this.H(this.sx);
        this.r(this::lambda$new$0);
    }

    private void lambda$new$0() {
        this.e$src$V$b61y7i();
        if (this.I != null) {
            this.I.run();
        }
    }

    private void e$src$V$b61y7i() {
        if (this.K == null) {
            return;
        }
        Frame frame = ClientSettings.g(this.K);
        ClientSettings.T(this.K);
        Frame frame2 = ClientSettings.g(this.K);
        if (frame2 != null) {
            frame2.c(frame2.V$src$Z$1xhop3l());
        }
        if (frame != null && Objects.equals(frame, frame2)) {
            frame.l$src$V$1mibm4x();
        }
    }

    public void V(@Nullable Runnable runnable) {
        this.I = runnable;
    }

    public String s$src$Ljava_lang_String_$1w8t2t6() {
        return this.b;
    }
}

