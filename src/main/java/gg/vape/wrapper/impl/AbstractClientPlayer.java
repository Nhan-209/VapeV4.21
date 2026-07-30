package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MAbstractClientPlayerBridge;
import gg.vape.module.render.freecam.FreecamPlayerBridge;
import gg.vape.ui.click.component.GuiComponent;

public class AbstractClientPlayer
extends EntityPlayer {
    private static GuiComponent[] o;

    public static void R(GuiComponent[] guiComponentArray) {
        o = guiComponentArray;
    }

    public FreecamPlayerBridge getFreecamPlayerBridge() {
        return new FreecamPlayerBridge(AbstractClientPlayer.vapeInstance.getMappings().Dk.x(this.I));
    }

    static {
        if (AbstractClientPlayer.I() != null) {
            AbstractClientPlayer.R(new GuiComponent[5]);
        }
    }


    public AbstractClientPlayer(Object object) {
        super(object);
    }

    public static GuiComponent[] I() {
        return o;
    }

    public ResourceLocation O() {
        if (ForgeVersion.MC_1_20_6.d()) {
            return new PlayerSkin(MAbstractClientPlayerBridge.T(AbstractClientPlayer.vapeInstance.getMappings().Dk, this.I)).W();
        }
        return new ResourceLocation(MAbstractClientPlayerBridge.T(AbstractClientPlayer.vapeInstance.getMappings().Dk, this.I));
    }
}

