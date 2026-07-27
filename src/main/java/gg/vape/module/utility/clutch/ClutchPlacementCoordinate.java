package gg.vape.module.utility.clutch;


public class ClutchPlacementCoordinate {
    public double y;
    double score;
    public double x;
    public double z;

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ClutchPlacementCoordinate)) {
            return false;
        }
        ClutchPlacementCoordinate nO = (ClutchPlacementCoordinate)object;
        boolean bl = Double.compare(this.x, nO.x) == 0 && Double.compare(this.y, nO.y) == 0 && Double.compare(this.z, nO.z) == 0;
        return bl;
    }

    public ClutchPlacementCoordinate(double d, double d2, double d3, double d4) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.score = d4;
    }


    public int hashCode() {
        long l = Double.doubleToLongBits(this.x);
        long l2 = Double.doubleToLongBits(this.y);
        long l3 = Double.doubleToLongBits(this.z);
        return (int)(l ^ l >>> 32 ^ (l2 ^ l2 >>> 32) ^ (l3 ^ l3 >>> 32));
    }
}
