package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MStatusEffectSpriteUploader;
import gg.vape.wrapper.Wrapper;

public class StatusEffectSpriteUploader
extends Wrapper {
    public StatusEffectSpriteUploader(Object object) {
        super(object);
    }

    public TextureAtlasSprite l(StatusEffect statusEffect) {
        return new TextureAtlasSprite(MStatusEffectSpriteUploader.X(StatusEffectSpriteUploader.vapeInstance.getMappingsMapperCompat().hk, this.I, statusEffect.getObject()));
    }

    public TextureAtlasSprite T(Holder holder) {
        return new TextureAtlasSprite(MStatusEffectSpriteUploader.X(StatusEffectSpriteUploader.vapeInstance.getMappingsMapperCompat().hk, this.I, holder.getObject()));
    }

    public static StatusEffectSpriteUploader c() {
        Object object = MStatusEffectSpriteUploader.C(StatusEffectSpriteUploader.vapeInstance.getMappingsMapperCompat().hk, Minecraft.i());
        return new StatusEffectSpriteUploader(object);
    }
}

