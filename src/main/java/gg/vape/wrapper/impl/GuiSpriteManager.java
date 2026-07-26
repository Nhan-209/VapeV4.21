package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MGuiSpriteManager;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.TextureAtlasSprite;

public class GuiSpriteManager
extends Wrapper {
    public TextureAtlasSprite t(ResourceLocation jx_22) {
        return new TextureAtlasSprite(MGuiSpriteManager.F(GuiSpriteManager.c.getMappingsMapperCompat().h4, this.I, jx_22.getObject()));
    }

    public GuiSpriteManager(Object object) {
        super(object);
    }
}

