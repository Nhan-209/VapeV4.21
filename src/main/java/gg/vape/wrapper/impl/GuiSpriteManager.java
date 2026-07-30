package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGuiSpriteManager;
import gg.vape.wrapper.Wrapper;

public class GuiSpriteManager
extends Wrapper {
    public TextureAtlasSprite t(ResourceLocation jx_22) {
        return new TextureAtlasSprite(MGuiSpriteManager.F(GuiSpriteManager.vapeInstance.getMappingsMapperCompat().h4, this.I, jx_22.getObject()));
    }

    public GuiSpriteManager(Object object) {
        super(object);
    }
}

