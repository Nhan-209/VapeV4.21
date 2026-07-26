package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleRowBase;
import gg.vape.module.utility.inventory.cleaner.ui.SlotInventoryFilterRuleRowContent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class SlotInventoryFilterRuleRow
extends InventoryFilterRuleRowBase {
    private final PaddedComponent a;
    private final SlotInventoryFilterRule i;
    private final ColorAnimation Q;
    private final SlotInventoryFilterRuleRowContent K;
    private static final String o = "newclose";
    private final InventoryCleanerProfile I;
    @Nullable
    private Runnable O;
    private final ColorAnimation v;

    @Override
    public void p() {
        this.K.p();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        Runnable runnable;
        if ((double)guiMouseEvent.getX() >= this.G$src$D$1b2f02a() + this.A() - 10.0 && (double)guiMouseEvent.getX() <= this.G$src$D$1b2f02a() + this.A() && (double)guiMouseEvent.getY() >= this.n() && (double)guiMouseEvent.getY() <= this.n() + 8.0) {
            this.i.y();
            this.p();
            return;
        }
        if (this.K.w$src$Z$e457mb() && (runnable = this.O) != null) {
            ClientSettings.f6.execute(() -> this.lambda$onMouse$0(runnable));
        }
    }

    @Override
    public double C() {
        return this.a.L();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void c() {
        this.a.K(this.G$src$D$1b2f02a());
        this.a.S(this.n());
        this.a.l$src$V$1mibm4x();
        super.c();
        if (!this.i.q().j()) {
            GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() - 10.0, this.n(), 10.0, 1.0, this.Q.getInterpolatedColor());
            ImageRenderer.E(this.v.getInterpolatedColor(), (float)(this.G$src$D$1b2f02a() + this.A() - 11.0), (float)(this.n() - 1.0), o, 12.0f, 12.0f, false);
        }
    }

    @Override
    public double x() {
        return this.a.A();
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.Q.J();
            this.v.J();
        }
    }

    public void p(@Nullable Runnable runnable) {
        this.O = runnable;
    }

    private void lambda$onMouse$0(Runnable runnable) {
        runnable.run();
        this.p();
    }

    public SlotInventoryFilterRuleRow(InventoryCleanerProfile inventoryCleanerProfile, SlotInventoryFilterRule slotInventoryFilterRule) {
        this.Q = new ColorAnimation(0.15, new Color(0, 0, 0, 0), SlotInventoryFilterRuleRow.J.d);
        this.v = new ColorAnimation(0.15, new Color(0, 0, 0, 0), Color.WHITE);
        this.I = inventoryCleanerProfile;
        this.i = slotInventoryFilterRule;
        this.K = new SlotInventoryFilterRuleRowContent(this, inventoryCleanerProfile, slotInventoryFilterRule);
        this.a = new PaddedComponent(2.0, this.K);
        this.p();
        this.o(true);
        this.H(this.a);
    }

    @Nullable
    public Runnable L$src$Ljava_lang_Runnable_$ps7wst() {
        return this.O;
    }

    @Override
    public void onEnable() {
        this.Q.J();
        this.v.J();
    }
}

