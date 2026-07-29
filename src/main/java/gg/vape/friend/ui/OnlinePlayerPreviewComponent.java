package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.friend.ui.OnlinePlayerPreviewRenderContext;
import gg.vape.friend.ui.OnlinePlayerPreviewSettingsFrame;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class OnlinePlayerPreviewComponent
extends GuiComponent {
    private OnlinePlayerPreviewRenderContext O;
    private final OnlinePlayerPreviewSettingsFrame K;
    public static OnlinePlayerPreviewComponent b;

    public void S(EventPreRenderTick eventPreRenderTick) {
        if (!this.K.y$src$Z$1f55jvh() || !this.K.V$src$Z$1xhop3l()) {
            return;
        }
        if (eventPreRenderTick.getThePlayer().isNull() || eventPreRenderTick.getWorld().isNull()) {
            return;
        }
        if (this.O != null) {
            if (((Double)this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().p.getValue()).intValue() <= 0) {
                this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().p.setValue(1.0);
            }
            this.O.setFrameIntervalMillis(1000 / ((Double)this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().p.getValue()).intValue());
            this.O.setLevelView(this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().A.getEffectiveValue());
            this.O.setFieldOfView(((Double)this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().W.getValue()).intValue());
            this.O.renderOffscreenFrame();
        } else {
            this.O = new OnlinePlayerPreviewRenderContext();
        }
    }

    @Override
    public void F() {
    }


    @Override
    public void H() {
        this.j$src$V$pujnrp();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double x() {
        if (this.K.isPublicProfilePreviewActive()) {
            return 110.0 * (Double)this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().d.getValue();
        }
        return 110.0;
    }

    @Override
    public void I() {
        this.j$src$V$pujnrp();
    }

    @Override
    public void u() {
    }

    public OnlinePlayerPreviewComponent(OnlinePlayerPreviewSettingsFrame onlinePlayerPreviewSettingsFrame) {
        b = this;
        this.K = onlinePlayerPreviewSettingsFrame;
    }

    @Override
    public double C() {
        if (this.K.isPublicProfilePreviewActive()) {
            return 61.0 * (Double)this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().d.getValue();
        }
        return 61.0;
    }

    private void j$src$V$pujnrp() {
        float f = this.K.getEditorOpacity();
        if (this.O == null || !this.K.y$src$Z$1f55jvh()) {
            double d = this.L();
            double d2 = this.A();
            OpenGlBackendHolder.backend.setColor(0.1f, 0.1f, 0.1f, f);
            GuiRenderPrimitives.d((double)((float)this.G$src$D$1b2f02a()), (double)((float)this.n()), d2, d, this.K.applyDefaultEditorAlpha(OnlinePlayerPreviewComponent.J.r));
            ImageRenderer.drawImage(this.K.applyDefaultEditorAlpha(Color.WHITE), (float)(this.G$src$D$1b2f02a() + d2 / 2.0 - 10.0), (float)(this.n() + d / 2.0 - 10.0), "camera", 16.0f, 16.0f, false);
            return;
        }
        if (Minecraft.gameSettings().d() > 0) {
            Vape.INSTANCE.getFontManager().w().F("Disable Optifine AA to use this feature", this.G$src$D$1b2f02a() + 4.0, this.n() + 4.0, -65536);
            return;
        }
        if (!Minecraft.gameSettings().Y$src$Z$1rxemad()) {
            Vape.INSTANCE.getFontManager().w().F("Enable FBO to use this feature", this.G$src$D$1b2f02a() + 4.0, this.n() + 4.0, -65536);
            return;
        }
        if (Minecraft.gameSettings().M()) {
            Vape.INSTANCE.getFontManager().w().F("Disable fast render to use this feature", this.G$src$D$1b2f02a() + 4.0, this.n() + 4.0, -65536);
            return;
        }
        if (this.O.hasFrame()) {
            double d = this.L();
            double d3 = this.A();
            if (!this.K.isPublicProfilePreviewActive()) {
                d *= ((Double)this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().d.getValue()).doubleValue();
                d3 *= ((Double)this.K.B$src$Lgg_vape_friend_ui_OnlinePlayerPreviewSettings_$1v47p92().d.getValue()).doubleValue();
            }
            if (ForgeVersion.MC_1_21_4.d()) {
                Color color = this.K.applyDefaultEditorAlpha(new Color(0.1f, 0.1f, 0.1f, 0.2f));
                GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), d3, d, color);
                Color color2 = new Color(1.0f, 1.0f, 1.0f, f);
                this.O.drawFramebuffer(this.K.y$src$Z$1f55jvh(), this.G$src$D$1b2f02a() + 0.5, this.n() + 0.5, this.G$src$D$1b2f02a() + d3 - 0.5, this.n() + d - 0.5, color2);
                return;
            }
            boolean bl = GL11.glIsEnabled((int)3553);
            boolean bl2 = GL11.glIsEnabled((int)3008);
            boolean bl3 = GL11.glIsEnabled((int)3042);
            if (bl) {
                GlStateManager.disableTexture2D();
            }
            if (!bl2) {
                GlStateManager.enableAlpha();
            }
            if (!bl3) {
                GlStateManager.enableBlend();
            }
            Color color = this.K.applyDefaultEditorAlpha(new Color(0.1f, 0.1f, 0.1f, 0.2f));
            OpenGlBackendHolder.backend.setColor(0.1f, 0.1f, 0.1f, (float)color.getAlpha() / 255.0f);
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), d3, d, color);
            Color color3 = new Color(1.0f, 1.0f, 1.0f, f);
            this.O.drawFramebuffer(this.K.y$src$Z$1f55jvh(), this.G$src$D$1b2f02a() + 0.5, this.n() + 0.5, this.G$src$D$1b2f02a() + d3 - 0.5, this.n() + d - 0.5, color3);
            if (bl) {
                GlStateManager.enableTexture2D();
            }
            if (!bl2) {
                GlStateManager.disableAlpha();
            }
            if (!bl3) {
                GlStateManager.disableBlend();
            }
        }
    }
}

