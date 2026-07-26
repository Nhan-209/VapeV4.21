package gg.vape.module.render;

import com.google.common.collect.Lists;
import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderEntity;
import gg.vape.event.impl.EventPreRenderLiving;
import gg.vape.event.impl.EventPreRenderPlayerSpec;
import gg.vape.event.impl.EventRenderPlayerPost;
import gg.vape.event.impl.EventRenderPlayerPre;
import gg.vape.event.impl.EventSetArmorModel;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.ESP;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.Value;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Tessellator;
import java.awt.Color;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

public class Chams
extends Mod {
    private boolean H;
    private EntityLivingBase D;
    private final ColorValue r;
    private final ColorValue I;
    private final BooleanValue a;
    private final BooleanValue c;
    private final BooleanValue A = BooleanValue.create(this, "Hide Bots", false, "Doesn't apply chams on bots.");
    private int J;

    private static Exception a(Exception exception) {
        return exception;
    }

    @EventHandler
    public void J(EventPreRenderLiving eventPreRenderLiving) {
        if (!this.H) {
            return;
        }
        if (eventPreRenderLiving.getWorld().isNull()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventPreRenderLiving.getThePlayer();
        Entity entity = eventPreRenderLiving.getEntity();
        if (entity.equals(entityPlayerSP)) {
            return;
        }
        eventPreRenderLiving.setCancelled(true);
    }

    @EventHandler
    public void K(EventPreRenderEntity eventPreRenderEntity) {
        if (eventPreRenderEntity.getEntity().isInstance(MappedClasses.Yl) && !eventPreRenderEntity.getEntity().isInstance(MappedClasses.z5) && this.a.L().booleanValue() && this.H) {
            if (this.J == 1) {
                RenderUtils.w(this.c.L() != false ? this.r.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.I.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            }
            if (this.J == 2) {
                RenderUtils.w(this.I.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            }
        }
    }

    @EventHandler
    public void s(EventRenderPlayerPost eventRenderPlayerPost) {
        if (Vape.INSTANCE.getClientSettings().J(eventRenderPlayerPost.getEntityPlayer()) && this.A.L().booleanValue()) {
            return;
        }
        if (this.a.L().booleanValue()) {
            if (this.D != null) {
                EntityPlayer entityPlayer = eventRenderPlayerPost.getEntityPlayer();
                entityPlayer.q(false);
                this.D = null;
            }
            return;
        }
        if (eventRenderPlayerPost.getEntityPlayer().isInstance(MappedClasses.Yl) && !eventRenderPlayerPost.getEntityPlayer().isInstance(MappedClasses.z5)) {
            OpenGlBackendHolder.d.u$src$V$hntn98(32823);
            GL11.glPolygonOffset((float)1.0f, (float)2500000.0f);
        }
    }

    @EventHandler
    public void onSpecPreRenderLiving(EventPreRenderPlayerSpec eventPreRenderPlayerSpec) {
        if (eventPreRenderPlayerSpec.getClientPlayer().isInstance(MappedClasses.z5)) {
            return;
        }
        if (!this.H) {
            return;
        }
        if (Minecraft.theWorld().isNull()) {
            return;
        }
        eventPreRenderPlayerSpec.setCancelled(true);
    }

    @EventHandler
    public void R(EventRenderPlayerPre eventRenderPlayerPre) {
        if (this.A.L().booleanValue() && Vape.INSTANCE.getClientSettings().J(eventRenderPlayerPre.getEntityPlayer())) {
            return;
        }
        if (ClientSettings.E(eventRenderPlayerPre.getEntityPlayer())) {
            return;
        }
        ESP eSP = Vape.INSTANCE.getModManager().getMod(ESP.class);
        if (this.H || this.J == 3 || eSP.r$src$Z$14eylz9() && eSP.c()) {
            return;
        }
        if (!this.a.L().booleanValue()) {
            if (eventRenderPlayerPre.getEntityPlayer().isInstance(MappedClasses.Yl) && !eventRenderPlayerPre.getEntityPlayer().isInstance(MappedClasses.z5)) {
                OpenGlBackendHolder.d.l(32823);
                GL11.glPolygonOffset((float)1.0f, (float)-2500000.0f);
            }
        } else if (eventRenderPlayerPre.getEntityPlayer().isNotNull() && eventRenderPlayerPre.getRenderer().isNotNull() && !eventRenderPlayerPre.getEntityPlayer().isInstance(MappedClasses.z5)) {
            if (ForgeVersion.MC_1_7_10.L()) {
                eventRenderPlayerPre.setCancelled(true);
            }
            EntityPlayer entityPlayer = eventRenderPlayerPre.getEntityPlayer();
            double d = eventRenderPlayerPre.getX();
            double d2 = eventRenderPlayerPre.getY();
            double d3 = eventRenderPlayerPre.getZ();
            float f = entityPlayer.j() + (entityPlayer.J() - entityPlayer.j()) * eventRenderPlayerPre.getPartialTicks();
            RenderUtil.d();
            RenderUtils.g();
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            GlStateManager.disableLighting();
            RenderUtils.w(this.c.L() != false ? this.r.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.I.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            ArrayList arrayList = null;
            if (ForgeVersion.MC_1_7_10.Y()) {
                arrayList = Lists.newArrayList(eventRenderPlayerPre.getRenderer().getLayerRenderers());
                eventRenderPlayerPre.getRenderer().getLayerRenderers().clear();
            }
            try {
                this.H = true;
                this.J = 1;
                eventRenderPlayerPre.getRenderer().doRender(entityPlayer, d, d2, d3, f, eventRenderPlayerPre.getPartialTicks());
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.H = false;
            OpenGlBackendHolder.d.l(2929);
            OpenGlBackendHolder.d.l(3553);
            if (ForgeVersion.MC_1_7_10.L()) {
                this.J = 3;
                GL11.glPushMatrix();
                OpenGlBackendHolder.d.l(2896);
                eventRenderPlayerPre.getRenderer().doRender(entityPlayer, d, d2, d3, f, eventRenderPlayerPre.getPartialTicks());
                GL11.glDepthMask((boolean)false);
                OpenGlBackendHolder.d.u$src$V$hntn98(2896);
                GL11.glPopMatrix();
            }
            this.H = true;
            RenderUtils.w(this.I.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            try {
                this.J = 2;
                eventRenderPlayerPre.getRenderer().doRender(entityPlayer, d, d2, d3, f, eventRenderPlayerPre.getPartialTicks());
                this.H = false;
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (ForgeVersion.MC_1_7_10.Y()) {
                eventRenderPlayerPre.getRenderer().setLayerRenderers(arrayList);
            }
            OpenGlBackendHolder.d.l(3553);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.enableLighting();
            RenderUtils.f();
            GL11.glPopMatrix();
            this.J = -1;
            if (!entityPlayer.J$src$Z$fdev5g()) {
                this.D = entityPlayer;
                entityPlayer.q(true);
            }
            if (ForgeVersion.MC_1_16_5.d()) {
                Tessellator.getInstance().getWorldRenderer().Q(false);
            }
        }
    }

    public Chams() {
        super("Chams", -16711936, Category.k, "Render players through walls.");
        this.a = BooleanValue.create(this, "Colored", false, "Colors entities.");
        this.I = ColorValue.L(this, "Visible Color", new Color(255, 0, 0));
        this.c = BooleanValue.create(this, "Color Behind Walls", true, "Renders a different color when\nplayers are behind walls.");
        this.r = ColorValue.L(this, "Invisible Color", new Color(255, 255, 0));
        this.addValue(new Value[]{this.A, this.a.K(new Value[]{this.I, this.c.K(this.r)}), this.I, this.c, this.r});
    }

    @EventHandler
    public void onSetArmor(EventSetArmorModel eventSetArmorModel) {
        if (this.H) {
            if (Minecraft.theWorld().isNull()) {
                return;
            }
            eventSetArmorModel.setResult(0);
            eventSetArmorModel.setCancelled(true);
        }
    }
}

