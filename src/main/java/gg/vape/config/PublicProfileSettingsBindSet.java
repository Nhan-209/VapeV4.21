package gg.vape.config;

import gg.vape.config.PublicProfileSettings;
import gg.vape.input.BindSet;

public class PublicProfileSettingsBindSet
extends BindSet {
    final PublicProfileSettings D;

    @Override
    public void A() {
    }

    public PublicProfileSettingsBindSet(PublicProfileSettings publicProfileSettings, int n) {
        super(n);
        this.D = publicProfileSettings;
    }
}
