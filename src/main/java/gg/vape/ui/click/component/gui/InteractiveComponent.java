package gg.vape.ui.click.component.gui;

import gg.vape.Vape;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.ClickCooldownState;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public abstract class InteractiveComponent
extends GuiComponent {
    private static boolean R;
    private Color G;
    private ClickCooldownState i = new ClickCooldownState();
    private Color O;
    private boolean o = false;
    private List<GuiClickListener> a = new ArrayList<GuiClickListener>();

    public static boolean h$src$Z$ql1ynz() {
        return R;
    }

    public void o(Color color) {
        this.O = color;
    }

    public void s() {
        for (GuiClickListener guiClickListener : new ArrayList<GuiClickListener>(this.a)) {
            guiClickListener.G();
        }
    }

    public void k$src$V$qmpccm() {
        this.a.clear();
    }

    public Color e$src$Ljava_awt_Color_$1yl68fq() {
        return this.O;
    }

    public InteractiveComponent e(Supplier<@Nullable CompletableFuture<?>> supplier) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        this.s(() -> InteractiveComponent.lambda$setSingleFutureListener$1(atomicBoolean, supplier));
        return this;
    }

    public List<GuiClickListener> l$src$Ljava_util_List_$7yhdmw() {
        return this.a;
    }

    public void G(GuiClickListener guiClickListener) {
        this.a.remove(guiClickListener);
    }

    public void P$src$V$q7uwbv() {
        for (GuiClickListener guiClickListener : new ArrayList<GuiClickListener>(this.a)) {
            guiClickListener.P();
        }
    }

    private static void lambda$setSingleFutureListener$1(AtomicBoolean atomicBoolean, Supplier<@Nullable CompletableFuture<?>> supplier) {
        if (!atomicBoolean.get()) {
            return;
        }
        CompletableFuture<?> completableFuture = supplier.get();
        if (completableFuture == null) {
            return;
        }
        atomicBoolean.set(false);
        completableFuture.whenCompleteAsync((arg_0, arg_1) -> InteractiveComponent.lambda$null$0(atomicBoolean, arg_0, arg_1));
    }

    public Color N() {
        return this.G;
    }

    public InteractiveComponent r(GuiClickListener guiClickListener) {
        this.a.add(guiClickListener);
        return this;
    }

    private static void lambda$null$0(AtomicBoolean atomicBoolean, Object object, Throwable throwable) {
        atomicBoolean.set(true);
        if (throwable != null) {
            Vape.logThrowable(throwable);
        }
    }

    public void P(Color color) {
        this.G = color;
    }

    public static void Y(boolean bl) {
        R = bl;
    }

    public ClickCooldownState X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8() {
        return this.i;
    }


    public static boolean f$src$Z$qjydh9() {
        boolean bl = InteractiveComponent.h$src$Z$ql1ynz();
        return !bl;
    }

    public void k(boolean bl) {
        this.o = bl;
    }

    static {
        if (InteractiveComponent.f$src$Z$qjydh9()) {
            InteractiveComponent.Y(true);
        }
    }

    @Override
    public boolean w$src$Z$e457mb() {
        if (this.o) {
            return false;
        }
        return super.w$src$Z$e457mb();
    }

    public void s(@Nullable GuiClickListener guiClickListener) {
        this.a = new ArrayList<GuiClickListener>();
        if (guiClickListener != null) {
            this.a.add(guiClickListener);
        }
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.i.t()) {
            return;
        }
        if (this.o) {
            return;
        }
        if (this.V$src$Z$1xhop3l()) {
            if (guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
                this.P$src$V$q7uwbv();
            }
            if (guiMouseEvent.getAction().equals((Object)MouseButton.RIGHT_CLICK)) {
                this.s();
            }
        }
        this.i.j(true);
    }
}
