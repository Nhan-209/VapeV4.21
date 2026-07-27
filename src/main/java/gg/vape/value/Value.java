package gg.vape.value;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.config.PublicProfileSettings;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.value.ValueComponentFactory;
import gg.vape.unmap.INamed;
import gg.vape.unmap.PropertyContainer;
import gg.vape.utils.Base64Util;
import gg.vape.value.ColorValue;
import gg.vape.value.ConditionalValue;
import gg.vape.value.DirectValueAccessor;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.ListValue;
import gg.vape.value.ValueAccessor;
import gg.vape.value.ValueChangeListener;
import gg.vape.value.ValueChangeValidator;
import gg.vape.value.ValueCondition;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public abstract class Value<K, T extends Value<K, T>>
extends PropertyContainer
implements INamed {
    @Nullable
    private String X;
    private ValueAccessor<K, T> t;
    private final Object e;
    private final List<ValueChangeValidator<T, K>> U;
    private boolean N;
    protected K c;
    private boolean i = false;
    private final List<String> P;
    private GuiComponent E;
    private boolean F = false;
    private boolean q = true;
    private boolean k = true;
    private Value S;
    private final DirectValueAccessor<K, T> j;
    private final HashMap<ConditionalValue, ValueCondition> z = new HashMap();
    private final String H;
    private static String C;
    private final List<ValueChangeListener<T>> I = new ArrayList<ValueChangeListener<T>>();
    @Nullable
    private Color a = null;
    private K h;

    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.P$src$Ljava_lang_String_$1ijjhmj());
        return jsonObject;
    }

    public Object k$src$Ljava_lang_Object_$13p7u5q() {
        return this.e;
    }

    public boolean W(JsonObject jsonObject) {
        String string = ConfigJsonUtils.P(jsonObject, "id");
        for (String string2 : this.P) {
            if (!string2.equalsIgnoreCase(string)) continue;
            return true;
        }
        return this.P$src$Ljava_lang_String_$1ijjhmj().equalsIgnoreCase(string);
    }

    public K K() {
        return this.t.F();
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void Z() {
        for (ValueChangeListener<T> valueChangeListener : this.I) {
            valueChangeListener.m((T)this);
        }
    }

    @Override
    public String getName() {
        return this.H;
    }

    public K P$src$Ljava_lang_Object_$qcpui1() {
        return this.c;
    }

    public void X(ValueChangeValidator<T, K> valueChangeValidator) {
        this.U.add(valueChangeValidator);
    }

    public void r$src$V$1ar1p19() {
        this.t = this.j;
    }

    public boolean N$src$Z$1a793rp() {
        return this.k;
    }

    public void f(boolean bl) {
        this.N = bl;
    }

    public abstract void parse(String var1);

    public boolean k() {
        K k = this.K();
        K k2 = this.P$src$Ljava_lang_Object_$qcpui1();
        if (k2 == null && k == null) {
            return true;
        }
        if (k2 != null && k == null) {
            return false;
        }
        if (k2 == null) {
            return false;
        }
        if (this instanceof ListValue) {
            List list = (List)k2;
            List list2 = (List)k;
            if (list.size() != list2.size()) {
                return false;
            }
            for (int i = 0; i < list.size(); ++i) {
                Object object = ValueComponentFactory.z(list.get(i));
                Object object2 = ValueComponentFactory.z(list2.get(i));
                if (object == null && object2 == null || object != null && object.equals(object2)) continue;
                return false;
            }
            return true;
        }
        if (k2 instanceof double[]) {
            double[] dArray = (double[])k;
            double[] dArray2 = (double[])k2;
            if (dArray.length != dArray2.length) {
                return false;
            }
            boolean bl = true;
            for (int i = 0; i < dArray.length; ++i) {
                double d = dArray[i];
                double d2 = dArray2[i];
                if (d == d2) continue;
                bl = false;
                break;
            }
            return bl;
        }
        return k2.equals(k);
    }

    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.toJson();
        jsonObject.addProperty("id", this.P$src$Ljava_lang_String_$1ijjhmj());
        if (this.K() != null) {
            String string = "value";
            String string2 = this.K().toString();
            if (this.i) {
                string2 = "b64:" + Base64Util.encodeUtf8Base64(string2);
            }
            jsonObject.addProperty(string, string2);
        }
        return jsonObject;
    }

    public void U(ValueCondition valueCondition) {
        for (ConditionalValue conditionalValue : valueCondition.U().keySet()) {
            this.z.put(conditionalValue, valueCondition);
        }
    }

    public void B(ValueChangeListener<T> valueChangeListener) {
        this.I.add(valueChangeListener);
    }

    public void H$src$V$8t5pov(boolean bl) {
        this.F = bl;
    }

    public Value(Object object, String string, K k) {
        this.U = new ArrayList<ValueChangeValidator<T, K>>();
        this.P = new ArrayList<String>();
        this.j = new DirectValueAccessor<K, T>(this);
        this.t = this.j;
        this.e = object;
        this.H = string;
        this.A(k);
        this.h = k;
        if (object != null) {
            if (object instanceof ColorValue || object instanceof EntityTargetFilterValue) {
                return;
            }
            if (object instanceof PublicProfileSettings) {
                Vape.INSTANCE.getSettingsManager().registerValue(this);
            } else if (!(object instanceof Mod)) {
                Vape.INSTANCE.getValueManager().registerValue(this);
            }
        }
    }

    public void O(ValueAccessor<K, T> valueAccessor) {
        this.t = valueAccessor;
    }

    @Nullable
    public Color Q() {
        return null;
    }

    public void L(ConditionalValue conditionalValue) {
        this.z.put(conditionalValue, null);
    }

    public void e(boolean bl) {
        this.q = bl;
    }

    public static void m(String string) {
        C = string;
    }

    public boolean K$src$Z$1a5lpzm() {
        HashMap<ValueCondition, Boolean> hashMap = new HashMap<ValueCondition, Boolean>();
        for (Map.Entry<ConditionalValue, ValueCondition> entry : this.z.entrySet()) {
            ValueCondition valueCondition = entry.getValue();
            if (valueCondition != null) {
                if (!hashMap.containsKey(valueCondition)) {
                    hashMap.put(valueCondition, valueCondition.x());
                }
                if (((Boolean)hashMap.get(valueCondition)).booleanValue()) continue;
                return false;
            }
            if (entry.getKey().q(this)) continue;
            return false;
        }
        return true;
    }

    public T I(String string) {
        this.P.add(string);
        return (T)this;
    }

    @Nullable
    public Color q$src$Ljava_awt_Color_$1ibcet6() {
        return this.a;
    }

    public boolean L$src$Z$1a65ikz() {
        return this.N;
    }

    @Nullable
    public String w$src$Ljava_lang_String_$ikqblg() {
        return this.X;
    }

    public abstract T getALimit();

    public void L(GuiComponent guiComponent) {
        this.E = guiComponent;
    }

    public void F(K k) {
        this.h = k;
        this.g$src$V$1akzyia();
    }

    public boolean loadJson(JsonObject jsonObject) {
        if (this.W(jsonObject)) {
            String string = "";
            String string2 = ConfigJsonUtils.P(jsonObject, "value");
            if (string2 != null) {
                string = string2;
            }
            if (this.i && string.startsWith("b64:")) {
                string = string.split(":")[1];
                string = Base64Util.decodeUtf8Base64(string);
            }
            this.parse(string);
            this.Z();
            return true;
        }
        return false;
    }

    public Value getParent() {
        return this.S;
    }

    public K l() {
        return this.h;
    }

    public void g$src$V$1akzyia() {
        if (!this.N) {
            Vape.INSTANCE.saveAndStop();
        }
        for (ValueChangeListener<T> valueChangeListener : this.I) {
            valueChangeListener.m((T)this);
        }
    }

    public K O$src$Ljava_lang_Object_$1o24gsq() {
        return this.h;
    }

    public void setParent(Value value) {
        this.S = value;
    }

    public <T> T n(boolean bl) {
        this.k = bl;
        return (T)this;
    }

    public void o(K k) {
        this.t.e(k);
    }

    public void A(K k) {
        this.c = k;
    }

    public boolean C$src$Z$1a17d8q() {
        return this.F;
    }

    public GuiComponent R$src$Lgg_vape_ui_click_component_GuiComponent_$1gnoyjm() {
        return this.E;
    }

    public boolean s$src$Z$1arlhq2() {
        return this.q;
    }

    public T Z$src$Lgg_vape_value_Value_$16i62fx(@Nullable String string) {
        this.X = string;
        return (T)this;
    }

    public boolean b(K k) {
        K k2 = this.K();
        for (ValueChangeValidator<T, K> valueChangeValidator : this.U) {
            if (valueChangeValidator.u((T)this, k2, k)) continue;
            return false;
        }
        return true;
    }

    public void C(@Nullable Color color) {
        this.a = color;
    }

    public K m() {
        return this.t.a();
    }

    public static String K$src$Ljava_lang_String_$9z8f6o() {
        return C;
    }

    public String P$src$Ljava_lang_String_$1ijjhmj() {
        String string = this.H;
        if (this.e != null && this.e instanceof SubModule) {
            SubModule subModule = (SubModule)this.e;
            string = subModule.getName() + "-" + string;
        }
        if (this.S != null) {
            string = this.S.P$src$Ljava_lang_String_$1ijjhmj() + "-" + string;
        }
        return string;
    }

    public void S() {
        if (this.k && this.P$src$Ljava_lang_Object_$qcpui1() != null) {
            this.o(this.P$src$Ljava_lang_Object_$qcpui1());
        }
    }

    public String c() {
        K k = this.K();
        if (k == null) {
            return "";
        }
        String string = k.toString();
        if (string.indexOf(64) != -1 && string.indexOf(32) == -1) {
            return "";
        }
        return string;
    }

    public T W(boolean bl) {
        this.i = bl;
        return (T)this;
    }

    static {
        Value.m("RrLfY");
    }

    public Object java_lang_Object_K() {
        return this.K();
    }
}
