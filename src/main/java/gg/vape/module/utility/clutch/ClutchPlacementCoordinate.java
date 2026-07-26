package gg.vape.module.utility.clutch;

import gg.vape.runtime.ObfuscatedRuntimeException;

public class ClutchPlacementCoordinate {
    public double D;
    double A;
    public double J;
    public double w;

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ClutchPlacementCoordinate)) {
            return false;
        }
        ClutchPlacementCoordinate nO = (ClutchPlacementCoordinate)object;
        boolean bl = Double.compare(this.J, nO.J) == 0 && Double.compare(this.D, nO.D) == 0 && Double.compare(this.w, nO.w) == 0;
        return bl;
    }

    public ClutchPlacementCoordinate(double d, double d2, double d3, double d4) {
        this.J = d;
        this.D = d2;
        this.w = d3;
        this.A = d4;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public int hashCode() {
        long l = Double.doubleToLongBits(this.J);
        long l2 = Double.doubleToLongBits(this.D);
        long l3 = Double.doubleToLongBits(this.w);
        return (int)(l ^ l >>> 32 ^ (l2 ^ l2 >>> 32) ^ (l3 ^ l3 >>> 32));
    }
}

