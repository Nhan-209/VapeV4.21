package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderPlayer;

public class RenderPlayer
extends RenderLivingBase<EntityPlayer> {
    public RenderPlayer(Object object) {
        super(object);
    }

    public ModelBiped getModelBipedMain() {
        return new ModelBiped(MRenderPlayer.Z(RenderPlayer.vapeInstance.getMappingsMapperCompat().hn, this.I));
    }

    @Override
    public PlayerModel getMainModel() {
        return new PlayerModel(MRenderPlayer.Z(RenderPlayer.vapeInstance.getMappingsMapperCompat().hn, this.I));
    }
}

