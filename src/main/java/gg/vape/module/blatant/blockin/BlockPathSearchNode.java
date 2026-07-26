package gg.vape.module.blatant.blockin;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.wrapper.impl.BlockPos;

public class BlockPathSearchNode {
    private static String t;
    public double j;
    public final double w;
    public BlockPathSearchNode C;
    public final double E;
    public double T;
    public final double S;

    static {
        BlockPathSearchNode.l(null);
    }

    public BlockPathSearchNode(double d, double d2, double d3) {
        this.S = d;
        this.E = d2;
        this.w = d3;
    }

    public BlockCoordinate b() {
        return new BlockCoordinate(MathUtil.floor(this.S), MathUtil.floor(this.E), MathUtil.floor(this.w));
    }

    public String toString() {
        return "X: " + this.S + " Y: " + this.E + " Z: " + this.w;
    }

    public int hashCode() {
        return (int)BlockPos.f(MathUtil.floor(this.S), MathUtil.floor(this.E), MathUtil.floor(this.w));
    }

    public static void l(String string) {
        t = string;
    }

    public double p() {
        return this.j + this.T;
    }

    public static String O() {
        return t;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean equals(Object object) {
        if (!(object instanceof BlockPathSearchNode)) {
            return false;
        }
        BlockPathSearchNode blockPathSearchNode = (BlockPathSearchNode)object;
        return this.S == blockPathSearchNode.S && this.E == blockPathSearchNode.E && this.w == blockPathSearchNode.w;
    }
}

