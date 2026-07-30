package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGameSettingsGuiScale;
import gg.vape.wrapper.Wrapper;

public class GameSettingsGuiScale
extends Wrapper {
    public GameSettingsGuiScale(Object object) {
        super(object);
    }

    public Vec3 n() {
        return new Vec3(MGameSettingsGuiScale.C(GameSettingsGuiScale.vapeInstance.getMappingsMapperCompat().Dp, this.I));
    }
}

