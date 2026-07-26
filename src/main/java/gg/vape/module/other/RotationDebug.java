package gg.vape.module.other;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender2D;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.other.RotationDebugRenderer;
import gg.vape.module.other.RotationDebugState;
import gg.vape.module.other.rotation.RotationDebugSample;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.MathUtil;
import gg.vape.utils.MutableFloatTriple;
import gg.vape.utils.Vec3d;
import gg.vape.utils.datas.DirectionalPosition;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.C03PacketPlayer;
import gg.vape.wrapper.impl.CPacketPlayerBlockPlacement;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.UseEntityPacketBridge;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.Vec3i;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

public class RotationDebug
extends Mod {
    private final BooleanValue D;
    private ArrayList<RotationDebugSample> j = new ArrayList();
    private final BooleanValue o;
    private final BooleanValue O;
    private String S = "";
    private RotationDebugSample H;
    private ArrayList<RotationDebugSample> F = new ArrayList();
    private Entity C;

    public RotationDebug() {
        super("OutgoingPackets", -256, Category.Y, "");
        this.O = BooleanValue.create(this, "Log to file", false);
        this.D = BooleanValue.create(this, "Interactions", false);
        this.o = BooleanValue.create(this, "Placements", false);
        this.addValue(this.O, this.D, this.o);
    }

    @Override
    public void onEnable() {
        this.S = Long.toString(System.currentTimeMillis());
        this.z();
    }

    @EventHandler
    public void w(EventWorldChange eventWorldChange) {
        this.z();
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        if (this.j.isEmpty()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventRender2D.getThePlayer();
        WorldClient worldClient = eventRender2D.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull()) {
            this.z();
            return;
        }
        GuiRenderPrimitives.Y();
        OpenGlBackendHolder.d.m();
        boolean bl = OpenGlBackendHolder.d.L(3042);
        boolean bl2 = OpenGlBackendHolder.d.L(3553);
        if (!bl) {
            GlStateManager.enableBlend();
        }
        if (bl2) {
            GlStateManager.disableTexture2D();
        }
        double d = 200.0;
        double d2 = 90.0;
        this.D(120.0, 20.0, d, d2);
        this.C(340.0, 20.0, d, d2);
        this.M(120.0, 130.0, d, d2);
        this.Q(340.0, 130.0, d, d2);
        if (bl2) {
            GlStateManager.enableTexture2D();
        }
        if (!bl) {
            GlStateManager.disableBlend();
        }
        OpenGlBackendHolder.d.F();
        GuiRenderPrimitives.D();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        WorldClient worldClient = eventPreTick.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull()) {
            this.z();
            return;
        }
        this.H = new RotationDebugSample(entityPlayerSP.l());
        this.C = null;
        ArrayList arrayList = new ArrayList(worldClient.z());
        for (Object e : arrayList) {
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.zm) || entity.equals(entityPlayerSP) || !(entityPlayerSP.getDistanceToEntity(entity) < 6.0f)) continue;
            this.C = entity;
        }
    }

    private void m(double d, double d2, double d3, double d4) {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().H(true);
        double d5 = 5.0;
        Color color = new Color(0, 0, 0, 200);
        Color color2 = new Color(255, 255, 255, 100);
        Color color3 = new Color(0, 255, 0, 255);
        GuiRenderPrimitives.q(d - d5, d2 - d5, d3 + d5 + d5, d4 + d5 + d5, 1.0, color2, color);
        smoothFontRenderer.v("Yaw/Pitch Ratio", d + 10.0, d2, Color.WHITE);
        int n = 100;
        double d6 = d3 / 100.0;
        double d7 = d;
        if (GuiRenderPrimitives.d()) {
            for (int i = 1; i < this.j.size(); ++i) {
                RotationDebugSample rotationDebugSample = this.j.get(i - 1);
                RotationDebugSample rotationDebugSample2 = this.j.get(i);
                float f = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).z());
                float f2 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).N());
                float f3 = Math.abs(f / f2);
                double d8 = d2 + d4 - (double)f3 * d4;
                float f4 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample2).z());
                float f5 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample2).N());
                float f6 = Math.abs(f4 / f5);
                double d9 = d2 + d4 - (double)f6 * d4;
                GuiRenderPrimitives.u((double)((float)d7), d8, (double)((float)(d7 + d6)), d9, 1.0f, color3);
                d7 += d6;
            }
        } else {
            OpenGlBackendHolder.d.k((double)color3.getRed() / 255.0, (double)color3.getGreen() / 255.0, (double)color3.getBlue() / 255.0, (double)color3.getAlpha() / 255.0);
            OpenGlBackendHolder.d.r(1.0f);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.C(3);
            for (RotationDebugSample rotationDebugSample : this.j) {
                float f = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).z());
                float f7 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).N());
                float f8 = Math.abs(f / f7);
                GL11.glVertex2d((double)d7, (double)(d2 + d4 - (double)f8 * d4));
                d7 += d6;
            }
            OpenGlBackendHolder.d.M();
        }
    }

    private void Q(double d, double d2, double d3, double d4) {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().H(true);
        double d5 = 5.0;
        Color color = new Color(0, 0, 0, 200);
        Color color2 = new Color(255, 255, 255, 100);
        Color color3 = new Color(0, 255, 0, 255);
        GuiRenderPrimitives.q(d - d5, d2 - d5, d3 + d5 + d5, d4 + d5 + d5, 1.0, color2, color);
        GuiRenderPrimitives.u(d, d2 + d4 / 2.0, d + d3, d2 + d4 / 2.0, 0.25f, Color.RED);
        if (this.j.size() < 2) {
            return;
        }
        float f = 1.0f;
        float f2 = RotationDebugSample.p(this.j.get(0)).N();
        for (RotationDebugSample rotationDebugSample : this.j) {
            float f3 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).N() - f2);
            if (Math.abs(f3) > f) {
                f = (float)Math.floor(Math.abs(f3));
            }
            f2 = RotationDebugSample.p(rotationDebugSample).N();
        }
        float f4 = -f;
        float f5 = f;
        int n = 100;
        double d6 = d3 / 100.0;
        double d7 = d;
        smoothFontRenderer.v("Pitch Change/Time (+/- " + (int)f + ")", d + 10.0, d2, Color.WHITE);
        if (GuiRenderPrimitives.d()) {
            float f6 = RotationDebugSample.p(this.j.get(0)).N();
            float f7 = RotationDebugSample.p(this.j.get(1)).N();
            for (int i = 1; i < this.j.size(); ++i) {
                RotationDebugSample rotationDebugSample = this.j.get(i - 1);
                RotationDebugSample rotationDebugSample2 = this.j.get(i);
                float f8 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).N() - f6);
                double d8 = d2 + d4 / 2.0 + (double)(f8 / (f4 - f5)) * d4 / 2.0;
                float f9 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample2).N() - f7);
                double d9 = d2 + d4 / 2.0 + (double)(f9 / (f4 - f5)) * d4 / 2.0;
                GuiRenderPrimitives.u((double)((float)d7), d8, (double)((float)(d7 + d6)), d9, 1.0f, color3);
                d7 += d6;
                f6 = RotationDebugSample.p(rotationDebugSample).N();
                f7 = RotationDebugSample.p(rotationDebugSample2).N();
            }
        } else {
            OpenGlBackendHolder.d.k((double)color3.getRed() / 255.0, (double)color3.getGreen() / 255.0, (double)color3.getBlue() / 255.0, (double)color3.getAlpha() / 255.0);
            OpenGlBackendHolder.d.r(1.0f);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.C(3);
            float f10 = RotationDebugSample.p(this.j.get(0)).N();
            for (RotationDebugSample rotationDebugSample : this.j) {
                float f11 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).N() - f10);
                double d10 = d2 + d4 / 2.0 + (double)(f11 / (f4 - f5)) * d4 / 2.0;
                GL11.glVertex2d((double)d7, (double)d10);
                d7 += d6;
                f10 = RotationDebugSample.p(rotationDebugSample).N();
            }
            OpenGlBackendHolder.d.M();
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        Object object;
        Wrapper wrapper;
        Wrapper wrapper2;
        EntityPlayerSP entityPlayerSP = eventPacketSend.getThePlayer();
        WorldClient worldClient = eventPacketSend.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull()) {
            this.z();
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (this.H == null) {
            this.H = new RotationDebugSample(Minecraft.thePlayer().l());
        }
        if (packet.isInstance(MappedClasses.qD)) {
            if (this.C != null) {
                RotationDebugSample.q(this.H, new Vec3d(this.C.z(), this.C.N(), this.C.h()));
            }
            this.H.D(new C03PacketPlayer(packet.getObject()), eventPacketSend.getThePlayer());
        }
        if (packet.isInstance(MappedClasses.VF)) {
            RotationDebugSample.G(this.H, true);
        }
        if (UseEntityPacketBridge.h(packet)) {
            wrapper2 = new UseEntityPacketBridge(packet.getObject());
            if (((UseEntityPacketBridge)wrapper2).S() && (wrapper = ((UseEntityPacketBridge)wrapper2).C(eventPacketSend.getWorld())).equals(this.C)) {
                RotationDebugSample.s(this.H, true);
            }
            if (this.D.L().booleanValue() && (wrapper = ((UseEntityPacketBridge)wrapper2).C(worldClient)) != null && wrapper.isNotNull()) {
                object = new RotationDebugRenderer(entityPlayerSP.l(), (Entity)wrapper, ((UseEntityPacketBridge)wrapper2).A$src$Ljava_lang_String_$jiwkol());
                Vec3 vec3 = ((UseEntityPacketBridge)wrapper2).O();
                if (vec3 != null && vec3.isNotNull()) {
                    RotationDebugRenderer.G((RotationDebugRenderer)object, new Vec3d(vec3.getX(), vec3.getY(), vec3.getZ()));
                }
                Vape.debugLog(RotationDebugRenderer.S((RotationDebugRenderer)object));
            }
        }
        if (this.o.L().booleanValue() && packet.isInstance(MappedClasses.YB)) {
            wrapper2 = new CPacketPlayerBlockPlacement(packet.getObject());
            wrapper = ((CPacketPlayerBlockPlacement)wrapper2).Q();
            object = ((CPacketPlayerBlockPlacement)wrapper2).d$src$Lgg_vape_utils_MutableFloatTriple_$uj7uxi();
            int n = ((CPacketPlayerBlockPlacement)wrapper2).d$src$I$17a761m();
            DirectionalPosition directionalPosition = new DirectionalPosition(((Vec3i)wrapper).P(), ((Vec3i)wrapper).o(), ((Vec3i)wrapper).d(), n);
            RotationDebugState rotationDebugState = new RotationDebugState(entityPlayerSP.l(), directionalPosition, (MutableFloatTriple)object);
            if (ForgeVersion.MC_1_16_5_ACTUAL.d()) {
                RotationDebugState.Z(rotationDebugState, ((CPacketPlayerBlockPlacement)wrapper2).l().a());
                if (ForgeVersion.MC_1_21_11.d()) {
                    RotationDebugState.R(rotationDebugState, ((CPacketPlayerBlockPlacement)wrapper2).K());
                }
            }
            Vape.debugLog(RotationDebugState.C(rotationDebugState));
        }
    }

    public void A() {
        if (!this.O.L().booleanValue()) {
            this.F.clear();
            return;
        }
        String string = "C:\\dump\\outgoing_packets_" + this.S + ".txt";
        File file = new File(string);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileWriter fileWriter = new FileWriter(string, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (RotationDebugSample rotationDebugSample : this.F) {
                bufferedWriter.write(rotationDebugSample.toString() + '\n');
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            this.F.clear();
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    @EventHandler
    public void g(EventPostTick eventPostTick) {
        EntityPlayerSP entityPlayerSP = eventPostTick.getThePlayer();
        WorldClient worldClient = eventPostTick.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull()) {
            this.z();
            return;
        }
        if (this.H != null && RotationDebugSample.w(this.H) != null) {
            while (this.j.size() >= 100) {
                this.j.remove(0);
            }
            if (this.O.L().booleanValue()) {
                if (this.F.size() >= 100) {
                    this.A();
                }
                this.F.add(this.H);
            }
            this.j.add(this.H);
        }
    }

    private void C(double d, double d2, double d3, double d4) {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().H(true);
        double d5 = 5.0;
        Color color = new Color(0, 0, 0, 200);
        Color color2 = new Color(255, 255, 255, 100);
        Color color3 = new Color(0, 255, 0, 255);
        GuiRenderPrimitives.q(d - d5, d2 - d5, d3 + d5 + d5, d4 + d5 + d5, 1.0, color2, color);
        GuiRenderPrimitives.u(d, d2 + d4 / 2.0, d + d3, d2 + d4 / 2.0, 0.25f, Color.RED);
        if (this.j.size() < 2) {
            return;
        }
        float f = 1.0f;
        float f2 = RotationDebugSample.p(this.j.get(0)).z();
        for (RotationDebugSample rotationDebugSample : this.j) {
            float f3 = RotationDebugSample.p(rotationDebugSample).z() - f2;
            if (Math.abs(f3) > f) {
                f = (float)Math.floor(Math.abs(f3));
            }
            f2 = RotationDebugSample.p(rotationDebugSample).z();
        }
        float f4 = -f;
        float f5 = f;
        int n = 100;
        double d6 = d3 / 100.0;
        double d7 = d;
        smoothFontRenderer.v("Yaw Change/Time (+/- " + (int)f + ")", d + 10.0, d2, Color.WHITE);
        if (GuiRenderPrimitives.d()) {
            float f6 = RotationDebugSample.p(this.j.get(0)).z();
            float f7 = RotationDebugSample.p(this.j.get(1)).z();
            for (int i = 1; i < this.j.size(); ++i) {
                RotationDebugSample rotationDebugSample = this.j.get(i - 1);
                RotationDebugSample rotationDebugSample2 = this.j.get(i);
                float f8 = RotationDebugSample.p(rotationDebugSample).z() - f6;
                double d8 = d2 + d4 / 2.0 + (double)(f8 / (f4 - f5)) * d4 / 2.0;
                float f9 = RotationDebugSample.p(rotationDebugSample2).z() - f7;
                double d9 = d2 + d4 / 2.0 + (double)(f9 / (f4 - f5)) * d4 / 2.0;
                GuiRenderPrimitives.u((double)((float)d7), d8, (double)((float)(d7 + d6)), d9, 1.0f, color3);
                d7 += d6;
                f6 = RotationDebugSample.p(rotationDebugSample).z();
                f7 = RotationDebugSample.p(rotationDebugSample2).z();
            }
        } else {
            OpenGlBackendHolder.d.k((double)color3.getRed() / 255.0, (double)color3.getGreen() / 255.0, (double)color3.getBlue() / 255.0, (double)color3.getAlpha() / 255.0);
            OpenGlBackendHolder.d.r(1.0f);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.C(3);
            float f10 = RotationDebugSample.p(this.j.get(0)).z();
            for (RotationDebugSample rotationDebugSample : this.j) {
                float f11 = RotationDebugSample.p(rotationDebugSample).z() - f10;
                double d10 = d2 + d4 / 2.0 + (double)(f11 / (f4 - f5)) * d4 / 2.0;
                GL11.glVertex2d((double)d7, (double)d10);
                d7 += d6;
                f10 = RotationDebugSample.p(rotationDebugSample).z();
            }
            OpenGlBackendHolder.d.M();
        }
    }

    private void D(double d, double d2, double d3, double d4) {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().H(true);
        double d5 = 5.0;
        Color color = new Color(0, 0, 0, 200);
        Color color2 = new Color(255, 255, 255, 100);
        Color color3 = new Color(0, 255, 0, 255);
        GuiRenderPrimitives.q(d - d5, d2 - d5, d3 + d5 + d5, d4 + d5 + d5, 1.0, color2, color);
        GuiRenderPrimitives.u(d, d2 + d4 / 2.0, d + d3, d2 + d4 / 2.0, 0.25f, Color.RED);
        smoothFontRenderer.v("Yaw/Time " + this.j.size(), d + 10.0, d2, Color.WHITE);
        if (this.j.size() < 2) {
            return;
        }
        float f = -180.0f;
        float f2 = 180.0f;
        int n = 100;
        double d6 = d3 / 100.0;
        double d7 = d;
        if (GuiRenderPrimitives.d()) {
            for (int i = 1; i < this.j.size(); ++i) {
                RotationDebugSample rotationDebugSample = this.j.get(i - 1);
                RotationDebugSample rotationDebugSample2 = this.j.get(i);
                float f3 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).z());
                float f4 = (float)(d2 + d4 - (double)((f3 - f2) / (f - f2)) * d4);
                float f5 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample2).z());
                float f6 = (float)(d2 + d4 - (double)((f5 - f2) / (f - f2)) * d4);
                GuiRenderPrimitives.u((double)((float)d7), (double)f4, (double)((float)(d7 + d6)), (double)f6, 1.0f, color3);
                d7 += d6;
            }
        } else {
            OpenGlBackendHolder.d.k((double)color3.getRed() / 255.0, (double)color3.getGreen() / 255.0, (double)color3.getBlue() / 255.0, (double)color3.getAlpha() / 255.0);
            OpenGlBackendHolder.d.r(1.0f);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.C(3);
            for (RotationDebugSample rotationDebugSample : this.j) {
                float f7 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).z());
                float f8 = (float)(d2 + d4 - (double)((f7 - f2) / (f - f2)) * d4);
                GL11.glVertex2d((double)d7, (double)f8);
                d7 += d6;
            }
            OpenGlBackendHolder.d.M();
        }
    }

    @Override
    public void onDisable() {
        this.A();
        this.z();
    }

    private void z() {
        this.F.clear();
        this.j.clear();
        this.H = null;
    }

    private void M(double d, double d2, double d3, double d4) {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().H(true);
        double d5 = 5.0;
        Color color = new Color(0, 0, 0, 200);
        Color color2 = new Color(255, 255, 255, 100);
        Color color3 = new Color(0, 255, 0, 255);
        GuiRenderPrimitives.q(d - d5, d2 - d5, d3 + d5 + d5, d4 + d5 + d5, 1.0, color2, color);
        GuiRenderPrimitives.u(d, d2 + d4 / 2.0, d + d3, d2 + d4 / 2.0, 0.25f, Color.RED);
        smoothFontRenderer.v("Pitch/Time " + this.j.size(), d + 10.0, d2, Color.WHITE);
        if (this.j.size() < 2) {
            return;
        }
        float f = -90.0f;
        float f2 = 90.0f;
        int n = 100;
        double d6 = d3 / 100.0;
        double d7 = d;
        if (GuiRenderPrimitives.d()) {
            for (int i = 1; i < this.j.size(); ++i) {
                RotationDebugSample rotationDebugSample = this.j.get(i - 1);
                RotationDebugSample rotationDebugSample2 = this.j.get(i);
                float f3 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).N());
                float f4 = (float)(d2 + d4 - (double)((f3 - f2) / (f - f2)) * d4);
                float f5 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample2).N());
                float f6 = (float)(d2 + d4 - (double)((f5 - f2) / (f - f2)) * d4);
                GuiRenderPrimitives.u((double)((float)d7), (double)f4, (double)((float)(d7 + d6)), (double)f6, 1.0f, color3);
                d7 += d6;
            }
        } else {
            OpenGlBackendHolder.d.k((double)color3.getRed() / 255.0, (double)color3.getGreen() / 255.0, (double)color3.getBlue() / 255.0, (double)color3.getAlpha() / 255.0);
            OpenGlBackendHolder.d.r(1.0f);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.C(3);
            for (RotationDebugSample rotationDebugSample : this.j) {
                float f7 = MathUtil.wrapAngleTo180(RotationDebugSample.p(rotationDebugSample).N());
                float f8 = (float)(d2 + d4 - (double)((f7 - f2) / (f - f2)) * d4);
                GL11.glVertex2d((double)d7, (double)f8);
                d7 += d6;
            }
            OpenGlBackendHolder.d.M();
        }
    }
}

