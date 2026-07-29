package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MAbstractClientPlayerBridge;
import gg.vape.module.render.freecam.FreecamPlayerBridge;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.PlayerSkin;
import gg.vape.wrapper.impl.ResourceLocation;

public class AbstractClientPlayer
extends EntityPlayer {
    private static GuiComponent[] o;

    public static void R(GuiComponent[] guiComponentArray) {
        o = guiComponentArray;
    }

    public FreecamPlayerBridge getFreecamPlayerBridge() {
        return new FreecamPlayerBridge(AbstractClientPlayer.c.getMappings().Dk.x(this.I));
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
            return new PlayerSkin(MAbstractClientPlayerBridge.T(AbstractClientPlayer.c.getMappings().Dk, this.I)).W();
        }
        return new ResourceLocation(MAbstractClientPlayerBridge.T(AbstractClientPlayer.c.getMappings().Dk, this.I));
    }
}

