package gg.vape.friend.activity;

import gg.vape.protocol.ZeusPacketBuffer;

public class ActivityPositionData {
    private final double K;
    private final double B;
    private final double U;

    ActivityPositionData(ZeusPacketBuffer gx_12) {
        this.K = gx_12.S();
        this.B = gx_12.S();
        this.U = gx_12.S();
    }

    public void m(ZeusPacketBuffer gx_12) {
        gx_12.x(this.K);
        gx_12.x(this.B);
        gx_12.x(this.U);
    }

    public double h() {
        return this.U;
    }

    public double Q() {
        return this.B;
    }

    public double d() {
        return this.K;
    }

    public ActivityPositionData(double d, double d2, double d3) {
        this.K = d;
        this.B = d2;
        this.U = d3;
    }
}

