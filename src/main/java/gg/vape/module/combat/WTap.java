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
    private final SubModuleValue<WTapRightClickUseCancelMode> rightClickUseCancel = new WTapRightClickUseCancelMode(this, "Right-click use cancel").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
    private static final String[] stringPoolA;
    private static final Map callSiteCache;
    private final NumberValue chance;
    private final SubModuleValue<WTapSprintResetMode> sprintReset = new WTapSprintResetMode(this, "Normal").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
    private static final long constB;
    private static final long moduleId;
    private static final String[] stringPoolB;
    private final ModeValue mode = ModeValue.create((Object)this, "Mode", this.rightClickUseCancel, this.rightClickUseCancel, this.sprintReset);

    private static Object bootstrapInvoke(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        if (objectArray != null && objectArray.length >= 2 && objectArray[0] instanceof Integer && objectArray[1] instanceof Long) {
            return WTap.decodeConstant((Integer)objectArray[0], (Long)objectArray[1]);
        }
        return "";
    }

    private static String decodeUtf8(byte[] byArray) {
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

    private static String decodeConstant(int n, long l) {
        return "";
    }

    @Override
    public String r() {
        return this.E() + " " + this.chance.c() + "%";
    }

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public WTap() {
        super("WTap", 0, (int)moduleId, Category.g, "");
        this.chance = NumberValue.create(this, "Chance", "#", "%", 0.0, 90.0, 100.0);
        this.addValue(this.mode, this.chance);
        this.mode.Z$src$Lgg_vape_value_Value_$16i62fx("Mode");
        this.chance.C(0);
    }

    private static CallSite bootstrapCallSite(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        return new MutableCallSite(methodType);
    }

    public boolean a$src$Z$1npvv6h() {
        return (Double)this.chance.java_lang_Object_K() >= Math.random() * 100.0;
    }

    public boolean o$src$Z$1nxkzhj() {
        if (!this.rightClickUseCancel.o()) {
            return false;
        }
        return this.rightClickUseCancel.getInstance().l();
    }

    @Override
    public String E() {
        return ((ModeSelection)this.mode.java_lang_Object_K()).getName();
    }

    static {
        constB = 0L;
        moduleId = 0L;
        callSiteCache = new HashMap(13);
        stringPoolA = new String[7];
        stringPoolB = new String[7];
    }
}

