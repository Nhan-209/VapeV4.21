package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MPlayerSkin
extends Mapping {
    private MappingField k;
    private MappingField Z;

    public Object o(Object object) {
        return this.Z.getObject(object);
    }

    public Object S(Object object) {
        return this.k.getObject(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MPlayerSkin() {
        this(BlockData.W());
    }

    private MPlayerSkin(String[] stringArray) {
        super(MappedClasses.uZ);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_21_10.d()) {
                Class clazz = MappedClasses.zI;
                boolean bl = true;
                String string = "body";
                MPlayerSkin mPlayerSkin = this;
                this.k = mPlayerSkin.J(string, bl, clazz);
            } else {
                Class clazz = MappedClasses.zC;
                boolean bl = true;
                String string = "texture";
                MPlayerSkin mPlayerSkin = this;
                this.Z = mPlayerSkin.J(string, bl, clazz);
            }
            return;
        }
        Class clazz = MappedClasses.zC;
        boolean bl = true;
        String string = "texture";
        MPlayerSkin mPlayerSkin = this;
        this.Z = mPlayerSkin.J(string, bl, clazz);
    }
}

