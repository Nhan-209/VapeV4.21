package gg.vape.wrapper.impl;

public class BlockRayTraceResult
extends RayTraceResult {
    public BlockRayTraceResult(Object object) {
        super(object);
    }

    public static BlockRayTraceResult o(Vec3 vec3, Direction direction, BlockPos blockPos) {
        return new BlockRayTraceResult(BlockRayTraceResult.vapeInstance.getMappings().D6.Q(vec3.getObject(), direction.getObject(), blockPos.getObject()));
    }

    public boolean a() {
        return BlockRayTraceResult.vapeInstance.getMappings().D6.n(this.I);
    }
}

