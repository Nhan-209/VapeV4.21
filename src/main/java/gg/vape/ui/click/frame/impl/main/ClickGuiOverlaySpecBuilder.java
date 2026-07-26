package gg.vape.ui.click.frame.impl.main;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpecEntry;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import java.util.Objects;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public final class ClickGuiOverlaySpecBuilder {
    private Consumer<PanelComponent> m;
    private Consumer<ClickGuiSidecarPanelBase> p;
    private Boolean g;
    private static final String b = "Sidecar title must be provided";
    private ClickGuiOverlayTransitionMode W;
    private Double V;
    private ClickGuiOverlayPlacement r = ClickGuiOverlayPlacement.OVERLAY;
    private String U;
    private String q;
    private ClickGuiSidecarPanelBase K;

    public ClickGuiOverlaySpecBuilder r(ClickGuiOverlayTransitionMode clickGuiOverlayTransitionMode) {
        this.W = clickGuiOverlayTransitionMode;
        return this;
    }

    public ClickGuiOverlaySpecBuilder e(String string) {
        this.U = string;
        return this;
    }

    public ClickGuiOverlaySpecBuilder K(@Nullable Double d) {
        this.V = d;
        return this;
    }

    ClickGuiOverlaySpecBuilder(ClickGuiOverlaySpecEntry clickGuiOverlaySpecEntry) {
        this();
    }

    public ClickGuiOverlaySpecBuilder n(ClickGuiOverlayPlacement clickGuiOverlayPlacement) {
        this.r = clickGuiOverlayPlacement;
        return this;
    }

    public ClickGuiOverlaySpecBuilder x(boolean bl) {
        this.g = bl;
        return this;
    }

    public ClickGuiOverlaySpecBuilder C(@Nullable String string) {
        this.q = string;
        return this;
    }

    private ClickGuiOverlaySpecBuilder() {
        this.W = ClickGuiOverlayTransitionMode.REPLACE;
    }

    public ClickGuiOverlaySpecBuilder v(@Nullable ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        this.K = clickGuiSidecarPanelBase;
        return this;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ClickGuiOverlaySpecBuilder D(Consumer<ClickGuiSidecarPanelBase> consumer) {
        this.p = consumer;
        return this;
    }

    private static void lambda$build$0(PanelComponent panelComponent) {
    }

    public ClickGuiOverlaySpecBuilder N(Consumer<PanelComponent> consumer) {
        this.m = consumer;
        return this;
    }

    public ClickGuiOverlaySpec w() {
        String string = Objects.requireNonNull(this.U, b);
        Consumer<PanelComponent> consumer = this.m != null ? this.m : ClickGuiOverlaySpecBuilder::lambda$build$0;
        ClickGuiOverlayPlacement clickGuiOverlayPlacement = this.r != null ? this.r : ClickGuiOverlayPlacement.OVERLAY;
        boolean bl = this.g != null ? this.g : true;
        ClickGuiOverlayTransitionMode clickGuiOverlayTransitionMode = this.W != null ? this.W : ClickGuiOverlayTransitionMode.REPLACE;
        return new ClickGuiOverlaySpec(string, this.K, this.q, consumer, this.p, clickGuiOverlayPlacement, bl, clickGuiOverlayTransitionMode, this.V, null);
    }
}

