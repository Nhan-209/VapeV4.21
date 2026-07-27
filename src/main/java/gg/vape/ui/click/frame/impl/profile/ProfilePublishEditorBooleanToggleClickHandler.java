package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.profile.ProfilePublishEditorPanel;
import java.awt.Point;

public class ProfilePublishEditorBooleanToggleClickHandler
implements GuiMouseListener {
    final ProfilePublishEditorPanel o;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        ProfilePublishEditorPanel.m(this.o).Z(!ProfilePublishEditorPanel.m(this.o).V$src$Z$1xhop3l());
    }


    public ProfilePublishEditorBooleanToggleClickHandler(ProfilePublishEditorPanel profilePublishEditorPanel) {
        this.o = profilePublishEditorPanel;
    }
}

