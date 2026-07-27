package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.input.KeyboardInput;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.KeystrokesHudModule;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.click.frame.impl.hud.KeystrokesCpsCounterComponent;
import gg.vape.ui.click.frame.impl.hud.KeystrokesKeyComponent;
import gg.vape.ui.click.frame.impl.hud.KeystrokesMouseButtonComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.util.ArrayList;

public class KeystrokesHudFrame
extends HudModuleConfigFrameBase {
    private boolean o6;
    private final KeystrokesKeyComponent ow;
    private ArrayList<KeystrokesKeyComponent> o0;
    private boolean oj = false;
    private long op;
    public KeystrokesCpsCounterComponent oK;
    private GameSettings oz = Minecraft.gameSettings();
    private final KeystrokesKeyComponent oA;
    private KeystrokesHudModule os;
    private final KeystrokesKeyComponent o2;
    private final KeystrokesKeyComponent oQ;
    private final KeystrokesKeyComponent oy;
    private final KeystrokesKeyComponent oY;
    private final KeystrokesKeyComponent ot;

    @Override
    public String getName() {
        return "KeystrokesFrame";
    }

    private void p(double d, double d2) {
        ImageRenderer.drawResWithShadow(this.l(this.o2.j().getInterpolatedColor()), (float)d, (float)d2, "lmb", 0.75f, false);
        ImageRenderer.drawResWithShadow(this.l(this.oQ.j().getInterpolatedColor()), (float)(d + 20.0), (float)d2, "rmb", 0.75f, false);
        ImageRenderer.drawResWithShadow(this.l(new Color(225, 225, 225)), (float)(d + 21.5), (float)(d2 + 7.0), "mmb", 0.216f, false);
    }

    private boolean L$src$Z$bmylp1() {
        if (this.os == null) {
            return false;
        }
        return this.os.P.L();
    }

    public void WH() {
        this.G(this.o2.p.getKeyCode(), true, false);
        this.o6 = true;
        this.op = System.currentTimeMillis() + 25L;
        this.oK.O(0);
    }

    private void M(KeystrokesKeyComponent keystrokesKeyComponent, double d, double d2, double d3, double d4, String string) {
        KeyBinding keyBinding = keystrokesKeyComponent.p;
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().W(0.9, false);
        GuiRenderPrimitives.I(d, d2 - 0.5, d3, d4 + 0.5, this.l(keystrokesKeyComponent.j().getInterpolatedColor()), !keystrokesKeyComponent.R(), 1.0f, 1.0f, 3.0f, KeystrokesHudFrame.J.u);
        if (keystrokesKeyComponent.equals(this.oA)) {
            double d5 = 30.0;
            GuiRenderPrimitives.C(d + d3 / 2.0 - d5 / 2.0, d2 + 2.0, d5, 1.5, this.R(keystrokesKeyComponent.D().getInterpolatedColor(), 10));
        }
        if (string != null) {
            float f = 4.4f;
            ImageRenderer.E(this.l(keystrokesKeyComponent.D().getInterpolatedColor()), (float)(d + d3 / 2.5 - 4.0), (float)d2 + 2.0f, string, f, f, false);
        } else {
            String string2 = keystrokesKeyComponent == this.o2 ? "LMB" : (keystrokesKeyComponent == this.oQ ? "RMB" : (keystrokesKeyComponent == this.oA ? "" : KeyboardInput.getKeyName(keyBinding.getKeyCode())));
            smoothFontRenderer.d(string2, d + d3 / 2.5 - smoothFontRenderer.N(string2) / 2.0, d2 + 3.0, this.l(keystrokesKeyComponent.D().getInterpolatedColor()));
        }
    }

    public void e() {
        for (KeystrokesKeyComponent keystrokesKeyComponent : this.o0) {
            this.o(keystrokesKeyComponent.p, keystrokesKeyComponent.p.isKeyDown());
        }
    }

    private boolean R$src$Z$bq9d97() {
        if (this.os == null) {
            return false;
        }
        return this.os.J.K() == this.os.O;
    }

    public void Wx() {
        for (KeystrokesKeyComponent keystrokesKeyComponent : this.o0) {
            this.o(keystrokesKeyComponent.p, false);
        }
    }

    public void o(KeyBinding keyBinding, boolean bl) {
        this.G(keyBinding.getKeyCode(), bl, false);
    }

    public void T(EventKeyPress eventKeyPress) {
        this.G(eventKeyPress.getKey(), eventKeyPress.isDown(), ForgeVersion.MC_1_16_5.v());
    }

    @Override
    public double L() {
        if (this.os != null && this.os.c.L().booleanValue()) {
            return 20.0;
        }
        double d = 0.0;
        if (this.B$src$Z$bhgnrf()) {
            d += 40.0;
            if (this.L$src$Z$bmylp1()) {
                d += 14.0;
            }
        } else {
            d += 72.0;
            if (this.L$src$Z$bmylp1()) {
                d += 14.0;
            }
        }
        return d;
    }

    @Override
    public boolean m() {
        return this.U$src$Z$brwr1a() && this.os.c.L() == false;
    }

    public boolean U$src$Z$brwr1a() {
        return super.m();
    }

    private boolean B$src$Z$bhgnrf() {
        if (this.os == null) {
            return false;
        }
        return this.os.t.K() == this.os.K;
    }

    @Override
    public void u() {
        boolean bl;
        this.Wv();
        boolean bl2 = bl = !ClientSettings.fW.v();
        if (this.oj == bl) {
            return;
        }
        if (this.oj) {
            this.Wx();
        } else {
            this.e();
        }
        this.oj = bl;
    }

    public KeystrokesCpsCounterComponent o$src$Lgg_vape_ui_click_frame_impl_hud_KeystrokesCpsCo$1bghhzn() {
        return this.oK;
    }

    private void G(int n, boolean bl, boolean bl2) {
        for (KeystrokesKeyComponent keystrokesKeyComponent : this.o0) {
            int n2 = keystrokesKeyComponent.p.getKeyCode();
            if (n2 < 0) continue;
            if (bl2) {
                n2 = KeyboardCodeUtil.m(n2);
            }
            if (n2 != n) continue;
            if (bl != keystrokesKeyComponent.C) {
                keystrokesKeyComponent.a();
                if (!bl && !keystrokesKeyComponent.R()) {
                    keystrokesKeyComponent.t();
                }
            }
            keystrokesKeyComponent.C = bl;
        }
    }

    private void Wv() {
        if (!this.o6 || System.currentTimeMillis() < this.op) {
            return;
        }
        this.o6 = false;
        if (this.o2.C && !this.o2.p.isKeyDown()) {
            this.G(this.o2.p.getKeyCode(), false, false);
        }
    }

    public KeystrokesHudFrame() {
        super(KeystrokesHudModule.class);
        this.o0 = new ArrayList();
        this.ow = new KeystrokesKeyComponent(this, this.oz.Y());
        this.oY = new KeystrokesKeyComponent(this, this.oz.s());
        this.ot = new KeystrokesKeyComponent(this, this.oz.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg());
        this.oy = new KeystrokesKeyComponent(this, this.oz.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3());
        this.o2 = new KeystrokesMouseButtonComponent(this, this.oz.F());
        this.oQ = new KeystrokesMouseButtonComponent(this, this.oz.b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362());
        this.oA = new KeystrokesMouseButtonComponent(this, this.oz.O());
        this.os = (KeystrokesHudModule)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0();
        this.oK = new KeystrokesCpsCounterComponent(this);
        this.H(this.oK);
        this.o0.add(this.ow);
        this.o0.add(this.oY);
        this.o0.add(this.ot);
        this.o0.add(this.oy);
        this.o0.add(this.o2);
        this.o0.add(this.oQ);
        this.o0.add(this.oA);
    }

    @Override
    public double A() {
        if (this.os != null && this.os.c.L().booleanValue()) {
            return 75.0;
        }
        double d = 54.0;
        if (this.B$src$Z$bhgnrf()) {
            d += 48.0;
        }
        return d;
    }

    public void z(EventMouseButton eventMouseButton) {
        for (KeystrokesKeyComponent keystrokesKeyComponent : this.o0) {
            int n = keystrokesKeyComponent.p.getKeyCode();
            if (ForgeVersion.MC_1_16_5.v()) {
                n += 100;
            }
            if (n != eventMouseButton.getButton()) continue;
            if (eventMouseButton.getButtonState() != keystrokesKeyComponent.C) {
                keystrokesKeyComponent.a();
                if (!eventMouseButton.getButtonState() && !keystrokesKeyComponent.R()) {
                    keystrokesKeyComponent.t();
                }
            }
            keystrokesKeyComponent.C = eventMouseButton.getButtonState();
        }
    }

    @Override
    public void o() {
        double d;
        double d2;
        double d3;
        if (this.os != null && this.os.c.L().booleanValue()) {
            this.M(true);
            this.oK.g(true);
            this.oK.K(this.G$src$D$1b2f02a());
            this.oK.S(this.n());
            this.oK.o(55.0);
            this.oK.Y(10.0);
            return;
        }
        this.M(false);
        this.oK.g(false);
        int n = 17;
        double d4 = d3 = this.B$src$Z$bhgnrf() ? this.n() + 4.0 : this.n() + 2.0;
        if (this.B$src$Z$bhgnrf()) {
            d2 = this.G$src$D$1b2f02a() + 51.0 + 10.0;
            d = d3 - 6.0;
        } else {
            d2 = this.G$src$D$1b2f02a();
            d = d3 + 40.0;
        }
        this.M(this.ow, this.G$src$D$1b2f02a() + 19.0, d3 - 2.0, 17.0, 17.0, this.R$src$Z$bq9d97() ? "up" : null);
        this.M(this.ot, this.G$src$D$1b2f02a(), d3 + 19.0, 17.0, 17.0, this.R$src$Z$bq9d97() ? "left" : null);
        this.M(this.oY, this.G$src$D$1b2f02a() + 19.0, d3 + 19.0, 17.0, 17.0, this.R$src$Z$bq9d97() ? "down" : null);
        this.M(this.oy, this.G$src$D$1b2f02a() + 38.0, d3 + 19.0, 17.0, 17.0, this.R$src$Z$bq9d97() ? "right" : null);
        if (this.L$src$Z$bmylp1()) {
            this.M(this.oA, this.G$src$D$1b2f02a(), d3 + 39.5, 55.25, 11.0, null);
            d += 14.0;
        }
        if (this.B$src$Z$bhgnrf()) {
            this.p(d2 - 2.0, d);
            this.oK.o(40.0);
        } else {
            this.M(this.o2, d2, d, 26.35, 16.0, null);
            this.M(this.oQ, d2 + 26.35 + 2.0, d, 26.35, 16.0, null);
            this.oK.o(55.0);
        }
        double d5 = this.B$src$Z$bhgnrf() ? 22.0 : 17.0;
        this.oK.K(d2);
        this.oK.S(d + d5);
        this.oK.Y(10.0);
        this.o2.h.U(0.01);
    }

}

