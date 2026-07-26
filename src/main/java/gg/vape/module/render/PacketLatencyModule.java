package gg.vape.module.render;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.PacketLatencySample;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;

public class PacketLatencyModule
extends Mod {
    private long H;
    private int Y;
    private ArrayList<PacketLatencySample> r = new ArrayList();
    private int a;
    private NumberValue Z;
    private NumberValue k = NumberValue.create((Object)this, "Benchmark Time", "#", "s", 10.0, 30.0, 60.0, 1.0);
    private boolean P = false;
    private int s;
    private int J = 0;

    private void P() {
        Vape.debugLog("----------------------------");
        Vape.debugLog("Amount of Tests: " + this.Z.K());
        Vape.debugLog("Time Taken for each Tests: " + this.k.K() + "s");
        int n = 0;
        int n2 = 0;
        Vape.debugLog("Avg:");
        for (int i = 0; i < this.r.size(); ++i) {
            Vape.debugLog(i + 1 + ": fps - " + this.r.get(i).K() + " captures - " + this.r.get((int)i).S);
            Vape.debugLog("Highest FPS: " + this.r.get((int)i).G + " Lowest FPS: " + this.r.get((int)i).N);
            n = (int)((double)n + this.r.get(i).K());
            n2 = (int)((long)n2 + this.r.get((int)i).S);
        }
        Vape.debugLog("Avg for all tests: fps - " + n / this.r.size() + " captures - " + n2 / this.r.size());
        Vape.debugLog("----------------------------");
    }

    @EventHandler
    public void b(EventPostRenderTick eventPostRenderTick) {
        if (!this.P) {
            if (System.currentTimeMillis() - this.H <= 5000L) {
                return;
            }
            Vape.debugLog("Benchmark Started");
            this.P = true;
            this.H = System.currentTimeMillis();
            this.s = 0;
            this.a = Integer.MAX_VALUE;
            this.Y = Integer.MIN_VALUE;
        }
        if ((double)this.r.size() >= (Double)this.Z.K()) {
            this.P();
            this.F();
            return;
        }
        long l = System.currentTimeMillis() - this.H;
        if ((double)l >= this.U()) {
            this.r.add(new PacketLatencySample(this.s, this.J, this.Y, this.a));
            Vape.debugLog("Test " + this.r.size() + " completed");
            this.J = 0;
            this.s = 0;
            this.a = Integer.MAX_VALUE;
            this.Y = Integer.MIN_VALUE;
            this.H = System.currentTimeMillis();
            return;
        }
        ++this.s;
        int n = Minecraft.l();
        this.J += n;
        this.Y = Math.max(this.Y, n);
        this.a = Math.min(this.a, n);
    }

    public PacketLatencyModule() {
        super("Benchmark", -1, Category.Y);
        this.Z = NumberValue.create(this, "Test Amount", "#", "", 1.0, 5.0, 10.0, 1.0, "Amount of times it should benchmark to give an average");
        this.Y = Integer.MIN_VALUE;
        this.a = Integer.MAX_VALUE;
        this.addValue(this.k, this.Z);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private double U() {
        return (Double)this.k.K() * 1000.0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.r.clear();
        this.P = false;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Vape.debugLog("Starting Benchmark test in 5 seconds");
        this.H = System.currentTimeMillis();
    }
}

