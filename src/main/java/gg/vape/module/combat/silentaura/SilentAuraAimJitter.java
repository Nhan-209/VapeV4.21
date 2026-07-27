package gg.vape.module.combat.silentaura;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.TimerUtil;
import java.util.Random;

public class SilentAuraAimJitter {
    private Random random = new Random();
    private double minValue;
    private double current;
    private TimerUtil retargetTimer = new TimerUtil();
    private double target;
    private double maxValue;

    public double b() {
        return this.current;
    }

    public void v() {
        if (this.retargetTimer.hasTimeElapsed(MathUtil.randomExclusiveUpper(this.random, 100, 1000))) {
            this.retargetTimer.reset();
            this.target = MathUtil.randomRange(this.random, this.minValue, this.maxValue);
        }
        if (this.target > this.current) {
            this.current += 0.01 + MathUtil.randomRange(this.random, 0.0, 0.05);
            if (this.current > this.target) {
                this.current = this.target;
            }
        } else if (this.target < this.current) {
            this.current -= 0.01 + MathUtil.randomRange(this.random, 0.0, 0.05);
            if (this.current < this.target) {
                this.current = this.target;
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public SilentAuraAimJitter(double min, double max) {
        this.minValue = min;
        this.maxValue = max;
    }
}
