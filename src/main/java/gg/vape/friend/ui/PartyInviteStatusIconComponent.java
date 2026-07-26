package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyMemberEntryComponent;
import gg.vape.friend.ui.PartyMemberEntryModeSwitchMap;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.ImageRenderer;

final class PartyInviteStatusIconComponent
extends InteractiveComponent {
    final PartyMemberEntryComponent I;
    private final ColorAnimation b;
    private static final String K = "newclose";

    PartyInviteStatusIconComponent(PartyMemberEntryComponent partyMemberEntryComponent, PartyMemberEntryModeSwitchMap partyMemberEntryModeSwitchMap) {
        this(partyMemberEntryComponent);
    }

    private PartyInviteStatusIconComponent(PartyMemberEntryComponent partyMemberEntryComponent) {
        this.I = partyMemberEntryComponent;
        this.b = new ColorAnimation(0.15, PartyMemberEntryComponent.c$src$Ljava_awt_Color_$ph3aai(), PartyMemberEntryComponent.q$src$Ljava_awt_Color_$1uk170());
        this.o(12.0);
        this.Y(12.0);
        this.d(false);
    }

    @Override
    public void H() {
        this.b.u(this.w$src$Z$e457mb());
        double d = this.G$src$D$1b2f02a() + (this.A() - 6.0) / 2.0;
        double d2 = this.n() + (this.L() - 6.0) / 2.0;
        ImageRenderer.E(this.b.getInterpolatedColor(), (float)d, (float)d2, K, 6.0f, 6.0f, false);
    }

    @Override
    public double C() {
        return 12.0;
    }

    @Override
    public double x() {
        return 12.0;
    }
}

