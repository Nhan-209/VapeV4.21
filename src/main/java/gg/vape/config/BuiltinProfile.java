package gg.vape.config;

import gg.vape.Vape;
import gg.vape.config.ModuleProfileMetadataCodec;
import gg.vape.config.Profile;
import gg.vape.manager.ModManager;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import java.util.ArrayList;

public abstract class BuiltinProfile
extends Profile {
    private static final String r;
    private static GuiComponent[] Y;

    private static void F$src$V$1kb525t() {
        ModuleProfileMetadataCodec moduleProfileMetadataCodec = Vape.INSTANCE.getModuleProfileMetadataCodec();
        for (Mod mod : new ArrayList<Mod>(moduleProfileMetadataCodec.k())) {
            moduleProfileMetadataCodec.v(mod);
        }
    }

    public static GuiComponent[] y$src$ALgg_vape_ui_click_component_GuiComponent_$fbrch2() {
        return Y;
    }

    public final BuiltinProfile J() {
        BuiltinProfile.F$src$V$1kb525t();
        this.O();
        this.a();
        return this;
    }

    protected final void B(Class<? extends Mod> clazz) {
        ModManager modManager = Vape.INSTANCE.getModManager();
        Mod mod = modManager.getMod(clazz);
        if (mod == null) {
            return;
        }
        Vape.INSTANCE.getModuleProfileMetadataCodec().a(mod);
    }

    protected abstract void O();

    protected BuiltinProfile(String string) {
        super(string, r);
    }

    public abstract boolean E();

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        BuiltinProfile.b(null);
        r = "4.21";
    }

    public static void b(GuiComponent[] guiComponentArray) {
        Y = guiComponentArray;
    }
}

