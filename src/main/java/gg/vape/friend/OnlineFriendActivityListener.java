package gg.vape.friend;

import gg.vape.Vape;
import gg.vape.account.MinecraftSessionWrapper;
import gg.vape.config.Profile;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.event.impl.ProfileChangeEvent;
import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendActivityState;
import gg.vape.friend.OnlineFriendColorUtil;
import gg.vape.friend.PartyState;
import gg.vape.friend.activity.ActivitySnapshotPayload;
import gg.vape.input.MouseClickRateTracker;
import gg.vape.manager.client.OnlineActivityManager;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineConnectionState;
import gg.vape.manager.client.OnlineSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.blatant.KillAura;
import gg.vape.module.combat.AimAssist;
import gg.vape.module.combat.SilentAura;
import gg.vape.module.render.NameTags;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.render.OffscreenRenderContext;
import gg.vape.utils.MutableColor;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderHelper;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.ServerData;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class OnlineFriendActivityListener
implements EventListener {
    public static OnlineFriendActivityListener X = new OnlineFriendActivityListener();
    private TimerUtil g = new TimerUtil();
    private String E;
    private final SilentAura i;
    private final AimAssist f;
    private final KillAura J = Vape.INSTANCE.getModManager().getMod(KillAura.class);
    private long V;
    @Nullable
    private EntityPlayer c;
    private int d;

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (OnlineConnectionManager.T.n() != OnlineConnectionState.ONLINE) {
            return;
        }
        if (!this.g.hasTimeElapsed(1000L)) {
            return;
        }
        this.g.reset();
        LocalOnlineFriend localOnlineFriend = Vape.INSTANCE.getOnlineManager().r();
        this.F(localOnlineFriend);
        this.A(localOnlineFriend);
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (OnlineConnectionManager.T.n() != OnlineConnectionState.ONLINE) {
            return;
        }
        OnlineActivityManager onlineActivityManager = Vape.INSTANCE.getOnlineManager().V();
        OnlineFriendActivityState onlineFriendActivityState = Vape.INSTANCE.getOnlineManager().r().E();
        EntityPlayer entityPlayer = eventPrePlayerTick.getPlayer();
        WorldClient worldClient = eventPrePlayerTick.getWorld();
        if (this.c != null && System.currentTimeMillis() - this.V >= 10000L) {
            this.n(null);
        }
        onlineActivityManager.o(entityPlayer, worldClient);
        ActivitySnapshotPayload activitySnapshotPayload = OnlineFriendActivityState.f$src$Lgg_vape_friend_activity_ActivitySnapshotPayload$cbdquh(entityPlayer);
        onlineFriendActivityState.N(activitySnapshotPayload);
        int n = MouseClickRateTracker.getClicksPerSecond();
        onlineFriendActivityState.O(n);
        if (!onlineActivityManager.x()) {
            if (this.d != n) {
                ZeusConnectionManager.T().u().H(n);
            }
            this.d = n;
            onlineActivityManager.e(activitySnapshotPayload);
        }
    }

    private static int lambda$onRenderWorldLast$2(OnlineFriendActivityState onlineFriendActivityState, OnlineFriendActivityState onlineFriendActivityState2) {
        return onlineFriendActivityState.a().C().compareTo(onlineFriendActivityState2.a().C());
    }

    public OnlineFriendActivityListener() {
        this.i = Vape.INSTANCE.getModManager().getMod(SilentAura.class);
        this.f = Vape.INSTANCE.getModManager().getMod(AimAssist.class);
    }

    private void A(OnlineFriend onlineFriend) {
        OnlineActivityManager onlineActivityManager;
        ServerData serverData = Minecraft.H();
        String string = Minecraft.V() ? "Singleplayer" : (serverData.isNotNull() ? serverData.f() : null);
        String string2 = string;
        if (string2 != null) {
            if (!OnlineConnectionManager.T.S().z().getEffectiveValue().booleanValue()) {
                string = null;
            }
            if (onlineFriend.v() == null && string != null) {
                onlineFriend.V(string);
                ZeusConnectionManager.T().u().a(string);
            } else if (onlineFriend.v() != null && string == null) {
                onlineFriend.V(string);
                ZeusConnectionManager.T().u().a(string);
            }
            if (this.E != null) {
                if (this.E != null) {
                    // empty if block
                }
            } else {
                OnlineActivityManager onlineActivityManager2 = Vape.INSTANCE.getOnlineManager().V();
                if (!onlineActivityManager2.x()) {
                    onlineActivityManager2.T();
                }
            }
            this.E = string2;
            return;
        }
        if (!OnlineConnectionManager.T.S().z().getEffectiveValue().booleanValue()) {
            string = null;
        }
        if (onlineFriend.v() == null && string != null) {
            onlineFriend.V(string);
            ZeusConnectionManager.T().u().a(string);
        } else if (onlineFriend.v() != null && string == null) {
            onlineFriend.V(string);
            ZeusConnectionManager.T().u().a(string);
        }
        if (this.E == null) {
            // empty if block
        }
        if (this.E != null && !(onlineActivityManager = Vape.INSTANCE.getOnlineManager().V()).x()) {
            onlineActivityManager.T();
        }
        this.E = string2;
    }

    private static List<OnlineFriendActivityState> lambda$onRenderWorldLast$0(OnlineFriendActivityState onlineFriendActivityState, EntityPlayer entityPlayer, List<OnlineFriendActivityState> list) {
        List<OnlineFriendActivityState> list2 = list != null ? list : new ArrayList<>();
        list2.add(onlineFriendActivityState);
        return list2;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (OffscreenRenderContext.isRenderingOffscreen()) {
            return;
        }
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        WorldClient worldClient = eventRender3D.getWorld();
        if (worldClient.isNull()) {
            return;
        }
        LinkedHashMap<UUID, EntityPlayer> linkedHashMap = new LinkedHashMap<UUID, EntityPlayer>();
        for (Object entityHandle : worldClient.X()) {
            EntityPlayer entityPlayer = new EntityPlayer(entityHandle);
            linkedHashMap.put(entityPlayer.X$src$Ljava_util_UUID_$1o5dyg6(), entityPlayer);
        }
        if (linkedHashMap.isEmpty()) {
            return;
        }
        this.u(linkedHashMap.values());
        OnlineSettings onlineSettings = OnlineConnectionManager.T.S();
        LinkedHashMap<EntityPlayer, List<OnlineFriendActivityState>> statesByPlayer = new LinkedHashMap<>();
        Collection<OnlineFriendActivityState> activityStates = Vape.INSTANCE.getOnlineManager().V().X();
        for (OnlineFriendActivityState activityState : activityStates) {
            EntityPlayer entityPlayer;
            if (!partyState.c().contains(activityState.a()) || activityState.equals(Vape.INSTANCE.getOnlineManager().r().E()) && !onlineSettings.U().getEffectiveValue().booleanValue() || !activityState.Q() || (entityPlayer = linkedHashMap.get(activityState.m())) == null || entityPlayer.equals(Minecraft.thePlayer())) continue;
            statesByPlayer.compute(entityPlayer, (arg_0, arg_1) -> OnlineFriendActivityListener.lambda$onRenderWorldLast$0(activityState, arg_0, arg_1));
        }
        OnlineFriendActivityState localActivityState;
        EntityPlayer localEntityPlayer;
        if (onlineSettings.U().getEffectiveValue().booleanValue() && (localActivityState = Vape.INSTANCE.getOnlineManager().r().E()).Q() && (localEntityPlayer = linkedHashMap.get(localActivityState.m())) != null && !localEntityPlayer.equals(Minecraft.thePlayer())) {
            statesByPlayer.compute(localEntityPlayer, (arg_0, arg_1) -> OnlineFriendActivityListener.lambda$onRenderWorldLast$1(localActivityState, arg_0, arg_1));
        }
        if (statesByPlayer.isEmpty()) {
            return;
        }
        RenderUtils.g();
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        for (Map.Entry<EntityPlayer, List<OnlineFriendActivityState>> entry : statesByPlayer.entrySet()) {
            EntityPlayer entityPlayer = entry.getKey();
            List<OnlineFriendActivityState> list = entry.getValue();
            list.sort(OnlineFriendActivityListener::lambda$onRenderWorldLast$2);
            float f = eventRender3D.getTicks();
            double d4 = entityPlayer.M() + (entityPlayer.z() - entityPlayer.M()) * (double)f;
            double d5 = entityPlayer.W() + (entityPlayer.N() - entityPlayer.W()) * (double)f;
            double d6 = entityPlayer.m$src$D$fwnne5() + (entityPlayer.h() - entityPlayer.m$src$D$fwnne5()) * (double)f;
            GL11.glPushMatrix();
            GL11.glTranslated((double)(d4 - d + (double)0.03f), (double)(d5 - d2 + 0.001), (double)(d6 - d3 + (double)0.03f));
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScaled((double)0.1, (double)0.1, (double)0.1);
            float f2 = 10.0f;
            GL11.glPopMatrix();
            if (list.size() <= 0 || !onlineSettings.k$src$Lgg_vape_value_BooleanValue_$ffgfgd().getEffectiveValue().booleanValue()) continue;
            OnlineFriendActivityState onlineFriendActivityState = list.get(0);
            MutableColor mutableColor = new MutableColor(OnlineFriendColorUtil.V(onlineFriendActivityState.a())).withAlpha(150);
            GuiRenderPrimitives.R(entityPlayer.c(), entityPlayer.A(), entityPlayer.Z(), 50.0f, 0.7f, entityPlayer.Y(), mutableColor);
        }
        RenderUtils.f();
    }

    @EventHandler
    public void j(ProfileChangeEvent profileChangeEvent) {
        Profile profile = profileChangeEvent.getPreviousProfile();
        Profile profile2 = profileChangeEvent.getNewProfile();
        if (profile != null && profile.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() != null) {
            if (profile2.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() != null) {
                ZeusConnectionManager.T().u().p(profile2.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0().u());
            } else {
                ZeusConnectionManager.T().u().M();
            }
        } else if (profile2.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() != null) {
            ZeusConnectionManager.T().u().p(profile2.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0().u());
        }
    }

    private static List<OnlineFriendActivityState> r(String string) {
        ArrayList<OnlineFriendActivityState> arrayList = new ArrayList<OnlineFriendActivityState>();
        for (OnlineFriendActivityState onlineFriendActivityState : Vape.INSTANCE.getOnlineManager().V().X()) {
            if (!onlineFriendActivityState.Q() || !onlineFriendActivityState.O().equals(string)) continue;
            arrayList.add(onlineFriendActivityState);
        }
        OnlineFriendActivityState onlineFriendActivityState = Vape.INSTANCE.getOnlineManager().r().E();
        if (OnlineConnectionManager.T.S().U().getEffectiveValue().booleanValue() && onlineFriendActivityState.Q() && onlineFriendActivityState.O().equals(string)) {
            arrayList.add(onlineFriendActivityState);
        }
        return arrayList;
    }

    @Nullable
    public EntityPlayer M() {
        Wrapper wrapper;
        if (this.J.r$src$Z$14eylz9() && !this.J.targets.isEmpty()) {
            wrapper = Minecraft.currentScreen();
            if (!this.J.guiCheck.getEffectiveValue().booleanValue() || ((GuiScreen)wrapper).isNull()) {
                for (EntityLivingBase entityLivingBase : this.J.targets) {
                    if (!entityLivingBase.isInstance(MappedClasses.Yl)) continue;
                    return new EntityPlayer(entityLivingBase.getObject());
                }
            }
        }
        if (this.i.r$src$Z$14eylz9()) {
            wrapper = this.i.getTarget();
            GuiScreen currentScreen = Minecraft.currentScreen();
            if (currentScreen.isNull() && wrapper != null && wrapper.isInstance(MappedClasses.Yl)) {
                return new EntityPlayer(wrapper);
            }
        }
        EntityLivingBase aimAssistTarget;
        if (this.f.r$src$Z$14eylz9() && ((GuiScreen)(wrapper = Minecraft.currentScreen())).isNull() && (aimAssistTarget = this.f.getCurrentTarget()) != null && aimAssistTarget.isInstance(MappedClasses.Yl)) {
            return new EntityPlayer(aimAssistTarget.getObject());
        }
        if (this.c != null && System.currentTimeMillis() - this.V < 5000L) {
            return this.c;
        }
        return null;
    }

    @EventHandler
    public void W(EventWorldChange eventWorldChange) {
        Vape.INSTANCE.getOnlineManager().V().T();
        Vape.INSTANCE.getOnlineManager().N().A();
    }

    private void J(Entity entity, double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8;
        double d9;
        if (entity.M$src$Z$ff28xj()) {
            return;
        }
        double d10 = entity.c() - d5;
        double d11 = RotationUtil.y(d10, d9 = entity.A() - d6, d8 = entity.Z() - d7, d2, d3, d4);
        float f = (float)d11;
        float f2 = (double)f / 5.0 <= 2.0 ? 2.0f : (float)((double)f / 5.0);
        float f3 = 0.016666668f * f2;
        RenderUtil.d();
        RenderHelper.e();
        if (ForgeVersion.MC_1_16_5.d()) {
            if (Minecraft.gameSettings().x() == 0) {
                GL11.glTranslated((double)d10, (double)(d9 + (double)entity.Y() + 0.2), (double)d8);
                GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-Minecraft.D().getPlayerViewX()), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-Minecraft.D().getPlayerViewY()), (float)-1.0f, (float)0.0f, (float)0.0f);
            } else {
                ActiveRenderInfo activeRenderInfo = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
                double d12 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosX() - activeRenderInfo.o().getX();
                double d13 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosY() - activeRenderInfo.o().getY();
                double d14 = GuiRenderPrimitives.d() ? 0.0 : RenderManager.getInterpolatedRenderPosZ() - activeRenderInfo.o().getZ();
                GL11.glTranslated((double)(d10 + d12), (double)(d9 + d13 + (double)entity.Y() + (double)0.4f), (double)(d8 + d14));
                GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-Minecraft.D().getPlayerViewX()), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)Minecraft.D().getPlayerViewY(), (float)1.0f, (float)0.0f, (float)0.0f);
            }
        } else {
            GL11.glTranslated((double)(d10 + 0.0), (double)(d9 + (double)entity.Y() + 0.5), (double)d8);
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            if (Minecraft.gameSettings().x() == 2) {
                GL11.glRotatef((float)(-Minecraft.D().getPlayerViewX()), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)Minecraft.D().getPlayerViewY(), (float)-1.0f, (float)0.0f, (float)0.0f);
            } else {
                GL11.glRotatef((float)(-Minecraft.D().getPlayerViewX()), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)Minecraft.D().getPlayerViewY(), (float)1.0f, (float)0.0f, (float)0.0f);
            }
        }
        GL11.glScalef((float)(-f3), (float)(-f3), (float)f3);
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        float f4 = (float)(d11 / 5.0);
        float f5 = 0.01f * f4;
        GL11.glTranslated((double)0.0, (double)(-d), (double)0.0);
        GL11.glScaled((double)(1.0f / f3), (double)(1.0f / f3), (double)(-(1.0f / f3)));
        GL11.glScaled((double)f5, (double)f5, (double)f5);
        OnlineFriendActivityListener.t(entity);
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        RenderUtil.Y();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(1.0);
    }


    private void n(@Nullable EntityPlayer entityPlayer) {
        this.c = entityPlayer;
        this.V = System.currentTimeMillis();
    }

    @EventHandler
    public void y(EventPreAttack eventPreAttack) {
        if (Vape.INSTANCE.getOnlineManager().y().j() == null) {
            return;
        }
        if (eventPreAttack.getTarget().isInstance(MappedClasses.Yl)) {
            this.n(new EntityPlayer(eventPreAttack.getTarget().getObject()));
        }
    }

    public static void t(Entity entity) {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return;
        }
        if (!entity.isInstance(MappedClasses.Yl)) {
            return;
        }
        EntityPlayer entityPlayer = new EntityPlayer(entity.getObject());
        double d = 10.0;
        double d2 = 20.0;
        int n = 5;
        OnlineSettings onlineSettings = OnlineConnectionManager.T.S();
        OnlineFriendActivityState primaryState;
        if (OnlineConnectionManager.T.S().y().getEffectiveValue().booleanValue() && (primaryState = Vape.INSTANCE.getOnlineManager().V().X(entityPlayer.getName())) != null && partyState.c().contains(primaryState.a())) {
            Color color = OnlineFriendColorUtil.V(primaryState.a());
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255);
            GuiRenderPrimitives.V(-6.0, -n - 1, 12.0, 1.0, new Color(0, 0, 0, 96));
            GuiRenderPrimitives.V(-5.0, -n, 10.0, 1.0, color);
            n = (int)((double)n + 15.0);
        }
        if (OnlineConnectionManager.T.S().k$src$Lgg_vape_value_BooleanValue_$ffgfgd().getEffectiveValue().booleanValue()) {
            Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(0.0);
            OpenGlBackendHolder.backend.disableCapability(2896);
            GlStateManager.enableAlpha();
            GL11.glBlendFunc((int)770, (int)771);
            List<OnlineFriendActivityState> activityStates = OnlineFriendActivityListener.r(entityPlayer.getName());
            if (!activityStates.isEmpty() && onlineSettings.k$src$Lgg_vape_value_BooleanValue_$ffgfgd().getEffectiveValue().booleanValue()) {
                double d3 = -5.0 - (double)activityStates.size() * 20.0 / 2.0 + 10.0 - 2.0;
                for (OnlineFriendActivityState onlineFriendActivityState : activityStates) {
                    Color color = OnlineFriendColorUtil.V(onlineFriendActivityState.a());
                    color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255);
                    ImageRenderer.drawImage(new Color(0, 0, 0, 150), (float)d3 - 1.0f, (float)(-n) - 1.0f, "triangle", 16.0f, 16.0f, false);
                    ImageRenderer.drawImage(color, (float)d3, -n, "triangle", 14.0f, 14.0f, false);
                    d3 += 20.0;
                }
            }
            OpenGlBackendHolder.backend.enableCapability(2896);
            Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(0.0);
        }
    }

    private void F(OnlineFriend onlineFriend) {
        MinecraftSessionWrapper minecraftSessionWrapper = Minecraft.Q$src$Lgg_vape_account_MinecraftSessionWrapper_$1ftnn3u();
        if (minecraftSessionWrapper.isNull()) {
            return;
        }
        UUID uUID = onlineFriend.k();
        if (!onlineFriend.I().equals(minecraftSessionWrapper.M()) || uUID == null || !uUID.equals(minecraftSessionWrapper.R())) {
            onlineFriend.d(minecraftSessionWrapper.R(), minecraftSessionWrapper.M());
            ZeusConnectionManager.T().u().C(minecraftSessionWrapper.R(), minecraftSessionWrapper.M());
        }
    }

    private void u(Collection<EntityPlayer> collection) {
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d4 = entityPlayerSP.c() - d;
        double d5 = entityPlayerSP.A() - d2;
        double d6 = entityPlayerSP.Z() - d3;
        double d7 = 7.0;
        NameTags nameTags = Vape.INSTANCE.getModManager().getMod(NameTags.class);
        if (nameTags.r$src$Z$14eylz9()) {
            d7 += 7.0;
        }
        for (EntityPlayer entityPlayer : collection) {
            this.J(entityPlayer, d7, d4, d5, d6, d, d2, d3);
        }
    }

    private static List<OnlineFriendActivityState> lambda$onRenderWorldLast$1(OnlineFriendActivityState onlineFriendActivityState, EntityPlayer entityPlayer, List<OnlineFriendActivityState> list) {
        List<OnlineFriendActivityState> list2 = list != null ? list : new ArrayList<>();
        list2.add(onlineFriendActivityState);
        return list2;
    }
}
