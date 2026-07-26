package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderPlayer;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ModelBiped;
import gg.vape.wrapper.impl.PlayerModel;
import gg.vape.wrapper.impl.RenderLivingBase;

public class RenderPlayer
extends RenderLivingBase<EntityPlayer> {
    public RenderPlayer(Object object) {
        super(object);
    }

    public ModelBiped getModelBipedMain() {
        return new ModelBiped(MRenderPlayer.Z(RenderPlayer.c.getMappingsMapperCompat().hn, this.I));
    }

    @Override
    public PlayerModel getMainModel() {
        return new PlayerModel(MRenderPlayer.Z(RenderPlayer.c.getMappingsMapperCompat().hn, this.I));
    }
}

