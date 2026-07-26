package gg.vape.utils.datas;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.wrapper.impl.EnumFacing;
import java.util.Objects;

public class DirectionalPosition
extends BlockCoordinate {
    private int C;

    public DirectionalPosition(BlockCoordinate blockCoordinate, int n) {
        this(blockCoordinate.B(), blockCoordinate.E(), blockCoordinate.A(), n);
    }

    public DirectionalPosition(double d, double d2, double d3, int n) {
        super(d, d2, d3);
        this.C = n;
    }

    public DirectionalPosition(int n, int n2, int n3, int n4) {
        super(n, n2, n3);
        this.C = n4;
    }

    @Override
    public String toString() {
        return "BlockLocation.SideHit{x=" + this.B() + ", y=" + this.E() + ", z=" + this.A() + ", sideHit=" + this.C + '}';
    }

    public EnumFacing L() {
        return this.C == -1 ? null : EnumFacing.T(this.C);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.C);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        DirectionalPosition directionalPosition = (DirectionalPosition)object;
        return this.C == directionalPosition.C;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int X() {
        return this.C;
    }
}

