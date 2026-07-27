package gg.vape;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.vape.account.AccountInfo;
import gg.vape.account.AccountInfoResponse;
import gg.vape.account.LicenseManager;
import gg.vape.api.ApiAccessTokenProvider;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.asm.helper.DescUtils;
import gg.vape.combat.AttackStrengthTracker;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.ModuleProfileMetadataCodec;
import gg.vape.config.Profile;
import gg.vape.config.PublicProfileSettings;
import gg.vape.event.ClientListenerBootstrap;
import gg.vape.event.EventBus;
import gg.vape.event.EventNameFormatRewriteService;
import gg.vape.event.IEvent;
import gg.vape.event.impl.EventRenderWorldPassExecutorDrain;
import gg.vape.event.listener.ClientSettingsEventForwarder;
import gg.vape.event.listener.EventTimingOverlayListener;
import gg.vape.event.listener.VapeClientEventListener;
import gg.vape.event.listener.VapeLifecycleEventListener;
import gg.vape.event.listener.VapeShutdownEventListener;
import gg.vape.event.listener.WorldChangeEventDispatcher;
import gg.vape.friend.FriendAliasEventListener;
import gg.vape.friend.OnlineFriendActivityListener;
import gg.vape.friend.ping.PingManager;
import gg.vape.friend.ui.OnlineActivityPanelOptions;
import gg.vape.input.InputEventDispatcher;
import gg.vape.lifecycle.ClientDirectoryCleanupCallback;
import gg.vape.manager.MacroManager;
import gg.vape.manager.ModManager;
import gg.vape.manager.SearchManager;
import gg.vape.manager.ValueManager;
import gg.vape.manager.client.EnemyManager;
import gg.vape.manager.client.FriendManager;
import gg.vape.manager.client.IndependentSettingsManager;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineFriendManager;
import gg.vape.manager.client.OnlineManager;
import gg.vape.manager.client.ProfilesManager;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapper;
import gg.vape.mapping.MappingProfileSnapshotRegistry;
import gg.vape.mapping.PrimaryMappingTaskSet;
import gg.vape.mapping.runtime.RuntimeNameMappingRegistry;
import gg.vape.module.blatant.AntiBot;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.macro.MacroEventListener;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.NameTagsRenderStateTracker;
import gg.vape.module.render.entity.RenderEntityContextCacheListener;
import gg.vape.module.utility.inventory.ItemStackSemanticResolver;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetRegistry;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerProfileValueRefreshListener;
import gg.vape.movement.PlayerMovementTaskManager;
import gg.vape.notification.NotificationManager;
import gg.vape.notification.NotificationSoundPlayer;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.NativeBridge;
import gg.vape.status.NativePresenceUpdater;
import gg.vape.sync.SyncThread;
import gg.vape.tutorial.TutorialManager;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.value.FloatingValueDropdownLayer;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.font.FontManager;
import gg.vape.ui.font.FontOption;
import gg.vape.ui.font.FontSelector;
import gg.vape.unmap.BendableInputDispatcher;
import gg.vape.unmap.GLUtils;
import gg.vape.unmap.ItemHelper;
import gg.vape.utils.AttackCooldownUtil;
import gg.vape.utils.Base64Util;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.OpenGlDeviceInfo;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionException;
import java.util.function.Predicate;

public class Vape {
    private static int opaqueState; // obfuscation opaque-predicate counter (get=getOpaqueState(), set=setOpaqueState(int)); do not treat as meaningful state
    public static Vape INSTANCE;
    public static boolean mappingsLoaded;
    public boolean enabled;
    public static boolean renderReady;
    public static final boolean DEBUG = false; // always false; likely dead compile-time debug toggle
    public static final boolean DEV = false; // always false; likely dead compile-time toggle
    public static final String VERSION;
    static TimerUtil loadTimer;
    private boolean nativeAvailable;
    private boolean mappingsRemapped;
    private boolean vanillaMinecraftPresentCache;
    private boolean renderInitialized;
    private boolean vanillaMinecraftChecked;
    private boolean pendingTickAction; // one-shot: set via setPendingTickAction(true), consumed+cleared next client tick in EventTickBase.fire()
    private boolean unclassifiedFlag463; // purpose unconfirmed: setUnclassifiedFlag463(boolean)/isUnclassifiedFlag463Set have no discoverable callers
    private FriendManager friendManager;
    private FontManager fontManager;
    private ItemHelper itemHelper;
    private ProfilesManager profilesManager;
    private ClientListenerBootstrap clientListenerBootstrap;
    private PublicProfileSettings publicProfileSettings;
    private InventoryFilterPresetRegistry inventoryFilterPresetRegistry;
    private NotificationSoundPlayer notificationSoundPlayer;
    private GLUtils glUtils;
    private SearchManager searchManager;
    private EnemyManager enemyManager;
    private PublicProfileManager publicProfileManager;
    private AccountInfo accountInfo;
    private MacroManager macroManager;
    private String cachedAllData;
    private OnlineFriendManager onlineFriendManager;
    private ModManager modManager;
    private LicenseManager licenseManager;
    private IndependentSettingsManager independentSettingsManager;
    private Boolean isLabyModCache;
    private PrimaryMappingTaskSet primaryMappingTaskSet;
    private OnlineManager onlineManager;
    private ValueManager valueManager;
    private FontSelector fontSelector;
    private Mapper mapper;
    private NotificationManager notificationManager;
    private ItemStackSemanticResolver itemStackSemanticResolver;
    private gg.vape.config.ClientSettings clientSettings;
    private NativePresenceUpdater nativePresenceUpdater;
    private Boolean isFabricPresentCache;
    private ModuleProfileMetadataCodec moduleProfileMetadataCodec;
    private Object directoryCleanupCallback;
    private SyncThread syncThread;
    private static Date buildDate;
    private static int[] supportedVersionIds;

    public boolean isOnlineConnected() {
        return OnlineConnectionManager.T.g().A();
    }

    public boolean isMappingsRemapped() {
        return this.mappingsRemapped;
    }

    public void loadConfigData(JsonObject configRoot, boolean useNewOtherDataKey) {
        JsonObject profilesData;
        JsonObject profilesElement;
        JsonArray friendsArray = ConfigJsonUtils.q(configRoot, "friends");
        if (friendsArray != null) {
            this.friendManager.loadFriends(friendsArray);
        }
        if ((profilesElement = ConfigJsonUtils.E(configRoot, "profiles")) != null) {
            profilesData = configRoot.get("profiles").getAsJsonObject();
            this.profilesManager.C(profilesData);
        } else {
            Vape.debugLog("profilesData is NULL!");
        }
        JsonArray otherData = ConfigJsonUtils.q(configRoot, useNewOtherDataKey ? "otherData" : "otherdata");
        if (otherData != null && otherData.size() > 0) {
            this.independentSettingsManager.loadIndependentSettings(otherData);
        } else {
            Vape.debugLog("otherData is NULL!");
        }
        INSTANCE.getProfilesManager().T();
    }

    public MacroManager getMacrosManager() {
        return this.macroManager;
    }

    public void loadMappings() {
        this.traceStep(18);
        int opaqueSeed = Vape.getOpaqueState();
        MappedClasses.p();
        mappingsLoaded = true;
        int opaqueBranch = opaqueSeed;
        if (opaqueBranch != 0) {
            this.traceStep(19);
            if (this.nativeAvailable && ForgeVersion.MC_26_1.v()) {
                NativeBridge.fs();
                MappedClasses.p();
                NativeBridge.rsc();
            }
            MappingProfileSnapshotRegistry.X();
            RuntimeNameMappingRegistry.initializeRegistry();
            this.traceStep(20);
            this.mapper = new Mapper();
            this.mapper.loadMappings();
            this.traceStep(21);
            MappingProfileSnapshotRegistry.y();
            if (this.isForgeRemapActive()) {
                NativeBridge.fs();
                Mapper.RF.clear();
                this.mappingsRemapped = true;
                this.mapper = new Mapper();
                this.mapper.loadMappings();
            } else {
                this.mappingsRemapped = false;
            }
            MappingProfileSnapshotRegistry.h();
            this.traceStep(22);
            NativeBridge.su(Minecraft.Q$src$Lgg_vape_account_MinecraftSessionWrapper_$1ftnn3u().M());
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                Vape.setOpaqueState(++opaqueBranch);
            }
            return;
        }
        this.traceStep(19);
        this.mapper = new Mapper();
        this.mapper.loadMappings();
        this.traceStep(21);
        MappingProfileSnapshotRegistry.y();
        this.mappingsRemapped = false;
        MappingProfileSnapshotRegistry.h();
        this.traceStep(22);
        NativeBridge.su(Minecraft.Q$src$Lgg_vape_account_MinecraftSessionWrapper_$1ftnn3u().M());
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            Vape.setOpaqueState(++opaqueBranch);
        }
    }

    public static String formatThrowable(Throwable error) {
        StringWriter stackTraceWriter = new StringWriter();
        PrintWriter stackTracePrinter = new PrintWriter(stackTraceWriter);
        error.printStackTrace(stackTracePrinter);
        return "\nException " + error.getClass().getCanonicalName() + " " + stackTraceWriter.toString();
    }

    public FriendManager getFriendManager() {
        return this.friendManager;
    }

    public InventoryFilterPresetRegistry getInventoryFilterPresetRegistry() {
        return this.inventoryFilterPresetRegistry;
    }

    private void logInitError(String phase, Throwable error) {
        StringBuilder message = new StringBuilder();
        message.append(RenderBatchManager.i(phase, error));
        message.append(Vape.formatThrowable(error));
        Vape.logError(message.toString());
        Vape.logThrowable(error);
    }

    public static void setOpaqueState(int value) {
        opaqueState = value;
    }

    public void setPendingTickAction(boolean pending) {
        this.pendingTickAction = pending;
    }

    public Mapper getMappings() {
        return this.mapper;
    }

    public static void notifyNativeStackTrace() {
        DescUtils.traceStack();
        NativeBridge.mb(101);
    }

    private static boolean lambda$registerListeners$0(IEvent event) {
        return AttackStrengthTracker.B.g();
    }

    public SearchManager getSearch() {
        return this.searchManager;
    }

    public EnemyManager getEnemyManager() {
        return this.enemyManager;
    }

    public boolean isForgeRemapInactive() {
        return !this.isForgeRemapActive();
    }

    public NotificationSoundPlayer getNotificationSoundPlayer() {
        return this.notificationSoundPlayer;
    }

    public FontSelector getFontSelector() {
        return this.fontSelector;
    }

    public void setUnclassifiedFlag463(boolean value) {
        this.unclassifiedFlag463 = value;
    }

    public boolean isFabricMinecraftPresent() {
        if (this.isFabricPresentCache == null) {
            String fabricMinecraftClass = "net/minecraft/class_310";
            this.isFabricPresentCache = NativeBridge.gc(fabricMinecraftClass) != null;
        }
        return this.isFabricPresentCache;
    }

    public ItemHelper getItemHelper() {
        return this.itemHelper;
    }

    public AccountInfo getAccountInfo() {
        return this.accountInfo;
    }

    public boolean isForgeRemapActive() {
        if (this.isLabyModPresent()) {
            return false;
        }
        return ForgeVersion.MC_26_1.v();
    }

    public static byte[] readResource(String resourceName) {
        String resourcePath = "resources/" + resourceName;
        return NativeBridge.gfb(resourcePath);
    }

    public boolean initAccountInfo() {
        ApiAccessTokenProvider.i();
        try {
            ApiResponse<AccountInfoResponse> response = ApiServices.d().G().join();
            if (!response.t()) {
                Vape.logError("Failed to initialize account details1: " + response.N());
                return false;
            }
            this.accountInfo = AccountInfo.O(response.T());
        }
        catch (CancellationException | CompletionException error) {
            Vape.logError("Failed to initialize account details2: " + Vape.formatThrowable(error));
            return false;
        }
        return true;
    }

    public boolean isFeatureDisabled() {
        return false;
    }

    public ModManager getModManager() {
        return this.modManager;
    }

    public gg.vape.config.ClientSettings getClientSettings() {
        return this.clientSettings;
    }

    public void initializeManagers() {
        this.glUtils = new GLUtils();
        this.glUtils.initializeVertexBuffer(24, 1);
        this.itemHelper = new ItemHelper();
        this.publicProfileManager = new PublicProfileManager();
        Vape.opaquePredicate();
        this.fontSelector = new FontSelector();
        this.valueManager = new ValueManager();
        this.independentSettingsManager = new IndependentSettingsManager();
        this.friendManager = new FriendManager();
        this.onlineFriendManager = new OnlineFriendManager();
        this.enemyManager = new EnemyManager();
        this.macroManager = new MacroManager();
        this.searchManager = new SearchManager();
        this.clientSettings = new gg.vape.config.ClientSettings();
        this.publicProfileSettings = new PublicProfileSettings();
        this.modManager = new ModManager();
        this.modManager.init();
        this.clientSettings.q = this.modManager.getMod(AntiBot.class);
        this.moduleProfileMetadataCodec = new ModuleProfileMetadataCodec();
        this.traceStep(23);
        this.initPrimaryMappingTasks();
        this.traceStep(24);
        this.itemStackSemanticResolver = new ItemStackSemanticResolver();
        this.itemStackSemanticResolver.p();
        try {
            this.itemStackSemanticResolver.Q();
        }
        catch (Throwable ignored) {
            // empty catch block
        }
        PotionRegistry.d();
        this.inventoryFilterPresetRegistry = new InventoryFilterPresetRegistry();
        this.profilesManager = new ProfilesManager();
        this.modManager.A();
        this.notificationManager = new NotificationManager();
        this.syncThread.loadConfig();
        this.traceStep(25);
        this.modManager.m();
        if (this.publicProfileSettings.t() != null) {
            this.getProfilesManager().L(this.publicProfileSettings.t());
        } else {
            this.getProfilesManager().M();
        }
        if (this.profilesManager.M() != null) {
            if (Vape.INSTANCE.getPublicProfileSettings().u.L().booleanValue()) {
                this.profilesManager.M().r$src$V$1goqkjq();
            }
            this.profilesManager.M().l();
        }
        this.traceStep(26);
        this.onlineManager = new OnlineManager();
        this.traceStep(27);
        this.notificationSoundPlayer = new NotificationSoundPlayer();
        InputEventDispatcher.getInstance().registerHandlers();
        this.initClientListeners();
        this.showLoadCompleteNotification();
        this.syncThread.clearPendingSave();
        this.getFontSelector().N((FontOption)Vape.INSTANCE.getPublicProfileSettings().k.K());
        this.syncThread.start();
        this.traceStep(28);
        NativeBridge.dc();
        this.registerEventListeners();
        try {
            OnlineConnectionManager.T.E();
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public ItemStackSemanticResolver getItemStackResolver() {
        return this.itemStackSemanticResolver;
    }

    public boolean isVanillaMinecraftPresent() {
        if (this.vanillaMinecraftChecked) {
            return this.vanillaMinecraftPresentCache;
        }
        this.vanillaMinecraftChecked = true;
        if (!this.nativeAvailable) {
            return false;
        }
        if (ForgeVersion.MC_26_1.d()) {
            return false;
        }
        String vanillaMinecraftClass = "net/minecraft/client/Minecraft";
        this.vanillaMinecraftPresentCache = NativeBridge.gc(vanillaMinecraftClass) != null;
        return this.vanillaMinecraftPresentCache;
    }

    public static int opaquePredicate() {
        int state = Vape.getOpaqueState();
        if (state == 0) {
            return 20;
        }
        return 0;
    }

    public boolean isUnclassifiedFlag463Set() {
        return this.unclassifiedFlag463;
    }

    public ModuleProfileMetadataCodec getModuleProfileMetadataCodec() {
        return this.moduleProfileMetadataCodec;
    }

    public boolean isLabyModPresent() {
        if (this.isLabyModCache != null) {
            return this.isLabyModCache;
        }
        if (!ForgeVersion.MC_1_8_9.L()) {
            this.isLabyModCache = false;
            return false;
        }
        try {
            Class.forName("net.laby.launcher.classloading.LabyClassLoader", false, Vape.class.getClassLoader());
            this.isLabyModCache = true;
        }
        catch (Throwable ignored) {
            this.isLabyModCache = false;
        }
        return this.isLabyModCache;
    }

    public int getAccountTier() {
        if (this.accountInfo == null) {
            return 0;
        }
        return (int)this.accountInfo.i();
    }

    public NotificationManager getNotificationManager() {
        return this.notificationManager;
    }

    public TutorialManager getTutorialManager() {
        return null;
    }

    public PrimaryMappingTaskSet getPrimaryMappingTaskSet() {
        return this.primaryMappingTaskSet;
    }

    public ValueManager getValueManager() {
        return this.valueManager;
    }

    public NativePresenceUpdater getNativePresenceUpdater() {
        return this.nativePresenceUpdater;
    }

    public boolean isTickActionPending() {
        return this.pendingTickAction;
    }

    public void saveAndStop() {
        Profile activeProfile;
        this.syncThread.markDirty();
        if (this.profilesManager != null && (activeProfile = this.profilesManager.o()) != null) {
            activeProfile.c(true);
        }
    }

    public void exportFramesConfig(String filePath) {
        try {
            String output = this.modManager.T();
            for (Frame frame : ClientSettings.G()) {
                if (frame instanceof FloatingValueDropdownLayer || !frame.J$src$Z$1eqdghz()) continue;
                String frameLine = "frame." + frame.getName().toLowerCase().replace(" ", "_") + "=" + frame.getName();
                output = output + frameLine + "\n";
            }
            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(output.getBytes());
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            exception.printStackTrace();
        }
    }

    public FontManager getFontManager() {
        return this.fontManager;
    }

    public OnlineManager getOnlineManager() {
        return this.onlineManager;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public static boolean isSupportedVersion() {
        for (int versionId : supportedVersionIds) {
            if (versionId != ForgeVersion.c()) continue;
            return true;
        }
        return false;
    }

    private static boolean lambda$registerListeners$1(IEvent event) {
        return EventBus.y;
    }

    public void initClientListeners() {
        this.primaryMappingTaskSet.X();
        this.primaryMappingTaskSet.d();
        this.clientListenerBootstrap = new ClientListenerBootstrap();
        this.clientListenerBootstrap.registerListeners();
    }

    private void registerEventListeners() {
        EventBus.getInstance().registerListener(PacketDispatchGuard.b, new Predicate[0]);
        EventBus.getInstance().registerListener(new BendableInputDispatcher(), new Predicate[0]);
        EventBus.getInstance().registerListener(AttackPacketTimingTracker.a, new Predicate[0]);
        EventBus.getInstance().registerListener(PingManager.B, new Predicate[0]);
        EventBus.getInstance().registerListener(RotationManager.b, new Predicate[0]);
        EventBus.getInstance().registerListener(PlayerMovementTaskManager.G, new Predicate[0]);
        EventBus.getInstance().registerListener(OnlineFriendActivityListener.X, new Predicate[0]);
        EventBus.getInstance().registerListener(OnlineActivityPanelOptions.p, new Predicate[0]);
        EventBus.getInstance().registerListener(AttackStrengthTracker.B, Vape::lambda$registerListeners$0);
        EventBus.getInstance().registerListener(new ClientSettingsEventForwarder(), new Predicate[0]);
        EventBus.getInstance().registerListener(new WorldChangeEventDispatcher(), new Predicate[0]);
        EventBus.getInstance().registerListener(new RenderEntityContextCacheListener(), new Predicate[0]);
        EventBus.getInstance().registerListener(new MacroEventListener(), new Predicate[0]);
        EventBus.getInstance().registerListener(new InventoryCleanerProfileValueRefreshListener(), new Predicate[0]);
        EventBus.getInstance().registerListener(EventTimingOverlayListener.e, Vape::lambda$registerListeners$1);
        EventBus.getInstance().registerListener(new VapeShutdownEventListener(), new Predicate[0]);
        EventBus.getInstance().registerListener(NameTagsRenderStateTracker.u, new Predicate[0]);
        EventBus.getInstance().registerListener(this.notificationManager, new Predicate[0]);
        EventBus.getInstance().registerListener(new AttackCooldownUtil(), new Predicate[0]);
        EventBus.getInstance().registerListener(this.modManager, new Predicate[0]);
        if (ForgeVersion.MC_1_8_9.L()) {
            EventBus.getInstance().registerListener(new VapeLifecycleEventListener(), new Predicate[0]);
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            EventBus.getInstance().registerListener(new EventNameFormatRewriteService(), new Predicate[0]);
        } else {
            EventBus.getInstance().registerListener(new FriendAliasEventListener(), new Predicate[0]);
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            EventBus.getInstance().registerListener(new VapeClientEventListener(), new Predicate[0]);
        }
    }

    private void showLoadCompleteNotification() {
        ClientSettings clientSettingsModule = INSTANCE.getModManager().getMod(ClientSettings.class);
        if (clientSettingsModule.U.L().booleanValue()) {
            this.notificationManager.k("Finished Loading", "Press " + clientSettingsModule.a().h() + " to open GUI", 5000L);
        }
    }

    public boolean isNativeAvailable() {
        return this.nativeAvailable;
    }

    public ProfilesManager getProfilesManager() {
        return this.profilesManager;
    }

    public void initializeRender() {
        if (this.renderInitialized) {
            return;
        }
        try {
            OpenGlDeviceInfo.n();
            if (GuiRenderPrimitives.d()) {
                try {
                    RenderBatchManager.M();
                }
                catch (Throwable error) {
                    this.logInitError("RenderEngine initialization", error);
                    return;
                }
            }
            try {
                this.fontManager = new FontManager();
                this.fontManager.e();
            }
            catch (Throwable error) {
                this.logInitError("FontManager initialization", error);
                return;
            }
            try {
                ImageRenderer.a();
            }
            catch (Throwable error) {
                this.logInitError("DrawTexture pre-cache", error);
                return;
            }
            try {
                GuiRenderPrimitives.B();
            }
            catch (Throwable error) {
                this.logInitError("VapeRender initialization", error);
                return;
            }
            this.renderInitialized = true;
        }
        catch (Throwable error) {
            Vape.logThrowable(error);
        }
    }

    public void traceStep(int step) {
        NativeBridge.trs(step);
    }

    public static void logThrowable(Throwable error) {
        Vape.debugLog(Vape.formatThrowable(error));
    }

    public LicenseManager getLicenseManager() {
        return this.licenseManager;
    }

    public GLUtils getGlUtils() {
        return this.glUtils;
    }

    public static void logTimed(String message) {
        NativeBridge.p(String.format("[%02d] %s", System.currentTimeMillis(), message + " " + loadTimer.getLastMS()));
        loadTimer.reset();
    }

    static {
        Vape.setOpaqueState(66);
        VERSION = "4.21";
        Object[] discardedSlotStorage = new Object[925];
        Array.newInstance(Integer.TYPE, 737);
        Array.newInstance(Byte.TYPE, 960);
        Array.newInstance(Float.TYPE, 889);
        Array.newInstance(Long.TYPE, 885);
        Array.newInstance(Character.TYPE, 731);
        Array.newInstance(Object.class, 749);
        Array.newInstance(Boolean.TYPE, 854);
        Array.newInstance(Double.TYPE, 796);
        Array.newInstance(Short.TYPE, 617);
        supportedVersionIds = new int[]{13, 15, 23, 35, 36};
        buildDate = new Date(1710640988922L);
        renderReady = false;
        mappingsLoaded = false;
        loadTimer = new TimerUtil();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void initPrimaryMappingTasks() {
        int opaqueSeed = Vape.opaquePredicate();
        this.primaryMappingTaskSet = new PrimaryMappingTaskSet();
        this.primaryMappingTaskSet.L();
        this.primaryMappingTaskSet.d();
        int opaqueBranch = opaqueSeed;
        EventRenderWorldPassExecutorDrain.E.execute(ClientSettings::w$src$V$1h5ecnl);
        try {
            while (!ClientSettings.S) {
                try {
                    Thread.sleep(10L);
                    if (opaqueBranch != 0) return;
                }
                catch (InterruptedException interrupted) {
                    Vape.logThrowable(interrupted);
                    if (opaqueBranch == 0) continue;
                    break;
                }
            }
        }
        catch (CompletionException completionException) {
            try {
                throw Vape.rethrow(completionException);
            }
            catch (CompletionException retriedCompletionException) {
                throw Vape.rethrow(retriedCompletionException);
            }
        }
        this.primaryMappingTaskSet.C();
    }

    public OnlineFriendManager getOnlineFriendManager() {
        return this.onlineFriendManager;
    }

    public IndependentSettingsManager getSettingsManager() {
        return this.independentSettingsManager;
    }

    public static void logError(String message) {
        NativeBridge.sce(message);
    }

    public static void debugLog(String message) {
    }

    private static <T extends Throwable> T rethrow(T error) {
        return error;
    }

    public SyncThread getSyncThread() {
        return this.syncThread;
    }

    public PublicProfileManager getPublicProfileManager() {
        return this.publicProfileManager;
    }

    public PublicProfileSettings getPublicProfileSettings() {
        return this.publicProfileSettings;
    }

    public static int getOpaqueState() {
        return opaqueState;
    }

    public Vape() {
        Object[] discardedSlotStorage = new Object[712];
        Array.newInstance(Character.TYPE, 862);
        Array.newInstance(Byte.TYPE, 941);
        int opaqueBranch = Vape.opaquePredicate();
        Array.newInstance(Short.TYPE, 939);
        if (opaqueBranch != 0) {
            Array.newInstance(Boolean.TYPE, 721);
            Array.newInstance(Object.class, 792);
            Array.newInstance(Integer.TYPE, 950);
            Array.newInstance(Long.TYPE, 806);
            Array.newInstance(Double.TYPE, 860);
            Array.newInstance(Float.TYPE, 555);
            this.nativePresenceUpdater = new NativePresenceUpdater();
            this.syncThread = new SyncThread(this);
            this.vanillaMinecraftChecked = false;
            this.vanillaMinecraftPresentCache = false;
            this.isFabricPresentCache = null;
            this.renderInitialized = false;
            this.isLabyModCache = null;
            this.traceStep(17);
            INSTANCE = this;
            this.nativeAvailable = NativeBridge.iv();
            this.directoryCleanupCallback = new ClientDirectoryCleanupCallback();
            GuiComponent.D(new GuiComponent[4]);
            return;
        }
        Array.newInstance(Boolean.TYPE, 721);
        Array.newInstance(Object.class, 792);
        Array.newInstance(Integer.TYPE, 950);
        Array.newInstance(Long.TYPE, 806);
        Array.newInstance(Double.TYPE, 860);
        Array.newInstance(Float.TYPE, 555);
        this.nativePresenceUpdater = new NativePresenceUpdater();
        this.syncThread = new SyncThread(this);
        this.vanillaMinecraftChecked = false;
        this.vanillaMinecraftPresentCache = false;
        this.isFabricPresentCache = null;
        this.renderInitialized = false;
        this.isLabyModCache = null;
        this.traceStep(17);
        INSTANCE = this;
        this.nativeAvailable = NativeBridge.iv();
        this.directoryCleanupCallback = new ClientDirectoryCleanupCallback();
    }

    public synchronized String getDecodedAllData(boolean refresh) {
        block6: {
            block5: {
                try {
                    if (!refresh || this.cachedAllData != null) break block5;
                }
                catch (CompletionException completionException) {
                    throw Vape.rethrow(completionException);
                }
                String encoded = NativeBridge.gp("all");
                byte[] decoded = Base64Util.decodeBase64(encoded);
                this.cachedAllData = new String(decoded);
                break block6;
            }
            try {
                if (this.cachedAllData == null) {
                    return "";
                }
            }
            catch (CompletionException completionException) {
                throw Vape.rethrow(completionException);
            }
        }
        return this.cachedAllData;
    }

    public Mapper getMappingsCompat() {
        return this.getMappings();
    }

    public Mapper getMappingsMapperCompat() {
        return this.getMappings();
    }
}
