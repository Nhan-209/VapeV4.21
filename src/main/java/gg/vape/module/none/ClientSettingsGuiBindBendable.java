package gg.vape.module.none;

import com.google.common.collect.ImmutableList;
import gg.vape.Vape;
import gg.vape.input.BindSet;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModBendable;
import java.util.Collections;
import java.util.List;

public class ClientSettingsGuiBindBendable
extends ModBendable {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ClientSettingsGuiBindBendable(Mod mod) {
        super(mod);
    }

    @Override
    public boolean Y() {
        return false;
    }

    @Override
    public boolean A$src$Z$jg36ch() {
        return false;
    }

    @Override
    public List<Integer> L() {
        return ImmutableList.copyOf(((BindSet)Vape.INSTANCE.getPublicProfileSettings().Y.K()).L());
    }

    @Override
    public void c(List<Integer> list) {
        ((BindSet)Vape.INSTANCE.getPublicProfileSettings().Y.K()).c(list);
        if (!this.y$src$Z$r0tfl8()) {
            this.c(Collections.singletonList(161));
        }
    }
}

