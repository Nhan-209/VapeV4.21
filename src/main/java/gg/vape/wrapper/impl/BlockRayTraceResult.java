package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.Direction;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Vec3;

public class BlockRayTraceResult
extends RayTraceResult {
    public BlockRayTraceResult(Object object) {
        super(object);
    }

    public static BlockRayTraceResult o(Vec3 vec3, Direction direction, BlockPos blockPos) {
        return new BlockRayTraceResult(BlockRayTraceResult.c.getMappings().D6.Q(vec3.getObject(), direction.getObject(), blockPos.getObject()));
    }

    public boolean a() {
        return BlockRayTraceResult.c.getMappings().D6.n(this.I);
    }
}

