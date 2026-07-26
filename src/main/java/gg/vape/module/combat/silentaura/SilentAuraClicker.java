package gg.vape.module.combat.silentaura;

import gg.vape.Vape;
import gg.vape.click.ClickButton;
import gg.vape.click.ClickEngine;
import gg.vape.module.Category;
import gg.vape.module.combat.ClickerMod;
import gg.vape.module.combat.SilentAura;
import gg.vape.module.render.Animations;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import java.lang.invoke.MethodHandles;

public class SilentAuraClicker
extends ClickerMod {
    private static final String c;
    private final SilentAura K;
    private static final long a;

    static {
        a = ZkmLongKeyState.a(-648163879241163200L, -4399461328383509529L, MethodHandles.lookup().lookupClass()).a(173939022230375L);
        long l = a ^ 0x6FD3D3438238L;
        c = "auraClicker";
    }

    @Override
    public boolean C() {
        if (!this.K.r$src$Z$14eylz9()) {
            return true;
        }
        return !this.K.P();
    }

    @Override
    public boolean K(ClickEngine clickEngine, EntityPlayerSP entityPlayerSP) {
        Animations animations = Vape.INSTANCE.getModManager().getMod(Animations.class);
        return animations != null && animations.c();
    }

    @Override
    public boolean O() {
        return false;
    }

    private static String a(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SilentAuraClicker(SilentAura silentAura) {
        super(c, 0, Category.b);
        long l = a ^ 0x61D5216458AFL;
        this.K = silentAura;
        ClickEngine clickEngine = new ClickEngine(ClickButton.LEFT, silentAura.Ze, silentAura.j, silentAura.ZP, silentAura.Z5, null, new BooleanValue((Object)null, "", false), this);
        this.F(clickEngine);
        this.Y(true);
    }
}
