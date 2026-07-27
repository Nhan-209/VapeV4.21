package gg.vape.module.combat.silentaura;

import gg.vape.Vape;
import gg.vape.click.ClickButton;
import gg.vape.click.ClickEngine;
import gg.vape.module.Category;
import gg.vape.module.combat.ClickerMod;
import gg.vape.module.combat.SilentAura;
import gg.vape.module.render.Animations;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.EntityPlayerSP;

public class SilentAuraClicker
extends ClickerMod {
    private static final String c;
    private final SilentAura silentAura;

    static {
        c = "auraClicker";
    }

    @Override
    public boolean C() {
        if (!this.silentAura.r$src$Z$14eylz9()) {
            return true;
        }
        return !this.silentAura.P();
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

    private static String decodeUtf8(byte[] bytes) {
        int outIndex = 0;
        int length = bytes.length;
        char[] chars = new char[length];
        for (int i = 0; i < length; ++i) {
            char decoded;
            int b = 0xFF & bytes[i];
            if (b < 192) {
                chars[outIndex++] = (char)b;
                continue;
            }
            if (b < 224) {
                decoded = (char)((char)(b & 0x1F) << 6);
                b = bytes[++i];
                decoded = (char)(decoded | (char)(b & 0x3F));
                chars[outIndex++] = decoded;
                continue;
            }
            if (i >= length - 2) continue;
            decoded = (char)((char)(b & 0xF) << 12);
            b = bytes[++i];
            decoded = (char)(decoded | (char)(b & 0x3F) << 6);
            b = bytes[++i];
            decoded = (char)(decoded | (char)(b & 0x3F));
            chars[outIndex++] = decoded;
        }
        return new String(chars, 0, outIndex);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SilentAuraClicker(SilentAura silentAura) {
        super(c, 0, Category.b);
        this.silentAura = silentAura;
        ClickEngine clickEngine = new ClickEngine(ClickButton.LEFT, silentAura.Ze, silentAura.j, silentAura.ZP, silentAura.Z5, null, new BooleanValue((Object)null, "", false), this);
        this.F(clickEngine);
        this.Y(true);
    }
}
