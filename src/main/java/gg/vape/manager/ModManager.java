package gg.vape.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventModStateChange;
import gg.vape.module.Category;
import gg.vape.module.MinecraftVersionConstraint;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.blatant.AnchorMacro;
import gg.vape.module.blatant.AntiBot;
import gg.vape.module.blatant.AutoSoup;
import gg.vape.module.blatant.Backtrack;
import gg.vape.module.blatant.BlinkPacketRenderModule;
import gg.vape.module.blatant.BlockIn;
import gg.vape.module.blatant.Fly;
import gg.vape.module.blatant.HitBoxes;
import gg.vape.module.blatant.InvWalk;
import gg.vape.module.blatant.KeepSprint;
import gg.vape.module.blatant.KillAura;
import gg.vape.module.blatant.NoFall;
import gg.vape.module.blatant.NoSlow;
import gg.vape.module.blatant.SafeWalk;
import gg.vape.module.blatant.Scaffold;
import gg.vape.module.blatant.Speed;
import gg.vape.module.blatant.Timer;
import gg.vape.module.combat.AimAssist;
import gg.vape.module.combat.AutoClicker;
import gg.vape.module.combat.AutoClickerInputModule;
import gg.vape.module.combat.HitSwap;
import gg.vape.module.combat.BlockHit;
import gg.vape.module.combat.BowAimbot;
import gg.vape.module.combat.CrystalAura;
import gg.vape.module.combat.LeftClicker;
import gg.vape.module.combat.Reach;
import gg.vape.module.combat.RightClicker;
import gg.vape.module.combat.SilentAura;
import gg.vape.module.combat.SilentAuraTargetingModule;
import gg.vape.module.combat.Sprint;
import gg.vape.module.combat.WTap;
import gg.vape.module.combat.Velocity;
import gg.vape.module.combat.VelocityPacketMode;
import gg.vape.module.combat.VelocityPacketReceiveMode;
import gg.vape.module.combat.HitSelect;
import gg.vape.module.combat.silentaura.SilentAuraClicker;
import gg.vape.module.minigame.BedPlates;
import gg.vape.module.minigame.MurderMystery;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.MouseDelayFix;
import gg.vape.module.none.Search;
import gg.vape.module.none.TextGuiSettings;
import gg.vape.module.none.XRay;
import gg.vape.module.other.AntiAFK;
import gg.vape.module.render.Animations;
import gg.vape.module.render.AntiDebuff;
import gg.vape.module.render.Arrows;
import gg.vape.module.render.Chams;
import gg.vape.module.render.ESP;
import gg.vape.module.render.Explosions;
import gg.vape.module.render.Freecam;
import gg.vape.module.render.Fullbright;
import gg.vape.module.render.HealthDisplay;
import gg.vape.module.render.Indicators;
import gg.vape.module.render.ItemESP;
import gg.vape.module.render.NameTags;
import gg.vape.module.render.PearlESP;
import gg.vape.module.render.SpawnerFinder;
import gg.vape.module.render.StorageESP;
import gg.vape.module.render.Tracers;
import gg.vape.module.render.Trajectories;
import gg.vape.module.render.hud.ArmorStatusHudModule;
import gg.vape.module.render.hud.BlockOverlayHudModule;
import gg.vape.module.render.hud.BlockRenderColorOverrideHudModule;
import gg.vape.module.render.hud.BlockhitAnimationHudModule;
import gg.vape.module.render.hud.ClockHudModule;
import gg.vape.module.render.hud.CompassHudModule;
import gg.vape.module.render.hud.CoordinatesHudModule;
import gg.vape.module.render.hud.FpsDisplayHudModule;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.InventoryBlurHudModule;
import gg.vape.module.render.hud.KeystrokesHudModule;
import gg.vape.module.render.hud.NoClickDelayHudModule;
import gg.vape.module.render.hud.NoFogHudModule;
import gg.vape.module.render.hud.NoHurtCameraHudModule;
import gg.vape.module.render.hud.NoHurtDelayHudModule;
import gg.vape.module.render.hud.PotionEffectsHudModule;
import gg.vape.module.render.hud.ReachDisplayHudModule;
import gg.vape.module.render.hud.ScoreboardHudModule;
import gg.vape.module.render.hud.TimeChangerHudModule;
import gg.vape.module.render.hud.WeatherChangerHudModule;
import gg.vape.module.render.proj.Projectiles;
import gg.vape.module.utility.ArmorSwitch;
import gg.vape.module.utility.AutoArmor;
import gg.vape.module.utility.AutoHotbar;
import gg.vape.module.utility.AutoMLG;
import gg.vape.module.utility.AutoPearl;
import gg.vape.module.utility.AutoTool;
import gg.vape.module.utility.AutoTotem;
import gg.vape.module.utility.Clutch;
import gg.vape.module.utility.InvCleaner;
import gg.vape.module.utility.InventoryManager;
import gg.vape.module.utility.MLG;
import gg.vape.module.utility.Panic;
import gg.vape.module.utility.Parkour;
import gg.vape.module.utility.Refill;
import gg.vape.module.utility.ThrowDebuff;
import gg.vape.module.utility.Throwpot;
import gg.vape.module.utility.WindChargeJump;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.module.world.BedBreaker;
import gg.vape.module.world.ChestSteal;
import gg.vape.module.world.FastPlace;
import gg.vape.module.world.FastUseModule;
import gg.vape.notification.NotificationType;
import gg.vape.notification.ReusableTextNotification;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.ModeValue;
import gg.vape.value.SubModuleValue;
import gg.vape.value.Value;
import gg.vape.wrapper.impl.ForgeVersion;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

public class ModManager
implements EventListener {
    private static GuiComponent[] L;
    private Map<Class<? extends Mod>, Mod> slot_o_1_4;
    private ArrayList<Mod> slot_o_1_116;
    private HashSet<Mod> slot_o_1_155;
    private XRay slot_o_1_383;
    private ReusableTextNotification slot_o_1_661;
    private Map<Class<? extends Mod>, Mod> slot_o_1_757;
    private boolean slot_o_524_110;

    public Collection<Mod> f() {
        ArrayList<Mod> arrayList = new ArrayList<Mod>();
        for (Mod mod : this.s()) {
            if (mod instanceof SubModule) continue;
            arrayList.add(mod);
        }
        return arrayList;
    }

    public void init() {
        GuiComponent[] guiComponentArray = Category.N$src$ALgg_vape_ui_click_component_GuiComponent_$lect5v();
        Mod[] modArray = new Mod[61];
        modArray[0] = new ClientSettings();
        modArray[1] = new LeftClicker();
        modArray[2] = new RightClicker();
        modArray[3] = new Velocity();
        modArray[4] = new VelocityPacketReceiveMode();
        modArray[5] = new VelocityPacketMode();
        modArray[6] = new Reach();
        modArray[7] = new Throwpot();
        modArray[8] = new Refill();
        modArray[9] = new Tracers();
        modArray[10] = new NameTags();
        modArray[11] = new Search();
        modArray[12] = new ESP();
        modArray[13] = new ChestSteal();
        modArray[14] = new KeepSprint();
        modArray[15] = new FastPlace();
        modArray[16] = new HitBoxes();
        modArray[17] = new SpawnerFinder();
        modArray[18] = new StorageESP();
        modArray[19] = new Scaffold();
        modArray[20] = new Fullbright();
        modArray[21] = new WTap();
        modArray[22] = new AutoArmor();
        modArray[23] = new AutoHotbar();
        modArray[24] = new ThrowDebuff();
        modArray[25] = new AutoTool();
        modArray[26] = new AimAssist();
        modArray[27] = new Trajectories();
        modArray[28] = new AntiDebuff();
        modArray[29] = new SafeWalk();
        modArray[30] = new Projectiles();
        modArray[31] = new Fly();
        modArray[32] = new KillAura();
        modArray[33] = new Arrows();
        modArray[34] = new BlinkPacketRenderModule();
        modArray[35] = new AutoPearl();
        modArray[36] = new Panic();
        modArray[37] = new AntiAFK();
        modArray[38] = new ArmorSwitch();
        modArray[39] = new ItemESP();
        modArray[40] = new AutoMLG();
        modArray[41] = new InventoryManager();
        modArray[42] = new AutoSoup();
        modArray[43] = new Explosions();
        modArray[44] = new Parkour();
        modArray[45] = new MurderMystery();
        modArray[46] = new BowAimbot();
        modArray[47] = new Indicators();
        modArray[48] = new Sprint();
        modArray[49] = new HealthDisplay();
        modArray[50] = new HitSelect();
        modArray[51] = new Animations();
        SilentAura silentAura = new SilentAura();
        modArray[52] = silentAura;
        modArray[53] = new SilentAuraClicker(silentAura);
        modArray[54] = new SilentAuraTargetingModule();
        modArray[55] = new Clutch();
        modArray[56] = new InvCleaner();
        modArray[57] = new BlockHit();
        modArray[58] = new Timer();
        modArray[59] = new AutoClickerInputModule();
        modArray[60] = new BedPlates();
        this.L(Stream.of(modArray));
        ModRegistrationBuilder.X().O(new PearlESP()).H(ForgeVersion.MC_1_16_5.b()).e(this);
        Mod[] modArray2 = new Mod[2];
        modArray2[0] = new Chams();
        XRay xRay = new XRay();
        ModManager modManager = this;
        modManager.slot_o_1_383 = xRay;
        modArray2[1] = xRay;
        this.Y(Stream.of(modArray2), ModManager::lambda$addModules$0);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        ModRegistrationBuilder.X().O(new Freecam()).H(ForgeVersion.MC_1_16_5.b()).H(ForgeVersion.MC_1_21_11.n()).e(this);
        this.Y(Stream.of(new InvWalk()), ModManager::lambda$addModules$1);
        this.Y(Stream.of(new Backtrack()), ModManager::lambda$addModules$2);
        this.Y(Stream.of(new MLG(), new BedBreaker(), new BlockIn(), new FastUseModule()), ModManager::lambda$addModules$3);
        this.Y(Stream.of(new BedPlates()), ModManager::lambda$addModules$4);
        this.L(Stream.of(new AntiBot()));
        this.Y(Stream.of(new AutoClicker(), new HitSwap(), new AnchorMacro(), new WindChargeJump(), new CrystalAura(), new AutoTotem()), ModManager::lambda$addModules$5);
        this.Y(Stream.of(new NoFall(), new NoSlow(), new Speed(), new BlockHit(), new Timer()), ModManager::lambda$addModules$6);
        this.h();
        this.c();
        if (guiComponentArray2 == null) {
            GuiComponent.D(new GuiComponent[2]);
        }
    }

    public boolean getState(Class<? extends Mod> clazz) {
        for (Mod mod : this.collectMods()) {
            if (!mod.getClass().equals(clazz)) continue;
            return mod.r$src$Z$14eylz9();
        }
        return false;
    }

    private static void lambda$addLegitModules$7(ModRegistrationBuilder modRegistrationBuilder) {
        modRegistrationBuilder.H(ForgeVersion.MC_1_20_6.b());
    }

    public JsonObject getJsonObj() {
        JsonObject jsonObject = new JsonObject();
        for (Mod mod : this.s()) {
            if (mod instanceof HudModule || !mod.r$src$Z$14eylz9()) continue;
            jsonObject.addProperty(mod.getName(), Boolean.valueOf(mod.r$src$Z$14eylz9()));
        }
        return jsonObject;
    }

    public void S(Profile profile) {
        boolean bl = true;
        ModManager modManager = this;
        modManager.slot_o_524_110 = bl;
        JsonObject jsonObject = profile.V();
        int n = 0;
        for (Mod mod : this.collectMods()) {
            if (mod instanceof HudModule || mod.getCategory().equals(Category.b)) continue;
            try {
                if (jsonObject.has(mod.getName())) {
                    if (!mod.O()) continue;
                    try {
                        if (mod.r$src$Z$14eylz9()) continue;
                        mod.Y(jsonObject.get(mod.getName()).getAsBoolean());
                        ++n;
                    }
                    catch (Exception exception) {
                        Vape.logThrowable(exception);
                    }
                    continue;
                }
                if (mod instanceof ClientSettings || mod.h() == 0 || !mod.r$src$Z$14eylz9()) continue;
                mod.F();
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        boolean bl2 = false;
        ModManager modManager2 = this;
        modManager2.slot_o_524_110 = bl2;
        if (Vape.INSTANCE.getPublicProfileSettings().A.L().booleanValue()) {
            this.slot_o_1_661.S("Profile swap to " + gg.vape.config.ClientSettings.F + "6" + profile.n$src$Ljava_lang_String_$xqhelw()).m(n + " modules enabled").B();
            Vape.INSTANCE.getNotificationManager().m(this.slot_o_1_661);
        }
    }

    private static void lambda$addModules$6(ModRegistrationBuilder modRegistrationBuilder) {
        modRegistrationBuilder.H(ForgeVersion.MC_1_21_4.b());
    }

    public void A() {
        for (Mod mod : this.slot_o_1_4.values()) {
            mod.I();
        }
    }

    private void Y(Stream<Mod> stream, Consumer<ModRegistrationBuilder<?>> consumer) {
        stream.forEach(arg_0 -> this.lambda$registerStream$8(consumer, arg_0));
    }

    public HashSet<Mod> getMods() {
        return this.slot_o_1_155;
    }

    public static void Q(GuiComponent[] guiComponentArray) {
        L = guiComponentArray;
    }

    private void L(Stream<Mod> stream) {
        this.Y(stream, ModManager::lambda$registerStream$9);
    }

    void d(Mod mod, List<List<MinecraftVersionConstraint>> list, boolean bl) {
        this.slot_o_1_4.put(mod.getClass(), mod);
        if (!list.isEmpty()) {
            boolean bl2 = false;
            for (List<MinecraftVersionConstraint> list2 : list) {
                List<MinecraftVersionConstraint> activeConstraints = MinecraftVersionConstraint.o(list2);
                if (!activeConstraints.isEmpty()) continue;
                bl2 = true;
            }
            if (!bl2) {
                return;
            }
        }
        this.slot_o_1_757.put(mod.getClass(), mod);
        this.slot_o_1_116.add(mod);
        for (Value<?, ?> value : mod.F$src$Ljava_util_List_$1kytx9u()) {
            if (!(value instanceof ModeValue)) continue;
            ModeValue modeValue = (ModeValue)value;
            for (ModeSelection modeSelection : modeValue.getModes()) {
                SubModuleValue subModuleValue;
                if (!(modeSelection instanceof SubModuleValue) || !((SubModule)(subModuleValue = (SubModuleValue)modeSelection).getInstance()).U()) continue;
                this.d((Mod)subModuleValue.getInstance(), list, false);
                mod.k(new SubModule[]{subModuleValue.getInstance()});
            }
        }
        if (bl) {
            mod.Y(true);
        }
    }

    private void h() {
        ModRegistrationBuilder.X().O(new TextGuiSettings()).e(this);
    }

    private void lambda$registerStream$8(Consumer consumer, Mod mod) {
        ModRegistrationBuilder<Mod> modRegistrationBuilder = ModRegistrationBuilder.X().O(mod);
        consumer.accept(modRegistrationBuilder);
        modRegistrationBuilder.e(this);
    }

    public void m() {
        for (Mod mod : this.slot_o_1_757.values()) {
            mod.t();
        }
        if (ForgeVersion.MC_1_8_9.L()) {
            if (Vape.INSTANCE.isOnlineConnected()) {
                this.getMod(NoClickDelayHudModule.class).Y(true);
                this.getMod(MouseDelayFix.class).Y(true);
            }
            if (!this.getMod(MouseDelayFix.class).r$src$Z$14eylz9()) {
                this.getMod(MouseDelayFix.class).Y(true);
            }
        }
    }

    public String T() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Mod mod : this.collectMods()) {
            String string = mod.getName();
            String string2 = string.replace(" ", "_").toLowerCase();
            stringBuilder.append(string2 + "=" + string);
            stringBuilder.append("\n");
            if (mod.n() != null && !mod.n().equals("")) {
                stringBuilder.append(string2 + ".tooltip=" + mod.n().replace("\n", " "));
                stringBuilder.append("\n");
            }
            for (Value<?, ?> value : mod.F$src$Ljava_util_List_$1kytx9u()) {
                String string3 = value.getName();
                String string4 = string2 + "." + value.getName().replace(" ", "_").toLowerCase();
                stringBuilder.append(string4 + "=" + string3);
                stringBuilder.append("\n");
                if (value.w$src$Ljava_lang_String_$ikqblg() == null || value.w$src$Ljava_lang_String_$ikqblg().isEmpty()) continue;
                String string5 = value.w$src$Ljava_lang_String_$ikqblg().replace("\n", " ");
                String string6 = string4 + ".tooltip";
                stringBuilder.append(string6 + "=" + string5);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @EventHandler
    public void a(EventModStateChange eventModStateChange) {
        Mod mod = eventModStateChange.getModule();
        if (mod.r$src$Z$14eylz9()) {
            this.slot_o_1_155.add(mod);
        } else {
            this.slot_o_1_155.remove(mod);
        }
        for (Mod mod2 : this.getMods()) {
            mod2.U(mod);
        }
        if (ClientSettings.fW.v() && mod.q$src$Z$12h8h4c() && Vape.INSTANCE.getPublicProfileSettings().r.L().booleanValue() && !this.slot_o_524_110) {
            mod.B();
        }
    }

    @EventHandler
    public void y(EventModStateChange eventModStateChange) {
        Mod mod = eventModStateChange.getModule();
        if (eventModStateChange.isEnabled()) {
            mod.j();
        }
    }

    private static void lambda$addModules$5(ModRegistrationBuilder modRegistrationBuilder) {
        modRegistrationBuilder.H(ForgeVersion.MC_1_21_4.n());
    }

    static {
        ModManager.Q(new GuiComponent[4]);
    }

    private void c() {
        ModRegistrationBuilder.X().O(new FreeLookHudModule()).e(this);
        ModRegistrationBuilder.X().O(new NoClickDelayHudModule()).H(ForgeVersion.MC_1_7_10.N()).e(this);
        ModRegistrationBuilder.X().O(new MouseDelayFix()).H(ForgeVersion.MC_1_8_9.S()).e(this);
        this.L(Stream.of(new KeystrokesHudModule(), new ClockHudModule(), new PotionEffectsHudModule()));
        ModRegistrationBuilder.X().O(new BlockhitAnimationHudModule()).H(ForgeVersion.MC_1_8_9.S()).e(this);
        this.L(Stream.of(new NoHurtDelayHudModule(), new ArmorStatusHudModule(), new CompassHudModule(), new WeatherChangerHudModule(), new NoHurtCameraHudModule(), new TimeChangerHudModule(), new CoordinatesHudModule(), new FpsDisplayHudModule(), new ReachDisplayHudModule(), new NoFogHudModule(), new BlockOverlayHudModule()));
        ModRegistrationBuilder.X().O(new BlockRenderColorOverrideHudModule()).u(ForgeVersion.MC_1_7_10.N(), ForgeVersion.MC_1_16_5.b()).e(this);
        this.Y(Stream.of(new ScoreboardHudModule(), new InventoryBlurHudModule()), ModManager::lambda$addLegitModules$7);
    }

    public int x(Category category) {
        int n = 0;
        for (Mod mod : this.slot_o_1_757.values()) {
            if (mod.getCategory() != category || !mod.r$src$Z$14eylz9()) continue;
            ++n;
        }
        return n;
    }

    @Nullable
    public XRay G() {
        return this.slot_o_1_383;
    }

    public JsonArray toJson(boolean bl) {
        JsonArray jsonArray = new JsonArray();
        for (Mod mod : this.f()) {
            JsonObject jsonObject = mod.q(bl);
            if (jsonObject == null) continue;
            jsonArray.add((JsonElement)jsonObject);
        }
        return jsonArray;
    }

    public JsonObject e() {
        JsonObject jsonObject = new JsonObject();
        for (Mod mod : this.s()) {
            if (!(mod instanceof HudModule) || !mod.r$src$Z$14eylz9()) continue;
            jsonObject.addProperty(mod.getName(), Boolean.valueOf(mod.r$src$Z$14eylz9()));
        }
        return jsonObject;
    }

    public static GuiComponent[] S() {
        return L;
    }

    private static void lambda$addModules$2(ModRegistrationBuilder modRegistrationBuilder) {
        modRegistrationBuilder.H(ForgeVersion.MC_1_8_9.H()).H(ForgeVersion.MC_1_21_4.n());
    }

    public void T(JsonObject jsonObject) {
        for (Mod mod : this.collectMods()) {
            if (!(mod instanceof HudModule) || !jsonObject.has(mod.getName())) continue;
            boolean bl = jsonObject.get(mod.getName()).getAsBoolean();
            if (mod.r$src$Z$14eylz9() == bl) continue;
            mod.Y(bl);
        }
    }

    private static void lambda$addModules$1(ModRegistrationBuilder modRegistrationBuilder) {
        modRegistrationBuilder.H(ForgeVersion.MC_1_8_9.H());
    }

    public <T extends Mod> T getMod(Class<T> clazz) {
        return (T)((Mod)this.slot_o_1_757.get(clazz));
    }

    public boolean N(Class<? extends InventoryActionModule> clazz) {
        for (Mod mod : this.slot_o_1_757.values()) {
            if (mod.getClass() == clazz || !(mod instanceof InventoryActionModule)) continue;
            InventoryActionModule inventoryActionModule = (InventoryActionModule)((Object)mod);
            if (!mod.r$src$Z$14eylz9() || !inventoryActionModule.x()) continue;
            return true;
        }
        return false;
    }

    public void y() {
        boolean bl = true;
        ModManager modManager = this;
        modManager.slot_o_524_110 = bl;
        for (Mod mod : this.collectMods()) {
            if (mod.getCategory() == Category.b || !mod.r$src$Z$14eylz9() || mod instanceof HudModule) continue;
            mod.Y(false);
        }
        boolean bl2 = false;
        ModManager modManager2 = this;
        modManager2.slot_o_524_110 = bl2;
    }

    public Mod getMod(String string) {
        for (Map.Entry<Class<? extends Mod>, Mod> entry : this.slot_o_1_757.entrySet()) {
            if (!((Mod)entry.getValue()).getName().equals(string)) continue;
            return (Mod)entry.getValue();
        }
        return null;
    }

    private static void lambda$addModules$3(ModRegistrationBuilder modRegistrationBuilder) {
        modRegistrationBuilder.H(ForgeVersion.MC_1_7_10.N());
    }

    public List<Mod> F(JsonObject jsonObject) {
        ArrayList<Mod> arrayList = new ArrayList<Mod>();
        for (Mod mod : this.collectMods()) {
            if (!jsonObject.has(mod.getName()) || !mod.O() || mod.getCategory() == Category.b) continue;
            arrayList.add(mod);
        }
        return arrayList;
    }

    public Collection<Mod> s() {
        return this.slot_o_1_4.values();
    }

    private static void lambda$addModules$0(ModRegistrationBuilder modRegistrationBuilder) {
        modRegistrationBuilder.H(ForgeVersion.MC_1_16_5.b());
    }

    private static void lambda$registerStream$9(ModRegistrationBuilder modRegistrationBuilder) {
    }

    public Collection<Mod> collectMods() {
        return this.slot_o_1_757.values();
    }

    public void loadJson(JsonArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject jsonObject;
            JsonElement jsonElement = jsonArray.get(i);
            if (!jsonElement.isJsonObject() || jsonElement.isJsonNull() || (jsonObject = jsonElement.getAsJsonObject()).get("name") == null || jsonObject.get("name").isJsonNull()) continue;
            String string = jsonObject.get("name").getAsString();
            for (Mod mod : this.f()) {
                try {
                    if (!mod.getName().equalsIgnoreCase(string)) continue;
                    mod.loadJson(jsonObject);
                }
                catch (Exception exception) {
                    Vape.debugLog(mod.getName());
                    Vape.logThrowable(exception);
                }
            }
        }
    }

    public void i() {
        int n = 0;
        for (Mod mod : this.collectMods()) {
            if (mod.O() || mod instanceof ClientSettings || mod.h() == 0 || !mod.r$src$Z$14eylz9()) continue;
            ++n;
            mod.F();
        }
        if (n > 0) {
            Vape.INSTANCE.getNotificationManager().t("Hidden Disabled", n + " module(s) have been disabled!", NotificationType.WARNING, 2500L);
        }
    }

    public ModManager() {
        GuiComponent[] guiComponentArray = Category.N$src$ALgg_vape_ui_click_component_GuiComponent_$lect5v();
        Object[] discardedSlotStorage = new Object[877];
        Array.newInstance(Long.TYPE, 837);
        Array.newInstance(Byte.TYPE, 904);
        Array.newInstance(Float.TYPE, 627);
        Array.newInstance(Short.TYPE, 845);
        Array.newInstance(Object.class, 823);
        Array.newInstance(Character.TYPE, 598);
        Array.newInstance(Double.TYPE, 654);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        Array.newInstance(Integer.TYPE, 556);
        Array.newInstance(Boolean.TYPE, 506);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ModManager modManager = this;
        modManager.slot_o_1_4 = linkedHashMap;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        ModManager modManager2 = this;
        modManager2.slot_o_1_757 = linkedHashMap2;
        HashSet hashSet = new HashSet();
        ModManager modManager3 = this;
        modManager3.slot_o_1_155 = hashSet;
        ArrayList arrayList = new ArrayList();
        ModManager modManager4 = this;
        modManager4.slot_o_1_116 = arrayList;
        ReusableTextNotification reusableTextNotification = new ReusableTextNotification(NotificationType.INFO, "", "", 2000L);
        ModManager modManager5 = this;
        modManager5.slot_o_1_661 = reusableTextNotification;
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            Category.q(new GuiComponent[1]);
        }
    }

    public ArrayList<Mod> l() {
        return this.slot_o_1_116;
    }

    private static void lambda$addModules$4(ModRegistrationBuilder modRegistrationBuilder) {
        modRegistrationBuilder.u(ForgeVersion.MC_1_7_10.N(), ForgeVersion.MC_1_20_6.b());
    }
}
