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
    private boolean g;
    private static String N;
    private final List<ValueDisplayDescriptor> l;
    private final ReusableTextNotification X;
    private DelayedModuleToggleTask w;
    private final Bendable y;
    protected Category E;
    private final List<Value<?, ?>> d;
    private boolean R;
    private boolean Q;
    private final String f;
    private final int G;
    private final List<SubModule> z;
    private ModuleDisplayScope e;
    private final int M;
    private boolean m;
    private boolean q = true;
    private boolean h = true;
    private final List<Value<?, ?>> i = new ArrayList();
    private String u;

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
        return this.e;
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
        this.h = bl;
    }

    public boolean Q() {
        return this.getProperty(PropertyContainer.W);
    }

    public void i(ValueDisplayDescriptor ... valueDisplayDescriptorArray) {
        this.l.clear();
        this.l.addAll(Arrays.asList(valueDisplayDescriptorArray));
    }

    public String r() {
        return "";
    }

    public void loadJson(JsonObject jsonObject) {
        String string;
        if (this.y.Y()) {
            this.y.L().clear();
        }
        if ((string = ConfigJsonUtils.P(jsonObject, "name")) != null && string.equalsIgnoreCase(this.getName())) {
            Object object;
            JsonArray jsonArray;
            if (this.y.Y()) {
                jsonArray = ConfigJsonUtils.q(jsonObject, "keybinds_2");
                if (jsonArray != null) {
                    try {
                        this.y.O(jsonArray, false);
                    }
                    catch (Exception exception) {}
                } else {
                    jsonArray = ConfigJsonUtils.q(jsonObject, "keybinds");
                    if (jsonArray != null) {
                        try {
                            this.y.O(jsonArray, true);
                        }
                        catch (Exception exception) {}
                    } else {
                        this.y.L().clear();
                        if (this.G != 0) {
                            this.y.L().add(this.G);
                        }
                    }
                }
                if ((object = ConfigJsonUtils.P(jsonObject, "bind_mode")) != null && this.y.A$src$Z$jg36ch()) {
                    try {
                        this.y.Y(BindActivationMode.valueOf((String)object));
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        this.y.Y(BindActivationMode.TOGGLE);
                    }
                } else {
                    this.y.Y(BindActivationMode.TOGGLE);
                }
            }
            if ((jsonArray = jsonObject.getAsJsonArray("values")) != null) {
                java.util.Iterator<JsonElement> valueIterator = jsonArray.iterator();
                while (valueIterator.hasNext()) {
                    JsonElement jsonElement = valueIterator.next();
                    JsonObject jsonObject2 = jsonElement.getAsJsonObject();
                    for (Value<?, ?> value : this.i) {
                        if (!value.W(jsonObject2)) continue;
                        value.loadJson(jsonObject2);
                    }
                }
            }
            if ((object = ConfigJsonUtils.t(jsonObject, "visible")) != null) {
                this.h = (Boolean)object;
            }
        }
    }

    public Category getCategory() {
        return this.E;
    }

    public void R(boolean bl) {
        this.q = bl;
        this.h = bl;
    }

    public void B() {
        this.X.p(1500L);
        this.X.S("\u00a7f" + this.getName()).m(this.r$src$Z$14eylz9() ? "\u00a72Enabled" : "\u00a7cDisabled").B();
        Vape.INSTANCE.getNotificationManager().m(this.X);
    }

    @Nullable
    public JsonObject q(boolean bl) {
        JsonArray jsonArray;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", this.getName());
        if (this.y.Y()) {
            jsonArray = this.y.toJson$src$Lcom_google_gson_JsonArray_$13cfbto();
            if (this.G == 0 && jsonArray.size() != 0 || jsonArray.size() == 1 && jsonArray.get(0).getAsInt() != this.G) {
                jsonObject.add("keybinds_2", (JsonElement)jsonArray);
            }
            if (this.y.A$src$Z$jg36ch() && this.y.G() != BindActivationMode.TOGGLE) {
                jsonObject.addProperty("bind_mode", this.y.G().name());
            }
        }
        jsonArray = new JsonArray();
        for (Value<?, ?> value : this.i) {
            JsonObject jsonObject2;
            if (!value.s$src$Z$1arlhq2() || value.k() || (jsonObject2 = value.H(bl)).entrySet().size() <= 1) continue;
            jsonArray.add((JsonElement)jsonObject2);
        }
        if (jsonArray.size() != 0) {
            jsonObject.add("values", (JsonElement)jsonArray);
        }
        if (this.h != this.q) {
            jsonObject.addProperty("visible", Boolean.valueOf(this.h));
        }
        if (jsonObject.entrySet().size() == 1) {
            return null;
        }
        return jsonObject;
    }

    public void setSuffix(String string) {
        this.u = string;
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
        this.Q = bl;
    }

    public void v(long l, boolean bl) {
        if (this.w != null) {
            this.w.y(false);
        }
        this.w = new DelayedModuleToggleTask(this, l, bl);
        new Thread(this.w).start();
    }

    public boolean L() {
        return this.getProperty(PropertyContainer.x);
    }

    public void F() {
        if (this.X() && this.y.Y() && this.y.L().isEmpty()) {
            return;
        }
        this.Y(!this.g);
    }

    public void s(boolean bl, boolean bl2) {
        boolean bl3;
        boolean bl4 = bl3 = this.g != bl;
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
        this.g = bl;
        if (this.g) {
            EventBus.getInstance().registerListener(this, this.w());
            new EventModStateChange(this, true).fire();
            this.onEnable();
        } else {
            new EventModStateChange(this, false).fire();
            this.onDisable();
            if (!this.g) {
                EventBus.getInstance().unregisterListener(this);
            }
        }
        this.q(bl, bl2);
        if (bl3 && this.E != Category.b) {
            Vape.INSTANCE.saveAndStop();
        }
    }

    private static Exception b(Exception exception) {
        return exception;
    }

    public void y() {
        this.F();
    }

    public void t() {
    }

    public String n() {
        return this.u;
    }

    public void p(SubModuleValue subModuleValue, SubModuleValue subModuleValue2) {
        if (this instanceof SubModule) {
            return;
        }
        if (!this.g) {
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
        if (this.y.L().isEmpty()) {
            return;
        }
        if (Minecraft.currentScreen().getObject() == null && this.y.U(eventKeyPress.getKey(), eventKeyPress.isDown())) {
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
        return this.f;
    }

    public boolean X() {
        return this.R;
    }

    public boolean O() {
        return this.h;
    }

    public String toString() {
        return "Module{name='" + this.f + '\'' + ", defaultKeybind=" + this.G + ", guiColor=" + this.M + ", values=" + this.d + ", subModules=" + this.z + ", guiCategory=" + this.E + ", enabled=" + this.g + ", requiresBind=" + this.R + ", tooltip='" + this.u + '\'' + ", moduleRunnable=" + this.w + ", defaultVisible=" + this.q + ", visible=" + this.h + ", favorited=" + this.Q + '}';
    }

    public List<SubModule> u() {
        return this.z;
    }

    public void U(Value<?, ?> value, MinecraftVersionConstraint ... minecraftVersionConstraintArray) {
        this.i.add(value);
        List<MinecraftVersionConstraint> list = MinecraftVersionConstraint.L(minecraftVersionConstraintArray);
        if (!list.isEmpty()) {
            return;
        }
        this.d.add(value);
    }

    protected Predicate<IEvent> w() {
        return this::lambda$getEventPredicate$0;
    }

    public boolean b() {
        return this.q;
    }

    public void q() {
    }

    public boolean r$src$Z$14eylz9() {
        return this.g;
    }

    public static String o() {
        return N;
    }

    public boolean k() {
        return this.g;
    }

    public void X(MinecraftVersionConstraint minecraftVersionConstraint, Value<?, ?> ... valueArray) {
        for (Value<?, ?> value : valueArray) {
            this.U(value, minecraftVersionConstraint);
        }
    }

    public void U(Mod mod) {
    }

    public String f() {
        if (!this.l.isEmpty()) {
            return ModuleValueDisplayFormatter.Z(this.l);
        }
        if (this.F$src$Ljava_util_List_$1kytx9u().isEmpty()) {
            return "";
        }
        return ModuleValueDisplayFormatter.v(this.F$src$Ljava_util_List_$1kytx9u());
    }

    public List<Value<?, ?>> V() {
        return this.i;
    }

    public void o(ModuleDisplayScope moduleDisplayScope) {
        this.e = moduleDisplayScope;
    }

    public Value getValue(String string) {
        for (Value<?, ?> value : this.V()) {
            if (!value.P$src$Ljava_lang_String_$1ijjhmj().equalsIgnoreCase(string) && !value.getName().equalsIgnoreCase(string)) continue;
            return value;
        }
        return null;
    }

    public int h() {
        return this.M;
    }

    public void addValue(Value<?, ?> ... valueArray) {
        for (Value<?, ?> value : valueArray) {
            this.U(value, new MinecraftVersionConstraint[0]);
        }
    }

    public void j() {
        if (!this.m && this.Q()) {
            this.m = true;
            Vape.INSTANCE.getNotificationManager().t("Module in development", this.getName() + " is in development\n\nUse with caution and report issues to support", NotificationType.WARNING, 10000L);
        }
    }

    static {
        Mod.A("r6YMSc");
    }

    public Bendable a() {
        return this.y;
    }

    public void I() {
    }

    private boolean lambda$getEventPredicate$0(IEvent iEvent) {
        return this.r$src$Z$14eylz9();
    }

    public boolean f$src$Z$148d2ux() {
        return this.Q;
    }

    public int M$src$I$13um7m9() {
        return this.G;
    }

    public List<ValueDisplayDescriptor> X$src$Ljava_util_List_$6aol0g() {
        return this.l;
    }

    public boolean isBlatantMod() {
        return false;
    }

    public static void A(String string) {
        N = string;
    }

    public void onDisable() {
    }

    public List<Value<?, ?>> F$src$Ljava_util_List_$1kytx9u() {
        return this.d;
    }

    public List<ClickGuiModuleCardRenderState> S() {
        if (!this.l.isEmpty()) {
            return ModuleValueDisplayFormatter.b(this.l);
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
        this.d = new ArrayList();
        this.z = new ArrayList<SubModule>();
        this.l = new ArrayList<ValueDisplayDescriptor>();
        this.e = ModuleDisplayScope.ALL;
        this.X = new ReusableTextNotification(NotificationType.INFO, "", "", 1000L);
        this.f = string;
        this.G = n;
        this.M = n2;
        this.E = category;
        this.u = string2;
        this.y = this.C$src$Lgg_vape_unmap_Bendable_$1we4j6l();
        if (this.y.Y() && n != 0) {
            this.y.L().add(n);
        }
    }

    public boolean t$src$Z$14g275z() {
        return this.getProperty(PropertyContainer.B);
    }

    public void P(Value<?, ?> value, MinecraftVersionConstraint ... minecraftVersionConstraintArray) {
        this.i.add(0, value);
        List<MinecraftVersionConstraint> list = MinecraftVersionConstraint.L(minecraftVersionConstraintArray);
        if (!list.isEmpty()) {
            return;
        }
        this.d.add(0, value);
    }

    protected Bendable C$src$Lgg_vape_unmap_Bendable_$1we4j6l() {
        return new ModBendable(this);
    }

    public boolean boolean_r() {
        return this.r$src$Z$14eylz9();
    }
}
