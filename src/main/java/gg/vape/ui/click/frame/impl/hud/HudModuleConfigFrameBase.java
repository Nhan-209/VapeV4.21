package gg.vape.ui.click.frame.impl.hud;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.input.KeyboardInput;
import gg.vape.module.MinecraftVersionConstraint;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.HudModule;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.component.AnimatedIconButtonComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.value.ValueComponentFactory;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.AnchoredHudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudSnapCandidate;
import gg.vape.ui.click.frame.impl.hud.HudSnapEdge;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.utils.render.BlurRegionRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.Value;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ScaledResolution;
import java.awt.Color;

public abstract class HudModuleConfigFrameBase<T extends HudModule>
extends HudModuleFrameBase {
    private static String hC;
    private double ho = -1.0;
    public final int hG = (int)wb;
    private double hJ = -1.0;
    private BlurRegionRenderer hP = new BlurRegionRenderer(0, 0);
    private final BooleanValue hZ;
    private double hi = -1.0;
    private static final long wb;
    public T hI;
    private double hK = -1.0;

    @Override
    public void Y() {
        if (!this.IU || KeyboardInput.isKeyDown(160)) {
            this.hi = -1.0;
            this.hK = -1.0;
            this.hJ = -1.0;
            this.ho = -1.0;
        }
        if (this.m()) {
            this.hP.L((int)this.A() * 2, (int)this.L() * 2);
            this.hP.t((int)this.G$src$D$1b2f02a(), (int)this.n(), 6.0f, 4.0f);
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.b$src$Ljava_awt_Color_$t24dz2());
        }
        this.s$src$V$1axdubt();
        this.o();
        this.p$src$V$1avqgjq();
        if ((this.w$src$Z$e457mb() || this.p$src$Z$1avqgn6() || this.r$src$Z$1awu1tw()) && ClientSettings.fW != null && ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() instanceof ClickGuiFrameManager) {
            this.Z$src$V$1ajmzhs();
        }
    }

    @Override
    protected void h() {
        this.i(false);
        ((Mod)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0()).Y(false);
        this.Z(false);
    }

    public static String I$src$Ljava_lang_String_$jmqh3t() {
        return hC;
    }

    private HudSnapCandidate[] S(RectData rectData) {
        HudSnapCandidate hudSnapCandidate;
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        HudSnapCandidate hudSnapCandidate2 = null;
        HudSnapCandidate hudSnapCandidate3 = null;
        double d6 = 2.0;
        double d7 = 60.0;
        for (HudSnapEdge hudSnapEdge : HudSnapEdge.L()) {
            for (HudSnapEdge hudSnapEdge2 : HudSnapEdge.L()) {
                d5 = hudSnapEdge.t(rectData) - hudSnapEdge2.t(this.Q());
                if (!(Math.abs(d5) <= d6)) continue;
                d4 = HudSnapEdge.TOP.t(rectData) - HudSnapEdge.TOP.t(this.Q());
                if (d4 > 0.0) {
                    d3 = HudSnapEdge.BOTTOM.t(this.Q());
                    d2 = HudSnapEdge.TOP.t(rectData);
                } else {
                    d3 = HudSnapEdge.BOTTOM.t(rectData);
                    d2 = HudSnapEdge.TOP.t(this.Q());
                }
                d = hudSnapEdge.t(rectData);
                hudSnapCandidate = new HudSnapCandidate(hudSnapEdge.t(rectData), d3, hudSnapEdge2.t(this.Q()), d2, hudSnapEdge, hudSnapEdge2, d);
                if (!(hudSnapCandidate.n() < d7) || hudSnapCandidate2 != null && !(hudSnapCandidate.n() < hudSnapCandidate2.n())) continue;
                hudSnapCandidate2 = hudSnapCandidate;
            }
        }
        for (HudSnapEdge hudSnapEdge : HudSnapEdge.a()) {
            for (HudSnapEdge hudSnapEdge2 : HudSnapEdge.a()) {
                d5 = hudSnapEdge.t(rectData) - hudSnapEdge2.t(this.Q());
                if (!(Math.abs(d5) <= d6)) continue;
                d4 = HudSnapEdge.LEFT.t(rectData) - HudSnapEdge.LEFT.t(this.Q());
                if (d4 > 0.0) {
                    d3 = HudSnapEdge.RIGHT.t(this.Q());
                    d2 = HudSnapEdge.LEFT.t(rectData);
                } else {
                    d3 = HudSnapEdge.RIGHT.t(rectData);
                    d2 = HudSnapEdge.LEFT.t(this.Q());
                }
                d = hudSnapEdge.t(rectData);
                hudSnapCandidate = new HudSnapCandidate(d3, hudSnapEdge.t(rectData), d2, hudSnapEdge2.t(this.Q()), hudSnapEdge, hudSnapEdge2, d);
                if (!(hudSnapCandidate.M() < d7) || hudSnapCandidate3 != null && !(hudSnapCandidate.M() < hudSnapCandidate3.M())) continue;
                hudSnapCandidate3 = hudSnapCandidate;
            }
        }
        return new HudSnapCandidate[]{hudSnapCandidate2, hudSnapCandidate3};
    }

    @Override
    protected void k() {
        AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent> anchoredHudModuleConfigFrame = this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s();
        anchoredHudModuleConfigFrame.G((HudModule)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0());
        anchoredHudModuleConfigFrame.S();
        for (Value<?, ?> value : anchoredHudModuleConfigFrame.s$src$Lgg_vape_module_render_hud_HudModule_$14buku().F$src$Ljava_util_List_$1kytx9u()) {
            GuiComponent guiComponent = ValueComponentFactory.Y(value);
            if (guiComponent == null) continue;
            if (value.getParent() != null) {
                guiComponent.T(HudModuleConfigFrameBase.J.r);
            } else {
                guiComponent.T(HudModuleConfigFrameBase.J.i);
            }
            anchoredHudModuleConfigFrame.h(guiComponent, new Object[0]);
        }
        anchoredHudModuleConfigFrame.Z(true);
        anchoredHudModuleConfigFrame.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().Z(true);
        anchoredHudModuleConfigFrame.t(170.0);
        anchoredHudModuleConfigFrame.l$src$V$1mibm4x();
        if (!ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().Y().contains(anchoredHudModuleConfigFrame)) {
            ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().q(anchoredHudModuleConfigFrame);
        }
    }

    @Override
    protected void d$src$V$hfpqs2() {
        this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s().h();
    }

    public void e(boolean bl) {
        this.hZ.o(bl);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        this.F(guiMouseEvent);
    }

    public HudModuleConfigFrameBase(Class<T> clazz) {
        super(clazz.getName());
        this.hI = (T)Vape.INSTANCE.getModManager().getMod(clazz);
        this.T(HudModuleConfigFrameBase.J.i);
        this.K(30.0);
        this.S(10.0);
        this.N(true);
        this.Z(false);
        this.T(HudModuleConfigFrameBase.J.t);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.hZ = BooleanValue.create(this.hI, "Render background", true);
        ((Mod)this.hI).P(this.hZ, new MinecraftVersionConstraint[0]);
        this.W();
        ClientSettings.t.q(this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s());
        this.H(this.M$src$Lgg_vape_ui_click_component_AnimatedIconButtonCo$12x9cix(), this.d$src$Lgg_vape_ui_click_component_AnimatedIconButtonCo$69zuia());
    }

    public static boolean h$src$Z$1tlh1co() {
        return ClientSettings.I$src$Z$1gg3tgf() || ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() == ClientSettings.f5 && ClientSettings.f5.Y$src$Lgg_vape_ui_click_frame_impl_hud_HudOverlaySelec$z60fv4() != null && ClientSettings.f5.Y$src$Lgg_vape_ui_click_frame_impl_hud_HudOverlaySelec$z60fv4().V$src$Z$1xhop3l();
    }

    @Override
    public boolean l$src$Z$193vdc5() {
        return false;
    }

    private void E(RectData rectData) {
        double d;
        double d2;
        HudSnapCandidate[] hudSnapCandidateArray = this.S(rectData);
        HudSnapCandidate hudSnapCandidate = hudSnapCandidateArray[0];
        HudSnapCandidate hudSnapCandidate2 = hudSnapCandidateArray[1];
        if (hudSnapCandidate == null && hudSnapCandidate2 == null) {
            return;
        }
        MousePosition mousePosition = RenderUtils.h();
        boolean bl = false;
        boolean bl2 = false;
        double d3 = this.G$src$D$1b2f02a();
        double d4 = this.n();
        double d5 = this.A() - 20.0;
        double d6 = 2.0;
        if (hudSnapCandidate != null && hudSnapCandidate.M() <= d6) {
            d3 -= hudSnapCandidate.M();
            bl = true;
            if (this.hi == -1.0) {
                this.hi = mousePosition.O;
            }
        }
        if (hudSnapCandidate2 != null && hudSnapCandidate2.n() <= d6) {
            d4 -= hudSnapCandidate2.n();
            bl2 = true;
            if (this.hK == -1.0) {
                this.hK = mousePosition.H;
            }
        }
        if (bl || bl2) {
            float f;
            if (this.hi != -1.0 && Math.abs(f = (float)((double)mousePosition.O - this.hi)) > 5.0f) {
                bl = false;
                d3 += (double)f;
                this.hi = -1.0;
            }
            if (this.hK != -1.0 && Math.abs(f = (float)((double)mousePosition.H - this.hK)) > 5.0f) {
                bl2 = false;
                d4 += (double)f;
                this.hK = -1.0;
            }
        }
        this.Y(d3, d4);
        if (!bl && (d2 = this.G$src$D$1b2f02a() + this.hJ) != (double)mousePosition.O) {
            if (this.G$src$D$1b2f02a() < 3.0) {
                this.hJ = (double)RenderUtils.h().O - this.G$src$D$1b2f02a();
            } else {
                d3 = Math.abs((double)mousePosition.O - this.hJ);
            }
        }
        if (!bl2 && (d = this.n() + this.ho) != (double)mousePosition.H) {
            if (this.n() < 4.0) {
                this.ho = (double)RenderUtils.h().H - this.n();
            } else {
                d4 = Math.abs((double)mousePosition.H - this.ho);
            }
        }
        if (!bl || !bl2) {
            this.Y(d3, d4);
        }
        block4: for (HudSnapCandidate hudSnapCandidate3 : hudSnapCandidateArray) {
            if (hudSnapCandidate3 == null) continue;
            double d7 = hudSnapCandidate3.m().get((Object)HudSnapEdge.LEFT);
            double d8 = hudSnapCandidate3.m().get((Object)HudSnapEdge.TOP);
            double d9 = hudSnapCandidate3.m().get((Object)HudSnapEdge.RIGHT);
            double d10 = hudSnapCandidate3.m().get((Object)HudSnapEdge.BOTTOM);
            switch (hudSnapCandidate3.n$src$Lgg_vape_ui_click_frame_impl_hud_HudSnapEdge_$8n1nf9()) {
                case RIGHT: 
                case LEFT: 
                case VERTICAL_CENTRE: {
                    if (Math.abs(hudSnapCandidate3.n()) < 0.5) continue block4;
                    d7 = d9;
                }
                case BOTTOM: 
                case TOP: 
                case HORIZONTAL_CENTRE: {
                    if (Math.abs(hudSnapCandidate3.M()) < 0.5) continue block4;
                    d8 = d10;
                }
                default: {
                    GuiRenderPrimitives.u(d7, d8, d9, d10, 1.5f, Color.GREEN);
                }
            }
        }
    }

    public boolean m() {
        return this.hZ.L() != false && this.L() > 0.0;
    }

    @Override
    public double C() {
        return 30.0;
    }

    static {
        HudModuleConfigFrameBase.s("MkGgib");
        wb = 4057282779342176273L;
    }

    public AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent> Z$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$1jkbe02() {
        return this.r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s();
    }

    @Override
    public RectData Q() {
        return this.i$src$Lfunc_skidline_RectData_$1ykrzel();
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        super.D(guiMouseEvent);
        this.g(guiMouseEvent);
    }

    private void Q$src$V$1t8trlx() {
        if (!this.p$src$Z$1avqgn6()) {
            return;
        }
        if (this.hJ == -1.0 || this.ho == -1.0) {
            this.hJ = (double)RenderUtils.h().O - this.G$src$D$1b2f02a();
            this.ho = (double)RenderUtils.h().H - this.n();
        }
        for (Frame frame : ClientSettings.G()) {
            if (!(frame instanceof HudModuleConfigFrameBase) || !frame.V$src$Z$1xhop3l() || frame.equals(this)) continue;
            RectData rectData = frame.Q();
            RectData rectData2 = new RectData(rectData.o() - 2.0, rectData.W() - 2.0, rectData.e() + 4.0, rectData.R() + 4.0);
            this.E(rectData2);
        }
        ScaledResolution scaledResolution = Minecraft.G();
        this.E(new RectData((double)scaledResolution.T() / 2.0, 0.0, 20.0, scaledResolution.G()));
    }

    public boolean D$src$Z$1t1ofzo() {
        return this.hZ.L();
    }

    @Override
    public void v() {
        if (this.m()) {
            this.hP.L((int)this.A() * 2, (int)this.L() * 2);
            this.hP.t((int)this.G$src$D$1b2f02a(), (int)this.n(), 6.0f, 4.0f);
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.b$src$Ljava_awt_Color_$t24dz2());
        }
        if (this.p$src$Z$1avqgn6()) {
            this.i(false);
        }
        this.o();
    }

    public static void s(String string) {
        hC = string;
    }

    public void M(boolean bl) {
        this.hZ.H$src$V$8t5pov(!bl);
    }

    public static void w$src$V$1ttpy5n() {
        for (Frame frame : ClientSettings.G()) {
            if (frame instanceof HudModuleConfigFrameBase) {
                HudModuleConfigFrameBase hudModuleConfigFrameBase = (HudModuleConfigFrameBase)frame;
                hudModuleConfigFrameBase.i(false);
            }
            if (!(frame instanceof HudSettingsFrameBase)) continue;
            ((HudSettingsFrameBase)frame).i(false);
        }
    }


    @Override
    public String c$src$Ljava_lang_String_$14h9h1a() {
        if (this.hI != null && ((Mod)this.hI).getName() != null && !((Mod)this.hI).getName().isEmpty()) {
            return ((Mod)this.hI).getName();
        }
        return super.c$src$Ljava_lang_String_$14h9h1a();
    }

    public abstract void o();

    public T l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0() {
        return this.hI;
    }

    @Override
    public double x() {
        return 50.0;
    }
}
