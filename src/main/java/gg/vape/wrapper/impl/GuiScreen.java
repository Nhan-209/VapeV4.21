package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGuiScreen;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.Screen;

public class GuiScreen
extends Screen {
    private static boolean r;

    public static void Q(boolean bl) {
        r = bl;
    }

    public int g() {
        return MGuiScreen.U(GuiScreen.c.getMappings().h1, this.I);
    }

    public static boolean p$src$Z$8062rc() {
        return r;
    }

    public int k() {
        return MGuiScreen.D(GuiScreen.c.getMappings().h1, this.I);
    }

    public static boolean Z() {
        boolean bl = GuiScreen.p$src$Z$8062rc();
        return !bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean isNull() {
        if (!ClientSettings.fW.P) {
            return false;
        }
        return super.isNull();
    }

    public GuiScreen(Object object) {
        super(object);
    }

    public ITextComponent F() {
        return new ITextComponent(GuiScreen.c.getMappings().h1.S(this.I));
    }

    static {
        if (GuiScreen.Z()) {
            GuiScreen.Q(true);
        }
    }
}

