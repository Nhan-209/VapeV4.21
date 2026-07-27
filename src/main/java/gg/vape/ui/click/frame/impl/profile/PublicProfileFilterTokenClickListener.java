package gg.vape.ui.click.frame.impl.profile;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSearchFilterPanel;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicBoolean;

class PublicProfileFilterTokenClickListener
implements GuiMouseListener {
    final PublicProfileFilterTokenComponent W;
    final PaddedComponent O;
    final PublicProfileSearchFilterPanel L;
    final AtomicBoolean f;


    PublicProfileFilterTokenClickListener(PublicProfileSearchFilterPanel publicProfileSearchFilterPanel, AtomicBoolean atomicBoolean, PaddedComponent mn_12, PublicProfileFilterTokenComponent _j_02) {
        this.L = publicProfileSearchFilterPanel;
        this.f = atomicBoolean;
        this.O = mn_12;
        this.W = _j_02;
    }

    @Override
    public void g(Point point, MouseClickButton uA) {
        if (this.f.get()) {
            return;
        }
        this.f.set(true);
        ClientSettings.f6.execute(() -> this.lambda$onClick$0(this.O, this.W, this.f));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void lambda$onClick$0(PaddedComponent mn_12, PublicProfileFilterTokenComponent _j_02, AtomicBoolean atomicBoolean) {
        try {
            PublicProfileSearchFilterPanel.T(this.L).I(mn_12);
            PublicProfileSearchFilterPanel.k(this.L).V(_j_02);
        }
        finally {
            atomicBoolean.set(false);
        }
    }
}

