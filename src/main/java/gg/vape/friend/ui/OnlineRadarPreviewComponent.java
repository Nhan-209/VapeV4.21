package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.ui.OnlineRadarPreviewState;
import gg.vape.friend.ui.OnlineRadarSettingsFrame;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.module.render.entity.RenderEntityContextCache;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class OnlineRadarPreviewComponent
extends GuiComponent {
    private final OnlineRadarSettingsFrame R;
    static final boolean v = !OnlineRadarPreviewComponent.class.desiredAssertionStatus();

    public OnlineRadarPreviewComponent(OnlineRadarSettingsFrame onlineRadarSettingsFrame) {
        this.R = onlineRadarSettingsFrame;
    }

    private static double lambda$renderRadar$0(OnlineRadarPreviewState onlineRadarPreviewState) {
        return ((RenderEntityContext)onlineRadarPreviewState.h()).e();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void a(boolean bl) {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().Y(1.0);
        double d = this.G$src$D$1b2f02a();
        boolean bl2 = this.R.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null && this.R.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().V$src$Z$1xhop3l();
        double d2 = (float)((double)((float)this.n()) - (bl && bl2 ? this.R.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() : -2.0));
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull() || entityPlayerSP.isNull()) {
            return;
        }
        List list = worldClient.X();
        if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().n.K() == this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().W) {
            float f;
            float f2;
            Object object;
            boolean bl3;
            boolean bl4 = GL11.glIsEnabled((int)3042);
            double d3 = (Double)this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().h.K();
            double d4 = d3 / 2.0;
            double d5 = (Double)this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().V.K();
            double d6 = (Double)this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().s.K();
            boolean bl5 = bl3 = this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().p.K() == this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().O;
            if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().y.L().booleanValue()) {
                if (bl3) {
                    GuiRenderPrimitives.e(d, d2, d3, d3, this.R.l(new Color(-1877995504, true)), false, 3.0f, 1.0f);
                } else {
                    GuiRenderPrimitives.V(d, d2, d3, 1.0, this.R.l(new Color(0, 0, 0, 128)));
                }
            }
            if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().U.L().booleanValue()) {
                object = this.R.l(new Color(-10132123, true));
                float f3 = (float)((d -= 0.5) + d3 / 2.0 - 0.5);
                f2 = (float)((d2 -= 0.5) + 0.5);
                float f4 = (float)(d + d3 / 2.0 + 0.5);
                f = (float)(d2 + d3 - 0.5);
                float f5 = f4 - f3;
                float f6 = f - f2;
                GuiRenderPrimitives.y(f3, f2, f5, f6, (Color)object);
                f3 = (float)(d + 0.5);
                f2 = (float)(d2 + d3 / 2.0 - 0.5);
                f4 = (float)(d + d3 - 0.5);
                f = (float)(d2 + d3 / 2.0 + 0.5);
                f5 = f4 - f3;
                f6 = f - f2;
                GuiRenderPrimitives.y(f3, f2, f5, f6, (Color)object);
            }
            if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().y.L().booleanValue() && bl3) {
                if (Vape.INSTANCE.getClientSettings().w.g()) {
                    GuiRenderPrimitives.e(d + 1.0, d2 + 0.5, 2.0, 1.5, this.R.l(Vape.INSTANCE.getClientSettings().w.q$src$Lgg_vape_utils_MutableColor_$1dowyd3()), false, 1.0f, 1.0f);
                    object = Vape.INSTANCE.getClientSettings().w.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                    float[] fArray = new float[3];
                    Color.RGBtoHSB(((Color)object).getRed(), ((Color)object).getGreen(), ((Color)object).getBlue(), fArray);
                    f2 = fArray[0];
                    Object object2 = object;
                    f = 2.0f;
                    while ((double)f < d3 - 2.0) {
                        object2 = ColorUtil.Y(f2, 0.9f, 1.0f);
                        f2 = (float)((double)f2 + 0.005);
                        GuiRenderPrimitives.C(d + (double)f, d2 + 0.1, 1.0, 1.75, this.R.l((Color)object2));
                        f += 1.0f;
                    }
                    GuiRenderPrimitives.e(d + d3 - 3.0, d2 + 0.5, 2.0, 1.5, this.R.l((Color)object2), false, 1.0f, 1.0f);
                } else {
                    GuiRenderPrimitives.e(d + 1.0, d2 + 0.5, d3 - 2.0, 1.5, this.R.l(Vape.INSTANCE.getClientSettings().w.q$src$Lgg_vape_utils_MutableColor_$1dowyd3()), false, 1.0f, 1.0f);
                }
                object = new MutableColor(OnlineRadarPreviewComponent.J.r);
                ((MutableColor)object).withAlpha(100);
                GuiRenderPrimitives.P(d, d2, d3, d3, this.R.l((Color)object), 3.0f, 1.0f, 1.0f);
            }
            for (Object e : list) {
                double d7;
                double d8;
                if (e == entityPlayerSP.getObject()) continue;
                EntityPlayer entityPlayer = new EntityPlayer(e);
                if (Vape.INSTANCE.getClientSettings().J(entityPlayer)) continue;
                RenderEntityContext renderEntityContext = RenderEntityContextCache.V(entityPlayer, entityPlayerSP);
                double d9 = entityPlayerSP.M() + (entityPlayerSP.z() - entityPlayerSP.M()) * (double)Minecraft.getTimer().renderPartialTicks();
                double d10 = entityPlayerSP.m$src$D$fwnne5() + (entityPlayerSP.h() - entityPlayerSP.m$src$D$fwnne5()) * (double)Minecraft.getTimer().renderPartialTicks();
                double d11 = entityPlayer.M() + (entityPlayer.z() - entityPlayer.M()) * (double)Minecraft.getTimer().renderPartialTicks();
                double d12 = entityPlayer.m$src$D$fwnne5() + (entityPlayer.h() - entityPlayer.m$src$D$fwnne5()) * (double)Minecraft.getTimer().renderPartialTicks();
                double d13 = d11 - d9;
                double d14 = d12 - d10;
                double d15 = d + d4;
                double d16 = d2 + d4;
                float f7 = (float)Math.cos((double)entityPlayerSP.J() * (Math.PI / 180));
                float f8 = (float)Math.sin((double)entityPlayerSP.J() * (Math.PI / 180));
                double d17 = -(d13 * (double)f7 + d14 * (double)f8) * d5;
                double d18 = -(d14 * (double)f7 - d13 * (double)f8) * d5;
                if (bl3) {
                    d8 = d15 + (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().r.L() == false ? d17 : MathUtil.clamp(d17, -d4 + d6 / 2.0, d4 - d6 / 2.0));
                    d7 = d16 + (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().r.L() == false ? d18 : MathUtil.clamp(d18, -d4 + d6 / 2.0, d4 - d6 / 2.0));
                    d7 = Math.max(d7, d2 + 4.0);
                } else {
                    if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().r.L().booleanValue() && Math.sqrt(d17 * d17 + d18 * d18) > d4) {
                        float f9 = (float)Math.atan2(d18, d17);
                        d17 = (float)((d4 - 0.5) * Math.cos(f9));
                        d18 = (float)((d4 - 0.5) * Math.sin(f9));
                    }
                    d8 = d15 + d17;
                    d7 = d16 + d18;
                }
                Color color = Color.WHITE;
                if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().I.K() == this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().x) {
                    color = this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().v.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                } else if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().I.K() == this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().t) {
                    MutableColor mutableColor = renderEntityContext.E(true);
                    if (mutableColor != null) {
                        color = mutableColor;
                    }
                } else if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().I.K() == this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().f) {
                    Color color2 = color = renderEntityContext.R() ? this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().j.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().F.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                }
                if (renderEntityContext.K$src$Z$1xmao67() && Vape.INSTANCE.getFriendManager().q.L().booleanValue()) {
                    color = Vape.INSTANCE.getFriendManager().R.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                }
                if (renderEntityContext.f() && Vape.INSTANCE.getEnemyManager().p.L().booleanValue()) {
                    color = Vape.INSTANCE.getEnemyManager().i.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
                }
                if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().L.K() == this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().a) {
                    RenderUtils.M(d8 - d6 / 2.0, d7 - d6 / 2.0, d8 + d6 / 2.0, d7 + d6 / 2.0, 0.5, this.R.l(color), this.R.l(new Color(0x50000000, true)));
                    continue;
                }
                double d19 = d6 / 2.0;
                GuiRenderPrimitives.V(d8 - d19, d7 - d19, d6, 0.5, this.R.l(color));
                GuiRenderPrimitives.m((float)(d8 - d19), (float)(d7 - d19), (float)d6, 1.0f, 0.75f, this.R.l(new Color(-16777216, true)));
            }
            if (bl4) {
                GlStateManager.enableBlend();
            }
        } else {
            List<OnlineRadarPreviewState> list2 = new ArrayList<OnlineRadarPreviewState>();
            ArrayList arrayList = new ArrayList();
            int n = ((Double)this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().e.K()).intValue();
            for (Object object : list) {
                if (!MappedClasses.Yl.isAssignableFrom(object.getClass()) || object == entityPlayerSP.getObject() || arrayList.contains(object)) continue;
                EntityPlayer entityPlayer = new EntityPlayer(object);
                if (Vape.INSTANCE.getClientSettings().J(entityPlayer) || n != 0 && !(entityPlayerSP.getDistanceToEntity(entityPlayer) <= (float)n) || Vape.INSTANCE.getClientSettings().J(entityPlayer)) continue;
                list2.add(OnlineRadarPreviewState.l(entityPlayer, RenderEntityContextCache.V(entityPlayer, entityPlayerSP)));
                arrayList.add(object);
            }
            if (list2.isEmpty()) {
                if (!ClientSettings.fW.P && HudModuleConfigFrameBase.h$src$Z$1tlh1co()) {
                    String[][] object;
                    OnlineRadarSettingsFrame onlineRadarSettingsFrame = this.R;
                    object = new String[][]{{"Player1", "\u00a7a72m"}, {"Player2", "\u00a7e45m"}, {"Player3", "\u00a7c18m"}};
                    int n2 = 0;
                    for (String[] e : object) {
                        if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().y.L().booleanValue()) {
                            GuiRenderPrimitives.C(d, d2 + (double)n2, this.A(), 10.0, onlineRadarSettingsFrame.l(new Color(20, 20, 20, 180)));
                            GuiRenderPrimitives.C(d, d2 + (double)n2 + 9.5, this.A(), 0.5, onlineRadarSettingsFrame.l(new Color(25, 25, 25, 65)));
                        }
                        smoothFontRenderer.g((String)e[0], d + 1.0, d2 + 2.0 + (double)n2, onlineRadarSettingsFrame.l(Color.WHITE).getRGB());
                        smoothFontRenderer.g((String)e[1], d + this.A() - smoothFontRenderer.N((String)e[1]) - 1.0, d2 + 2.0 + (double)n2, onlineRadarSettingsFrame.l(Color.WHITE).getRGB());
                        n2 += 10;
                    }
                }
                return;
            }
            list2.sort(Comparator.comparingDouble(OnlineRadarPreviewComponent::lambda$renderRadar$0));
            int n3 = ((Double)this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().C.K()).intValue();
            int n4 = 0;
            if ((Double)this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().C.K() != 0.0 && list2.size() > n3) {
                n4 = list2.size() - n3;
                list2 = list2.subList(0, n3);
            }
            int n5 = 0;
            for (OnlineRadarPreviewState onlineRadarPreviewState : list2) {
                EntityPlayer entityPlayer = (EntityPlayer)onlineRadarPreviewState.P();
                RenderEntityContext renderEntityContext = (RenderEntityContext)onlineRadarPreviewState.R();
                if (!v && renderEntityContext == null) {
                    throw new AssertionError();
                }
                int n6 = (int)renderEntityContext.e();
                String string = n6 > 100 ? "a" : (n6 > 50 ? "e" : "c");
                String string2 = String.format("\u00a7%s%dm", string, n6);
                if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().y.L().booleanValue()) {
                    GuiRenderPrimitives.C(d, d2 + (double)n5, this.A(), 10.0, this.R.l(new Color(20, 20, 20, 180)));
                    GuiRenderPrimitives.C(d, d2 + (double)n5 + 9.5, this.A(), 0.5, this.R.l(new Color(25, 25, 25, 65)));
                }
                String string3 = renderEntityContext.k();
                Color color = Vape.INSTANCE.getFriendManager().E(string3) ? new Color(Vape.INSTANCE.getFriendManager().R.HSBtoRGB()) : (Vape.INSTANCE.getEnemyManager().q(string3) ? new Color(Vape.INSTANCE.getEnemyManager().i.HSBtoRGB()) : Color.WHITE);
                smoothFontRenderer.g(string3, d + 1.0, d2 + 2.0 + (double)n5, this.R.l(color).getRGB());
                smoothFontRenderer.g(string2, d + this.A() - smoothFontRenderer.N(string2) - 1.0, d2 + 2.0 + (double)n5, this.R.l(Color.WHITE).getRGB());
                n5 += 10;
            }
            if (n4 > 0) {
                if (this.R.H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8().y.L().booleanValue()) {
                    GuiRenderPrimitives.C(d, d2 + (double)n5, this.A(), 10.0, this.R.l(new Color(20, 20, 20, 180)));
                    GuiRenderPrimitives.C(d, d2 + (double)n5 + 9.5, this.A(), 0.5, this.R.l(new Color(25, 25, 25, 65)));
                }
                smoothFontRenderer.g(n4 + " more...", d + 1.0, d2 + 2.0 + (double)n5, this.R.l(Color.WHITE).getRGB());
            }
        }
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public void I() {
        this.a(true);
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public void u() {
    }

    @Override
    public void F() {
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void H() {
        this.a(false);
    }
}
