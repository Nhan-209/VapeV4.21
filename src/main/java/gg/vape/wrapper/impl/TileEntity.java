package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class TileEntity
extends Wrapper {
    private static boolean U;


    public TileEntity(Object object) {
        super(object);
    }

    public BlockPos getBlockPos() {
        return new BlockPos(TileEntity.vapeInstance.getMappingsMapperCompat().DP.D(this.I));
    }

    public int getX() {
        if (U) {
            return TileEntity.vapeInstance.getMappingsMapperCompat().DP.E(this.I);
        }
        return this.getBlockPos().P();
    }

    public static boolean j(boolean bl) {
        U = bl;
        return U;
    }

    public int getY() {
        if (U) {
            return TileEntity.vapeInstance.getMappingsMapperCompat().DP.K(this.I);
        }
        return this.getBlockPos().int_o();
    }

    public int getZ() {
        if (U) {
            return TileEntity.vapeInstance.getMappingsMapperCompat().DP.g(this.I);
        }
        return this.getBlockPos().int_d();
    }
}

