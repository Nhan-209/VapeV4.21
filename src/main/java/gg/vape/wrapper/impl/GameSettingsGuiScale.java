package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGameSettingsGuiScale;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Vec3;

public class GameSettingsGuiScale
extends Wrapper {
    public GameSettingsGuiScale(Object object) {
        super(object);
    }

    public Vec3 n() {
        return new Vec3(MGameSettingsGuiScale.C(GameSettingsGuiScale.c.getMappingsMapperCompat().Dp, this.I));
    }
}

