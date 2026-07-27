package gg.vape.config;

import gg.vape.config.PublicProfileSettings;
import gg.vape.value.StringValue;

public class PublicProfileSecondaryDirtyStringValue
extends StringValue {
    final PublicProfileSettings G;
    boolean g;

    public PublicProfileSecondaryDirtyStringValue(PublicProfileSettings publicProfileSettings, Object object, String string, String string2) {
        super(object, string, string2);
        this.G = publicProfileSettings;
    }


    @Override
    public void Z() {
        if (this.g) {
            return;
        }
        this.g = true;
    }
}
