package gg.vape.module.combat.silentaura;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.TimerUtil;
import java.util.Random;

public class SilentAuraAimJitter {
    private Random d = new Random();
    private double n;
    private double Z;
    private TimerUtil Y = new TimerUtil();
    private double r;
    private double c;

    public double b() {
        return this.Z;
    }

    public void v() {
        if (this.Y.hasTimeElapsed(MathUtil.randomExclusiveUpper(this.d, 100, 1000))) {
            this.Y.reset();
            this.r = MathUtil.randomRange(this.d, this.n, this.c);
        }
        if (this.r > this.Z) {
            this.Z += 0.01 + MathUtil.randomRange(this.d, 0.0, 0.05);
            if (this.Z > this.r) {
                this.Z = this.r;
            }
        } else if (this.r < this.Z) {
            this.Z -= 0.01 + MathUtil.randomRange(this.d, 0.0, 0.05);
            if (this.Z < this.r) {
                this.Z = this.r;
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public SilentAuraAimJitter(double d, double d2) {
        this.n = d;
        this.c = d2;
    }
}

