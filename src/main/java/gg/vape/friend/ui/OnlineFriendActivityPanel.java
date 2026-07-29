package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.OnlineFriendActivityState;
import gg.vape.friend.OnlineFriendColorUtil;
import gg.vape.friend.activity.ActivityItemStack;
import gg.vape.friend.ui.OnlineActivityHeldItemSlotComponent;
import gg.vape.friend.ui.OnlineActivityPanelOptions;
import gg.vape.friend.ui.OnlineActivitySettingsFrame;
import gg.vape.module.Mod;
import gg.vape.ui.click.component.AnimatedPanelComponent;
import gg.vape.ui.click.component.ItemStackSlotComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextLabelComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.EnchantmentUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RemoteImageTextureManager;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class OnlineFriendActivityPanel
extends AnimatedPanelComponent {
    private float p_ = 0.0f;
    int ps = 0;
    TimerUtil pg;
    TimerUtil pk;
    private OnlineActivityHeldItemSlotComponent pA;
    private static final List<Integer> pQ = Arrays.asList(1, 2, 3, 0);
    private boolean pW = false;
    private ItemStackSlotComponent[] pb;
    private final TextLabelComponent pw;
    private EntityPlayer p6 = null;
    private final TextLabelComponent p5;
    private ArrayList<ItemStackSlotComponent> pn;
    private PanelComponent py;
    float p3;
    private final OnlineActivityPanelOptions pP;
    private PanelComponent pZ;
    @NotNull
    private final OnlineFriendActivityState pV;
    private final Color pv = new Color(-2130728448);
    private boolean pm;

    private void l(boolean bl) {
        boolean bl2;
        OnlineActivityHeldItemSlotComponent onlineActivityHeldItemSlotComponent;
        int n;
        block9: {
            block8: {
                OnlineActivityHeldItemSlotComponent onlineActivityHeldItemSlotComponent2;
                block7: {
                    OnlineActivityHeldItemSlotComponent onlineActivityHeldItemSlotComponent3;
                    ItemStack itemStack;
                    OnlineActivityHeldItemSlotComponent onlineActivityHeldItemSlotComponent4;
                    OnlineActivityHeldItemSlotComponent onlineActivityHeldItemSlotComponent5;
                    this.pZ.setShowDisabledOverlay(false);
                    this.pZ.setDisabledOverlayColor(OnlineFriendActivityPanel.J.d);
                    this.n$src$V$naoy1s();
                    float f = this.c$src$F$n4n751();
                    n = (int)(f * 255.0f);
                    boolean bl3 = this.a$src$Z$n3jmfj();
                    if (bl3) {
                        ActivityItemStack activityItemStack = this.pV.N$src$ALgg_vape_friend_activity_ActivityItemStack_$1nvfl9h()[this.pV.N()];
                        ItemStack itemStack2 = this.p6 != null && this.p6.isNotNull() ? (this.p6.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().isNotNull() ? this.p6.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt() : null) : null;
                        OnlineActivityHeldItemSlotComponent onlineActivityHeldItemSlotComponent6 = this.pA;
                        onlineActivityHeldItemSlotComponent6.setItemStack(itemStack2);
                        OnlineActivityHeldItemSlotComponent onlineActivityHeldItemSlotComponent7 = this.pA;
                        onlineActivityHeldItemSlotComponent7.setSelected(itemStack2 != null && EnchantmentUtil.A(itemStack2).size() > 0);
                        this.pA.X(n);
                        this.pA.setShowDisabledOverlay(bl);
                        for (int i = 0; i < this.pb.length; ++i) {
                            ItemStackSlotComponent itemStackSlotComponent = this.pb[i];
                            ItemStack itemStack3 = new ItemStack(Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().i()[3 - i]);
                            itemStackSlotComponent.setItemStack(itemStack3.isNotNull() ? itemStack3 : null);
                            itemStackSlotComponent.setSelected(itemStack3.isNotNull() && EnchantmentUtil.A(itemStack3).size() > 0);
                            itemStackSlotComponent.setShowDisabledOverlay(bl);
                        }
                        return;
                    }
                    ActivityItemStack activityItemStack = this.pV.N$src$ALgg_vape_friend_activity_ActivityItemStack_$1nvfl9h()[this.pV.N()];
                    ItemStack itemStack4 = this.p6 != null && this.p6.isNotNull() ? (this.p6.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().isNotNull() ? this.p6.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt() : null) : null;
                    OnlineActivityHeldItemSlotComponent onlineActivityHeldItemSlotComponent8 = onlineActivityHeldItemSlotComponent5 = this.pA;
                    if (activityItemStack != null) {
                        onlineActivityHeldItemSlotComponent4 = onlineActivityHeldItemSlotComponent8;
                        itemStack = activityItemStack.T();
                    } else {
                        onlineActivityHeldItemSlotComponent4 = onlineActivityHeldItemSlotComponent8;
                        itemStack = null;
                    }
                    onlineActivityHeldItemSlotComponent4.setItemStack(itemStack);
                    onlineActivityHeldItemSlotComponent2 = onlineActivityHeldItemSlotComponent3 = this.pA;
                    if (activityItemStack == null) break block7;
                    onlineActivityHeldItemSlotComponent = onlineActivityHeldItemSlotComponent2;
                    if (!activityItemStack.O()) break block8;
                    bl2 = true;
                    break block9;
                }
                onlineActivityHeldItemSlotComponent = onlineActivityHeldItemSlotComponent2;
            }
            bl2 = false;
        }
        onlineActivityHeldItemSlotComponent.setSelected(bl2);
        this.pA.X(n);
        this.pA.setShowDisabledOverlay(bl);
        for (int i = 0; i < this.pb.length; ++i) {
            ItemStackSlotComponent itemStackSlotComponent = this.pb[i];
            ActivityItemStack activityItemStack = this.pV.I()[3 - i];
            if (activityItemStack != null && activityItemStack.I() != 0) {
                itemStackSlotComponent.setItemStack(activityItemStack.T());
                itemStackSlotComponent.setSelected(activityItemStack.O());
            } else {
                itemStackSlotComponent.setItemStack(null);
            }
            itemStackSlotComponent.setShowDisabledOverlay(bl);
        }
    }

    public void w(double d, double d2, double d3, double d4) {
        double d5 = d3 / 2.0;
        double d6 = d + d5;
        double d7 = d2 + d5;
        double d8 = Math.toRadians(d4);
        double d9 = Math.toRadians(d4 - 12.0);
        double d10 = Math.toRadians(d4 + 12.0);
        double d11 = d6 + (d5 + 4.0) * Math.sin(d8);
        double d12 = d7 - (d5 + 4.0) * Math.cos(d8);
        double d13 = d6 + (d5 + 2.0) * Math.sin(d9);
        double d14 = d7 - (d5 + 2.0) * Math.cos(d9);
        double d15 = d6 + (d5 + 2.0) * Math.sin(d10);
        double d16 = d7 - (d5 + 2.0) * Math.cos(d10);
        GuiRenderPrimitives.U(d13, d14, d11, d12, d15, d16, OnlineFriendActivityPanel.J.f);
    }

    private double K(double d, double d2, double d3, double d4) {
        double d5 = 0.0;
        double d6 = d3 - d;
        double d7 = d4 - d2;
        if (d7 > 0.0 && d6 > 0.0) {
            d5 = Math.toDegrees(-Math.atan(d6 / d7));
        } else if (d7 > 0.0 && d6 < 0.0) {
            d5 = Math.toDegrees(-Math.atan(d6 / d7));
        } else if (d7 < 0.0 && d6 > 0.0) {
            d5 = -90.0 + Math.toDegrees(Math.atan(d7 / d6));
        } else if (d7 < 0.0 && d6 < 0.0) {
            d5 = 90.0 + Math.toDegrees(Math.atan(d7 / d6));
        }
        double d8 = MathUtil.wrapAngleTo180((float)d5);
        return MathUtil.wrapAngleTo180((float)(d8 -= (double)MathUtil.wrapAngleTo180(Minecraft.thePlayer().J())));
    }

    private void k(double d, double d2, boolean bl) {
        SmoothFontRenderer smoothFontRenderer = this.getFontRenderer(0.75);
        String string = this.pV.L() + "";
        if (bl) {
            // empty if block
        }
        smoothFontRenderer.v(string, d - smoothFontRenderer.N(string) / 2.0, d2 - smoothFontRenderer.d(string) / 2.0, Color.white);
    }

    public OnlineFriendActivityPanel(LocalOnlineFriend localOnlineFriend) {
        this(localOnlineFriend.E());
        this.pm = true;
    }

    public void l(OnlineActivitySettingsFrame onlineActivitySettingsFrame) {
        Entity entity;
        if (this.p6 != null && !this.p6.equals(Minecraft.thePlayer())) {
            entity = Minecraft.theWorld().V(this.p6.S());
            this.p6 = entity.isNotNull() ? new EntityPlayer(entity) : null;
        }
        entity = Minecraft.thePlayer();
        double d = this.pV.v(this.p6);
        double d2 = this.pV.X(this.p6);
        double d3 = this.pV.W(this.p6);
        float f = this.pV.l(this.p6);
        float f2 = this.pV.I(this.p6);
        float f3 = this.pV.F(this.p6);
        if (this.A$src$Z$mly7fz()) {
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), new Color(26, 25, 26, 150));
        }
        double d4 = this.G$src$D$1b2f02a() + 6.0;
        double d5 = this.n() + 10.0;
        double d6 = 22.0;
        GlImageTexture glImageTexture = RemoteImageTextureManager.getInstance().getTexture(this.pV.a().I(), 32);
        if (glImageTexture != null) {
            GuiRenderPrimitives.V((float)(d4 - 0.5), (float)(d5 - 0.5), (float)(d6 + 1.0), 1.0, new Color(50, 50, 50, 255));
            GuiRenderPrimitives.u((float)d4, (float)d5, (float)d6, 1.0f, Color.WHITE, glImageTexture);
        }
        double d7 = d4 + d6 + 6.0;
        this.pw.setText(this.pV != null ? this.pV.a().I() : "N/A");
        double d8 = d5 + 4.0;
        this.pw.renderAt(d7, d8 - this.pw.getTextHeight() / 2.0);
        this.p5.setText(this.pV != null ? this.pV.a().C() : "N/A");
        this.p5.renderAt(d7, d8 + 10.0 - this.p5.getTextHeight() / 2.0);
        double d9 = d5 + 18.0;
        this.O(d7, d9, f, f2, f3);
        this.b$src$V$n43exg();
        this.u(f);
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() - 8.0, this.n() + 4.0, 4.0, 1.0, new Color(0, 0, 0, 255));
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() - 8.0, this.n() + 4.0, 4.0, 1.0, OnlineFriendColorUtil.f(this.pV.a().d()));
        if (!this.pm) {
            this.w(d4, d5, d6, this.C(Minecraft.thePlayer(), d, d3));
            int n = (int)RotationUtil.y(entity.c(), entity.A(), entity.Z(), d, d2, d3);
            String string = n > 1000000000 ? "very far away" : n + "m";
            SmoothFontRenderer smoothFontRenderer = this.h(n);
            double d10 = Math.max(d4 + d6 / 2.0 - smoothFontRenderer.N(string) / 2.0, this.G$src$D$1b2f02a() + 2.0);
            double d11 = d5 + d6 + 6.0 + (this.n() + this.L() - 2.0 - (d5 + 4.0 + d6 + 4.0) - smoothFontRenderer.d(string)) / 2.0;
            smoothFontRenderer.v(string, d10, d11, OnlineFriendActivityPanel.J.A);
        }
    }

    private void h() {
        block8: {
            boolean bl = this.a$src$Z$n3jmfj();
            if (!bl) break block8;
            int n = 0;
            for (int n2 : pQ) {
                for (int i = 0; i < 9; ++i) {
                    boolean bl2;
                    ItemStackSlotComponent itemStackSlotComponent;
                    block11: {
                        block10: {
                            ItemStackSlotComponent itemStackSlotComponent2;
                            block9: {
                                ItemStackSlotComponent itemStackSlotComponent3;
                                ItemStack itemStack;
                                ItemStackSlotComponent itemStackSlotComponent4;
                                ItemStackSlotComponent itemStackSlotComponent5;
                                int n3 = n2 * 9 + i;
                                ActivityItemStack activityItemStack = this.pV.N$src$ALgg_vape_friend_activity_ActivityItemStack_$1nvfl9h()[n3];
                                ItemStack itemStack2 = this.p6 != null && this.p6.isNotNull() ? this.p6.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n3) : null;
                                ItemStackSlotComponent itemStackSlotComponent6 = this.pn.get(n);
                                boolean bl3 = itemStack2 != null && itemStack2.isNotNull();
                                ItemStackSlotComponent itemStackSlotComponent7 = itemStackSlotComponent5 = itemStackSlotComponent6;
                                if (bl3) {
                                    itemStackSlotComponent4 = itemStackSlotComponent7;
                                    itemStack = itemStack2;
                                } else {
                                    itemStackSlotComponent4 = itemStackSlotComponent7;
                                    itemStack = null;
                                }
                                itemStackSlotComponent4.setItemStack(itemStack);
                                itemStackSlotComponent2 = itemStackSlotComponent3 = itemStackSlotComponent6;
                                if (!bl3) break block9;
                                itemStackSlotComponent = itemStackSlotComponent2;
                                if (EnchantmentUtil.A(itemStack2).size() <= 0) break block10;
                                bl2 = true;
                                break block11;
                            }
                            itemStackSlotComponent = itemStackSlotComponent2;
                        }
                        bl2 = false;
                    }
                    itemStackSlotComponent.setSelected(bl2);
                    ++n;
                }
            }
            return;
        }
        int n = 0;
        for (int n4 : pQ) {
            for (int i = 0; i < 9; ++i) {
                boolean bl;
                ItemStackSlotComponent itemStackSlotComponent;
                block14: {
                    block13: {
                        ItemStackSlotComponent itemStackSlotComponent8;
                        block12: {
                            ItemStackSlotComponent itemStackSlotComponent9;
                            ItemStack itemStack;
                            ItemStackSlotComponent itemStackSlotComponent10;
                            ItemStackSlotComponent itemStackSlotComponent11;
                            int n5 = n4 * 9 + i;
                            ActivityItemStack activityItemStack = this.pV.N$src$ALgg_vape_friend_activity_ActivityItemStack_$1nvfl9h()[n5];
                            ItemStack itemStack3 = this.p6 != null && this.p6.isNotNull() ? this.p6.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n5) : null;
                            ItemStackSlotComponent itemStackSlotComponent12 = this.pn.get(n);
                            boolean bl4 = itemStack3 != null && itemStack3.isNotNull();
                            ItemStackSlotComponent itemStackSlotComponent13 = itemStackSlotComponent11 = itemStackSlotComponent12;
                            if (activityItemStack != null) {
                                itemStackSlotComponent10 = itemStackSlotComponent13;
                                itemStack = activityItemStack.T();
                            } else {
                                itemStackSlotComponent10 = itemStackSlotComponent13;
                                itemStack = null;
                            }
                            itemStackSlotComponent10.setItemStack(itemStack);
                            itemStackSlotComponent8 = itemStackSlotComponent9 = itemStackSlotComponent12;
                            if (activityItemStack == null) break block12;
                            itemStackSlotComponent = itemStackSlotComponent8;
                            if (!activityItemStack.O()) break block13;
                            bl = true;
                            break block14;
                        }
                        itemStackSlotComponent = itemStackSlotComponent8;
                    }
                    bl = false;
                }
                itemStackSlotComponent.setSelected(bl);
                ++n;
            }
        }
    }

    private boolean A$src$Z$mly7fz() {
        return this.pP.P().getEffectiveValue();
    }

    public OnlineFriendActivityState y$src$Lgg_vape_friend_OnlineFriendActivityState_$6vxj8m() {
        return this.pV;
    }

    private boolean P$src$Z$mu74ce() {
        return this.pP.i().getEffectiveValue();
    }


    public void U(boolean bl) {
        if (bl == this.pW) {
            if (this.pW) {
                this.h();
            }
            return;
        }
        this.pW = bl;
        if (bl) {
            this.h();
            this.pZ.setVisible(false);
            this.py.setVisible(true);
        } else {
            this.py.setVisible(false);
            this.pZ.setVisible(true);
            this.pZ.l$src$V$1mibm4x();
        }
    }

    private SmoothFontRenderer h(double d) {
        int n = (d + "m").length();
        if (n < 10) {
            return this.getFontRenderer(0.8);
        }
        return this.getFontRenderer(0.7);
    }

    private void n$src$V$naoy1s() {
        boolean bl;
        int n = this.pV.L();
        int n2 = this.pV.f(this.p6);
        boolean bl2 = bl = this.p6 == null || n > 4 && n2 != 0;
        if (bl) {
            if (n != 0 && this.pk.hasTimeElapsed(1000 / n)) {
                this.pk.reset();
                ++this.ps;
            }
        } else if (n2 == 1 && this.pV.j() > 1) {
            this.pk.reset();
            ++this.ps;
        }
    }

    private double C(Entity entity, double d, double d2) {
        return this.K(entity.c(), entity.Z(), d, d2);
    }

    private void e$src$V$n5qspj() {
        this.pn.clear();
        this.py.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.py.setShowDisabledOverlay(false);
        this.py.t$src$V$zbu1jn();
        this.py.h(new SpacerComponent(110.0, 1.5), new Object[0]);
        PanelComponent panelComponent = new PanelComponent(110.0, 6.0);
        panelComponent.addChildren(new SpacerComponent(110.0, 1.0), new SpacerComponent(93.0, 6.0));
        this.py.h(panelComponent, new Object[0]);
        panelComponent.setShowDisabledOverlay(false);
        for (int n : pQ) {
            PanelComponent panelComponent2 = new PanelComponent(110.0, 11.0);
            panelComponent2.setShowDisabledOverlay(false);
            panelComponent2.h(new SpacerComponent(8.0, 10.0), new Object[0]);
            for (int i = 0; i < 9; ++i) {
                ItemStackSlotComponent itemStackSlotComponent = new ItemStackSlotComponent(10.0, 10.0, 8);
                ActivityItemStack activityItemStack = this.pV.N$src$ALgg_vape_friend_activity_ActivityItemStack_$1nvfl9h()[n * 9 + i];
                itemStackSlotComponent.setItemStack(activityItemStack != null ? activityItemStack.T() : null);
                this.pn.add(itemStackSlotComponent);
                if (i != 0) {
                    panelComponent2.h(new SpacerComponent(1.0, 11.0), new Object[0]);
                }
                panelComponent2.h(itemStackSlotComponent, new Object[0]);
            }
            this.py.h(panelComponent2, new Object[0]);
        }
    }

    public OnlineFriendActivityPanel(@NotNull OnlineFriendActivityState onlineFriendActivityState) {
        super(114.0, 52.0);
        this.pA = new OnlineActivityHeldItemSlotComponent();
        this.pb = new ItemStackSlotComponent[]{new ItemStackSlotComponent(), new ItemStackSlotComponent(), new ItemStackSlotComponent(), new ItemStackSlotComponent()};
        this.pn = new ArrayList();
        this.py = new PanelComponent(110.0, 45.0);
        this.pZ = new PanelComponent(110.0, 58.0);
        this.pk = new TimerUtil();
        this.pg = new TimerUtil();
        this.pV = onlineFriendActivityState;
        this.setShowDisabledOverlay(false);
        this.pw = new TextLabelComponent(onlineFriendActivityState.a().I(), 0.7, 1.0, 0.1, 74.0, false, true, Color.white);
        this.p5 = new TextLabelComponent(onlineFriendActivityState.a().C(), 0.6, 0.9, 0.1, 74.0, false, true, OnlineFriendActivityPanel.J.A);
        this.pZ.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.pZ.addChildren(new SpacerComponent(110.0, 36.0));
        PanelComponent panelComponent = new PanelComponent(110.0, 23.0);
        panelComponent.setShowDisabledOverlay(false);
        panelComponent.h(new SpacerComponent(34.0, 1.0), new Object[0]);
        panelComponent.h(this.pA, new Object[0]);
        for (ItemStackSlotComponent itemStackSlotComponent : this.pb) {
            panelComponent.addChildren(new SpacerComponent(1.0, 0.0), itemStackSlotComponent);
        }
        this.pZ.h(panelComponent, new Object[0]);
        this.pZ.setShowDisabledOverlay(false);
        this.h(this.pZ, new Object[0]);
        this.py.setShowDisabledOverlay(false);
        this.e$src$V$n5qspj();
        this.py.setVisible(false);
        this.h(this.py, new Object[0]);
        this.pP = OnlineActivityPanelOptions.p;
    }

    @Override
    public void u() {
        super.u();
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        this.l(this.A$src$Z$mly7fz());
    }

    private void O(double d, double d2, float f, float f2, float f3) {
        float f4;
        float f5;
        float f6 = 0.5f;
        if (this.p_ < f) {
            f5 = this.p_ / f;
            f4 = 1.0f - f5;
            this.p_ += f6 * f4;
        }
        if (this.p_ > f) {
            f5 = f / this.p_;
            if (this.p_ == 0.0f) {
                f5 = 0.0f;
            }
            f4 = 1.0f - f5;
            this.p_ -= f6 * f4;
        }
        if (Float.isNaN(this.p_) || !Float.isFinite(this.p_)) {
            this.p_ = f;
        }
        f = Math.max(f, 0.0f);
        double d3 = d;
        double d4 = d2;
        double d5 = 75.0;
        double d6 = 2.0;
        float f7 = 0.6f;
        float f8 = f / Math.max(f2, 1.0f);
        float f9 = f / Math.max(f2, 1.0f);
        GuiRenderPrimitives.I(d3, d4, d5, d6, new Color(54, 54, 54, 255), true, f7, 1.0f, 4.0f, new Color(0, 0, 0, 152));
        if (this.p6 == null) {
            f9 = 1.0f;
        }
        Color color = f > 0.0f ? RenderUtils.q(f9, true) : Color.RED;
        GuiRenderPrimitives.e(d3, d4, Math.min(d5 * (double)f8, d5), d6, color, false, f7, 1.0f);
        if (f3 > 0.0f) {
            f3 = Math.min(10.0f, f3);
            double d7 = Math.max(d3, d3 + d5 * (double)f8 - 2.0);
            double d8 = d3 + d5;
            double d9 = d3 + d5 * (double)f8;
            double d10 = 10.0f * (f3 / 2.0f);
            double d11 = d8 - (d9 - 2.0 + d10);
            if (d11 < 0.0) {
                d7 -= Math.abs(d11);
            }
            GuiRenderPrimitives.e(d7, d4, d10, d6, this.pv, true, f7, 1.0f);
        }
    }

    private void u(float f) {
        if (f <= 0.0f) {
            GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + 5.0, this.n() + 7.0, 24.0, 1.0, new Color(0, 0, 0, 200));
            ImageRenderer.drawImage(new Color(197, 49, 49, 255), (float)this.G$src$D$1b2f02a() + 5.0f + 12.0f - 4.0f, (float)this.n() + 9.0f + 13.0f - 6.0f, "newblatant", 8.0f, 8.0f, true);
        }
    }

    @Override
    public void c() {
        OnlineActivitySettingsFrame onlineActivitySettingsFrame = (OnlineActivitySettingsFrame)this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa();
        this.l$src$V$1mibm4x();
        if (this.pW) {
            ItemStackSlotComponent itemStackSlotComponent = this.Y(onlineActivitySettingsFrame);
            super.c();
            GuiRenderPrimitives.P(itemStackSlotComponent.G$src$D$1b2f02a(), itemStackSlotComponent.n(), itemStackSlotComponent.A(), itemStackSlotComponent.L(), Color.white, 1.6f, 0.8f, 1.0f);
            return;
        }
        this.l(onlineActivitySettingsFrame);
        super.c();
        if (this.P$src$Z$mu74ce()) {
            this.k(this.G$src$D$1b2f02a() + 30.0, this.n() + this.L() - 10.0, this.A$src$Z$mly7fz());
        }
        if (this.pg.hasTimeElapsed(50L)) {
            this.pg.reset();
        }
        this.c$src$F$n4n751();
    }

    public void s$src$V$ndfx0l() {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        if (this.pV.a() instanceof LocalOnlineFriend) {
            this.p6 = Minecraft.thePlayer();
            return;
        }
        if (this.p6 != null && ((World)worldClient).V(this.p6.S()).isNull()) {
            this.p6 = null;
        }
        if (this.p6 != null) {
            return;
        }
        for (Object e : worldClient.X()) {
            EntityPlayer entityPlayer = new EntityPlayer(e);
            if (!entityPlayer.getName().equalsIgnoreCase(this.pV.a().I())) continue;
            this.p6 = entityPlayer;
            break;
        }
    }

    private void b$src$V$n43exg() {
        int n = this.pV.e(this.p6);
        if (n > 0) {
            double d = (double)n / 20.0;
            int n2 = (int)(255.0 * d);
            GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + 6.0, this.n() + 10.0, 22.0, 1.0, new Color(255, 0, 0, n2));
        }
    }

    private boolean a$src$Z$n3jmfj() {
        List<OnlineFriendActivityState> list = this.pP.D();
        return list.size() == 0;
    }

    private void O(double d, double d2) {
        if (!this.pV.Q()) {
            return;
        }
        GlImageTexture glImageTexture = RemoteImageTextureManager.getInstance().getTexture(this.pV.m() + "", 32);
        if (glImageTexture != null) {
            GuiRenderPrimitives.V((float)(d - 0.5), (float)(d2 - 0.5), 11.0, 1.0, OnlineFriendActivityPanel.J.d);
            GuiRenderPrimitives.u((float)d, (float)d2, 10.0f, 1.0f, Color.WHITE, glImageTexture);
        }
    }

    private ItemStackSlotComponent Y(OnlineActivitySettingsFrame onlineActivitySettingsFrame) {
        boolean bl = this.a$src$Z$n3jmfj();
        this.py.K(this.G$src$D$1b2f02a());
        this.py.S(this.n());
        this.py.l$src$V$1mibm4x();
        String string = "";
        for (Mod object : Vape.INSTANCE.getModManager().collectMods()) {
            if (!object.r$src$Z$14eylz9() || object.h() == 0) continue;
            string = string + object.getName() + "\n";
        }
        for (ItemStackSlotComponent itemStackSlotComponent : this.pn) {
            itemStackSlotComponent.setDisabledOverlayColor(this.A$src$Z$mly7fz() ? OnlineFriendActivityPanel.J.i : new Color(26, 25, 26, 150));
        }
        if (this.A$src$Z$mly7fz()) {
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), new Color(26, 25, 26, 150));
        }
        SmoothFontRenderer smoothFontRenderer = this.getFontRenderer(0.75);
        smoothFontRenderer.v(this.pV.a().I(), this.G$src$D$1b2f02a() + 8.0, this.n() + 4.0 - smoothFontRenderer.d(this.pV.a().I()) / 2.0, this.A$src$Z$mly7fz() ? OnlineFriendActivityPanel.J.A : Color.white);
        return this.pn.get(bl ? this.p6.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() + 27 : this.pV.N() + 27);
    }

    private float c$src$F$n4n751() {
        if (this.p3 > 0.0f) {
            this.p3 = (float)this.pg.getLastMS() / 50.0f;
            this.p3 = Math.max(this.p3, 0.0f);
        }
        if (this.ps > 0 && this.p3 <= 0.0f) {
            this.p3 = 1.0f;
            --this.ps;
        }
        return this.p3;
    }

    public EntityPlayer D$src$Lgg_vape_wrapper_impl_EntityPlayer_$1f3pcbg() {
        return this.p6;
    }
}

