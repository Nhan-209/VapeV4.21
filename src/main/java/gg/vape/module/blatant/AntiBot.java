package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventEntityJoinWorld;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.MinecraftVersionConstraint;
import gg.vape.module.Mod;
import gg.vape.module.blatant.AntiBotBooleanValue;
import gg.vape.module.blatant.AntiBotEntityCache;
import gg.vape.module.blatant.AntiBotModeValue;
import gg.vape.module.blatant.AntiBotStateTracker;
import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.notification.NotificationType;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.MutableColor;
import gg.vape.utils.RotationUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.GuiPlayerTabOverlayBridge;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.NetHandlerPlayClientImpl;
import gg.vape.wrapper.impl.PlayerInfo;
import gg.vape.wrapper.impl.ScorePlayerTeam;
import gg.vape.wrapper.impl.Team;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class AntiBot
extends Mod {
    private final ModeValue teamColorMode;
    private final Map<Integer, Integer> pingByEntityId;
    private final ModeOption darkBlueOption;
    private final AntiBotStateTracker stateTracker;
    private final ModeOption blueOption;
    private final ModeOption grayOption;
    private int botJoinCount = 0;
    private final BooleanValue recolorVisuals;
    private List<Object> playerInfoList;
    private final HashMap<ModeOption, Character> colorCharByOption = new HashMap();
    private Object lastWorld;
    private final ModeOption yellowOption;
    private final ModeOption greenOption;
    private final List<Object> trackedEntities;
    private final ModeOption darkPurpleOption;
    private final ModeOption darkAquaOption;
    private final ModeOption darkRedOption;
    private final BooleanValue antiBot;
    private final ModeOption redOption;
    private final AntiBotEntityCache entityCache;
    private final BooleanValue autoDetectColor;
    private final ModeOption purpleOption;
    private int tickCounter = 0;
    private final ModeOption goldOption;
    private static final char COLOR_NONE = (char)255;
    private final AntiBotBooleanValue detectedTeamColorValue;
    private final BooleanValue teamsByServer;
    private final ModeOption aquaOption;
    private final Map<Character, Color> colorByChar = new HashMap<Character, Color>();
    private final BooleanValue teamsByColor;
    private final ModeOption darkGrayOption;
    private List<UUID> playerUuids;
    private final ModeOption darkGreenOption;
    private final Map<Integer, Integer> botScoreByEntityId;
    private final ModeOption blackOption;
    private final ModeOption whiteOption;

    @Nullable
    public MutableColor F(RenderEntityContext renderEntityContext) {
        return this.f(renderEntityContext, false);
    }

    @Nullable
    public MutableColor f(RenderEntityContext renderEntityContext, boolean bl) {
        return this.O(renderEntityContext, bl, false);
    }

    private void refreshPlayerInfo() {
        WorldClient worldClient = Minecraft.theWorld();
        NetHandlerPlayClientImpl netHandlerPlayClientImpl = Minecraft.thePlayer().sendQueue();
        if (worldClient.isNull() || netHandlerPlayClientImpl.isNull()) {
            return;
        }
        this.playerInfoList = ForgeVersion.MC_1_20_6.d() ? Arrays.asList(netHandlerPlayClientImpl.getPlayerInfoMap().stream().sorted(GuiPlayerTabOverlayBridge.T()).toArray()) : GuiPlayerTabOverlayBridge.O().E(netHandlerPlayClientImpl.getPlayerInfoMap());
        ArrayList<UUID> arrayList = new ArrayList<UUID>();
        for (Object object : this.playerInfoList) {
            PlayerInfo playerInfo = new PlayerInfo(object);
            if (!playerInfo.isNotNull()) continue;
            arrayList.add(playerInfo.v().getUUID());
        }
        this.playerUuids = arrayList;
    }

    public boolean z() {
        return this.r$src$Z$14eylz9() && this.teamsByServer.L() != false;
    }

    @EventHandler
    public void E(EventEntityJoinWorld eventEntityJoinWorld) {
        if (!this.h$src$Z$6u7aex()) {
            return;
        }
        if (MappedClasses.Yl.isAssignableFrom(eventEntityJoinWorld.getEntity().getObject().getClass())) {
            ++this.botJoinCount;
        }
    }

    public char B(String string, String string2) {
        int n;
        if (string2.contains(ClientSettings.F) && (n = string2.indexOf(string)) > 0) {
            for (int i = n - 1; i >= 0; --i) {
                char c;
                String string3 = String.valueOf(string2.charAt(i));
                if (!string3.equals(ClientSettings.F) || (c = string2.charAt(i + 1)) > 'f') continue;
                return c;
            }
        }
        return '\u00ff';
    }

    public boolean g$src$Z$6tnhtk() {
        return this.P$src$Z$6h0869() && this.recolorVisuals.L() != false;
    }

    private void applyColorComponents(char c, float[] fArray) {
        switch (c) {
            case '4': {
                fArray[0] = 1.0f;
                fArray[1] = 0.0f;
                fArray[2] = 0.0f;
                break;
            }
            case 'c': {
                fArray[0] = 1.0f;
                fArray[1] = 0.33f;
                fArray[2] = 0.33f;
                break;
            }
            case '6': {
                fArray[0] = 1.0f;
                fArray[1] = 0.66f;
                fArray[2] = 0.0f;
                break;
            }
            case 'e': {
                fArray[0] = 1.0f;
                fArray[1] = 1.0f;
                fArray[2] = 0.33f;
                break;
            }
            case '2': {
                fArray[0] = 0.0f;
                fArray[1] = 0.66f;
                fArray[2] = 0.0f;
                break;
            }
            case 'a': {
                fArray[0] = 0.33f;
                fArray[1] = 1.0f;
                fArray[2] = 0.33f;
                break;
            }
            case 'b': {
                fArray[0] = 0.33f;
                fArray[1] = 1.0f;
                fArray[2] = 1.0f;
                break;
            }
            case '3': {
                fArray[0] = 0.0f;
                fArray[1] = 0.66f;
                fArray[2] = 0.66f;
                break;
            }
            case '1': {
                fArray[0] = 0.0f;
                fArray[1] = 0.0f;
                fArray[2] = 0.66f;
                break;
            }
            case '9': {
                fArray[0] = 0.33f;
                fArray[1] = 0.33f;
                fArray[2] = 1.0f;
                break;
            }
            case 'd': {
                fArray[0] = 1.0f;
                fArray[1] = 0.33f;
                fArray[2] = 1.0f;
                break;
            }
            case '5': {
                fArray[0] = 0.66f;
                fArray[1] = 0.0f;
                fArray[2] = 0.66f;
                break;
            }
            case 'f': {
                fArray[0] = 1.0f;
                fArray[1] = 1.0f;
                fArray[2] = 1.0f;
                break;
            }
            case '7': {
                fArray[0] = 0.66f;
                fArray[1] = 0.66f;
                fArray[2] = 0.66f;
                break;
            }
            case '8': {
                fArray[0] = 0.33f;
                fArray[1] = 0.33f;
                fArray[2] = 0.33f;
                break;
            }
            case '0': {
                fArray[0] = 0.0f;
                fArray[1] = 0.0f;
                fArray[2] = 0.0f;
            }
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        Serializable serializable;
        Object object;
        if (!this.h$src$Z$6u7aex()) {
            return;
        }
        this.updatePingCache();
        if (this.lastWorld == null || !eventPrePlayerTick.getWorld().getObject().equals(this.lastWorld)) {
            this.pingByEntityId.clear();
            this.botScoreByEntityId.clear();
            this.trackedEntities.clear();
            this.lastWorld = eventPrePlayerTick.getWorld().getObject();
        }
        WorldClient worldClient = eventPrePlayerTick.getWorld();
        if (this.tickCounter == 1) {
            try {
                java.util.Iterator<Map.Entry<Integer, Integer>> entityScoreIterator = this.botScoreByEntityId.entrySet().iterator();
                while (entityScoreIterator.hasNext()) {
                    Map.Entry<Integer, Integer> entityScoreEntry = entityScoreIterator.next();
                    Integer entityId = entityScoreEntry.getKey();
                    Entity entity = worldClient.V(entityId);
                    if (!entity.isNull()) continue;
                    entityScoreIterator.remove();
                }
                List<?> loadedEntities = worldClient.z();
                java.util.Iterator<Object> trackedEntityIterator = this.trackedEntities.iterator();
                while (trackedEntityIterator.hasNext()) {
                    if (loadedEntities.contains(trackedEntityIterator.next())) continue;
                    trackedEntityIterator.remove();
                }
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        boolean bl = this.getPing((EntityPlayer)(object = Minecraft.thePlayer())) == 1;
        serializable = new ArrayList();
        for (Object e : worldClient.X()) {
            boolean bl2;
            boolean bl3;
            double d;
            double d2;
            double d3;
            double d4;
            EntityPlayer entityPlayer;
            if (MappedClasses.z5.isAssignableFrom(e.getClass()) || this.botScoreByEntityId.getOrDefault((entityPlayer = new EntityPlayer(e)).S(), 0) >= 3000 && entityPlayer.n$src$Z$fx7gig()) continue;
            if (((Entity)object).l() > 250 && entityPlayer.l() <= 2 && !this.trackedEntities.contains(entityPlayer.getObject())) {
                d4 = ((Entity)object).z() - entityPlayer.z();
                d3 = ((Entity)object).N() - entityPlayer.h();
                d2 = ((Entity)object).h() - entityPlayer.h();
                d = MathUtil.sqrt(d4 * d4 + d3 * d3 + d2 * d2);
                String string = entityPlayer.Q().C();
                bl3 = string.endsWith("\u00a7c" + entityPlayer.getName() + "\u00a7r");
                if (Math.abs(d) > 3.0 && ((Entity)object).l() > 260 && this.botJoinCount <= 12 && bl3) {
                    this.trackedEntities.add(entityPlayer.getObject());
                    this.botScoreByEntityId.put(entityPlayer.S(), -20);
                }
            }
            if (entityPlayer.l() <= 150 && ((Entity)object).getDistanceToEntity(entityPlayer) < 50.0f && ((Entity)object).l() > 150 && (d = (d4 = entityPlayer.M() - entityPlayer.z()) * d4 + (d3 = entityPlayer.W() - entityPlayer.N()) * d3 + (d2 = entityPlayer.m$src$D$fwnne5() - entityPlayer.h()) * d2) > 2.0 && d < 400.0 && this.trackedEntities.contains(entityPlayer.getObject()) && (!((Entity)object).J$src$Z$fdev5g() || ((EntityPlayer)object).C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().H()) && !entityPlayer.f$src$Z$fst3rk()) {
                int n = this.botScoreByEntityId.getOrDefault(entityPlayer.S(), 0);
                if (n > -50000 && !entityPlayer.J$src$Z$fdev5g() && entityPlayer.Q().C().contains("\u00a7c" + entityPlayer.getName() + "\u00a7r") && (double)((Entity)object).getDistanceToEntity(entityPlayer) < 7.5) {
                    Vape.INSTANCE.getNotificationManager().t("\u00a7cInvalid Player Spawn", entityPlayer.Q().C() + " \u00a7fmay be a fake player!", NotificationType.WARNING, 5000L);
                    this.botScoreByEntityId.put(entityPlayer.S(), -999999);
                }
                this.botScoreByEntityId.put(entityPlayer.S(), Math.max(n - 50, -50));
            }
            if (bl && this.playerUuids.contains(entityPlayer.X$src$Ljava_util_UUID_$1o5dyg6()) && this.getPing(entityPlayer) == 1 && entityPlayer.n$src$Z$fx7gig() && this.botScoreByEntityId.getOrDefault(entityPlayer.S(), 0) > 1500) continue;
            int n = (int)Math.floor(entityPlayer.z());
            int n2 = (int)Math.floor(entityPlayer.N() + (double)entityPlayer.X());
            int n3 = (int)Math.floor(entityPlayer.h());
            BlockState blockState = null;
            if (ForgeVersion.MC_1_12_2.d()) {
                blockState = worldClient.getBlockState(BlockPos.create(n, n2, n3));
            }
            boolean bl4 = !worldClient.getBlockByPos(n, n2, n3).p(blockState);
            double d5 = entityPlayer.W() - entityPlayer.N();
            double d6 = Math.abs(entityPlayer.W() - entityPlayer.N());
            bl3 = this.C(worldClient, n, (int)(entityPlayer.N() - (d5 < 0.05 ? 0.45 : 0.9)), n3);
            boolean bl5 = bl2 = bl4 && bl3;
            if (bl2) {
                this.botScoreByEntityId.put(entityPlayer.S(), this.botScoreByEntityId.getOrDefault(entityPlayer.S(), 0) + (d6 < 0.05 ? (entityPlayer.J$src$Z$fdev5g() ? 1 : 3) : 1));
                continue;
            }
            if (!(entityPlayer.c$src$I$15a9iwo() <= 0 && !entityPlayer.P() && !entityPlayer.f$src$Z$fst3rk() && d6 > 0.1) && (!entityPlayer.J$src$Z$fdev5g() || d6 != 0.0 && !(d6 > 0.5))) continue;
            this.botScoreByEntityId.put(entityPlayer.S(), this.botScoreByEntityId.getOrDefault(entityPlayer.S(), 0) - (entityPlayer.J$src$Z$fdev5g() ? 4 : 1));
        }
        this.botJoinCount = 0;
    }

    @Override
    public void onDisable() {
        this.pingByEntityId.clear();
        this.botScoreByEntityId.clear();
        this.trackedEntities.clear();
    }

    @Nullable
    public MutableColor O(RenderEntityContext renderEntityContext, boolean bl, boolean bl2) {
        char c;
        if (!bl2 && !this.r$src$Z$14eylz9()) {
            return null;
        }
        if (!bl && !this.g$src$Z$6tnhtk()) {
            return null;
        }
        if (ForgeVersion.MC_1_21_11.d()) {
            Integer n;
            EntityPlayer entityPlayer = renderEntityContext.T();
            if (entityPlayer != null && (n = this.entityCache.r(entityPlayer)) != null) {
                return new MutableColor(new Color(n));
            }
            return null;
        }
        String string = renderEntityContext.k();
        String string2 = renderEntityContext.o();
        if (string2.startsWith(ClientSettings.F) && (c = this.B(string, string2)) != '\u00ff') {
            return new MutableColor(this.colorForChar(c));
        }
        return null;
    }

    public boolean C(WorldClient worldClient, int n, int n2, int n3) {
        BlockPos blockPos = BlockPos.create(n, n2, n3);
        BlockState blockState = worldClient.getBlockState(blockPos);
        if (blockState.isNull()) {
            return false;
        }
        Block block = blockState.getBlock();
        if (block.isNull()) {
            return false;
        }
        return block.p(blockState) || !BlockUtil.p(block);
    }

    public boolean g(Entity entity, boolean bl) {
        if (entity.isNull()) {
            return false;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entity.isInstance(MappedClasses.zS)) {
            return false;
        }
        if (entity.equals(entityPlayerSP)) {
            return false;
        }
        if (!entity.isInstance(MappedClasses.zm)) {
            return false;
        }
        if (ForgeVersion.MC_1_7_10.Y() && entity.isInstance(MappedClasses.FT)) {
            return false;
        }
        EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getObject());
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f) {
            return false;
        }
        if (bl && RotationUtil.k(entityLivingBase)) {
            return false;
        }
        if (Vape.INSTANCE.getFriendManager().isFriend(entityLivingBase)) {
            return false;
        }
        if (this.Z(entityPlayerSP, entity)) {
            return false;
        }
        return !this.S(entity);
    }

    public boolean h(WorldClient worldClient, double d, double d2, double d3) {
        boolean bl;
        boolean bl2;
        boolean bl3 = bl2 = MathUtil.roundToScale(d2 - (double)((int)d2), 1) == 0.5;
        if (bl2) {
            WorldClient worldClient2 = worldClient;
            double d4 = d;
            double d5 = d2;
            BlockState blockState = worldClient2.getBlockState(BlockPos.D(d4, d5 - 0.0, d3));
            if (blockState.isNull()) {
                return false;
            }
            Block block = blockState.getBlock();
            if (block.isNull()) {
                return false;
            }
            boolean bl4 = BlockUtil.p(block);
            if (bl4) {
                return true;
            }
            int n = (int)d2;
            int n2 = (int)d;
            WorldClient worldClient3 = worldClient;
            AntiBot antiBot = this;
            return antiBot.C(worldClient3, n2, n - 0, (int)d3);
        }
        WorldClient worldClient4 = worldClient;
        double d6 = d;
        double d7 = d2;
        BlockState blockState = worldClient4.getBlockState(BlockPos.D(d6, d7 - 0.1, d3));
        if (blockState.isNull()) {
            return false;
        }
        Block block = blockState.getBlock();
        if (block.isNull()) {
            return false;
        }
        boolean bl5 = bl = !block.p(blockState) || BlockUtil.p(block);
        if (bl) {
            return true;
        }
        int n = (int)d2;
        int n3 = (int)d;
        WorldClient worldClient5 = worldClient;
        AntiBot antiBot = this;
        return antiBot.C(worldClient5, n3, n - 1, (int)d3);
    }

    private void updatePingCache() {
        if (this.tickCounter >= 20) {
            this.tickCounter = 0;
        }
        if (this.tickCounter++ == 0) {
            this.pingByEntityId.clear();
            this.refreshPlayerInfo();
            List list = Minecraft.theWorld().X();
            for (Object object : this.playerInfoList) {
                if (object == null) continue;
                PlayerInfo playerInfo = new PlayerInfo(object);
                for (Object e : list) {
                    EntityPlayer entityPlayer = new EntityPlayer(e);
                    if (!entityPlayer.c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937().isNotNull() || !entityPlayer.c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937().equals(playerInfo.v())) continue;
                    int n = Math.max(playerInfo.z(), 0);
                    this.pingByEntityId.put(entityPlayer.S(), n);
                }
            }
        }
    }

    private static Exception identityException(Exception exception) {
        return exception;
    }

    public boolean Z(@Nullable EntityPlayerSP entityPlayerSP, Entity entity) {
        EntityPlayerSP entityPlayerSP2;
        if (!this.r$src$Z$14eylz9()) {
            return false;
        }
        if (!this.P$src$Z$6h0869() && !this.z()) {
            return false;
        }
        EntityPlayerSP entityPlayerSP3 = entityPlayerSP2 = entityPlayerSP != null ? entityPlayerSP : Minecraft.thePlayer();
        if (entity.isInstance(MappedClasses.lG)) {
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(entity);
            if (this.P$src$Z$6h0869() && (ForgeVersion.MC_1_21_11.d() ? this.entityCache.r(entityPlayerSP2, entityOtherPlayerMP) : this.stateTracker.A((ModeOption)this.teamColorMode.K(), entityOtherPlayerMP))) {
                return true;
            }
            if (this.z()) {
                Team team = this.w(entityPlayerSP2);
                if (team == null || team.isNull()) {
                    return false;
                }
                Team team2 = this.w(entityOtherPlayerMP);
                if (team2 == null || team2.isNull()) {
                    return false;
                }
                if (team.isInstance(MappedClasses.u6) && team2.isInstance(MappedClasses.u6)) {
                    String string;
                    ScorePlayerTeam scorePlayerTeam = new ScorePlayerTeam(team);
                    ScorePlayerTeam scorePlayerTeam2 = new ScorePlayerTeam(team2);
                    String string2 = scorePlayerTeam.A();
                    if (string2.equals(string = scorePlayerTeam2.A())) {
                        return true;
                    }
                }
                return team.isSameTeam(team2);
            }
        }
        return false;
    }

    public boolean S(Entity entity) {
        if (!this.h$src$Z$6u7aex()) {
            return false;
        }
        if (!ClientSettings.H) {
            return false;
        }
        if (entity.isInstance(MappedClasses.z5)) {
            return false;
        }
        if (entity.isInstance(MappedClasses.Yl)) {
            boolean bl;
            int n;
            EntityPlayer entityPlayer = new EntityPlayer(entity);
            if (this.getPing(Minecraft.thePlayer()) == 1 && (n = this.getPing(entityPlayer)) != -1) {
                boolean bl2;
                boolean bl3 = bl2 = n == 1;
                return !bl2;
            }
            String string = entityPlayer.Q().C();
            if (this.botScoreByEntityId.getOrDefault(entityPlayer.S(), 0) < 15) {
                return true;
            }
            if (entityPlayer.w$src$Z$1iu64de()) {
                return true;
            }
            boolean bl4 = bl = !this.playerUuids.contains(entityPlayer.X$src$Ljava_util_UUID_$1o5dyg6());
            if (string.equals("\u00a7r" + entityPlayer.getName() + "\u00a7r") || string.equals(entityPlayer.getName() + "\u00a7r") || string.contains("[NPC]")) {
                return bl;
            }
        }
        return false;
    }

    public boolean o(Entity entity) {
        return this.Z(null, entity);
    }

    public ModeValue K() {
        return this.teamColorMode;
    }

    private int getPing(EntityPlayer entityPlayer) {
        return this.pingByEntityId.getOrDefault(entityPlayer.S(), -1);
    }

    public AntiBot() {
        super("TargetFilter", -28416, Category.Y, "");
        this.teamsByServer = BooleanValue.create(this, "Teams by server", false, "Ignore players on your team designated by the server\n\u00a7cThis is not guaranteed to be accurate, as server teams are assigned by the server");
        this.teamsByColor = BooleanValue.create(this, "Teams by color", false, "Ignore players with the selected name color\n\u00a7cThis is not guaranteed to be accurate - team colors depend on the server implementation");
        this.recolorVisuals = BooleanValue.create(this, "Recolor visuals", false, "Changes colors of visuals(Tracers, ESP) to their according team color");
        this.autoDetectColor = BooleanValue.create(this, "Auto-Detect color", true, "Automatically detects your team color\n\u00a7cThis is not guaranteed to be accurate, this relies on the server giving you the same name color as your teammates");
        this.antiBot = BooleanValue.create(this, "AntiBot", false, "Prevents modules from attacking bots");
        this.trackedEntities = new ArrayList<Object>();
        this.botScoreByEntityId = new HashMap<Integer, Integer>();
        this.pingByEntityId = new HashMap<Integer, Integer>();
        this.playerInfoList = new ArrayList<Object>();
        this.playerUuids = new ArrayList<UUID>();
        this.greenOption = new ModeOption("\u00a7aGreen", 0.8);
        this.colorCharByOption.put(this.greenOption, Character.valueOf('a'));
        this.darkGreenOption = new ModeOption("\u00a72Dark Green", 0.8);
        this.colorCharByOption.put(this.darkGreenOption, Character.valueOf('2'));
        this.redOption = new ModeOption("\u00a7cRed", 0.8);
        this.colorCharByOption.put(this.redOption, Character.valueOf('c'));
        this.darkRedOption = new ModeOption("\u00a74Dark Red", 0.8);
        this.colorCharByOption.put(this.darkRedOption, Character.valueOf('4'));
        this.yellowOption = new ModeOption("\u00a7eYellow", 0.8);
        this.colorCharByOption.put(this.yellowOption, Character.valueOf('e'));
        this.goldOption = new ModeOption("\u00a76Gold", 0.8);
        this.colorCharByOption.put(this.goldOption, Character.valueOf('6'));
        this.blueOption = new ModeOption("\u00a79Blue", 0.8);
        this.colorCharByOption.put(this.blueOption, Character.valueOf('9'));
        this.darkBlueOption = new ModeOption("\u00a71Dark Blue", 0.8);
        this.colorCharByOption.put(this.darkBlueOption, Character.valueOf('1'));
        this.aquaOption = new ModeOption("\u00a7bAqua", 0.8);
        this.colorCharByOption.put(this.aquaOption, Character.valueOf('b'));
        this.darkAquaOption = new ModeOption("\u00a73Dark Aqua", 0.8);
        this.colorCharByOption.put(this.darkAquaOption, Character.valueOf('3'));
        this.purpleOption = new ModeOption("\u00a7dPurple", 0.8);
        this.colorCharByOption.put(this.purpleOption, Character.valueOf('d'));
        this.darkPurpleOption = new ModeOption("\u00a75Dark Purple", 0.8);
        this.colorCharByOption.put(this.darkPurpleOption, Character.valueOf('5'));
        this.grayOption = new ModeOption("\u00a77Gray", 0.8);
        this.colorCharByOption.put(this.grayOption, Character.valueOf('7'));
        this.darkGrayOption = new ModeOption("\u00a78Dark Gray", 0.8);
        this.colorCharByOption.put(this.darkGrayOption, Character.valueOf('8'));
        this.whiteOption = new ModeOption("\u00a7fWhite", 0.8);
        this.colorCharByOption.put(this.whiteOption, Character.valueOf('f'));
        this.blackOption = new ModeOption("\u00a70Black", 0.8);
        this.colorCharByOption.put(this.blackOption, Character.valueOf('0'));
        this.stateTracker = new AntiBotStateTracker(this.colorCharByOption);
        this.entityCache = new AntiBotEntityCache();
        this.teamColorMode = AntiBotModeValue.u(this, "Your team color", "Uses this color to determine your team", this.greenOption, 2, this.darkRedOption, this.redOption, this.goldOption, this.yellowOption, this.darkGreenOption, this.greenOption, this.aquaOption, this.darkAquaOption, this.darkBlueOption, this.blueOption, this.purpleOption, this.darkPurpleOption, this.whiteOption, this.grayOption, this.darkGrayOption, this.blackOption);
        this.detectedTeamColorValue = AntiBotBooleanValue.i(this, "Your team color", "Shows your detected team color", null);
        if (ForgeVersion.MC_1_21_11.d()) {
            this.teamsByColor.K(this.recolorVisuals, this.detectedTeamColorValue);
        } else {
            this.teamsByColor.K(this.recolorVisuals, this.autoDetectColor, this.teamColorMode);
        }
        this.addValue(this.teamsByServer, this.teamsByColor, this.recolorVisuals);
        this.U(this.autoDetectColor, ForgeVersion.MC_1_21_11.b());
        this.U(this.teamColorMode, ForgeVersion.MC_1_21_11.b());
        this.U(this.detectedTeamColorValue, ForgeVersion.MC_1_21_11.n());
        this.U(this.antiBot, new MinecraftVersionConstraint[0]);
    }

    public AntiBotBooleanValue P() {
        return this.detectedTeamColorValue;
    }

    public boolean P$src$Z$6h0869() {
        return this.r$src$Z$14eylz9() && this.teamsByColor.L() != false;
    }

    public boolean h$src$Z$6u7aex() {
        return this.r$src$Z$14eylz9() && this.antiBot.L() != false;
    }

    @Nullable
    public Team w(Entity entity) {
        if (!entity.isInstance(MappedClasses.Yl)) {
            return null;
        }
        EntityPlayer entityPlayer = new EntityPlayer(entity);
        Team team = entityPlayer.J$src$Lgg_vape_wrapper_impl_Team_$1jrmnx4();
        if (team.isNotNull()) {
            return team;
        }
        GameProfile gameProfile = entityPlayer.c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937();
        UUID uUID = gameProfile.getUUID();
        for (Object e : Minecraft.N().getPlayerInfoMap()) {
            ScorePlayerTeam scorePlayerTeam;
            PlayerInfo playerInfo = new PlayerInfo(e);
            GameProfile gameProfile2 = playerInfo.v();
            if (!gameProfile2.getUUID().equals(uUID) || !(scorePlayerTeam = playerInfo.X()).isNotNull()) continue;
            return scorePlayerTeam;
        }
        return null;
    }

    private Color colorForChar(char c) {
        Color color = this.colorByChar.get(Character.valueOf(c));
        if (color != null) {
            return color;
        }
        float[] fArray = new float[4];
        this.applyColorComponents(c, fArray);
        Color color2 = new Color((int)(fArray[0] * 255.0f), (int)(fArray[1] * 255.0f), (int)(fArray[2] * 255.0f));
        this.colorByChar.put(Character.valueOf(c), color2);
        return color2;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        ModeOption modeOption;
        if (!this.r$src$Z$14eylz9() || !this.teamsByColor.L().booleanValue() || !this.autoDetectColor.L().booleanValue() && !ForgeVersion.MC_1_21_11.d()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        if (ForgeVersion.MC_1_21_11.d()) {
            Integer n = this.entityCache.r(entityPlayerSP);
            if (n != null) {
                this.detectedTeamColorValue.f(true);
                this.detectedTeamColorValue.o(n);
                this.detectedTeamColorValue.f(false);
            }
            return;
        }
        char c = this.stateTracker.s(entityPlayerSP);
        if (c != '\u00ff' && (modeOption = this.stateTracker.c(c)) != null) {
            this.teamColorMode.f(true);
            this.teamColorMode.setValue(modeOption);
            this.teamColorMode.f(false);
        }
    }
}
