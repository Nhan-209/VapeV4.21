package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MStatusEffectSpriteUploader;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Holder;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.StatusEffect;
import gg.vape.wrapper.impl.TextureAtlasSprite;

public class StatusEffectSpriteUploader
extends Wrapper {
    public StatusEffectSpriteUploader(Object object) {
        super(object);
    }

    public TextureAtlasSprite l(StatusEffect statusEffect) {
        return new TextureAtlasSprite(MStatusEffectSpriteUploader.X(StatusEffectSpriteUploader.c.getMappingsMapperCompat().hk, this.I, statusEffect.getObject()));
    }

    public TextureAtlasSprite T(Holder holder) {
        return new TextureAtlasSprite(MStatusEffectSpriteUploader.X(StatusEffectSpriteUploader.c.getMappingsMapperCompat().hk, this.I, holder.getObject()));
    }

    public static StatusEffectSpriteUploader c() {
        Object object = MStatusEffectSpriteUploader.C(StatusEffectSpriteUploader.c.getMappingsMapperCompat().hk, Minecraft.i());
        return new StatusEffectSpriteUploader(object);
    }
}

