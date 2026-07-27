package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.render.hud.CoordinatesHudModule;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.Biome;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.Chunk;
import gg.vape.wrapper.impl.ChunkWorldBridge;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;

public class CoordinatesHudFrame
extends HudModuleConfigFrameBase {
    private double Fk;
    private static final long xb = -66983186814468096L;
    private CoordinatesHudModule FD = (CoordinatesHudModule)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0();

    private void N(SmoothFontRenderer smoothFontRenderer, String string, double d, double d2, Color color) {
        d = (int)d;
        if (this.m()) {
            smoothFontRenderer.d(string, d, d2, this.l(color));
        } else {
            smoothFontRenderer.T(string, d, d2, color, this.l(new Color((int)xb, true)));
        }
    }

    private void e() {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.7, true);
        SmoothFontRenderer smoothFontRenderer2 = Vape.INSTANCE.getFontManager().K(1.1, true);
        double d = this.L() / 4.0;
        double d2 = 8.0;
        Color color = ColorUtil.W(Color.WHITE, 51);
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int n = (int)Math.round(entityPlayerSP.z());
        int n2 = (int)Math.round(entityPlayerSP.N());
        int n3 = (int)Math.round(entityPlayerSP.h());
        this.N(smoothFontRenderer, "X", this.G$src$D$1b2f02a() + d2, this.n() + d / 2.0, this.l(Color.WHITE));
        this.N(smoothFontRenderer2, String.valueOf(n), this.G$src$D$1b2f02a() + d2 + smoothFontRenderer.N("X") + 2.5, this.n() + d / 2.0 - 2.5, this.l(Color.WHITE));
        this.w(this.G$src$D$1b2f02a() + this.A() - 12.0, this.n() + d / 2.0 + 1.5, this.e$src$Z$15df6vb());
        this.N(smoothFontRenderer, "Y", this.G$src$D$1b2f02a() + d2, this.n() + d + d / 2.0, this.l(Color.WHITE));
        this.N(smoothFontRenderer2, String.valueOf(n2), this.G$src$D$1b2f02a() + d2 + smoothFontRenderer.N("Y") + 2.5, this.n() + d + d / 2.0 - 2.5, this.l(Color.WHITE));
        this.N(smoothFontRenderer, "Z", this.G$src$D$1b2f02a() + d2, this.n() + d * 2.0 + d / 2.0, this.l(Color.WHITE));
        this.N(smoothFontRenderer2, String.valueOf(n3), this.G$src$D$1b2f02a() + d2 + smoothFontRenderer.N("Z") + 2.5, this.n() + d * 2.0 + d / 2.0 - 2.5, this.l(Color.WHITE));
        this.w(this.G$src$D$1b2f02a() + this.A() - 12.0, this.n() + d * 2.0 + d / 2.0, this.U$src$Z$154mhdj());
        GuiRenderPrimitives.a(this.G$src$D$1b2f02a() + d2, this.n() + d + 1.5, this.A() - 15.0, 1.0f, color);
        GuiRenderPrimitives.a(this.G$src$D$1b2f02a() + d2, this.n() + d * 2.0 + 1.5, this.A() - 15.0, 1.0f, color);
        GuiRenderPrimitives.a(this.G$src$D$1b2f02a() + d2, this.n() + d * 3.0 + 1.5, this.A() - 15.0, 1.0f, color);
        this.N(smoothFontRenderer, "BIOME:", this.G$src$D$1b2f02a() + d2, this.n() + d * 3.0 + d / 2.0 - 2.5, this.l(Color.WHITE));
        this.N(smoothFontRenderer, this.F$src$Ljava_lang_String_$1237edm(), this.G$src$D$1b2f02a() + d2 + smoothFontRenderer.N("BIOME:") + 4.0, this.n() + d * 3.0 + d / 2.0 - 2.5, this.l(CoordinatesHudFrame.J.Y));
    }

    private boolean e$src$Z$15df6vb() {
        float f = this.D$src$F$14v9yt6();
        return f > 0.0f && f < 180.0f;
    }

    @Override
    public String getName() {
        return "CoordinateFrame";
    }

    private boolean L$src$Z$14zoc1a() {
        if (this.FD == null) {
            return false;
        }
        return this.FD.p.K() == this.FD.b;
    }

    private String F$src$Ljava_lang_String_$1237edm() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int n = (int)Math.floor(entityPlayerSP.z());
        int n2 = (int)Math.floor(entityPlayerSP.h());
        if (ForgeVersion.MC_1_16_5.d()) {
            return Minecraft.theWorld().Y(BlockPos.create(n, 0, n2)).n();
        }
        Chunk chunk = Minecraft.theWorld().P(n, n2);
        ChunkWorldBridge chunkWorldBridge = Minecraft.theWorld().C();
        if (chunk == null || chunkWorldBridge == null) {
            return "";
        }
        Biome biome = chunk.J(n, n2, Minecraft.theWorld().C());
        return biome.n();
    }

    private void cO() {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.75, true);
        SmoothFontRenderer smoothFontRenderer2 = Vape.INSTANCE.getFontManager().K(1.2, true);
        double d = this.A() / 3.0;
        double d2 = this.L() / 2.0;
        Color color = ColorUtil.W(Color.WHITE, 51);
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int n = (int)Math.round(entityPlayerSP.z());
        int n2 = (int)Math.round(entityPlayerSP.N());
        int n3 = (int)Math.round(entityPlayerSP.h());
        double d3 = 10.0;
        this.N(smoothFontRenderer, "X", this.G$src$D$1b2f02a() + d3, this.n() + d2 / 2.0 + 2.5, this.l(Color.WHITE));
        this.N(smoothFontRenderer2, String.valueOf(n), this.G$src$D$1b2f02a() + (d3 += smoothFontRenderer.N("X") + 2.5), this.n() + d2 / 2.0, this.l(Color.WHITE));
        this.w(this.G$src$D$1b2f02a() + (d3 += Math.max(22.0, 5.0 + smoothFontRenderer2.N("0") * (double)String.valueOf(n).length())), this.n() + d2 / 2.0 + 4.0, this.e$src$Z$15df6vb());
        GuiRenderPrimitives.d((double)((int)(this.G$src$D$1b2f02a() + (d3 += 10.0))), (double)((int)(this.n() + d2 / 2.0)), 8.0, 1.2f, color);
        this.N(smoothFontRenderer, "Y", this.G$src$D$1b2f02a() + (d3 += 10.0), this.n() + d2 / 2.0 + 2.5, this.l(Color.WHITE));
        this.N(smoothFontRenderer2, String.valueOf(n2), this.G$src$D$1b2f02a() + (d3 += smoothFontRenderer.N("Y") + 2.5), this.n() + d2 / 2.0, this.l(Color.WHITE));
        GuiRenderPrimitives.d((double)((int)(this.G$src$D$1b2f02a() + (d3 += Math.max(22.0, 5.0 + smoothFontRenderer2.N("0") * (double)String.valueOf(n2).length())))), (double)((int)(this.n() + d2 / 2.0)), 8.0, 1.2f, color);
        this.N(smoothFontRenderer, "Z", this.G$src$D$1b2f02a() + (d3 += 10.0), this.n() + d2 / 2.0 + 2.5, this.l(Color.WHITE));
        this.N(smoothFontRenderer2, String.valueOf(n3), this.G$src$D$1b2f02a() + (d3 += smoothFontRenderer.N("Z") + 2.5), this.n() + d2 / 2.0, this.l(Color.WHITE));
        this.w(this.G$src$D$1b2f02a() + (d3 += Math.max(22.0, 5.0 + smoothFontRenderer2.N("0") * (double)String.valueOf(n3).length())), this.n() + d2 / 2.0 + 4.0, this.U$src$Z$154mhdj());
        this.Fk = d3;
        d3 = 10.0;
        this.N(smoothFontRenderer, "BIOME:", this.G$src$D$1b2f02a() + d3, this.n() + d2 + d2 / 2.0 - 2.5, this.l(Color.WHITE));
        this.N(smoothFontRenderer, this.F$src$Ljava_lang_String_$1237edm(), this.G$src$D$1b2f02a() + (d3 += smoothFontRenderer.N("BIOME: ")), this.n() + d2 + d2 / 2.0 - 2.5, this.l(CoordinatesHudFrame.J.Y));
    }

    @Override
    public double L() {
        if (this.P$src$Z$151vieq()) {
            return 90.0;
        }
        return 35.0;
    }

    private boolean U$src$Z$154mhdj() {
        float f = this.D$src$F$14v9yt6();
        return f > 90.0f && f < 270.0f;
    }

    @Override
    public double A() {
        if (this.P$src$Z$151vieq()) {
            return 70.0;
        }
        return this.Fk + 12.0;
    }

    private float D$src$F$14v9yt6() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f = entityPlayerSP.J() % 360.0f;
        if (f < -180.0f) {
            f += 360.0f;
        }
        if (f > 180.0f) {
            f -= 360.0f;
        }
        return f + 180.0f;
    }

    private boolean P$src$Z$151vieq() {
        if (this.FD == null) {
            return false;
        }
        return this.FD.p.K() == this.FD.U;
    }

    public CoordinatesHudFrame() {
        super(CoordinatesHudModule.class);
    }

    private void w(double d, double d2, boolean bl) {
        Color color;
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        double d9 = d - 4.0;
        double d10 = d2 - 4.0;
        GuiRenderPrimitives.d(d9, d10, 8.0, 8.0, this.l(ColorUtil.W(CoordinatesHudFrame.J.i, 145)));
        if (bl) {
            d8 = d9 + 1.8;
            d7 = d10 + 4.5;
            d6 = d9 + 4.0;
            d5 = d10 + 2.5;
            d4 = d9 + 5.7;
            d3 = d10 + 4.5;
            color = CoordinatesHudFrame.J.B;
        } else {
            d8 = d9 + 1.8;
            d7 = d10 + 3.0;
            d6 = d9 + 5.7;
            d5 = d10 + 3.0;
            d4 = d9 + 4.0;
            d3 = d10 + 5.0;
            color = CoordinatesHudFrame.J.d;
        }
        GuiRenderPrimitives.U(d8, d7, d6, d5, d4, d3, this.l(color));
    }

    @Override
    public void o() {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        if (this.P$src$Z$151vieq()) {
            this.e();
            return;
        }
        this.cO();
    }

}

