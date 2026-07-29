package gg.vape.friend.ui;

import com.google.gson.JsonObject;
import gg.vape.event.EventBus;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventEntityJoinWorld;
import gg.vape.event.impl.EventLivingUpdate;
import gg.vape.event.impl.EventPlayerUseItem;
import gg.vape.friend.ui.OnlineCombatStatComparisonComponent;
import gg.vape.friend.ui.OnlineCombatStatsTargetLabelComponent;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionsFrame;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EntityPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public class OnlineCombatStatsSettingsFrame
extends HudSettingsFrameBase
implements EventListener {
    private int IM;
    private World IA;
    private int IY;
    private int Iu;
    private int Im;
    private OnlineCombatStatComparisonComponent IN;
    private EntityPlayer Ii;
    private double Ij;
    private int If;
    private OnlineCombatStatsTargetLabelComponent IK = new OnlineCombatStatsTargetLabelComponent(this);
    private OnlineCombatStatComparisonComponent I4;
    private double In;
    private String I0;
    private double Ig;

    @Override
    public void v() {
        double d = this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null && this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().V$src$Z$1xhop3l() ? this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() : 0.0;
        Color color = new Color(OnlineCombatStatsSettingsFrame.J.m.getRed(), OnlineCombatStatsSettingsFrame.J.m.getGreen(), OnlineCombatStatsSettingsFrame.J.m.getBlue(), 240);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n() + d, this.A(), this.L() - d, color);
    }

    @Override
    public void Y() {
    }

    @Override
    public String getName() {
        return "Duel Info";
    }


    @Override
    public void V() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!this.y$src$Z$1f55jvh() || Minecraft.thePlayer().isNull()) {
            this.fq();
            return;
        }
        boolean bl = Math.abs(entityPlayerSP.z() - this.In) > 120.0 || Math.abs(entityPlayerSP.N() - this.Ij) > 120.0 || Math.abs(entityPlayerSP.h() - this.Ig) > 120.0;
        this.In = entityPlayerSP.z();
        this.Ij = entityPlayerSP.N();
        this.Ig = entityPlayerSP.h();
        if (this.e() || bl) {
            this.fq();
            return;
        }
        if (Minecraft.theWorld().isNull()) {
            return;
        }
        if (this.Ii == null || this.Ii.isNull()) {
            this.fd();
        } else {
            if (entityPlayerSP.M$src$Z$ff28xj() || this.Ii.M$src$Z$ff28xj()) {
                this.fq();
                return;
            }
            boolean bl2 = false;
            for (Object e : Minecraft.theWorld().X()) {
                if (this.Ii.getObject().equals(e)) {
                    bl2 = true;
                    break;
                }
                EntityPlayer entityPlayer = new EntityPlayer(e);
                if (this.Ii.getObject().equals(entityPlayer.getObject()) || !this.Ii.getName().equalsIgnoreCase(entityPlayer.getName())) continue;
                this.Ii = entityPlayer;
            }
            if (!bl2) {
                this.Ii = null;
                this.fd();
            }
        }
    }

    public EntityPlayer m$src$Lgg_vape_wrapper_impl_EntityPlayer_$1x97g87() {
        return this.Ii;
    }

    @EventHandler
    public void Y(EventEntityJoinWorld eventEntityJoinWorld) {
        if (this.Ii == null || this.Ii.isNull() || Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!eventEntityJoinWorld.getEntity().isInstance(MappedClasses.Zf)) {
            return;
        }
        EntityPotion entityPotion = new EntityPotion(eventEntityJoinWorld.getEntity());
        if (entityPotion.getPotion().isNull() || !ItemStackScoreUtil.i(entityPotion.getPotion())) {
            return;
        }
        if (this.Im > 0) {
            ++this.IM;
            --this.Im;
        } else {
            ++this.Iu;
        }
        this.ff();
    }

    @Override
    protected void renderHudModeBorder() {
        int n = HudModuleConfigFrameBase.isHudEditorContext() ? 200 : 102;
        Color color = new Color(OnlineCombatStatsSettingsFrame.J.i.getRed(), OnlineCombatStatsSettingsFrame.J.i.getGreen(), OnlineCombatStatsSettingsFrame.J.i.getBlue(), n);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.applyDefaultEditorAlpha(color));
    }

    private void ff() {
        this.I4.n(this.IY);
        this.I4.N(this.If);
        this.IN.n(this.Iu);
        this.IN.N(this.IM);
    }

    public OnlineCombatStatsSettingsFrame() {
        super("newduelinfo", "Duel Info");
        this.IN = new OnlineCombatStatComparisonComponent("Potions", this);
        this.I4 = new OnlineCombatStatComparisonComponent("Sword Hits", this);
        if (this.q()) {
            this.w();
        }
        this.addSettings(this.IK, this.IN, this.I4);
        this.fq();
        EventBus.getInstance().registerListener(this, new Predicate[0]);
    }

    @Override
    public void t(JsonObject jsonObject) {
        super.t(jsonObject);
        ClientSettings.getFrame(QuickActionsFrame.class).Y$src$Lgg_vape_ui_click_frame_impl_quickactions_QuickA$p4ezt5().setValue(this.V$src$Z$1xhop3l());
    }

    @EventHandler
    public void C(EventPlayerUseItem eventPlayerUseItem) {
        if (this.Ii == null || this.Ii.isNull()) {
            return;
        }
        ItemStack itemStack = eventPlayerUseItem.getItemStack();
        if (itemStack.isNotNull() && MappedClasses.Di.isInstance(itemStack.getItem().getObject()) && ItemStackScoreUtil.i(itemStack)) {
            ++this.Im;
        }
    }

    private void fq() {
        this.I0 = "Searching...";
        this.Ii = null;
        this.IM = 0;
        this.Iu = 0;
        this.Im = 0;
        this.IY = 0;
        this.If = 0;
        this.ff();
    }

    private void fd() {
        WorldClient worldClient = Minecraft.theWorld();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (worldClient.isNull() || entityPlayerSP.isNull()) {
            return;
        }
        this.I0 = "Searching...";
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(worldClient.X());
        ArrayList<EntityPlayer> arrayList = new ArrayList<EntityPlayer>();
        for (Object e : copyOnWriteArrayList) {
            EntityPlayer entityPlayer;
            if (!MappedClasses.Yl.isInstance(e) || (entityPlayer = new EntityPlayer(e)).J$src$Z$fdev5g() || entityPlayer.getObject().equals(entityPlayerSP.getObject()) || entityPlayer.M$src$Z$ff28xj() || entityPlayer.S() == -420 || !(entityPlayerSP.getDistanceToEntity(entityPlayer) < 32.0f)) continue;
            arrayList.add(entityPlayer);
        }
        if (arrayList.size() > 1) {
            this.I0 = "More than one target";
        } else if (arrayList.size() == 1) {
            this.Ii = (EntityPlayer)arrayList.get(0);
            this.I0 = this.Ii.getName();
            this.ff();
        }
    }

    public String b$src$Ljava_lang_String_$tewuww() {
        return this.I0;
    }

    @EventHandler
    public void onUpdate(EventLivingUpdate eventLivingUpdate) {
        if (this.Ii == null || this.I0 == null) {
            return;
        }
        if (Minecraft.thePlayer().getDistanceToEntity(this.Ii) > 6.0f) {
            return;
        }
        if (eventLivingUpdate.getEntity().getObject().equals(Minecraft.thePlayer().getObject())) {
            ++this.If;
        }
        if (eventLivingUpdate.getEntity().getObject().equals(this.Ii.getObject())) {
            ++this.IY;
        }
        this.ff();
    }

    private boolean e() {
        WorldClient worldClient = Minecraft.theWorld();
        if (this.IA == null) {
            this.IA = worldClient;
            return true;
        }
        boolean bl = worldClient.isNotNull() && !worldClient.getObject().equals(this.IA.getObject());
        this.IA = worldClient;
        return bl;
    }
}

