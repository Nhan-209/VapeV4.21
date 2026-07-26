package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpecBuilder;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpecEntry;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public final class ClickGuiOverlaySpec {
    @Nullable
    private final String c;
    private final String d;
    private final Consumer<PanelComponent> B;
    @Nullable
    private final Double v;
    @Nullable
    private final Consumer<ClickGuiSidecarPanelBase> L;
    @Nullable
    private final ClickGuiSidecarPanelBase k;
    private final ClickGuiOverlayTransitionMode b;
    private final boolean j;
    private final ClickGuiOverlayPlacement i;

    @Nullable
    public String java_lang_String_O() {
        return this.c;
    }

    @Nullable
    public Double B() {
        return this.v;
    }

    public Consumer<PanelComponent> n() {
        return this.B;
    }

    public ClickGuiOverlayPlacement a_oc_0_O() {
        return this.i;
    }

    @Nullable
    public ClickGuiSidecarPanelBase o() {
        return this.k;
    }

    ClickGuiOverlaySpec(String string, ClickGuiSidecarPanelBase clickGuiSidecarPanelBase, String string2, Consumer consumer, Consumer consumer2, ClickGuiOverlayPlacement clickGuiOverlayPlacement, boolean bl, ClickGuiOverlayTransitionMode clickGuiOverlayTransitionMode, Double d, ClickGuiOverlaySpecEntry clickGuiOverlaySpecEntry) {
        this(string, clickGuiSidecarPanelBase, string2, consumer, consumer2, clickGuiOverlayPlacement, bl, clickGuiOverlayTransitionMode, d);
    }

    public String p() {
        return this.d;
    }

    @Nullable
    public Consumer<ClickGuiSidecarPanelBase> t() {
        return this.L;
    }

    public boolean R() {
        return this.j;
    }

    public ClickGuiOverlayTransitionMode y() {
        return this.b;
    }

    public static ClickGuiOverlaySpecBuilder q() {
        return new ClickGuiOverlaySpecBuilder(null);
    }

    private ClickGuiOverlaySpec(String string, @Nullable ClickGuiSidecarPanelBase clickGuiSidecarPanelBase, @Nullable String string2, Consumer<PanelComponent> consumer, @Nullable Consumer<ClickGuiSidecarPanelBase> consumer2, ClickGuiOverlayPlacement clickGuiOverlayPlacement, boolean bl, ClickGuiOverlayTransitionMode clickGuiOverlayTransitionMode, @Nullable Double d) {
        this.d = string;
        this.k = clickGuiSidecarPanelBase;
        this.c = string2;
        this.B = consumer;
        this.L = consumer2;
        this.i = clickGuiOverlayPlacement;
        this.j = bl;
        this.b = clickGuiOverlayTransitionMode;
        this.v = d;
    }

    public /* synthetic */ ClickGuiOverlayPlacement O() {
        return this.a_oc_0_O();
    }

    public /* synthetic */ String O$src$Ljava_lang_String_$vd7j9e() {
        return this.java_lang_String_O();
    }
}

