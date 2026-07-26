package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.ToolTips;
import gg.vape.utils.EnchantmentUtil;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.wrapper.impl.Enchantment;
import gg.vape.wrapper.impl.ItemStack;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.Map;

public class ItemStackSlotComponent
extends GuiComponent {
    private int R;
    boolean i = false;
    private final Enchantment[] o = new Enchantment[]{Enchantment.protection(), Enchantment.unbreaking(), Enchantment.sharpness(), Enchantment.fireAspect(), Enchantment.efficiency(), Enchantment.featherFalling(), Enchantment.power(), Enchantment.flame(), Enchantment.punch(), Enchantment.fortune(), Enchantment.infinity(), Enchantment.thorns(), Enchantment.knockback()};
    boolean a = false;
    private ItemStack I;

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void u() {
        if (!this.i) {
            return;
        }
        if (this.I != null) {
            boolean bl;
            boolean bl2 = ItemStackScoreUtil.R(this.I.getItem());
            boolean bl3 = bl = ItemStackScoreUtil.V$src$Z$dcbuai(this.I) || ItemStackScoreUtil.Y(this.I);
            if (!bl2 && !bl) {
                ToolTips toolTips = new ToolTips(this, this.I.x() + " (" + this.I.t() + ")");
                this.M(toolTips);
            } else {
                Map<Enchantment, Short> enchantments;
                String string = this.I.x();
                String string2 = new BigDecimal((double)(this.I.getItem().a() - this.I.L()) / (double)this.I.getItem().a() * 100.0 + "").setScale(0, 4).toPlainString() + "% durability";
                String string3 = "";
                try {
                    enchantments = EnchantmentUtil.A(this.I);
                    for (Map.Entry<Enchantment, Short> object3 : enchantments.entrySet()) {
                        Enchantment enchantment = object3.getKey();
                        short s = object3.getValue();
                        String string4 = enchantment.getTranslatedName(s);
                        string3 = string3 + "\n" + string4;
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
                String itemSummary = bl2 ? "+" + (int)ItemStackScoreUtil.L(this.I) + " Protection\n" : "+" + (int)ItemStackScoreUtil.I$src$F$dh3k81(this.I) + " Damage\n";
                String string5 = itemSummary + string2 + string3;
                ToolTips toolTips = new ToolTips(this, string5, 0.75, ItemStackSlotComponent.J.A, false, string, 0.9, ItemStackSlotComponent.J.B, true);
                this.M(toolTips);
            }
        } else {
            this.M(null);
        }
        this.i = false;
    }

    public ItemStack Q$src$Lgg_vape_wrapper_impl_ItemStack_$1pwm558() {
        return this.I;
    }

    @Override
    public void c() {
        super.c();
        this.onDisable();
        if (this.I != null) {
            double d = this.A() - (double)this.R;
            double d2 = this.L() - (double)this.R;
            double d3 = d / 2.0;
            double d4 = d2 / 2.0;
            ItemIconRenderer.d(this.I, (float)(this.G$src$D$1b2f02a() + d3), (float)(this.n() + d4), this.R, this.R, 1.0f, 1.0f);
            if (this.a) {
                GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), new Color(126, 84, 217, 215), 1.0f, 0.8f, 1.0f);
            }
        }
    }

    @Override
    public void F() {
    }

    @Override
    public void H() {
    }

    public boolean u$src$Z$10mnk6f() {
        return this.a;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public ItemStackSlotComponent() {
        this(12.0, 12.0, 10);
    }

    public void P(ItemStack itemStack) {
        this.I = itemStack;
        this.i = true;
    }

    public ItemStackSlotComponent(double d, double d2, int n) {
        this.o(d);
        this.Y(d2);
        this.R = (int)Math.min(Math.min((double)n, d), d2);
    }

    @Override
    public void onDisable() {
        if (!this.Z$src$Z$16e8vsp()) {
            return;
        }
        GuiRenderPrimitives.e(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.d(), false, 1.0f, 1.0f);
    }

    public void e(boolean bl) {
        this.a = bl;
    }

    @Override
    public void I() {
    }

    @Override
    public double C() {
        return 0.0;
    }
}
