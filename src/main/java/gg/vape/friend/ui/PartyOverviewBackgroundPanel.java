package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyDetailsPanel;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

public class PartyOverviewBackgroundPanel
extends PanelComponent {
    final PartyDetailsPanel Es;

    @Override
    public void z(boolean bl) {
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n() - 4.0, this.A(), this.L() + 2.0 + 4.0, PartyOverviewBackgroundPanel.J.i);
    }

    public PartyOverviewBackgroundPanel(PartyDetailsPanel partyDetailsPanel, double d, double d2) {
        super(d, d2);
        this.Es = partyDetailsPanel;
    }
}
