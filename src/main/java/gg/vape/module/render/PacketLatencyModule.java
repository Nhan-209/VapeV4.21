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
    private long lastTimestamp;
    private int highestFps;
    private ArrayList<PacketLatencySample> samples = new ArrayList();
    private int lowestFps;
    private NumberValue testAmount;
    private NumberValue benchmarkTime = NumberValue.create((Object)this, "Benchmark Time", "#", "s", 10.0, 30.0, 60.0, 1.0);
    private boolean running = false;
    private int captureCount;
    private int fpsSum = 0;

    private void logResults() {
        Vape.debugLog("----------------------------");
        Vape.debugLog("Amount of Tests: " + this.testAmount.K());
        Vape.debugLog("Time Taken for each Tests: " + this.benchmarkTime.K() + "s");
        int n = 0;
        int n2 = 0;
        Vape.debugLog("Avg:");
        for (int i = 0; i < this.samples.size(); ++i) {
            Vape.debugLog(i + 1 + ": fps - " + this.samples.get(i).K() + " captures - " + this.samples.get((int)i).S);
            Vape.debugLog("Highest FPS: " + this.samples.get((int)i).G + " Lowest FPS: " + this.samples.get((int)i).N);
            n = (int)((double)n + this.samples.get(i).K());
            n2 = (int)((long)n2 + this.samples.get((int)i).S);
        }
        Vape.debugLog("Avg for all tests: fps - " + n / this.samples.size() + " captures - " + n2 / this.samples.size());
        Vape.debugLog("----------------------------");
    }

    @EventHandler
    public void b(EventPostRenderTick eventPostRenderTick) {
        if (!this.running) {
            if (System.currentTimeMillis() - this.lastTimestamp <= 5000L) {
                return;
            }
            Vape.debugLog("Benchmark Started");
            this.running = true;
            this.lastTimestamp = System.currentTimeMillis();
            this.captureCount = 0;
            this.lowestFps = Integer.MAX_VALUE;
            this.highestFps = Integer.MIN_VALUE;
        }
        if ((double)this.samples.size() >= (Double)this.testAmount.K()) {
            this.logResults();
            this.F();
            return;
        }
        long l = System.currentTimeMillis() - this.lastTimestamp;
        if ((double)l >= this.testDurationMillis()) {
            this.samples.add(new PacketLatencySample(this.captureCount, this.fpsSum, this.highestFps, this.lowestFps));
            Vape.debugLog("Test " + this.samples.size() + " completed");
            this.fpsSum = 0;
            this.captureCount = 0;
            this.lowestFps = Integer.MAX_VALUE;
            this.highestFps = Integer.MIN_VALUE;
            this.lastTimestamp = System.currentTimeMillis();
            return;
        }
        ++this.captureCount;
        int n = Minecraft.l();
        this.fpsSum += n;
        this.highestFps = Math.max(this.highestFps, n);
        this.lowestFps = Math.min(this.lowestFps, n);
    }

    public PacketLatencyModule() {
        super("Benchmark", -1, Category.Y);
        this.testAmount = NumberValue.create(this, "Test Amount", "#", "", 1.0, 5.0, 10.0, 1.0, "Amount of times it should benchmark to give an average");
        this.highestFps = Integer.MIN_VALUE;
        this.lowestFps = Integer.MAX_VALUE;
        this.addValue(this.benchmarkTime, this.testAmount);
    }

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private double testDurationMillis() {
        return (Double)this.benchmarkTime.K() * 1000.0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.samples.clear();
        this.running = false;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Vape.debugLog("Starting Benchmark test in 5 seconds");
        this.lastTimestamp = System.currentTimeMillis();
    }
}

