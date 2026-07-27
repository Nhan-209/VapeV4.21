package gg.vape.movement;

import gg.vape.movement.PlayerMovementTask;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class TargetPositionMovementTask
extends PlayerMovementTask {
    public double C;
    public double t;


    public void i(double d, double d2) {
        this.C = d;
        this.t = d2;
    }

    @Override
    public boolean z() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double[] dArray = new double[]{entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h()};
        this.T = this.b() ? 0.0 : this.C - dArray[0];
        double d = this.s = this.B() ? 0.0 : this.t - dArray[2];
        if (this.H()) {
            return Math.abs(this.T) <= 0.1 && Math.abs(this.s) <= 0.1;
        }
        return Math.abs(this.T) <= this.Y() && Math.abs(this.s) <= this.Y();
    }

    public TargetPositionMovementTask(double d, double d2) {
        this.C = d;
        this.t = d2;
    }
}

