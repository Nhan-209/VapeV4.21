package gg.vape.utils.render;

public class PoseMatrix {
    public int h;
    public int X;
    public int v;
    public int K;

    public PoseMatrix(int n, int n2, int n3, int n4) {
        this.h = n;
        this.K = n2;
        this.v = n3;
        this.X = n4;
    }

    public void set(int n, int n2, int n3, int n4) {
        this.h = n;
        this.K = n2;
        this.v = n3;
        this.X = n4;
    }

    public boolean equals(Object object) {
        if (!(object instanceof PoseMatrix)) {
            return false;
        }
        PoseMatrix poseMatrix = (PoseMatrix)object;
        return this.h == poseMatrix.h && this.K == poseMatrix.K && this.v == poseMatrix.v && this.X == poseMatrix.X;
    }

    public int hashCode() {
        int n = this.K + this.v;
        int n2 = this.X + this.h;
        int n3 = n * (n + 1) / 2 + this.K;
        int n4 = n2 * (n2 + 1) / 2 + this.h;
        int n5 = n3 + n4;
        return n5 * (n5 + 1) / 2 + n4;
    }
}

