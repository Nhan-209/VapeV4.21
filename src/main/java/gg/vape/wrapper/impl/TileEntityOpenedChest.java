package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.OpenedChestTypeSentinel;
import gg.vape.wrapper.impl.TileEntity;

public class TileEntityOpenedChest
extends TileEntity {
    public float getLidAngle() {
        return TileEntityOpenedChest.c.getMappingsMapperCompat().h5.Z(this.getObject());
    }

    public int getNumPlayersUsing() {
        if (ForgeVersion.MC_1_16_5.d()) {
            if (this.isInstance(MappedClasses.qH)) {
                return 1;
            }
            return 0;
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            OpenedChestTypeSentinel openedChestTypeSentinel = new OpenedChestTypeSentinel(TileEntityOpenedChest.c.getMappingsMapperCompat().h5.b(this.getObject()));
            if (openedChestTypeSentinel.isNull()) {
                return 0;
            }
            int n = TileEntityOpenedChest.c.getMappingsMapperCompat().h5.b(this.getObject()).equals(OpenedChestTypeSentinel.Y().getObject()) ? 0 : 1;
            return n;
        }
        return TileEntityOpenedChest.c.getMappingsMapperCompat().h5.s(this.getObject());
    }


    public TileEntityOpenedChest(Object object) {
        super(object);
    }

    public float Y() {
        if (ForgeVersion.MC_1_17.d()) {
            return TileEntityOpenedChest.c.getMappingsMapperCompat().h5.o(this.getObject(), 0.0f);
        }
        return TileEntityOpenedChest.c.getMappingsMapperCompat().h5.G(this.getObject());
    }
}

