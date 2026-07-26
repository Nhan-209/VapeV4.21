package gg.vape.module.render;

public class PacketLatencySample {
    public long S;
    public int N;
    public int G;
    public int R;

    public double K() {
        return (long)this.R / this.S;
    }

    public PacketLatencySample(long l, int n, int n2, int n3) {
        this.R = n;
        this.S = l;
        this.G = n2;
        this.N = n3;
    }
}

