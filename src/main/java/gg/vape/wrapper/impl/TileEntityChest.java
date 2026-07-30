package gg.vape.wrapper.impl;

public class TileEntityChest
extends TileEntity {
    public float D() {
        if (ForgeVersion.MC_1_17.d()) {
            return TileEntityChest.vapeInstance.getMappingsMapperCompat().hI.t(this.getObject(), 0.0f);
        }
        return TileEntityChest.vapeInstance.getMappingsMapperCompat().hI.p(this.getObject());
    }

    public TileEntityChest(Object object) {
        super(object);
    }

    public float b() {
        return TileEntityChest.vapeInstance.getMappingsMapperCompat().hI.b(this.getObject());
    }

}

