package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerProfileRow;
import gg.vape.notification.NotificationType;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameComponent;
import java.util.List;

public class InventoryCleanerProfileValueComponent
extends GuiComponent {
    private final FlowLayoutComponent o;
    private boolean i;
    private final FlowLayoutComponent G;
    private static GuiComponent[] I;
    private final InventoryCleanerProfileValue v;

    public static GuiComponent[] G$src$ALgg_vape_ui_click_component_GuiComponent_$2jjb6h() {
        return I;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.G.w$src$Z$e457mb()) {
            this.G.D(guiMouseEvent);
        }
    }

    public InventoryCleanerProfileValueComponent(InventoryCleanerProfileValue inventoryCleanerProfileValue) {
        this.v = inventoryCleanerProfileValue;
        this.C(inventoryCleanerProfileValue);
        this.G = new FlowLayoutComponent(this.x());
        this.G.d(false);
        this.G.k(true);
        this.o = new FlowLayoutComponent(this.x());
        this.o.d(false);
    }

    public void b$src$V$18h0koc() {
        Runnable runnable = this::b$src$V$18h0koc;
        this.G.S();
        this.G.h(new SpacerComponent(0.0, 1.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("INVENTORY PRESETS", 0.75);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.o(this.A());
        this.G.h(simpleTextLabelComponent, new Object[0]);
        this.G.h(new SpacerComponent(this.A(), 2.0), new Object[0]);
        this.G.h(new SpacerComponent(5.0, 0.0), new Object[0]);
        double d = this.A();
        this.getClass();
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent("newadd", 7.0, 7.0, d - (double)(5.0f * 2.0f), 15.0, InventoryCleanerProfileValueComponent.J.B, InventoryCleanerProfileValueComponent.J.O, InventoryCleanerProfileValueComponent.J.l);
        glyphIconComponent.q(true);
        glyphIconComponent.R(true);
        glyphIconComponent.r(() -> this.lambda$populate$0(runnable));
        glyphIconComponent.w("Add new inventory preset");
        this.G.h(glyphIconComponent, new Object[0]);
        this.G.h(new SpacerComponent(this.A(), 5.0), new Object[0]);
        this.G.h(this.o, new Object[0]);
        this.o.S();
        List<InventoryCleanerProfile> list = this.v.w();
        for (int i = 0; i < list.size(); ++i) {
            boolean bl;
            InventoryCleanerProfile inventoryCleanerProfile = list.get(i);
            InventoryCleanerProfileRow inventoryCleanerProfileRow = new InventoryCleanerProfileRow(this.v, inventoryCleanerProfile, runnable);
            inventoryCleanerProfileRow.o(this.A());
            boolean bl2 = bl = i == list.size() - 1;
            if (bl) {
                this.o.h(new SpacerComponent(0.0, 1.0), new Object[0]);
            }
            this.o.h(new PaddedComponent(0.0, bl ? 0.0 : 1.0, inventoryCleanerProfileRow), new Object[0]);
        }
        this.G.H(true);
        FrameComponent frameComponent = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
        if (frameComponent != null) {
            frameComponent.H(true);
        }
    }

    @Override
    public void F() {
        this.G.J();
    }

    @Override
    public void u() {
        this.G.T$src$V$1wse0de();
    }

    @Override
    public double C() {
        return this.G.L() + 2.0;
    }

    public static void I(GuiComponent[] guiComponentArray) {
        I = guiComponentArray;
    }

    @Override
    public void H() {
        double d = this.A();
        this.G.B(d);
        this.G.o(d);
        this.o.B(d);
        this.o.o(d);
        if (!this.i) {
            this.i = true;
            this.b$src$V$18h0koc();
        }
        this.d(true);
        this.onDisable();
        this.G.K(this.G$src$D$1b2f02a());
        this.G.S(this.n());
        this.G.c();
        FrameComponent frameComponent = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
        if (frameComponent != null) {
            frameComponent.H(true);
        }
    }

    static {
        InventoryCleanerProfileValueComponent.I(new GuiComponent[2]);
    }

    @Override
    public double x() {
        return 110.0;
    }

    private void lambda$populate$0(Runnable runnable) {
        if (this.v.w().size() >= 10) {
            Vape.INSTANCE.getNotificationManager().K("Inventory Manager", "You've reached the limit of 10 inventories.", NotificationType.WARNING, 5000L, true);
            return;
        }
        InventoryCleanerProfile inventoryCleanerProfile = new InventoryCleanerProfile();
        if (this.v.w().isEmpty()) {
            this.v.o(inventoryCleanerProfile);
        }
        this.v.I(inventoryCleanerProfile);
        ClientSettings.f6.execute(runnable);
    }
}

