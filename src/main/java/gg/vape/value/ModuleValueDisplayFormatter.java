package gg.vape.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.main.ClickGuiModuleCardRenderState;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.SubModuleValue;
import gg.vape.value.Value;
import gg.vape.value.ValueDisplayDescriptor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public final class ModuleValueDisplayFormatter {
    public static List<ClickGuiModuleCardRenderState> b(List<ValueDisplayDescriptor> list) {
        ModeValue modeValue;
        boolean bl;
        List<ValueDisplayDescriptor> list2 = ModuleValueDisplayFormatter.L(list);
        List<ClickGuiModuleCardRenderState> list3 = ModuleValueDisplayFormatter.J(list2, bl = ModuleValueDisplayFormatter.k$src$Z$jh7n4t(list2), modeValue = ModuleValueDisplayFormatter.k(list2), false);
        int n = ModuleValueDisplayFormatter.H(list3);
        if (n < 50) {
            return ModuleValueDisplayFormatter.J(list2, bl, modeValue, true);
        }
        return list3;
    }

    private static String i(Value<?, ?> value, boolean bl, boolean bl2) {
        if (value instanceof BooleanValue) {
            BooleanValue booleanValue = (BooleanValue)value;
            if (ModuleValueDisplayFormatter.K(booleanValue)) {
                return ModuleValueDisplayFormatter.X(booleanValue);
            }
            return booleanValue.o();
        }
        String string = value.c();
        if ((bl || bl2) && value instanceof ModeValue) {
            return string;
        }
        if (string.isEmpty()) {
            return value.getName();
        }
        return string + " " + value.getName();
    }

    private ModuleValueDisplayFormatter() {
    }

    private static List<Value<?, ?>> h(List<Value<?, ?>> list) {
        HashSet<Value> hashSet = new HashSet<Value>();
        HashSet<Object> hashSet2 = new HashSet<Object>();
        for (Value<?, ?> value : list) {
            if (!(value instanceof BooleanValue)) continue;
            BooleanValue value2 = (BooleanValue)value;
            if (ModuleValueDisplayFormatter.K(value2)) {
                for (Value value3 : value2.q$src$Ljava_util_List_$fyau59()) {
                    hashSet.add(value3);
                }
                continue;
            }
            if (!ModuleValueDisplayFormatter.I(value2)) continue;
            hashSet2.add(value);
        }
        ArrayList<Value<?, ?>> arrayList = new ArrayList<Value<?, ?>>();
        for (Value<?, ?> value : list) {
            if (hashSet.contains(value) || hashSet2.contains(value) || !ModuleValueDisplayFormatter.I(value)) continue;
            arrayList.add(value);
        }
        return arrayList;
    }

    private static boolean I(BooleanValue booleanValue) {
        List<Value> list = booleanValue.q$src$Ljava_util_List_$fyau59();
        if (list.isEmpty()) {
            return false;
        }
        for (Value value : list) {
            if (value instanceof ColorValue) continue;
            return false;
        }
        return true;
    }

    private static boolean x(BooleanValue booleanValue) {
        for (Value value : booleanValue.q$src$Ljava_util_List_$fyau59()) {
            LimitValue limitValue;
            if (!(value instanceof LimitValue) || ((List)(limitValue = (LimitValue)value).K()).isEmpty()) continue;
            return true;
        }
        return false;
    }

    private static String D(ValueDisplayDescriptor valueDisplayDescriptor, boolean bl, boolean bl2, boolean bl3) {
        String string;
        Value<?, ?> value = valueDisplayDescriptor.M();
        if (value instanceof BooleanValue) {
            BooleanValue booleanValue = (BooleanValue)value;
            if (ModuleValueDisplayFormatter.K(booleanValue)) {
                return ModuleValueDisplayFormatter.X(booleanValue);
            }
            return bl3 ? valueDisplayDescriptor.M$src$Ljava_lang_String_$1ohdx77() : valueDisplayDescriptor.S();
        }
        String string2 = value.c();
        if ((bl || bl2) && value instanceof ModeValue) {
            return string2;
        }
        String string3 = string = bl3 ? valueDisplayDescriptor.M$src$Ljava_lang_String_$1ohdx77() : valueDisplayDescriptor.S();
        if (string2.isEmpty()) {
            return string;
        }
        return string2 + " " + string;
    }

    private static boolean C(List<Value<?, ?>> list) {
        int n = 0;
        for (Value<?, ?> value : list) {
            if (!(value instanceof ModeValue) || ++n <= 1) continue;
            return false;
        }
        return n == 1;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static boolean k$src$Z$jh7n4t(List<ValueDisplayDescriptor> list) {
        int n = 0;
        for (ValueDisplayDescriptor valueDisplayDescriptor : list) {
            if (!(valueDisplayDescriptor.M() instanceof ModeValue) || ++n <= 1) continue;
            return false;
        }
        return n == 1;
    }

    public static String Z(List<ValueDisplayDescriptor> list) {
        List<ValueDisplayDescriptor> list2 = ModuleValueDisplayFormatter.L(list);
        boolean bl = ModuleValueDisplayFormatter.k$src$Z$jh7n4t(list2);
        ModeValue modeValue = ModuleValueDisplayFormatter.k(list2);
        if (modeValue != null) {
            StringJoiner stringJoiner = new StringJoiner(", ");
            boolean bl2 = false;
            Iterator<ValueDisplayDescriptor> iterator = list2.iterator();
            while (iterator.hasNext()) {
                ValueDisplayDescriptor valueDisplayDescriptor = iterator.next();
                boolean bl6 = valueDisplayDescriptor.M() == modeValue;
                String string = ModuleValueDisplayFormatter.D(valueDisplayDescriptor, bl, bl6, false);
                if (string.isEmpty()) continue;
                stringJoiner.add(string);
                bl2 = true;
            }
            if (!bl2) {
                return "";
            }
            String string = stringJoiner.toString();
            if (string.length() < 50) {
                StringJoiner object = new StringJoiner(", ");
                Iterator<ValueDisplayDescriptor> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    ValueDisplayDescriptor valueDisplayDescriptor;
                    boolean bl3 = (valueDisplayDescriptor = iterator2.next()).M() == modeValue;
                    String string2 = ModuleValueDisplayFormatter.D(valueDisplayDescriptor, bl, bl3, true);
                    if (string2.isEmpty()) continue;
                    object.add(string2);
                }
                return object.toString();
            }
            return string;
        }
        StringJoiner stringJoiner = new StringJoiner(", ");
        boolean bl5 = false;
        for (ValueDisplayDescriptor valueDisplayDescriptor : list2) {
            boolean bl4;
            String string = ModuleValueDisplayFormatter.D(valueDisplayDescriptor, bl, bl4 = false, false);
            if (string.isEmpty()) continue;
            stringJoiner.add(string);
            bl5 = true;
        }
        if (!bl5) {
            return "";
        }
        String string = stringJoiner.toString();
        if (string.length() < 50) {
            StringJoiner stringJoiner2 = new StringJoiner(", ");
            for (ValueDisplayDescriptor valueDisplayDescriptor : list2) {
                boolean bl6;
                String string2 = ModuleValueDisplayFormatter.D(valueDisplayDescriptor, bl, bl6 = false, true);
                if (string2.isEmpty()) continue;
                stringJoiner2.add(string2);
            }
            return stringJoiner2.toString();
        }
        return string;
    }

    public static List<ClickGuiModuleCardRenderState> I(List<Value<?, ?>> list) {
        List<Value<?, ?>> list2 = ModuleValueDisplayFormatter.h(list);
        boolean bl = ModuleValueDisplayFormatter.C(list2);
        ModeValue modeValue = ModuleValueDisplayFormatter.d(list2);
        return ModuleValueDisplayFormatter.o(list2, bl, modeValue);
    }

    private static boolean T(ModeValue modeValue) {
        for (ModeSelection modeSelection : modeValue.getModes()) {
            if (!(modeSelection instanceof SubModuleValue)) continue;
            return true;
        }
        return false;
    }

    public static String v(List<Value<?, ?>> list) {
        List<Value<?, ?>> list2 = ModuleValueDisplayFormatter.h(list);
        StringJoiner stringJoiner = new StringJoiner(", ");
        boolean bl = ModuleValueDisplayFormatter.C(list2);
        ModeValue modeValue = ModuleValueDisplayFormatter.d(list2);
        Iterator<Value<?, ?>> iterator = list2.iterator();
        while (iterator.hasNext()) {
            Value<?, ?> value = iterator.next();
            String string = ModuleValueDisplayFormatter.i(value, bl, value == modeValue);
            if (string.isEmpty()) continue;
            stringJoiner.add(string);
        }
        return stringJoiner.toString();
    }

    private static ModeValue d(List<Value<?, ?>> list) {
        ModeValue modeValue = null;
        for (Value<?, ?> value : list) {
            if (!(value instanceof ModeValue) || !ModuleValueDisplayFormatter.T((ModeValue)value)) continue;
            if (modeValue != null) {
                return null;
            }
            modeValue = (ModeValue)value;
        }
        return modeValue;
    }

    private static List<ClickGuiModuleCardRenderState> J(List<ValueDisplayDescriptor> list, boolean bl, ModeValue modeValue, boolean bl2) {
        ArrayList<ClickGuiModuleCardRenderState> arrayList = new ArrayList<ClickGuiModuleCardRenderState>();
        boolean bl3 = true;
        boolean bl4 = false;
        if (modeValue != null) {
            for (ValueDisplayDescriptor valueDisplayDescriptor : list) {
                Color color;
                if (valueDisplayDescriptor.M() != modeValue) continue;
                String string = ModuleValueDisplayFormatter.D(valueDisplayDescriptor, bl, true, bl2);
                if (string.isEmpty() && modeValue.Q() == null) break;
                bl3 = false;
                if (!string.isEmpty()) {
                    arrayList.add(ClickGuiModuleCardRenderState.j(string));
                }
                if ((color = modeValue.Q()) == null) break;
                arrayList.add(ClickGuiModuleCardRenderState.b(color));
                bl4 = true;
                break;
            }
        }
        for (ValueDisplayDescriptor valueDisplayDescriptor : list) {
            Color color;
            Value<?, ?> value = valueDisplayDescriptor.M();
            if (value == modeValue) continue;
            boolean bl5 = false;
            String string = ModuleValueDisplayFormatter.D(valueDisplayDescriptor, bl, false, bl2);
            if (string.isEmpty() && value.Q() == null) continue;
            if (!bl3) {
                arrayList.add(ClickGuiModuleCardRenderState.j(bl4 ? " " : ", "));
            }
            bl3 = false;
            if (!string.isEmpty()) {
                arrayList.add(ClickGuiModuleCardRenderState.j(string));
            }
            if (!(bl4 = (color = value.Q()) != null)) continue;
            arrayList.add(ClickGuiModuleCardRenderState.b(color));
        }
        return arrayList;
    }

    private static int H(List<ClickGuiModuleCardRenderState> list) {
        int n = 0;
        for (ClickGuiModuleCardRenderState clickGuiModuleCardRenderState : list) {
            if (!clickGuiModuleCardRenderState.n$src$Z$1c2q0zn()) continue;
            n += clickGuiModuleCardRenderState.n().length();
        }
        return n;
    }

    private static ModeValue k(List<ValueDisplayDescriptor> list) {
        ModeValue modeValue = null;
        for (ValueDisplayDescriptor valueDisplayDescriptor : list) {
            Value<?, ?> value = valueDisplayDescriptor.M();
            if (!(value instanceof ModeValue) || !ModuleValueDisplayFormatter.T((ModeValue)value)) continue;
            if (modeValue != null) {
                return null;
            }
            modeValue = (ModeValue)value;
        }
        return modeValue;
    }

    private static List<ClickGuiModuleCardRenderState> o(List<Value<?, ?>> list, boolean bl, ModeValue modeValue) {
        ArrayList<ClickGuiModuleCardRenderState> arrayList = new ArrayList<ClickGuiModuleCardRenderState>();
        boolean bl2 = true;
        boolean bl3 = false;
        if (modeValue != null) {
            String string = ModuleValueDisplayFormatter.i(modeValue, bl, true);
            if (!string.isEmpty() || modeValue.Q() != null) {
                Color color;
                bl2 = false;
                if (!string.isEmpty()) {
                    arrayList.add(ClickGuiModuleCardRenderState.j(string));
                }
                if ((color = modeValue.Q()) != null) {
                    arrayList.add(ClickGuiModuleCardRenderState.b(color));
                    bl3 = true;
                }
            }
        }
        for (Value<?, ?> value : list) {
            Color color;
            String string;
            if (value == modeValue || (string = ModuleValueDisplayFormatter.i(value, bl, false)).isEmpty() && value.Q() == null) continue;
            if (!bl2) {
                arrayList.add(ClickGuiModuleCardRenderState.j(bl3 ? " " : ", "));
            }
            bl2 = false;
            if (!string.isEmpty()) {
                arrayList.add(ClickGuiModuleCardRenderState.j(string));
            }
            if (!(bl3 = (color = value.Q()) != null)) continue;
            arrayList.add(ClickGuiModuleCardRenderState.b(color));
        }
        return arrayList;
    }

    private static boolean K(BooleanValue booleanValue) {
        List<Value> list = booleanValue.q$src$Ljava_util_List_$fyau59();
        if (list.isEmpty()) {
            return false;
        }
        for (Value value : list) {
            if (value instanceof LimitValue) continue;
            return false;
        }
        return true;
    }

    private static List<ValueDisplayDescriptor> L(List<ValueDisplayDescriptor> list) {
        ArrayList<ValueDisplayDescriptor> arrayList = new ArrayList<ValueDisplayDescriptor>();
        for (ValueDisplayDescriptor valueDisplayDescriptor : list) {
            if (!ModuleValueDisplayFormatter.I(valueDisplayDescriptor.M())) continue;
            arrayList.add(valueDisplayDescriptor);
        }
        return arrayList;
    }

    private static boolean I(Value<?, ?> value) {
        if (value instanceof EntityTargetFilterValue) {
            return false;
        }
        if (!value.K$src$Z$1a5lpzm()) {
            return false;
        }
        if (value instanceof BooleanValue) {
            if (ModuleValueDisplayFormatter.K((BooleanValue)value)) {
                return ((BooleanValue)value).L() != false && ModuleValueDisplayFormatter.x((BooleanValue)value);
            }
            return ((BooleanValue)value).L();
        }
        return !value.c().isEmpty() || value.Q() != null;
    }

    private static String X(BooleanValue booleanValue) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        HashSet<Value> hashSet = new HashSet<Value>();
        for (Value value : booleanValue.q$src$Ljava_util_List_$fyau59()) {
            LimitValue limitValue;
            int n;
            if (!(value instanceof LimitValue) || !hashSet.add(value) || (n = ((List)(limitValue = (LimitValue)value).K()).size()) == 0) continue;
            stringJoiner.add(n + " " + limitValue.getName());
        }
        String string = stringJoiner.toString();
        return string.isEmpty() ? "" : string;
    }
}
