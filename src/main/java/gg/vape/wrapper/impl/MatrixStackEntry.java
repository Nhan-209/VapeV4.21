package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MMatrixStackEntry;
import gg.vape.wrapper.Wrapper;

public class MatrixStackEntry
extends Wrapper {
    public Matrix4f u() {
        return new Matrix4f(MMatrixStackEntry.s(MatrixStackEntry.vapeInstance.getMappingsMapperCompat().CK, this.I));
    }

    public MatrixStackEntry(Object object) {
        super(object);
    }
}

