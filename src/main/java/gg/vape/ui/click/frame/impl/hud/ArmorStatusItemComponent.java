package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.ArmorStatusHudFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import java.awt.Color;

public class ArmorStatusItemComponent
extends GuiComponent {
    private int O;
    private boolean G;
    private boolean R;
    private boolean Q = false;
    private TimerUtil I = new TimerUtil();
    private boolean K;
    private ArmorStatusHudFrame i;
    private boolean a;
    private ItemStack b;

    public void V(boolean bl) {
        this.Q = bl;
    }

    public Slot B$src$Lgg_vape_wrapper_impl_Slot_$1r5ac0m() {
        return Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(this.O);
    }

    public void m(boolean bl) {
        this.K = bl;
    }

    @Override
    public double x() {
        return 0.0;
    }

    private void k(ItemStack itemStack) {
        if (this.R) {
            GuiRenderPrimitives.C((float)this.G$src$D$1b2f02a() - 2.0f, (float)this.n(), this.i.A(), this.L(), this.i.l(new Color(250, 50, 57, 120)));
            ImageRenderer.drawRes(this.i.l(ArmorStatusItemComponent.J.d), (float)this.G$src$D$1b2f02a() + 4.0f, (float)this.n() + 3.0f, "armor", 0.45f);
            ImageRenderer.drawRes(this.i.l(ArmorStatusItemComponent.J.d), (float)(this.G$src$D$1b2f02a() + 1.0), (float)this.n() + 15.0f, "bar-1", 0.15f);
            return;
        }
        if (itemStack.isNull()) {
            return;
        }
        int n = this.W();
        if (n == -1) {
            return;
        }
        float f = (float)n / 100.0f;
        if (f < 0.1f) {
            float f2 = (0.1f - f) / 0.1f;
            int n2 = (int)(30.0f + 30.0f * f2);
            Color color = this.i.l(new Color(250, 50, 57, n2));
            double d = this.L();
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a() - 2.0, this.n(), this.i.A(), d, color);
        }
        ItemIconRenderer.C(itemStack, (float)(this.G$src$D$1b2f02a() + 3.0), (float)this.n(), 16, 16, this.i.r$src$F$35g3yx());
        GuiRenderPrimitives.e(this.G$src$D$1b2f02a() + 1.0, this.n() + 17.0, 20.0, 1.5, this.i.l(new Color(0, 0, 0, 153)), false, 1.0f, 1.0f);
        GuiRenderPrimitives.I(this.G$src$D$1b2f02a() + 1.0, this.n() + 17.0, 20.0f * f, 1.5, this.i.l(RenderUtils.q(f, true)), true, 1.0f, 1.0f, 4.0f, this.i.l(new Color(0, 0, 0, 75)));
        if (!this.K) {
            Color color = ColorUtil.W(Color.WHITE, 51);
            GuiRenderPrimitives.a(this.G$src$D$1b2f02a() + 7.0, this.n() + 23.0, 8.0, 2.0f, color);
        }
    }

    public boolean E$src$Z$nxro5x() {
        return this.R;
    }

    public void q(boolean bl) {
        this.a = bl;
    }

    @Override
    public double C() {
        return 0.0;
    }

    private int W() {
        ItemStack itemStack = this.y$src$Lgg_vape_wrapper_impl_ItemStack_$151qkau();
        if (itemStack == null || itemStack.isNull()) {
            return -1;
        }
        Item item = itemStack.getItem();
        if (item.isNull()) {
            return -1;
        }
        float f = ForgeVersion.MC_1_20_6.d() ? (float)itemStack.y() : (float)item.a();
        float f2 = itemStack.L();
        if (f <= 0.0f) {
            return -1;
        }
        float f3 = f - f2;
        if (f3 == 0.0f) {
            return 1;
        }
        if (f3 < 0.0f) {
            return 100;
        }
        return (int)Math.ceil(f3 / f * 100.0f);
    }

    public ItemStack E() {
        return Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(this.O).I();
    }

    @Override
    public double L() {
        return this.i.e();
    }

    private void z(ItemStack itemStack) {
        Object object;
        int n;
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().W(0.85, true);
        if (this.R) {
            GuiRenderPrimitives.C((float)this.G$src$D$1b2f02a() - 2.0f, (float)this.n(), this.i.A(), this.L(), this.i.l(new Color(250, 50, 57, 120)));
            ImageRenderer.drawRes(this.i.l(ArmorStatusItemComponent.J.d), (float)this.G$src$D$1b2f02a() + 2.0f, (float)this.n() + 3.0f, "armor", 0.45f);
            ImageRenderer.drawRes(this.i.l(ArmorStatusItemComponent.J.d), (float)this.G$src$D$1b2f02a() + 23.0f, (float)this.n() + 11.0f, "bar-1", 0.2f);
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 20.0, this.n() + 4.0, 12.0, 2.0f, this.i.l(ArmorStatusItemComponent.J.d));
            smoothFontRenderer.d("0%", this.G$src$D$1b2f02a() + 26.0, this.n() + 5.0, this.i.m$src$Ljava_awt_Color_$ppsp8z());
            return;
        }
        if (itemStack.isNull()) {
            return;
        }
        float f = (float)this.W() / 100.0f;
        if (f < 0.1f) {
            float f2 = (0.1f - f) / 0.1f;
            n = (int)(30.0f + 30.0f * f2);
            object = this.i.l(new Color(250, 50, 57, n));
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a() - 2.0, this.n(), this.i.A(), this.L(), (Color)object);
        }
        Color color = ColorUtil.W(Color.WHITE, 51);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 20.0, this.n() + 4.0, 12.0, 2.0f, this.i.l(color));
        ItemIconRenderer.d(itemStack, (float)(this.G$src$D$1b2f02a() + 2.0), (float)(this.n() + 2.0), 16, 16, this.i.r$src$F$35g3yx(), 1.0f);
        n = this.W();
        if (n == -1) {
            return;
        }
        object = String.valueOf(n);
        smoothFontRenderer.d((String)object + "%", this.G$src$D$1b2f02a() + 26.0, this.n() + 5.0, this.i.m$src$Ljava_awt_Color_$ppsp8z());
        GuiRenderPrimitives.e(this.G$src$D$1b2f02a() + 26.0, this.n() + 13.0, 20.0, 1.5, this.i.l(new Color(0, 0, 0, 153)), false, 1.0f, 1.0f);
        GuiRenderPrimitives.I(this.G$src$D$1b2f02a() + 26.0, this.n() + 13.0, 20.0f * f, 1.5, this.i.l(RenderUtils.q(f, true)), true, 1.0f, 1.0f, 4.0f, this.i.l(new Color(0, 0, 0, 75)));
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public ArmorStatusItemComponent(ArmorStatusHudFrame armorStatusHudFrame, int n, ItemStack itemStack) {
        this.i = armorStatusHudFrame;
        this.O = n;
        this.b = itemStack;
    }

    @Override
    public void I() {
        this.H();
    }

    @Override
    public void H() {
        ItemStack itemStack = this.y$src$Lgg_vape_wrapper_impl_ItemStack_$151qkau();
        if (itemStack.isNotNull() || this.Q) {
            this.I.reset();
            this.R = false;
            this.G = this.W() <= 2;
        } else if (this.G) {
            this.R = true;
            this.G = false;
        }
        if (this.R && this.I.hasTimeElapsed(4000L)) {
            this.Z(false);
        }
        if (itemStack.isNull() && !this.R) {
            this.Z(false);
        }
        if (this.a) {
            this.k(itemStack);
        } else {
            try {
                this.z(itemStack);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
    }

    @Override
    public double A() {
        return this.i.A();
    }

    public ItemStack y$src$Lgg_vape_wrapper_impl_ItemStack_$151qkau() {
        if (this.Q) {
            return this.b;
        }
        return this.E();
    }
}

