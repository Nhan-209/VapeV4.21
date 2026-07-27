package gg.vape.module.render;

public class PacketLatencySample {
    public long S;
    public int N;
    public int G;
    public int R;

    public double K() {
        return (long)this.R / this.S;
    }

    public PacketLatencySample(long timestamp, int size, int sequence, int id) {
        this.R = size;
        this.S = timestamp;
        this.G = sequence;
        this.N = id;
    }
}

