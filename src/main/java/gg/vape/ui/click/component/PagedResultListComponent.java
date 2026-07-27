package gg.vape.ui.click.component;

import gg.vape.api.PagedResult;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public class PagedResultListComponent
extends PanelComponent {
    private GlyphIconComponent pB;
    @Nullable
    private PagedResult<?> p0;
    private boolean p3;
    private int pv;
    private CompletableFuture<List<GuiComponent>> px;
    @Nullable
    private Supplier<CompletableFuture<List<GuiComponent>>> pc;
    @Nullable
    private Supplier<GuiComponent> pO;
    private int pS = 1;
    private int pK = 1;
    private int ps;
    private List<GuiComponent> pC = new ArrayList<GuiComponent>();
    private long py;
    private static final long gb = 4641203030845292568L;
    private boolean pD = false;
    private long p9;
    @Nullable
    private PanelComponent pJ;

    @Override
    public void c() {
        super.c();
        PanelComponent panelComponent = this.c$src$Lgg_vape_ui_click_component_PanelComponent_$ntzwqg();
        this.pB.K(this.G$src$D$1b2f02a() + (this.A() - 18.0));
        this.pB.S(panelComponent.n() + 4.0);
        this.pB.Z(panelComponent.J$src$D$hx1pag() < -panelComponent.L());
        if (this.pB.V$src$Z$1xhop3l()) {
            this.pB.c();
        }
    }

    public PagedResultListComponent(double d, double d2) {
        this(d, d2, 1);
    }

    @Nullable
    public Supplier<GuiComponent> f$src$Ljava_util_function_Supplier_$1cbdavl() {
        return this.pO;
    }

    @Override
    public double x() {
        if (this.pJ != null) {
            return this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C();
        }
        return super.x();
    }

    @Override
    public void S() {
        super.S();
        this.c(true);
    }

    @Nullable
    public PagedResult<?> y$src$Lgg_vape_api_PagedResult_$rip6se() {
        return this.p0;
    }

    public int p() {
        return this.pS;
    }

    @Override
    public void h(GuiComponent guiComponent, Object ... objectArray) {
        boolean bl = this.pS == 1 || this.pv > 0 && (this.pv + 1) % this.pS == 0;
        super.h(guiComponent, bl ? "wrap" : "");
        ++this.pv;
        if (this.pJ != null) {
            this.u(this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y());
            this.t(this.H$src$D$1wlsgtk());
        }
    }

    public void f(List<GuiComponent> list) {
        this.pC = list;
    }

    public int h() {
        return this.ps;
    }

    @Nullable
    public Supplier<CompletableFuture<List<GuiComponent>>> r$src$Ljava_util_function_Supplier_$154sbx9() {
        return this.pc;
    }

    public void N(@Nullable Supplier<CompletableFuture<List<GuiComponent>>> supplier) {
        this.pc = supplier;
    }

    public long A$src$J$1vju51i() {
        return this.py;
    }

    public void W() {
        this.c(false);
        this.K$src$V$1vpc39g();
    }


    private void e$src$V$1w3mqou() {
        CompletableFuture<List<GuiComponent>> completableFuture = this.px;
        if (completableFuture != null) {
            completableFuture.cancel(true);
            this.px = null;
            this.b(this.pC, new ArrayList<GuiComponent>());
            this.pC.clear();
        }
    }

    @Override
    public void F() {
        if (this.pB.V$src$Z$1xhop3l() && this.pB.t()) {
            this.pB.F();
        }
    }

    public void H(int n) {
        this.pK = n;
    }

    @Override
    public double C() {
        if (this.pJ != null) {
            return this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y();
        }
        return super.C();
    }

    public void A(int n) {
        this.pS = n;
    }

    @Nullable
    public PanelComponent Y$src$Lgg_vape_ui_click_component_PanelComponent_$1h13gfi() {
        return this.pJ;
    }

    private void lambda$loadNewContent$1(AtomicReference atomicReference, List list, Throwable throwable) {
        if (throwable != null) {
            this.px = null;
            return;
        }
        if (list == null) {
            this.px = null;
            return;
        }
        if (atomicReference.get() != this.px) {
            return;
        }
        this.b(this.pC, list);
        this.pC.clear();
        if (list.isEmpty()) {
            this.pD = true;
        } else {
            ++this.py;
        }
        this.p3 = true;
        this.px = null;
    }

    public int K$src$I$1vpc2y9() {
        return this.pK;
    }

    private List lambda$loadNewContent$2(Throwable throwable) {
        this.px = null;
        return null;
    }

    @Override
    public void t$src$V$zbu1jn() {
        super.t$src$V$zbu1jn();
        this.c(true);
    }

    public void j(long l) {
        this.p9 = l;
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        if (this.pB.V$src$Z$1xhop3l() && this.pB.t()) {
            this.pB.D(guiMouseEvent);
            return;
        }
        super.D(guiMouseEvent);
    }

    public void s$src$V$1wbbuzw() {
        List<GuiComponent> list = this.f();
        double d = this.J$src$D$hx1pag();
        super.S();
        this.pv = 0;
        for (GuiComponent guiComponent : list) {
            this.h(guiComponent, new Object[0]);
        }
        this.W(d);
    }

    public long N$src$J$1vqzgr7() {
        return this.p9;
    }

    public void b(List<GuiComponent> list, List<GuiComponent> list2) {
        double d = this.J$src$D$hx1pag();
        for (GuiComponent guiComponent : list) {
            this.I(guiComponent);
        }
        for (GuiComponent guiComponent : list2) {
            this.h(guiComponent, new Object[0]);
        }
        this.s$src$V$1wbbuzw();
        this.W(d);
    }

    public PagedResultListComponent(double d, double d2, int n) {
        super(d, d2);
        this.ps = (int)gb;
        this.pB = new GlyphIconComponent("up_arrow", 8.0, 8.0, 15.0, 15.0, Color.WHITE, PagedResultListComponent.J.f, new Color(255, 255, 255, 64));
        this.p9 = n;
        this.py = n;
        this.pB.d(6.0);
        this.pB.U(6.0);
        this.pB.o(PagedResultListComponent.J.W);
        this.pB.E(PagedResultListComponent.J.m, PagedResultListComponent.J.m.brighter());
        this.pB.j(PagedResultListComponent.J.l);
        this.pB.Z(0.75f);
        this.pB.d(true);
        this.pB.o(14.0);
        this.pB.Y(10.0);
        this.pB.i(5.0f);
        this.pB.q(true);
        this.pB.R(true);
        this.pB.r(this::lambda$new$0);
    }

    private PanelComponent c$src$Lgg_vape_ui_click_component_PanelComponent_$ntzwqg() {
        return this.pJ != null ? this.pJ : this;
    }

    public void T(@Nullable PanelComponent panelComponent) {
        this.pJ = panelComponent;
    }

    public void e(@Nullable Supplier<GuiComponent> supplier) {
        this.pO = supplier;
    }

    private void c(boolean bl) {
        this.py = this.p9;
        this.pD = this.p0 != null && this.p0.F();
        this.p3 = false;
        this.pv = 0;
        if (!bl) {
            this.S();
        }
        this.e$src$V$1w3mqou();
    }

    public void X(int n) {
        this.ps = n;
    }

    private void lambda$new$0() {
        PanelComponent panelComponent = this.c$src$Lgg_vape_ui_click_component_PanelComponent_$ntzwqg();
        panelComponent.b(0.0);
    }

    @Override
    public void I(GuiComponent guiComponent) {
        super.I(guiComponent);
        --this.pv;
    }

    @Override
    public void u() {
        super.u();
        if (this.pB.V$src$Z$1xhop3l()) {
            this.pB.u();
        }
    }

    private void K$src$V$1vpc39g() {
        this.e$src$V$1w3mqou();
        Supplier<CompletableFuture<List<GuiComponent>>> supplier = this.pc;
        if (supplier == null) {
            return;
        }
        Supplier<GuiComponent> supplier2 = this.pO;
        if (supplier2 != null) {
            int n = this.ps;
            PagedResult<?> pagedResult = this.p0;
            if (pagedResult != null && this.py > pagedResult.A()) {
                n = (int)pagedResult.L() % this.ps;
            }
            ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>();
            for (int i = 0; i < n; ++i) {
                arrayList.add(supplier2.get());
            }
            this.H(arrayList.toArray(new GuiComponent[0]));
            this.f(arrayList);
        }
        AtomicReference<CompletableFuture<List<GuiComponent>>> atomicReference = new AtomicReference<CompletableFuture<List<GuiComponent>>>();
        this.px = supplier.get().whenCompleteAsync((arg_0, arg_1) -> this.lambda$loadNewContent$1(atomicReference, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(this::lambda$loadNewContent$2);
        atomicReference.set(this.px);
    }

    @Override
    public void Y() {
        if (this.px != null) {
            return;
        }
        PanelComponent panelComponent = this.c$src$Lgg_vape_ui_click_component_PanelComponent_$ntzwqg();
        if (this.p3) {
            this.p3 = false;
            return;
        }
        if (panelComponent.J$src$D$hx1pag() == 0.0) {
            return;
        }
        int n = this.f().size();
        int n2 = 0;
        double d = panelComponent.n() + panelComponent.L();
        for (GuiComponent guiComponent : this.f()) {
            if (!(guiComponent.n() + guiComponent.L() / 2.0 <= d)) break;
            ++n2;
        }
        int n3 = n - n2;
        if (n3 <= this.pK && !this.pD) {
            this.K$src$V$1vpc39g();
        }
    }

    public void t(@Nullable PagedResult<?> pagedResult) {
        this.p0 = pagedResult;
        if (pagedResult != null && pagedResult.F()) {
            this.pD = true;
        }
    }
}
