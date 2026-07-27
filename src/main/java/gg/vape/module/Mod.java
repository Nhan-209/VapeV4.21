package gg.vape.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.event.EventBus;
import gg.vape.event.EventListener;
import gg.vape.event.IEvent;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventModStateChange;
import gg.vape.input.BindActivationMode;
import gg.vape.module.Category;
import gg.vape.module.DelayedModuleToggleTask;
import gg.vape.module.MinecraftVersionConstraint;
import gg.vape.module.ModDisplayInfo;
import gg.vape.module.ModuleDisplayScope;
import gg.vape.module.SubModule;
import gg.vape.module.UtilityMod;
import gg.vape.notification.NotificationType;
import gg.vape.notification.ReusableTextNotification;
import gg.vape.ui.click.frame.impl.main.ClickGuiModuleCardRenderState;
import gg.vape.unmap.Bendable;
import gg.vape.unmap.INamed;
import gg.vape.unmap.ModBendable;
import gg.vape.unmap.PropertyContainer;
import gg.vape.value.ModuleValueDisplayFormatter;
import gg.vape.value.SubModuleValue;
import gg.vape.value.Value;
import gg.vape.value.ValueDisplayDescriptor;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class Mod
extends PropertyContainer
implements INamed,
EventListener {
    private boolean enabled;
    private static String staticName;
    private final List<ValueDisplayDescriptor> valueDisplayDescriptors;
    private final ReusableTextNotification toggleNotification;
    private DelayedModuleToggleTask moduleRunnable;
    private final Bendable bind;
    protected Category E;
    private final List<Value<?, ?>> values;
    private boolean requiresBind;
    private boolean favorited;
    private final String name;
    private final int defaultKeybind;
    private final List<SubModule> subModules;
    private ModuleDisplayScope displayScope;
    private final int guiColor;
    private boolean developmentWarningShown;
    private boolean defaultVisible = true;
    private boolean visible = true;
    private final List<Value<?, ?>> allValues = new ArrayList();
    private String tooltip;

    public ModDisplayInfo J() {
        return null;
    }

    public String E() {
        return "";
    }

    public void k(SubModule ... subModuleArray) {
        this.u().addAll(Arrays.asList(subModuleArray));
    }

    public ModuleDisplayScope J$src$Lgg_vape_module_ModuleDisplayScope_$1w905sh() {
        return this.displayScope;
    }

    public void onEnable() {
    }

    public SubModule a(String string) {
        for (SubModule subModule : this.u()) {
            if (!subModule.getName().equalsIgnoreCase(string)) continue;
            return subModule;
        }
        return null;
    }

    public void C(boolean bl) {
        this.visible = bl;
    }

    public boolean Q() {
        return this.getProperty(PropertyContainer.W);
    }

    public void i(ValueDisplayDescriptor ... valueDisplayDescriptorArray) {
        this.valueDisplayDescriptors.clear();
        this.valueDisplayDescriptors.addAll(Arrays.asList(valueDisplayDescriptorArray));
    }

    public String r() {
        return "";
    }

    public void loadJson(JsonObject jsonObject) {
        String string;
        if (this.bind.Y()) {
            this.bind.L().clear();
        }
        if ((string = ConfigJsonUtils.P(jsonObject, "name")) != null && string.equalsIgnoreCase(this.getName())) {
            Object object;
            JsonArray jsonArray;
            if (this.bind.Y()) {
                jsonArray = ConfigJsonUtils.q(jsonObject, "keybinds_2");
                if (jsonArray != null) {
                    try {
                        this.bind.O(jsonArray, false);
                    }
                    catch (Exception exception) {}
                } else {
                    jsonArray = ConfigJsonUtils.q(jsonObject, "keybinds");
                    if (jsonArray != null) {
                        try {
                            this.bind.O(jsonArray, true);
                        }
                        catch (Exception exception) {}
                    } else {
                        this.bind.L().clear();
                        if (this.defaultKeybind != 0) {
                            this.bind.L().add(this.defaultKeybind);
                        }
                    }
                }
                if ((object = ConfigJsonUtils.P(jsonObject, "bind_mode")) != null && this.bind.A$src$Z$jg36ch()) {
                    try {
                        this.bind.Y(BindActivationMode.valueOf((String)object));
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        this.bind.Y(BindActivationMode.TOGGLE);
                    }
                } else {
                    this.bind.Y(BindActivationMode.TOGGLE);
                }
            }
            if ((jsonArray = jsonObject.getAsJsonArray("values")) != null) {
                java.util.Iterator<JsonElement> valueIterator = jsonArray.iterator();
                while (valueIterator.hasNext()) {
                    JsonElement jsonElement = valueIterator.next();
                    JsonObject jsonObject2 = jsonElement.getAsJsonObject();
                    for (Value<?, ?> value : this.allValues) {
                        if (!value.W(jsonObject2)) continue;
                        value.loadJson(jsonObject2);
                    }
                }
            }
            if ((object = ConfigJsonUtils.t(jsonObject, "visible")) != null) {
                this.visible = (Boolean)object;
            }
        }
    }

    public Category getCategory() {
        return this.E;
    }

    public void R(boolean bl) {
        this.defaultVisible = bl;
        this.visible = bl;
    }

    public void B() {
        this.toggleNotification.p(1500L);
        this.toggleNotification.S("\u00a7f" + this.getName()).m(this.r$src$Z$14eylz9() ? "\u00a72Enabled" : "\u00a7cDisabled").B();
        Vape.INSTANCE.getNotificationManager().m(this.toggleNotification);
    }

    @Nullable
    public JsonObject q(boolean bl) {
        JsonArray jsonArray;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", this.getName());
        if (this.bind.Y()) {
            jsonArray = this.bind.toJson$src$Lcom_google_gson_JsonArray_$13cfbto();
            if (this.defaultKeybind == 0 && jsonArray.size() != 0 || jsonArray.size() == 1 && jsonArray.get(0).getAsInt() != this.defaultKeybind) {
                jsonObject.add("keybinds_2", (JsonElement)jsonArray);
            }
            if (this.bind.A$src$Z$jg36ch() && this.bind.G() != BindActivationMode.TOGGLE) {
                jsonObject.addProperty("bind_mode", this.bind.G().name());
            }
        }
        jsonArray = new JsonArray();
        for (Value<?, ?> value : this.allValues) {
            JsonObject jsonObject2;
            if (!value.s$src$Z$1arlhq2() || value.k() || (jsonObject2 = value.H(bl)).entrySet().size() <= 1) continue;
            jsonArray.add((JsonElement)jsonObject2);
        }
        if (jsonArray.size() != 0) {
            jsonObject.add("values", (JsonElement)jsonArray);
        }
        if (this.visible != this.defaultVisible) {
            jsonObject.addProperty("visible", Boolean.valueOf(this.visible));
        }
        if (jsonObject.entrySet().size() == 1) {
            return null;
        }
        return jsonObject;
    }

    public void setSuffix(String string) {
        this.tooltip = string;
    }

    public void q(boolean bl, boolean bl2) {
        for (SubModule subModule : this.u()) {
            if (subModule.J$src$Z$gcqtyf()) {
                if (subModule.r$src$Z$14eylz9() == bl) continue;
                subModule.s(bl, bl2);
                continue;
            }
            if (!subModule.G()) continue;
            subModule.s(false, bl2);
        }
    }

    public String c(int n) {
        String string = "";
        if (n == 0) {
            string = this.E();
        }
        if (n == 1 && ((string = this.r()) == null || string.isEmpty())) {
            string = this.E();
        }
        return string;
    }

    public Mod(String string, int n) {
        this(string, n, 0, Category.b, null);
    }

    public void M(boolean bl) {
        this.favorited = bl;
    }

    public void v(long l, boolean bl) {
        if (this.moduleRunnable != null) {
            this.moduleRunnable.y(false);
        }
        this.moduleRunnable = new DelayedModuleToggleTask(this, l, bl);
        new Thread(this.moduleRunnable).start();
    }

    public boolean L() {
        return this.getProperty(PropertyContainer.x);
    }

    public void F() {
        if (this.X() && this.bind.Y() && this.bind.L().isEmpty()) {
            return;
        }
        this.Y(!this.enabled);
    }

    public void s(boolean bl, boolean bl2) {
        boolean bl3;
        boolean bl4 = bl3 = this.enabled != bl;
        if (!bl2 && !this.O() && this.E != Category.b && bl) {
            if (Vape.INSTANCE.getNotificationManager() != null) {
                Vape.INSTANCE.getNotificationManager().t("Hidden Module", "Attempted to toggle " + this.getName() + "!", NotificationType.WARNING, 2500L);
            }
            return;
        }
        if (bl && this.isBlatantMod()) {
            Vape.INSTANCE.getPrimaryMappingTaskSet().f();
        }
        if (!bl) {
            this.g();
        }
        this.enabled = bl;
        if (this.enabled) {
            EventBus.getInstance().registerListener(this, this.w());
            new EventModStateChange(this, true).fire();
            this.onEnable();
        } else {
            new EventModStateChange(this, false).fire();
            this.onDisable();
            if (!this.enabled) {
                EventBus.getInstance().unregisterListener(this);
            }
        }
        this.q(bl, bl2);
        if (bl3 && this.E != Category.b) {
            Vape.INSTANCE.saveAndStop();
        }
    }

    private static Exception passThrough(Exception exception) {
        return exception;
    }

    public void y() {
        this.F();
    }

    public void t() {
    }

    public String n() {
        return this.tooltip;
    }

    public void p(SubModuleValue subModuleValue, SubModuleValue subModuleValue2) {
        if (this instanceof SubModule) {
            return;
        }
        if (!this.enabled) {
            return;
        }
        Object t = subModuleValue.getInstance();
        Object t2 = subModuleValue2.getInstance();
        if (((SubModule)t).G()) {
            ((Mod)t).Y(false);
        }
        if (!((SubModule)t2).G()) {
            ((Mod)t2).Y(true);
        }
    }

    public boolean q$src$Z$12h8h4c() {
        return this.getCategory() != Category.b && !(this instanceof UtilityMod);
    }

    public void K(boolean bl) {
        if (bl) {
            Vape.INSTANCE.getModuleProfileMetadataCodec().a(this);
        } else {
            Vape.INSTANCE.getModuleProfileMetadataCodec().v(this);
        }
    }

    public Mod(String string, int n, Category category) {
        this(string, 0, n, category, null);
    }

    public void u(EventKeyPress eventKeyPress) {
        if (eventKeyPress.getKey() <= 0) {
            return;
        }
        if (this.bind.L().isEmpty()) {
            return;
        }
        if (Minecraft.currentScreen().getObject() == null && this.bind.U(eventKeyPress.getKey(), eventKeyPress.isDown())) {
            eventKeyPress.setCancelled(true);
        }
    }

    public void Y(boolean bl) {
        this.s(bl, false);
    }

    public Mod(String string, int n, Category category, String string2) {
        this(string, 0, n, category, string2);
    }

    public void g() {
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean X() {
        return this.requiresBind;
    }

    public boolean O() {
        return this.visible;
    }

    public String toString() {
        return "Module{name='" + this.name + '\'' + ", defaultKeybind=" + this.defaultKeybind + ", guiColor=" + this.guiColor + ", values=" + this.values + ", subModules=" + this.subModules + ", guiCategory=" + this.E + ", enabled=" + this.enabled + ", requiresBind=" + this.requiresBind + ", tooltip='" + this.tooltip + '\'' + ", moduleRunnable=" + this.moduleRunnable + ", defaultVisible=" + this.defaultVisible + ", visible=" + this.visible + ", favorited=" + this.favorited + '}';
    }

    public List<SubModule> u() {
        return this.subModules;
    }

    public void U(Value<?, ?> value, MinecraftVersionConstraint ... minecraftVersionConstraintArray) {
        this.allValues.add(value);
        List<MinecraftVersionConstraint> list = MinecraftVersionConstraint.L(minecraftVersionConstraintArray);
        if (!list.isEmpty()) {
            return;
        }
        this.values.add(value);
    }

    protected Predicate<IEvent> w() {
        return this::matchesEnabled;
    }

    public boolean b() {
        return this.defaultVisible;
    }

    public void q() {
    }

    public boolean r$src$Z$14eylz9() {
        return this.enabled;
    }

    public static String o() {
        return staticName;
    }

    public boolean k() {
        return this.enabled;
    }

    public void X(MinecraftVersionConstraint minecraftVersionConstraint, Value<?, ?> ... valueArray) {
        for (Value<?, ?> value : valueArray) {
            this.U(value, minecraftVersionConstraint);
        }
    }

    public void U(Mod mod) {
    }

    public String f() {
        if (!this.valueDisplayDescriptors.isEmpty()) {
            return ModuleValueDisplayFormatter.Z(this.valueDisplayDescriptors);
        }
        if (this.F$src$Ljava_util_List_$1kytx9u().isEmpty()) {
            return "";
        }
        return ModuleValueDisplayFormatter.v(this.F$src$Ljava_util_List_$1kytx9u());
    }

    public List<Value<?, ?>> V() {
        return this.allValues;
    }

    public void o(ModuleDisplayScope moduleDisplayScope) {
        this.displayScope = moduleDisplayScope;
    }

    public Value getValue(String string) {
        for (Value<?, ?> value : this.V()) {
            if (!value.P$src$Ljava_lang_String_$1ijjhmj().equalsIgnoreCase(string) && !value.getName().equalsIgnoreCase(string)) continue;
            return value;
        }
        return null;
    }

    public int h() {
        return this.guiColor;
    }

    public void addValue(Value<?, ?> ... valueArray) {
        for (Value<?, ?> value : valueArray) {
            this.U(value, new MinecraftVersionConstraint[0]);
        }
    }

    public void j() {
        if (!this.developmentWarningShown && this.Q()) {
            this.developmentWarningShown = true;
            Vape.INSTANCE.getNotificationManager().t("Module in development", this.getName() + " is in development\n\nUse with caution and report issues to support", NotificationType.WARNING, 10000L);
        }
    }

    static {
        Mod.A("r6YMSc");
    }

    public Bendable a() {
        return this.bind;
    }

    public void I() {
    }

    private boolean matchesEnabled(IEvent iEvent) {
        return this.r$src$Z$14eylz9();
    }

    public boolean f$src$Z$148d2ux() {
        return this.favorited;
    }

    public int M$src$I$13um7m9() {
        return this.defaultKeybind;
    }

    public List<ValueDisplayDescriptor> X$src$Ljava_util_List_$6aol0g() {
        return this.valueDisplayDescriptors;
    }

    public boolean isBlatantMod() {
        return false;
    }

    public static void A(String string) {
        staticName = string;
    }

    public void onDisable() {
    }

    public List<Value<?, ?>> F$src$Ljava_util_List_$1kytx9u() {
        return this.values;
    }

    public List<ClickGuiModuleCardRenderState> S() {
        if (!this.valueDisplayDescriptors.isEmpty()) {
            return ModuleValueDisplayFormatter.b(this.valueDisplayDescriptors);
        }
        if (this.F$src$Ljava_util_List_$1kytx9u().isEmpty()) {
            return Collections.emptyList();
        }
        return ModuleValueDisplayFormatter.I(this.F$src$Ljava_util_List_$1kytx9u());
    }

    public Mod(String string) {
        this(string, 0, Category.b);
    }

    public Mod(String string, int n, int n2, Category category, String string2) {
        this.values = new ArrayList();
        this.subModules = new ArrayList<SubModule>();
        this.valueDisplayDescriptors = new ArrayList<ValueDisplayDescriptor>();
        this.displayScope = ModuleDisplayScope.ALL;
        this.toggleNotification = new ReusableTextNotification(NotificationType.INFO, "", "", 1000L);
        this.name = string;
        this.defaultKeybind = n;
        this.guiColor = n2;
        this.E = category;
        this.tooltip = string2;
        this.bind = this.C$src$Lgg_vape_unmap_Bendable_$1we4j6l();
        if (this.bind.Y() && n != 0) {
            this.bind.L().add(n);
        }
    }

    public boolean t$src$Z$14g275z() {
        return this.getProperty(PropertyContainer.B);
    }

    public void P(Value<?, ?> value, MinecraftVersionConstraint ... minecraftVersionConstraintArray) {
        this.allValues.add(0, value);
        List<MinecraftVersionConstraint> list = MinecraftVersionConstraint.L(minecraftVersionConstraintArray);
        if (!list.isEmpty()) {
            return;
        }
        this.values.add(0, value);
    }

    protected Bendable C$src$Lgg_vape_unmap_Bendable_$1we4j6l() {
        return new ModBendable(this);
    }

    public boolean boolean_r() {
        return this.r$src$Z$14eylz9();
    }
}
