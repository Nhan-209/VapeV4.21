package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MBlockBed;

public class BlockBed
extends Block {
    public static BlockProperty O() {
        return new BlockProperty(MBlockBed.X(BlockBed.vapeInstance.getMappings().Dj));
    }

    public boolean f(World world, int n, int n2, int n3) {
        if (ForgeVersion.MC_1_16_5.d()) {
            BlockPos blockPos = BlockPos.create(n, n2, n3);
            BlockState blockState = world.getBlockState(blockPos);
            Object object = blockState.I(BlockBed.O());
            if (object != null) {
                return object.toString().equals("foot");
            }
            return true;
        }
        if (ForgeVersion.MC_1_7_10.L()) {
            int n4 = world.A(n, n2, n3);
            boolean bl = (n4 & 8) != 0;
            return !bl;
        }
        BlockPos blockPos = BlockPos.create(n, n2, n3);
        BlockState blockState = world.getBlockState(blockPos);
        BlockState blockState2 = new BlockState(BlockBed.vapeInstance.getMappings().qg.y(this.I, blockState.getObject(), world.getObject(), blockPos.getObject()));
        Object object = blockState2.I(BlockBed.O());
        if (object != null) {
            return object.toString().equals("foot");
        }
        return true;
    }

    public EnumFacing e(World world, int n, int n2, int n3) {
        if (ForgeVersion.MC_1_16_5.d()) {
            BlockPos blockPos = BlockPos.create(n, n2, n3);
            Object object = MBlockBed.i(BlockBed.vapeInstance.getMappings().Dj, world.getObject(), blockPos.getObject());
            return new EnumFacing(object);
        }
        BlockPos blockPos = BlockPos.create(n, n2, n3);
        BlockState blockState = world.getBlockState(blockPos);
        BlockState blockState2 = new BlockState(BlockBed.vapeInstance.getMappings().qg.y(this.I, blockState.getObject(), world.getObject(), blockPos.getObject()));
        Object object = blockState2.I(BlockHorizontal.V());
        return new EnumFacing(object);
    }


    public BlockBed(Object object) {
        super(object);
    }
}

