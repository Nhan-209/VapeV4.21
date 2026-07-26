package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.frame.impl.profile.ProfilePublishEditorPanel;

public class ProfilePublishSecondFixedWidthNoSubmitInputComponent
extends TextInputComponentBase {
    final ProfilePublishEditorPanel rQ;
    final double rx;

    @Override
    public double C() {
        return 16.0;
    }

    @Override
    public void p() {
    }

    @Override
    public double x() {
        return this.rx;
    }

    public ProfilePublishSecondFixedWidthNoSubmitInputComponent(ProfilePublishEditorPanel profilePublishEditorPanel, String string, double d) {
        super(string);
        this.rQ = profilePublishEditorPanel;
        this.rx = d;
    }
}
