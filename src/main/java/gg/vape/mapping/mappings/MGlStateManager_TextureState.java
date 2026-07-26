package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingFieldBuilder;
import gg.vape.wrapper.impl.ForgeVersion;

public class MGlStateManager_TextureState
extends Mapping {
    public MappingField c;

    public MGlStateManager_TextureState() {
        super(MappedClasses.Zn);
        Class<Integer> clazz = Integer.TYPE;
        String string = "textureName";
        MGlStateManager_TextureState mGlStateManager_TextureState = this;
        this.c = ((MappingFieldBuilder)this.T(string, clazz).A(ForgeVersion.MC_1_20_6.n(), "binding")).z();
    }

    public int R(Object object) {
        return this.c.getInt(object);
    }

    public void g(Object object, int n) {
        this.c.setInt(object, n);
    }
}

