package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyDetailsPanel;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.GroupOption;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.value.BooleanValue;
import gg.vape.value.Value;
import java.awt.Point;

public class PartyOverviewGroupOptionSyncMouseListener
implements GuiMouseListener {
    final GroupOption z;
    private boolean G;
    final PartyDetailsPanel c;
    final BooleanValue P;
    final Value N;

    public PartyOverviewGroupOptionSyncMouseListener(PartyDetailsPanel partyDetailsPanel, BooleanValue booleanValue, GroupOption groupOption, Value value) {
        this.c = partyDetailsPanel;
        this.P = booleanValue;
        this.z = groupOption;
        this.N = value;
        this.G = this.P.L();
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (this.G != this.P.L()) {
            this.G = this.P.L();
            ZeusConnectionManager.T().u().Y(this.z, this.N.K());
        }
    }

}

