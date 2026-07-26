package gg.vape.module.combat;

import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.wtap.WTapRightClickUseCancelMode;
import gg.vape.module.combat.wtap.WTapSprintResetMode;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.SubModuleValue;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.util.HashMap;
import java.util.Map;

public class WTap
extends Mod {
    private final SubModuleValue<WTapRightClickUseCancelMode> I = new WTapRightClickUseCancelMode(this, "Right-click use cancel").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
    private static final String[] k;
    private static final Map p;
    private final NumberValue c;
    private final SubModuleValue<WTapSprintResetMode> a = new WTapSprintResetMode(this, "Normal").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
    private static final long b;
    private static final long r;
    private static final String[] o;
    private final ModeValue j = ModeValue.create((Object)this, "Mode", this.I, this.I, this.a);

    private static Object a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        if (objectArray != null && objectArray.length >= 2 && objectArray[0] instanceof Integer && objectArray[1] instanceof Long) {
            return WTap.a((Integer)objectArray[0], (Long)objectArray[1]);
        }
        return "";
    }

    private static String a(byte[] byArray) {
        int n = byArray.length;
        char[] cArray = new char[n];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            char c;
            int n3 = byArray[i] & 0xFF;
            if (n3 < 192) {
                cArray[n2++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((n3 & 0x1F) << 6);
                n3 = byArray[++i];
                cArray[n2++] = (char)(c | n3 & 0x3F);
                continue;
            }
            if (i >= n - 2) continue;
            c = (char)((n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (n3 & 0x3F) << 6);
            n3 = byArray[++i];
            cArray[n2++] = (char)(c | n3 & 0x3F);
        }
        return new String(cArray, 0, n2);
    }

    private static String a(int n, long l) {
        return "";
    }

    @Override
    public String r() {
        return this.E() + " " + this.c.c() + "%";
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public WTap() {
        super("WTap", 0, (int)r, Category.g, "");
        this.c = NumberValue.create(this, "Chance", "#", "%", 0.0, 90.0, 100.0);
        this.addValue(this.j, this.c);
        this.j.Z$src$Lgg_vape_value_Value_$16i62fx("Mode");
        this.c.C(0);
    }

    private static CallSite a(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        return new MutableCallSite(methodType);
    }

    public boolean a$src$Z$1npvv6h() {
        return (Double)this.c.java_lang_Object_K() >= Math.random() * 100.0;
    }

    public boolean o$src$Z$1nxkzhj() {
        if (!this.I.o()) {
            return false;
        }
        return this.I.getInstance().l();
    }

    @Override
    public String E() {
        return ((ModeSelection)this.j.java_lang_Object_K()).getName();
    }

    static {
        b = 0L;
        r = 0L;
        p = new HashMap(13);
        k = new String[7];
        o = new String[7];
    }
}

