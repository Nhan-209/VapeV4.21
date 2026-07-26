package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.frame.impl.profile.ProfilePublishEditorPanel;

public class ProfilePublishFirstFixedWidthNoSubmitInputComponent
extends TextInputComponentBase {
    final ProfilePublishEditorPanel hn;
    final double hr;

    public ProfilePublishFirstFixedWidthNoSubmitInputComponent(ProfilePublishEditorPanel profilePublishEditorPanel, String string, double d) {
        super(string);
        this.hn = profilePublishEditorPanel;
        this.hr = d;
    }

    @Override
    public void p() {
    }

    @Override
    public double x() {
        return this.hr;
    }

    @Override
    public double C() {
        return 16.0;
    }
}
