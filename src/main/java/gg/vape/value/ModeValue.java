package gg.vape.value;

import com.google.gson.JsonObject;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.ConditionalValue;
import gg.vape.value.SubModuleValue;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModeValue
extends ConditionalValue<ModeSelection, ModeValue> {
    private static GuiComponent[] b;
    private static final String f;
    private final Map<Value<?, ?>, ArrayList<ModeSelection>> s = new HashMap();
    private final String o;
    private final ModeSelection[] u;

    public static void k(GuiComponent[] guiComponentArray) {
        b = guiComponentArray;
    }

    public static ModeValue create(Object object, String string, ModeSelection modeSelection, ModeSelection ... modeSelectionArray) {
        return ModeValue.create(object, string, string, modeSelection, modeSelectionArray);
    }

    public static ModeValue e(Object object, String string, String string2, String string3, ModeSelection modeSelection, int n, ModeSelection[] modeSelectionArray) {
        ModeValue modeValue = new ModeValue(object, string, string2, modeSelection, modeSelectionArray);
        modeValue.Z$src$Lgg_vape_value_Value_$16i62fx(string3);
        for (ModeSelection modeSelection2 : modeSelectionArray) {
            modeSelection2.S(modeValue);
        }
        return modeValue;
    }

    @Override
    public String c() {
        return ((ModeSelection)this.K()).toString();
    }

    public int w$src$I$15qcf2k() {
        for (int i = 0; i < this.getModes().length; ++i) {
            if (!((ModeSelection)this.K()).equals(this.getModes()[i])) continue;
            return i;
        }
        return 0;
    }

    public static GuiComponent[] h() {
        return b;
    }

    @Override
    public boolean P() {
        return true;
    }

    public static ModeValue j(Object object, String string, String string2, ModeSelection modeSelection, int n, ModeSelection ... modeSelectionArray) {
        return ModeValue.e(object, string, string, string2, modeSelection, n, modeSelectionArray);
    }

    public void L(Value value, ModeOption modeOption) {
        this.K(value);
        if (!this.s.containsKey(value)) {
            this.s.put(value, new ArrayList());
        }
        ArrayList<ModeSelection> arrayList = this.s.get(value);
        arrayList.add(modeOption);
    }

    static {
        ModeValue.k(null);
        f = "value";
    }

    public ModeValue V() {
        return new ModeValue(null, this.P$src$Ljava_lang_String_$1ijjhmj(), this.getName(), (ModeSelection)this.K(), this.getModes());
    }

    @Override
    public ModeValue getALimit() {
        return this.V();
    }

    @Override
    public String getName() {
        return this.o;
    }

    public void setValue(ModeSelection modeSelection) {
        if (((ModeSelection)this.K()).equals(modeSelection)) {
            return;
        }
        if (this.k$src$Ljava_lang_Object_$13p7u5q() != null && modeSelection instanceof SubModuleValue && this.K() instanceof SubModuleValue) {
            this.h((SubModuleValue)this.K(), (SubModuleValue)modeSelection);
        }
        super.o(modeSelection);
    }

    public ModeSelection[] getModes() {
        return this.u;
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.toJson();
        if (this.K() != null) {
            jsonObject.addProperty(f, ((ModeSelection)this.K()).z());
        }
        return jsonObject;
    }

    @Override
    public boolean q(Value value) {
        if (this.s.containsKey(value)) {
            ArrayList<ModeSelection> arrayList = this.s.get(value);
            return arrayList.contains(this.K());
        }
        return false;
    }

    private void h(SubModuleValue subModuleValue, SubModuleValue subModuleValue2) {
        if (this.L$src$Z$1a65ikz()) {
            return;
        }
        Object t = ((SubModule)subModuleValue.getInstance()).getParent();
        ((Mod)t).p(subModuleValue, subModuleValue2);
    }

    public static ModeValue create(Object object, String string, String string2, ModeSelection modeSelection, ModeSelection ... modeSelectionArray) {
        return ModeValue.e(object, string, string, string2, modeSelection, 1, modeSelectionArray);
    }

    public static ModeValue create(Object object, String string, String string2, String string3, ModeSelection modeSelection, ModeSelection ... modeSelectionArray) {
        return ModeValue.e(object, string, string2, string3, modeSelection, 1, modeSelectionArray);
    }

    public void M(int n) {
        this.setValue(this.getModes()[n]);
    }

    public ModeValue(Object object, String string, String string2, ModeSelection modeSelection, ModeSelection[] modeSelectionArray) {
        super(object, string, modeSelection);
        this.o = string2;
        this.u = modeSelectionArray;
        if (object instanceof Mod) {
            Mod mod = (Mod)object;
            for (ModeSelection modeSelection2 : modeSelectionArray) {
                if (!(modeSelection2 instanceof SubModuleValue)) continue;
                SubModuleValue subModuleValue = (SubModuleValue)modeSelection2;
                for (Value<?, ?> value : ((Mod)subModuleValue.getInstance()).V()) {
                    mod.addValue(value);
                    this.L(value, subModuleValue);
                }
            }
        }
    }

    @Override
    public void parse(String string) {
        ModeValue modeValue = ((ModeSelection)this.K()).getMode();
        if (modeValue == null) {
            return;
        }
        ModeSelection modeSelection = ModeSelection.x(modeValue, string);
        if (modeSelection == null) {
            return;
        }
        this.setValue(modeSelection);
    }

    public void f(ModeOption modeOption, Value ... valueArray) {
        this.K(valueArray);
        for (Value value : valueArray) {
            this.L(value, modeOption);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
