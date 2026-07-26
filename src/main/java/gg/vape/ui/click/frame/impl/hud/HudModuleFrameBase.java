package gg.vape.ui.click.frame.impl.hud;

import com.google.gson.JsonObject;
import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.AnimatedIconButtonComponent;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.AnchoredHudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBaseCloseClickHandler;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameCloseClickHandler;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;

public class HudModuleFrameBase
extends Frame {
    private boolean wx = false;
    private boolean wu = false;
    private boolean wV = false;
    private static boolean wF;
    private boolean wY;
    private boolean wS = false;
    private AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent> wO;
    private AnimatedIconButtonComponent wW;
    private boolean w8 = false;
    private boolean wi = false;
    private AnimatedIconButtonComponent w9;

    public void R(boolean bl) {
        this.wi = bl;
    }

    @Override
    public void Y() {
    }

    public Color R(Color color, int n) {
        int n2 = color.getAlpha();
        if (!HudModuleConfigFrameBase.h$src$Z$1tlh1co() && !ClientSettings.fW.P) {
            float f = this.r$src$Z$1awu1tw() ? 0.75f : (float)n / 100.0f;
            n2 = (int)((float)n2 * f);
        }
        return ColorUtil.W(color, n2);
    }

    public double p() {
        return Math.max(26.0, this.L());
    }

    protected void d$src$V$hfpqs2() {
        double d = this.w9.n();
        if (this.w9.G$src$D$1b2f02a() + this.w9.A() + this.wO.A() > (double)Minecraft.G().T()) {
            this.wO.M(this.w9.G$src$D$1b2f02a() - this.wO.A() + 13.0, d);
        } else {
            this.wO.M(this.w9.G$src$D$1b2f02a() + this.w9.A() - 13.0, d);
        }
    }

    public boolean p$src$Z$1avqgn6() {
        return this.wx;
    }

    public void N$src$V$bhucvl() {
        this.wW.Z(false);
        this.w9.Z(false);
        if (this.wO.V$src$Z$1xhop3l()) {
            this.wO.Z(false);
        }
        for (GuiComponent guiComponent : this.wO.f()) {
            if (!(guiComponent instanceof DropdownSelectComponent) || !((DropdownSelectComponent)guiComponent).l$src$Z$1rzrun0()) continue;
            guiComponent.Z(false);
        }
    }

    public double v$src$D$1l3l1d1() {
        return Math.floor((double)Minecraft.h() / Vape.INSTANCE.getClientSettings().s() / 2.0 - this.L() - 2.5);
    }

    public void y(boolean bl) {
        this.wu = bl;
    }

    public void v(boolean bl) {
        this.w8 = bl;
    }

    public double i$src$D$uqmc0b() {
        return Math.floor((double)Minecraft.J() / Vape.INSTANCE.getClientSettings().s() / 2.0 - this.A() - 2.5);
    }

    protected void F(GuiMouseEvent guiMouseEvent) {
        if (!HudModuleConfigFrameBase.h$src$Z$1tlh1co()) {
            return;
        }
        if (guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
            RectData rectData;
            if (!this.p$src$Z$1avqgn6()) {
                HudModuleConfigFrameBase.w$src$V$1ttpy5n();
                this.i(true);
            }
            this.Io = RenderUtils.h();
            double d = this.A();
            if (this.p$src$Z$1avqgn6() && this.wW.V$src$Z$1xhop3l()) {
                d += 2.0 + this.wW.A() + 2.0;
            }
            if ((rectData = new RectData(this.G$src$D$1b2f02a(), this.n(), d, this.p())).J(this.Io.O, this.Io.H)) {
                this.IU = true;
                this.N(false);
            }
        }
    }

    public boolean r$src$Z$1awu1tw() {
        return this.wY;
    }

    public AnimatedIconButtonComponent M$src$Lgg_vape_ui_click_component_AnimatedIconButtonCo$12x9cix() {
        return this.wW;
    }

    public AnimatedIconButtonComponent d$src$Lgg_vape_ui_click_component_AnimatedIconButtonCo$69zuia() {
        return this.w9;
    }

    protected void p$src$V$1avqgjq() {
        if (!this.r$src$Z$1awu1tw() || this.p$src$Z$1avqgn6()) {
            return;
        }
        Color color = new Color(HudModuleFrameBase.J.O.getRed(), HudModuleFrameBase.J.O.getGreen(), HudModuleFrameBase.J.O.getBlue(), 150);
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a() - 1.0, this.n() - 1.0, this.A() + 2.0, this.p() + 2.0, color, 1.5f, 1.0f, 1.0f);
    }

    @Override
    public void v() {
    }

    protected RectData i$src$Lfunc_skidline_RectData_$1ykrzel() {
        RectData rectData = super.Q();
        rectData.A(rectData.e() + 20.0);
        rectData.U(this.p());
        return rectData;
    }

    protected void Z$src$V$1ajmzhs() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.875);
        String string = this.c$src$Ljava_lang_String_$14h9h1a();
        float f = (float)(this.n() - smoothFontRenderer.d(string) - 3.0);
        smoothFontRenderer.T(string, (float)this.G$src$D$1b2f02a(), f, Color.WHITE, new Color(0, 0, 0, 100));
    }

    public void K$src$V$1abe2ld() {
        this.wW.Z(true);
        this.wW.K(this.G$src$D$1b2f02a() + this.A() + 2.0);
        this.wW.S(this.n() - 0.25);
        this.wW.Y(13.0);
        this.wW.o(13.0);
        this.w9.Z(true);
        this.w9.K(this.G$src$D$1b2f02a() + this.A() + 2.0);
        this.w9.S(this.n() + this.wW.L() + 2.0);
        this.w9.Y(13.0);
        this.w9.o(13.0);
        if (this.wW.t()) {
            this.wW.J();
        } else if (this.wW.w$src$Z$e457mb()) {
            this.wW.g$src$V$1x2u3n9();
        }
        if (this.w9.t()) {
            this.w9.J();
        } else if (this.w9.w$src$Z$e457mb()) {
            this.w9.g$src$V$1x2u3n9();
        }
        this.wW.c();
        this.w9.c();
        for (double d = this.G$src$D$1b2f02a() - 2.0; d < this.G$src$D$1b2f02a() + this.A() + 4.0 + this.wW.A(); d += 2.0) {
            GuiRenderPrimitives.a(d, this.n() - 3.5, 1.0, 1.0f, HudModuleFrameBase.J.O);
            GuiRenderPrimitives.a(d, this.n() + this.p() + 3.5, 1.0, 1.0f, HudModuleFrameBase.J.O);
        }
        for (double d = this.n() - 2.0; d < this.n() + this.p() + 4.0; d += 2.0) {
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() - 2.0, d, 1.0, 1.0f, HudModuleFrameBase.J.O);
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + this.A() + 2.0 + this.wW.A() + 2.0, d, 1.0, 1.0f, HudModuleFrameBase.J.O);
        }
    }

    protected void s$src$V$1axdubt() {
        if (this.p$src$Z$1avqgn6()) {
            this.K$src$V$1abe2ld();
            if (this.V$src$Z$1xhop3l() && this.wO.V$src$Z$1xhop3l()) {
                this.d$src$V$hfpqs2();
                this.wO.U();
            }
        } else {
            this.N$src$V$bhucvl();
        }
    }

    public void U(boolean bl) {
        this.wY = bl;
    }

    public boolean u$src$Z$1ayhflz() {
        return this.wV;
    }

    @Override
    public String getName() {
        return null;
    }

    protected void h() {
        this.i(false);
        this.Z(false);
    }

    public void C(boolean bl) {
        this.wV = bl;
    }

    static {
        HudModuleFrameBase.O(false);
    }

    public static boolean I$src$Z$1aaahi3() {
        boolean bl = HudModuleFrameBase.S$src$Z$1afsffp();
        return true;
    }

    @Override
    public void t(boolean bl, boolean bl2) {
        super.t(bl, bl2);
        if (!bl && this.wO != null) {
            this.i(false);
            this.N$src$V$bhucvl();
        }
    }

    public static void O(boolean bl) {
        wF = bl;
    }

    public boolean N$src$Z$1ad1ggw() {
        return ClientSettings.fW != null && ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() instanceof ClickGuiFrameManager;
    }

    @Override
    public void t(JsonObject jsonObject) {
        Double d;
        super.t(jsonObject);
        Double d2 = ConfigJsonUtils.p(jsonObject, "width");
        if (d2 != null) {
            this.o(d2);
        }
        if ((d = ConfigJsonUtils.p(jsonObject, "height")) != null) {
            this.Y(d);
        }
    }

    public Color m$src$Ljava_awt_Color_$ppsp8z() {
        return this.l(Color.WHITE);
    }

    public double r$src$D$1awu1ay() {
        return Math.floor(2.5);
    }

    @Override
    protected void T(double d, double d2) {
        super.T(d, d2);
    }

    public static boolean S$src$Z$1afsffp() {
        return wF;
    }

    public HudModuleFrameBase(String string) {
    }

    public boolean c$src$Z$1aol4xh() {
        return this.wS;
    }

    public Color b$src$Ljava_awt_Color_$t24dz2() {
        int n = 102;
        return this.l(new Color(HudModuleFrameBase.J.i.getRed(), HudModuleFrameBase.J.i.getGreen(), HudModuleFrameBase.J.i.getBlue(), n));
    }

    protected void W() {
        int n = (int)Math.floor(76.5);
        this.wW = new AnimatedIconButtonComponent("newclose", HudModuleFrameBase.J.m);
        this.w9 = new AnimatedIconButtonComponent("settingdots", 0.7, HudModuleFrameBase.J.m);
        this.wW.T(new Color(HudModuleFrameBase.J.m.getRed(), HudModuleFrameBase.J.m.getGreen(), HudModuleFrameBase.J.m.getBlue(), n));
        this.w9.T(new Color(HudModuleFrameBase.J.i.getRed(), HudModuleFrameBase.J.i.getGreen(), HudModuleFrameBase.J.i.getBlue(), n));
        this.wW.Z(false);
        this.w9.Z(false);
        this.wW.r(new HudModuleFrameCloseClickHandler(this));
        this.w9.r(new HudModuleFrameBaseCloseClickHandler(this));
        this.wO = new AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent>(this.w9);
    }

    public Color l(Color color) {
        return this.R(color, 40);
    }

    protected void I(GuiMouseEvent guiMouseEvent) {
        if (this.p$src$Z$1avqgn6()) {
            if (this.wW.V$src$Z$1xhop3l() && this.wW.t()) {
                this.wW.D(guiMouseEvent);
                return;
            }
            if (this.w9.V$src$Z$1xhop3l() && this.w9.t()) {
                this.w9.D(guiMouseEvent);
                return;
            }
        }
        super.D(guiMouseEvent);
        this.g(guiMouseEvent);
    }

    public boolean x$src$Z$1b04te2() {
        return this.wi;
    }

    public double q$src$D$1awa8pl() {
        return this.r$src$D$1awu1ay();
    }

    public String c$src$Ljava_lang_String_$14h9h1a() {
        String string = this.getName();
        if (string != null && !string.isEmpty()) {
            return string;
        }
        return this.getClass().getSimpleName();
    }

    public boolean C$src$Z$1a6zpxx() {
        return this.w8;
    }

    public void i(boolean bl) {
        this.wx = bl;
    }

    protected void k() {
    }

    public AnchoredHudModuleConfigFrame<AnimatedIconButtonComponent> r$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$9c1t7s() {
        return this.wO;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean f$src$Z$1aq8ipk() {
        return this.wu;
    }

    public float r$src$F$35g3yx() {
        if (!HudModuleConfigFrameBase.h$src$Z$1tlh1co() && !ClientSettings.fW.P) {
            return this.r$src$Z$1awu1tw() ? 0.75f : 0.4f;
        }
        return 1.0f;
    }

    @Override
    public JsonObject Z() {
        JsonObject jsonObject = super.Z();
        jsonObject.addProperty("width", (Number)this.A());
        jsonObject.addProperty("height", (Number)this.L());
        return jsonObject;
    }
}

