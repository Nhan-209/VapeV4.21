package gg.vape.ui.click.component.module;

import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.utils.render.RenderUtils;

class ModuleComponentToggleClickHandler
implements GuiClickListener {
    final Mod E;
    final ModuleComponent L;

    @Override
    public void G() {
        if (ClientSettings.Y) {
            return;
        }
        ModuleComponent.v(this.L).P$src$V$q7uwbv();
    }

    ModuleComponentToggleClickHandler(ModuleComponent moduleComponent, Mod mod) {
        this.L = moduleComponent;
        this.E = mod;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void P() {
        if (ClientSettings.Y) {
            if (!ModuleComponent.P(this.L) && ModuleComponent.F(this.L).Z(RenderUtils.h())) {
                this.E.C(!this.E.O());
                if (this.E.r$src$Z$14eylz9() && !this.E.O()) {
                    this.E.Y(false);
                }
                if (ModuleComponent.v$src$Z$1nzvssj(this.L)) {
                    ModuleComponent.m(this.L, false);
                }
                ClientSettings.M$src$V$1giazqf();
            }
            return;
        }
        if (this.E.X()) {
            if (!this.E.a().y$src$Z$r0tfl8()) {
                this.L.j("must be bound");
                ModuleComponent.k(this.L).w(true);
                return;
            }
            this.L.j("use via bind");
            ModuleComponent.k(this.L).w(true);
            return;
        }
        this.E.s(!this.E.r$src$Z$14eylz9(), true);
        if (!this.E.O()) {
            this.E.C(true);
            ClientSettings.M$src$V$1giazqf();
        }
    }
}

