package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleRowBase;
import gg.vape.module.utility.inventory.cleaner.ui.ItemInventoryFilterRuleRowContent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class ItemInventoryFilterRuleRow
extends InventoryFilterRuleRowBase {
    @Nullable
    private Runnable b;
    private final ItemInventoryFilterRule a;
    private static final String I = "newclose";
    private final InventoryCleanerProfile i;
    private final PaddedComponent K;
    @Nullable
    private Runnable Q;
    private final ItemInventoryFilterRuleRowContent o;
    private final ColorAnimation G;
    private final ColorAnimation O;

    public ItemInventoryFilterRule R() {
        return this.a;
    }

    @Override
    public double C() {
        return this.K.L();
    }

    public ItemInventoryFilterRuleRow(InventoryCleanerProfile inventoryCleanerProfile, ItemInventoryFilterRule itemInventoryFilterRule) {
        this.G = new ColorAnimation(0.15, new Color(0, 0, 0, 0), ItemInventoryFilterRuleRow.J.d);
        this.O = new ColorAnimation(0.15, new Color(0, 0, 0, 0), Color.WHITE);
        this.i = inventoryCleanerProfile;
        this.a = itemInventoryFilterRule;
        this.o(true);
        this.o = new ItemInventoryFilterRuleRowContent(this, inventoryCleanerProfile, itemInventoryFilterRule);
        this.K = new PaddedComponent(2.0, this.o);
        this.H(this.K);
        this.p();
    }

    @Override
    public double x() {
        return this.K.A();
    }

    @Override
    public void c() {
        this.K.K(this.G$src$D$1b2f02a());
        this.K.S(this.n());
        this.K.l$src$V$1mibm4x();
        super.c();
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() - 10.0, this.n(), 10.0, 1.0, this.G.getInterpolatedColor());
        ImageRenderer.E(this.O.getInterpolatedColor(), (float)(this.G$src$D$1b2f02a() + this.A() - 11.0), (float)(this.n() - 1.0), I, 12.0f, 12.0f, false);
    }

    @Override
    public void p() {
        this.o.p();
    }

    public InventoryCleanerProfile x$src$Lgg_vape_module_utility_inventory_cleaner_Invent$r6nguw() {
        return this.i;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void z(@Nullable Runnable runnable) {
        this.Q = runnable;
    }

    @Nullable
    public Runnable d$src$Ljava_lang_Runnable_$o59fr4() {
        return this.b;
    }

    public void v(@Nullable Runnable runnable) {
        this.b = runnable;
    }

    @Nullable
    public Runnable a$src$Ljava_lang_Runnable_$rgxzz7() {
        return this.Q;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if ((double)guiMouseEvent.getX() >= this.G$src$D$1b2f02a() + this.A() - 10.0 && (double)guiMouseEvent.getX() <= this.G$src$D$1b2f02a() + this.A() && (double)guiMouseEvent.getY() >= this.n() && (double)guiMouseEvent.getY() <= this.n() + 8.0) {
            Runnable runnable = this.b;
            if (runnable != null) {
                runnable.run();
            }
            return;
        }
        if (this.o.w$src$Z$e457mb()) {
            Runnable runnable = this.Q;
            if (runnable != null) {
                ClientSettings.f6.execute(runnable);
            }
            this.p();
        }
    }

    @Override
    public void onEnable() {
        this.G.J();
        this.O.J();
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.G.J();
            this.O.J();
        }
    }
}

