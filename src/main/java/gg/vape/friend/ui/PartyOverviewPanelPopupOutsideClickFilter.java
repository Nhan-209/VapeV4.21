package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyDetailsPanel;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.frame.PopupFrame;
import java.awt.Point;

public class PartyOverviewPanelPopupOutsideClickFilter
implements GuiMouseListener {
    final PopupFrame v;
    final PartyDetailsPanel I;


    public PartyOverviewPanelPopupOutsideClickFilter(PartyDetailsPanel partyDetailsPanel, PopupFrame popupFrame) {
        this.I = partyDetailsPanel;
        this.v = popupFrame;
    }

    @Override
    public boolean Q(Point point) {
        if (this.I.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().getBounds().R(point) && !this.v.getBounds().R(point)) {
            return true;
        }
        return GuiMouseListener.super.Q(point);
    }
}

