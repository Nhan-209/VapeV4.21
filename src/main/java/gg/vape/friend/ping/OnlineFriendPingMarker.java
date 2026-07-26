package gg.vape.friend.ping;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendColorUtil;
import gg.vape.friend.ping.PingMarker;
import gg.vape.protocol.packet.PingTargetData;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderMatrix4f;
import java.awt.Color;
import java.util.List;

public class OnlineFriendPingMarker
extends PingMarker {
    private static boolean K;
    private static final String b;
    private DoubleAnimation t = new DoubleAnimation(0.5, 0.0, 1.0);

    public static boolean k() {
        boolean bl = OnlineFriendPingMarker.L();
        return true;
    }

    public void d(double d, double d2, double d3, double d4, double d5, Color color, DoubleAnimation doubleAnimation) {
        double d6 = doubleAnimation.X();
        GuiRenderPrimitives.m((float)(d2 - d4 / 2.0 - d5 - d / 2.0 * d6), (float)(d3 - d4 / 2.0 - d5 - d / 2.0 * d6), (float)(d4 + d5 * 2.0 + d * d6), 2.0f, 1.5f, new Color(26, 25, 26, (int)((1.0 - d6) * 100.0)));
        d5 = 0.0;
        GuiRenderPrimitives.m((float)(d2 - d4 / 2.0 - d5 - d / 2.0 * d6), (float)(d3 - d4 / 2.0 - d5 - d / 2.0 * d6), (float)(d4 + d5 * 2.0 + d * d6), 2.0f, 1.0f, new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((1.0 - d6) * 255.0)));
    }

    @Override
    public void o() {
        super.o();
        this.t.c();
    }

    public static boolean L() {
        return K;
    }

    static {
        OnlineFriendPingMarker.l(false);
        b = "ping_location";
    }

    @Override
    public void d() {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public OnlineFriendPingMarker(OnlineFriend onlineFriend, double[] dArray) {
        super(onlineFriend, dArray);
        this.u(18.0);
        this.Z(18.0);
    }

    @Override
    public PingTargetData T() {
        return PingTargetData.C(this.Z(), this.N(), this.F());
    }

    public static void l(boolean bl) {
        K = bl;
    }

    @Override
    public void K(boolean bl) {
        float f;
        Color color = OnlineFriendColorUtil.u(this.O());
        double d = this.t.X();
        int n = (int)(100.0 + 150.0 * d);
        Color color2 = color;
        Color color3 = color2 = new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), n);
        if (color.equals(OnlineFriendColorUtil.f(-1))) {
            color3 = new Color(0, 0, 0, n);
        }
        double d2 = 0.0;
        double d3 = 0.0;
        double d4 = 1.0;
        double d5 = 5.0;
        double d6 = 2.5;
        if (bl) {
            GuiRenderPrimitives.m(-3.5f, (float)(d3 - 2.5) - 1.0f, 7.0f, 2.0f, 1.0f, Color.WHITE);
            GuiRenderPrimitives.V(-2.5, (float)(d3 - 2.5), 5.0, 1.0, color3);
        } else {
            float f2 = 12.0f;
            float f3 = 0.0f - f2 / 2.0f;
            float f4 = (float)d3 - f2;
            ImageRenderer.E(Color.WHITE, f3, f4, b, f2, f2, false);
            GuiRenderPrimitives.m(-2.5f, (float)((d3 -= 7.7) - 2.5), 5.0f, 2.0f, 1.0f, new Color(0, 0, 0, n));
            GuiRenderPrimitives.V(-2.5, (float)(d3 - 2.5), 5.0, 1.0, color3);
        }
        boolean bl2 = false;
        List<DoubleAnimation> list = this.W();
        for (int i = list.size() - 1; i >= 0; --i) {
            if (!list.get(i).N()) {
                this.d(40.0, 0.0, d3, 5.0, 1.0, color2, list.get(i));
                bl2 = true;
                continue;
            }
            list.remove(i);
        }
        float f5 = f = bl ? 0.0f : 7.7f;
        if (bl2) {
            double d7 = this.t.X();
            float f6 = -45.0f;
            double d8 = Math.min(d7, 0.98);
            double d9 = 500.0 - 500.0 * d8 - 2.0;
            double d10 = 4.0 + (7.0 - d7 * 7.0);
            double d11 = 4.0 - d7 * 4.0 + 1.0;
            for (int i = 0; i < 4; ++i) {
                double d12 = Math.cos(Math.toRadians(f6)) * (d9 + 1.0);
                double d13 = Math.sin(Math.toRadians(f6)) * (d9 + 1.0);
                if (GuiRenderPrimitives.d()) {
                    RenderMatrix4f renderMatrix4f = BufferedGuiRenderPrimitives.X.c();
                    OpenGlBackendHolder.d.m();
                    BufferedGuiRenderPrimitives.X.c().u(renderMatrix4f);
                } else {
                    OpenGlBackendHolder.d.m();
                }
                OpenGlBackendHolder.d.I(0.0 + d12, 0.0 + d13 - (double)f, 0.0);
                OpenGlBackendHolder.d.X(f6, 0.0f, 0.0f, 1.0f);
                GuiRenderPrimitives.B(0.0, 0.0, d10 * d11, 0.5 * d11, new MutableColor(Color.BLACK).withAlpha(255), 1.0f);
                GuiRenderPrimitives.B(0.0, 0.0, d10 * d11, 0.5 * d11, new MutableColor(Color.WHITE).withAlpha(255), 1.0f);
                OpenGlBackendHolder.d.F();
                f6 += 90.0f;
            }
        }
    }
}

