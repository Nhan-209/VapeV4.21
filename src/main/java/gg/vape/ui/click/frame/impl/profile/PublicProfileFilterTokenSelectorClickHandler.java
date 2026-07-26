package gg.vape.ui.click.frame.impl.profile;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenSelectorComponent;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicBoolean;

public class PublicProfileFilterTokenSelectorClickHandler
implements GuiMouseListener {
    final PublicProfileFilterTokenSelectorComponent D;
    final AtomicBoolean F;

    private void lambda$onClick$0(AtomicBoolean atomicBoolean) {
        try {
            this.D.A$src$V$14t6dd1();
        }
        finally {
            atomicBoolean.set(false);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (PublicProfileFilterTokenSelectorComponent.A(this.D)) {
            if (PublicProfileFilterTokenSelectorComponent.E(this.D).w$src$Z$e457mb()) {
                this.F.set(true);
                ClientSettings.f6.execute(() -> this.lambda$onClick$0(this.F));
            }
            return;
        }
        for (PublicProfileFilterTokenComponent publicProfileFilterTokenComponent : PublicProfileFilterTokenSelectorComponent.u(this.D)) {
            if (!publicProfileFilterTokenComponent.w$src$Z$e457mb()) continue;
            if (this.F.get()) {
                return;
            }
            this.F.set(true);
            ClientSettings.f6.execute(() -> this.lambda$onClick$1(publicProfileFilterTokenComponent, this.F));
            return;
        }
    }

    public PublicProfileFilterTokenSelectorClickHandler(PublicProfileFilterTokenSelectorComponent publicProfileFilterTokenSelectorComponent, AtomicBoolean atomicBoolean) {
        this.D = publicProfileFilterTokenSelectorComponent;
        this.F = atomicBoolean;
    }

    private void lambda$onClick$1(PublicProfileFilterTokenComponent publicProfileFilterTokenComponent, AtomicBoolean atomicBoolean) {
        try {
            this.D.R(publicProfileFilterTokenComponent);
        }
        finally {
            atomicBoolean.set(false);
        }
    }
}

